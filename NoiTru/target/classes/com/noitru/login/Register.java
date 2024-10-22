package com.noitru.login;

import com.noitru.TaiKhoan;
import com.noitru.login.swing.EventLogin;
import com.noitru.login.swing.PanelCustom;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Register extends PanelCustom {

    private EventLogin event;

    public Register() {
        initComponents();
        button1.addActionListener((ActionEvent e) -> {
            String TaiKhoant = tkEdt.getText().trim();
            String MatKhau = passEdt.getText().trim();
            String Email = emailEdt.getText().trim();
            if (TaiKhoan.addTaiKhoan(TaiKhoant, MatKhau, Email)) {
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
        tkEdt = new com.noitru.login.swing.TextField();
        emailEdt = new com.noitru.login.swing.TextField();
        passEdt = new com.noitru.login.swing.Password();
        repassEdt = new com.noitru.login.swing.Password();
        button1 = new com.noitru.login.swing.Button();

        setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SIGN UP");

        tkEdt.setForeground(new java.awt.Color(242, 242, 242));
        tkEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tkEdt.setHint("NAME");

        emailEdt.setForeground(new java.awt.Color(242, 242, 242));
        emailEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailEdt.setHint("EMAIL");

        passEdt.setForeground(new java.awt.Color(242, 242, 242));
        passEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passEdt.setHint("PASS");

        repassEdt.setForeground(new java.awt.Color(242, 242, 242));
        repassEdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        repassEdt.setHint("REPASS");

        button1.setBackground(new java.awt.Color(204, 153, 255));
        button1.setForeground(new java.awt.Color(242, 242, 242));
        button1.setText("SIGN UP");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tkEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emailEdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(repassEdt, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(tkEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emailEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(repassEdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.login.swing.Button button1;
    private com.noitru.login.swing.TextField emailEdt;
    private javax.swing.JLabel jLabel1;
    private com.noitru.login.swing.Password passEdt;
    private com.noitru.login.swing.Password repassEdt;
    private com.noitru.login.swing.TextField tkEdt;
    // End of variables declaration//GEN-END:variables
}
