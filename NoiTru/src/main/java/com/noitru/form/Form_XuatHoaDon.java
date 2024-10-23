package com.noitru.form;

import com.noitru.BenhNhan;
import com.noitru.DieuTri;
import com.noitru.GiuongBenh;
import com.noitru.HoaDon;
import com.noitru.model.Model_BenhNhan;
import com.noitru.model.Model_DieuTri;
import com.noitru.model.Model_GiuongBenh;
import com.noitru.model.Model_HoaDon;
import com.noitru.print.FieldReportHoaDon;
import com.noitru.print.ParameterReportHoaDon;
import com.noitru.print.ReportManager;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Form_XuatHoaDon extends javax.swing.JPanel {

    private long soNgayO;
    private long tongTienPhong;

    public Form_XuatHoaDon() {
        initComponents();
        xuatCB();
        xuatHoaDon();
    }

    private void xuatHoaDon() {
        xuatHoaDonBtn.addActionListener((ActionEvent e) -> {
            String MaBN = mbnCb.getSelectedItem().toString();
//            BenhNhan.suaBenhNhanTheoTinhTrang(MaBN, "Đã Xuất Viện");
//            BenhNhan.suaBenhNhanTheoNgayRaVien(MaBN, LocalDate.now().toString());
            String Ten = null;
            int TongTienThuoc = 0;
            String LoaiPhong = null;
            List<Model_HoaDon> hoaDonList = HoaDon.timKiemTheoMaBN(MaBN);
            List<Model_BenhNhan> benhNhanList = BenhNhan.timKiemTheoMaBN(MaBN);
            List<Model_GiuongBenh> tienPhongList = GiuongBenh.timKiemTheoMaBN(MaBN);
            for (Model_GiuongBenh model_GiuongBenh : tienPhongList) {
                LoaiPhong = model_GiuongBenh.getLoaiPhong();
            }
            for (Model_BenhNhan model_BenhNhan : benhNhanList) {
                Ten = model_BenhNhan.getTenBN();
            }
            try {
                ReportManager.getInstance().compileReport();
                List<FieldReportHoaDon> field = new ArrayList<>();
                for (Model_HoaDon hd : hoaDonList) {
                    field.add(new FieldReportHoaDon(hd.getThuoc(), hd.getSoLuongThuoc(), hd.getGiaTien(), hd.getThanhTien()));
                    TongTienThuoc += hd.getThanhTien();
                }
//                field.add(new FieldReportHoaDon(LoaiPhong, soNgayO, tongTienPhong));
                ParameterReportHoaDon dataprint = new ParameterReportHoaDon(Ten, String.valueOf(tongTienPhong + TongTienThuoc),
                         LoaiPhong, String.valueOf(soNgayO), String.valueOf(tongTienPhong), field);
                ReportManager.getInstance().printReportPayment(dataprint);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    private void xuatCB() {
        List<Model_BenhNhan> benhNhanList = BenhNhan.timKiemBNChuaXuatVien();
        for (Model_BenhNhan mbn : benhNhanList) {
            mbnCb.addItem(mbn.getMaBN());
        }
        mbnCb.addActionListener((ActionEvent e) -> {
            String text = mbnCb.getSelectedItem().toString();
            if (text != null && !text.equals("--Chọn Mã BN--")) {
                for (Model_BenhNhan nhan : benhNhanList) {
                    tbnEdt.setText(nhan.getTenBN());
                    dcEdt.setText(nhan.getDiaChi());
                    gtEdt.setText(nhan.getGioiTinh());
                    cccdEdt.setText(nhan.getCCCD());
                }
                int tienThuoc = 0;
                List<Model_DieuTri> tienThuocList = DieuTri.timKiemTheoMaBN(text);
                for (Model_DieuTri mdt : tienThuocList) {
                    tienThuoc = mdt.getThanhTien();
                    ttEdt.setText(String.valueOf(mdt.getThanhTien()));
                }
                List<Model_GiuongBenh> tienPhongList = GiuongBenh.timKiemTheoMaBN(text);
                List<Model_BenhNhan> ttkmList = BenhNhan.timKiemTheoMaBN(text);

                String ngayHienTai = LocalDate.now().toString();
                String ngayDKKham = null;
                for (Model_BenhNhan thongTinKhamBenh : ttkmList) {
                    ngayDKKham = thongTinKhamBenh.getNgayDKKham();
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate nHienTai = LocalDate.parse(ngayHienTai, formatter);
                LocalDate nDKKham = LocalDate.parse(ngayDKKham, formatter);
                soNgayO = ChronoUnit.DAYS.between(nDKKham, nHienTai);
                for (Model_GiuongBenh tienPhong : tienPhongList) {
                    tongTienPhong = switch (tienPhong.getLoaiPhong()) {
                        case "Giường bệnh thường" ->
                            soNgayO * 5000;
                        case "Giường bệnh VIP" ->
                            soNgayO * 10000;
                        default ->
                            soNgayO * 15000;
                    };
                }
                tpEdt.setText(String.valueOf(tongTienPhong));
                tongEdt.setText(String.valueOf(tongTienPhong + tienThuoc));
            } else {
                tbnEdt.setText("");
                dcEdt.setText("");
                gtEdt.setText("");
                cccdEdt.setText("");
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new com.noitru.swing.jcombosuggestion.ComboSuggestionUI();
        xuatHoaDonBtn = new com.noitru.swing.Button();
        panelBorder1 = new com.noitru.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mbnCb = new com.noitru.swing.jcombosuggestion.ComboBoxSuggestion();
        jLabel3 = new javax.swing.JLabel();
        tbnEdt = new com.noitru.swing.TextFeild();
        jLabel4 = new javax.swing.JLabel();
        dcEdt = new com.noitru.swing.TextFeild();
        jLabel5 = new javax.swing.JLabel();
        cccdEdt = new com.noitru.swing.TextFeild();
        jLabel6 = new javax.swing.JLabel();
        gtEdt = new com.noitru.swing.TextFeild();
        panelBorder2 = new com.noitru.swing.PanelBorder();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ttEdt = new com.noitru.swing.TextFeild();
        jLabel11 = new javax.swing.JLabel();
        tpEdt = new com.noitru.swing.TextFeild();
        tongEdt = new com.noitru.swing.TextFeild();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        xuatHoaDonBtn.setBackground(new java.awt.Color(20, 20, 94));
        xuatHoaDonBtn.setForeground(new java.awt.Color(255, 255, 255));
        xuatHoaDonBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/noitru/icon/finance.png"))); // NOI18N
        xuatHoaDonBtn.setText("Xuất Hóa Đơn");
        xuatHoaDonBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("HỒ SƠ BỆNH NHÂN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Mã BN");

        mbnCb.setBackground(new java.awt.Color(248, 248, 255));
        mbnCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Chọn Mã BN--" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Tên Bệnh Nhân");

        tbnEdt.setBackground(new java.awt.Color(248, 248, 255));
        tbnEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Địa Chỉ");

        dcEdt.setBackground(new java.awt.Color(248, 248, 255));
        dcEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("CCCD");

        cccdEdt.setBackground(new java.awt.Color(248, 248, 255));
        cccdEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Giới Tính");

        gtEdt.setBackground(new java.awt.Color(248, 248, 255));
        gtEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tbnEdt, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(cccdEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(gtEdt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(82, 82, 82)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mbnCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbnEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cccdEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gtEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBorder2.setBackground(new java.awt.Color(165, 165, 246));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("HÓA ĐƠN XUẤT VIỆN");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Tiền Thuốc");

        ttEdt.setBackground(new java.awt.Color(248, 248, 255));
        ttEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Tiền Phòng");

        tpEdt.setBackground(new java.awt.Color(248, 248, 255));
        tpEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        tongEdt.setBackground(new java.awt.Color(248, 248, 255));
        tongEdt.setShadowColor(new java.awt.Color(51, 51, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Tổng Chi Phí");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9)
                        .addComponent(ttEdt, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(tpEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(tongEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(173, 307, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(81, 81, 81)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(ttEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(tpEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(tongEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(xuatHoaDonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(80, 80, 80)
                .addComponent(xuatHoaDonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.swing.TextFeild cccdEdt;
    private com.noitru.swing.jcombosuggestion.ComboSuggestionUI comboSuggestionUI1;
    private com.noitru.swing.TextFeild dcEdt;
    private com.noitru.swing.TextFeild gtEdt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private com.noitru.swing.jcombosuggestion.ComboBoxSuggestion mbnCb;
    private com.noitru.swing.PanelBorder panelBorder1;
    private com.noitru.swing.PanelBorder panelBorder2;
    private com.noitru.swing.TextFeild tbnEdt;
    private com.noitru.swing.TextFeild tongEdt;
    private com.noitru.swing.TextFeild tpEdt;
    private com.noitru.swing.TextFeild ttEdt;
    private com.noitru.swing.Button xuatHoaDonBtn;
    // End of variables declaration//GEN-END:variables
}
