package org.example.ipdp.proiect.frontend;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setTitle("Test state machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //(new HomeScreen()).init(frame);
        (new WorkScreen()).init(frame);
    }
}
