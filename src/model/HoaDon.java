/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private int maHD;
    private int maNV;
    private String ngayDat;
    private double tongTien;
    private int maKH;

    public HoaDon() {
    }

    public HoaDon(int maHD, int maNV, String ngayDat, double tongTien, int maKH) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.maKH = maKH;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

}
