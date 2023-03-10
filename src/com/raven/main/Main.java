/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.main;

import com.raven.event.EventMenuSelected;
import com.raven.list.ListAccount;
import com.raven.list.ListAssignment;
import com.raven.list.ListClassName;
import com.raven.list.ListCommittee;
import com.raven.list.ListDepartment;
import com.raven.list.ListSession;
import com.raven.list.ListTeacher;
import com.raven.list.ListStudent;
import com.raven.list.ListSubject;
import com.raven.list.ListTypeTrainning;
import com.raven.login.Login;
import com.raven.login.changePassword;
import com.raven.model.Account;
import com.raven.profile.profileStudent;
import com.raven.profile.profileTeacher;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author RAVEN
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private JFrame fram;
    private Account acc;

    public void setFram(JFrame fram) {
        this.fram = fram;
    }

    public Account getAcc() {
        return acc;
    }

    public Main() {
        initComponents();
        setLocationRelativeTo(null);
//        cardTeacher1.setData(new ModelCardTeacher(new ImageIcon(getClass().getResource("/com/raven/icon/stock.png")), "Ma giang vien", "Ten giang vien", "Gioi", "CNTT", "n20dcc@gmail.com", "Thac si", "Truong khoa"));
        setBackground(new Color(0, 0, 0, 0));
        menu.initMoving(Main.this);
        setFram(fram);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                switch (index) {
                    case 1:
                        setForm(new ListTeacher(acc.getRole()));
                        System.out.println(index);
                        break;
                    case 2:
                        setForm(new ListStudent(acc.getRole()));
                        System.out.println(index);
                        break;
                    case 3:
                        setForm(new ListTypeTrainning(acc.getRole()));
                        System.out.println(index);
                        break;

                    case 4:
                        setForm(new ListSession(acc.getRole()));
                        System.out.println(index);
                        break;

                    case 5:
                        setForm(new ListClassName(acc.getRole()));
                        System.out.println(index);
                        break;
                    case 6:
                        if (acc.getRole() > 1) {
                            JOptionPane.showMessageDialog(fram, "Kh??ng c?? quy???n");
                            break;
                        }
                        setForm(new ListAccount(acc.getRole()));
                        System.out.println(index);
                        break;
                    case 7:
                        setForm(new ListSubject(fram, acc));
                        System.out.println(index);
                        break;
//                    case 8:
//                        setForm(new ListAssignment());
//                        System.out.println(index);
//                        break;
                    case 8:
                        setForm(new ListCommittee(fram, acc.getRole()));
                        System.out.println(index);
                        break;
//                    case 9:
//                        setForm(new ListDepartment(acc.getRole()));
//                        System.out.println(index);
//                        break;

                    case 11:
                        if (acc.getRole() == 2) {
                            setForm(new profileTeacher(acc));
                        } else if (acc.getRole() == 3) {
                            setForm(new profileStudent(acc));
                        }
                        break;
                    case 12:
                        setForm(new changePassword(acc));
                        System.out.println(index);
                        break;

                    case 13:
                        int responeLogin = JOptionPane.showConfirmDialog(fram, "B???n c?? ch???c ch????", "Quye v??? ????ng nh???p", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (responeLogin == JOptionPane.YES_OPTION) {
                            Login login = new Login();
                            login.setVisible(true);
                            dispose();
                            break;
                        } else {
                            break;
                        }

                    case 14:
                        System.out.println(index);
                        setFram(fram);
                        int responeExit = JOptionPane.showConfirmDialog(fram, "B???n c?? ch???c mu???n tho??t kh??ng?", "Tho??t", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (responeExit == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        } else {
                            break;
                        }
                    default:
                        System.out.println(index);
                        break;
                }
            }
        });
//        setForm(new ListTeacher(acc.getRole()));
    }

    private void setForm(JComponent component) {
        mainPanel.removeAll();
        mainPanel.add(component);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        menu = new com.raven.view.MenuAdmin();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(102, 153, 255));
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
            .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(288, 288, 288))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private com.raven.view.MenuAdmin menu;
    private com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
