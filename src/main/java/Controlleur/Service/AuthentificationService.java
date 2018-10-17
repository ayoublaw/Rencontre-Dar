package Controlleur.Service;

import Controlleur.Exception.DataException;
import Model.Dao.DaoFactory;
import Model.Entity.Address;
import Model.Entity.CentreInt;
import Model.Entity.Users;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
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

    public Users register(String Email,String nom,String Prenom,int age,String sex,String password) throws DataException {
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
        DaoFactory.getUsersDao().Save(user);
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
}
