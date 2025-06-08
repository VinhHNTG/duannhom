/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class NhanVien {
    private int maNV;
    private String tenNV;
    private String namSinh;
    private String sdt;
    private String chucVu;

    public NhanVien() {
    }

    public NhanVien(int maNV, String tenNV, String namSinh, String sdt, String chucVu) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.namSinh = namSinh;
        this.sdt = sdt;
        this.chucVu = chucVu;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
