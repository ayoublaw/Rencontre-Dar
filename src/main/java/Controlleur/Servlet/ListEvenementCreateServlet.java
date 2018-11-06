package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.EvenementService;
import Controlleur.Service.JsonService;
import Model.Entity.Evenement;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListEvenementCreateServlet",urlPatterns = "/Listcreate")
public class ListEvenementCreateServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    EvenementService even = new EvenementService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Users usercurrent = auth.CurrentUser(request);
            List<Evenement> list = even.ListEvenementCreateActif(usercurrent);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(list);
            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }
    }
}
