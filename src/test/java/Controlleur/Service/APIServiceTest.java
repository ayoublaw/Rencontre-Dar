package Controlleur.Service;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class APIServiceTest {
    APIService api = new APIService();
    @Test
    public void APITest(){
        String json =api.GetPLaceBeetwenTwoAddress("5 rue paul dautier 78140","142 Rue Montmatre 75005","restaurant");
        System.out.println(json);
        Assert.assertNotNull(json);
    }
    @Test
    public void APInearbyTest(){
        String json =api.GetPlaceNearbyAddress("5 rue paul dautier 78140","restaurant");
        System.out.println(json);
        Assert.assertNotNull(json);
    }
    @Test
    public void APIDirectionsTest() throws ParseException {
        String json =api.GetPlaceDirections("5 rue paul dautier 78140","142 Rue Montmatre 75005",new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("10/11/2018 22:30:00").getTime());
        System.out.println(json);
        Assert.assertNotNull(json);
    }

}