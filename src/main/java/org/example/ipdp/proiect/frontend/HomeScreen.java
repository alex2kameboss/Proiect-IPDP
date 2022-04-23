package org.example.ipdp.proiect.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class HomeScreen extends State{
    protected JTextField path = null;

    @Override
    protected void userInit() {
        path = new JTextField(50);
        JButton selectDirectory = new JButton("Select...");
        JButton openProject = new JButton("Open Project");
        JButton createProject = new JButton("Create Project");

        selectDirectory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Select project directory");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    path.setText(chooser.getCurrentDirectory().toString());
                }
            }
        });

        createProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File dir = new File(path.getText());
                if(dir.isDirectory() && Objects.requireNonNull(dir.list()).length == 0) {
                    // TODO: move to next state
                    System.out.println("Dir empty");
                } else {
                    // TODO: show error
                    System.out.println("Dir not empty");
                }
            }
        });

        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();

        row1.setLayout(new FlowLayout());
        row2.setLayout(new FlowLayout());

        row1.add(new JLabel("Path: "));
        row1.add(path);
        row1.add(selectDirectory);
        row2.add(openProject);
        row2.add(createProject);

        frame.setLayout(new GridLayout(2,1));
        frame.add(row1);
        frame.add(row2);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void exit() {
        path = null;
    }
}
