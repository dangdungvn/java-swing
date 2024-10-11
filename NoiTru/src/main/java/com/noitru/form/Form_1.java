package com.noitru.form;

import com.noitru.BacSi;
import com.noitru.BenhNhan;
import com.noitru.component.CheckLoi;
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
        List<String> data = BacSi.getAllChuyenKhoa();
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
//        xoaBsBtn.addActionListener(e -> {
//            String MaBS = mbsEdt.getText().trim();
//            if (MaBS.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Vui lòng chọn bác sĩ để xóa!");
//                return;
//            }
//            BacSi.xoaBacSi(MaBS);
//            getAllDataBacSi();
//            mbsEdt.setText("");
//            tbsEdt.setText("");
//            nknEdt.setText("");
//            chuyenkhoaCb.setSelectedIndex(0);
//        });
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
        suaBsBtn.addActionListener(e -> {
            try {
                String MaBS = mbsEdt.getText().trim();
                String TenBS = tbsEdt.getText().trim();
                String KinhNghiem = nknEdt.getText().trim();
                String ChuyenKhoa = chuyenkhoaCb.getSelectedItem().toString();
                if (MaBS.isEmpty() || TenBS.isEmpty() || KinhNghiem.isEmpty() || ChuyenKhoa.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ!");
                    return;
                } else {
                    BacSi.suaBacSi(MaBS, TenBS, KinhNghiem, ChuyenKhoa);
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
                    BacSi.addBacSi(MaBS, TenBS, KinhNghiem, ChuyenKhoa);
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
            List<Model_BacSi> bacSiList = BacSi.getAllBacSi();
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
        panelBorder2 = new com.noitru.swing.PanelBorder();
        themBsBtn = new com.noitru.swing.Button();
        suaBsBtn = new com.noitru.swing.Button();
        suaBtn2 = new com.noitru.swing.Button();

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

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
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

        panelBorder2.setBackground(new java.awt.Color(221, 221, 221));

        themBsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))); // NOI18N
        themBsBtn.setText("Thêm");

        suaBsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))); // NOI18N
        suaBsBtn.setText("Sửa");

        suaBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))); // NOI18N
        suaBtn2.setText("Xuất Excel");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(themBsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(suaBsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(suaBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themBsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suaBsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suaBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(nknEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tbsEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel6))
                                .addComponent(mbsEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(40, 40, 40)
                        .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(191, 191, 191))))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.swing.Table bsTable;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion chuyenkhoaCb;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.noitru.swing.TextFeild mbsEdt;
    private com.noitru.swing.TextFeild nknEdt;
    private com.noitru.swing.PanelBorder panelBorder2;
    private com.noitru.swing.PanelBorder panelBorder5;
    private javax.swing.JScrollPane spTable4;
    private com.noitru.swing.Button suaBsBtn;
    private com.noitru.swing.Button suaBtn2;
    private com.noitru.swing.TextFeild tbsEdt;
    private com.noitru.swing.Button themBsBtn;
    private com.noitru.swing.search.TextFieldSearchOption timKiemBsEdt;
    // End of variables declaration//GEN-END:variables
}
