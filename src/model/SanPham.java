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
public class SanPham {
    private int maSP;
    private String loai;
    private String tenSP;
    private int giaban;
    private Date ngaydathang;

    public SanPham(int maSP1, String loai1, String ten, Date ngaydathang1, int giaBan) {
    }

    

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public Date getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(Date ngayban) {
        this.ngaydathang = ngayban;
    }

    public SanPham(int maSP, String loai, String tenSP, int giaban, Date ngaydathang) {
        this.maSP = maSP;
        this.loai = loai;
        this.tenSP = tenSP;
        this.giaban = giaban;
        this.ngaydathang = ngaydathang;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

}
