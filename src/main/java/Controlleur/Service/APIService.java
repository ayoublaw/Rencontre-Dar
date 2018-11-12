package Controlleur.Service;

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
            System.out.println(tmp);
            JSONObject obj = new JSONObject(tmp);
            conn.disconnect();
            return obj;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String GetPLaceBeetwenTwoAddress(String adr1,String adr2,String type){
        JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/directions/json?origin="+adr1+"&destination="+adr2+"&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        JSONArray tab = (JSONArray) json.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
        int iteration = tab.length()%2 == 0 ? tab.length()/2 : (tab.length()/2)+1;


        Double lat = (Double) tab.getJSONObject(iteration).getJSONObject("end_location").get("lat");
        Double lng = (Double) tab.getJSONObject(iteration).getJSONObject("end_location").get("lng");

        JSONObject resu = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius=1500&type="+type+"&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        if(resu == null){
            return null;
        }
        JSONArray resultas = (JSONArray) resu.get("results");
        return resultas.toString();
    }
    public String GetPlaceNearbyAddress(String adr,String type){
       JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+adr+"&inputtype=textquery&fields=geometry/location&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        Double lat = (Double) json.getJSONArray("candidates").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat");
        Double lng = (Double) json.getJSONArray("candidates").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng");

        JSONObject resu = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius=1500&type="+type+"&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        if(resu == null){
            return null;
        }
        JSONArray resultas = (JSONArray) resu.get("results");
        return resultas.toString();
    }
    public String GetPlaceDetails(String place_id){
        JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/details/json?placeid="+place_id+"&fields=address_component,adr_address,alt_id,formatted_address,geometry,icon,id,name,permanently_closed,photo,place_id,plus_code,scope,type,url,utc_offset,vicinity&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        return json.toString();
    }
    public String GetPlaceDirections(String adr1, String adr2){
        JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/directions/json?origin="+adr1+"&destination="+adr2+"&mode=transit&transit_mode=rail&arrival_time&key=AIzaSyCCP9mY6YG5WyvRqftQQdavXQW6Rm4u9es\n");
        return json.toString();
    }
}
