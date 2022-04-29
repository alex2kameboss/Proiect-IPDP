package org.example.ipdp.proiect.frontend;

import javax.swing.*;
import java.awt.*;

public class WorkScreen extends State{
    JTabbedPane entities;

    @Override
    protected void userInit() {
        JButton saveButton = new JButton("Save");
        JButton undoButton = new JButton("Undo");
        JButton addEntityButton = new JButton("Add Entity");
        JButton removeEntity = new JButton("Remove Entity");
        JButton closeProjectButton = new JButton("Close project");

        JPanel buttonsRaw = new JPanel();
        buttonsRaw.setLayout(new FlowLayout());

        buttonsRaw.add(saveButton);
        buttonsRaw.add(undoButton);
        buttonsRaw.add(addEntityButton);
        buttonsRaw.add(removeEntity);
        buttonsRaw.add(closeProjectButton);

        entities = new JTabbedPane();
        entities.addTab("entity1", new EntityPanel("entity1"));
        entities.addTab("entity2", new EntityPanel("entity2"));
        entities.addTab("entity2", new EntityPanel("entity2"));

        frame.setLayout(new BorderLayout());
        frame.add(buttonsRaw, BorderLayout.NORTH);
        frame.add(entities, BorderLayout.CENTER);

        frame.setSize(1500, 1000);
        frame.setVisible(true);
    }

    @Override
    protected void exit() {

    }
}
