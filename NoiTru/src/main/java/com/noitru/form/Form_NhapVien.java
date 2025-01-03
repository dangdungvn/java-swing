package com.noitru.form;

import com.noitru.BacSi;
import com.noitru.BenhNhan;
import com.noitru.ConnectDB;
import com.noitru.GiuongBenh;
import com.noitru.ThongTinKhamBenh;
import com.noitru.model.Model_BacSi;
import com.noitru.model.Model_BenhNhan;
import com.noitru.model.Model_GiuongBenh;
import com.noitru.model.Model_ThongTinKhamBenh;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class Form_NhapVien extends javax.swing.JPanel {

    Map<String, String[]> phanphongMap = new HashMap<>();

    public Form_NhapVien() {
        initComponents();
        spTable4.setVerticalScrollBar(new ScrollBar());
        spTable4.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable4.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable4.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        loadGiuongBenh();
        loadDBCB();
        themData();
        suaData();
        xoaData();
        tableClick();
        timKiemData();
        lpEdt.setEnabled(false);
        ttgEdt.setEnabled(false);
        themBtn.setEnabled(true);
        xuatData();
    }

    private void xuatData() {
        xuatBtn.addActionListener((ActionEvent e) -> {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("dieutri");
                // register the columns you wish to track and compute the column width
                CreationHelper createHelper = workbook.getCreationHelper();
                XSSFRow row = null;
                Cell cell = null;
                row = spreadsheet.createRow((short) 2);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("DANH SÁCH BỆNH NHÂN ĐANG ĐIỀU TRỊ");

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
                cell.setCellValue("Chuyên Khoa");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Loại Phòng");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Số Phòng");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("Số Giường");

                cell = row.createCell(5, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("MaBN");

                cell = row.createCell(6, CellType.STRING);
                cell.setCellStyle(cellStyle_Head);
                cell.setCellValue("MaBS");

                //Kết nối DB
                Connection con = ConnectDB.connect();
                String sql = "Select * From giuongbenh";
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
                    cell.setCellValue(rs.getString("ChuyenKhoa"));

                    cell = row.createCell(2);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("LoaiPhong"));
                    cell = row.createCell(3);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("SoPhong"));

                    cell = row.createCell(4);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("SoGiuong"));

                    cell = row.createCell(5);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("MaBN"));

                    cell = row.createCell(6);
                    cell.setCellStyle(cellStyle_data);
                    cell.setCellValue(rs.getString("MaBS"));
                    i++;
                }
                for (int col = 0; col < tongsocot; col++) {
                    spreadsheet.autoSizeColumn(col);
                }

                File f = new File("D:\\DanhsachBenhNhanDangDieuTri.xlsx");
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
        mbnCb.addActionListener((ActionEvent e) -> {
            String selectedMaBN = (String) mbnCb.getSelectedItem();
            chuyenkhoaCb.removeAllItems();
            chuyenkhoaCb.addItem("--Chọn chuyên khoa--");
            if (selectedMaBN != null && !selectedMaBN.equals("--Chọn Bệnh Nhân--")) {
                List<Model_ThongTinKhamBenh> data = ThongTinKhamBenh.timKiemTheoMaBN(selectedMaBN);
                for (Model_ThongTinKhamBenh string : data) {
                    chuyenkhoaCb.addItem(string.getChuyenKhoa());
                }
            }
        });
        chuyenkhoaCb.addActionListener((ActionEvent e) -> {
            String selectedChuyenKhoa = (String) chuyenkhoaCb.getSelectedItem();
            mbsCb.removeAllItems();
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
                    if (giuongBenh.getSoPhong().equals(spCb.getSelectedItem().toString())
                            && giuongBenh.getSoGiuong().equals(selectedBed)) {
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

    private void xoaData() {
        xoaBtn.addActionListener(e -> {
            String MaBN = mbnCb.getSelectedItem().toString();
            if (MaBN.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bác sĩ để xóa!");
                return;
            }
            GiuongBenh.xoaGiuongBenh(MaBN);
            loadGiuongBenh();
            lpEdt.setText("...");
            ttgEdt.setText("...");
            chuyenkhoaCb.setSelectedIndex(0);
            spCb.setSelectedIndex(0);
            sgCb.setSelectedIndex(0);
            mbnCb.setSelectedIndex(0);
            mbsCb.setSelectedIndex(0);
        });
    }

    private void timKiemData() {
        timKiemEdt.addOption(new SearchOption("Mã Bác Sĩ",
                new ImageIcon(getClass().getResource("/com/noitru/icon/credit-card.png"))));
        timKiemEdt.addOption(new SearchOption("Mã Bệnh Nhân",
                new ImageIcon(getClass().getResource("/com/noitru/icon/man-avatar.png"))));
        timKiemEdt.addEventOptionSelected((SearchOption option, int index) -> {
            timKiemEdt.setHint("Tìm Kiếm " + option.getName() + "...");
        });
        timKiemEdt.setSelectedIndex(0);
        timKiemEdt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (timKiemEdt.isSelected()) {
                    int option = timKiemEdt.getSelectedIndex();
                    String text = timKiemEdt.getText().trim();
                    switch (option) {
                        case 0 -> {
                            ((DefaultTableModel) gbTable.getModel()).setRowCount(0);
                            SwingUtilities.invokeLater(() -> {
                                List<Model_GiuongBenh> giuongBenhList = GiuongBenh.timKiemTheoMaBS(text);
                                for (Model_GiuongBenh giuongBenh : giuongBenhList) {
                                    Object[] row = {giuongBenh.getMaBN(), giuongBenh.getChuyenKhoa(), giuongBenh.getMaBS(),
                                        giuongBenh.getSoPhong(), giuongBenh.getLoaiPhong(), giuongBenh.getSoGiuong()};
                                    gbTable.addRow(row);
                                }
                            });
                        }
                        default -> {
                            ((DefaultTableModel) gbTable.getModel()).setRowCount(0);
                            SwingUtilities.invokeLater(() -> {
                                List<Model_GiuongBenh> giuongBenhList = GiuongBenh.timKiemTheoMaBN(text);
                                for (Model_GiuongBenh giuongBenh : giuongBenhList) {
                                    Object[] row = {giuongBenh.getMaBN(), giuongBenh.getChuyenKhoa(), giuongBenh.getMaBS(),
                                        giuongBenh.getSoPhong(), giuongBenh.getLoaiPhong(), giuongBenh.getSoGiuong()};
                                    gbTable.addRow(row);
                                }
                            });
                        }
                    }
                }
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
                if (ChuyenKhoa.isEmpty() || LoaiPhong.isEmpty() || SoPhong.isEmpty() || SoGiuong.isEmpty()
                        || MaBN.isEmpty() || MaBS.isEmpty()) {
                    JOptionPane.showMessageDialog(Form_NhapVien.this, "Vui Lòng Nhập Thông Tin");
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
                    BenhNhan.suaBenhNhanTheoTinhTrang(MaBN, "Đã Nhập Viện");
                }
            } catch (HeadlessException ex) {
            }
        });
    }

    private void suaData() {
        suaBtn.addActionListener(e -> {
            try {
                String ChuyenKhoa = chuyenkhoaCb.getSelectedItem().toString();
                String LoaiPhong = lpEdt.getText().trim();
                String SoPhong = spCb.getSelectedItem().toString();
                String SoGiuong = sgCb.getSelectedItem().toString();
                String MaBN = mbnCb.getSelectedItem().toString();
                String MaBS = mbsCb.getSelectedItem().toString();
                if (ChuyenKhoa.isEmpty() || LoaiPhong.isEmpty() || SoPhong.isEmpty() || SoGiuong.isEmpty()
                        || MaBN.isEmpty() || MaBS.isEmpty()) {
                    JOptionPane.showMessageDialog(Form_NhapVien.this, "Vui Lòng Nhập Thông Tin");
                } else {
                    GiuongBenh.suaGiuongBenh(ChuyenKhoa, LoaiPhong, SoPhong, SoGiuong, MaBN, MaBS);
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

    private void tableClick() {
        gbTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = gbTable.getSelectedRow();
                if (selectedRow != -1) {
                    mbnCb.setSelectedItem(gbTable.getValueAt(selectedRow, 0).toString());
                    chuyenkhoaCb.setSelectedItem(gbTable.getValueAt(selectedRow, 1).toString());
                    mbsCb.setSelectedItem(gbTable.getValueAt(selectedRow, 2).toString());
                    spCb.setSelectedItem(gbTable.getValueAt(selectedRow, 3).toString());
                    lpEdt.setText((String) gbTable.getValueAt(selectedRow, 4));
                    sgCb.setSelectedItem(gbTable.getValueAt(selectedRow, 5).toString());
                }
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.noitru.swing.PanelBorder();
        jLabel9 = new javax.swing.JLabel();
        sgCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        jLabel10 = new javax.swing.JLabel();
        chuyenkhoaCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        spCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        mbnCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        mbsCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        lpEdt = new com.noitru.swing.TextFeild();
        ttgEdt = new com.noitru.swing.TextFeild();
        panelBorder2 = new com.noitru.swing.PanelBorder();
        xoaBtn = new com.noitru.swing.Button();
        themBtn = new com.noitru.swing.Button();
        suaBtn = new com.noitru.swing.Button();
        xuatBtn = new com.noitru.swing.Button();
        panelBorder5 = new com.noitru.swing.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable4 = new javax.swing.JScrollPane();
        gbTable = new com.noitru.swing.Table();
        timKiemEdt = new com.noitru.swing.search.TextFieldSearchOption();

        setBackground(new java.awt.Color(245, 245, 255));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 255));
        jLabel9.setText("Số Giường");

        sgCb.setBackground(new java.awt.Color(248, 248, 255));
        sgCb.setForeground(new java.awt.Color(80, 80, 80));
        sgCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn số giường--" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 255));
        jLabel10.setText("Trạng Thái Giường");

        chuyenkhoaCb.setBackground(new java.awt.Color(248, 248, 255));
        chuyenkhoaCb.setForeground(new java.awt.Color(80, 80, 80));
        chuyenkhoaCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn chuyên khoa--" }));

        spCb.setBackground(new java.awt.Color(248, 248, 255));
        spCb.setForeground(new java.awt.Color(80, 80, 80));
        spCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn số phòng--", "101", "102", "103", "104", "201", "202", "203", "204", "301", "302", "303", "304" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 255));
        jLabel8.setText("Loại Phòng");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Mã BN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setText("Chuyên Khoa");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 255));
        jLabel3.setText("Mã BS Phụ Trách");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setText("Số Phòng");

        mbnCb.setBackground(new java.awt.Color(248, 248, 255));
        mbnCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Bệnh Nhân--" }));

        mbsCb.setBackground(new java.awt.Color(248, 248, 255));
        mbsCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Bác Sĩ--" }));

        lpEdt.setBackground(new java.awt.Color(248, 248, 255));
        lpEdt.setSelectionColor(new java.awt.Color(153, 153, 255));
        lpEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        ttgEdt.setBackground(new java.awt.Color(248, 248, 255));
        ttgEdt.setSelectionColor(new java.awt.Color(153, 153, 255));
        ttgEdt.setShadowColor(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
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
                            .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(sgCb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(mbsCb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lpEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ttgEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chuyenkhoaCb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mbsCb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spCb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lpEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sgCb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ttgEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBorder2.setBackground(new java.awt.Color(222, 231, 255));

        xoaBtn.setBackground(new java.awt.Color(146, 146, 248));
        xoaBtn.setForeground(new java.awt.Color(255, 255, 255));
        xoaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/delete.png"))); // NOI18N
        xoaBtn.setText("Xóa");
        xoaBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        themBtn.setBackground(new java.awt.Color(51, 51, 114));
        themBtn.setForeground(new java.awt.Color(255, 255, 255));
        themBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/plus.png"))); // NOI18N
        themBtn.setText("Thêm");
        themBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        suaBtn.setForeground(new java.awt.Color(102, 102, 255));
        suaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/tools.png"))); // NOI18N
        suaBtn.setText("Sửa");
        suaBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        xuatBtn.setBackground(new java.awt.Color(148, 148, 242));
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
                .addGap(20, 20, 20)
                .addComponent(suaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(xoaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(xuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xoaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelBorder5.setBackground(new java.awt.Color(146, 146, 248));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Danh sách bệnh nhân");

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

        timKiemEdt.setBackground(new java.awt.Color(255, 255, 255));
        timKiemEdt.setForeground(new java.awt.Color(80, 80, 80));
        timKiemEdt.setColorOverlay2(new java.awt.Color(153, 153, 255));

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
                        .addComponent(timKiemEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(timKiemEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
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
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion sgCb;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion spCb;
    private javax.swing.JScrollPane spTable4;
    private com.noitru.swing.Button suaBtn;
    private com.noitru.swing.Button themBtn;
    private com.noitru.swing.search.TextFieldSearchOption timKiemEdt;
    private com.noitru.swing.TextFeild ttgEdt;
    private com.noitru.swing.Button xoaBtn;
    private com.noitru.swing.Button xuatBtn;
    // End of variables declaration//GEN-END:variables
}
