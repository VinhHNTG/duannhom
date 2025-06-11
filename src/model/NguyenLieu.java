/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class NguyenLieu {
    private int MaNL;
    private String TenNL;
    private String GiaNhap;
    private int Soluong;
    private int MaSP;

    public NguyenLieu(int maNL, String tenNL, int soluong, String gianhap, int maSP) {
    }

    public NguyenLieu(int MaNL, String TenNL, String GiaNhap, int Soluong, int MaSP) {
        this.MaNL = MaNL;
        this.TenNL = TenNL;
        this.GiaNhap = GiaNhap;
        this.Soluong = Soluong;
        this.MaSP = MaSP;
    }

    public int getMaNL() {
        return MaNL;
    }

    public void setMaNL(int MaNL) {
        this.MaNL = MaNL;
    }

    public String getTenNL() {
        return TenNL;
    }

    public void setTenNL(String TenNL) {
        this.TenNL = TenNL;
    }

    public String getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(String GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }
    
}
