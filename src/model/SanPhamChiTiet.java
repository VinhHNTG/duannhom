/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class SanPhamChiTiet {
    private int maSPCT;
    private int MaSP;
    private String size;
    private int MaTopping;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int maSPCT, int MaSP, String size, int MaTopping) {
        this.maSPCT = maSPCT;
        this.MaSP = MaSP;
        this.size = size;
        this.MaTopping = MaTopping;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getMaTopping() {
        return MaTopping;
    }

    public void setMaTopping(int MaTopping) {
        this.MaTopping = MaTopping;
    }
    
}
