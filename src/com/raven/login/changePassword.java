package com.raven.login;

import com.raven.list.*;
import com.raven.DAO.AccountDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.Account;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import raven.cell.TableFillCellRender;
import raven.cell.TableFillEditor;
import raven.cell.TableFillEvent;

public class changePassword extends javax.swing.JPanel {

    public changePassword(Account acc) {
        initComponents();
        setAcc(acc);
        System.out.println(this.acc);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        addstudent2 = new com.raven.view.Addstudent();
        newPassword = new com.raven.swing.TextField();
        currentPassword = new com.raven.swing.TextField();
        confirmPassword = new com.raven.swing.TextField();
        btnReset = new com.raven.swing.Button();

        jFrame1.setMinimumSize(new java.awt.Dimension(356, 503));

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addstudent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addstudent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(204, 255, 255));

        newPassword.setLabelText("Mật khẩu mới");
        newPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPasswordActionPerformed(evt);
            }
        });

        currentPassword.setLabelText("Mật khẩu hiện tại");

        confirmPassword.setLabelText("Xác nhận mật khẩu ");
        confirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(51, 51, 255));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Đặt lại");
        btnReset.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .addComponent(confirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(318, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(currentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPasswordActionPerformed

    private void confirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordActionPerformed
    private void resetHelperText() {
        confirmPassword.setHelperText("");
        newPassword.setHelperText("");
        currentPassword.setHelperText("");
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        System.out.println(acc.getUserName());
        System.out.println(acc.getPassword());
        System.out.println(currentPassword.getText());
        int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có chắc cập nhật không??", "Cập nhật", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            if (currentPassword.getText().equals(acc.getPassword())) {
                if (currentPassword.getText().equals(confirmPassword.getText())) {
                    try {
                        String sql = """
                                     BEGIN transaction
                                     UPDATE TAI_KHOAN set Password=?
                                     WHERE UserName=?
                                     commit""";
                        ConnectDatabase myConnection = new ConnectDatabase();
                        Connection conn = myConnection.openConnection();
                        PreparedStatement p = conn.prepareStatement(sql);
                        p.setString(1, newPassword.getText());
                        p.setString(2, acc.getUserName());
                        p.executeUpdate();
                        p.close();
                        acc.setPassword(newPassword.getText());
                        System.out.println(acc.getPassword());
                        JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công");
                    } catch (SQLException ex) {
                        Logger.getLogger(changePassword.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu sai");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sai mật khẩu");
            }
        }
    }//GEN-LAST:event_btnResetActionPerformed
    private Account acc;

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.view.Addstudent addstudent2;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.TextField confirmPassword;
    private com.raven.swing.TextField currentPassword;
    private javax.swing.JFrame jFrame1;
    private com.raven.swing.TextField newPassword;
    // End of variables declaration//GEN-END:variables
}
