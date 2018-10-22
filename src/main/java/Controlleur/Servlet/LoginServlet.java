package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mysql.fabric.xmlrpc.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

     AuthentificationService auth = new AuthentificationService();

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String email = request.getParameter("email");
         String password = request.getParameter("password");

        try {
            Users client = auth.Checklogin(email,password);
            HttpSession session = request.getSession(true);
            session.setAttribute("Email",email);
            session.setAttribute("Role",client.getRole());
            response.sendRedirect("/");
        } catch (DataException e) {
            JsonObject json = new JsonObject();
            json.addProperty("erreur",e.toString());
            String r = json.toString();
            response.setStatus(404);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(r);

        }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.html").forward(request,response);
    }
}
