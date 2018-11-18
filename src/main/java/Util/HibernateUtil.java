package Util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.net.URI;

public class HibernateUtil {
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            final String DATABASE_URL = System.getenv("DATABASE_URL");
            if (DATABASE_URL != null && !DATABASE_URL.isEmpty()) {

                URI dbUri = new URI(DATABASE_URL);
                String dbUrl = System.getenv("JDBC_DATABASE_URL");
                configuration.setProperty("hibernate.connection.url", dbUrl);
                configuration.getProperties().list(System.out);
            }


            StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder();
            registry.configure("hibernate.cfg.xml").applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = registry.build();

            return configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }
    public static SessionFactory getSessionFactory(){
        return buildSessionFactory();
    }
}

