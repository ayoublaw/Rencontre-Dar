package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AddressService;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;
import Model.Dao.DaoFactory;
import Model.Entity.Address;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateAddressServlet")
public class UpdateAddressServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    AddressService adr = new AddressService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String numero = request.getParameter("numero");
        String rue = request.getParameter("rue");
        String ville = request.getParameter("ville");
        String codepostal = request.getParameter("codepostal");
        String addressID = request.getParameter("addressID");
        Address adress = DaoFactory.getAddressDao().getById(Integer.parseInt(addressID));
        try {
            Users user = auth.CurrentUser(request);
            adr.UpdateAddress(adress,nom,numero,rue,ville,codepostal,user);
            JsonService.StringJsonResponse(response,"text","Address bien changé");
        } catch (DataException e) {
            JsonService.ErrJsonResponse(response, e);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
