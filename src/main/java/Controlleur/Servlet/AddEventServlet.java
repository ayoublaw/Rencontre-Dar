package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.EvenementService;
import Controlleur.Service.JsonService;
import Model.Entity.Evenement;
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
import java.text.ParseException;

@WebServlet(name = "AddEventServlet",urlPatterns = "/Addevent")
public class AddEventServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    EvenementService even = new EvenementService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj =JsonService.getJsonObjectFromBufferReader(request.getReader());
        String decription = jsonobj.getString("description");
        String lieu = jsonobj.get("lieu") instanceof String ? (String) jsonobj.get("lieu") : null ;
        String nbrParticipants = jsonobj.getString("nbrParticipants");
        String date = jsonobj.getString("date");
        String CentreInt = jsonobj.getString("CentreInt");
        String adr_proposer = jsonobj.getString("adr_proposer");

        try {
            Users currentUser = auth.CurrentUser(request);
            even.AddEvenement(currentUser,adr_proposer, decription, lieu, nbrParticipants, date, CentreInt);

            JsonObject jsonO = new JsonObject();
            jsonO.addProperty("message","Address bien enregistré");
            String json = new Gson().toJson(jsonO);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);
            response.getWriter().write(json);

        } catch (DataException e) {
            JsonService.ErrJsonResponse(response, e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
