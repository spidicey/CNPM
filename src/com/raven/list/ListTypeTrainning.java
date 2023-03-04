package com.raven.list;

import com.raven.DAO.TypeTrainningDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.TypeTrainning;
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

public class ListTypeTrainning extends javax.swing.JPanel {

    public ListTypeTrainning() {
        initComponents();
        //  add row table
        TableFillEvent event = new TableFillEvent() {
            @Override
            public void fill(int row) {
                TableModel model = tblTypeTrainning.getModel();
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(row, i);
                    System.out.println(rowData[i]);
                }
                idTypeTrainning.setText((String) rowData[0]);
                nameTypeTrainning.setText((String) rowData[1]);
                System.out.println(row);
            }
        };
        TypeTrainningDAO typeTrainningDAO = new TypeTrainningDAO();
        List<TypeTrainning> typeTrainningList = typeTrainningDAO.getAll();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        for (TypeTrainning typeTrainning : typeTrainningList) {
            tblTypeTrainning.addRow(new Object[]{typeTrainning.getIdTypeTrainning(), typeTrainning.getNameTypeTrainning()});
        }
        tblTypeTrainning.getColumnModel().getColumn(2).setCellRenderer(new TableFillCellRender());
        tblTypeTrainning.getColumnModel().getColumn(2).setCellEditor(new TableFillEditor(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        addstudent2 = new com.raven.view.Addstudent();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        idTypeTrainning = new com.raven.swing.TextField();
        nameTypeTrainning = new com.raven.swing.TextField();
        spTable = new javax.swing.JScrollPane();
        tblTypeTrainning = new com.raven.swing.Table();
        btnAdd = new com.raven.swing.Button();
        btnDelete = new com.raven.swing.Button();
        btnUpdate = new com.raven.swing.Button();
        header1 = new com.raven.view.Header();
        btnSearch = new com.raven.swing.Button();
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
        jLabel1.setText("Danh sách hệ đào tạo");

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

        idTypeTrainning.setLabelText("Mã hệ đào tạo");
        idTypeTrainning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTypeTrainningActionPerformed(evt);
            }
        });

        nameTypeTrainning.setLabelText("Tên hệ đào tạo");

        spTable.setBorder(null);

        tblTypeTrainning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hệ đào tạo", "Tên hệ đào tạo", "Chỉnh sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblTypeTrainning);
        if (tblTypeTrainning.getColumnModel().getColumnCount() > 0) {
            tblTypeTrainning.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblTypeTrainning.getColumnModel().getColumn(1).setPreferredWidth(100);
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
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(idTypeTrainning, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(188, 188, 188)
                                        .addComponent(nameTypeTrainning, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                                        .addGap(42, 42, 42)))))
                        .addGap(0, 3, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTypeTrainning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTypeTrainning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(227, 227, 227)
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
        String selectedValue = tblTypeTrainning.getModel().getValueAt(tblTypeTrainning.getSelectedRow(), 0).toString();

        System.out.println(selectedValue);
        String sql = "BEGIN transaction\n"
                + "DELETE FROM HE_DAO_TAO WHERE IDHeDaoTao=?\n"
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
                DefaultTableModel model = (DefaultTableModel) tblTypeTrainning.getModel();
                model.removeRow(tblTypeTrainning.getSelectedRow());
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Xoá thất bại");

            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    private void resetHelperText() {
        this.idTypeTrainning.setHelperText("");
        this.nameTypeTrainning.setHelperText("");

    }
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        String idTypeTrainning = this.idTypeTrainning.getText();
        String nameTypeTrainning = this.nameTypeTrainning.getText();
        boolean flag = true;
        if (idTypeTrainning.trim().equals("")) {
            this.idTypeTrainning.setHelperText("Không được bỏ trống mã hệ đào tạo");
            flag = false;
        }
        if (nameTypeTrainning.trim().equals("")) {
            this.nameTypeTrainning.setHelperText("Không được bỏ trống tên hệ đào tạo");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có chắc cập nhật không??", "Cập nhật", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = """
                             BEGIN transaction
                             UPDATE HE_DAO_TAO set IDHeDaoTao=?,TenHeDaoTao=?
                             where IDHeDaoTao=?
                             commit""";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idTypeTrainning);
                p.setString(2, nameTypeTrainning);
                p.setString(3, idTypeTrainning);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Đã cập nhật thành công");
                DefaultTableModel model = (DefaultTableModel) tblTypeTrainning.getModel();
                model.setRowCount(0);
                TypeTrainningDAO typeTrainningDAO = new TypeTrainningDAO();
                List<TypeTrainning> typeTrainningList = typeTrainningDAO.getAll();
                for (TypeTrainning typeTrainning : typeTrainningList) {
                    tblTypeTrainning.addRow(new Object[]{typeTrainning.getIdTypeTrainning(), typeTrainning.getNameTypeTrainning()});
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        resetHelperText();
        String idTypeTrainning = this.idTypeTrainning.getText();
        String nameTypeTrainning = this.nameTypeTrainning.getText();
        boolean flag = true;
        if (idTypeTrainning.trim().equals("")) {
            this.idTypeTrainning.setHelperText("Không được bỏ trống mã hệ đào tạo");
            flag = false;
        }
        if (nameTypeTrainning.trim().equals("")) {
            this.nameTypeTrainning.setHelperText("Không được bỏ trống tên hệ đào tạo");
            flag = false;
        }
        if (!flag) {
            return;
        }

        int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có chắc thêm không??", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = "BEGIN transaction\n"
                        + "INSERT INTO HE_DAO_TAO (IDHeDaoTao,TenHeDaoTao)\n"
                        + "VALUES(?,?)\n"
                        + "commit";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idTypeTrainning);
                p.setString(2, nameTypeTrainning);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Đã thêm thành công");
                DefaultTableModel model = (DefaultTableModel) tblTypeTrainning.getModel();
                model.setRowCount(0);
                TypeTrainningDAO typeTrainningDAO = new TypeTrainningDAO();
                List<TypeTrainning> typeTrainningList = typeTrainningDAO.getAll();
                for (TypeTrainning typeTrainning : typeTrainningList) {
                    tblTypeTrainning.addRow(new Object[]{typeTrainning.getIdTypeTrainning(), typeTrainning.getNameTypeTrainning()});
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void idTypeTrainningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTypeTrainningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTypeTrainningActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        CharSequence searchText = header1.getString().toLowerCase();
        TypeTrainningDAO typeTrainningDAO = new TypeTrainningDAO();
        List<TypeTrainning> typeTrainningList = typeTrainningDAO.getAll();
        DefaultTableModel model = (DefaultTableModel) tblTypeTrainning.getModel();
        model.setRowCount(0);
        for (TypeTrainning typeTrainning : typeTrainningList) {
            if (typeTrainning.getNameTypeTrainning().toLowerCase().contains(searchText)) {
                tblTypeTrainning.addRow(new Object[]{typeTrainning.getIdTypeTrainning(), typeTrainning.getNameTypeTrainning()});
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        idTypeTrainning.setText("");
        nameTypeTrainning.setText("");

    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.view.Addstudent addstudent2;
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button btnUpdate;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idTypeTrainning;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private com.raven.swing.TextField nameTypeTrainning;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblTypeTrainning;
    // End of variables declaration//GEN-END:variables
}
