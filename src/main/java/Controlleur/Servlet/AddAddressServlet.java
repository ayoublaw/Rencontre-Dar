package Controlleur.Servlet;

import Controlleur.Exception.DataException;
import Controlleur.Service.AddressService;
import Controlleur.Service.AuthentificationService;
import Controlleur.Service.JsonService;
import Model.Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddAddressServlet",urlPatterns = "/addAdress")
public class AddAddressServlet extends HttpServlet {
    AuthentificationService auth = new AuthentificationService();
    AddressService adr = new AddressService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj =JsonService.getJsonObjectFromBufferReader(request.getReader());
        String nom = jsonobj.getString("nom");
        String numero = jsonobj.getString("numero");
        String rue = jsonobj.getString("rue");
        String ville = jsonobj.getString("ville");
        String codepostal = jsonobj.getString("codepostal");
        try {
            Users user = auth.CurrentUser(request);
            adr.AddAddress(nom,numero,rue,ville,codepostal,user);

            JsonObject jsonO = new JsonObject();
            jsonO.addProperty("message","Address bien enregistré");
            String json = new Gson().toJson(jsonO);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);
            response.getWriter().write(json);
        } catch (DataException e) {
            JsonService.ErrJsonResponse(response,e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
