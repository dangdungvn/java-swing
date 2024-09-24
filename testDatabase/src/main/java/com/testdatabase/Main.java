/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.testdatabase;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        spTable.setBorder(null);
        setBackground(new Color(0, 0, 0, 0));
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        loadDataToTable();
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    mxbEdt.setText((String) table.getValueAt(selectedRow, 0));
                    tnxbEdt.setText((String) table.getValueAt(selectedRow, 1));
                    nxbEdt.setText((String) table.getValueAt(selectedRow, 2));
                    dtEdt.setText((String) table.getValueAt(selectedRow, 3));
                    emEdt.setText((String) table.getValueAt(selectedRow, 4));
                    dcEdt.setText((String) table.getValueAt(selectedRow, 5));
                    gcEdt.setText((String) table.getValueAt(selectedRow, 6));
                }
            }
        });
        themData();
        suaData();
        xoaData();
        timKiemData();
    }
    static Connection conn;

    public Date checkNgay(String ngay) throws ParseException {
        String regex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ngay);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(this, "Nhập ngày tháng lỗi! Mời nhập lại");
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date nxb = java.sql.Date.valueOf(format.format(format.parse(ngay)));
        return nxb;
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

    public String checkMail(String mail) {
        String regex = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,}$)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(this, "Nhập email lỗi! Mời nhập lại");
            return null;
        }
        return mail;
    }

    private boolean checkKey(String mxb) {
        boolean kq = false;
        try {
            conn = TestDatabase.connect();
            String sql = "SELECT * FROM `nxb` Where mxb = ('" + mxb + "') ";
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

    private void timKiemData() {
        btnTimKiem.addActionListener((e) -> {
            String mxb = mxbEdt.getText().trim();
            String tnxb = tnxbEdt.getText().trim();
            String nxb = nxbEdt.getText().trim();
            ((DefaultTableModel) table.getModel()).setRowCount(0);
            SwingUtilities.invokeLater(() -> {
                List<NhaXuatBan> nxbListTK = TestDatabase.timKiemNhaXuatBan(mxb, tnxb, nxb);
                for (NhaXuatBan nxbTK : nxbListTK) {
                    Object[] row = {nxbTK.getMaNXB(), nxbTK.getTenNXB(), nxbTK.getNgayXB(), nxbTK.getDienThoai(), nxbTK.getEmail(), nxbTK.getDiaChi(), nxbTK.getGhiChu()};
                    table.addRow(row);
                }
            });
        });
    }

    private void xoaData() {
        btnXoa.addActionListener((e) -> {
            String mxb = mxbEdt.getText().trim();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa nhà xuát bản với mã " + mxb + "?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                TestDatabase.xoaNhaXuatBan(mxb);
                loadDataToTable();
                mxbEdt.setText("");
            }
        });
    }

    private void suaData() {
        btnSua.addActionListener((e) -> {
            try {
                String mxb = mxbEdt.getText().trim();
                String tnxb = tnxbEdt.getText().trim();
                String nxb = nxbEdt.getText().trim();
                if (checkNgay(nxb) == null) {
                    return;
                }
                String dt = dtEdt.getText().trim();
                if (checkDT(dt) == null) {
                    return;
                }
                String em = emEdt.getText().trim();
                if (checkMail(em) == null) {
                    return;
                }
                String dc = dcEdt.getText().trim();
                String gc = gcEdt.getText().trim();
                if (mxb.isEmpty() || tnxb.isEmpty() || nxb.isEmpty() || dt.isEmpty() || em.isEmpty() || dc.isEmpty()) {
                    System.out.println("Vui lòng điền đầy đủ thông tin!");
                } else {
                    TestDatabase.suaNhaXuatBan(mxb, tnxb, nxb, dt, em, dc, gc);
                    loadDataToTable();
                    mxbEdt.setText("");
                    tnxbEdt.setText("");
                    nxbEdt.setText("");
                    dtEdt.setText("");
                    emEdt.setText("");
                    dcEdt.setText("");
                    gcEdt.setText("");
                }
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void themData() {
        btnThem.addActionListener(e -> {
            try {
                String mxb = mxbEdt.getText().trim();
                if (!checkKey(mxb)) {
                    JOptionPane.showMessageDialog(this, "Lỗi trùng nhà xuất bản! Mời nhập lại");
                    return;
                }
                String tnxb = tnxbEdt.getText().trim();
                String nxb = nxbEdt.getText().trim();
                if (checkNgay(nxb) == null) {
                    return;
                }
                String dt = dtEdt.getText().trim();
                if (checkDT(dt) == null) {
                    return;
                }
                String em = emEdt.getText().trim();
                if (checkMail(em) == null) {
                    return;
                }
                String dc = dcEdt.getText().trim();
                String gc = gcEdt.getText().trim();
                if (mxb.isEmpty() || tnxb.isEmpty() || nxb.isEmpty() || dt.isEmpty() || em.isEmpty() || dc.isEmpty()) {
                    System.out.println("Vui lòng điền đầy đủ thông tin!");
                } else {
                    TestDatabase.addNhaXuatBan(mxb, tnxb, nxb, dt, em, dc, gc);
                    loadDataToTable();
                    mxbEdt.setText("");
                    tnxbEdt.setText("");
                    nxbEdt.setText("");
                    dtEdt.setText("");
                    emEdt.setText("");
                    dcEdt.setText("");
                    gcEdt.setText("");
                }
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void loadDataToTable() {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<NhaXuatBan> nxbList = TestDatabase.getAllNhaXuatBan();
            for (NhaXuatBan nxb : nxbList) {
                Object[] row = {nxb.getMaNXB(), nxb.getTenNXB(), nxb.getNgayXB(), nxb.getDienThoai(), nxb.getEmail(), nxb.getDiaChi(), nxb.getGhiChu()};
                table.addRow(row);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.testdatabase.PanelBorder();
        panelBorder2 = new com.testdatabase.PanelBorder();
        jLabel8 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.testdatabase.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new com.testdatabase.button.Button();
        btnSua = new com.testdatabase.button.Button();
        btnXoa = new com.testdatabase.button.Button();
        btnTimKiem = new com.testdatabase.button.Button();
        mxbEdt = new com.testdatabase.EdtTextFeild();
        tnxbEdt = new com.testdatabase.EdtTextFeild();
        nxbEdt = new com.testdatabase.EdtTextFeild();
        dtEdt = new com.testdatabase.EdtTextFeild();
        emEdt = new com.testdatabase.EdtTextFeild();
        dcEdt = new com.testdatabase.EdtTextFeild();
        gcEdt = new com.testdatabase.EdtTextFeild();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Bảng Thông Tin Chi Tiết");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã XB", "Tên NXB", "Ngày XB", "Điện Thoại", "Email", "Địa Chỉ", "Ghi Chú"
            }
        ));
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Tên Nhà Xuất Bản:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Mã Nhà Xuất Bản:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Điện Thoại:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Ngày Xuất Bản:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Email:");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Địa Chỉ:");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Ghi Chú:");

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setLayout(new java.awt.GridLayout(2, 0, 100, 20));

        btnThem.setBackground(new java.awt.Color(133, 218, 128));
        btnThem.setText("Thêm");
        jPanel1.add(btnThem);

        btnSua.setBackground(new java.awt.Color(236, 244, 80));
        btnSua.setText("Sửa");
        jPanel1.add(btnSua);

        btnXoa.setBackground(new java.awt.Color(238, 131, 108));
        btnXoa.setText("Xóa");
        jPanel1.add(btnXoa);

        btnTimKiem.setText("Tìm Kiếm");
        jPanel1.add(btnTimKiem);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                        .addGap(90, 90, 90))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mxbEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tnxbEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nxbEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gcEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mxbEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tnxbEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nxbEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gcEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tnxbEdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnxbEdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnxbEdtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.testdatabase.button.Button btnSua;
    private com.testdatabase.button.Button btnThem;
    private com.testdatabase.button.Button btnTimKiem;
    private com.testdatabase.button.Button btnXoa;
    private com.testdatabase.EdtTextFeild dcEdt;
    private com.testdatabase.EdtTextFeild dtEdt;
    private com.testdatabase.EdtTextFeild emEdt;
    private com.testdatabase.EdtTextFeild gcEdt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private com.testdatabase.EdtTextFeild mxbEdt;
    private com.testdatabase.EdtTextFeild nxbEdt;
    private com.testdatabase.PanelBorder panelBorder1;
    private com.testdatabase.PanelBorder panelBorder2;
    private javax.swing.JScrollPane spTable;
    private com.testdatabase.Table table;
    private com.testdatabase.EdtTextFeild tnxbEdt;
    // End of variables declaration//GEN-END:variables
}
