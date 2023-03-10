package com.raven.list;

import com.raven.DAO.SessionDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.Session;
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

public class ListSession extends javax.swing.JPanel {

    private int role;

    public ListSession(int role) {
        initComponents();
        this.role = role;
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
        //  add row table
        TableFillEvent event = new TableFillEvent() {
            @Override
            public void fill(int row) {
                TableModel model = tblSession.getModel();
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(row, i);
                    System.out.println(rowData[i]);
                }
                idSession.setText((String) rowData[0]);
                session.setText((String) rowData[1]);
                note.setText((String) rowData[2]);

                System.out.println(row);
            }
        };
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        SessionDAO sessionDAO = new SessionDAO();
        List<Session> sessionList = sessionDAO.getAll();
        for (Session session : sessionList) {
            tblSession.addRow(new Object[]{session.getIdSession(), session.getSession(), session.getNote()});
        }
        tblSession.getColumnModel().getColumn(3).setCellRenderer(new TableFillCellRender());
        tblSession.getColumnModel().getColumn(3).setCellEditor(new TableFillEditor(event));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        addstudent2 = new com.raven.view.Addstudent();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        session = new com.raven.swing.TextField();
        note = new com.raven.swing.TextField();
        spTable = new javax.swing.JScrollPane();
        tblSession = new com.raven.swing.Table();
        btnAdd = new com.raven.swing.Button();
        btnDelete = new com.raven.swing.Button();
        btnUpdate = new com.raven.swing.Button();
        header1 = new com.raven.view.Header();
        idSession = new com.raven.swing.TextField();
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
        jLabel1.setText("Danh s??ch kho?? ????o t???o");

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

        session.setLabelText("Ni??n kho??");
        session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessionActionPerformed(evt);
            }
        });

        note.setLabelText("Ghi ch??");

        spTable.setBorder(null);

        tblSession.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? kho?? ????o t???o", "Ni??n kho??", "Ghi ch??", "Ch???nh s???a"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblSession);
        if (tblSession.getColumnModel().getColumnCount() > 0) {
            tblSession.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblSession.getColumnModel().getColumn(1).setPreferredWidth(100);
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

        idSession.setLabelText("M?? kho?? ????o t???o");
        idSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idSessionActionPerformed(evt);
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
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idSession, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(201, 201, 201)
                                .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(session, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(274, 274, 274)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(session, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
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
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? quy???n");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String selectedValue = tblSession.getModel().getValueAt(tblSession.getSelectedRow(), 0).toString();

        System.out.println(selectedValue);
        String sql = "BEGIN transaction\n"
                + "DELETE FROM KHOA_DAO_TAO WHERE IDKhoaDaoTao=?\n"
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
                DefaultTableModel model = (DefaultTableModel) tblSession.getModel();
                model.removeRow(tblSession.getSelectedRow());
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
        resetHelperText();
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? quy???n");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String idSession = this.idSession.getText();
        String session = this.session.getText();
        String note = this.note.getText();

        if (idSession.trim().equals("")) {
            this.idSession.setHelperText("Kh??ng ???????c b??? tr???ng m?? kho??");
            flag = false;
        }
        if (session.trim().equals("")) {
            this.session.setHelperText("Kh??ng ???????c b??? tr???ng ni??n kho??");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c c???p nh???t kh??ng??", "C???p nh???t", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = """
                             BEGIN transaction
                             UPDATE KHOA_DAO_TAO SET IDKhoaDaoTao=?,NienKhoa=?,GhiChu=?
                             WHERE IDKhoaDaoTao=?
                             commit""";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idSession);
                p.setString(2, session);
                p.setString(3, note);
                p.setString(4, idSession);

                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "C???p nh???t th??nh c??ng");
                DefaultTableModel model = (DefaultTableModel) tblSession.getModel();
                model.setRowCount(0);
                SessionDAO sessionDAO = new SessionDAO();
                List<Session> sessionList = sessionDAO.getAll();
                for (Session sessions : sessionList) {
                    tblSession.addRow(new Object[]{sessions.getIdSession(), sessions.getSession(), sessions.getNote()});
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
        resetHelperText();
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? quy???n");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String idSession = this.idSession.getText();
        String session = this.session.getText();
        String note = this.note.getText();

        if (idSession.trim().equals("")) {
            this.idSession.setHelperText("Kh??ng ???????c b??? tr???ng m?? kho??");
            flag = false;
        }
        if (session.trim().equals("")) {
            this.session.setHelperText("Kh??ng ???????c b??? tr???ng ni??n kho??");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c th??m kh??ng??", "Th??m", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = "BEGIN transaction\n"
                        + "INSERT INTO KHOA_DAO_TAO (IDKhoaDaoTao,NienKhoa,GhiChu)\n"
                        + "VALUES(?,?,?)\n"
                        + "commit";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idSession);
                p.setString(2, session);
                p.setString(3, note);

                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "???? th??m th??nh c??ng");
                DefaultTableModel model = (DefaultTableModel) tblSession.getModel();
                model.setRowCount(0);
                SessionDAO sessionDAO = new SessionDAO();
                List<Session> sessionList = sessionDAO.getAll();
                for (Session sessions : sessionList) {
                    tblSession.addRow(new Object[]{sessions.getIdSession(), sessions.getSession(), sessions.getNote()});
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Th??m th???t b???i");
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sessionActionPerformed

    private void idSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idSessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idSessionActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        CharSequence searchText = header1.getString().toLowerCase();
        SessionDAO sessionDAO = new SessionDAO();
        List<Session> sessionList = sessionDAO.getAll();
        DefaultTableModel model = (DefaultTableModel) tblSession.getModel();
        model.setRowCount(0);
        for (Session session : sessionList) {
            if (session.getIdSession().toLowerCase().contains(searchText)) {
                tblSession.addRow(new Object[]{session.getIdSession(), session.getSession(), session.getNote()});
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed
    private void resetHelperText() {
        idSession.setHelperText("");
        session.setHelperText("");
        note.setHelperText("");
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        idSession.setText("");
        session.setText("");
        note.setText("");

    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.view.Addstudent addstudent2;
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button btnUpdate;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idSession;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private com.raven.swing.TextField note;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.TextField session;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblSession;
    // End of variables declaration//GEN-END:variables
}
