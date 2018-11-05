package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.JsonService;
import Controlleur.Service.SignalCompteService;
import Model.Entity.SignalCompte;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebServlet(name = "ListeSignalCompteServlet")
public class ListeSignalCompteServlet extends HttpServlet {
    SignalCompteService sign = new SignalCompteService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SignalCompte> ListSignalCompte = null;
        try {
        ListSignalCompte = sign.ListSignalCompte();
        HttpSession session = request.getSession(true);
        if(session.isNew() || !session.getAttribute("Role").equals("Admin")){
            String json = new Gson().toJson("ERR : you don't have permition");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(404);
            response.getWriter().write(json);

        }
        String json = new Gson().toJson(ListSignalCompte);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        response.getWriter().write(json);

        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }
    }
}
