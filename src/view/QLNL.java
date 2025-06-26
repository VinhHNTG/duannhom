/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.NguyenLieuDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NguyenLieu;

/**
 *
 * @author ACER
 */
public class QLNL extends javax.swing.JPanel {

    /**
     * Creates new form QLNL
     */
   DefaultTableModel tableModel;
    NguyenLieuDAO nlDao = new NguyenLieuDAO();
     int index = -1;

    /**
     * Creates new form QLNLpanel
     */
    public QLNL() {
        initComponents();
        initTable();
        fillTable();
        showdetail();
    }

    public void initTable() {
        String[] cols = new String[]{"Mã Nl", "Tên Nl", "Số lượng", "Giá nhập", "Mã SP"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        TBnguyenLieu.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (NguyenLieu nl : nlDao.getAll()) {
            tableModel.addRow(nlDao.getRow(nl));
        }
    }
    
    public boolean showdetail() {
        int i = TBnguyenLieu.getSelectedRow();
        if (i != -1) {
            TXTmaNL.setText(String.valueOf(nlDao.getAll().get(i).getMaSP()));
            TXTtenNL.setText(String.valueOf(nlDao.getAll().get(i).getTenNL()));
            TXTsoLuong.setText(String.valueOf(nlDao.getAll().get(i).getSoluong()));
            TXTgiaNhap.setText(String.valueOf(nlDao.getAll().get(i).getGiaNhap()));
            TXTmaSP.setText(String.valueOf(nlDao.getAll().get(i).getMaSP()));
            return true;
        }
        return false;
    }

    private boolean validateForm() {
        if (TXTmaNL.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nguyên liệu.");
            return false;
        }
        if (TXTtenNL.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nguyên liệu.");
            return false;
        }
        if (TXTsoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng.");
            return false;
        }  
         try {
            int soluong = Integer.parseInt(TXTsoLuong.getText().trim());
            if (soluong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số hợp lệ.");
            return false;
        }
        if (TXTgiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá nhập.");
            return false;
        }
        try {
            double gia = Double.parseDouble(TXTgiaNhap.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số hợp lệ.");
            return false;
        }

        if (TXTmaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm.");
            return false;
        }

        return true;
    }
    
    public void add() {
    try {
        int maNL = Integer.parseInt(TXTmaNL.getText().trim());
        String tenNL = TXTtenNL.getText().trim();
        double giaNhap =Double.valueOf(TXTgiaNhap.getText().trim()) ;
        int soLuong = Integer.parseInt(TXTsoLuong.getText().trim());
        int maSP = Integer.parseInt(TXTmaSP.getText().trim());

        model.NguyenLieu nl = new model.NguyenLieu(maNL, tenNL, giaNhap, soLuong, maSP);
        int result = nlDao.addNL(nl);
        if (result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm nguyên liệu thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại! (Có thể MaSP không tồn tại)");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!");
    }
}

public void update() {
    index =TBnguyenLieu.getSelectedRow();
    if (index >= 0) {
        try {
            int maCu = nlDao.getAll().get(index).getMaNL();

            int maNL = Integer.parseInt(TXTmaNL.getText().trim());
            String tenNL = TXTtenNL.getText().trim();
            double giaNhap =Double.valueOf(TXTgiaNhap.getText().trim()) ;
            int soLuong = Integer.parseInt(TXTsoLuong.getText().trim());
            int maSP = Integer.parseInt(TXTmaSP.getText().trim());

            model.NguyenLieu nl = new model.NguyenLieu(maNL, tenNL, giaNhap, soLuong, maSP);
            int result = nlDao.editNL(nl); // Không cần truyền maCu nếu editNL update theo maNL
            if (result == 1) {
                JOptionPane.showMessageDialog(this, "Sửa nguyên liệu thành công!");
                fillTable();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để sửa!");
    }
}

public void deleteNL() {
    index = TBnguyenLieu.getSelectedRow();
    if (index >= 0) {
        int maNL = nlDao.getAll().get(index).getMaNL();
        int result = nlDao.deleteNL(maNL);
        if (result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Xoá nguyên liệu thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Xoá thất bại!");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để xoá!");
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
        TXTmaNL = new javax.swing.JTextField();
        TXTtenNL = new javax.swing.JTextField();
        TXTsoLuong = new javax.swing.JTextField();
        TXTgiaNhap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TXTmaSP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBnguyenLieu = new javax.swing.JTable();
        BTupdateNL = new javax.swing.JButton();
        BTaddNL = new javax.swing.JButton();
        BTdeleteNL = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Quản lý nguyên liệu");

        jLabel2.setText("Mã nguyên liệu:");

        jLabel3.setText("Tên nguyên liệu:");

        jLabel4.setText("Số lượng:");

        jLabel5.setText("Giá nhập:");

        jLabel6.setText("Mã sản phẩm:");

        TBnguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
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
        TBnguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBnguyenLieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBnguyenLieu);

        BTupdateNL.setText("SỬA");
        BTupdateNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTupdateNLActionPerformed(evt);
            }
        });

        BTaddNL.setText("THÊM");
        BTaddNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTaddNLActionPerformed(evt);
            }
        });

        BTdeleteNL.setText("XÓA");
        BTdeleteNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTdeleteNLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(247, 247, 247)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTgiaNhap)
                                    .addComponent(TXTsoLuong)
                                    .addComponent(TXTtenNL)
                                    .addComponent(TXTmaNL)
                                    .addComponent(TXTmaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTdeleteNL, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(BTupdateNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BTaddNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(TXTmaNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BTaddNL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TXTtenNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TXTsoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(BTupdateNL, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(BTdeleteNL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TXTgiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TXTmaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTaddNLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTaddNLActionPerformed
        // TODO add your handling code here:
        if(validateForm()){
            add();
            fillTable();
        }
        
    }//GEN-LAST:event_BTaddNLActionPerformed

    private void BTupdateNLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTupdateNLActionPerformed
        // TOO add your handling code here:
            update();
        fillTable();
        
        
    }//GEN-LAST:event_BTupdateNLActionPerformed

    private void BTdeleteNLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTdeleteNLActionPerformed
        // TODO add your handling code here:
        deleteNL();
        fillTable();
    }//GEN-LAST:event_BTdeleteNLActionPerformed

    private void TBnguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBnguyenLieuMouseClicked
showdetail();
        // TODO add your handling code here:
    }//GEN-LAST:event_TBnguyenLieuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTaddNL;
    private javax.swing.JButton BTdeleteNL;
    private javax.swing.JButton BTupdateNL;
    private javax.swing.JTable TBnguyenLieu;
    private javax.swing.JTextField TXTgiaNhap;
    private javax.swing.JTextField TXTmaNL;
    private javax.swing.JTextField TXTmaSP;
    private javax.swing.JTextField TXTsoLuong;
    private javax.swing.JTextField TXTtenNL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
