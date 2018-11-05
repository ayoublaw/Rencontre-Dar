package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.EvenementService;
import Controlleur.Service.JsonService;
import Model.Entity.Evenement;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "AddEventServlet",urlPatterns = "/Addevent")
public class AddEventServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    EvenementService even = new EvenementService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String decription = request.getParameter("description");
        String lieu = request.getParameter("lieu");
        String nbrParticipants = request.getParameter("nbrParticipants");
        String date = request.getParameter("date");
        String CentreInt = request.getParameter("CentreInt");
        try {
            Users currentUser = auth.CurrentUser(request);
            even.AddEvenement(currentUser, decription, lieu, nbrParticipants, date, CentreInt);

            JsonObject jsonO = new JsonObject();
            jsonO.addProperty("Text","Address bien enregistré");
            String json = new Gson().toJson(jsonO);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);
            response.getWriter().write(json);

        } catch (DataException | ParseException e) {
            JsonService.ErrJsonResponse(response, (DataException) e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
