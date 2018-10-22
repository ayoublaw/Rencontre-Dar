package Controlleur.Servlet;

import Controlleur.Service.SignalCompteService;
import Model.Entity.SignalCompte;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        List<SignalCompte> ListSignalCompte = sign.ListSignalCompte();
        String json = new Gson().toJson(ListSignalCompte);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
