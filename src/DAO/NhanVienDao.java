/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import model.SanPham;
import service.DBconnect;

/**
 *
 * @author ADMIN
 */
public class NhanVienDao {
    public Object getRow(NhanVien nv) {
        int maNV = nv.getMaNV();
        String tenNV=nv.getTenNV();
        String namSinh = nv.getNamSinh();
        String sdt = nv.getSdt();
        String chucVu = nv.getChucVu();
        Object[] row = new Object[]{maNV,tenNV,namSinh,sdt,chucVu};
        return row;
    }
    
     public List<NhanVien> getALL() {
        List<NhanVien> listNV = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maNV = rs.getInt(1);
                String tenNV = rs.getString(2);
                String namSinh = rs.getString(3);
                String sdt = rs.getString(4);
                String chucVu = rs.getString(5);
                NhanVien nv = new NhanVien(maNV,tenNV,namSinh,sdt,chucVu);
                listNV.add(nv);
            }
        } catch (Exception e) {
        }
        return listNV;
    }
    
     public boolean getadd(NhanVien nv) {
        String sql = "INSERT INTO SanPham(MaNV, TenNV, Tuoi, SDT, ChucVu) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, nv.getMaNV());
            stm.setString(2, nv.getTenNV());
            stm.setString(3, nv.getNamSinh());
            stm.setString(4, nv.getSdt());
            stm.setString(5, nv.getChucVu());
            if (stm.executeUpdate()>0) {
                System.out.println("Them thanh cong");
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean deleteHD(NhanVien nv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}