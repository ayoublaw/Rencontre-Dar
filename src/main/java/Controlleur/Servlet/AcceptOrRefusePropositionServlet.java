package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.EvenementService;
import Controlleur.Service.JsonService;
import Model.Entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AcceptOrRefusePropositionServlet")
public class AcceptOrRefusePropositionServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    EvenementService even = new EvenementService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String evenementID = request.getParameter("evenementID");
        String lieu = request.getParameter("lieu");
        Boolean b = Boolean.parseBoolean(request.getParameter("decision"));
        try {
            Users user = auth.CurrentUser(request);
            even.AcceptOrRefuseProposition(user,Integer.parseInt(evenementID),b,lieu);
            JsonService.StringJsonResponse(response,"text","Demande enregistré");

        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
