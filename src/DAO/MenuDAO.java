/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.menuModel;
import java.sql.Connection;
import service.DBconnect;
import java.sql.*;

/**
 *
 * @author ACER
 */
public class MenuDAO {
    public List<menuModel> getAll() {
        List<menuModel> list = new ArrayList<>();
        String sql = "select * from Menu";
        try {
            Connection con = DBconnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                int giaTien = rs.getInt(3);

                menuModel mn = new menuModel(ma, ten, giaTien);
                list.add(mn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Object[] getRow(menuModel mn) {
        String maSP = mn.getMaSP();
        String tenSP = mn.getTenSP();
        int giaTien = mn.getGiaTien();

        return new Object[]{maSP, tenSP, giaTien};
    }
    
    public menuModel getByMaSP(String maSP) {
    String sql = "SELECT * FROM Menu WHERE MaSP = ?";
    try (Connection con = DBconnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, maSP);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String ma = rs.getString("MaSP");
            String ten = rs.getString("TenSP");
            int giaTien = rs.getInt("GiaTien");
            return new menuModel(ma, ten, giaTien);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}
