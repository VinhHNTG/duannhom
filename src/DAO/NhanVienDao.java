/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import model.SanPham;
import service.DBconnect;

/**
 *
 * @author ADMIN
 */
public class NhanVienDao {

    public Object[] getRow(NhanVien nv) {
        int maNV = nv.getMaNV();
        String tenNV = nv.getTenNV();
        String namSinh = nv.getNamSinh();
        String sdt = nv.getSdt();
        String chucVu = nv.getChucVu();
        Object[] row = new Object[]{maNV, tenNV, namSinh, sdt, chucVu};
        return row;
    }

    public List<NhanVien> getALL() {
        List<NhanVien> listNV = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maNV = rs.getInt(1);
                String tenNV = rs.getString(2);
                String namSinh = rs.getString(3);
                String sdt = rs.getString(4);
                String chucVu = rs.getString(5);
                NhanVien nv = new NhanVien(maNV, tenNV, namSinh, sdt, chucVu);
                listNV.add(nv);
            }
        } catch (Exception e) {
        }
        return listNV;
    }

    public int getadd(NhanVien nv) {
        String sql = "INSERT INTO NhanVien VALUES(?, ?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, nv.getMaNV());
            stm.setString(2, nv.getTenNV());
            stm.setString(3, nv.getNamSinh());
            stm.setString(4, nv.getSdt());
            stm.setString(5, nv.getChucVu());
            if (stm.executeUpdate() > 0) {
                System.out.println("Them thanh cong");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // xoa 
    public int getdelete(int ma) {
        String sql = "delete from NhanVien WHERE MaNV = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, ma);
            if (stm.executeUpdate() > 0) {
                System.out.println("Xóa Thành Công");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getUpdate(view.NhanVien nv, int ma) {
        String sql = "delete from NhanVien WHERE MaNV = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, ma);
            if (stm.executeUpdate() > 0) {
                System.out.println("Xóa Thành Công");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int Update(NhanVien nv, int TheoMa) {
        String sql = "UPDATE NhanVien SET MaNV =  ?  ,\n"
                + "                       TenNV =  ? ,\n"
                + "		          NamSinh = ? ,\n"
                + "			  SDT =  ? ,\n"
                + "			  ChucVu =  ?  \n"
                + "			  WHERE MaNV =  ? ";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, nv.getMaNV());
            pstm.setString(2, nv.getTenNV());
            pstm.setString(3, nv.getNamSinh());
            pstm.setString(4, nv.getSdt());
            pstm.setString(5, nv.getChucVu());
            pstm.setInt(6, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua. Connect");
                return 1;
            }
        } catch (Exception e) {            
        }       
        return 0;
    }    
}
