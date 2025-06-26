/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import DAO.*;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChiTietHoaDon;
import model.HoaDon;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author ADMIN
 */
public class BanHang extends javax.swing.JPanel {
    DefaultTableModel tableModel = new DefaultTableModel();
    HoaDonChiTietDAO_1 hdctDao = new HoaDonChiTietDAO_1();
    HoaDonDAO hdDao = new HoaDonDAO();
    SanPhamDao spDAO = new SanPhamDao();
    int index = -1;
    
    /**
     * Creates new form BanHang
     */
    public BanHang() {
        initComponents();
        initTableHD();
        fillTableHD();
        initTableSP();
        fillTableSP();
        getHoaDonHeader();
    }
        public void initTableHD() {
        String[] cols = new String[]{"Mã HĐ", "Mã NV", "Ngày đặt", "Giá tiền", "Mã KH"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        tblHoaDon.setModel(tableModel);
    }
            public void fillTableHD() {
        tableModel.setRowCount(0);
        for (HoaDon hd : hdDao.getAll()) {
            tableModel.addRow(hdDao.getRow(hd));
        }
    }
     public void initTableSP(){
        tableModel= new DefaultTableModel();        
        String[] cols = new String[]{"MÃ SẢn PHẨM","SỐ LƯỢNG","TÊN SẢN PHẨM","NGÀY ĐẶT HÀNG","GIÁ BÁN"};
        tableModel.setColumnIdentifiers(cols);
        tblSP.setModel(tableModel);
    }
         public void fillTableSP(){
        tableModel.setRowCount(0);
        for (model.SanPham sp : spDAO.getALL()) {
            tableModel.addRow((Object[]) spDAO.getRow(sp));
        }
    }
//         public void createHD() {
//        int maHD = Integer.valueOf(txtMaHD.getText()) ;
//        int maNV = Integer.valueOf(txtMaNV.getText());
//        Date  ngayDat = Date.valueOf(txtNgayDat.getText());
//        double tongtien = Double.valueOf(txtTongTien.getText());
//        String maHK = txtMaKH.getText();
//        
//        HoaDon hd = new HoaDon(maHD, maNV, ngayDat, tongtien, maNV);
//        if (hdDao.addHD(hd) == true) {
//            fillTableHD();
//            JOptionPane.showMessageDialog(this, "Tạo Hóa Đơn thành công!");
//        } else {
//            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
//        }
//    }
         public String getHoaDonHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔════════════════════════════════════════════════════════════════════════════════╗\n");
        sb.append("║                        CỬA HÀNG TRÀ SỮA FPT                    ║\n");
        sb.append("║                     Địa chỉ: FPT polyschool Hà Nội             ║\n");
        sb.append("║                        Điện thoại: 0974492469                  ║\n");
        sb.append("╟────────────────────────────────────────────────────────────────────────────────╢\n");
        return sb.toString();
    }
         
        public boolean validateform() {
        if (txtMaHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Yêu cầu nhập Mã Hóa Đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtMaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Yêu cầu nhập Mã Nhân Viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
                if (txtNgayDat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Yêu cầu nhập ngay Đặt", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
                        if (txtTongTien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Yêu cầu nhập Tổng Tiền", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
                if (txtMaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Yêu cầu nhập Mã Khách Hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
        public void ShowDetail() {
        index = tblHoaDon.getSelectedRow();
        if (index >= 0) {
            HoaDon hd = hdDao.getAll().get(index);
            int MaHD = hd.getMaHD();
            int MaNV = hd.getMaNV();
            Date NgayNhap = hd.getNgayDat();
            double TongTien = hd.getTongTien();
            int MaKH = hd.getMaKH();
            txtMaHD.setText(String.valueOf(MaHD));
            txtMaNV.setText(String.valueOf(MaNV));
            txtNgayDat.setText(String.valueOf(NgayNhap));
            txtTongTien.setText(String.valueOf(TongTien));
            txtMaKH.setText(String.valueOf(MaKH));
        }
    }
        
        private boolean validateForm() {
        if (txtMaHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn.");
            return false;
        }
        if (txtMaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            return false;
        }
        if (txtNgayDat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày đặt.");
            return false;
        }
        if (txtTongTien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá tiền.");
            return false;
        }
        try {
            double gia = Double.parseDouble(txtTongTien.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá tiền phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải là số hợp lệ.");
            return false;
        }
        if (txtMaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng.");
            return false;
        }
        return true;
    }

    public void addHD() {
        if (!validateForm()) {
            return;
        }

        int maHD = Integer.parseInt(txtMaHD.getText());
        int maNV = Integer.parseInt(txtMaNV.getText());
        double tongTien = Double.parseDouble(txtTongTien.getText());
        int maKH = Integer.parseInt(txtMaKH.getText());

        java.sql.Date ngayDat;
        String ngayDatStr = txtNgayDat.getText().trim();
        if (!ngayDatStr.isEmpty()) {
            try {
                ngayDat = java.sql.Date.valueOf(ngayDatStr); // yyyy-MM-dd
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Ngày đặt phải đúng định dạng yyyy-MM-dd.");
                return;
            }
        } else {
            ngayDat = new java.sql.Date(System.currentTimeMillis());
            txtNgayDat.setText(String.valueOf(ngayDat));
        }

        // Thứ tự ĐÚNG: (maHD, maNV, ngayDat, tongTien, maKH)
        HoaDon hd = new HoaDon(maHD, maNV, ngayDat, tongTien, maKH);
        int result = hdDao.addHD(hd);
        if (result == 1) {
            fillTableHD();
            JOptionPane.showMessageDialog(this, "Nhập hóa đơn thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn!");
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

        btnTaoHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNgayDat = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        BThienthiHD = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh Toán Sản Phẩm"));

        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSP);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mã Khách Hàng:");

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mã Hóa Đơn:");

        txtMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Mã Nhân Viên:");

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Hóa Đơn:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Sản Phẩm:");

        txtNgayDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayDatActionPerformed(evt);
            }
        });

        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Ngày Đặt :");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tổng Tiền");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        BThienthiHD.setText("Hiển thị hóa đơn");
        BThienthiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BThienthiHDActionPerformed(evt);
            }
        });

        jButton1.setText("In hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(BThienthiHD)
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addContainerGap(234, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BThienthiHD)
                    .addComponent(jButton1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayDat)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel9)
                                .addGap(12, 12, 12)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)
                                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        addHD();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSPMouseClicked

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtNgayDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayDatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayDatActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void BThienthiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BThienthiHDActionPerformed
        // TODO add your handling code here:
        if (validateform()) {

            try {
                // Lấy ngày hiện tại
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = currentDate.format(formatter);

                // Tạo nội dung hoá đơn
                StringBuilder hoaDonContent = new StringBuilder();
                hoaDonContent.append(getHoaDonHeader()); // Thêm header
                hoaDonContent.append("\nNgày: ").append(formattedDate).append("\n\n");
                hoaDonContent.append("Mã hoá đơn:\t").append(txtMaHD.getText()).append("\n");
                hoaDonContent.append("Mã khách hàng:\t").append(txtMaKH.getText()).append("\n");
                hoaDonContent.append("Mã nhân viên:\t").append(txtMaNV.getText()).append("\n");
                hoaDonContent.append("Ngày lập:\t").append(txtNgayDat.getText()).append("\n");
                hoaDonContent.append("Tổng tiền:\t").append(txtTongTien.getText()).append("\n");

                jTextArea1.setText(hoaDonContent.toString());
                jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị hoá đơn: " + ex.getMessage());
            }

        }
    }//GEN-LAST:event_BThienthiHDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            jTextArea1.print();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BThienthiHD;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayDat;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
