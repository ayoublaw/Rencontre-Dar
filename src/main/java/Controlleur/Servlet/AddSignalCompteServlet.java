package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;
import Controlleur.Service.SignalCompteService;
import Model.Dao.DaoFactory;
import Model.Entity.SignalCompte;
import Model.Entity.Users;
import org.hibernate.Session;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddSignalCompteServlet",urlPatterns = "/SignalCompte")
public class AddSignalCompteServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    SignalCompteService sign = new SignalCompteService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        String nom = jsonobj.getString("nom");
        String prenom = jsonobj.getString("prenom");
        String description = jsonobj.getString("description");
        try {
            Users currentUser = auth.CurrentUser(request);
            sign.AddSignalCompte(nom,prenom,currentUser,description);
            JsonService.StringJsonResponse(response,"message","message bien envoyer");
        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
