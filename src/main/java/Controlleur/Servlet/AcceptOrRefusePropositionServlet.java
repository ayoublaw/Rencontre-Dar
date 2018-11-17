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

@WebServlet(name = "AcceptOrRefusePropositionServlet" ,urlPatterns = "/acceptOrrefuse")
public class AcceptOrRefusePropositionServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    EvenementService even = new EvenementService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        int evenementID = jsonobj.getInt("evenementID");
        Boolean b = jsonobj.getBoolean("decision");
        try {
            Users user = auth.CurrentUser(request);
            even.AcceptOrRefuseProposition(user,evenementID,b);
            JsonService.StringJsonResponse(response,"message","Demande enregistré");

        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
