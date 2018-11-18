package Controlleur.Servlet;

import Controlleur.Service.APIService;
import Controlleur.Service.JsonService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetPlaceNearbyServlet" , urlPatterns = "/GetPlaceNearby")
public class GetPlaceNearbyServlet extends HttpServlet {
    APIService api =new APIService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        String adr = jsonobj.getString("adr");
        String type = jsonobj.getString("type");
        String results = api.GetPlaceNearbyAddress(adr,type);
        response.setContentType("application/json");
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(results);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
