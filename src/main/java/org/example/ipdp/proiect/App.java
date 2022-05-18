package org.example.ipdp.proiect;

import org.example.ipdp.proiect.frontend.HomeScreen;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JFrame frame=new JFrame();
        frame.setTitle("Proiect IPDP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        (new HomeScreen()).init(frame);
    }
}
