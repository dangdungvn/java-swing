package com.noitru.form;

import com.noitru.NoiTru;
import com.noitru.model.CheckLoi;
import com.noitru.model.Model_BacSi;
import com.noitru.swing.ScrollBar;
import com.noitru.swing.search.SearchOptinEvent;
import com.noitru.swing.search.SearchOption;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class Form_1 extends javax.swing.JPanel {

    public Form_1() {
        initComponents();
        spTable4.setVerticalScrollBar(new ScrollBar());
        spTable4.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable4.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable4.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        mbsEdt.setHint("Nhập mã bác sĩ...");
        tbsEdt.setHint("Nhập tên bác sĩ...");
        nknEdt.setHint("Nhập năm sinh...");
        getAllDataBacSi();
        themData();
        suaData();
        tableClick();
        cbData();
        xoaData();
        timKiemData();
    }

    @SuppressWarnings("unchecked")
    private void cbData() {
        List<String> data = NoiTru.getAllChuyenKhoa();
        for (String item : data) {
            chuyenkhoaCb.addItem(item);
        }
    }

    private void timKiemData() {
        timKiemBsEdt.addOption(new SearchOption("Mã Bác Sĩ",
                new ImageIcon(getClass().getResource("/com/noitru/icon/credit-card.png"))));
        timKiemBsEdt.addOption(new SearchOption("Tên Bác Sĩ",
                new ImageIcon(getClass().getResource("/com/noitru/icon/man-avatar.png"))));
        timKiemBsEdt.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                timKiemBsEdt.setHint("Tìm Kiếm " + option.getName() + "...");
            }

        });
        timKiemBsEdt.setSelectedIndex(0);
    }

    private void xoaData() {
        xoaBsBtn.addActionListener(e -> {
            String MaBS = mbsEdt.getText().trim();
            if (MaBS.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bác sĩ để xóa!");
                return;
            }
            NoiTru.xoaBacSi(MaBS);
            getAllDataBacSi();
            mbsEdt.setText("");
            tbsEdt.setText("");
            nknEdt.setText("");
            chuyenkhoaCb.setSelectedIndex(0);
        });
    }

    private void tableClick() {
        bsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = bsTable.getSelectedRow();
                if (selectedRow != -1) {
                    mbsEdt.setText((String) bsTable.getValueAt(selectedRow, 0));
                    tbsEdt.setText((String) bsTable.getValueAt(selectedRow, 1));
                    nknEdt.setText((String) bsTable.getValueAt(selectedRow, 2));
                    chuyenkhoaCb.setSelectedItem(bsTable.getValueAt(selectedRow, 3).toString());
                }
            }
        });
    }

    // static Connection conn;
    private void suaData() {
        SuaBsBtn.addActionListener(e -> {
            try {
                String MaBS = mbsEdt.getText().trim();
                String TenBS = tbsEdt.getText().trim();
                String KinhNghiem = nknEdt.getText().trim();
                String ChuyenKhoa = chuyenkhoaCb.getSelectedItem().toString();
                if (MaBS.isEmpty() || TenBS.isEmpty() || KinhNghiem.isEmpty() || ChuyenKhoa.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ!");
                    return;
                } else {
                    NoiTru.suaBacSi(MaBS, TenBS, KinhNghiem, ChuyenKhoa);
                    getAllDataBacSi();
                    mbsEdt.setText("");
                    tbsEdt.setText("");
                    nknEdt.setText("");
                    chuyenkhoaCb.setSelectedIndex(0);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void themData() {
        themBsBtn.addActionListener(e -> {
            try {
                String MaBS = mbsEdt.getText().trim();
                if (!CheckLoi.checkKey(MaBS, "bacsi")) {
                    JOptionPane.showMessageDialog(this, "Lỗi trùng mã bác sĩ! Mời nhập lại");
                    return;
                }
                String TenBS = tbsEdt.getText().trim();
                String KinhNghiem = nknEdt.getText().trim();
                String ChuyenKhoa = chuyenkhoaCb.getSelectedItem().toString();
                if (MaBS.isEmpty() || TenBS.isEmpty() || KinhNghiem.isEmpty() || ChuyenKhoa.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                    return;
                } else {
                    NoiTru.addBacSi(MaBS, TenBS, KinhNghiem, ChuyenKhoa);
                    getAllDataBacSi();
                    mbsEdt.setText("");
                    tbsEdt.setText("");
                    nknEdt.setText("");
                    chuyenkhoaCb.setSelectedIndex(0);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void getAllDataBacSi() {
        ((DefaultTableModel) bsTable.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<Model_BacSi> bacSiList = NoiTru.getAllBacSi();
            for (Model_BacSi bacSi : bacSiList) {
                Object[] row = {bacSi.getMaBS(), bacSi.getTenBS(), bacSi.getKinhNghiem(),
                    bacSi.getChuyenKhoa()};
                bsTable.addRow(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder5 = new com.noitru.swing.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable4 = new javax.swing.JScrollPane();
        bsTable = new com.noitru.swing.Table();
        timKiemBsEdt = new com.noitru.swing.search.TextFieldSearchOption();
        mbsEdt = new com.noitru.swing.TextFeild();
        tbsEdt = new com.noitru.swing.TextFeild();
        chuyenkhoaCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nknEdt = new com.noitru.swing.TextFeild();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        themBsBtn = new com.noitru.swing.Button();
        SuaBsBtn = new com.noitru.swing.Button();
        xoaBsBtn = new com.noitru.swing.Button();

        setBackground(new java.awt.Color(242, 242, 242));

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Danh sách bác sĩ");

        spTable4.setBorder(null);

        bsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaBS", "TenBS", "Kinh Nghiệm", "Chuyên Khoa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable4.setViewportView(bsTable);

        timKiemBsEdt.setBackground(new java.awt.Color(255, 255, 255));
        timKiemBsEdt.setForeground(new java.awt.Color(80, 80, 80));
        timKiemBsEdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timKiemBsEdtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(124, 124, 124)
                        .addComponent(timKiemBsEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(timKiemBsEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        chuyenkhoaCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn chuyên khoa--" }));

        jLabel6.setForeground(new java.awt.Color(120, 120, 120));
        jLabel6.setText("Mã Bác sĩ");

        jLabel7.setForeground(new java.awt.Color(120, 120, 120));
        jLabel7.setText("Tên bác sĩ");

        jLabel8.setForeground(new java.awt.Color(120, 120, 120));
        jLabel8.setText("Số năm kinh nghiệm");

        jLabel9.setForeground(new java.awt.Color(120, 120, 120));
        jLabel9.setText("Chuyên khoa");

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 20, 10));

        themBsBtn.setBackground(new java.awt.Color(133, 218, 128));
        themBsBtn.setText("Thêm BS");
        jPanel1.add(themBsBtn);

        SuaBsBtn.setBackground(new java.awt.Color(236, 244, 80));
        SuaBsBtn.setText("Sửa BS");
        jPanel1.add(SuaBsBtn);

        xoaBsBtn.setBackground(new java.awt.Color(238, 131, 108));
        xoaBsBtn.setText("Xóa BS");
        jPanel1.add(xoaBsBtn);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nknEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mbsEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tbsEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)))
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mbsEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbsEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nknEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void timKiemBsEdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timKiemBsEdtKeyReleased
        if (timKiemBsEdt.isSelected()) {
            int option = timKiemBsEdt.getSelectedIndex();
            String text = timKiemBsEdt.getText().trim();
            if (option == 0) {
                ((DefaultTableModel) bsTable.getModel()).setRowCount(0);
                SwingUtilities.invokeLater(() -> {
                    List<Model_BacSi> bacSiListTK = NoiTru.timKiemTheoMaBS(text);
                    for (Model_BacSi bacSi : bacSiListTK) {
                        Object[] row = {bacSi.getMaBS(), bacSi.getTenBS(),
                            bacSi.getKinhNghiem(),
                            bacSi.getChuyenKhoa(),};
                        bsTable.addRow(row);
                    }
                });
            } else if (option == 1) {
                ((DefaultTableModel) bsTable.getModel()).setRowCount(0);
                SwingUtilities.invokeLater(() -> {
                    List<Model_BacSi> bacSiListTK = NoiTru.timKiemTheoTenBS(text);
                    for (Model_BacSi bacSi : bacSiListTK) {
                        Object[] row = {bacSi.getMaBS(), bacSi.getTenBS(),
                            bacSi.getKinhNghiem(),
                            bacSi.getChuyenKhoa(),};
                        bsTable.addRow(row);
                    }
                });
            }
        }
    }//GEN-LAST:event_timKiemBsEdtKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.swing.Button SuaBsBtn;
    private com.noitru.swing.Table bsTable;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion chuyenkhoaCb;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private com.noitru.swing.TextFeild mbsEdt;
    private com.noitru.swing.TextFeild nknEdt;
    private com.noitru.swing.PanelBorder panelBorder5;
    private javax.swing.JScrollPane spTable4;
    private com.noitru.swing.TextFeild tbsEdt;
    private com.noitru.swing.Button themBsBtn;
    private com.noitru.swing.search.TextFieldSearchOption timKiemBsEdt;
    private com.noitru.swing.Button xoaBsBtn;
    // End of variables declaration//GEN-END:variables
}
