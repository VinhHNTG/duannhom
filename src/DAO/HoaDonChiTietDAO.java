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
import model.NguyenLieu;
import java.sql.*;
import service.DBconnect;
import model.*;
/**
 *
 * @author ACER
 */
public class HoaDonChiTietDAO {
     public List<ChiTietHoaDon> getAll() {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "select * from HoaDonChiTiet";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maHDCT = rs.getInt(1);
                int maHD = rs.getInt(2);
                int maSP = rs.getInt(3);
                String ghichu = rs.getString(4);
                double dongia = rs.getInt(5);
                String trangthai = rs.getString(6);
                

                ChiTietHoaDon hd = new ChiTietHoaDon(maHDCT, maHD, maSP, ghichu,dongia, trangthai);
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Object[] getRow(ChiTietHoaDon hd) {
        int maHDCT = hd.getMaCTHD();
                int maHD = hd.getMaHD();
                int maSP = hd.getMaSP();
                String ghichu = hd.getGhichu();
                double dongia = hd.getDonGia();
                String trangthai = hd.getTrangThai();

        return new Object[]{maHDCT, maHD, maSP, ghichu, dongia,trangthai};
    }

    public int addHDCT(ChiTietHoaDon hdct) {
        String sql = "insert into HoaDonChiTiet(MaCTHD, MaHD, MaSP, GhiChu, DonGia, TrangThai) values (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, hdct.getMaCTHD());
            pstm.setInt(2, hdct.getMaHD());
            pstm.setInt(3, hdct.getMaSP());
            pstm.setString(4, hdct.getGhichu());
            pstm.setDouble(5, hdct.getDonGia());
            pstm.setString(6, hdct.getTrangThai());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int editHDCT(ChiTietHoaDon hdct) {
        String sql = "UPDATE ChiTietHoaDon SET  MaHD = ?, MaSP = ?, GhiChu = ?, DonGia = ?, TrangThai = ?, WHERE MaCTHD = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setInt(1, hdct.getMaCTHD());
            pstm.setInt(2, hdct.getMaHD());
            pstm.setInt(3, hdct.getMaSP());
            pstm.setString(4, hdct.getGhichu());
            pstm.setDouble(5, hdct.getDonGia());
            pstm.setString(6, hdct.getTrangThai());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteHDCT(ChiTietHoaDon cthd) {
        String sql = "delete from ChiTietHoaDon where MaHDCT = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, cthd.getMaCTHD());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
