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
    try (
        Connection con = DBconnect.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql)
    ) {
        while (rs.next()) {
            int maNL = rs.getInt(1);
            String tenNL = rs.getString(2);
            double giaNhap = rs.getDouble(3);  // assuming numeric
            int soLuong = rs.getInt(4);
            int maSP = rs.getInt(5);

            NguyenLieu nl = new  NguyenLieu(maNL, tenNL, giaNhap, soLuong, maSP);
            listPB.add(nl);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return listPB;
}

public Object[] getRow(NguyenLieu nl) {
    return new Object[]{
        nl.getMaNL(),
        nl.getTenNL(),
        nl.getSoluong(),
        nl.getGiaNhap(),
        nl.getMaSP()
    };
}

public int addNL(NguyenLieu nl) {
    if (!existsMaSP(nl.getMaSP())) {
        System.out.println("Mã sản phẩm không tồn tại! Không thể thêm nguyên liệu.");
        return 0;
    }

    String sql = "INSERT INTO NguyenLieu (MaNL, TenNL, GiaNhap, SoLuong, MaSP) VALUES (?, ?, ?, ?, ?)";
    try (
        Connection con = DBconnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql)
    ) {
        pstm.setInt(1, nl.getMaNL());
        pstm.setString(2, nl.getTenNL());
        pstm.setDouble(3, nl.getGiaNhap());
        pstm.setInt(4, nl.getSoluong());
        pstm.setInt(5, nl.getMaSP());

        return pstm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

public int editNL(NguyenLieu nl) {
    String sql = "UPDATE NguyenLieu SET TenNL = ?, GiaNhap = ?, SoLuong = ?, MaSP = ? WHERE MaNL = ?";
    try (
        Connection con = DBconnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql)
    ) {
        pstm.setString(1, nl.getTenNL());
        pstm.setDouble(2,nl.getGiaNhap());;
        pstm.setInt(3, nl.getSoluong());
        pstm.setInt(4, nl.getMaSP());
        pstm.setInt(5, nl.getMaNL());

        return pstm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

public int deleteNL(int maNL) {
    String sql = "DELETE FROM NguyenLieu WHERE MaNL = ?";
    try (
        Connection con = DBconnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql)
    ) {
        pstm.setInt(1, maNL);
        return pstm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

// Sửa tên hàm cho đúng ý nghĩa
private boolean existsMaSP(int maSP) {
    String sql = "SELECT COUNT(*) FROM SanPham WHERE MaSP = ?";
    try (
        Connection con = DBconnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql)
    ) {
        pstm.setInt(1, maSP);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

}
