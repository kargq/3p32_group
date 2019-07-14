package main;

import org.hibernate.Session;

public class DbTest {
    public static void main(String[] args) {
        Session session = Database.openSession();
        session.createQuery("SELECT C FROM GemModel C").list().forEach(System.out::println);
        session.close();
    }
}
