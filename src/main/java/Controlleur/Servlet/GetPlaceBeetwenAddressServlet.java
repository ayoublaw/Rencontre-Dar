package Controlleur.Servlet;

import Controlleur.Service.APIService;
import Controlleur.Service.AuthentificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetPlaceBeetwenAddressServlet")
public class GetPlaceBeetwenAddressServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    APIService api =new APIService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adr1 = request.getParameter("adr1");
        String adr2 = request.getParameter("adr2");
        String type = request.getParameter("type");
        String results = api.GetPLaceBeetwenTwoAddress(adr1,adr2,type);
        response.setContentType("application/json");
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(results);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
