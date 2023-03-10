package com.raven.list;

import com.raven.DAO.DepartmentDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.Department;
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

public class ListDepartment extends javax.swing.JPanel {

    private int role;

    public ListDepartment(int role) {
        this.role = role;
        initComponents();
        TableFillEvent event = new TableFillEvent() {
            @Override
            public void fill(int row) {
                TableModel model = tblDepartment.getModel();
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(row, i);
                    System.out.println(rowData[i]);
                }
                idDepartment.setText((String) rowData[0]);
                nameDepartment.setText((String) rowData[1]);
                president.setText((String) rowData[2]);
                System.out.println(row);
            }
        };
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> departmentList = departmentDAO.getAll();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        for (Department department : departmentList) {
            tblDepartment.addRow(new Object[]{department.getIdDepartment(), department.getNameDepartment(), department.getPresident()});
        }
        tblDepartment.getColumnModel().getColumn(3).setCellRenderer(new TableFillCellRender());
        tblDepartment.getColumnModel().getColumn(3).setCellEditor(new TableFillEditor(event));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        addstudent2 = new com.raven.view.Addstudent();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        nameDepartment = new com.raven.swing.TextField();
        president = new com.raven.swing.TextField();
        spTable = new javax.swing.JScrollPane();
        tblDepartment = new com.raven.swing.Table();
        btnAdd = new com.raven.swing.Button();
        btnDelete = new com.raven.swing.Button();
        btnUpdate = new com.raven.swing.Button();
        header1 = new com.raven.view.Header();
        btnSearch = new com.raven.swing.Button();
        idDepartment = new com.raven.swing.TextField();
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

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh s??ch khoa");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(735, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nameDepartment.setLabelText("T??n khoa");
        nameDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameDepartmentActionPerformed(evt);
            }
        });

        president.setLabelText("Tr?????ng khoa");

        spTable.setBorder(null);

        tblDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? khoa", "T??n khoa", "Tr?????ng Khoa", "Ch???nh s???a"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblDepartment);
        if (tblDepartment.getColumnModel().getColumnCount() > 0) {
            tblDepartment.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblDepartment.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        btnAdd.setBackground(new java.awt.Color(22, 255, 0));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Th??m");
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Xo??");
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 237, 0));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Ch???nh s???a");
        btnUpdate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(22, 255, 0));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("T??m ki???m");
        btnSearch.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        idDepartment.setLabelText("M?? khoa");
        idDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idDepartmentActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(51, 51, 255));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("?????t l???i");
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
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)))
                        .addGap(0, 3, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGap(191, 191, 191)
                .addComponent(president, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(president, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(nameDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125)
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
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? quy???n");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String selectedValue = tblDepartment.getModel().getValueAt(tblDepartment.getSelectedRow(), 0).toString();
        System.out.println(selectedValue);
        String sql = "BEGIN transaction\n"
                + "DELETE FROM KHOA WHERE IDKhoa=?\n"
                + "commit";
        int responeLogin = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c xo?? kh??ng??", "Xo??", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeLogin == JOptionPane.YES_OPTION) {
            try {
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, selectedValue);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Xo?? th??nh c??ng");
                DefaultTableModel model = (DefaultTableModel) tblDepartment.getModel();
                model.removeRow(tblDepartment.getSelectedRow());
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Xo?? th???t b???i");

            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? quy???n");
            flag = false;

        }
        if (!flag) {
            return;
        }
        resetHelperText();
        String idDepartment = this.idDepartment.getText();
        String nameDepartment = this.nameDepartment.getText();
        String president = this.president.getText();

        if (idDepartment.trim().equals("")) {
            this.idDepartment.setHelperText("Kh??ng ???????c b??? tr???ng m?? khoa");
            flag = false;
        }
        if (nameDepartment.trim().equals("")) {
            this.nameDepartment.setHelperText("Kh??ng ???????c b??? tr???ng t??n khoa");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n c???p nh???t kh??ng?", "Th??m", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = """
                             BEGIN transaction
                             UPDATE KHOA SET IDKhoa=?,TenKhoa=?,TruongKhoa=?
                             WHERE IDKhoa=?
                             commit""";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idDepartment);
                p.setString(2, nameDepartment);
                p.setString(3, president);
                p.setString(4, idDepartment);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "C???p nh???t th??nh c??ng");
                DefaultTableModel model = (DefaultTableModel) tblDepartment.getModel();
                model.setRowCount(0);

                DepartmentDAO departmentDAO = new DepartmentDAO();
                List<Department> departmentList = departmentDAO.getAll();
                for (Department department : departmentList) {
                    tblDepartment.addRow(new Object[]{department.getIdDepartment(), department.getNameDepartment(), department.getPresident()});
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "C???p nh???t th???t b???i");
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? quy???n");
            flag = false;

        }
        if (!flag) {
            return;
        }
        resetHelperText();
        String idDepartment = this.idDepartment.getText();
        String nameDepartment = this.nameDepartment.getText();
        String president = this.president.getText();

        if (idDepartment.trim().equals("")) {
            this.idDepartment.setHelperText("Kh??ng ???????c b??? tr???ng m?? khoa");
            flag = false;
        }
        if (nameDepartment.trim().equals("")) {
            this.nameDepartment.setHelperText("Kh??ng ???????c b??? tr???ng t??n khoa");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c th??m kh??ng??", "Th??m", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = "BEGIN transaction\n"
                        + "INSERT INTO KHOA (IDKhoa,TenKhoa,TruongKhoa)\n"
                        + "VALUES(?,?,?)\n"
                        + "commit";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idDepartment);
                p.setString(2, nameDepartment);
                p.setString(3, president);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "???? th??m th??nh c??ng");
                DefaultTableModel model = (DefaultTableModel) tblDepartment.getModel();
                model.setRowCount(0);

                DepartmentDAO departmentDAO = new DepartmentDAO();
                List<Department> departmentList = departmentDAO.getAll();
                for (Department department : departmentList) {
                    tblDepartment.addRow(new Object[]{department.getIdDepartment(), department.getNameDepartment(), department.getPresident()});
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void nameDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameDepartmentActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        CharSequence searchText = header1.getString().toLowerCase();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> departmentList = departmentDAO.getAll();
        DefaultTableModel model = (DefaultTableModel) tblDepartment.getModel();
        model.setRowCount(0);
        for (Department department : departmentList) {
            if (department.getNameDepartment().toLowerCase().contains(searchText)) {
                tblDepartment.addRow(new Object[]{department.getIdDepartment(), department.getNameDepartment(), department.getPresident()});
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void idDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idDepartmentActionPerformed
    private void resetHelperText() {
        idDepartment.setHelperText("");
        nameDepartment.setHelperText("");
        president.setHelperText("");
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        idDepartment.setText("");
        nameDepartment.setText("");
        president.setText("");

    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.view.Addstudent addstudent2;
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button btnUpdate;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idDepartment;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private com.raven.swing.TextField nameDepartment;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.TextField president;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblDepartment;
    // End of variables declaration//GEN-END:variables
}
