package Controlleur.Service;

import Controlleur.Exception.DataException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonService {
    public static void ErrJsonResponse(HttpServletResponse response, DataException e) throws IOException {
        JsonObject jsonO = new JsonObject();
        jsonO.addProperty("Err",e.toString());
        String json = new Gson().toJson(jsonO);

        response.setContentType("application/json");
        response.setStatus(404);
        response.setCharacterEncoding("UTF-8");
        response.sendError(404,e.getMessage());
        response.getWriter().write(json);
    }
    public static void StringJsonResponse(HttpServletResponse response,String attribut,String text) throws IOException {
        JsonObject jsonO = new JsonObject();
        jsonO.addProperty(attribut,text);
        String json = new Gson().toJson(jsonO);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        response.getWriter().write(json);

    }
    public static JSONObject getJsonObjectFromBufferReader(BufferedReader b) throws IOException {
        StringBuilder s = new StringBuilder();
        String line;
        while((line= b.readLine()) != null){
            s.append(line);
        }
        return new JSONObject(s.toString());
    }
        public static String[] JsonArraytoStringArray(JSONArray array){
        String[] str = new String[array.length()];
        for(int i = 0;i<array.length();i++){
            str[i] = array.getString(i);
        }
        return str;

        }
}
