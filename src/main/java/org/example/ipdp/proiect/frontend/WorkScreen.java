package org.example.ipdp.proiect.frontend;

import org.example.ipdp.proiect.misc.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class WorkScreen extends State{
    JTabbedPane entities;
    DataModel data = null;

    public WorkScreen() {
        data = new DataModel();
    }

    protected void addEntity(String entityName) {
        entities.addTab(entityName, new EntityPanel(entityName));
    }

    protected void removeEntity(String entityName) {
        entities.removeTabAt(entities.indexOfTab(entityName));
    }

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

        addEntityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entityName = (String)JOptionPane.showInputDialog(
                        frame,
                        "New entity name:",
                        "Add entity",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                if (entityName != null && entityName.length() > 0) {
                    addEntity(entityName);
                }
            }
        });

        removeEntity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = entities.getTabCount();
                Vector<String> possibilities = new Vector<>(count);

                for (int i = 0; i < count; ++i) {
                    possibilities.add(entities.getTitleAt(i));
                }

                String entityName = (String)JOptionPane.showInputDialog(
                        frame,
                        "Remove entity:",
                        "Remove entity",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities.toArray(),
                        null
                );

                if (entityName != null && entityName.length() > 0) {
                    removeEntity(entityName);
                }
            }
        });

        entities = new JTabbedPane();
        //entities.addTab("entity1", new EntityPanel("entity1"));
        //entities.addTab("entity2", new EntityPanel("entity2"));
        //entities.addTab("entity2", new EntityPanel("entity2"));

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
