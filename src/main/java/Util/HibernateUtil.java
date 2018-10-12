package Util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure( "hibernate.cfg.xml" )
                    .build();

            Metadata metadata = new MetadataSources( standardRegistry )
                    .getMetadataBuilder()
                    .build();

            return metadata.getSessionFactoryBuilder().build();

        }
        catch (Throwable E){
            throw new ExceptionInInitializerError(E);
        }
    }
    public static SessionFactory getSessionFactory(){
        return buildSessionFactory();
    }
}

