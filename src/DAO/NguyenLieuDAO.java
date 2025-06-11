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

/**
 *
 * @author ACER
 */
public class NguyenLieuDAO {
    public List<QLNL> getAll() {
        List<QLNL> listPB = new ArrayList<>();
        String sql = "SELECT * FROM NguyenLieu";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String maNL = rs.getString(1);
                String tenNL = rs.getString(2);
                Date ngaynhap = rs.getDate(3);
                int gianhap = rs.getInt(4);
                String maSP = rs.getString(5);

                NguyenLieu nl = new NguyenLieu(maNL, tenNL, ngaynhap, gianhap, maSP);
                listPB.add(nl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPB;
    }

    public Object[] getRow(NguyenLieu nl) {
        String maNL = nl.getMaNL();
        String tenNL = nl.getTenNL();
        Date ngaynhap = nl.getNgayNhap();
        int gianhap = nl.getGiaNhap();
        String maSP = nl.getMaSP();

        return new Object[]{maNL, tenNL, ngaynhap, gianhap, maSP};
    }
    
    public int addHD(NguyenLieu nl) {
        String sql = "insert into NguyenLieu(MaNL, TenNL, NgayNhap, GiaNhap, MaSP) values (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, nl.getMaNL());
            pstm.setString(2, nl.getTenNL());
          //pstm.setDate(3, nl.getNgayNhap());
            pstm.setInt(4, nl.getGiaNhap());
            pstm.setString(5, nl.getMaSP());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int editHD(NguyenLieu nl, String maCu) {
    String sql = "UPDATE NguyenLieu SET MaNL = ?, TenNL = ?, NgayNhap = ?, GiaNhap = ?, MaSP = ?, WHERE MaNV = ?";
    try {
        Connection con = DBConnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, nl.getMaNL());
        pstm.setString(2, nl.getTenNL());
        //pstm.setDate(3, nl.getNgayNhap());
        pstm.setInt(4, nl.getGiaNhap());
        pstm.setString(5, nl.getMaSP());
        pstm.setString(5, maCu);

        if (pstm.executeUpdate() > 0) {
            return 1;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
    
    public int deleteHD(NguyenLieu nl, int ma) {
        String sql = "delete from NguyenLieu where MaNL = ?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, nl.getMaNL());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
