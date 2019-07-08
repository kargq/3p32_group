package main;

import javax.swing.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Random;


public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setContentPane(new Menu().getPanel());
        f.setVisible(true);
        f.setSize(750, 610);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
