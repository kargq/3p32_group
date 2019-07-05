import javax.swing.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;



public class Main {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setContentPane(new Menu().getPanel());
        f.setVisible(true);
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void close() {
        sessionFactory.close();
        sessionFactory = null;
    }

    public Session hello() {
        if (sessionFactory == null) {
            Configuration config = new Configuration();
            config.configure();
            sessionFactory = config.buildSessionFactory();
        }
        return sessionFactory.openSession();
    }
}
