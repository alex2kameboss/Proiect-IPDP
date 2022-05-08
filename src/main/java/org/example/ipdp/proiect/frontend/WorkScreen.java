package org.example.ipdp.proiect.frontend;

import org.example.ipdp.proiect.actions.AddEntityAction;
import org.example.ipdp.proiect.actions.RemoveEntityAction;
import org.example.ipdp.proiect.misc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class WorkScreen extends State{
    JTabbedPane entities;
    HistoryManager history;

    public WorkScreen() {
        history = new HistoryManager(new IStorage() {
            @Override
            public void addEntity(Entity entity) {

            }

            @Override
            public Entity removeEntity(Entity entity) {
                return null;
            }

            @Override
            public Entity updateEntity(Entity oldEntity, Entity newEntity) {
                return null;
            }

            @Override
            public void addAttribute(String parent, Attribute attribute) {

            }

            @Override
            public Attribute removeAttribute(String parentName, Attribute attribute) {
                return null;
            }

            @Override
            public Attribute updateAttribute(String parentName, Attribute oldAttribute, Attribute newAttribute) {
                return null;
            }

            @Override
            public void addRelation(Relation relation) {

            }

            @Override
            public Relation removeRelation(Relation relation) {
                return null;
            }

            @Override
            public Relation updateRelation(Relation oldRelation, Relation newRelation) {
                return null;
            }

            @Override
            public DataModel getDataModel() {
                return new DataModel();
            }

            @Override
            public boolean applyActions(List<IAction> actions) {
                return false;
            }
        });
    }

    protected void addEntity(String entityName) {
        entities.addTab(entityName, new EntityPanel(entityName, this));

        history.addAction(new AddEntityAction(new Entity(entityName)));
    }

    protected void removeEntity(String entityName) {
        entities.removeTabAt(entities.indexOfTab(entityName));

        history.addAction(new RemoveEntityAction(new Entity(entityName)));
    }

    protected void redrawDataModel() {
        entities.removeAll();

        for (Entity entity: history.getData().getEntities()){
            entities.addTab(entity.getEntityName(),
                    new EntityPanel(entity, this));
        }
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

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                history.undoAction();

                redrawDataModel();
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

        redrawDataModel();
    }

    @Override
    protected void exit() {

    }
}
