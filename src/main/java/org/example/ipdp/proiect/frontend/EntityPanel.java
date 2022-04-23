package org.example.ipdp.proiect.frontend;

import javax.swing.*;
import java.awt.*;

public class EntityPanel extends JPanel {
    protected String entityName;
    protected JTable attributes;

    public EntityPanel(String entityName) {
        this.entityName = entityName;
        setSize(1000, 800);

        JButton addAttributeButton = new JButton("Add Attribute");
        JButton removeAttributeButton = new JButton("Remove Attribute");
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout());
        actionsPanel.add(addAttributeButton);
        actionsPanel.add(removeAttributeButton);

        String columnNames[] = { "Name", "Type"};
        String dataValues[][] = {{"name1", "type1"}, {"name1", "type1"}};
        this.attributes = new JTable(dataValues, columnNames);
        setLayout(new BorderLayout());
        add(actionsPanel, BorderLayout.NORTH);
        add(attributes, BorderLayout.CENTER);
    }
}
