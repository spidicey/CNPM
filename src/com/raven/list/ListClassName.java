package com.raven.list;

import com.raven.DAO.ClassNameDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.ClassName;
import com.raven.swing.ScrollBar;
import com.raven.swing.TextField;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class ListClassName extends javax.swing.JPanel {

    private int role;

    public ListClassName(int role) {
        initComponents();
        this.role = role;
        TableFillEvent event = new TableFillEvent() {
            @Override
            public void fill(int row) {
                TableModel model = tblClassName.getModel();
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(row, i);
                    System.out.println(rowData[i]);
                }
                String idKhoa = "";
                String idHeDaoTao = "";
                String idKhoaDaoTao = "";
                String sql = "SELECT IDKhoaDaoTao,IDKhoa,IDHeDaoTao FROM KHOA_DAO_TAO,KHOA,HE_DAO_TAO WHERE TenKhoa=? and TenHeDaoTao=? and NienKhoa=?";
                try {
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, (String) rowData[3]);
                    p.setString(2, (String) rowData[4]);
                    p.setString(3, (String) rowData[5]);

                    ResultSet rs = p.executeQuery();
                    while (rs.next()) {
                        idKhoa = rs.getString(2);
                        idHeDaoTao = rs.getString(3);
                        idKhoaDaoTao = rs.getString(1);
                    }
                } catch (SQLException ex) {
                }
                idClassName.setText((String) rowData[0]);
                className.setText((String) rowData[1]);
                attendants.setText(Integer.toString((int) rowData[2]));
                Session.setText(idKhoaDaoTao);
                department.setText(idKhoa);
                typeTrainning.setText(idHeDaoTao);
                System.out.println(row);
            }
        };
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        ClassNameDAO classNameDAO = new ClassNameDAO();
        List<ClassName> classNameList = classNameDAO.getAll();
        for (ClassName className : classNameList) {
            tblClassName.addRow(new Object[]{className.getIdClass(), className.getClassName(), className.getAttendants(), className.getDepartment(), className.getTypeTrainning(), className.getSession()});
        }
        tblClassName.getColumnModel().getColumn(6).setCellRenderer(new TableFillCellRender());
        tblClassName.getColumnModel().getColumn(6).setCellEditor(new TableFillEditor(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        Session = new com.raven.swing.TextField();
        className = new com.raven.swing.TextField();
        spTable = new javax.swing.JScrollPane();
        tblClassName = new com.raven.swing.Table();
        btnAdd = new com.raven.swing.Button();
        btnDelete = new com.raven.swing.Button();
        btnUpdate = new com.raven.swing.Button();
        header1 = new com.raven.view.Header();
        attendants = new com.raven.swing.TextField();
        btnSearch = new com.raven.swing.Button();
        department = new com.raven.swing.TextField();
        typeTrainning = new com.raven.swing.TextField();
        idClassName = new com.raven.swing.TextField();
        btnReset = new com.raven.swing.Button();

        setBackground(new java.awt.Color(204, 255, 255));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh sách lớp");

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
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Session.setLabelText("Mã niên khoá");
        Session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SessionActionPerformed(evt);
            }
        });

        className.setLabelText("Tên lớp");

        spTable.setBorder(null);

        tblClassName.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lớp", "Tên lớp", "Số sinh viên", "Khoa", "Hệ đào tao", "Niên khoá", "Chỉnh sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblClassName);
        if (tblClassName.getColumnModel().getColumnCount() > 0) {
            tblClassName.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblClassName.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblClassName.getColumnModel().getColumn(2).setPreferredWidth(10);
        }

        btnAdd.setBackground(new java.awt.Color(22, 255, 0));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

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

        attendants.setLabelText("Số sinh viên");
        attendants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendantsActionPerformed(evt);
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

        department.setLabelText("Mã khoa");
        department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentActionPerformed(evt);
            }
        });

        typeTrainning.setLabelText("Mã hệ đào tạo");
        typeTrainning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeTrainningActionPerformed(evt);
            }
        });

        idClassName.setLabelText("Mã lớp");
        idClassName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idClassNameActionPerformed(evt);
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
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(Session, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(274, 274, 274)
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(201, 201, 201)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(className, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(attendants, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(typeTrainning, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(className, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Session, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attendants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeTrainning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Không có quyền");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String selectedValue = tblClassName.getModel().getValueAt(tblClassName.getSelectedRow(), 0).toString();
        System.out.println(selectedValue);
        String sql = "BEGIN transaction\n"
                + "DELETE FROM LOP WHERE IDLop=?\n"
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
                DefaultTableModel model = (DefaultTableModel) tblClassName.getModel();
                model.removeRow(tblClassName.getSelectedRow());
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Xoá thất bại");

            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Không có quyền");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String idClassName = this.idClassName.getText();
        String className = this.className.getText();
        String attendants = this.attendants.getText();
        String idDepartment = this.department.getText();
        String session = this.Session.getText();
        String typeTrainning = this.typeTrainning.getText();

        System.out.println(idClassName + " " + className);
        if (idClassName.trim().equals("")) {
            this.idClassName.setHelperText("Không được bỏ trống mã lớp");
            flag = false;
        }
        if (className.trim().equals("")) {
            this.className.setHelperText("Không được bỏ trống tên lớp");
            flag = false;

        }
        if (attendants.trim().equals("")) {
            this.attendants.setHelperText("Không được bỏ trống số sinh viên");
            flag = false;
        }
        if (idDepartment.trim().equals("")) {
            this.department.setHelperText("Không được bỏ trống mã khoa");
            flag = false;
        }
        if (session.trim().equals("")) {
            this.Session.setHelperText("Không được bỏ trống mã khoá");
            flag = false;
        }
        if (typeTrainning.trim().equals("")) {
            this.typeTrainning.setHelperText("Không được bỏ trống mã hệ đào tạo");
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
                             UPDATE LOP SET IDLop=?,TenLop=?,SoSV=?,IDKhoa=?,IDHeDaoTao=?,IDKhoaDaoTao=?
                             WHERE IDLop=?
                             commit""";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idClassName);
                p.setString(2, className);
                p.setString(3, attendants);
                p.setString(4, idDepartment);
                p.setString(5, typeTrainning);
                p.setString(6, session);
                p.setString(7, idClassName);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                DefaultTableModel model = (DefaultTableModel) tblClassName.getModel();
                model.setRowCount(0);
                ClassNameDAO classNameDAO = new ClassNameDAO();
                List<ClassName> classNameList = classNameDAO.getAll();
                for (ClassName a : classNameList) {
                    tblClassName.addRow(new Object[]{a.getIdClass(), a.getClassName(), a.getAttendants(), a.getDepartment(), a.getTypeTrainning(), a.getSession()});
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");

            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        resetHelperText();
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Không có quyền");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String idClassName = this.idClassName.getText();
        String className = this.className.getText();
        String attendants = this.attendants.getText();
        String idDepartment = this.department.getText();
        String session = this.Session.getText();
        String typeTrainning = this.typeTrainning.getText();

        System.out.println(idClassName + " " + className);
        if (idClassName.trim().equals("")) {
            this.idClassName.setHelperText("Không được bỏ trống mã lớp");
            flag = false;
        }
        if (className.trim().equals("")) {
            this.className.setHelperText("Không được bỏ trống tên lớp");
            flag = false;

        }
        if (attendants.trim().equals("")) {
            this.attendants.setHelperText("Không được bỏ trống số sinh viên");
            flag = false;
        }
        if (idDepartment.trim().equals("")) {
            this.department.setHelperText("Không được bỏ trống mã khoa");
            flag = false;
        }
        if (session.trim().equals("")) {
            this.Session.setHelperText("Không được bỏ trống mã khoá");
            flag = false;
        }
        if (typeTrainning.trim().equals("")) {
            this.typeTrainning.setHelperText("Không được bỏ trống mã hệ đào tạo");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có chắc thêm không??", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = "BEGIN transaction\n"
                        + "INSERT INTO LOP (IDLop,TenLop,SoSV,IDKhoa,IDHeDaoTao,IDKhoaDaoTao)\n"
                        + "VALUES(?,?,?,?,?,?)\n"
                        + "commit";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idClassName);
                p.setString(2, className);
                p.setString(3, attendants);
                p.setString(4, idDepartment);
                p.setString(5, typeTrainning);
                p.setString(6, session);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Đã thêm thành công");
                DefaultTableModel model = (DefaultTableModel) tblClassName.getModel();
                model.setRowCount(0);
                ClassNameDAO classNameDAO = new ClassNameDAO();
                List<ClassName> classNameList = classNameDAO.getAll();
                for (ClassName a : classNameList) {
                    tblClassName.addRow(new Object[]{a.getIdClass(), a.getClassName(), a.getAttendants(), a.getDepartment(), a.getTypeTrainning(), a.getSession()});
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "TFhêm thất bại");

            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void SessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SessionActionPerformed

    private void attendantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendantsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attendantsActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        CharSequence searchText = header1.getString().toLowerCase();
        ClassNameDAO classNameDAO = new ClassNameDAO();
        List<ClassName> classNameList = classNameDAO.getAll();
        DefaultTableModel model = (DefaultTableModel) tblClassName.getModel();
        model.setRowCount(0);
        for (ClassName className : classNameList) {
            if (className.getIdClass().toLowerCase().contains(searchText)) {
                tblClassName.addRow(new Object[]{className.getIdClass(), className.getClassName(), className.getAttendants(), className.getDepartment(), className.getTypeTrainning(), className.getSession()});
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void departmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departmentActionPerformed

    private void typeTrainningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeTrainningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeTrainningActionPerformed

    private void idClassNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idClassNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idClassNameActionPerformed

    private void resetHelperText() {
        idClassName.setHelperText("");
        Session.setHelperText("");
        className.setHelperText("");
        attendants.setHelperText("");
        department.setHelperText("");
        typeTrainning.setHelperText("");
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        idClassName.setText("");
        Session.setText("");
        className.setText("");
        attendants.setText("");
        department.setText("");
        typeTrainning.setText("");
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.TextField Session;
    private com.raven.swing.TextField attendants;
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button btnUpdate;
    private com.raven.swing.TextField className;
    private com.raven.swing.TextField department;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idClassName;
    private javax.swing.JLabel jLabel1;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblClassName;
    private com.raven.swing.TextField typeTrainning;
    // End of variables declaration//GEN-END:variables
}
