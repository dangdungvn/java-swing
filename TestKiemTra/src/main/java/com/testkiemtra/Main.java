/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.testkiemtra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Main extends javax.swing.JFrame {

    static Connection conn;
    private boolean actionCb = true;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        loadDBProduct();
        loadDBCategory();
        msEdt.setEnabled(false);
        tdEdt.setEnabled(false);
        gEdt.setEnabled(false);
        slEdt.setEnabled(false);
        luuBtn.setEnabled(false);
        xoaBtn.setEnabled(false);
        bqBtn.setEnabled(false);
    }

    private boolean checkKey(String mxb) {
        boolean kq = false;
        try {
            conn = TestKiemTra.connect();
            String sql = "SELECT * FROM `product` Where productcode = ('" + mxb + "') ";
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

    public int checkGia(int gia) {
        if ((gia > 0) && (gia % 1000 == 0)) {
            return gia;
        } else {
            JOptionPane.showMessageDialog(this, "Nhập sai giá trị! Mời nhập lại!");
            return 0;
        }
    }

    public int checkSl(int sl) {
        if ((sl > 0)) {
            return sl;
        } else {
            JOptionPane.showMessageDialog(this, "Nhập sai giá trị! Mời nhập lại!");
            return 0;
        }
    }
    private String[] arr = {"Mã sách", "Tiêu đề", "Giá", "Số Lượng"};
    private DefaultTableModel model = new DefaultTableModel(arr, 0);

    public void checkEnableLuuBtn() {
        if (!msEdt.getText().isEmpty() || !tdEdt.getText().isEmpty() || !gEdt.getText().isEmpty() || !slEdt.getText().isEmpty()) {
            xoaBtn.setEnabled(true);
            luuBtn.setEnabled(true);
        } else {
            xoaBtn.setEnabled(false);
            luuBtn.setEnabled(false);
        }
    }

    public void loadDBCategory() {
        try {
            conn = TestKiemTra.connect();
            String sql = "SELECT * FROM `category`";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbCategory.addItem(rs.getString("categoryname"));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDBProduct() {
        try {
            conn = TestKiemTra.connect();
            ((DefaultTableModel) table.getModel()).setRowCount(0);
            String sql = "SELECT * FROM `product`";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<String> vt = new Vector<>();
                vt.add(rs.getString("productcode"));
                vt.add(rs.getString("description"));
                vt.add(rs.getString("unitprice"));
                vt.add(rs.getString("onhandquality"));
                model.addRow(vt);
                System.out.println(vt);
            }
            table.setModel(model);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        msEdt = new javax.swing.JTextField();
        tdEdt = new javax.swing.JTextField();
        gEdt = new javax.swing.JTextField();
        slEdt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tmBtn = new javax.swing.JButton();
        suaBtn = new javax.swing.JButton();
        luuBtn = new javax.swing.JButton();
        xoaBtn = new javax.swing.JButton();
        bqBtn = new javax.swing.JButton();
        gDuoiEdt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        gTrenEdt = new javax.swing.JTextField();
        tkBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cbCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Loại Sách--" }));
        cbCategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoryItemStateChanged(evt);
            }
        });
        cbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Loại Sách:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Mã Sách:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Tiêu Đề:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Giá:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Số Lượng:");

        msEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        msEdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                msEdtKeyReleased(evt);
            }
        });

        tdEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tdEdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tdEdtKeyReleased(evt);
            }
        });

        gEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gEdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                gEdtKeyReleased(evt);
            }
        });

        slEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        slEdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                slEdtKeyReleased(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(100);
            table.getColumnModel().getColumn(1).setMinWidth(300);
            table.getColumnModel().getColumn(2).setMinWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(100);
        }

        tmBtn.setText("Thêm mới");
        tmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmBtnActionPerformed(evt);
            }
        });

        suaBtn.setText("Sửa");
        suaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaBtnActionPerformed(evt);
            }
        });

        luuBtn.setText("Lưu");
        luuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luuBtnActionPerformed(evt);
            }
        });

        xoaBtn.setText("Xóa");
        xoaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaBtnActionPerformed(evt);
            }
        });

        bqBtn.setText("Bỏ qua");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Tìm Kiếm Theo Số Lượng:");

        tkBtn.setText("Tìm kiếm");
        tkBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCategory, 0, 261, Short.MAX_VALUE)
                            .addComponent(tdEdt)
                            .addComponent(msEdt))
                        .addGap(171, 171, 171)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gEdt, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(slEdt)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(40, 40, 40)
                                .addComponent(gDuoiEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(gTrenEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tmBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(suaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(luuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(xoaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bqBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tkBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(msEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(tdEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(tmBtn)
                        .addGap(20, 20, 20)
                        .addComponent(suaBtn)
                        .addGap(20, 20, 20)
                        .addComponent(luuBtn)
                        .addGap(20, 20, 20)
                        .addComponent(xoaBtn)
                        .addGap(20, 20, 20)
                        .addComponent(bqBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gDuoiEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(gTrenEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tkBtn))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoryItemStateChanged
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            ((DefaultTableModel) table.getModel()).setRowCount(0);
            String text = cbCategory.getSelectedItem().toString();
            conn = TestKiemTra.connect();
            String kqCB = null;
            String sql = "SELECT * FROM `category` Where categoryname like '%" + text + "%' ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                kqCB = rs.getString("categoryid");
            }

            if (kqCB != null) {
                conn = TestKiemTra.connect();
                String sqlCB = "SELECT * FROM `product` Where categoryid like '%" + kqCB + "%' ";
                ResultSet rsCB = st.executeQuery(sqlCB);

                while (rsCB.next()) {
                    Vector<String> vt = new Vector<>();
                    vt.add(rsCB.getString("productcode"));
                    vt.add(rsCB.getString("description"));
                    vt.add(rsCB.getString("unitprice"));
                    vt.add(rsCB.getString("onhandquality"));
                    model.addRow(vt);
                    System.out.println(vt);
                }
                conn.close();
            } else {

                loadDBProduct();
            }
            conn.close();
            table.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }

    }//GEN-LAST:event_cbCategoryItemStateChanged

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int i = table.getSelectedRow();
        DefaultTableModel tb = (DefaultTableModel) table.getModel();
        String text = tb.getValueAt(i, 0).toString();
        msEdt.setText(tb.getValueAt(i, 0).toString());
        tdEdt.setText(tb.getValueAt(i, 1).toString());
        gEdt.setText(tb.getValueAt(i, 2).toString());
        slEdt.setText(tb.getValueAt(i, 3).toString());
        cbCategory.setEnabled(false);
        String kqCb1 = null;

        try {
            conn = TestKiemTra.connect();
            String sql = "SELECT * FROM `product` Where productcode like '%" + text + "%' ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                kqCb1 = rs.getString("categoryid");
            }
            String sqlKq = "SELECT * FROM `category` Where categoryid like '%" + kqCb1 + "%' ";
            ResultSet rsKq = st.executeQuery(sqlKq);
            while (rsKq.next()) {
                String cb = rsKq.getString("categoryname");
                if (cb.equals("Lập Trình")) {
                    cbCategory.setSelectedItem("Lập Trình");
                } else {
                    cbCategory.setSelectedItem("Toán");
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void tmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmBtnActionPerformed
        // TODO add your handling code here:
        msEdt.setEnabled(true);
        tdEdt.setEnabled(true);
        gEdt.setEnabled(true);
        slEdt.setEnabled(true);
        cbCategory.setEnabled(true);

    }//GEN-LAST:event_tmBtnActionPerformed

    private void msEdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_msEdtKeyReleased
        // TODO add your handling code here:
        checkEnableLuuBtn();
    }//GEN-LAST:event_msEdtKeyReleased

    private void tdEdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdEdtKeyReleased
        // TODO add your handling code here:
        checkEnableLuuBtn();
    }//GEN-LAST:event_tdEdtKeyReleased

    private void gEdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gEdtKeyReleased
        // TODO add your handling code here:
        checkEnableLuuBtn();
    }//GEN-LAST:event_gEdtKeyReleased

    private void slEdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_slEdtKeyReleased
        // TODO add your handling code here:
        checkEnableLuuBtn();
    }//GEN-LAST:event_slEdtKeyReleased

    private void cbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoryActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbCategoryActionPerformed

    private void luuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luuBtnActionPerformed
        try {

            // TODO add your handling code here:
            conn = TestKiemTra.connect();
            String productcode = msEdt.getText().trim();
            if (!checkKey(productcode)) {
                JOptionPane.showMessageDialog(this, "Lỗi trùng nhà xuất bản! Mời nhập lại");
                return;
            }
            String description = tdEdt.getText().trim();
            int unitprice = Integer.parseInt(gEdt.getText().trim());
            if (checkGia(unitprice) == 0) {
                return;
            }
            int onhandquality = Integer.parseInt(slEdt.getText().trim());
            if (checkSl(onhandquality) == 0) {
                return;
            }
            String categoryname = cbCategory.getSelectedItem().toString();
            String categoryid = null;
            if (categoryname.equals("--Chọn Loại Sách--")) {
                JOptionPane.showMessageDialog(this, "Hãy chọn loại sách!");
                return;
            } else {
                conn = TestKiemTra.connect();
                String sql = "SELECT * FROM `category` Where categoryname like '%" + categoryname + "%' ";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    categoryid = rs.getString("categoryid");
                }
                conn.close();
            }
            String sql = "Insert product Values('" + productcode + "',N'" + description + "','" + unitprice + "','" + onhandquality + "','" + categoryid + "')";
            conn = TestKiemTra.connect();
            try (Statement st = conn.createStatement()) {
                st.executeUpdate(sql);
            }
            conn.close();
            loadDBProduct();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_luuBtnActionPerformed

    private void suaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaBtnActionPerformed
        try {
            conn = TestKiemTra.connect();
            // TODO add your handling code here:
            String productcode = msEdt.getText().trim();
            String description = tdEdt.getText().trim();
            int unitprice = Integer.parseInt(gEdt.getText().trim());
            if (checkGia(unitprice) == 0) {
                return;
            }
            int onhandquality = Integer.parseInt(slEdt.getText().trim());
            if (checkSl(onhandquality) == 0) {
                return;
            }
            String categoryname = cbCategory.getSelectedItem().toString();
            String categoryid = null;
            if (categoryname.equals("--Chọn Loại Sách--")) {
                JOptionPane.showMessageDialog(this, "Hãy chọn loại sách!");
                return;
            } else {
                String sql = "SELECT * FROM `category` Where categoryname like '%" + categoryname + "%' ";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    categoryid = rs.getString("categoryid");
                }
            }
            String sql = "Update product Set description = (N'" + description + "'), unitprice = ('" + unitprice + "'), onhandquality = ('" + onhandquality + "'), categoryid = ('"
                    + categoryid + "') Where productcode = ('" + productcode + "')";
            conn = TestKiemTra.connect();
            try (Statement st = conn.createStatement()) {
                st.executeUpdate(sql);
            }

            conn.close();
            loadDBProduct();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }// TODO add your handling code here:

    }//GEN-LAST:event_suaBtnActionPerformed

    private void xoaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaBtnActionPerformed
        // TODO add your handling code here:
        try {
            String productcode = msEdt.getText().trim();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa sách với mã " + productcode + "?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String sql = "Delete From product Where productcode = ('" + productcode + "')";
                conn = TestKiemTra.connect();
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                conn.close();
                loadDBProduct();
//                loadJCB();
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_xoaBtnActionPerformed

    private void tkBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkBtnActionPerformed
        // TODO add your handling code here:
        try {
            ((DefaultTableModel) table.getModel()).setRowCount(0);
            int giaDuoi = Integer.parseInt(gDuoiEdt.getText().trim());
            int giaTren = Integer.parseInt(gTrenEdt.getText().trim());
            conn = TestKiemTra.connect();
            String sql = "SELECT * FROM `product` Where unitprice >= '" + giaDuoi + "' AND unitprice <= '" + giaTren + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector<String> vt = new Vector<>();
                vt.add(rs.getString("productcode"));
                vt.add(rs.getString("description"));
                vt.add(rs.getString("unitprice"));
                vt.add(rs.getString("onhandquality"));
                model.addRow(vt);
                System.out.println(vt);
            }
            conn.close();
            table.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tkBtnActionPerformed

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
    private javax.swing.JButton bqBtn;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JTextField gDuoiEdt;
    private javax.swing.JTextField gEdt;
    private javax.swing.JTextField gTrenEdt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton luuBtn;
    private javax.swing.JTextField msEdt;
    private javax.swing.JTextField slEdt;
    private javax.swing.JButton suaBtn;
    private javax.swing.JTable table;
    private javax.swing.JTextField tdEdt;
    private javax.swing.JButton tkBtn;
    private javax.swing.JButton tmBtn;
    private javax.swing.JButton xoaBtn;
    // End of variables declaration//GEN-END:variables
}
