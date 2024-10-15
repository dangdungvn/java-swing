package com.noitru.form;

import com.noitru.DieuTri;
import com.noitru.model.Model_DieuTri;
import com.noitru.swing.ScrollBar;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Form_6 extends javax.swing.JPanel {

    public Form_6() {
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        loadDB();
        loadCB();
        addData();
    }

    private void addData() {
        kedonBtn.addActionListener((ActionEvent e) -> {
            try {
                String maBN = mbnCb.getSelectedItem().toString();
                int soNgay = Integer.parseInt(nnvEdt.getText());
                String Ngay = ngayEdt.getText().trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(Ngay);
                Ngay = new SimpleDateFormat("yyyy-MM-dd").format(date);
                String Thuoc = thuocEdt.getText().trim();
                int SoLuongThuoc = Integer.parseInt(sltEdt.getText().trim());
                int ThanhTien = SoLuongThuoc * 20000;
                DieuTri.addThongTin(maBN, soNgay, Ngay, Thuoc, SoLuongThuoc, ThanhTien);
                loadDB();
            } catch (ParseException ex) {
                Logger.getLogger(Form_6.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void loadCB() {
        List<Model_DieuTri> ttkbList = DieuTri.getAllThongTin();
        for (Model_DieuTri mdt : ttkbList) {
            mbnCb.addItem(mdt.getMaBN());
        }
        mbnCb.addActionListener((ActionEvent e) -> {
            String text = mbnCb.getSelectedItem().toString();
            if (text != null && !text.equals("--Chọn Mã BN--")) {
                List<Model_DieuTri> ttkbListTK = DieuTri.timKiemTheoMaBN(text);
                for (Model_DieuTri mdt : ttkbListTK) {
                    nnvEdt.setText(String.valueOf(mdt.getSoNgayNhapVien()));
                    thuocEdt.setText(mdt.getThuoc());
                    sltEdt.setText(String.valueOf(mdt.getSoLuongThuoc()));
                    ngayEdt.setText(mdt.getNgay());
                }
                ((DefaultTableModel) table.getModel()).setRowCount(0);
                SwingUtilities.invokeLater(() -> {
                    for (Model_DieuTri benhNhan : ttkbListTK) {
                        Object[] row = {
                                benhNhan.getMaBN(),
                                benhNhan.getSoNgayNhapVien(),
                                benhNhan.getThuoc(),
                                benhNhan.getSoLuongThuoc(),
                                benhNhan.getThanhTien(),
                                benhNhan.getNgay()
                        };
                        table.addRow(row);
                    }
                });
                int tong = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    tong += Integer.parseInt(table.getValueAt(i, 4).toString());
                }
                ttEdt.setText(String.valueOf(tong));

            } else {
                loadDB();
            }

        });
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int tong = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    tong += Integer.parseInt(table.getValueAt(i, 4).toString());
                }
                ttEdt.setText(String.valueOf(tong)); // Cập nhật tổng tiền vào ttEdt
            }
        });
    }

    private void loadDB() {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<Model_DieuTri> ttkbList = DieuTri.getAllThongTin();
            for (Model_DieuTri benhNhan : ttkbList) {
                Object[] row = {
                        benhNhan.getMaBN(),
                        benhNhan.getSoNgayNhapVien(),
                        benhNhan.getThuoc(),
                        benhNhan.getSoLuongThuoc(),
                        benhNhan.getThanhTien(),
                        benhNhan.getNgay()
                };
                table.addRow(row);
            }
        });
        int tong = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            tong += Integer.parseInt(table.getValueAt(i, 4).toString());
        }
        ttEdt.setText(String.valueOf(tong));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        thuocEdt = new com.noitru.swing.TextFeild();
        sltEdt = new com.noitru.swing.TextFeild();
        jLabel2 = new javax.swing.JLabel();
        ngayEdt = new com.noitru.swing.TextFeild();
        mbnCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        panelBorder1 = new com.noitru.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.noitru.swing.Table();
        jLabel3 = new javax.swing.JLabel();
        nnvEdt = new com.noitru.swing.TextFeild();
        jLabel6 = new javax.swing.JLabel();
        kedonBtn = new com.noitru.swing.Button();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ttEdt = new com.noitru.swing.TextFeild();

        dateChooser1.setTextRefernce(ngayEdt);

        setBackground(new java.awt.Color(242, 242, 242));

        thuocEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        sltEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setText("Mã BN");

        ngayEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        mbnCb.setBackground(new java.awt.Color(204, 204, 255));
        mbnCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Mã BN--" }));

        panelBorder1.setBackground(new java.awt.Color(144, 144, 235));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ĐIỀU TRỊ HÀNG NGÀY");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "MaBN", "Số Ngày Nhập Viện", "Thuốc", "Số Lượng", "Thành Tiền", "Ngày"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelBorder1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(spTable))
                                .addGap(20, 20, 20)));
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addGap(20, 20, 20)
                                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                                .addGap(20, 20, 20)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 255));
        jLabel3.setText("Số Ngày Nhập Viện:");

        nnvEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 255));
        jLabel6.setText("Thuốc:");

        kedonBtn.setBackground(new java.awt.Color(204, 222, 255));
        kedonBtn.setForeground(new java.awt.Color(51, 51, 255));
        kedonBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/google-docs.png"))); // NOI18N
        kedonBtn.setText("Kê Đơn");
        kedonBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 255));
        jLabel7.setText("SL Thuốc:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Tổng Tiền");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setText("Ngày:");

        ttEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ngayEdt, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sltEdt, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(thuocEdt, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(nnvEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(427, 427, 427)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ttEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44,
                                                        Short.MAX_VALUE)
                                                .addComponent(kedonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(20, 20, 20)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nnvEdt, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(thuocEdt, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sltEdt, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ngayEdt, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(180, 180, 180)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(kedonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ttEdt, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                .addGap(17, 17, 17)));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private com.noitru.swing.Button kedonBtn;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion mbnCb;
    private com.noitru.swing.TextFeild ngayEdt;
    private com.noitru.swing.TextFeild nnvEdt;
    private com.noitru.swing.PanelBorder panelBorder1;
    private com.noitru.swing.TextFeild sltEdt;
    private javax.swing.JScrollPane spTable;
    private com.noitru.swing.Table table;
    private com.noitru.swing.TextFeild thuocEdt;
    private com.noitru.swing.TextFeild ttEdt;
    // End of variables declaration//GEN-END:variables
}
