package org.example.ipdp.proiect.frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class State1 extends State implements ActionListener {

    @Override
    protected void userInit() {
        JButton button = new JButton("To state 2");
        button.setBounds(130, 100, 100, 40);
        button.addActionListener(this);

        frame.add(button);

        frame.setSize(400, 500);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    protected void exit() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        next(new State2());
    }
}

class State2 extends State implements ActionListener {

    @Override
    protected void userInit() {
        JButton button = new JButton("To state 1");
        button.setBounds(130, 100, 100, 40);
        button.addActionListener(this);

        frame.add(button);

        frame.setSize(400, 500);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    protected void exit() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        next(new State1());
    }
}

public class StateTest {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setTitle("Test state machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        (new State1()).init(frame);
    }
}
