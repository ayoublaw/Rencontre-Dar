package Controlleur.Service;

import Controlleur.Exception.DataException;
import Model.Dao.DaoFactory;
import Model.Entity.Address;
import Model.Entity.CentreInt;
import Model.Entity.Users;
import com.google.common.hash.Hashing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class AuthentificationService {

    public Users Checklogin(String Email , String Password) throws DataException{
        Users user = DaoFactory.getUsersDao().GetUserByEmail(Email);
        if(user == null || Password.isEmpty() || !user.getPassword().equals(Hashing.sha256().hashString(Password, StandardCharsets.UTF_8).toString()))
        {
            throw new DataException("Email or Username Invalid");
        }
        return user;
    }

    public Users register(String Email,String nom,String Prenom,int age,String sex,String password,String[] centreInt) throws DataException {
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        if(DaoFactory.getUsersDao().GetUserByEmail(Email) != null){
            throw new DataException("Email Already used");
        }
        Users user = new Users();
        user.setPrenom(Prenom);
        user.setSex(sex);
        user.setPassword(hashedPassword);
        user.setAge(age);
        user.setEmail(Email);
        user.setNom(nom);
        user.setEtat(Users.Etat.Actif);
        user.setRole(Users.Roles.Utilisateur);
        DaoFactory.getUsersDao().Save(user);

        List<CentreInt> listCentreInt = new ArrayList<CentreInt>();
        for (String s :centreInt){
            CentreInt centreint = new CentreInt();
            centreint.setUsers(user);
            centreint.setName(s);
            listCentreInt.add(centreint);
            DaoFactory.getCenterIntDao().Save(centreint);
        }
        user.setCentreInteret(listCentreInt);
        DaoFactory.getUsersDao().update(user);
        return user;
    }

    public CentreInt AddCentreIntList(String Name, Users user ){
            CentreInt centreint = new CentreInt();
            List<CentreInt> ListCentreint  = user.getCentreInteret();

            centreint.setName(Name);
            centreint.setUsers(user);
            ListCentreint.add(centreint);
            user.setCentreInteret(ListCentreint);

            DaoFactory.getCenterIntDao().Save(centreint);
            DaoFactory.getUsersDao().update(user);
        return centreint;
    }
    public Address AddAddress(String Nom,String rue,String ville,int CodePostal,Users user){
            Address adr = new Address();
            List<Address> ListAdr = user.getAddress();

            adr.setUser(user);
            adr.setRue(rue);
            adr.setNom(Nom);
            adr.setVille(ville);
            adr.setCodePostal(CodePostal);
            ListAdr.add(adr);
            user.setAddress(ListAdr);

            DaoFactory.getAddressDao().Save(adr);
            DaoFactory.getUsersDao().update(user);
        return adr;
    }
    public Users CurrentUser(HttpServletRequest request) throws DataException {
        HttpSession session = request.getSession(true);
        if(session.isNew()){
        throw new DataException("You are not connecting");
        }
        Users user =  DaoFactory.getUsersDao().GetUserByEmail((String) session.getAttribute("Email"));
        if(user == null){
            throw new DataException("User not exist");
        }
        if(user.getEtat().equals(Users.Etat.Suspendu)){
            throw new DataException("you are Suspendu");
        }
        return user;
    }
}
