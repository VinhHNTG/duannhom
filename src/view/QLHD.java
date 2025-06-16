package view;

import DAO.HoaDonDAO;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author ACER
 */
public class QLHD extends javax.swing.JPanel {

    /**
     * Creates new form QLDH
     */
    DefaultTableModel tableModel;
    HoaDonDAO hdDao = new HoaDonDAO();
    int index = -1;

    /**
     * Creates new form QLHD
     */
    public QLHD() {
        initComponents();
        initTable();
        fillTable();
    }

    public void initTable() {
        String[] cols = new String[]{"Mã HĐ", "Mã NV", "Ngày đặt", "Giá tiền", "Mã KH"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        TBHoaDon.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (HoaDon hd : hdDao.getAll()) {
            tableModel.addRow(hdDao.getRow(hd));
        }
    }

    private boolean validateForm() {
        if (TXTmaHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn.");
            return false;
        }
        if (TXTmaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            return false;
        }
        if (TXTngayDat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày đặt.");
            return false;
        }
        if (TXTgia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá tiền.");
            return false;
        }
        try {
            double gia = Double.parseDouble(TXTgia.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá tiền phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải là số hợp lệ.");
            return false;
        }
        if (TXTmaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng.");
            return false;
        }
        return true;
    }

public void addHD() {
    if (!validateForm()) {
        return;
    }

    int maHD = Integer.parseInt(TXTmaHD.getText());
    int maNV = Integer.parseInt(TXTmaNV.getText());
    double tongTien = Double.parseDouble(TXTgia.getText());
    int maKH = Integer.parseInt(TXTmaKH.getText());

    // Always set the current date
    java.sql.Date ngayDat = new java.sql.Date(System.currentTimeMillis());
    TXTngayDat.setText(String.valueOf(ngayDat));

    HoaDon hd = new HoaDon(maHD, maNV, ngayDat, tongTien, maKH);
    int result = hdDao.addHD(hd);

    if (result == 1) {
        fillTable();
        JOptionPane.showMessageDialog(this, "Nhập hóa đơn thành công!");
    } else {
        JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn!");
    }
}


    public void updateHD() {
        index = TBHoaDon.getSelectedRow();
        if (index >= 0) {
            int TheoMa = hdDao.getAll().get(index).getMaHD();
            int maHD = Integer.parseInt(TXTmaHD.getText());
            int maNV = Integer.parseInt(TXTmaNV.getText());
            double tongTien = Double.parseDouble(TXTgia.getText());
            int maKH = Integer.parseInt(TXTmaKH.getText());

            java.sql.Date ngayDat;
            try {
                ngayDat = java.sql.Date.valueOf(TXTngayDat.getText().trim());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Ngày đặt phải đúng định dạng yyyy-MM-dd.");
                return;
            }

            HoaDon hd = new HoaDon(maHD, maNV, ngayDat, tongTien, maKH);
            int result = hdDao.updateHD(hd, TheoMa);
            if (result == 1) {
                JOptionPane.showMessageDialog(this, "Sửa hóa đơn thành công!");
                fillTable();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa hóa đơn thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để sửa.");
        }
    }

    public void deleteHD() {
        index = TBHoaDon.getSelectedRow();
        if (index >= 0) {
            int MaHD = hdDao.getAll().get(index).getMaHD();
            int result = hdDao.deleteHD(MaHD);
            if (result == 1) {
                fillTable();
                JOptionPane.showMessageDialog(this, "Xóa hóa đơn thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
            }
        }

    }

    public void ShowDetail() {
        index = TBHoaDon.getSelectedRow();
        if (index >= 0) {
            HoaDon hd = hdDao.getAll().get(index);
            int MaHD = hd.getMaHD();
            int MaNV = hd.getMaNV();
            Date NgayNhap = hd.getNgayDat();
            double TongTien = hd.getTongTien();
            int MaKH = hd.getMaKH();
            TXTmaHD.setText(String.valueOf(MaHD));
            TXTmaNV.setText(String.valueOf(MaNV));
            TXTngayDat.setText(String.valueOf(NgayNhap));
            TXTgia.setText(String.valueOf(TongTien));
            TXTmaKH.setText(String.valueOf(MaKH));
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        BTaddDH = new javax.swing.JButton();
        BTupdateDH = new javax.swing.JButton();
        BTdeleteDH = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        TXTmaNV = new javax.swing.JTextField();
        TXTngayDat = new javax.swing.JTextField();
        TXTgia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TXTmaHD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TXTmaKH = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TBHoaDon = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Quản lý hóa đơn");

        BTaddDH.setText("THÊM");
        BTaddDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTaddDHActionPerformed(evt);
            }
        });

        BTupdateDH.setText("SỬA");
        BTupdateDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTupdateDHActionPerformed(evt);
            }
        });

        BTdeleteDH.setText("XÓA");
        BTdeleteDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTdeleteDHActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Quản Lý Hóa Đơn"));

        jLabel2.setText("Mã hóa đơn:");

        jLabel3.setText("Mã nhân viên");

        jLabel4.setText("Ngày đặt");

        jLabel5.setText("Đơn giá:");

        jLabel6.setText("Mã khách hàng:");

        TXTmaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTmaKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(TXTgia, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTmaNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTmaHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TXTmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TXTngayDat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTmaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TXTmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TXTngayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TXTgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TXTmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        TBHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        TBHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TBHoaDon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BTdeleteDH, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addComponent(BTupdateDH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTaddDH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(BTaddDH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTupdateDH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(BTdeleteDH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TXTmaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTmaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTmaKHActionPerformed

    private void BTaddDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTaddDHActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            addHD();
            fillTable();
        }
    }//GEN-LAST:event_BTaddDHActionPerformed

    private void BTupdateDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTupdateDHActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            updateHD();
            fillTable();
        }
    }//GEN-LAST:event_BTupdateDHActionPerformed

    private void BTdeleteDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTdeleteDHActionPerformed
        // TODO add your handling code here:
        deleteHD();
    }//GEN-LAST:event_BTdeleteDHActionPerformed

    private void TBHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBHoaDonMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_TBHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTaddDH;
    private javax.swing.JButton BTdeleteDH;
    private javax.swing.JButton BTupdateDH;
    private javax.swing.JTable TBHoaDon;
    private javax.swing.JTextField TXTgia;
    private javax.swing.JTextField TXTmaHD;
    private javax.swing.JTextField TXTmaKH;
    private javax.swing.JTextField TXTmaNV;
    private javax.swing.JTextField TXTngayDat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
