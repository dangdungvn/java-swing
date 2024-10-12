package com.noitru.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value + "");
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                component.setBackground(Color.WHITE);
                if (component instanceof JLabel jLabel) {
                    jLabel.setHorizontalAlignment(JLabel.CENTER);
                }
                setBorder(noFocusBorder);
                if (isSelected) {
                    component.setForeground(new Color(221, 221, 221));
                    component.setBackground(new Color(153, 153, 255));
                } else {
                    component.setForeground(new Color(102, 102, 102));
                    component.setBackground(Color.WHITE);
                }
                return component;
            }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
