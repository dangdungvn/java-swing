package com.noitru.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {

    private static ReportManager instance;
    private JasperReport reportPay;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    public ReportManager() {
    }

    public void compileReport() throws JRException {
        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("/com/noitru/print/HoaDon.jrxml"));
    }

    public void printReportPayment(ParameterReportHoaDon data) throws JRException {
        Map para = new HashMap();
        para.put("tenBenhNhan", data.getTenBenhNhan());
        para.put("tongTien", data.getTongTien());
        para.put("loaiPhong", data.getLoaiPhong());
        para.put("soNgayO", data.getSoNgayO());
        para.put("tienPhong", data.getTienPhong());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);
        view(print);
    }

    public void view(JasperPrint print) {
        JasperViewer.viewReport(print, false);
    }
}
