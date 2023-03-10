package com.raven.list;

import com.raven.DAO.TeacherDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.Teacher;
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

public class ListTeacher extends javax.swing.JPanel {

    private int role;

    public ListTeacher(int role) {
        initComponents();
        this.role = role;
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
        //  add row table
        TableFillEvent event = new TableFillEvent() {
            @Override
            public void fill(int row) {
                resetHelperText();
                TableModel model = tblTeacher.getModel();
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(row, i);
                    System.out.println(rowData[i]);
                }
                idTeacher.setText((String) rowData[0]);
                teacherName.setText((String) rowData[1]);
                sex.setSelectedItem(rowData[2]);
                email.setText((String) rowData[3]);
//                idDepartment.setSelectedItem(rowData[4]);
                academic.setText((String) rowData[4]);
                position.setText((String) rowData[5]);

                System.out.println(row);
            }
        };
        tblTeacher.setAutoCreateRowSorter(true);
        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teacherList = teacherDAO.getAll();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
//        tblTeacher.addRow(new Object[]{"GV01", "L??u M??i K??? Thi", "andrewstrauss@gmail.com", "Nam", "CNTT", "Th???c s??", "Hi???u ph??",});
        for (Teacher teacher : teacherList) {
//            table.addRow(new Object[]{"N20DCCN075", "Nguy???n Ph?????c Duy Th???nh", "D20CQCN01", "Nam","C?? Mau" ,"2002","sdgwb@gmail.com",4.0});
            tblTeacher.addRow(new Object[]{teacher.getIdGiangVien(), teacher.getHoTen(), teacher.getGioiTinh(), teacher.getEmail(), teacher.getHocVi(), teacher.getChucVu()});
        }
        tblTeacher.getColumnModel().getColumn(6).setCellRenderer(new TableFillCellRender());
        tblTeacher.getColumnModel().getColumn(6).setCellEditor(new TableFillEditor(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        header1 = new com.raven.view.Header();
        btnDelete = new com.raven.swing.Button();
        btnUpdate = new com.raven.swing.Button();
        btnAdd = new com.raven.swing.Button();
        spTable = new javax.swing.JScrollPane();
        tblTeacher = new com.raven.swing.Table();
        idTeacher = new com.raven.swing.TextField();
        teacherName = new com.raven.swing.TextField();
        email = new com.raven.swing.TextField();
        academic = new com.raven.swing.TextField();
        position = new com.raven.swing.TextField();
        sex = new javax.swing.JComboBox<>();
        btnSearch = new com.raven.swing.Button();
        btnReset = new com.raven.swing.Button();

        setBackground(new java.awt.Color(204, 255, 255));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh s??ch gi???ng vi??n");

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

        btnAdd.setBackground(new java.awt.Color(22, 255, 0));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Th??m");
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        spTable.setBorder(null);

        tblTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? gi???ng vi??n", "T??n gi???ng vi??n", "Gi???i t??nh", "Email", "H???c v???", "Ch???c v???", "util"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTeacher.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(tblTeacher);
        if (tblTeacher.getColumnModel().getColumnCount() > 0) {
            tblTeacher.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblTeacher.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblTeacher.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblTeacher.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblTeacher.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblTeacher.getColumnModel().getColumn(6).setPreferredWidth(5);
        }

        idTeacher.setLabelText("M?? gi???ng vi??n");
        idTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTeacherActionPerformed(evt);
            }
        });

        teacherName.setLabelText("T??n gi???ng vi??n");
        teacherName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherNameActionPerformed(evt);
            }
        });

        email.setLabelText("Email");
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        academic.setLabelText("H???c v???");
        academic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                academicActionPerformed(evt);
            }
        });

        position.setLabelText("Ch???c v???");
        position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionActionPerformed(evt);
            }
        });

        sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "N???" }));
        sex.setToolTipText("");

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
                .addGap(650, 650, 650)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addGap(259, 259, 259))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(idTeacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(teacherName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(academic, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(academic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        boolean flag = true;
        if (this.role > 1) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? quy???n");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String selectedValue = tblTeacher.getModel().getValueAt(tblTeacher.getSelectedRow(), 0).toString();
        String sql = """
                     BEGIN transaction
                     DELETE FROM GIANG_VIEN WHERE IDGiangVien=?
                     commit""";
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
                DefaultTableModel model = (DefaultTableModel) tblTeacher.getModel();
                model.removeRow(tblTeacher.getSelectedRow());
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
        String idTeacher = this.idTeacher.getText();
        String nameTeacher = this.teacherName.getText();
        String sex = (String) this.sex.getSelectedItem();
        String email = this.email.getText();
//        String idDepartment = (String) this.idDepartment.getSelectedItem();
        String academic = this.academic.getText();
        String position = this.position.getText();
        System.out.println(idTeacher + " " + nameTeacher);
        if (idTeacher.trim().equals("")) {
            this.idTeacher.setHelperText("Kh??ng ???????c b??? tr???ng m?? gi???ng vi??n");
            flag = false;
        }
        if (nameTeacher.trim().equals("")) {
            this.teacherName.setHelperText("Kh??ng ???????c b??? tr???ng t??n gi???ng vi??n");
            flag = false;

        }
        if (email.trim().equals("")) {
            this.email.setHelperText("Kh??ng ???????c b??? tr???ng email");
            flag = false;
        }
        if (academic.trim().equals("")) {
            this.academic.setHelperText("Kh??ng ???????c b??? tr???ng h???c v???");
            flag = false;
        }
        if (position.trim().equals("")) {
            this.position.setHelperText("Kh??ng ???????c b??? tr???ng ch???c v???");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c c???p nh???t kh??ng??", "C???p nh???t", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = "BEGIN transaction\n"
                        + "UPDATE GIANG_VIEN set IDGiangVien=?,TenGiangVien=?,GioiTinh=?,Email=?,HocVi=?,ChucVu=?\n"
                        + "WHERE IDGiangVien=?\n"
                        + "commit";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idTeacher);
                p.setString(2, nameTeacher);
                p.setString(3, sex);
                p.setString(4, email);
//                p.setString(5, idDepartment);
                p.setString(5, academic);
                p.setString(6, position);
                p.setString(7, idTeacher);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "???? c???p nh???t th??nh c??ng");
                DefaultTableModel model = (DefaultTableModel) tblTeacher.getModel();
                model.setRowCount(0);
                TeacherDAO teacherDAO = new TeacherDAO();
                List<Teacher> teacherList = teacherDAO.getAll();
                for (Teacher teacher : teacherList) {
                    tblTeacher.addRow(new Object[]{teacher.getIdGiangVien(), teacher.getHoTen(), teacher.getGioiTinh(), teacher.getEmail(),  teacher.getHocVi(), teacher.getChucVu()});
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
        String idTeacher = this.idTeacher.getText();
        String nameTeacher = this.teacherName.getText();
        String sex = (String) this.sex.getSelectedItem();
        String email = this.email.getText();
//        String idDepartment = (String) this.idDepartment.getSelectedItem();
        String academic = this.academic.getText();
        String position = this.position.getText();

        System.out.println(idTeacher + " " + nameTeacher);
        if (idTeacher.trim().equals("")) {
            this.idTeacher.setHelperText("Kh??ng ???????c b??? tr???ng m?? gi???ng vi??n");
            flag = false;
        }
        if (nameTeacher.trim().equals("")) {
            this.teacherName.setHelperText("Kh??ng ???????c b??? tr???ng t??n gi???ng vi??n");
            flag = false;

        }
        if (email.trim().equals("")) {
            this.email.setHelperText("Kh??ng ???????c b??? tr???ng email");
            flag = false;
        }
        if (academic.trim().equals("")) {
            this.academic.setHelperText("Kh??ng ???????c b??? tr???ng h???c v???");
            flag = false;
        }
        if (position.trim().equals("")) {
            this.position.setHelperText("Kh??ng ???????c b??? tr???ng ch???c v???");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c th??m kh??ng??", "Th??m", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = "BEGIN transaction\n"
                        + "INSERT INTO GIANG_VIEN (IDGiangVien,TenGiangVien,GioiTinh,Email,HocVi,ChucVu)\n"
                        + "VALUES(?,?,?,?,?,?)\n"
                        + "commit";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idTeacher);
                p.setString(2, nameTeacher);
                p.setString(3, sex);
                p.setString(4, email);
//                p.setString(5, idDepartment);
                p.setString(5, academic);
                p.setString(6, position);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "???? th??m th??nh c??ng");
                DefaultTableModel model = (DefaultTableModel) tblTeacher.getModel();
                model.setRowCount(0);
                TeacherDAO teacherDAO = new TeacherDAO();
                List<Teacher> teacherList = teacherDAO.getAll();
                for (Teacher teacher : teacherList) {
                    tblTeacher.addRow(new Object[]{teacher.getIdGiangVien(), teacher.getHoTen(), teacher.getGioiTinh(), teacher.getEmail(),  teacher.getHocVi(), teacher.getChucVu()});
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Th??m th???t b???i");

            }
        } else {
            return;
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void resetHelperText() {
        this.idTeacher.setHelperText("");
        this.email.setHelperText("");
        this.position.setHelperText("");
        this.teacherName.setHelperText("");
        this.academic.setHelperText("");
    }

    private void idTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTeacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTeacherActionPerformed

    private void teacherNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherNameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void academicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_academicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_academicActionPerformed

    private void positionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_positionActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        CharSequence searchText = header1.getString().toLowerCase();
        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teacherList = teacherDAO.getAll();
        DefaultTableModel model = (DefaultTableModel) tblTeacher.getModel();
        model.setRowCount(0);
        for (Teacher teacher : teacherList) {
            if (teacher.getHoTen().toLowerCase().contains(searchText)) {
                tblTeacher.addRow(new Object[]{teacher.getIdGiangVien(), teacher.getHoTen(), teacher.getGioiTinh(), teacher.getEmail(), teacher.getHocVi(), teacher.getChucVu()});
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        idTeacher.setText("");
        teacherName.setText("");
        email.setText("");
        academic.setText("");
        position.setText("");
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.TextField academic;
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button btnUpdate;
    private com.raven.swing.TextField email;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idTeacher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.TextField position;
    private javax.swing.JComboBox<String> sex;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblTeacher;
    private com.raven.swing.TextField teacherName;
    // End of variables declaration//GEN-END:variables
}
