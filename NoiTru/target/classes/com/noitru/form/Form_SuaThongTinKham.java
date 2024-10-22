package com.noitru.form;

import com.noitru.BacSi;
import com.noitru.ConnectDB;
import com.noitru.ThongTinKhamBenh;
import com.noitru.model.Model_BacSi;
import com.noitru.model.Model_ThongTinKhamBenh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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

public class Form_SuaThongTinKham extends javax.swing.JPanel {

    String PhongKham;

    public Form_SuaThongTinKham() {
//        spTable.setVerticalScrollBar(new ScrollBar());
//        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
//        spTable.getViewport().setBackground(Color.WHITE);
//        JPanel p = new JPanel();
//        p.setBackground(Color.WHITE);
//        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        initComponents();
        loadDB();
        loadCB();
        tableClick();
        updateData();
        xuatData();
    }

    public String getDuLieuCot3(int row) {
        return table.getValueAt(row, 2).toString();
    }

    private void tableClick() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    mbsCb.setSelectedItem(table.getValueAt(selectedRow, 0));
                    String text = table.getValueAt(selectedRow, 4).toString();
                    List<Model_ThongTinKhamBenh> ttkbList = ThongTinKhamBenh.timKiemTheoMaBN(text);
                    for (Model_ThongTinKhamBenh benhNhan : ttkbList) {
                        mbnCb.setSelectedItem(benhNhan.getMaBN());
                        cnEdt.setText(benhNhan.getCanNang());
                        nmEdt.setText(benhNhan.getNhomMau());
                        ndEdt.setText(benhNhan.getNhietDo());
                        machEdt.setText(benhNhan.getMach());
                        haEdt.setText(benhNhan.getHuyetAp());
                        ntEdt.setText(benhNhan.getNhipTho());
                        ldkEdt.setText(benhNhan.getLyDoKham());
                        tthtEdt.setText(benhNhan.getTinhTrangHienTai());
                        cdEdt.setText(benhNhan.getChuanDoanSoBo());
                        hdtEdt.setText(benhNhan.getHuongDieuTri());
                        ckCb.setSelectedItem(benhNhan.getChuyenKhoa());
                        PhongKham = benhNhan.getPhongKham();
                        nkEdt.setText(benhNhan.getNgayKham());
                    }
                }
            }
        });
    }

    private void xuatData() {
        xuatBtn.addActionListener((var e) -> {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("thongtinkhambenh");
                // register the columns you wish to track and compute the column width
                CreationHelper createHelper = workbook.getCreationHelper();
                XSSFRow row = null;
                Cell cell = null;
                row = spreadsheet.createRow((short) 2);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("DANH SÁCH THÔNG TIN KHÁM BỆNH");

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
                cell.setCellValue("Ngày Khám");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Phòng Khám");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Mã Bệnh Nhân");

                cell = row.createCell(5, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Chuyên Khoa");

                cell = row.createCell(6, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Cân Nặng");

                cell = row.createCell(7, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Nhóm Máu");

                cell = row.createCell(8, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Nhiệt Độ");
                cell = row.createCell(9, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Mạch");

                cell = row.createCell(10, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Huyết Áp");

                cell = row.createCell(11, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Nhịp Thở");
                cell = row.createCell(12, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Lý Do Khám");

                cell = row.createCell(13, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Tình Trạng Hiện Tại");

                cell = row.createCell(14, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Chuẩn Đoán Sơ Bộ");

                cell = row.createCell(15, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Hướng Điều Trị");

                cell = row.createCell(16, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Tên Bệnh Nhân");
                cell = row.createCell(17, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Địa Chỉ");
                cell = row.createCell(18, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("CCCD");

                cell = row.createCell(19, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Giới Tính");

                cell = row.createCell(20, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Ngày ĐK Khám");

                cell = row.createCell(21, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Tình Trạng");

                //Kết nối DB
                Connection con = ConnectDB.connect();
                String sql = "SELECT thongtinkhambenh.*, benhnhan.TenBN, benhnhan.DiaChi, benhnhan.CCCD, benhnhan.GioiTinh ,benhnhan.NgayDKKham, benhnhan.TinhTrang "
                        + "FROM thongtinkhambenh "
                        + "JOIN benhnhan ON thongtinkhambenh.MaBN = benhnhan.MaBN";
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

                    //Định dạng ngày tháng trong excel
                    java.util.Date ngay = new java.util.Date(rs.getDate("NgayKham").getTime());
                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cell = row.createCell(2);
                    cell.setCellValue(ngay);
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(3);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("PhongKham"));

                    cell = row.createCell(4);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("MaBN"));

                    cell = row.createCell(5);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("ChuyenKhoa"));

                    cell = row.createCell(6);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("CanNang"));

                    cell = row.createCell(7);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("NhomMau"));

                    cell = row.createCell(8);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("NhietDo"));

                    cell = row.createCell(9);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("Mach"));

                    cell = row.createCell(10);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("HuyetAp"));

                    cell = row.createCell(11);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("NhipTho"));

                    cell = row.createCell(12);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("LyDoKham"));

                    cell = row.createCell(13);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("TinhTrangHienTai"));

                    cell = row.createCell(14);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("ChuanDoanSoBo"));

                    cell = row.createCell(15);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("HuongDieuTri"));

                    cell = row.createCell(16);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("TenBN"));

                    cell = row.createCell(17);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("DiaChi"));

                    cell = row.createCell(18);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("CCCD"));

                    cell = row.createCell(19);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("GioiTinh"));

                    java.util.Date ngaydk = new java.util.Date(rs.getDate("NgayDKKham").getTime());
                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cell = row.createCell(20);
                    cell.setCellValue(ngay);
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(21);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("TinhTrang"));
                    i++;
                }
                for (int col = 0; col < tongsocot; col++) {
                    spreadsheet.autoSizeColumn(col);
                }

                File f = new File("D:\\DanhsachTinhTrangBenhNhan.xlsx");
                try (FileOutputStream out = new FileOutputStream(f)) {
                    workbook.write(out);
                }

            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Form_BacSi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void updateData() {
        updateBtn.addActionListener((ActionEvent e) -> {
            try {
                String MaBS = mbsCb.getSelectedItem().toString();
                String NgayKham = nkEdt.getText().trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(NgayKham);
                NgayKham = new SimpleDateFormat("yyyy-MM-dd").format(date);
                String MaBN = mbnCb.getSelectedItem().toString();
                String ChuyenKhoa = ckCb.getSelectedItem().toString();
                String CanNang = cnEdt.getText().trim();
                String NhomMau = nmEdt.getText().trim();
                String NhietDo = ndEdt.getText().trim();
                String Mach = machEdt.getText().trim();
                String HuyetAp = haEdt.getText().trim();
                String NhipTho = ntEdt.getText().trim();
                String LyDoKham = ldkEdt.getText().trim();
                String TinhTrangHienTai = tthtEdt.getText().trim();
                String ChuanDoanSoBo = cdEdt.getText().trim();
                String HuongDieuTri = hdtEdt.getText().trim();
                if (ThongTinKhamBenh.suaThongTin(MaBS, NgayKham, PhongKham, MaBN, ChuyenKhoa, CanNang, NhomMau, NhietDo,
                        Mach, HuyetAp, NhipTho, LyDoKham, TinhTrangHienTai, ChuanDoanSoBo,
                        HuongDieuTri)) {
                    JOptionPane.showMessageDialog(this, "Đã sửa");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi");
                }
                loadDB();
            } catch (ParseException ex) {
                Logger.getLogger(Form_SuaThongTinKham.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void loadCB() {
        List<Model_ThongTinKhamBenh> ttkbList = ThongTinKhamBenh.getAllThongTin();
        for (Model_ThongTinKhamBenh mttkb : ttkbList) {
            mbnCb.addItem(mttkb.getMaBN());
            nkEdt.setText(mttkb.getNgayKham());
        }
        List<Model_BacSi> listBS = BacSi.getAllBacSi();
        for (Model_BacSi mbs : listBS) {
            mbsCb.addItem(mbs.getMaBS());
        }
        mbnCb.addActionListener((ActionEvent e) -> {
            String text = mbnCb.getSelectedItem().toString();
            if (text != null && !text.equals("--Chọn Mã BN--")) {
                ((DefaultTableModel) table.getModel()).setRowCount(0);
                SwingUtilities.invokeLater(() -> {
                    List<Model_ThongTinKhamBenh> ttkbListTK = ThongTinKhamBenh
                            .timKiemTheoMaBN(text);
                    for (Model_ThongTinKhamBenh benhNhan : ttkbListTK) {
                        Object[] row = {benhNhan.getMaBS(), benhNhan.getNgayKham(),
                            benhNhan.getPhongKham(), benhNhan.getChuyenKhoa(),
                            benhNhan.getMaBN()};
                        table.addRow(row);
                    }
                });
            } else {
                loadDB();
            }

        });
        List<String> chuyenKhoaList = BacSi.getAllChuyenKhoa();
        for (String string : chuyenKhoaList) {
            ckCb.addItem(string);
        }
    }

    private void loadDB() {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        SwingUtilities.invokeLater(() -> {
            List<Model_ThongTinKhamBenh> ttkbList = ThongTinKhamBenh.getAllThongTin();
            for (Model_ThongTinKhamBenh benhNhan : ttkbList) {
                Object[] row = {benhNhan.getMaBS(), benhNhan.getNgayKham(),
                    benhNhan.getPhongKham(), benhNhan.getChuyenKhoa(), benhNhan.getMaBN()};
                table.addRow(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        panelBorder7 = new com.noitru.swing.PanelBorder();
        jLabel16 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.noitru.swing.Table();
        mbnCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        updateBtn = new com.noitru.swing.Button();
        xuatBtn = new com.noitru.swing.Button();
        panelBorder1 = new com.noitru.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cnEdt = new com.noitru.swing.TextFeild();
        nmEdt = new com.noitru.swing.TextFeild();
        ndEdt = new com.noitru.swing.TextFeild();
        jLabel4 = new javax.swing.JLabel();
        machEdt = new com.noitru.swing.TextFeild();
        jLabel5 = new javax.swing.JLabel();
        haEdt = new com.noitru.swing.TextFeild();
        jLabel6 = new javax.swing.JLabel();
        ntEdt = new com.noitru.swing.TextFeild();
        jLabel7 = new javax.swing.JLabel();
        ldkEdt = new com.noitru.swing.TextFeild();
        jLabel8 = new javax.swing.JLabel();
        tthtEdt = new com.noitru.swing.TextFeild();
        jLabel9 = new javax.swing.JLabel();
        cdEdt = new com.noitru.swing.TextFeild();
        hdtEdt = new com.noitru.swing.TextFeild();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ckCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        jLabel19 = new javax.swing.JLabel();
        mbsCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        nkEdt = new com.noitru.swing.TextFeild();

        dateChooser1.setTextRefernce(nkEdt);

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        panelBorder7.setBackground(new java.awt.Color(153, 153, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 255));
        jLabel16.setText("DANH SÁCH BỆNH NHÂN");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaBS", "Ngày Khám", "Phòng Khám", "Chuyên Khoa", "MaBN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        mbnCb.setBackground(new java.awt.Color(153, 153, 255));
        mbnCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Mã BN--" }));

        javax.swing.GroupLayout panelBorder7Layout = new javax.swing.GroupLayout(panelBorder7);
        panelBorder7.setLayout(panelBorder7Layout);
        panelBorder7Layout.setHorizontalGroup(
            panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addGroup(panelBorder7Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        panelBorder7Layout.setVerticalGroup(
            panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(spTable)
                .addGap(20, 20, 20))
        );

        updateBtn.setBackground(new java.awt.Color(20, 20, 84));
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        xuatBtn.setForeground(new java.awt.Color(51, 51, 255));
        xuatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/up-arrow.png"))); // NOI18N
        xuatBtn.setText("Xuất Excel");
        xuatBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        xuatBtn.setShadowColor(new java.awt.Color(0, 51, 255));

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Cân nặng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Nhóm máu");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Nhiệt độ");

        cnEdt.setBackground(new java.awt.Color(248, 248, 255));
        cnEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        nmEdt.setBackground(new java.awt.Color(248, 248, 255));
        nmEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        ndEdt.setBackground(new java.awt.Color(248, 248, 255));
        ndEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Mạch");

        machEdt.setBackground(new java.awt.Color(248, 248, 255));
        machEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Huyết áp");

        haEdt.setBackground(new java.awt.Color(248, 248, 255));
        haEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Nhịp Thở");

        ntEdt.setBackground(new java.awt.Color(248, 248, 255));
        ntEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Lý Do Khám");

        ldkEdt.setShadowColor(new java.awt.Color(153, 153, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Tình Trạng Hiện Tại");

        tthtEdt.setShadowColor(new java.awt.Color(153, 153, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Chuẩn đoán sơ bộ");

        cdEdt.setShadowColor(new java.awt.Color(153, 153, 255));

        hdtEdt.setShadowColor(new java.awt.Color(153, 153, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Hướng Điều Trị");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Chuyên Khoa");

        ckCb.setBackground(new java.awt.Color(204, 204, 255));
        ckCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Chuyên Khoa--" }));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Mã BS");

        mbsCb.setBackground(new java.awt.Color(204, 204, 255));
        mbsCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Mã BS--" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Ngày Khám");

        nkEdt.setShadowColor(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cnEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(machEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jLabel3))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(haEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(nmEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(33, 33, 33)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ntEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ndEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8)
                        .addComponent(tthtEdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addComponent(cdEdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addComponent(hdtEdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addComponent(ckCb, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addGap(95, 95, 95))
                        .addComponent(ldkEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addComponent(mbsCb, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nkEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2))))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nmEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ndEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(machEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(haEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ntEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ldkEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tthtEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cdEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hdtEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mbsCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nkEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(xuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(xuatBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.swing.TextFeild cdEdt;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion ckCb;
    private com.noitru.swing.TextFeild cnEdt;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.noitru.swing.TextFeild haEdt;
    private com.noitru.swing.TextFeild hdtEdt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.noitru.swing.TextFeild ldkEdt;
    private com.noitru.swing.TextFeild machEdt;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion mbnCb;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion mbsCb;
    private com.noitru.swing.TextFeild ndEdt;
    private com.noitru.swing.TextFeild nkEdt;
    private com.noitru.swing.TextFeild nmEdt;
    private com.noitru.swing.TextFeild ntEdt;
    private com.noitru.swing.PanelBorder panelBorder1;
    private com.noitru.swing.PanelBorder panelBorder7;
    private javax.swing.JScrollPane spTable;
    private com.noitru.swing.Table table;
    private com.noitru.swing.TextFeild tthtEdt;
    private com.noitru.swing.Button updateBtn;
    private com.noitru.swing.Button xuatBtn;
    // End of variables declaration//GEN-END:variables
}
