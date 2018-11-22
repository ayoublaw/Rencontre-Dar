package Controlleur.Servlet;

import Controlleur.Service.JsonService;
import Controlleur.Service.SignalCompteService;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ConfirmSignalCompteServlet",urlPatterns = "/ConfirmSignalCompte")
public class ConfirmSignalCompteServlet extends HttpServlet {
    SignalCompteService sign = new SignalCompteService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        String Email = jsonobj.getString("Email");
        HttpSession session = request.getSession(true);
        if(session.isNew() || !session.getAttribute("Role").equals("Admin")){
            String json = new Gson().toJson("ERR : you don't have permition");
            response.setStatus(404);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        sign.ConfirmSignalCompte(Email);
        JsonService.StringJsonResponse(response,"message","L'utilisateur "+Email+" est bloqué");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
