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
import model.SanPhamChiTiet;
import service.DBconnect;

/**
 *
 * @author ACER
 */
public class SanPhamChiTietDAO {

    public SanPhamChiTietDAO(int MaSPCT, int MaSP, String size, int MaTopping) {
    }

    public SanPhamChiTietDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<SanPhamChiTietDAO> getAll() {
        List<SanPhamChiTietDAO> listSPCT = new ArrayList<>();
        String sql = "SELECT * FROM SanPhamChiTiet";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int MaSPCT = rs.getInt(1);
                int MaSP = rs.getInt(2);
                String size = rs.getString(3);
                int MaTopping = rs.getInt(4);
              
               SanPhamChiTietDAO spct = new SanPhamChiTietDAO(MaSPCT, MaSP, size, MaTopping);
                listSPCT.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSPCT;
    }

    public Object[] getRow(SanPhamChiTiet spct) {
        int MaSPCT = spct.getMaSPCT();
        int MaSP = spct.getMaSP();
        String size = spct.getSize();
        int MaTopping = spct.getMaTopping();

        return new Object[]{MaSPCT, MaSP, size, MaTopping};
    }
    
    public int addNL(SanPhamChiTiet spct) {
        String sql = "insert into SanPhamChiTiet(MaSPCT, MaSP, Size, MaToping) values (?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, spct.getMaSPCT());
            pstm.setInt(2, spct.getMaSP());
            pstm.setString(3, spct.getSize());
            pstm.setInt(4, spct.getMaTopping());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int editNL(SanPhamChiTiet spct) {
    String sql = "UPDATE NguyenLieu SET MaSPCT = ?, MaSP = ?, Size = ?, MaToping = ?,  WHERE MaSPCT = ?";
    try {
        Connection con = DBconnect.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, spct.getMaSPCT());
            pstm.setInt(2, spct.getMaSP());
            pstm.setString(3, spct.getSize());
            pstm.setInt(4, spct.getMaTopping());

        if (pstm.executeUpdate() > 0) {
            return 1;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
    
    public int deleteNL(SanPhamChiTiet spct) {
        String sql = "delete from SanPhamChiTiet where MaSPCT = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, spct.getMaSPCT());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
