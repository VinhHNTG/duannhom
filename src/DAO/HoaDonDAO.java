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
import java.util.List;
import service.DBconnect;
import model.HoaDon;
import java.sql.*;

/**
 *
 * @author ACER
 */
public class HoaDonDAO {
    public List<HoaDon> getAll() {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maHoaDon = rs.getInt(1);
                int maNV = rs.getInt(2);
                Date ngaydat = rs.getDate(3);
                Double giaTien = rs.getDouble(4);
                int maKH = rs.getInt(5);

                HoaDon hd = new HoaDon(maHoaDon, maNV, giaTien, ngaydat, maKH);
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public Object[] getRow(HoaDon hd) {
        int maHoaDon = hd.getMaHD();
        int maNV = hd.getMaNV();
        Date ngaydat = hd.getNgayDat();
        Double giaTien = hd.getTongTien();
        int maKH = hd.getMaKH();

        return new Object[]{maHoaDon, maNV, ngaydat, giaTien, maKH};
    }
    
    public int addHD(HoaDon hd) {
        String sql = "insert into HoaDon(MaHD, MaNV, NgayDat, TongTien, MaKH) values (?, ?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, hd.getMaHD());
            pstm.setInt(2, hd.getMaNV());
            pstm.setDate(3, hd.getNgayDat());
            pstm.setDouble(4, hd.getTongTien());
            pstm.setInt(5, hd.getMaKH());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int editHD(HoaDon hd) {
    String sql = "UPDATE HoaDon SET MaHD = ?, MaNV = ?, NgayDat = ?, TongTien = ?, MaKH = ?, WHERE MaHD = ?";
    try {
        Connection con = DBconnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, hd.getMaHD());
            pstm.setInt(2, hd.getMaNV());
            pstm.setDate(3, hd.getNgayDat());
            pstm.setDouble(4, hd.getTongTien());
            pstm.setInt(5, hd.getMaKH());

        if (pstm.executeUpdate() > 0) {
            return 1;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
    
    public int deleteHD(HoaDon hd) {
        String sql = "delete from HoaDon where MaHD = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, hd.getMaHD());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
