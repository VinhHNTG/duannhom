/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import javax.swing.table.DefaultTableModel;
import DAO.NhanVienDao;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class NhanVien extends javax.swing.JPanel {

    int index = -1;
    DefaultTableModel tableModel;
    NhanVienDao nvDao = new NhanVienDao();

    /**
     * Creates new form NewJPanel
     */
    public NhanVien() {
        initComponents();
        initTable();
        fillTable();
    }

    public void initTable() {
        tableModel = new DefaultTableModel();
        String[] cols = new String[]{"MÃ NHÂN VIÊN", "TÊN NHÂN VIÊN", "NĂM SINH", "SỐ ĐIỆN THOẠI", "CHỨC VỤ"};
        tableModel.setColumnIdentifiers(cols);
        tableNV.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (model.NhanVien nv : nvDao.getALL()) {
            tableModel.addRow(nvDao.getRow(nv));
        }
    }
    
   private boolean validateForm() {
        if (txtMaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            return false;
        }
        if (txtTenNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên.");
            return false;
        }
        if (txtTuoi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập năm sinh.");
            return false;
        }
        if (txtSDT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập SDT.");
            return false;
        }
        if (txtChucVu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập chức vụ.");
            return false;
        }               
        if (txtSDT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập SDT.");
            return false;
        }
        try {
             String sdt = txtSDT.getText().trim();
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

    public void showdetail() {
        int i = tableNV.getSelectedRow();
        if (i != -1) {
            txtMaNV.setText(String.valueOf(nvDao.getALL().get(i).getMaNV()));
            txtTenNV.setText(String.valueOf(nvDao.getALL().get(i).getTenNV()));
            txtTuoi.setText(String.valueOf(nvDao.getALL().get(i).getNamSinh()));
            txtSDT.setText(String.valueOf(nvDao.getALL().get(i).getSdt()));
            txtChucVu.setText(String.valueOf(nvDao.getALL().get(i).getChucVu()));
        }
    }

    public void add() {
        int maNV = Integer.parseInt(txtMaNV.getText());
        String tenNV = txtTenNV.getText();
        String namSinh = txtTuoi.getText();
        String SDT = txtSDT.getText();
        String chucVu = txtChucVu.getText();
        model.NhanVien nv = new model.NhanVien(maNV, tenNV, namSinh, SDT, chucVu);
        int fix = nvDao.getadd(nv);
        if (fix == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Nhap thanh cong");
        } else {
            JOptionPane.showMessageDialog(this, "Loi!");
        }

    }

    public void Update() {
        index = tableNV.getSelectedRow();
        if (index >= 0) {
            int MaCu = nvDao.getALL().get(index).getMaNV();
            
            int maNV = Integer.parseInt(txtMaNV.getText());
            String tenNV = txtTenNV.getText();
            String namSinh = txtTuoi.getText();
            String SDT = txtSDT.getText();
            String chucVu = txtChucVu.getText();
            model.NhanVien nv = new model.NhanVien(maNV, tenNV, namSinh, SDT, chucVu);
            
            int re = nvDao.Update(nv, MaCu);
            if (re == 1) {
                JOptionPane.showMessageDialog(this, "Sủa Dữ Liệu Thành Công");
                fillTable();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa dữ liệu thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui long chon 1 hang de sua");
        }
    }
    // Xoá Nhân Viên
    public void deleteNV() {
        index = tableNV.getSelectedRow();
        if (index >= 0) {
            int Ma = nvDao.getALL().get(index).getMaNV();
            int re = nvDao.getdelete(Ma);
            if (re == 1) {
                fillTable();
                JOptionPane.showMessageDialog(this, "Xoa Thành Công");
            } else {
                JOptionPane.showMessageDialog(this, "Loi!");
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtChucVu = new javax.swing.JTextField();
        txtTuoi = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXóa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();

        jLabel1.setText("Mã NV");

        jLabel2.setText("Tên NV");

        jLabel3.setText("Năm Sinh");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Chức vụ");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Quản Lý Nhân Viên ");

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

        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Tuổi", "Số Điện Thoại", "Chức vụ"
            }
        ));
        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNV);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnXóa))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSua))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(125, 125, 125)
                                            .addComponent(btnThem)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel6)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXóa))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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
        deleteNV();
    }//GEN-LAST:event_btnXóaActionPerformed

    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        // TODO add your handling code here:
        showdetail();
    }//GEN-LAST:event_tableNVMouseClicked

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXóa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableNV;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}
