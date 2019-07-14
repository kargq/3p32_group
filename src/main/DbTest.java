package main;

import org.hibernate.Session;

public class DbTest {
    public static void main(String[] args) {
        try {
            Session session = Database.openSession();
            session.createQuery("SELECT C FROM ClazzModel C").list().forEach(System.out::println);
            session.close();
            System.out.println("It is probably working");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Don't think it's working");
        }

    }
}
