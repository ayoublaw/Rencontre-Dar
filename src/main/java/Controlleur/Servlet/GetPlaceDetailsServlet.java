package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.APIService;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;
import Model.Entity.Users;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetPlaceDetailsServlet",urlPatterns = "/getPlaceDetails")
public class GetPlaceDetailsServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    APIService api =new APIService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        String place_id = jsonobj.getString("id");
        try {
            Users user = auth.CurrentUser(request);
            String results = api.GetPlaceDetails(place_id);
            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(results);

        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
