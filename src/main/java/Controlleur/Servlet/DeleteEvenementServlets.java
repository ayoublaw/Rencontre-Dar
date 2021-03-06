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

@WebServlet(name = "DeleteEvenementServlets",urlPatterns = "/DeleteEvenement")
public class DeleteEvenementServlets extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    EvenementService even = new EvenementService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        int evenementID = jsonobj.getInt("evenementId");
        try {
            Users userCurrent = auth.CurrentUser(request);
            even.DeleteEvenement(userCurrent,evenementID);
            JsonService.StringJsonResponse(response,"text","Evenement bien Supprimer");
        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
