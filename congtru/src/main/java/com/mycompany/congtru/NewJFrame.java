/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.congtru;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
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
        jPanel1 = new javax.swing.JPanel();
        BtnSoChan = new javax.swing.JButton();
        BtnLe = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        BtnPrime = new javax.swing.JButton();
        BtnBoToden = new javax.swing.JButton();
        BtnXoaToDen = new javax.swing.JButton();
        BtnTong = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        BtnNhap = new javax.swing.JButton();
        EdtNhap = new javax.swing.JTextField();
        CbAm = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListItem = new javax.swing.JList<>();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thao tác trên JList - Checkbox");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn Tác Vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        BtnSoChan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BtnSoChan.setText("Tô đen số chẵn");
        BtnSoChan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSoChanActionPerformed(evt);
            }
        });

        BtnLe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BtnLe.setText("Tô đen số lẻ");
        BtnLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLeActionPerformed(evt);
            }
        });

        BtnPrime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BtnPrime.setText("Tô đen số nguyên tố");
        BtnPrime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrimeActionPerformed(evt);
            }
        });

        BtnBoToden.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BtnBoToden.setText("Bỏ tô đen");
        BtnBoToden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBoTodenActionPerformed(evt);
            }
        });

        BtnXoaToDen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BtnXoaToDen.setText("Xóa các giá trị đang tô đen");
        BtnXoaToDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnXoaToDenActionPerformed(evt);
            }
        });

        BtnTong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BtnTong.setText("Tổng các giá trị trong JList");
        BtnTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnSoChan)
                    .addComponent(BtnLe)
                    .addComponent(jLabel2)
                    .addComponent(BtnPrime)
                    .addComponent(BtnBoToden)
                    .addComponent(BtnXoaToDen)
                    .addComponent(BtnTong))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(BtnSoChan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnLe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnPrime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBoToden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnXoaToDen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnTong)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập Thông Tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        BtnNhap.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BtnNhap.setText("Nhập");
        BtnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNhapActionPerformed(evt);
            }
        });

        EdtNhap.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        CbAm.setText("Cho nhập số âm");

        ListItem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(ListItem);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnNhap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EdtNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CbAm, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CbAm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EdtNhap)
                    .addComponent(BtnNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton8.setText("Đóng Chương Trình");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addComponent(jButton8)
                .addGap(49, 49, 49))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Chọn Tác Vụ");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    static DefaultListModel lm = new DefaultListModel();

    static boolean prime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    private void BtnSoChanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSoChanActionPerformed
        // TODO add your handling code here:
        ListItem.clearSelection();
        for (int i = 0; i < lm.getSize(); i++) {
            int so = Integer.parseInt(lm.getElementAt(i).toString());
            if (so % 2 == 0) {
                ListItem.setSelectionBackground(Color.lightGray);
                ListItem.addSelectionInterval(i, i);
            }
        }
    }//GEN-LAST:event_BtnSoChanActionPerformed

    private void BtnBoTodenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBoTodenActionPerformed
        // TODO add your handling code here:
        ListItem.clearSelection();
    }//GEN-LAST:event_BtnBoTodenActionPerformed

    private void BtnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNhapActionPerformed
        // TODO add your handling code here:
        int n = Integer.parseInt(EdtNhap.getText().toString());
        if (CbAm.isSelected()) {
            lm.addElement(n);
        } else {
            if (n > 0) {
                lm.addElement(n);
            } else {
                JOptionPane.showMessageDialog(this, "Tích vào check box trước khi nhập số âm!");
            }
        }
        ListItem.setModel(lm);
    }//GEN-LAST:event_BtnNhapActionPerformed

    private void BtnLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLeActionPerformed
        // TODO add your handling code here:
        ListItem.clearSelection();
        for (int i = 0; i < lm.getSize(); i++) {
            int so = Integer.parseInt(lm.getElementAt(i).toString());
            if (so % 2 != 0) {
                ListItem.setSelectionBackground(Color.lightGray);
                ListItem.addSelectionInterval(i, i);
            }
        }
    }//GEN-LAST:event_BtnLeActionPerformed

    private void BtnPrimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrimeActionPerformed
        // TODO add your handling code here:p
        ListItem.clearSelection();
        for (int i = 0; i < lm.getSize(); i++) {
            int so = Integer.parseInt(lm.getElementAt(i).toString());
            if (prime(so)) {
                ListItem.setSelectionBackground(Color.lightGray);
                ListItem.addSelectionInterval(i, i);
            }
        }
    }//GEN-LAST:event_BtnPrimeActionPerformed

    private void BtnXoaToDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnXoaToDenActionPerformed
        // TODO add your handling code here:
        int a[] = new int[lm.getSize()];
        a = ListItem.getSelectedIndices();
        for (int i = a.length - 1; i >= 0; i--) {
            lm.removeElementAt(a[i]);
        }
    }//GEN-LAST:event_BtnXoaToDenActionPerformed

    private void BtnTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTongActionPerformed
        // TODO add your handling code here:
        int sum = 0;
        for (int i = 0; i < lm.getSize(); i++) {
            int so = Integer.parseInt(lm.getElementAt(i).toString());
            sum += so;
        }
        JOptionPane.showMessageDialog(this, "Tổng là: " + sum);
    }//GEN-LAST:event_BtnTongActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát không?", "Xác nhận Thoát", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBoToden;
    private javax.swing.JButton BtnLe;
    private javax.swing.JButton BtnNhap;
    private javax.swing.JButton BtnPrime;
    private javax.swing.JButton BtnSoChan;
    private javax.swing.JButton BtnTong;
    private javax.swing.JButton BtnXoaToDen;
    private javax.swing.JCheckBox CbAm;
    private javax.swing.JTextField EdtNhap;
    private javax.swing.JList<String> ListItem;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
