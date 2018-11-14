package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.EvenementService;
import Controlleur.Service.JsonService;
import Model.Entity.Users;
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
        String evenementID = jsonobj.getString("Id");
        String lieu = (String) jsonobj.get("lieu");
        try {
            Users user = auth.CurrentUser(request);
            event.ParticipateInEvenement(user,Integer.parseInt(evenementID),lieu);
            JsonService.StringJsonResponse(response,"message","Participation Enregistré");
        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
