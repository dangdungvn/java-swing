package com.noitru.form;

import com.noitru.BenhNhan;
import com.noitru.ConnectDB;
import com.noitru.ThongTinKhamBenh;
import com.noitru.component.CheckLoi;
import com.noitru.model.Model_BenhNhan;
import com.noitru.swing.ScrollBar;
import com.noitru.swing.search.SearchOption;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        xuatData();
    }

    private void xuatData() {
        xuatBtn.addActionListener((ActionEvent e) -> {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("benhnhan");
                // register the columns you wish to track and compute the column width
                CreationHelper createHelper = workbook.getCreationHelper();
                XSSFRow row = null;
                Cell cell = null;
                row = spreadsheet.createRow((short) 2);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("DANH SÁCH BỆNH NHÂN");

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
                cell.setCellValue("Ngày Sinh");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Giới Tính");

                cell = row.createCell(5, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("CCCD");

                cell = row.createCell(6, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Địa Chỉ");

                cell = row.createCell(7, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("BHYT");

                cell = row.createCell(8, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Điện Thoại");
                cell = row.createCell(9, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Tình Trạng");

                //Kết nối DB
                Connection con = ConnectDB.connect();
                String sql = "Select * From benhnhan";
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
                    cell.setCellValue(rs.getString("MaBN"));

                    cell = row.createCell(2);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("TenBN"));

                    //Định dạng ngày tháng trong excel
                    java.util.Date ngay = new java.util.Date(rs.getDate("Ngaysinh").getTime());
                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cell = row.createCell(3);
                    cell.setCellValue(ngay);
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(4);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("GioiTinh"));

                    cell = row.createCell(5);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("CCCD"));

                    cell = row.createCell(6);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("DiaChi"));

                    cell = row.createCell(7);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("BHYT"));

                    cell = row.createCell(8);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("DienThoai"));

                    cell = row.createCell(9);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("TinhTrang"));
                    i++;
                }
                for (int col = 0; col < tongsocot; col++) {
                    spreadsheet.autoSizeColumn(col);
                }

                File f = new File("D:\\DanhsachBenhNhan.xlsx");
                FileOutputStream out = new FileOutputStream(f);
                workbook.write(out);
                out.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Form_BacSi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // static Connection conn;
    private void tableClick() {
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

    private void getAllDataBenhNhan() {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<Model_BenhNhan> benhNhanList = BenhNhan.getAllBenhNhan();
            for (Model_BenhNhan benhNhan : benhNhanList) {
                Object[] row = {benhNhan.getMaBN(), benhNhan.getTenBN(), benhNhan.getNgaySinh(),
                    benhNhan.getGioiTinh(),
                    benhNhan.getCCCD(), benhNhan.getDiaChi(), benhNhan.getBHYT(),
                    benhNhan.getDienThoai(), benhNhan.getTinhTrang(), benhNhan.getNgayDKKham(), benhNhan.getNgayRaVien()};
                table.addRow(row);
            }
        });
    }

    private void themData() {
        themBtn.addActionListener(e -> {
            try {
                String MaBN = mbnEdt.getText().trim();
                if (!CheckLoi.checkKey(MaBN, "benhnhan")) {
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
                String TinhTrang = "Chờ Xét Nghiệm";
                String NgayDKKham = LocalDate.now().toString();
                if (CheckLoi.checkDT(DienThoai) == null) {
                    JOptionPane.showMessageDialog(this, "Lỗi số điện thoại, xin vui lòng nhập lại!");
                    return;
                }
                if (TenBN.isEmpty() || NgaySinh.isEmpty() || GioiTinh.isEmpty() || CCCD.isEmpty()
                        || DiaChi.isEmpty()
                        || BHYT.isEmpty() || DienThoai.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                } else {
                    BenhNhan.addBenhNhan(MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT,
                            DienThoai, TinhTrang, NgayDKKham, null);
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
                String TinhTrang = "Chờ Xét Nghiệm";
                if (CheckLoi.checkDT(DienThoai) == null) {
                    JOptionPane.showMessageDialog(this, "Lỗi số điện thoại, xin vui lòng nhập lại!");
                    return;
                }
                if (TenBN.isEmpty() || NgaySinh.isEmpty() || GioiTinh.isEmpty() || CCCD.isEmpty()
                        || DiaChi.isEmpty()
                        || BHYT.isEmpty() || DienThoai.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                } else {
                    BenhNhan.suaBenhNhan(MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT,
                            DienThoai, TinhTrang);
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
        timKiemEdt.addOption(new SearchOption("Mã Bệnh Nhân",
                new ImageIcon(getClass().getResource("/com/noitru/icon/credit-card.png"))));
        timKiemEdt.addOption(new SearchOption("Tên Bệnh Nhân",
                new ImageIcon(getClass().getResource("/com/noitru/icon/man-avatar.png"))));
        timKiemEdt.addOption(new SearchOption("Ngày Sinh",
                new ImageIcon(getClass().getResource("/com/noitru/icon/calendar-page.png"))));
        timKiemEdt.addEventOptionSelected((SearchOption option, int index) -> {
            timKiemEdt.setHint("Tìm Kiếm " + option.getName() + "...");
        });
        timKiemEdt.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        panelBorder1 = new com.noitru.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.noitru.swing.Table();
        timKiemEdt = new com.noitru.swing.search.TextFieldSearchOption();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mbnEdt = new com.noitru.swing.TextFeild();
        jLabel3 = new javax.swing.JLabel();
        tbnEdt = new com.noitru.swing.TextFeild();
        jLabel4 = new javax.swing.JLabel();
        nsEdt = new com.noitru.swing.TextFeild();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cccdEdt = new com.noitru.swing.TextFeild();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dcEdt = new com.noitru.swing.TextFeild();
        bhEdt = new com.noitru.swing.TextFeild();
        dtEdt = new com.noitru.swing.TextFeild();
        gtEdt = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        panelBorder2 = new com.noitru.swing.PanelBorder();
        themBtn = new com.noitru.swing.Button();
        suaBtn = new com.noitru.swing.Button();
        xuatBtn = new com.noitru.swing.Button();

        dateChooser1.setTextRefernce(nsEdt);

        setBackground(new java.awt.Color(242, 242, 242));

        panelBorder1.setBackground(new java.awt.Color(146, 146, 236));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bệnh Nhân Nhập Viện");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaBN", "TenBN", "Ngày Sinh", "Giới Tính", "CCCD", "Địa Chỉ", "BHYT", "Điện Thoại", "Tình Trạng", "NgayDKKham", "NgayXuatVien"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        timKiemEdt.setBackground(new java.awt.Color(255, 255, 255));
        timKiemEdt.setForeground(new java.awt.Color(80, 80, 80));
        timKiemEdt.setColorOverlay2(new java.awt.Color(153, 153, 255));
        timKiemEdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timKiemEdtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timKiemEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(timKiemEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã Bệnh Nhân");

        mbnEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tên Bệnh Nhân");

        tbnEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ngày Sinh");

        nsEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Giới Tính");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CCCD");

        cccdEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Địa Chỉ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BHYT");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Điện Thoại");

        dcEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        bhEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        dtEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        gtEdt.setBackground(new java.awt.Color(248, 248, 255));
        gtEdt.setForeground(new java.awt.Color(80, 80, 80));
        gtEdt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Giới Tính--", "Nam", "Nữ" }));

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(mbnEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbnEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nsEdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gtEdt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cccdEdt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(bhEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        panelBorder2.setBackground(new java.awt.Color(227, 227, 255));
        panelBorder2.setForeground(new java.awt.Color(227, 227, 255));

        themBtn.setBackground(new java.awt.Color(44, 44, 168));
        themBtn.setForeground(new java.awt.Color(255, 255, 255));
        themBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/plus.png"))); // NOI18N
        themBtn.setText("Thêm");
        themBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        themBtn.setShadowColor(new java.awt.Color(0, 0, 255));

        suaBtn.setForeground(new java.awt.Color(51, 51, 255));
        suaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/wrench.png"))); // NOI18N
        suaBtn.setText("Sửa");
        suaBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        suaBtn.setShadowColor(new java.awt.Color(0, 51, 255));

        xuatBtn.setBackground(new java.awt.Color(90, 90, 224));
        xuatBtn.setForeground(new java.awt.Color(255, 255, 255));
        xuatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/up-arrow.png"))); // NOI18N
        xuatBtn.setText("Xuất Excel");
        xuatBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(suaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(xuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void timKiemEdtKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_timKiemEdtKeyReleased
        // TODO add your handling code here:
        if (timKiemEdt.isSelected()) {
            int option = timKiemEdt.getSelectedIndex();
            String text = timKiemEdt.getText().trim();
            switch (option) {
                case 0 -> {
                    ((DefaultTableModel) table.getModel()).setRowCount(0);
                    SwingUtilities.invokeLater(() -> {
                        List<Model_BenhNhan> benhNhanListTK = BenhNhan.timKiemTheoMaBN(text);
                        for (Model_BenhNhan benhNhan : benhNhanListTK) {
                            Object[] row = {benhNhan.getMaBN(), benhNhan.getTenBN(),
                                benhNhan.getNgaySinh(),
                                benhNhan.getGioiTinh(),
                                benhNhan.getCCCD(), benhNhan.getDiaChi(),
                                benhNhan.getBHYT(), benhNhan.getDienThoai()};
                            table.addRow(row);
                        }
                    });
                }
                case 1 -> {
                    ((DefaultTableModel) table.getModel()).setRowCount(0);
                    SwingUtilities.invokeLater(() -> {
                        List<Model_BenhNhan> benhNhanListTK = BenhNhan.timKiemTheoMaBN(text);
                        for (Model_BenhNhan benhNhan : benhNhanListTK) {
                            Object[] row = {benhNhan.getMaBN(), benhNhan.getTenBN(),
                                benhNhan.getNgaySinh(),
                                benhNhan.getGioiTinh(),
                                benhNhan.getCCCD(), benhNhan.getDiaChi(),
                                benhNhan.getBHYT(), benhNhan.getDienThoai(), benhNhan.getTinhTrang()};
                            table.addRow(row);
                        }
                    });
                }
                default -> {
                    ((DefaultTableModel) table.getModel()).setRowCount(0);
                    SwingUtilities.invokeLater(() -> {
                        List<Model_BenhNhan> benhNhanListTK = BenhNhan.timKiemTheoNgaySinh(text);
                        for (Model_BenhNhan benhNhan : benhNhanListTK) {
                            Object[] row = {benhNhan.getMaBN(), benhNhan.getTenBN(),
                                benhNhan.getNgaySinh(),
                                benhNhan.getGioiTinh(),
                                benhNhan.getCCCD(), benhNhan.getDiaChi(),
                                benhNhan.getBHYT(), benhNhan.getDienThoai(), benhNhan.getTinhTrang()};
                            table.addRow(row);
                        }
                    });
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.swing.TextFeild bhEdt;
    private com.noitru.swing.TextFeild cccdEdt;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.noitru.swing.TextFeild dcEdt;
    private com.noitru.swing.TextFeild dtEdt;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion gtEdt;
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
    private com.noitru.swing.PanelBorder panelBorder2;
    private javax.swing.JScrollPane spTable;
    private com.noitru.swing.Button suaBtn;
    private com.noitru.swing.Table table;
    private com.noitru.swing.TextFeild tbnEdt;
    private com.noitru.swing.Button themBtn;
    private com.noitru.swing.search.TextFieldSearchOption timKiemEdt;
    private com.noitru.swing.Button xuatBtn;
    // End of variables declaration//GEN-END:variables
}
