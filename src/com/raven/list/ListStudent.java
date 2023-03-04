package com.raven.list;

import com.raven.DAO.StudentDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.Student;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.beans.Statement;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class ListStudent extends javax.swing.JPanel {

    public ListStudent() {
        initComponents();
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
        //  add row table
        TableFillEvent event = new TableFillEvent() {
            @Override
            public void fill(int row) {
                String idLop = "";
                TableModel model = tblStudent.getModel();
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(row, i);
//                    System.out.println(rowData[i]);
                }
                String sql = "SELECT IDLop FROM LOP WHERE TenLop=?";
                try {
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, (String) rowData[2]);
                    ResultSet rs = p.executeQuery();
                    while (rs.next()) {
                        idLop = rs.getString(1);
                    }
                } catch (SQLException ex) {
                }
                System.out.println(rowData[2]);
                idStudent.setText((String) rowData[0]);
                studentName.setText((String) rowData[1]);
                idClass.setText(idLop);
                sex.setSelectedItem(rowData[3]);
                email.setText((String) rowData[6]);
                birthPlace.setText((String) rowData[4]);
                dateOfBirth.setText((String) rowData[5]);
                gpa.setText(Float.toString((float) rowData[7]));
//                System.out.println(row);
            }
        };
        StudentDAO studentDao = new StudentDAO();
        List<Student> studentList = studentDao.getAll();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Student student : studentList) {
//            table.addRow(new Object[]{"N20DCCN075", "Nguyễn Phước Duy Thịnh", "D20CQCN01", "Nam","Cà Mau" ,"2002","sdgwb@gmail.com",4.0});
            tblStudent.addRow(new Object[]{student.getIdSinhVien(), student.getHoTen(), student.getLop(), student.getGioiTinh(), student.getQueQuan(), dateFormat.format(student.getNamSinh()), student.getEmail(), student.getGpa()});
        }
        tblStudent.getColumnModel().getColumn(8).setCellRenderer(new TableFillCellRender());
        tblStudent.getColumnModel().getColumn(8).setCellEditor(new TableFillEditor(event));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        addstudent2 = new com.raven.view.Addstudent();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        idStudent = new com.raven.swing.TextField();
        studentName = new com.raven.swing.TextField();
        birthPlace = new com.raven.swing.TextField();
        idClass = new com.raven.swing.TextField();
        dateOfBirth = new com.raven.swing.TextField();
        email = new com.raven.swing.TextField();
        gpa = new com.raven.swing.TextField();
        spTable = new javax.swing.JScrollPane();
        tblStudent = new com.raven.swing.Table();
        btnAdd = new com.raven.swing.Button();
        btnDelete = new com.raven.swing.Button();
        btnUpdate = new com.raven.swing.Button();
        header1 = new com.raven.view.Header();
        sex = new javax.swing.JComboBox<>();
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
        jLabel1.setText("Danh sách sinh viên");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(737, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        idStudent.setLabelText("Mã sinh viên");
        idStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idStudentActionPerformed(evt);
            }
        });

        studentName.setLabelText("Tên sinh viên");

        birthPlace.setLabelText("Quê quán");

        idClass.setLabelText("Mã lớp");

        dateOfBirth.setLabelText("Năm sinh");

        email.setLabelText("Email");

        gpa.setLabelText("GPA");

        spTable.setBorder(null);

        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Tên sinh viên", "Lớp", "Giới tính", "Quê Quán", "Năm sinh", "Email", "Gpa", "Chỉnh sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblStudent);
        if (tblStudent.getColumnModel().getColumnCount() > 0) {
            tblStudent.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblStudent.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblStudent.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblStudent.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblStudent.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblStudent.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblStudent.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblStudent.getColumnModel().getColumn(7).setPreferredWidth(10);
            tblStudent.getColumnModel().getColumn(8).setPreferredWidth(20);
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

        sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        sex.setToolTipText("");

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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(birthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idClass, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(78, 78, 78)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(gpa, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(45, 45, 45))
                        .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
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
        String selectedValue = tblStudent.getModel().getValueAt(tblStudent.getSelectedRow(), 0).toString();

        System.out.println(selectedValue);
        String sql = "BEGIN transaction\n"
                + "DELETE FROM SINH_VIEN WHERE IDSinhVien=?\n"
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
                DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
                model.removeRow(tblStudent.getSelectedRow());
            } catch (SQLException ex) {
                Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Xoá thất bại");

            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    private void resetHelperText() {
        this.idClass.setHelperText("");
        this.idStudent.setHelperText("");
        this.studentName.setHelperText("");
        this.birthPlace.setHelperText("");
        this.dateOfBirth.setHelperText("");
        this.idClass.setHelperText("");
        this.email.setHelperText("");
        this.gpa.setHelperText("");
    }
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        resetHelperText();
        String idStudent = this.idStudent.getText();
        String studentName = this.studentName.getText();
        String sex = (String) this.sex.getSelectedItem();
        String email = this.email.getText();
        String birthPlace = this.birthPlace.getText();
        String idClass = this.idClass.getText();
        boolean flag = true;
        if (idStudent.trim().equals("")) {
            this.idStudent.setHelperText("Không được bỏ trống mã sinh viên");
            flag = false;
        }
        if (studentName.trim().equals("")) {
            this.studentName.setHelperText("Không được bỏ trống tên sinh viên");
            flag = false;

        }
        if (email.trim().equals("")) {
            this.email.setHelperText("Không được bỏ trống email");
            flag = false;
        }
        if (this.dateOfBirth.getText().trim().equals("")) {
            this.dateOfBirth.setHelperText("Không được bỏ trống ngày thánh năm sinh");
            flag = false;
        }
        if (idClass.trim().equals("")) {
            this.idClass.setHelperText("Không được bỏ trống chức vụ");
            flag = false;
        }
        if (this.gpa.getText().trim().equals("")) {
            this.gpa.setHelperText("Không được bỏ trống gpa");

        }
        if (this.birthPlace.getText().trim().equals("")) {
            this.birthPlace.setHelperText("Không được bỏ trống gpa");

        }
        if (!flag) {
            return;
        }
        float gpa = Float.parseFloat(this.gpa.getText());
        if (gpa < 0 || gpa > 4) {
            this.gpa.setHelperText("GPA không được < 0 hoặc >4 ");
            return;
        }
        String inputDateString = this.dateOfBirth.getText();
        String[] dateComponents = inputDateString.split("-");
        String outputDateString = dateComponents[2] + "-" + dateComponents[1] + "-" + dateComponents[0];
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = Date.valueOf(outputDateString);
            int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responeADD == JOptionPane.YES_OPTION) {
                try {
                    String sql = "BEGIN TRANSACTION\n"
                            + "UPDATE SINH_VIEN SET IDSinhVien=?,HoTen=?,GioiTinh=?,NamSinh=?,QueQuan=?,Email=?,GPA=?,IDLop=?\n"
                            + "WHERE IDSinhVien=?\n"
                            + "COMMIT";
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, idStudent);
                    p.setString(2, studentName);
                    p.setString(3, sex);
                    p.setDate(4, dateOfBirth);
                    p.setString(5, birthPlace);
                    p.setString(6, email);
                    p.setFloat(7, gpa);
                    p.setString(8, idClass);
                    p.setString(9, idStudent);
                    p.executeUpdate();
                    p.close();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
                    model.setRowCount(0);
                    StudentDAO studentDao = new StudentDAO();
                    List<Student> studentList = studentDao.getAll();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    for (Student student : studentList) {
                        tblStudent.addRow(new Object[]{student.getIdSinhVien(), student.getHoTen(), student.getLop(), student.getGioiTinh(), student.getQueQuan(), dateFormat.format(student.getNamSinh()), student.getEmail(), student.getGpa()});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            } else {
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng format yyyy/MM/dd");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        resetHelperText();
        String idStudent = this.idStudent.getText();
        String studentName = this.studentName.getText();
        String sex = (String) this.sex.getSelectedItem();
        String email = this.email.getText();
        String birthPlace = this.birthPlace.getText();
        String idClass = this.idClass.getText();
        boolean flag = true;
        System.out.println(gpa);
        if (idStudent.trim().equals("")) {
            this.idStudent.setHelperText("Không được bỏ trống mã sinh viên");
            flag = false;
        }
        if (studentName.trim().equals("")) {
            this.studentName.setHelperText("Không được bỏ trống tên sinh viên");
            flag = false;

        }
        if (email.trim().equals("")) {
            this.email.setHelperText("Không được bỏ trống email");
            flag = false;
        }
        if (this.dateOfBirth.getText().trim().equals("")) {
            this.dateOfBirth.setHelperText("Không được bỏ trống ngày thánh năm sinh");
            flag = false;
        }
        if (idClass.trim().equals("")) {
            this.idClass.setHelperText("Không được bỏ trống chức vụ");
            flag = false;
        }
        if (this.gpa.getText().trim().equals("")) {
            this.gpa.setHelperText("Không được bỏ trống gpa");

        }
        if (this.birthPlace.getText().trim().equals("")) {
            this.birthPlace.setHelperText("Không được bỏ trống gpa");

        }
        if (!flag) {
            return;
        }
        float gpa = Float.parseFloat(this.gpa.getText());
        if (gpa < 0 || gpa > 4) {
            this.gpa.setHelperText("GPA không được < 0 hoặc >4 ");
            return;
        }
        String inputDateString = this.dateOfBirth.getText();
        String[] dateComponents = inputDateString.split("-");
        String outputDateString = dateComponents[2] + "-" + dateComponents[1] + "-" + dateComponents[0];
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = Date.valueOf(outputDateString);
            int responeADD = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm không??", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responeADD == JOptionPane.YES_OPTION) {
                try {
                    String sql = "BEGIN TRANSACTION\n"
                            + "INSERT INTO SINH_VIEN (IDSinhVien,HoTen,GioiTinh,NamSinh,QueQuan,Email,GPA,IDLop)\n"
                            + "VALUES (?,?,?,?,?,?,?,?)\n"
                            + "COMMIT";
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, idStudent);
                    p.setString(2, studentName);
                    p.setString(3, sex);
                    p.setDate(4, dateOfBirth);
                    p.setString(5, birthPlace);
                    p.setString(6, email);
                    p.setFloat(7, gpa);
                    p.setString(8, idClass);
                    p.executeUpdate();
                    p.close();
                    JOptionPane.showMessageDialog(this, "Đã thêm thành công");
                    DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
                    model.setRowCount(0);
                    StudentDAO studentDao = new StudentDAO();
                    List<Student> studentList = studentDao.getAll();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    for (Student student : studentList) {
                        tblStudent.addRow(new Object[]{student.getIdSinhVien(), student.getHoTen(), student.getLop(), student.getGioiTinh(), student.getQueQuan(), dateFormat.format(student.getNamSinh()), student.getEmail(), student.getGpa()});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListTeacher.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            } else {
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng format dd/MM/yyyy");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void idStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idStudentActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        CharSequence searchText = header1.getString().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
        model.setRowCount(0);
        StudentDAO studentDao = new StudentDAO();
        List<Student> studentList = studentDao.getAll();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Student student : studentList) {
            if (student.getHoTen().toLowerCase().contains(searchText)) {
                tblStudent.addRow(new Object[]{student.getIdSinhVien(), student.getHoTen(), student.getLop(), student.getGioiTinh(), student.getQueQuan(), dateFormat.format(student.getNamSinh()), student.getEmail(), student.getGpa()});
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        this.idClass.setText("");
        this.idStudent.setText("");
        this.studentName.setText("");
        this.birthPlace.setText("");
        this.dateOfBirth.setText("");
        this.idClass.setText("");
        this.email.setText("");
        this.gpa.setText("");
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.view.Addstudent addstudent2;
    private com.raven.swing.TextField birthPlace;
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button btnUpdate;
    private com.raven.swing.TextField dateOfBirth;
    private com.raven.swing.TextField email;
    private com.raven.swing.TextField gpa;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idClass;
    private com.raven.swing.TextField idStudent;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JComboBox<String> sex;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.TextField studentName;
    private com.raven.swing.Table tblStudent;
    // End of variables declaration//GEN-END:variables
}
