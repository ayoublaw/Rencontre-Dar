package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AddressService;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;
import Model.Entity.Address;
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

@WebServlet(name = "ListAddressServlet",urlPatterns = "/Address")
public class ListAddressServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    AddressService adr = new AddressService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Users user = auth.CurrentUser(request);
            List<Address> listAddress = adr.getUserAddress(user);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(listAddress);

            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (DataException e) {
            JsonService.ErrJsonResponse(response, e);
        }
    }


}
