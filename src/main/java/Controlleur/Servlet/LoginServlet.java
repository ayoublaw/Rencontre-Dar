package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Model.Entity.Users;
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
            response.sendRedirect("/");
        } catch (DataException e) {
            System.out.println(email);
            System.out.println(password);
            response.getWriter().write(e.getMessage());
        }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.html").forward(request,response);
    }
}
