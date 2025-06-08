/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDon {
    private int maCTHD;
    private int maHD;
    private int maSP;
    private int sl;
    private double giaBan;
    private double donGia;
    private String trangThai;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int maCTHD, int maHD, int maSP, int sl, double giaBan, double donGia, String trangThai) {
        this.maCTHD = maCTHD;
        this.maHD = maHD;
        this.maSP = maSP;
        this.sl = sl;
        this.giaBan = giaBan;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public int getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        this.maCTHD = maCTHD;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
