/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.KhachHangDAO;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.*;
/**
 *
 * @author Admin
 */
public class QLKH extends javax.swing.JPanel {
    DefaultTableModel tableModel;
    KhachHangDAO khDao = new KhachHangDAO();
  
    /**
     * Creates new form Menu
     */
    public QLKH() {
        initComponents();
        initTable();
        fillTable();
    }
    
    public void initTable() {
        String[] cols = new String[]{"Mã KH", "Tên KH", "Giới tính", "Tuổi", "Số bàn"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        jTablekh.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (KhachHang kh : khDao.getAll()) {
            tableModel.addRow(khDao.getRow(kh));
        }
    }

    private boolean validateForm() {
        if (txtMakh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng.");
            return false;
        }
        if (txtTenkh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng.");
            return false;
        }
        if (txtSdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập SDT.");
            return false;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng địa chỉ.");
            return false;
        }
        try {
             String sdt = txtSdt.getText().trim();
    if (sdt.length() != 10) {
        JOptionPane.showMessageDialog(this, "Số điện thoại phải có đúng 10 chữ số.");
        return false;
    }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ko được điền kí tự vào SDT.");
            return false;
        }
            return true;
            }

        public void add() {
    try {
        int maKH = Integer.parseInt(txtMakh.getText().trim());
        String tenKH = txtTenkh.getText().trim();
        String sdt = txtSdt.getText().trim();
        String diachi = txtDiaChi.getText().trim();

        model.KhachHang kh = new model.KhachHang(maKH, tenKH, sdt, diachi);
        int r = khDao.ThemKH(kh);
        if (r == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại!");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số!");
    }
}

public void update() {
    int i = jTablekh.getSelectedRow();
    if (i != -1) {
        try {
            int maKH = Integer.parseInt(txtMakh.getText().trim());
            String tenKH = txtTenkh.getText().trim();
            String sdt = txtSdt.getText().trim();
            String diachi = txtDiaChi.getText().trim();

            model.KhachHang kh = new model.KhachHang(maKH, tenKH, sdt, diachi);
            int r = khDao.SuaKH(kh, maKH); // Sửa HEIGHT => maKH
            if (r == 1) {
                JOptionPane.showMessageDialog(this, "Sửa khách hàng thành công!");
                fillTable();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa khách hàng thất bại!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số!");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để sửa!");
    }
}

public void deleteKH() { // Đổi tên hàm cho đúng ý nghĩa
    try {
        int maKH = Integer.parseInt(txtMakh.getText().trim());
        int r = khDao.DeleteKH(maKH);
        if (r == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại!");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số!");
    }
}

     public boolean showdetail() {
    int i = jTablekh.getSelectedRow(); // Đổi tên bảng thành bảng Khách Hàng
    if (i != -1) {
        txtMakh.setText(String.valueOf(khDao.getAll().get(i).getMaKH())); 
        txtTenkh.setText(String.valueOf(khDao.getAll().get(i).getTenKH())); 
        txtSdt.setText(String.valueOf(khDao.getAll().get(i).getSdt())); 
        txtDiaChi.setText(String.valueOf(khDao.getAll().get(i).getDiaChi())); 
        return true;
    }
    return false;
}
     public void reset(){
         txtMakh.setText("");
         txtTenkh.setText("");
         txtSdt.setText("");
         txtDiaChi.setText("");
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMakh = new javax.swing.JTextField();
        txtTenkh = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtThem = new javax.swing.JButton();
        txtSua = new javax.swing.JButton();
        txtXoa = new javax.swing.JButton();
        txtLammoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablekh = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Quản Lý Khách Hàng");

        jLabel2.setText("Mã Khách Hàng");

        jLabel3.setText("Tên Khách Hàng");

        jLabel4.setText("Số Điện Thoại");

        jLabel5.setText("Địa Chỉ");

        txtThem.setText("Thêm");
        txtThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThemActionPerformed(evt);
            }
        });

        txtSua.setText("Sửa");
        txtSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSuaActionPerformed(evt);
            }
        });

        txtXoa.setText("Xóa");
        txtXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXoaActionPerformed(evt);
            }
        });

        txtLammoi.setText("Làm mới");
        txtLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLammoiActionPerformed(evt);
            }
        });

        jTablekh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTablekh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablekhMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablekh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMakh, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtThem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSua))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(txtXoa)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtLammoi)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThemActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_txtThemActionPerformed

    private void txtSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_txtSuaActionPerformed

    private void txtXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtXoaActionPerformed
        // TODO add your handling code here:
        deleteKH();
    }//GEN-LAST:event_txtXoaActionPerformed

    private void txtLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLammoiActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_txtLammoiActionPerformed

    private void jTablekhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablekhMouseClicked
        // TODO add your handling code here:
        showdetail();
    }//GEN-LAST:event_jTablekhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablekh;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JButton txtLammoi;
    private javax.swing.JTextField txtMakh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JButton txtSua;
    private javax.swing.JTextField txtTenkh;
    private javax.swing.JButton txtThem;
    private javax.swing.JButton txtXoa;
    // End of variables declaration//GEN-END:variables
}
