package org.example.ipdp.proiect.frontend;

import org.apache.cayenne.configuration.Constants;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.configuration.server.ServerRuntimeBuilder;
import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.MapBuilder;
import org.apache.cayenne.di.Module;
import org.example.ipdp.proiect.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class HomeScreen extends State{
    protected JTextField path = null;

    public HomeScreen getRef() {
        return this;
    }

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
                    DataPassing.getContext().clear();
                    DataPassing.getContext().addData("PATH", path.getText());
                    DataPassing.getContext().addData("CONTEXT", new FileContext(path.getText()));
                    Log.getContext().setLogFile(path.getText());
                    next(Objects.requireNonNull(NextStateFactory.getNextState(getRef())));

                    System.out.println("Dir empty");
                } else {
                    // TODO: show error
                    Log.getContext().error("Directory not empty: " + path.getText());
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
