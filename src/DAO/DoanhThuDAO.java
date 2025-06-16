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
import javax.swing.JOptionPane;
import model.DoanhThu;
import service.DBconnect;

/**
 *
 * @author ACER
 */
public class DoanhThuDAO {
public List<DoanhThu> getAll() { 
    List<DoanhThu> list = new ArrayList<>();
    String sql = "SELECT MaDT, MaHD, TongTien FROM DoanhThu";
    
    try {
        Connection con = DBconnect.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
        while (rs.next()) {
            int maDT = rs.getInt(1);  // Mã Doanh Thu
            int maHD = rs.getInt(2);  // Mã Hóa Đơn
            int TongTien = rs.getInt(3);  // Tổng Tiền
            
            DoanhThu dt = new DoanhThu(maDT, maHD, TongTien);
            list.add(dt);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu doanh thu: " + e.getMessage());
    }
    return list;
}
public Object[] getRow(DoanhThu dt) {
    int MaDoanhThu = dt.getMaDT();
    int MaHoaDon = dt.getMaHD();
    int TongTien = dt.getTongTien();

    return new Object[]{MaDoanhThu, MaHoaDon, TongTien};
}
public boolean addDoanhThu(DoanhThu dt) {
    String sql = "INSERT INTO DoanhThu(MaDT, MaHD, TongTien) VALUES (?, ?, ?)";

    try (Connection con = DBconnect.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
        pstm.setInt(1, dt.getMaDT());
        pstm.setInt(2, dt.getMaHD());
        pstm.setInt(3, dt.getTongTien());

        return pstm.executeUpdate() > 0;  // Trả về true nếu thành công
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
public String generateNewMaDoanhThu() {
    String sql = "SELECT MAX(CAST(SUBSTRING(MaDT, 3, LEN(MaDT)) AS int)) FROM DoanhThu WHERE MaDT LIKE 'DT%'";

    try (Connection con = DBconnect.getConnection();
         Statement stm = con.createStatement();
         ResultSet rs = stm.executeQuery(sql)) {
        
        if (rs.next()) {
            int maxId = rs.getInt(1);
            return "DT" + (maxId + 1);
        }
        return "DT1"; // Nếu chưa có bản ghi nào
    } catch (Exception e) {
        e.printStackTrace();
        return "DT1"; // Fallback nếu có lỗi
    }
}
}
