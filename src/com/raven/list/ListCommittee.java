package com.raven.list;

import com.raven.DAO.CommitteeDAO;
import com.raven.DAO.TeacherDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.Committee;
import com.raven.model.ModelCardTeacher;
import com.raven.model.ModelCard;
import com.raven.model.StatusType;
import com.raven.model.Teacher;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class ListCommittee extends javax.swing.JPanel {

    private String selectedValue;

    public ListCommittee() {
        initComponents();
        CommitteeDAO committeeDAO = new CommitteeDAO();
        List<Committee> committeeList = committeeDAO.getAll();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        String firstValvue;
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println(selectedValue);
            }

            @Override
            public void onDelete(int row) {
                if (tblCommittee.isEditing()) {
                    tblCommittee.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tblCommittee.getModel();
                model.removeRow(row);
            }

            @Override
            public void onView(int row) {
                TableActionEvent event1 = new TableActionEvent() {
                    @Override
                    public void onEdit(int row) {

                    }

                    @Override
                    public void onDelete(int row) {
                        if (tblDetail.isEditing()) {
                            tblDetail.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
                        model.removeRow(row);
                    }

                    @Override
                    public void onView(int row) {
                        DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
                        model.setRowCount(0);
                        spTable.setViewportView(tblCommittee);
//                        tblDetail.setVisible(true);
                        for (Committee committee : committeeList) {
                            tblCommittee.addRow(new Object[]{committee.getIdCommittee(), committee.getPresident(), committee.getMembers(), committee.getComment()});
                        }
                    }
                };
                selectedValue = tblCommittee.getModel().getValueAt(tblCommittee.getSelectedRow(), 0).toString();
                DefaultTableModel model = (DefaultTableModel) tblCommittee.getModel();
                model.setRowCount(0);
                DefaultTableModel model1 = (DefaultTableModel) tblDetail.getModel();
                model1.setRowCount(0);
                spTable.setViewportView(tblDetail);
                tblDetail.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
                tblDetail.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(event1));
                String sql = "{CALL SelectAllDetailCommittee(?)}";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                try {
                    if (conn != null) {
                        try {
                            CallableStatement stmt = conn.prepareCall(sql);
                            stmt.setString(1, selectedValue);
                            ResultSet rs = stmt.executeQuery();
                            while (rs.next()) {
                                tblDetail.addRow(new Object[]{rs.getString("IDHoiDong"), rs.getString("TenGiangVien")});
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("Connection fail!");
                    }
                } catch (Exception e) {
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("close failed");
                    }
                }
            }
        };
        for (Committee committee : committeeList) {
            tblCommittee.addRow(new Object[]{committee.getIdCommittee(), committee.getPresident(), committee.getMembers(), committee.getComment()});
        }
        tblCommittee.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        tblCommittee.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetail = new com.raven.swing.Table();
        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        header1 = new com.raven.view.Header();
        button1 = new com.raven.swing.Button();
        button2 = new com.raven.swing.Button();
        button3 = new com.raven.swing.Button();
        spTable = new javax.swing.JScrollPane();
        tblCommittee = new com.raven.swing.Table();
        idTeacher = new com.raven.swing.TextField();
        teacherName = new com.raven.swing.TextField();
        idDepartment = new com.raven.swing.TextField();
        email = new com.raven.swing.TextField();
        btnSearch = new com.raven.swing.Button();

        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã hội đồng", "Thành viên", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDetail);

        setBackground(new java.awt.Color(204, 255, 255));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh sách giảng viên");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        button1.setBackground(new java.awt.Color(255, 0, 0));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Xoá");
        button1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(255, 237, 0));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Chỉnh sửa");
        button2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(22, 255, 0));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Thêm");
        button3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        spTable.setBorder(null);

        tblCommittee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hội đồng", "Chủ tịch hội đồng", "Số thành viên", "Nhận xét", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCommittee.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(tblCommittee);
        if (tblCommittee.getColumnModel().getColumnCount() > 0) {
            tblCommittee.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblCommittee.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        idTeacher.setLabelText("Mã hội đồng");
        idTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTeacherActionPerformed(evt);
            }
        });

        teacherName.setLabelText("Chủ tịch hội đồng");
        teacherName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherNameActionPerformed(evt);
            }
        });

        idDepartment.setLabelText("Nhận xét");
        idDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idDepartmentActionPerformed(evt);
            }
        });

        email.setLabelText("Số thành viên");
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(22, 255, 0));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(650, 650, 650)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addGap(259, 259, 259))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(idTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(teacherName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 276, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button3ActionPerformed

    private void idTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTeacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTeacherActionPerformed

    private void idDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idDepartmentActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void teacherNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherNameActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        CharSequence searchText = header1.getString().toLowerCase();
        CommitteeDAO committeeDAO = new CommitteeDAO();
        List<Committee> committeeList = committeeDAO.getAll();
        DefaultTableModel model = (DefaultTableModel) tblCommittee.getModel();
        model.setRowCount(0);
        for (Committee committee : committeeList) {
            if (committee.getIdCommittee().toLowerCase().contains(searchText)) {
                tblCommittee.addRow(new Object[]{committee.getIdCommittee(), committee.getPresident(), committee.getMembers(), committee.getComment()});

            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button button1;
    private com.raven.swing.Button button2;
    private com.raven.swing.Button button3;
    private com.raven.swing.TextField email;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idDepartment;
    private com.raven.swing.TextField idTeacher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblCommittee;
    private com.raven.swing.Table tblDetail;
    private com.raven.swing.TextField teacherName;
    // End of variables declaration//GEN-END:variables
}
