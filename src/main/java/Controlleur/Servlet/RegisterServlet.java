package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;
import Model.Entity.Users;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Email = request.getParameter("email");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String[] centreInt = request.getParameterValues("centreInt");


        try {
            Users client = auth.register(Email,nom,prenom,age,sex,password,centreInt);
            HttpSession session = request.getSession(true);
            session.setAttribute("Email",Email);
            session.setAttribute("Role",client.getRole());
            response.sendRedirect("/");
        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.html").forward(request,response);
    }
}
