package com.raven.component;

import com.raven.model.Model_Card;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Card extends javax.swing.JPanel {

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    private Color color1;
    private Color color2;

    public Card() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }

    public void setData(Model_Card data) {
        lbIcon.setIcon(data.getIcon());
        lbId.setText(data.getId());
        lbName.setText(data.getName());
        lbGender.setText(data.getGender());
        lbclassDepartment.setText(data.getDepartment());
        lbEmail.setText(data.getEmail());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbGender = new javax.swing.JLabel();
        lbclassDepartment = new javax.swing.JLabel();
        lbsubject = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/stock.png"))); // NOI18N

        lbId.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbId.setForeground(new java.awt.Color(255, 255, 255));
        lbId.setText("Title");

        lbName.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Values");

        lbGender.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbGender.setForeground(new java.awt.Color(255, 255, 255));
        lbGender.setText("Description");

        lbclassDepartment.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbclassDepartment.setForeground(new java.awt.Color(255, 255, 255));
        lbclassDepartment.setText("Description");

        lbsubject.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbsubject.setForeground(new java.awt.Color(255, 255, 255));
        lbsubject.setText("Description");

        lbEmail.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(255, 255, 255));
        lbEmail.setText("Description");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbsubject)
                    .addComponent(lbGender)
                    .addComponent(lbclassDepartment)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbEmail))
                    .addComponent(lbIcon)
                    .addComponent(lbId))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(lbEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbGender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbclassDepartment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbsubject)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbGender;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbclassDepartment;
    private javax.swing.JLabel lbsubject;
    // End of variables declaration//GEN-END:variables
}
