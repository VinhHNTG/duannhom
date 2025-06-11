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
                KhachHang kh = new KhachHang(maKH, tenKH,sdt ,diachi);
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

        return new Object[]{maKH, tenKH,sdt ,diachi };
    }
    
    public int addKH(KhachHang kh) {
        String sql = "insert into KhachHang(MaKH, TenKH, SDT, DiaChi ) values (?, ?, ?, ?)";
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
    
    public int editKH(KhachHang kh) {
        String sql = "UPDATE KhachHang SET MaKH = ?, TenKH = ?, SDT = ?,  DiaChi= ?, WHERE MaKH = ?";
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

    public int deleteKH(KhachHang kh) {
        String sql = "delete from KhachHang where MaKH = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, kh.getMaKH());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
