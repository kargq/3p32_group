package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {
    private static SessionFactory sessionFactory;

    public static Session openSession(){
        if (sessionFactory == null){
            Configuration config = new Configuration();
            config.configure();
            sessionFactory = config.buildSessionFactory();
        }
        return sessionFactory.openSession();
    }

    public static void close(){
        sessionFactory.close();
        sessionFactory = null;
    }
}
