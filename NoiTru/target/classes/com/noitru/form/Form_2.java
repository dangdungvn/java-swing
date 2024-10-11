package com.noitru.form;

import com.noitru.BacSi;
import com.noitru.BenhNhan;
import com.noitru.GiuongBenh;
import com.noitru.component.CheckLoi;
import com.noitru.model.Model_BacSi;
import com.noitru.model.Model_BenhNhan;
import com.noitru.model.Model_GiuongBenh;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Form_2 extends javax.swing.JPanel {

    Map<String, String[]> phanphongMap = new HashMap<>();

    public Form_2() {
        initComponents();
        loadGiuongBenh();
        loadDBCB();
        themData();
        lpEdt.setEnabled(false);
        ttgEdt.setEnabled(false);
    }

    private void addMap() {
        phanphongMap.put("Nội khoa", new String[]{"101", "102", "103"});
        phanphongMap.put("Ngoại khoa", new String[]{"201", "202", "203"});
        phanphongMap.put("Sản phụ khoa", new String[]{"301", "302", "303"});
        phanphongMap.put("Nhi khoa", new String[]{"401", "402", "403"});
        phanphongMap.put("Tim mạch", new String[]{"501", "502", "503"});
        phanphongMap.put("Thần kinh", new String[]{"601", "602", "603"});
        phanphongMap.put("Tai mũi họng", new String[]{"701", "702", "703"});
        phanphongMap.put("Da liễu", new String[]{"801", "802", "803"});
        phanphongMap.put("Hô hấp", new String[]{"901", "902", "903"});
        phanphongMap.put("Chấn thương chỉnh hình", new String[]{"1001", "1002", "1003"});
    }

    @SuppressWarnings("unchecked")
    private void loadDBCB() {
        addMap();
        List<String> data = BacSi.getAllChuyenKhoa();
        for (String item : data) {
            chuyenkhoaCb.addItem(item);
        }
        chuyenkhoaCb.addActionListener((ActionEvent e) -> {
            String selectedChuyenKhoa = (String) chuyenkhoaCb.getSelectedItem();
            mbsCb.removeAllItems(); // Xóa tất cả các mục hiện tại trong mbsCbl
            spCb.removeAllItems();
            mbsCb.addItem("--Chọn Bác Sĩ--");
            spCb.addItem("--Chọn số phòng--");
            if (selectedChuyenKhoa != null && !selectedChuyenKhoa.equals("--Chọn chuyên khoa--")) {
                List<Model_BacSi> bacSiList = BacSi.getBacSiByChuyenKhoa(selectedChuyenKhoa);
                for (Model_BacSi bacSi : bacSiList) {
                    mbsCb.addItem(bacSi.getMaBS());
                }
                String[] soPhongList = phanphongMap.get(selectedChuyenKhoa);
                for (String soPhong : soPhongList) {
                    spCb.addItem(soPhong);
                }
            }
        });
        List<Model_BenhNhan> benhNhanList = GiuongBenh.getBenhNhanChuaDuocXepGiuong();
        for (Model_BenhNhan benhNhan : benhNhanList) {
            mbnCb.addItem(benhNhan.getMaBN());
        }
        spCb.addActionListener((ActionEvent e) -> {
            String selectedRoom = (String) spCb.getSelectedItem();
            if (selectedRoom != null && !selectedRoom.equals("--Chọn số phòng--")) {
                char lastDigit = selectedRoom.charAt(selectedRoom.length() - 1);
                sgCb.removeAllItems(); // Xóa các item cũ trước khi thêm mới
                sgCb.addItem("--Chọn số giường--");
                switch (lastDigit) {
                    case '1' -> {
                        lpEdt.setText("Giường Thường");
                        for (int i = 1; i <= 5; i++) {
                            sgCb.addItem(String.valueOf(i));
                        }
                    }
                    case '2' -> {
                        lpEdt.setText("Giường VIP");
                        for (int i = 1; i <= 4; i++) {
                            sgCb.addItem(String.valueOf(i));
                        }
                    }
                    case '3' -> {
                        lpEdt.setText("Giường Đặc Biệt");
                        for (int i = 1; i <= 3; i++) {
                            sgCb.addItem(String.valueOf(i));
                        }
                    }
                    default -> {
                        lpEdt.setText("...");
                        sgCb.removeAllItems(); // Trường hợp không hợp lệ, xóa hết item
                        sgCb.addItem("--Chọn số giường--");
                    }
                }
            } else {
                lpEdt.setText("...");
                sgCb.removeAllItems(); // Nếu không chọn phòng, xóa hết item
                sgCb.addItem("--Chọn số giường--");
            }
        });
        sgCb.addActionListener((ActionEvent e) -> {
            String selectedBed = (String) sgCb.getSelectedItem();
            if (selectedBed != null && !selectedBed.equals("--Chọn số giường--")) {
                List<Model_GiuongBenh> giuongBenhList = GiuongBenh.getAllGiuongBenh();
                boolean found = false;
                for (Model_GiuongBenh giuongBenh : giuongBenhList) {
                    if (giuongBenh.getSoPhong().equals(spCb.getSelectedItem().toString()) && giuongBenh.getSoGiuong().equals(selectedBed)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ttgEdt.setText("Giường còn trống");
                    themBtn.setEnabled(true);
                } else {
                    ttgEdt.setText("Đã có bệnh nhân");
                    themBtn.setEnabled(false);
                }
            } else {
                ttgEdt.setText("...");
                themBtn.setEnabled(true);
            }
        });
    }

    private void themData() {
        themBtn.addActionListener((ActionEvent e) -> {
            try {
                String ChuyenKhoa = chuyenkhoaCb.getSelectedItem().toString();
                String LoaiPhong = lpEdt.getText().trim();
                String SoPhong = spCb.getSelectedItem().toString();
                String SoGiuong = sgCb.getSelectedItem().toString();
                String MaBN = mbnCb.getSelectedItem().toString();
                String MaBS = mbsCb.getSelectedItem().toString();
                if (ChuyenKhoa.isEmpty() || LoaiPhong.isEmpty() || SoPhong.isEmpty() || SoGiuong.isEmpty() || MaBN.isEmpty() || MaBS.isEmpty()) {
                    JOptionPane.showMessageDialog(Form_2.this, "Vui Lòng Nhập Thông Tin");
                    return;
                } else {
                    GiuongBenh.addGiuongBenh(ChuyenKhoa, LoaiPhong, SoPhong, SoGiuong, MaBN, MaBS);
                    loadGiuongBenh();
                    lpEdt.setText("...");
                    ttgEdt.setText("...");
                    chuyenkhoaCb.setSelectedIndex(0);
                    spCb.setSelectedIndex(0);
                    sgCb.setSelectedIndex(0);
                    mbnCb.setSelectedIndex(0);
                    mbsCb.setSelectedIndex(0);
                }
            } catch (HeadlessException ex) {
            }
        });
    }

    private void loadGiuongBenh() {
        ((DefaultTableModel) gbTable.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<Model_GiuongBenh> giuongBenhList = GiuongBenh.getAllGiuongBenh();
            for (Model_GiuongBenh giuongBenh : giuongBenhList) {
                Object[] row = {giuongBenh.getMaBN(), giuongBenh.getChuyenKhoa(), giuongBenh.getMaBS(),
                    giuongBenh.getSoPhong(), giuongBenh.getLoaiPhong(), giuongBenh.getSoGiuong()};
                gbTable.addRow(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.noitru.swing.PanelBorder();
        lpEdt = new com.noitru.swing.TextFeild();
        jLabel9 = new javax.swing.JLabel();
        sgCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        jLabel10 = new javax.swing.JLabel();
        ttgEdt = new com.noitru.swing.TextFeild();
        chuyenkhoaCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        spCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        mbnCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        mbsCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        panelBorder2 = new com.noitru.swing.PanelBorder();
        themBtn = new com.noitru.swing.Button();
        suaBtn = new com.noitru.swing.Button();
        resetBtn = new com.noitru.swing.Button();
        suaBtn2 = new com.noitru.swing.Button();
        xoaBtn = new com.noitru.swing.Button();
        panelBorder5 = new com.noitru.swing.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable4 = new javax.swing.JScrollPane();
        gbTable = new com.noitru.swing.Table();
        timKiemBsEdt = new com.noitru.swing.search.TextFieldSearchOption();

        setBackground(new java.awt.Color(242, 242, 242));

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel9.setForeground(new java.awt.Color(120, 120, 120));
        jLabel9.setText("Số Giường");

        sgCb.setForeground(new java.awt.Color(80, 80, 80));
        sgCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn số giường--" }));

        jLabel10.setForeground(new java.awt.Color(120, 120, 120));
        jLabel10.setText("Trạng Thái Giường");

        chuyenkhoaCb.setForeground(new java.awt.Color(80, 80, 80));
        chuyenkhoaCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn chuyên khoa--" }));

        spCb.setForeground(new java.awt.Color(80, 80, 80));
        spCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn số phòng--", "101", "102", "103", "104", "201", "202", "203", "204", "301", "302", "303", "304" }));

        jLabel8.setForeground(new java.awt.Color(120, 120, 120));
        jLabel8.setText("Loại Phòng");

        jLabel1.setForeground(new java.awt.Color(120, 120, 120));
        jLabel1.setText("Mã BN");

        jLabel2.setForeground(new java.awt.Color(120, 120, 120));
        jLabel2.setText("Chuyên Khoa");

        jLabel3.setForeground(new java.awt.Color(120, 120, 120));
        jLabel3.setText("Mã BS Phụ Trách");

        jLabel4.setForeground(new java.awt.Color(120, 120, 120));
        jLabel4.setText("Số Phòng");

        mbnCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Bệnh Nhân--" }));

        mbsCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Bác Sĩ--" }));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mbnCb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(sgCb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lpEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(ttgEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mbsCb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mbsCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lpEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sgCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ttgEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        panelBorder2.setBackground(new java.awt.Color(221, 221, 221));

        themBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))); // NOI18N
        themBtn.setText("Thêm");

        suaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))); // NOI18N
        suaBtn.setText("Sửa");

        resetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))); // NOI18N
        resetBtn.setText("Reset");

        suaBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))); // NOI18N
        suaBtn2.setText("Xuất Excel");

        xoaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))); // NOI18N
        xoaBtn.setText("Xóa");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(suaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(xoaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(suaBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suaBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xoaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Danh sách bác sĩ");

        spTable4.setBorder(null);

        gbTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaBN", "Chuyên Khoa", "Mã BS", "Số Phòng", "Loại Phòng", "Số Giường"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable4.setViewportView(gbTable);

        timKiemBsEdt.setBackground(new java.awt.Color(255, 255, 255));
        timKiemBsEdt.setForeground(new java.awt.Color(80, 80, 80));

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
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
                .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion chuyenkhoaCb;
    private com.noitru.swing.Table gbTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.noitru.swing.TextFeild lpEdt;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion mbnCb;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion mbsCb;
    private com.noitru.swing.PanelBorder panelBorder1;
    private com.noitru.swing.PanelBorder panelBorder2;
    private com.noitru.swing.PanelBorder panelBorder5;
    private com.noitru.swing.Button resetBtn;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion sgCb;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion spCb;
    private javax.swing.JScrollPane spTable4;
    private com.noitru.swing.Button suaBtn;
    private com.noitru.swing.Button suaBtn2;
    private com.noitru.swing.Button themBtn;
    private com.noitru.swing.search.TextFieldSearchOption timKiemBsEdt;
    private com.noitru.swing.TextFeild ttgEdt;
    private com.noitru.swing.Button xoaBtn;
    // End of variables declaration//GEN-END:variables
}
