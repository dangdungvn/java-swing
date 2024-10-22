package com.noitru.login;

import com.noitru.TaiKhoan;
import com.noitru.login.swing.EventLogin;
import com.noitru.login.swing.PanelCustom;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class LoginTrong extends PanelCustom {

    private EventLogin event;

    public LoginTrong() {
        initComponents();
//        setAlpha(1);
        button1.addActionListener((ActionEvent e) -> {
            String Email = emEdt.getText().trim();
            String Password = passEdt.getText().trim();
            if (TaiKhoan.checkTaiKhoan(Email, Password)) {
                if (event != null) {
                    event.loginDone();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi");
            }

        });
    }

    public void setEventLogin(EventLogin event) {
        this.event = event;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        button1 = new com.noitru.login.swing.Button();
        emEdt = new com.noitru.login.swing.TextField();
        passEdt = new com.noitru.login.swing.Password();

        setBackground(new java.awt.Color(247, 247, 247));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(76, 76, 76));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SIGN IN");

        button1.setBackground(new java.awt.Color(204, 153, 255));
        button1.setForeground(new java.awt.Color(242, 242, 242));
        button1.setText("SIGN IN");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        emEdt.setForeground(new java.awt.Color(102, 102, 102));
        emEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emEdt.setHint("EMAIL");

        passEdt.setForeground(new java.awt.Color(102, 102, 102));
        passEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passEdt.setHint("PASS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(emEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.login.swing.Button button1;
    private com.noitru.login.swing.TextField emEdt;
    private javax.swing.JLabel jLabel1;
    private com.noitru.login.swing.Password passEdt;
    // End of variables declaration//GEN-END:variables
}
