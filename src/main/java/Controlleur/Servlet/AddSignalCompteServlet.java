package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.SignalCompteService;
import Model.Dao.DaoFactory;
import Model.Entity.SignalCompte;
import Model.Entity.Users;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddSignalCompteServlet")
public class AddSignalCompteServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    SignalCompteService sign = new SignalCompteService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String description = request.getParameter("description");
        try {
            Users currentUser = auth.CurrentUser(request);
            sign.AddSignalCompte(nom,prenom,currentUser,description);
            response.getWriter().write("Good");

        } catch (DataException e) {
            e.printStackTrace();
            response.getWriter().write("False");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
