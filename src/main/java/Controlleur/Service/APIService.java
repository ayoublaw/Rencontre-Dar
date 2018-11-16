package Controlleur.Service;

import Controlleur.Exception.DataException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class APIService {
    private JSONObject GetJsonFromUrl(String url){
        url = url.replaceAll(" ","%20");
        URL Url;
        String tmp = "";
        String line;
        try {
            Url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            while ((line = br.readLine()) != null) {
                    tmp += line;
                }
            JSONObject obj = new JSONObject(tmp);
            conn.disconnect();
            return obj;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String GetPLaceBeetwenTwoAddress(String adr1,String adr2,String type) throws DataException {
        System.out.println("Adresse 1 Get place beetwen : "+adr1);
        System.out.println("Adresse 2 Get place beetwen : "+adr2);
        System.out.println("Type Get place beetwen : "+type);
        JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/directions/json?origin="+adr1+"&destination="+adr2+"&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es");
        if(json == null){
            throw new DataException("Directions inconnu");
        }
        JSONArray tab = (JSONArray) json.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
        if(tab == null || tab.length() == 0){
            throw new DataException("les places dans la route not found");
        }
        int iteration = tab.length()%2 == 0 ? tab.length()/2 : (tab.length()/2)+1;

        Double lat = (Double) tab.getJSONObject(iteration).getJSONObject("end_location").get("lat");
        Double lng = (Double) tab.getJSONObject(iteration).getJSONObject("end_location").get("lng");

        JSONObject resu = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius=1500&type="+type+"&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es");
        if(resu == null){
            throw new DataException("les places a coté found");
        }
        JSONArray resultas = (JSONArray) resu.get("results");
        if(resultas.length() == 0){
            throw new DataException("les resultas des places est vides");
        }
        return limitArray(resultas,3).toString();
    }
    public String GetPlaceNearbyAddress(String adr,String type){
       JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+adr+"&inputtype=textquery&language=fr&fields=geometry/location&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        Double lat = (Double) json.getJSONArray("candidates").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat");
        Double lng = (Double) json.getJSONArray("candidates").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng");

        JSONObject resu = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius=1500&language=fr&type="+type+"&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        if(resu == null){
            return null;
        }
        JSONArray resultas = (JSONArray) resu.get("results");
        return limitArray(resultas,3).toString();
    }
    public String GetPlaceDetails(String place_id){
        JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/details/json?placeid="+place_id+"&fields=address_component,adr_address,alt_id,formatted_address,geometry,icon,id,name,permanently_closed,photo,place_id,plus_code,scope,type,url,utc_offset,vicinity&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        return json.toString();
    }
    public String GetPlaceDirections(String adr1, String adr2){
        JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/directions/json?origin="+adr1+"&destination="+adr2+"&mode=transit&transit_mode=rail&arrival_time&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        json.put("adr",adr1);
        return json.toString();
    }
    public JSONArray limitArray(JSONArray array ,int limit){
        JSONArray json =new JSONArray();
        if(array.length() <= limit){
            return array;
        }
        for (int i = 0; i < limit; i++) {

            json.put(array.getJSONObject(i));
        }
        return json;
    }
}
