package org.example.ipdp.proiect.frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EntityPanel extends JPanel {
    protected String entityName;
    protected JTable attributes;
    protected DefaultTableModel model;

    protected void addAttribute(String name, String type) {
        model.addRow(new Object[]{name, type});
    }

    protected void removeAttribute(String name) {
        int index = -1;
        int count = model.getRowCount();

        for (int i = 0; i < count; ++i) {
            if (((String) model.getValueAt(i, 0)).equals(name)) {
                index = i;
                break;
            }
        }

        model.removeRow(index);
    }

    public EntityPanel(String entityName) {
        this.entityName = entityName;
        setSize(1000, 800);

        JButton addAttributeButton = new JButton("Add Attribute");
        JButton removeAttributeButton = new JButton("Remove Attribute");
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout());
        actionsPanel.add(addAttributeButton);
        actionsPanel.add(removeAttributeButton);

        addAttributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField attributeName = new JTextField();
                JTextField attributeType = new JTextField();
                JComponent[] inputs = new JComponent[] {
                        new JLabel("Attribute name:"),
                        attributeName,
                        new JLabel("Attribute type:"),
                        attributeType
                };

                int result = JOptionPane.showConfirmDialog(null,
                        inputs,
                        "Add attribute",
                        JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    if (attributeName.getText().length() > 0
                            && attributeType.getText().length() > 0) {
                        addAttribute(attributeName.getText(), attributeType.getText());
                    } else {
                        // TODO: log error because empty field
                    }
                }
            }
        });

        removeAttributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = model.getRowCount();
                Vector<String> possibilities = new Vector<>(count);

                for (int i = 0; i < count; ++i) {
                    possibilities.add((String) model.getValueAt(i, 0));
                }

                String attributeName = (String)JOptionPane.showInputDialog(
                        null,
                        "Remove attribute:",
                        "Remove attribute",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities.toArray(),
                        null
                );

                if (attributeName != null && attributeName.length() > 0) {
                    removeAttribute(attributeName);
                }
            }
        });

        this.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Name");
        model.addColumn("Type");
        this.attributes = new JTable(model);

        setLayout(new BorderLayout());
        add(actionsPanel, BorderLayout.NORTH);
        add(new JScrollPane(attributes), BorderLayout.CENTER);
    }
}
