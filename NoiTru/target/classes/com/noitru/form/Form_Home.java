package com.noitru.form;

import com.noitru.NoiTru;
import com.noitru.model.Model_BenhNhan;
import com.noitru.swing.ScrollBar;
import com.noitru.swing.search.SearchOptinEvent;
import com.noitru.swing.search.SearchOption;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Form_Home extends javax.swing.JPanel {

    public Form_Home() {
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        getAllDataBenhNhan();
        mbnEdt.setHint("Nhập mã bệnh nhân...");
        tbnEdt.setHint("Nhập tên bệnh nhân...");
        nsEdt.setHint("Nhập năm sinh...");
        cccdEdt.setHint("Nhập CCCD...");
        dcEdt.setHint("Nhập địa chỉ...");
        bhEdt.setHint("Nhập BHYT...");
        dtEdt.setHint("Nhập SDT...");

        themData();
        tableClick();
        suaData();
        timKiemData();
    }
    static Connection conn;

    private boolean checkKey(String MaBN) {
        boolean kq = false;
        try {
            conn = NoiTru.connect();
            String sql = "SELECT * FROM `benhnhan` Where MaBN = ('" + MaBN + "') ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                kq = true;
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
        return kq;
    }

    public String checkDT(String dt) {
        String regex = "^(\\+84|0)[0-9]{9}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dt);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(this, "Nhập số điện thoại lỗi! Mời nhập lại");
            return null;
        }
        return dt;
    }

    public void tableClick() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    mbnEdt.setText((String) table.getValueAt(selectedRow, 0));
                    tbnEdt.setText((String) table.getValueAt(selectedRow, 1));
                    nsEdt.setText((String) table.getValueAt(selectedRow, 2));
                    gtEdt.setSelectedItem(table.getValueAt(selectedRow, 3).toString());
                    cccdEdt.setText((String) table.getValueAt(selectedRow, 4));
                    dcEdt.setText((String) table.getValueAt(selectedRow, 5));
                    bhEdt.setText((String) table.getValueAt(selectedRow, 6));
                    dtEdt.setText((String) table.getValueAt(selectedRow, 7));
                }
            }
        });
    }

    public void getAllDataBenhNhan() {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<Model_BenhNhan> benhNhanList = NoiTru.getAllBenhNhan();
            for (Model_BenhNhan benhNhan : benhNhanList) {
                Object[] row = {benhNhan.getMaBN(), benhNhan.getTenBN(), benhNhan.getNgaySinh(), benhNhan.getGioiTinh(),
                    benhNhan.getCCCD(), benhNhan.getDiaChi(), benhNhan.getBHYT(), benhNhan.getDienThoai()};
                table.addRow(row);
            }
        });
    }

    private void themData() {
        themBtn.addActionListener(e -> {
            try {
                String MaBN = mbnEdt.getText().trim();
                if (!checkKey(MaBN)) {
                    JOptionPane.showMessageDialog(this, "Lỗi trùng mã bệnh nhân! Mời nhập lại");
                    return;
                }
                String TenBN = tbnEdt.getText().trim();
                String NgaySinh = nsEdt.getText().trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(NgaySinh);
                NgaySinh = new SimpleDateFormat("yyyy-MM-dd").format(date);
                String GioiTinh = gtEdt.getSelectedItem().toString();
                String CCCD = cccdEdt.getText().trim();
                String DiaChi = dcEdt.getText().trim();
                String BHYT = bhEdt.getText().trim();
                String DienThoai = dtEdt.getText().trim();
                if (checkDT(DienThoai) == null) {
                    return;
                }
                if (TenBN.isEmpty() || NgaySinh.isEmpty() || GioiTinh.isEmpty() || CCCD.isEmpty() || DiaChi.isEmpty() || BHYT.isEmpty() || DienThoai.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                    return;
                } else {
                    NoiTru.addBenhNhan(MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT, DienThoai);
                    getAllDataBenhNhan();
                    mbnEdt.setText("");
                    tbnEdt.setText("");
                    nsEdt.setText("");
                    gtEdt.setSelectedIndex(0);
                    cccdEdt.setText("");
                    dcEdt.setText("");
                    bhEdt.setText("");
                    dtEdt.setText("");
                }
            } catch (ParseException ex) {
                Logger.getLogger(Form_Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void suaData() {
        suaBtn.addActionListener(e -> {
            try {
                String MaBN = mbnEdt.getText().trim();
                String TenBN = tbnEdt.getText().trim();
                String NgaySinh = nsEdt.getText().trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(NgaySinh);
                NgaySinh = new SimpleDateFormat("yyyy-MM-dd").format(date);
                String GioiTinh = gtEdt.getSelectedItem().toString();
                String CCCD = cccdEdt.getText().trim();
                String DiaChi = dcEdt.getText().trim();
                String BHYT = bhEdt.getText().trim();
                String DienThoai = dtEdt.getText().trim();
                if (checkDT(DienThoai) == null) {
                    return;
                }
                if (TenBN.isEmpty() || NgaySinh.isEmpty() || GioiTinh.isEmpty() || CCCD.isEmpty() || DiaChi.isEmpty() || BHYT.isEmpty() || DienThoai.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                    return;
                } else {
                    NoiTru.suaBenhNhan(MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT, DienThoai);
                    getAllDataBenhNhan();
                    mbnEdt.setText("");
                    tbnEdt.setText("");
                    nsEdt.setText("");
                    gtEdt.setSelectedIndex(0);
                    cccdEdt.setText("");
                    dcEdt.setText("");
                    bhEdt.setText("");
                    dtEdt.setText("");
                }
            } catch (ParseException ex) {
                Logger.getLogger(Form_Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void timKiemData() {
        timKiemEdt.addOption(new SearchOption("Mã Bệnh Nhân", new ImageIcon(getClass().getResource("/com/noitru/icon/credit-card.png"))));
        timKiemEdt.addOption(new SearchOption("Tên Bệnh Nhân", new ImageIcon(getClass().getResource("/com/noitru/icon/man-avatar.png"))));
        timKiemEdt.addOption(new SearchOption("Ngày Sinh", new ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))));
        timKiemEdt.setSelectedIndex(0);
        timKiemEdt.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                timKiemEdt.setHint("Tìm Kiếm " + option.getName() + "...");
            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        panelBorder1 = new com.noitru.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.noitru.swing.Table();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mbnEdt = new com.noitru.swing.TextFeild();
        jLabel3 = new javax.swing.JLabel();
        tbnEdt = new com.noitru.swing.TextFeild();
        jLabel4 = new javax.swing.JLabel();
        nsEdt = new com.noitru.swing.TextFeild();
        jLabel5 = new javax.swing.JLabel();
        gtEdt = new com.noitru.swing.Combobox();
        jLabel6 = new javax.swing.JLabel();
        cccdEdt = new com.noitru.swing.TextFeild();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dcEdt = new com.noitru.swing.TextFeild();
        bhEdt = new com.noitru.swing.TextFeild();
        dtEdt = new com.noitru.swing.TextFeild();
        themBtn = new com.noitru.swing.Button();
        suaBtn = new com.noitru.swing.Button();
        timKiemEdt = new com.noitru.swing.search.TextFieldSearchOption();

        dateChooser1.setTextRefernce(nsEdt);

        setBackground(new java.awt.Color(242, 242, 242));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Bệnh Nhân Nhập Viện");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaBN", "TenBN", "Ngày Sinh", "Giới Tính", "CCCD", "Địa Chỉ", "BHYT", "Điện Thoại"
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
                    .addComponent(jLabel1)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(120, 120, 120));
        jLabel2.setText("Mã Bệnh Nhân");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(120, 120, 120));
        jLabel3.setText("Tên Bệnh Nhân");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(120, 120, 120));
        jLabel4.setText("Ngày Sinh");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(120, 120, 120));
        jLabel5.setText("Giới Tính");

        gtEdt.setForeground(new java.awt.Color(80, 80, 80));
        gtEdt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Giới Tính--", "Nam", "Nữ" }));
        gtEdt.setLabeText("");
        gtEdt.setShadowColor(new java.awt.Color(150, 150, 150));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(120, 120, 120));
        jLabel6.setText("CCCD");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(120, 120, 120));
        jLabel7.setText("Địa Chỉ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(120, 120, 120));
        jLabel8.setText("BHYT");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(120, 120, 120));
        jLabel9.setText("Điện Thoại");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(mbnEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(tbnEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nsEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(gtEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cccdEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bhEdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtEdt, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mbnEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(cccdEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tbnEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(dcEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nsEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(bhEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(gtEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        themBtn.setBackground(new java.awt.Color(114, 197, 125));
        themBtn.setText("Thêm BN");

        suaBtn.setBackground(new java.awt.Color(226, 219, 59));
        suaBtn.setText("Sửa BN");

        timKiemEdt.setBackground(new java.awt.Color(255, 255, 255));
        timKiemEdt.setForeground(new java.awt.Color(80, 80, 80));
        timKiemEdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timKiemEdtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(suaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timKiemEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(timKiemEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(suaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void timKiemEdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timKiemEdtKeyReleased
        // TODO add your handling code here:
        if (timKiemEdt.isSelected()) {
            int option = timKiemEdt.getSelectedIndex();
            String text = timKiemEdt.getText().trim();
            if (option == 0) {
                ((DefaultTableModel) table.getModel()).setRowCount(0);
                SwingUtilities.invokeLater(() -> {
                    List<Model_BenhNhan> benhNhanListTK = NoiTru.timKiemTheoMaBN(text);
                    for (Model_BenhNhan benhNhan : benhNhanListTK) {
                        Object[] row = {benhNhan.getMaBN(), benhNhan.getTenBN(), benhNhan.getNgaySinh(), benhNhan.getGioiTinh(),
                            benhNhan.getCCCD(), benhNhan.getDiaChi(), benhNhan.getBHYT(), benhNhan.getDienThoai()};
                        table.addRow(row);
                    }
                });
            } else if (option == 1) {
                ((DefaultTableModel) table.getModel()).setRowCount(0);
                SwingUtilities.invokeLater(() -> {
                    List<Model_BenhNhan> benhNhanListTK = NoiTru.timKiemTheoTenBN(text);
                    for (Model_BenhNhan benhNhan : benhNhanListTK) {
                        Object[] row = {benhNhan.getMaBN(), benhNhan.getTenBN(), benhNhan.getNgaySinh(), benhNhan.getGioiTinh(),
                            benhNhan.getCCCD(), benhNhan.getDiaChi(), benhNhan.getBHYT(), benhNhan.getDienThoai()};
                        table.addRow(row);
                    }
                });
            } else {
                ((DefaultTableModel) table.getModel()).setRowCount(0);
                SwingUtilities.invokeLater(() -> {
                    List<Model_BenhNhan> benhNhanListTK = NoiTru.timKiemTheoNgaySinh(text);
                    for (Model_BenhNhan benhNhan : benhNhanListTK) {
                        Object[] row = {benhNhan.getMaBN(), benhNhan.getTenBN(), benhNhan.getNgaySinh(), benhNhan.getGioiTinh(),
                            benhNhan.getCCCD(), benhNhan.getDiaChi(), benhNhan.getBHYT(), benhNhan.getDienThoai()};
                        table.addRow(row);
                    }
                });
            }
        }
    }//GEN-LAST:event_timKiemEdtKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.swing.TextFeild bhEdt;
    private com.noitru.swing.TextFeild cccdEdt;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.noitru.swing.TextFeild dcEdt;
    private com.noitru.swing.TextFeild dtEdt;
    private com.noitru.swing.Combobox gtEdt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private com.noitru.swing.TextFeild mbnEdt;
    private com.noitru.swing.TextFeild nsEdt;
    private com.noitru.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.noitru.swing.Button suaBtn;
    private com.noitru.swing.Table table;
    private com.noitru.swing.TextFeild tbnEdt;
    private com.noitru.swing.Button themBtn;
    private com.noitru.swing.search.TextFieldSearchOption timKiemEdt;
    // End of variables declaration//GEN-END:variables
}
