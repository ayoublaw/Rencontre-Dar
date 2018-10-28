package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AddressService;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteAddressServlet")
public class DeleteAddressServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    AddressService adr = new AddressService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        try {
            Users user = auth.CurrentUser(request);
            adr.DeleteAddress(nom,user);
            JsonService.StringJsonResponse(response,"text","ADDRESS bien Supprimé");
        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
