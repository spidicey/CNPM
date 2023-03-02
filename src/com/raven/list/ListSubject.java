package com.raven.list;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.raven.DAO.SubjectDAO;
import com.raven.DAO.TeacherDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.Subject;
import com.raven.model.Teacher;
import com.raven.swing.ScrollBar;
import com.raven.view.test;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import jnafilechooser.api.JnaFileChooser;
import raven.cell.TableDonwloadUploadCellEditor;
import raven.cell.TableDownloadUploadCellRender;
import raven.cell.TableDownloadUploadEvent;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ListSubject extends JPanel {

    public JFrame parent;

    public void setFram(JFrame a) {
        this.parent = a;
    }

    public ListSubject(JFrame parent) {
        initComponents();
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
        //  add row table
        setFram(parent);
        TableDownloadUploadEvent banMemDoAn = new TableDownloadUploadEvent() {
            @Override
            public void download(int row) {
                String selectedValue = tblSubject.getModel().getValueAt(tblSubject.getSelectedRow(), 0).toString();
                JnaFileChooser jnaCh = new JnaFileChooser();
                boolean save = jnaCh.showSaveDialog(parent);
                jnaCh.addFilter("All Files", "*");
                if (save) {
                    try {
                        File file = jnaCh.getSelectedFile();
                        PdfWriter writer;
                        writer = new PdfWriter(file.getPath());
                        PdfDocument pdf = new PdfDocument(writer);
                        Document document = new Document(pdf);
                        System.out.println(file.getPath());
                        byte[] pdfData = Files.readAllBytes(file.toPath());
                        ConnectDatabase myConnection = new ConnectDatabase();
                        Connection conn = myConnection.openConnection();
                        String sql = "SELECT BanMemDeTai FROM DE_TAI where TenDeTai=?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, selectedValue);
                        ResultSet resultSet = stmt.executeQuery();
                        if (resultSet.next()) {
                            byte[] pdfData1 = resultSet.getBytes("BanMemDeTai");
                            FileOutputStream outputStream = new FileOutputStream(file);
                            outputStream.write(pdfData1);
                            outputStream.close();
                        }
                        System.out.println(pdfData);
                        JOptionPane.showMessageDialog(parent, "Tải xuống thành công");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(parent, "Tải xuống thất bại");

                    } catch (IOException ex) {
                        Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Creating a PdfDocument object
                }
            }

            @Override
            public void upload(int row) {
                String selectedValue = tblSubject.getModel().getValueAt(tblSubject.getSelectedRow(), 0).toString();
                System.out.println(selectedValue);
                JnaFileChooser jnaCh = new JnaFileChooser();
                boolean save = jnaCh.showOpenDialog(parent);
                if (save) {
                    String a = jnaCh.getSelectedFile().getPath();
                    File file = jnaCh.getSelectedFile();
                    try {
                        byte[] pdfData = Files.readAllBytes(file.toPath());
                        ConnectDatabase myConnection = new ConnectDatabase();
                        Connection conn = myConnection.openConnection();
                        String sql = "UPDATE DE_TAI SET BanMemDeTai=?  where TenDeTai=?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setBytes(1, pdfData);
                        stmt.setString(2, selectedValue);
                        stmt.executeUpdate();
                        System.out.println(pdfData);
                        JOptionPane.showMessageDialog(parent, "Tải lên thành công");

                    } catch (IOException ex) {
                    } catch (SQLException ex) {
                        Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(parent, "Tải lên thất bại");

                    }
                }
            }
        };
        TableDownloadUploadEvent sourceCode = new TableDownloadUploadEvent() {
            @Override
            public void download(int row) {
                String selectedValue = tblSubject.getModel().getValueAt(tblSubject.getSelectedRow(), 0).toString();
                JnaFileChooser jnaCh = new JnaFileChooser();
                boolean save = jnaCh.showSaveDialog(parent);
                jnaCh.addFilter("All Files", "*");
                if (save) {
                    try {
                        File file = jnaCh.getSelectedFile();
                        System.out.println(file.getPath());
                        ConnectDatabase myConnection = new ConnectDatabase();
                        Connection conn = myConnection.openConnection();
                        String sql = "SELECT SOURCECODE FROM DE_TAI where TenDeTai=?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, selectedValue);
                        ResultSet resultSet = stmt.executeQuery();

                        if (resultSet.next()) {
                            byte[] pdfData1 = resultSet.getBytes("SOURCECODE");
                            FileOutputStream outputStream = new FileOutputStream(jnaCh.getSelectedFile());
                            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
                            ZipEntry entry = new ZipEntry("data.bin");
                            zipOut.putNextEntry(entry);
                            zipOut.write(pdfData1);
                            zipOut.closeEntry();
                            zipOut.close();
                        }
                        JOptionPane.showMessageDialog(parent, "Tải xuống thành công");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(parent, "Tải xuống thất bại");

                    } catch (IOException ex) {
                        Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Creating a PdfDocument object
                }
            }

            @Override
            public void upload(int row) {
                String selectedValue = tblSubject.getModel().getValueAt(tblSubject.getSelectedRow(), 0).toString();
                System.out.println(selectedValue);
                JnaFileChooser jnaCh = new JnaFileChooser();
                boolean save = jnaCh.showOpenDialog(parent);
                if (save) {
                    String a = jnaCh.getSelectedFile().getPath();
                    File file = jnaCh.getSelectedFile();
                    try {
                        byte[] data = Files.readAllBytes(file.toPath());
                        ConnectDatabase myConnection = new ConnectDatabase();
                        Connection conn = myConnection.openConnection();
                        String sql = "UPDATE DE_TAI SET SOURCECODE=?  where TenDeTai=?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setBytes(1, data);
                        stmt.setString(2, selectedValue);
                        stmt.executeUpdate();
                        System.out.println(data);
                        JOptionPane.showMessageDialog(parent, "Tải lên thành công");

                    } catch (IOException ex) {
                    } catch (SQLException ex) {
                        Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(parent, "Tải lên thất bại");

                    }
                }
            }
        };
        SubjectDAO subjectDAO = new SubjectDAO();
        List<Subject> subjectList = subjectDAO.getAll();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
//        tblTeacher.addRow(new Object[]{"GV01", "Lưu Mãi Kỳ Thi", "andrewstrauss@gmail.com", "Nam", "CNTT", "Thạc sĩ", "Hiệu phó",});
        for (Subject subject : subjectList) {
//            table.addRow(new Object[]{"N20DCCN075", "Nguyễn Phước Duy Thịnh", "D20CQCN01", "Nam","Cà Mau" ,"2002","sdgwb@gmail.com",4.0});
            tblSubject.addRow(new Object[]{subject.getNameSubject(), subject.getStudent(), "", "", subject.getInstructor(), subject.getInstructorMark(), subject.getThesis_dissertation(), subject.getThesisMark(), subject.getCommittee(), subject.getCommitteeMark(), subject.getComment()});
        }
        tblSubject.getColumnModel().getColumn(2).setCellRenderer(new TableDownloadUploadCellRender());
        tblSubject.getColumnModel().getColumn(2).setCellEditor(new TableDonwloadUploadCellEditor(banMemDoAn));
        tblSubject.getColumnModel().getColumn(3).setCellRenderer(new TableDownloadUploadCellRender());
        tblSubject.getColumnModel().getColumn(3).setCellEditor(new TableDonwloadUploadCellEditor(sourceCode));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        header1 = new com.raven.view.Header();
        btnDelete = new com.raven.swing.Button();
        button2 = new com.raven.swing.Button();
        btnAdd = new com.raven.swing.Button();
        spTable = new javax.swing.JScrollPane();
        tblSubject = new com.raven.swing.Table();
        idTeacher = new com.raven.swing.TextField();
        teacherName = new com.raven.swing.TextField();
        email = new com.raven.swing.TextField();
        academic = new com.raven.swing.TextField();
        position = new com.raven.swing.TextField();
        sex = new javax.swing.JComboBox<>();
        idDepartment = new javax.swing.JComboBox<>();
        btnSearch = new com.raven.swing.Button();

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

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Xoá");
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
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

        tblSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên đề tài", "Sinh viên thực hiện", "Bản mềm đồ án", "Sourcecode", "Giảng viên hướng dẫn", "Điểm hướng dẫn", "Giảng viên phản biện", "Điểm phản biện", "Hội đồng ", "Điểm hội dồng", "Nhận xét"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSubject.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(tblSubject);
        if (tblSubject.getColumnModel().getColumnCount() > 0) {
            tblSubject.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblSubject.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblSubject.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblSubject.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblSubject.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        idTeacher.setLabelText("Mã giảng viên");
        idTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTeacherActionPerformed(evt);
            }
        });

        teacherName.setLabelText("Tên giảng viên");
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

        academic.setLabelText("Học vị");
        academic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                academicActionPerformed(evt);
            }
        });

        position.setLabelText("Chức vụ");
        position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionActionPerformed(evt);
            }
        });

        sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        sex.setToolTipText("");

        idDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ATTT", "CNTT", "DPT", "DT", "VT" }));
        idDepartment.setToolTipText("");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel)
                        .addGap(259, 259, 259))))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(idTeacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(teacherName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(sex, javax.swing.GroupLayout.Alignment.TRAILING, 0, 230, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 406, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(academic, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(idDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(academic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String selectedValue = tblSubject.getModel().getValueAt(tblSubject.getSelectedRow(), 0).toString();

        System.out.println(selectedValue);
        String sql = "BEGIN transaction\n"
                + "DELETE FROM DE_TAI WHERE TenDeTai=?\n"
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
                DefaultTableModel model = (DefaultTableModel) tblSubject.getModel();
                model.removeRow(tblSubject.getSelectedRow());
            } catch (SQLException ex) {
                Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Xoá thất bại");

            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button2ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        String idTeacher = this.idTeacher.getText();
        String nameTeacher = this.teacherName.getText();
        String sex = (String) this.sex.getSelectedItem();
        String email = this.email.getText();
        String idDepartment = (String) this.idDepartment.getSelectedItem();
        String academic = this.academic.getText();
        String position = this.position.getText();
        boolean flag = true;
        System.out.println(idTeacher + " " + nameTeacher);
        if (idTeacher.trim().equals("")) {
            this.idTeacher.setHelperText("Không được bỏ trống mã giảng viên");
            flag = false;
        }
        if (nameTeacher.trim().equals("")) {
            this.teacherName.setHelperText("Không được bỏ trống tên giảng viên");
            flag = false;

        }
        if (email.trim().equals("")) {
            this.email.setHelperText("Không được bỏ trống email");
            flag = false;
        }
        if (academic.trim().equals("")) {
            this.academic.setHelperText("Không được bỏ trống học vị");
            flag = false;
        }
        if (position.trim().equals("")) {
            this.position.setHelperText("Không được bỏ trống chức vụ");
            flag = false;
        }
        if (!flag) {
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có chắc thêm không??", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = "BEGIN transaction\n"
                        + "INSERT INTO GIANG_VIEN (IDGiangVien,TenGiangVien,GioiTinh,Email,IDKhoa,HocVi,ChucVu)\n"
                        + "VALUES(?,?,?,?,?,?,?)\n"
                        + "commit";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idTeacher);
                p.setString(2, nameTeacher);
                p.setString(3, sex);
                p.setString(4, email);
                p.setString(5, idDepartment);
                p.setString(6, academic);
                p.setString(7, position);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Đã thêm thành công");
                DefaultTableModel model = (DefaultTableModel) tblSubject.getModel();
                model.setRowCount(0);
                TeacherDAO teacherDAO = new TeacherDAO();
                List<Teacher> teacherList = teacherDAO.getAll();
                for (Teacher teacher : teacherList) {
                    tblSubject.addRow(new Object[]{teacher.getIdGiangVien(), teacher.getHoTen(), teacher.getGioiTinh(), teacher.getEmail(), teacher.getTenKhoa(), teacher.getHocVi(), teacher.getChucVu()});
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return;
        }

    }//GEN-LAST:event_btnAddActionPerformed

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
        SubjectDAO subjectDAO = new SubjectDAO();
        List<Subject> subjectList = subjectDAO.getAll();
        DefaultTableModel model = (DefaultTableModel) tblSubject.getModel();
        model.setRowCount(0);
        for (Subject subject : subjectList) {
//            table.addRow(new Object[]{"N20DCCN075", "Nguyễn Phước Duy Thịnh", "D20CQCN01", "Nam","Cà Mau" ,"2002","sdgwb@gmail.com",4.0});
            tblSubject.addRow(new Object[]{subject.getNameSubject(), subject.getStudent(), "", "", subject.getInstructor(), subject.getInstructorMark(), subject.getThesis_dissertation(), subject.getThesisMark(), subject.getCommittee(), subject.getCommitteeMark(), subject.getComment()});
        }
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.TextField academic;
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button button2;
    private com.raven.swing.TextField email;
    private com.raven.view.Header header1;
    private javax.swing.JComboBox<String> idDepartment;
    private com.raven.swing.TextField idTeacher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.TextField position;
    private javax.swing.JComboBox<String> sex;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblSubject;
    private com.raven.swing.TextField teacherName;
    // End of variables declaration//GEN-END:variables
}
