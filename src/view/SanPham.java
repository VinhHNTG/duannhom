/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.SanPhamDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
/**
 *
 * @author ADMIN
 */
public class SanPham extends javax.swing.JPanel {

    public SanPham() {
        initComponents();
        initTable();
        fillTable();
    }
    DefaultTableModel tablemodel;
    SanPhamDao spDAO = new SanPhamDao();
    /**
     * Creates new form SanPham
     */
    
     public void initTable(){
        tablemodel = new DefaultTableModel();        
        String[] cols = new String[]{"MÃ SẢn PHẨM","TÊN SẢN PHẨM","SỐ LƯỢNG","NGÀY ĐẶT HÀNG","GIÁ BÁN"};
        tablemodel.setColumnIdentifiers(cols);
        tableSP.setModel(tablemodel);
    }
    
        public void fillTable() {
        tablemodel.setRowCount(0);
        for ( model.SanPham sp : spDAO.getALL()) {
            tablemodel.addRow( spDAO.getRow(sp));
        }
    }
        
         private boolean validateForm() {
        if (txtMaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm.");
            return false;
        }
        if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm.");
            return false;
        }
        if (TXTSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng.");
            return false;
        }
        try {
            int soluong = Integer.parseInt(TXTSoLuong.getText().trim());
            if (soluong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số hợp lệ.");
            return false;
        }
        if (TXTngayban.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày bán.");
            return false;
        }
        if (TXTgiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá bán.");
            return false;
        }               
        try {
            double gia = Double.parseDouble(TXTgiaBan.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số hợp lệ.");
            return false;
        }
         
        return true;
    }
    
    public void showdetail() {
        int i = tableSP.getSelectedRow();
        if (i != -1) {
            txtMaSP.setText(String.valueOf(spDAO.getALL().get(i).getMaSP()));
            txtTenSP.setText(String.valueOf(spDAO.getALL().get(i).getTenSP()));
            TXTSoLuong.setText(String.valueOf(spDAO.getALL().get(i).getSoluong()));
            TXTngayban.setText(String.valueOf(spDAO.getALL().get(i).getNgaydathang()));
            TXTgiaBan.setText(String.valueOf(spDAO.getALL().get(i).getGiaban()));
        }
    }
    
 public void add() {
    int maSP = Integer.parseInt(txtMaSP.getText().trim());
    String tenSP = txtTenSP.getText().trim();
    int soluong = Integer.valueOf(TXTSoLuong.getText().trim());
    int giaban = Integer.valueOf(TXTgiaBan.getText().trim());

    // Kiểm tra nếu TXTngayban trống, dùng ngày hiện tại
    Date ngaydathang;
    if (TXTngayban.getText().trim().isEmpty()) {
        ngaydathang = java.sql.Date.valueOf(LocalDate.now()); // Lấy ngày hiện tại đúng định dạng
    } else {
        try {
            ngaydathang = java.sql.Date.valueOf(TXTngayban.getText().trim()); // Chuyển đổi ngày từ người dùng nhập
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ! Định dạng phải là yyyy-MM-dd.");
            return; // Dừng lại nếu ngày sai định dạng
        }
    }

    model.SanPham sp = new model.SanPham(maSP, soluong, tenSP, giaban, ngaydathang);
    int r = spDAO.ThemDL(sp);

    if (r == 1) {
        fillTable();
        JOptionPane.showMessageDialog(this, "Nhập thành công!");
    } else {
        JOptionPane.showMessageDialog(this, "Lỗi!");
    }
}
    
    public void Update() {
        int i = tableSP.getSelectedRow();
        int mcu = spDAO.getALL().get(i).getMaSP(); // Lấy mã sản phẩm cũ
        if (i != -1) {          
        int maSP = Integer.parseInt(txtMaSP.getText());
        String tenSP = txtTenSP.getText();
        int soluong = Integer.valueOf(TXTSoLuong.getText());
        Date ngaydathang = Date.valueOf(TXTngayban.getText());
        int giaban = Integer.valueOf(TXTgiaBan.getText());
        model.SanPham sp = new model.SanPham(maSP, soluong, tenSP, giaban, ngaydathang);
        int r = spDAO.Updatesp(sp,mcu);
        if (r==1) {
            JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
            fillTable();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa dữ liệu thất bại");
        } 
        }else{
            JOptionPane.showMessageDialog(this, "Vui long chon 1 hang de sua");
        }
    }
    
     public void deleteSP() {
        int maSP = Integer.valueOf(txtMaSP.getText());
        String ten = txtTenSP.getText();
        int soluong = Integer.valueOf(TXTSoLuong.getText());
        Date ngaydathang = Date.valueOf(TXTngayban.getText());
        int giaban = Integer.valueOf(TXTgiaBan.getText());
        model.SanPham sp = new model.SanPham(maSP, soluong, ten, giaban, ngaydathang);
        int r = spDAO.deleteHD(maSP);
        if (r==1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm mới thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXóa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TXTSoLuong = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSP = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TXTngayban = new javax.swing.JTextField();
        TXTgiaBan = new javax.swing.JTextField();

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Quản Lý Sản Phẩm ");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXóa.setText("Xóa");
        btnXóa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXóaActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã Sản Phẩm");

        jLabel2.setText("Tên Sản Phẩm");

        jLabel3.setText("Số lượng");

        TXTSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTSoLuongActionPerformed(evt);
            }
        });

        tableSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSP);

        jLabel4.setText("Ngày đặt hàng");

        jLabel5.setText("Giá bán");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTgiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(TXTSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnXóa))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSua))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(125, 125, 125)
                                            .addComponent(btnThem))
                                        .addComponent(TXTngayban, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel6)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TXTSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXóa))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TXTngayban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TXTgiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(validateForm()){
          add();
          fillTable();
        }
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(validateForm()){
          Update();
          fillTable();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXóaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXóaActionPerformed
        // TODO add your handling code here:
        deleteSP();
    }//GEN-LAST:event_btnXóaActionPerformed

    private void TXTSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTSoLuongActionPerformed

    private void tableSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSPMouseClicked
        // TODO add your handling code here:
        showdetail();
    }//GEN-LAST:event_tableSPMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTSoLuong;
    private javax.swing.JTextField TXTgiaBan;
    private javax.swing.JTextField TXTngayban;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXóa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
