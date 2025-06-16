/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.*;
import java.sql.*;
import model.*;
import service.DBconnect;

/**
 *
 * @author ACER
 */
public class KhachHangDAO {

    public List<KhachHang> getAll() {
        List<KhachHang> listPB = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maKH = rs.getInt(1);
                String tenKH = rs.getString(2);
                String sdt = rs.getString(3);
                String diachi = rs.getString(4);
                KhachHang kh = new KhachHang(maKH, tenKH, sdt, diachi);
                listPB.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPB;
    }

    public Object[] getRow(KhachHang kh) {
        int maKH = kh.getMaKH();
        String tenKH = kh.getTenKH();
        String sdt = kh.getSdt();
        String diachi = kh.getDiaChi();

        Object[] obj = new Object[]{maKH, tenKH, sdt, diachi};
        return obj;
    }

    public int ThemKH(KhachHang kh) {
        String sql = "INSERT INTO KhachHang  VALUES (?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, kh.getMaKH());
            pstm.setString(2, kh.getTenKH());
            pstm.setString(3, kh.getSdt());
            pstm.setString(4, kh.getDiaChi());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

public int SuaKH(KhachHang kh, int TheoMa) {
    String sql = "UPDATE KhachHang SET MaKH = ?, TenKH = ?, SDT = ?, DiaChi = ? WHERE MaKH = ?";
    try {
        Connection con = DBconnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setInt(1, kh.getMaKH());
        pstm.setString(2, kh.getTenKH());
        pstm.setString(3, kh.getSdt());
        pstm.setString(4, kh.getDiaChi());
        pstm.setInt(5, TheoMa); // Quan trọng: KHÔNG bỏ qua!

        return pstm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

    public int DeleteKH(int TheoMa) {
        String sql = "DELETE FROM KhachHang WHERE MaKH = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, TheoMa);

            if (pstm.executeUpdate() > 0) {
                System.out.println("Xoa. Connect");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int SuaKH(KhachHang kh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int DeleteKH(KhachHang kh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
