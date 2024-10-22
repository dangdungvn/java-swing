package com.noitru.form;

import com.noitru.BaoCao;

import com.noitru.model.Model_BaoCao;
import com.noitru.swing.ScrollBar;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Form_4 extends javax.swing.JPanel {

    public Form_4() {
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        mbnCb.addActionListener((ActionEvent e) -> {
            String text = mbnCb.getSelectedItem().toString();
            if (text != null && !text.equals("--Chọn Mã BS--")) {
                ((DefaultTableModel) table.getModel()).setRowCount(0);
                SwingUtilities.invokeLater(() -> {
                    List<Model_BaoCao> thongTinKhamBenhList = BaoCao.timKiemTheoMaBN(text);
                    for (Model_BaoCao thongTinKhamBenh : thongTinKhamBenhList) {
                        Object[] row = {thongTinKhamBenh.getTenBN(), thongTinKhamBenh.getDiaChi(), thongTinKhamBenh.getCCCD(),
                            thongTinKhamBenh.getGioiTinh(), thongTinKhamBenh.getChuanDoanSoBo(), thongTinKhamBenh.getChuyenKhoa(),
                            thongTinKhamBenh.getHuongDieuTri()};
                        table.addRow(row);
                    }
                });
            } else {
                loadDB();
            }
        });
        loadDB();
        loadCB();
    }

    private void loadCB() {
        List<Model_BaoCao> thongTinKhamBenhList = BaoCao.getAllThongTin();
        for (Model_BaoCao mbc : thongTinKhamBenhList) {
            mbnCb.addItem(mbc.getMaBN());
        }
    }

    private void loadDB() {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<Model_BaoCao> thongTinKhamBenhList = BaoCao.getAllThongTin();
            for (Model_BaoCao thongTinKhamBenh : thongTinKhamBenhList) {
                Object[] row = {thongTinKhamBenh.getTenBN(), thongTinKhamBenh.getDiaChi(), thongTinKhamBenh.getCCCD(),
                    thongTinKhamBenh.getGioiTinh(), thongTinKhamBenh.getChuanDoanSoBo(), thongTinKhamBenh.getChuyenKhoa(),
                    thongTinKhamBenh.getHuongDieuTri()};
                table.addRow(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.noitru.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mbnCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        spTable = new javax.swing.JScrollPane();
        table = new com.noitru.swing.Table();

        setBackground(new java.awt.Color(204, 204, 255));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("MẪU BÁO CÁO TÓM TẮT");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Mã BN");

        mbnCb.setBackground(new java.awt.Color(136, 136, 255));
        mbnCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn mã BN--" }));

        spTable.setBorder(null);

        table.setBackground(new java.awt.Color(232, 232, 252));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TenBN", "Địa Chỉ", "CCCD", "Giới Tính", "Chuẩn Đoán", "Chuyên Khoa", "Hướng Điều Trị", "Số Ngày NV"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion mbnCb;
    private com.noitru.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.noitru.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
