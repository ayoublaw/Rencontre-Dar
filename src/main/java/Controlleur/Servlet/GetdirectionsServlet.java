package Controlleur.Servlet;

import Controlleur.Service.APIService;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetdirectionsServlet",urlPatterns = "/Directions")
public class GetdirectionsServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    APIService api =new APIService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        String adr1 = jsonobj.getString("adr1");
        String adr2 = jsonobj.getString("adr2");
        String place_id = jsonobj.getString("place_id");
        String results = api.GetPlaceDirections(adr1,adr2,place_id);
        response.setContentType("application/json");
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(results);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
