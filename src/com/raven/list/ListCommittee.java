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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class ListCommittee extends javax.swing.JPanel {

    private String selectedValue;
    private JFrame parent;

    public void setFram(JFrame a) {
        this.parent = a;
    }

    public ListCommittee(JFrame parent) {
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
                TableModel model = tblCommittee.getModel();
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(row, i);
                    System.out.println(rowData[i]);
                }
                idCommittee.setText((String) rowData[0]);
                president.setText((String) rowData[1]);
                members.setText(Integer.toString((int) rowData[2]));
                comment.setText((String) rowData[3]);

            }

            @Override
            public void onView(int row) {
                idCommittee.setText("");
                members.setText("");
                president.setText("");
                comment.setText("");
                TableActionEvent event1 = new TableActionEvent() {
                    @Override
                    public void onEdit(int row) {
                        TableModel model = tblDetail.getModel();
                        Object[] rowData = new Object[model.getColumnCount()];
                        for (int i = 0; i < model.getColumnCount(); i++) {
                            rowData[i] = model.getValueAt(row, i);
                            System.out.println(rowData[i]);
                        }
                        idCommittee.setText((String) rowData[0]);
                        members.setText((String) rowData[1]);
                    }

//                    @Override
//                    public void onDelete(int row) {
//                        System.out.println(row);
//                        if (tblDetail.isEditing()) {
//                            tblDetail.getCellEditor().stopCellEditing();
//                        }
//                        selectedValue = tblDetail.getModel().getValueAt(tblDetail.getSelectedRow(), 1).toString();
//                        System.out.println(selectedValue);
//                        String sql = "BEGIN transaction\n"
//                                + "Delete from CT_HOI_DONG where IDGiangVien=?\n"
//                                + "commit";
//                        int responeLogin = JOptionPane.showConfirmDialog(parent, "Bạn có chắc xoá không??", "Xoá", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
//                        if (responeLogin == JOptionPane.YES_OPTION) {
//                            try {
//                                ConnectDatabase myConnection = new ConnectDatabase();
//                                Connection conn = myConnection.openConnection();
//                                PreparedStatement p = conn.prepareStatement(sql);
//                                p.setString(1, selectedValue);
//                                p.executeUpdate();
//                                p.close();
//                                JOptionPane.showMessageDialog(parent, "Xoá thành công");
//                                DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
//                                model.removeRow(tblDetail.getSelectedRow());
//                            } catch (SQLException ex) {
//                                Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
//                                JOptionPane.showMessageDialog(parent, "Xoá thất bại");
//
//                            }
//                        } else {
//                            return;
//                        }
//                    }
                    @Override
                    public void onView(int row) {
                        idCommittee.setText("");
                        members.setText("");
                        president.setText("");
                        comment.setText("");

                        president.setVisible(true);
                        comment.setVisible(true);
                        members.setLabelText("Số thành viên");
                        jLabel1.setText("Danh sách hội đồng");
                        DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
                        model.setRowCount(0);
                        spTable.setViewportView(tblCommittee);
//                        tblDetail.setVisible(true);
                        for (Committee committee : committeeList) {
                            tblCommittee.addRow(new Object[]{committee.getIdCommittee(), committee.getPresident(), committee.getMembers(), committee.getComment()});
                        }
                    }
                };
                president.setVisible(false);
                comment.setVisible(false);
                members.setLabelText("Mã giảng viên");
                jLabel1.setText("Chi tiết hội đồng");
                selectedValue = tblCommittee.getModel().getValueAt(tblCommittee.getSelectedRow(), 0).toString();
                DefaultTableModel model = (DefaultTableModel) tblCommittee.getModel();
                model.setRowCount(0);
                DefaultTableModel model1 = (DefaultTableModel) tblDetail.getModel();
                model1.setRowCount(0);
                spTable.setViewportView(tblDetail);
                tblDetail.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
                tblDetail.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event1));

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
                                tblDetail.addRow(new Object[]{rs.getString("IDHoiDong"), rs.getString("IDGiangVien"), rs.getString("TenGiangVien")});
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
        btnDelete = new com.raven.swing.Button();
        btnUpdate = new com.raven.swing.Button();
        btnAdd = new com.raven.swing.Button();
        spTable = new javax.swing.JScrollPane();
        tblCommittee = new com.raven.swing.Table();
        idCommittee = new com.raven.swing.TextField();
        president = new com.raven.swing.TextField();
        comment = new com.raven.swing.TextField();
        members = new com.raven.swing.TextField();
        btnSearch = new com.raven.swing.Button();
        btnReset = new com.raven.swing.Button();

        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hội đồng", "Mã thành viên", "Thành viên", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
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
        jLabel1.setText("Danh sách hội đồng");

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

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Xoá");
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 237, 0));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Chỉnh sửa");
        btnUpdate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(22, 255, 0));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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

        idCommittee.setLabelText("Mã hội đồng");
        idCommittee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCommitteeActionPerformed(evt);
            }
        });

        president.setLabelText("Chủ tịch hội đồng");
        president.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presidentActionPerformed(evt);
            }
        });

        comment.setLabelText("Nhận xét");
        comment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentActionPerformed(evt);
            }
        });

        members.setLabelText("Số thành viên");
        members.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                membersActionPerformed(evt);
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

        btnReset.setBackground(new java.awt.Color(51, 51, 255));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Đặt lại");
        btnReset.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
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
                                .addComponent(idCommittee, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(president, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(members, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 276, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(idCommittee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(members, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(president, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (jLabel1.getText().equals("Danh sách hội đồng")) {
            String selectedValue = tblCommittee.getModel().getValueAt(tblCommittee.getSelectedRow(), 0).toString();

            System.out.println(selectedValue);
            String sql = "BEGIN transaction\n"
                    + "DELETE FROM HOI_DONG WHERE IDHoiDong=?\n"
                    + "commit";
            int responeLogin = JOptionPane.showConfirmDialog(this, "Bạn có chắc xoá không??", "Xoá", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responeLogin == JOptionPane.YES_OPTION) {
                try {
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, selectedValue);
                    p.executeUpdate();
                    p.close();
                    JOptionPane.showMessageDialog(this, "Xoá thành công");
                    DefaultTableModel model = (DefaultTableModel) tblCommittee.getModel();
                    model.removeRow(tblCommittee.getSelectedRow());
                } catch (SQLException ex) {
                    Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Xoá thất bại");

                }
            } else {
                return;
            }
        } else {
            String selectedValue = tblDetail.getModel().getValueAt(tblDetail.getSelectedRow(), 0).toString();
            String selectedValue1 = tblDetail.getModel().getValueAt(tblDetail.getSelectedRow(), 1).toString();
            System.out.println(selectedValue);
            String sql = "BEGIN transaction\n"
                    + "DELETE FROM CT_HOI_DONG WHERE IDHoiDong=? and IDGiangVien=?\n"
                    + "commit";
            int responeLogin = JOptionPane.showConfirmDialog(this, "Bạn có chắc xoá không??", "Xoá", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responeLogin == JOptionPane.YES_OPTION) {
                try {
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, selectedValue);
                    p.setString(2, selectedValue1);
                    p.executeUpdate();
                    p.close();
                    JOptionPane.showMessageDialog(this, "Xoá thành công");
                    DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
                    model.removeRow(tblDetail.getSelectedRow());
                } catch (SQLException ex) {
                    Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Xoá thất bại");

                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        if (jLabel1.getText().equals("Danh sách hội đồng")) {
            String idCommittee = this.idCommittee.getText();
            String president = this.president.getText();
            String comment = this.comment.getText();
            boolean flag = true;
            if (idCommittee.trim().equals("")) {
                this.idCommittee.setHelperText("Không được bỏ trống mã hội đồng");
                flag = false;
            }
            if (president.trim().equals("")) {
                this.president.setHelperText("Không được bỏ trống chủ tịch hội đồng");
                flag = false;
            }
            if (this.members.getText().trim().equals("")) {
                this.members.setHelperText("Không được bỏ trống số thành viên");
                flag = false;
            }
            if (!flag) {
                return;
            }
            int members = Integer.parseInt(this.members.getText());
            int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responeADD == JOptionPane.YES_OPTION) {
                try {
                    String sql = """
                                 BEGIN transaction
                                 UPDATE HOI_DONG SET IDHoiDong=?,ChuTichHoiDong=?,SoThanhVien=?,NhanXet=?
                                 WHERE IDHoiDong=?
                                 commit""";
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, idCommittee);
                    p.setString(2, president);
                    p.setInt(3, members);
                    p.setString(4, comment);
                    p.setString(5, idCommittee);
                    p.executeUpdate();
                    p.close();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    DefaultTableModel model = (DefaultTableModel) tblCommittee.getModel();
                    model.setRowCount(0);
                    CommitteeDAO committeeDAO = new CommitteeDAO();
                    List<Committee> committeeList = committeeDAO.getAll();
                    for (Committee committee : committeeList) {
                        tblCommittee.addRow(new Object[]{committee.getIdCommittee(), committee.getPresident(), committee.getMembers(), committee.getComment()});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");

                }
            } else {
                return;
            }
        } else {
            String idCommittee1 = this.idCommittee.getText();
            String idTeacher = this.members.getText();
            boolean flag = true;
            if (idCommittee1.trim().equals("")) {
                this.idCommittee.setHelperText("Không được bỏ trống mã hội đồng");
                flag = false;
            }
            if (this.members.getText().trim().equals("")) {
                this.members.setHelperText("Không được bỏ trống mã giảng viên");
                flag = false;
            }
            if (!flag) {
                return;
            }
            int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responeADD == JOptionPane.YES_OPTION) {
                try {
                    String sql = """
                                 BEGIN transaction
                                 UPDATE CT_HOI_DONG SET IDHoiDong=?,IDGiangVien=?
                                 WHERE IDHoiDong=?
                                 commit""";
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, idCommittee1);
                    p.setString(2, idTeacher);
                    p.setString(3, idCommittee1);
                    p.executeUpdate();
                    p.close();
                    sql = "SELECT GIANG_VIEN.TenGiangVien FROM CT_HOI_DONG,GIANG_VIEN WHERE CT_HOI_DONG.IDGiangVien=GIANG_VIEN.IDGiangVien AND CT_HOI_DONG.IDGiangVien=?";
                    p = conn.prepareStatement(sql);
                    p.setString(1, idTeacher);
                    ResultSet rs = p.executeQuery();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    tblDetail.addRow(new Object[]{idCommittee1, idTeacher, rs.getString(1)});

                } catch (SQLException ex) {
                    Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");

                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        if (jLabel1.getText().equals("Danh sách hội đồng")) {
            String idCommittee = this.idCommittee.getText();
            String president = this.president.getText();
            String comment = this.comment.getText();
            boolean flag = true;
            if (idCommittee.trim().equals("")) {
                this.idCommittee.setHelperText("Không được bỏ trống mã hội đồng");
                flag = false;
            }
            if (president.trim().equals("")) {
                this.president.setHelperText("Không được bỏ trống chủ tịch hội đồng");
                flag = false;
            }
            if (this.members.getText().trim().equals("")) {
                this.members.setHelperText("Không được bỏ trống số thành viên");
                flag = false;
            }
            if (!flag) {
                return;
            }
            int members = Integer.parseInt(this.members.getText());
            int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có chắc thêm không??", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responeADD == JOptionPane.YES_OPTION) {
                try {
                    String sql = "BEGIN transaction\n"
                            + "INSERT INTO HOI_DONG(IDHoiDong,ChuTichHoiDong,SoThanhVien,NhanXet)\n"
                            + "VALUES(?,?,?,?)\n"
                            + "commit";
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, idCommittee);
                    p.setString(2, president);
                    p.setInt(3, members);
                    p.setString(4, comment);

                    p.executeUpdate();
                    p.close();
                    JOptionPane.showMessageDialog(this, "Đã thêm thành công");
                    DefaultTableModel model = (DefaultTableModel) tblCommittee.getModel();
                    model.setRowCount(0);
                    CommitteeDAO committeeDAO = new CommitteeDAO();
                    List<Committee> committeeList = committeeDAO.getAll();
                    for (Committee committee : committeeList) {
                        tblCommittee.addRow(new Object[]{committee.getIdCommittee(), committee.getPresident(), committee.getMembers(), committee.getComment()});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");

                }
            } else {
                return;
            }
        } else {
            String idCommittee1 = this.idCommittee.getText();
            String idTeacher = this.members.getText();
            boolean flag = true;
            if (idCommittee1.trim().equals("")) {
                this.idCommittee.setHelperText("Không được bỏ trống mã hội đồng");
                flag = false;
            }
            if (this.members.getText().trim().equals("")) {
                this.members.setHelperText("Không được bỏ trống mã giảng viên");
                flag = false;
            }
            if (!flag) {
                return;
            }
            int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có chắc thêm không??", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responeADD == JOptionPane.YES_OPTION) {
                try {
                    String sql = "BEGIN transaction\n"
                            + "INSERT INTO CT_HOI_DONG(IDHoiDong,IDGiangVien)\n"
                            + "VALUES(?,?)\n"
                            + "commit";
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, idCommittee1);
                    p.setString(2, idTeacher);

                    p.executeUpdate();
                    p.close();
                    sql = "SELECT GIANG_VIEN.TenGiangVien FROM CT_HOI_DONG,GIANG_VIEN WHERE CT_HOI_DONG.IDGiangVien=GIANG_VIEN.IDGiangVien AND CT_HOI_DONG.IDGiangVien=?";
                    p = conn.prepareStatement(sql);
                    p.setString(1, idTeacher);
                    ResultSet rs = p.executeQuery();
                    JOptionPane.showMessageDialog(this, "Đã thêm thành công");
                    tblDetail.addRow(new Object[]{idCommittee1, idTeacher, rs.getString(1)});

                } catch (SQLException ex) {
                    Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(this, "Thêm thất bại");

                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void idCommitteeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCommitteeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCommitteeActionPerformed

    private void commentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commentActionPerformed

    private void membersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_membersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_membersActionPerformed

    private void presidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presidentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_presidentActionPerformed
    private void resetHelperText() {
        idCommittee.setHelperText("");
        members.setHelperText("");
        president.setHelperText("");
        comment.setHelperText("");

    }
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

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        idCommittee.setText("");
        members.setText("");
        president.setText("");
        comment.setText("");
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button btnUpdate;
    private com.raven.swing.TextField comment;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idCommittee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.raven.swing.TextField members;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.TextField president;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblCommittee;
    private com.raven.swing.Table tblDetail;
    // End of variables declaration//GEN-END:variables
}
