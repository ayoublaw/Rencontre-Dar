package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.EvenementService;
import Controlleur.Service.JsonService;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ParticipateServlet" ,urlPatterns = "/Participate")
public class ParticipateServlet extends HttpServlet {
    private AuthentificationService auth = new AuthentificationService();
    private EvenementService event = new EvenementService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        Integer evenementID = jsonobj.getInt("Id");
        String lieu =null;
        if(jsonobj.get("lieu") != null) {
            lieu =jsonobj.get("lieu") instanceof String ? (String) jsonobj.get("lieu") : null ;
        }
        try {
            Users user = auth.CurrentUser(request);
            event.ParticipateInEvenement(user,evenementID,lieu);
            JsonService.StringJsonResponse(response,"message","Participation Enregistré");
        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
