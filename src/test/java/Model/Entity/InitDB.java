package Model.Entity;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Model.Dao.DaoFactory;
import org.junit.Assert;
import org.junit.Test;

public class InitDB{
@Test
public void InitUserDB() throws DataException {
    AuthentificationService auth = new AuthentificationService();
    auth.register("Ayoublaw123@gmail.com","MOuhri","Ayoub",25,"M","147");
    Assert.assertNotNull(DaoFactory.getUsersDao().GetUserByEmail("Ayoublaw123@gmail.com"));
}
}
