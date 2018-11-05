package Controlleur.Service;

import Controlleur.Exception.DataException;
import Model.Dao.DaoFactory;
import Model.Entity.Address;
import Model.Entity.Users;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    public void AddAddress(String[] nom, String[] numero, String[] rue, String[] ville, String[] codepostal, Users user){
        List<Address> listaddress = new ArrayList<Address>();
        for (int i = 0; i < nom.length; i++) {
            Address adr = new Address();
            adr.setNom(nom[i]);
            adr.setNumero(Integer.parseInt(numero[i]));
            adr.setRue(rue[i]);
            adr.setVille(ville[i]);
            adr.setCodePostal(Integer.parseInt(codepostal[i]));
            adr.setUser(user);
            DaoFactory.getAddressDao().Save(adr);
            listaddress.add(adr);
        }
        user.getAddress().addAll(listaddress);
        DaoFactory.getUsersDao().update(user);
    }
    public void UpdateAddress(Address address,String nom,String numero, String rue, String ville, String codepostal,Users user) throws DataException {
        if(address.getUser() == user ){
            throw new DataException("you don't have this Address");
        }
        if(nom != null){
            address.setNom(nom);
        }
        if(rue != null){
            address.setRue(rue);
        }if(numero !=null ){
            address.setNumero(Integer.parseInt(numero));
        }if(ville != null){
            address.setVille(ville);
        }
        if(codepostal != null){
            address.setCodePostal(Integer.parseInt(codepostal));
        }
        DaoFactory.getAddressDao().update(address);
    }
    public void DeleteAddress(String nom ,Users user) throws DataException {

        Address adr= DaoFactory.getAddressDao().selectUserOneAddress(user,nom);
        if(adr == null){
            throw new DataException("you don't have this address");
        }
        DaoFactory.getAddressDao().remove(adr);
    }
    public List<Address> getUserAddress(Users user) throws DataException {
        List<Address> adr = DaoFactory.getAddressDao().selectUser(user);
        if(adr == null || adr.isEmpty()){
            throw new DataException("You don't have any Address");
        }
        return adr;
    }
}
