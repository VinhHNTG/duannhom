/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.NguyenLieu;
import service.DBconnect;

/**
 *
 * @author ACER
 */
public class NguyenLieuDAO {
    public List<NguyenLieu> getAll() {
        List<NguyenLieu> listPB = new ArrayList<>();
        String sql = "SELECT * FROM NguyenLieu";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int MaNL = rs.getInt(1);
                String TenNL = rs.getString(2);
                String gianhap = rs.getString(3);
                int soluong = rs.getInt(4);
                int maSP = rs.getInt(5);

                NguyenLieu nl = new NguyenLieu(MaNL, TenNL, gianhap, soluong, maSP);
                listPB.add(nl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPB;
    }

    public Object[] getRow(NguyenLieu nl) {
        int MaNL = nl.getMaNL();
        String TenNL = nl.getTenNL();
        int soluong = nl.getSoluong();
        String gianhap = nl.getGiaNhap();
        int maSP = nl.getMaSP();

        return new Object[]{MaNL, TenNL, soluong, gianhap, maSP};
    }
    
    public int addNL(NguyenLieu nl) {
        String sql = "insert into NguyenLieu(MaNL, TenNL, Soluong, GiaNhap, MaSP) values (?, ?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, nl.getMaNL());
            pstm.setString(2, nl.getTenNL());
            pstm.setInt(3, nl.getSoluong());
            pstm.setString(4, nl.getGiaNhap());
            pstm.setInt(5, nl.getMaSP());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int editNL(NguyenLieu nl) {
    String sql = "UPDATE NguyenLieu SET MaNL = ?, TenNL = ?, Soluong = ?, GiaNhap = ?, MaSP = ?, WHERE MaNV = ?";
    try {
        Connection con = DBconnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, nl.getMaNL());
            pstm.setString(2, nl.getTenNL());
            pstm.setInt(3, nl.getSoluong());
            pstm.setString(4, nl.getGiaNhap());
            pstm.setInt(5, nl.getMaSP());

        if (pstm.executeUpdate() > 0) {
            return 1;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
    
    public int deleteNL(NguyenLieu nl) {
        String sql = "delete from NguyenLieu where MaNL = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, nl.getMaNL());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
