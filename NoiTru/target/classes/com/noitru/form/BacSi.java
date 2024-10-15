/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.noitru.form;

import com.noitru.ConnectDB;
import com.noitru.component.CheckLoi;
import com.noitru.model.Model_BacSi;
import com.noitru.swing.ScrollBar;
import com.noitru.swing.search.SearchOption;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class BacSi extends javax.swing.JPanel {

    public BacSi() {
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
        xuatData();
    }

    private void xuatData() {
        xuatEdt.addActionListener((ActionEvent e) -> {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("bacsi");
                // register the columns you wish to track and compute the column width
                CreationHelper createHelper = workbook.getCreationHelper();
                XSSFRow row = null;
                Cell cell = null;
                row = spreadsheet.createRow((short) 2);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("DANH SÁCH BÁC SĨ");

                //Tạo dòng tiêu đều của bảng
                // create CellStyle
                CellStyle cellStyle_Head = ConnectDB.DinhdangHeader(spreadsheet);
                row = spreadsheet.createRow((short) 3);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("STT");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Mã Bác Sĩ");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Tên Bác Sĩ");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Kinh Nghiệm");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Chuyên Khoa");

                //Kết nối DB
                Connection con = ConnectDB.connect();
                String sql = "Select * From Tacgia";
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                //Đổ dữ liệu từ rs vào các ô trong excel
                ResultSetMetaData rsmd = rs.getMetaData();
                int tongsocot = rsmd.getColumnCount();

                //Đinh dạng Tạo đường kẻ cho ô chứa dữ liệu
                CellStyle cellStyle_data = spreadsheet.getWorkbook().createCellStyle();
                cellStyle_data.setBorderLeft(BorderStyle.THIN);
                cellStyle_data.setBorderRight(BorderStyle.THIN);
                cellStyle_data.setBorderBottom(BorderStyle.THIN);
                int i = 0;
                while (rs.next()) {
                    row = spreadsheet.createRow((short) 4 + i);
                    row.setHeight((short) 400);

                    cell = row.createCell(0);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(i + 1);

                    cell = row.createCell(1);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("MaBS"));

                    cell = row.createCell(2);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("TenBS"));

                    //Định dạng ngày tháng trong excel
                    cell = row.createCell(3);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("KinhNghiem"));

                    cell = row.createCell(4);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("ChuyenKhoa"));
                    i++;
                }
                for (int col = 0; col < tongsocot; col++) {
                    spreadsheet.autoSizeColumn(col);
                }

                File f = new File("D:\\Danhsach.xlsx");
                FileOutputStream out = new FileOutputStream(f);
                workbook.write(out);
                out.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(BacSi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void cbData() {
        List<String> data = com.noitru.BacSi.getAllChuyenKhoa();
        for (String item : data) {
            chuyenkhoaCb.addItem(item);
        }
    }

    private void timKiemData() {
        timKiemBsEdt.addOption(new SearchOption("Mã Bác Sĩ",
                new ImageIcon(getClass().getResource("/com/noitru/icon/credit-card.png"))));
        timKiemBsEdt.addOption(new SearchOption("Tên Bác Sĩ",
                new ImageIcon(getClass().getResource("/com/noitru/icon/man-avatar.png"))));
        timKiemBsEdt.addEventOptionSelected((SearchOption option, int index) -> {
            timKiemBsEdt.setHint("Tìm Kiếm " + option.getName() + "...");
        });
        timKiemBsEdt.setSelectedIndex(0);
        timKiemBsEdt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (timKiemBsEdt.isSelected()) {
                    int option = timKiemBsEdt.getSelectedIndex();
                    String text = timKiemBsEdt.getText().trim();
                    switch (option) {
                        case 0 -> {
                            ((DefaultTableModel) bsTable.getModel()).setRowCount(0);
                            SwingUtilities.invokeLater(() -> {
                                List<Model_BacSi> bacSiList = com.noitru.BacSi.timKiemTheoMaBS(text);
                                for (Model_BacSi bacSi : bacSiList) {
                                    Object[] row = {bacSi.getMaBS(), bacSi.getTenBS(), bacSi.getKinhNghiem(),
                                        bacSi.getChuyenKhoa()};
                                    bsTable.addRow(row);
                                }
                            });
                        }
                        default -> {
                            ((DefaultTableModel) bsTable.getModel()).setRowCount(0);
                            SwingUtilities.invokeLater(() -> {
                                List<Model_BacSi> bacSiList = com.noitru.BacSi.timKiemTheoTenBS(text);
                                for (Model_BacSi bacSi : bacSiList) {
                                    Object[] row = {bacSi.getMaBS(), bacSi.getTenBS(), bacSi.getKinhNghiem(),
                                        bacSi.getChuyenKhoa()};
                                    bsTable.addRow(row);
                                }
                            });
                        }
                    }
                }
            }

        });
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
                } else {
                    com.noitru.BacSi.suaBacSi(MaBS, TenBS, KinhNghiem, ChuyenKhoa);
                    getAllDataBacSi();
                    mbsEdt.setText("");
                    tbsEdt.setText("");
                    nknEdt.setText("");
                    chuyenkhoaCb.setSelectedIndex(0);
                }
            } catch (HeadlessException ex) {
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
                } else {
                    com.noitru.BacSi.addBacSi(MaBS, TenBS, KinhNghiem, ChuyenKhoa);
                    getAllDataBacSi();
                    mbsEdt.setText("");
                    tbsEdt.setText("");
                    nknEdt.setText("");
                    chuyenkhoaCb.setSelectedIndex(0);
                }
            } catch (HeadlessException ex) {
            }
        });
    }

    private void getAllDataBacSi() {
        ((DefaultTableModel) bsTable.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<Model_BacSi> bacSiList = com.noitru.BacSi.getAllBacSi();
            for (Model_BacSi bacSi : bacSiList) {
                Object[] row = {bacSi.getMaBS(), bacSi.getTenBS(), bacSi.getKinhNghiem(),
                    bacSi.getChuyenKhoa()};
                bsTable.addRow(row);
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

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelBorder2 = new com.noitru.swing.PanelBorder();
        themBsBtn = new com.noitru.swing.Button();
        suaBsBtn = new com.noitru.swing.Button();
        suaBtn1 = new com.noitru.swing.Button();
        xuatEdt = new com.noitru.swing.Button();
        panelBorder5 = new com.noitru.swing.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable4 = new javax.swing.JScrollPane();
        bsTable = new com.noitru.swing.Table();
        timKiemBsEdt = new com.noitru.swing.search.TextFieldSearchOption();
        mbsEdt = new com.noitru.swing.TextFeild();
        nknEdt = new com.noitru.swing.TextFeild();
        tbsEdt = new com.noitru.swing.TextFeild();
        chuyenkhoaCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(112, 112, 214));
        jLabel6.setText("Mã Bác sĩ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(112, 112, 214));
        jLabel7.setText("Tên bác sĩ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(112, 112, 214));
        jLabel9.setText("Chuyên khoa");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(112, 112, 214));
        jLabel8.setText("Số năm kinh nghiệm");

        panelBorder2.setBackground(new java.awt.Color(204, 204, 255));

        themBsBtn.setBackground(new java.awt.Color(127, 148, 240));
        themBsBtn.setForeground(new java.awt.Color(255, 255, 255));
        themBsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/add3.png"))); // NOI18N
        themBsBtn.setText("Thêm");
        themBsBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        suaBsBtn.setBackground(new java.awt.Color(29, 29, 140));
        suaBsBtn.setForeground(new java.awt.Color(255, 255, 255));
        suaBsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/wrench.png"))); // NOI18N
        suaBsBtn.setText("Sửa");
        suaBsBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        suaBtn1.setForeground(new java.awt.Color(102, 102, 255));
        suaBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/undo.png"))); // NOI18N
        suaBtn1.setText("Reset");
        suaBtn1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        xuatEdt.setBackground(new java.awt.Color(90, 90, 224));
        xuatEdt.setForeground(new java.awt.Color(255, 255, 255));
        xuatEdt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/up-arrow.png"))); // NOI18N
        xuatEdt.setText("Xuất Excel");
        xuatEdt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(themBsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(suaBsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(suaBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(xuatEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themBsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suaBsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suaBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xuatEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelBorder5.setBackground(new java.awt.Color(201, 214, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DANH SÁCH BÁC SĨ");

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

        timKiemBsEdt.setForeground(new java.awt.Color(80, 80, 80));
        timKiemBsEdt.setColorOverlay2(new java.awt.Color(146, 146, 246));

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
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
                .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        mbsEdt.setBackground(new java.awt.Color(248, 248, 255));
        mbsEdt.setSelectionColor(new java.awt.Color(102, 102, 255));
        mbsEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        nknEdt.setBackground(new java.awt.Color(248, 248, 255));
        nknEdt.setSelectionColor(new java.awt.Color(102, 102, 255));
        nknEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        tbsEdt.setBackground(new java.awt.Color(248, 248, 255));
        tbsEdt.setSelectionColor(new java.awt.Color(102, 102, 255));
        tbsEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        chuyenkhoaCb.setBackground(new java.awt.Color(248, 248, 255));
        chuyenkhoaCb.setForeground(new java.awt.Color(102, 102, 255));
        chuyenkhoaCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn chuyên khoa--" }));

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
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nknEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tbsEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mbsEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel9)
                            .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mbsEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbsEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nknEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207)))
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
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
    private com.noitru.swing.Button suaBtn1;
    private com.noitru.swing.TextFeild tbsEdt;
    private com.noitru.swing.Button themBsBtn;
    private com.noitru.swing.search.TextFieldSearchOption timKiemBsEdt;
    private com.noitru.swing.Button xuatEdt;
    // End of variables declaration//GEN-END:variables
}
