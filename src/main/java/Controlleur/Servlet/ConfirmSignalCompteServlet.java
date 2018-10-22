package Controlleur.Servlet;

import Controlleur.Service.SignalCompteService;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ConfirmSignalCompteServlet")
public class ConfirmSignalCompteServlet extends HttpServlet {
    SignalCompteService sign = new SignalCompteService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        HttpSession session = request.getSession(true);
        if(session.isNew() || !session.getAttribute("Role").equals("Admin")){
            String json = new Gson().toJson("ERR : you don't have permition");
            response.setStatus(404);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }

        sign.ConfirmSignalCompte(nom,prenom);

        Gson gson= new Gson();
        JsonElement jsonElement=gson.toJsonTree(nom);
        jsonElement.getAsJsonObject().addProperty("prenom",prenom);
        jsonElement.getAsJsonObject().addProperty("resultas","Confirmé");
        String json = gson.toJson(jsonElement);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
