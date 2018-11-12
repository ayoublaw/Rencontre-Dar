package Controlleur.Servlet;

import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SessionServlets",urlPatterns = "/Session")
public class SessionServlets extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        JsonService.StringJsonResponse(response,"session", (String) session.getAttribute("Email"));
    }
}
