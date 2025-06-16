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
import model.SanPham;
import service.DBconnect;

/**
 *
 * @author ADMIN
 */
public class SanPhamDao {
    public Object[] getRow(SanPham sp) {
        int maSP = sp.getMaSP();
        int soluong = sp.getSoluong();
        String ten = sp.getTenSP();
        Date ngaydathang = sp.getNgaydathang();
        int giaban = sp.getGiaban();
        Object[] row = new Object[]{maSP,soluong,ten,ngaydathang,giaban};
        return row;
    }
    
     public List<SanPham> getALL() {
        List<SanPham> listSP = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maSP = rs.getInt(1);
                int soluong = rs.getInt(2);
                String ten = rs.getString(3);
                Date ngaydathang = rs.getDate(4);
                int giaBan = rs.getInt(5);
                SanPham sp = new SanPham(maSP, soluong, ten, giaBan, ngaydathang);
                listSP.add(sp);
            }
        } catch (Exception e) {
        }
        return listSP;
    }
     
      public boolean getadd(SanPham sp) {
        String sql = "INSERT INTO SanPham(Ma, Soluong, TenSP, NgayDatHang, GiaBan) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, sp.getMaSP());
            stm.setInt(2, sp.getSoluong());
            stm.setString(3, sp.getTenSP());
            stm.setDate(4, sp.getNgaydathang());
            stm.setInt(5, sp.getGiaban());
            if (stm.executeUpdate()>0) {
                System.out.println("Them thanh cong");
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean UpdateSP(SanPham sp) {
        String sql = "UPDATE SanPham SET TenSP = ?, Soluong = ?, NgayDatHang = ?, GiaBan = ?, where MaSP = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, sp.getSoluong());
            pstm.setString(2, sp.getTenSP());
            pstm.setDate(3, sp.getNgaydathang());
            pstm.setInt(4, sp.getGiaban());
            if (pstm.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean deleteHD(SanPham sp) {
        String sql = "delete from SanPham where MaSP = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, sp.getMaSP());

            if (pstm.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
