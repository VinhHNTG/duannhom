/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private int maHD;
    private int maNV;
    private Date ngayDat;
    private double tongTien;
    private int maKH;

    public HoaDon(int maHoaDon, int maNV1, double giatien, Date ngaydat, int maKH1) {
    }

    public HoaDon(int maHD, int maNV, Date ngayDat, double tongTien, int maKH) {
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

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
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
