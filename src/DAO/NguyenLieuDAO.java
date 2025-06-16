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
                int MaNL = rs.getInt("MaNL");
                String TenNL = rs.getString("TenNL");
                String gianhap = rs.getString("GiaNhap");
                int soluong = rs.getInt("Soluong");
                int maSP = rs.getInt("MaSP");

                NguyenLieu nl = new NguyenLieu(MaNL, TenNL, gianhap, soluong, maSP);
                listPB.add(nl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPB;
    }

    public Object[] getRow(NguyenLieu nl) {
        return new Object[]{nl.getMaNL(), nl.getTenNL(), nl.getSoluong(), nl.getGiaNhap(), nl.getMaSP()};
    }

    public int addNL(NguyenLieu nl) {
//        if (!existsMaSP(nl.getMaSP())) {
//            System.out.println("Mã sản phẩm không tồn tại! Không thể thêm nguyên liệu.");
//            return 0;
//        }

        String sql = "INSERT INTO NguyenLieu VALUES\n" +
                     "(   ?   , ?   , ?   , ?  , ?   )";
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
        String sql = "UPDATE NguyenLieu SET MaNL = ?, TenNL = ?, Soluong = ?, GiaNhap = ?, MaSP = ? WHERE MaNL = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, nl.getMaNL());
            pstm.setString(2, nl.getTenNL());
            pstm.setInt(3, nl.getSoluong());
            pstm.setString(4, nl.getGiaNhap());
            pstm.setInt(5, nl.getMaSP());
            pstm.setInt(6, nl.getMaNL());

            return pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteNL(int MaNL) {
        String sql = "DELETE FROM NguyenLieu WHERE MaNL = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, MaNL);

            return pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private boolean existsMaSP(int maSP) {
        try {
            Connection con = DBconnect.getConnection();
            String sql = "SELECT COUNT(*) FROM SanPham WHERE MaSP = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maSP);
            ResultSet rs = pstm.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
