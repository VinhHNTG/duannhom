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
import java.time.LocalDate;
import java.util.Date;

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
                java.sql.Date ngayDat = rs.getDate(3); // sử dụng sql.Date
                double tongTien = rs.getDouble(4);
                int maKH = rs.getInt(5);

                HoaDon hd = new HoaDon(maHoaDon, maNV, ngayDat, tongTien, maKH); // đúng thứ tự
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
        Object[] obj = new Object[]{maHoaDon, maNV, ngaydat, giaTien, maKH};
        return obj;
    }

    public int addHD(HoaDon hd) {
        String sql = "INSERT INTO HoaDon VALUES (?, ?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setInt(1, hd.getMaHD());
            stm.setInt(2, hd.getMaNV());

            // Chuyển java.util.Date hoặc java.sql.Date đều dùng getTime()
            java.sql.Date ngayDat = new java.sql.Date(hd.getNgayDat().getTime());
            stm.setDate(3, ngayDat);

            stm.setDouble(4, hd.getTongTien());
            stm.setInt(5, hd.getMaKH());

            if (stm.executeUpdate() > 0) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 🔄 Cập nhật hóa đơn
    public int updateHD(HoaDon hd, int TheoMa) {
        String sql = "UPDATE HoaDon SET MaHD = ?, MaNV = ?, NgayDat = ?, TongTien = ?, MaKH = ? WHERE MaHD = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setInt(1, hd.getMaHD());
            stm.setInt(2, hd.getMaNV());

            java.sql.Date ngayDat = new java.sql.Date(hd.getNgayDat().getTime());
            stm.setDate(3, ngayDat);

            stm.setDouble(4, hd.getTongTien());
            stm.setInt(5, hd.getMaKH());
            stm.setInt(6, TheoMa); // WHERE MaHD = ?

            if (stm.executeUpdate() > 0) {
                System.out.println("Cập nhật hóa đơn thành công");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 🗑 Xóa hóa đơn
    public int deleteHD(int maHD) {
        String sql = "DELETE FROM HoaDon WHERE MaHD = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, maHD);

            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
