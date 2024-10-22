package com.noitru.print;

public class FieldReportHoaDon {

    String name;
    int qty;
    double price;
    double total;
    String loaiphong;
    long songayo;
    long thanhtien;

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    public long getSongayo() {
        return songayo;
    }

    public void setSongayo(int songayo) {
        this.songayo = songayo;
    }

    public long getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    public FieldReportHoaDon() {
    }

    public FieldReportHoaDon(String name, int qty, double price, double total, String loaiphong, long songayo, long thanhtien) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.total = total;
        this.loaiphong = loaiphong;
        this.songayo = songayo;
        this.thanhtien = thanhtien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
