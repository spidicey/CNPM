package com.raven.list;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.raven.DAO.SubjectDAO;
import com.raven.conection.ConnectDatabase;
import com.raven.model.Account;
import com.raven.model.Subject;
import com.raven.swing.ScrollBar;
import com.raven.view.test;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
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
import javax.swing.table.TableModel;
import raven.cell.TableFillCellRender;
import raven.cell.TableFillEditor;
import raven.cell.TableFillEvent;

public class ListSubject extends JPanel {

    private JFrame parent;
    private int role;

    public void setFram(JFrame a) {
        this.parent = a;
    }

    public ListSubject(JFrame parent, Account acc) {
        initComponents();
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
        //  add row table
        thesisMark.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        insMark.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        committeeMark.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        this.role = acc.getRole();
        setFram(parent);
        TableFillEvent event = new TableFillEvent() {
            @Override
            public void fill(int row) {
                resetHelperText();
                TableModel model = tblSubject.getModel();
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(row, i);
//                    System.out.println(rowData[i]);
                }
                String idStudentTmp = "";
                String idInsTeacherTmp = "";
                String idThesisTeacherTmp = "";
                String idSubjectTmp = "";
                String sql = """
                             SELECT SINH_VIEN.IDSinhVien,IDGiangVienHD,IDGiangVienPhanBien,idDeTai FROM SINH_VIEN,DE_TAI,
                             (Select DISTINCT IDGiangVien FROM GIANG_VIEN,DE_TAI where GIANG_VIEN.IDGiangVien=DE_TAI.IDGiangVienHD) as IDGiangVienHD,
                             (Select DISTINCT IDGiangVien FROM GIANG_VIEN,DE_TAI where GIANG_VIEN.IDGiangVien=DE_TAI.IDGiangVienPhanBien) as IDGiangVienPhanBien
                             WHERE DE_TAI.IDSinhVien=SINH_VIEN.IDSinhVien AND IDGiangVienHD.IDGiangVien=DE_TAI.IDGiangVienHD AND IDGiangVienPhanBien.IDGiangVien=DE_TAI.IDGiangVienPhanBien
                             and TenDeTai=?""";
                try {
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, (String) rowData[0]);

                    ResultSet rs = p.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                        idStudentTmp = rs.getString(1);
                        idInsTeacherTmp = rs.getString(2);
                        idThesisTeacherTmp = rs.getString(3);
                        idSubjectTmp = rs.getString(4);
                    }
                } catch (SQLException ex) {

                }
                idSubject.setText(idSubjectTmp);
                nameSubject.setText((String) rowData[0]);
                idStudent.setText(idStudentTmp);
                idInsTeacher.setText(idInsTeacherTmp);
                idThesisTeacher.setText(idThesisTeacherTmp);
                idCommittee.setText((String) rowData[8]);
                insMark.setText(Float.toString((float) rowData[5]));
                thesisMark.setText(Float.toString((float) rowData[7]));
                committeeMark.setText(Float.toString((float) rowData[9]));
                System.out.println(row);
            }
        };
        TableDownloadUploadEvent banMemDoAn = new TableDownloadUploadEvent() {
            @Override
            public void download(int row) {
                String selectedValue = tblSubject.getModel().getValueAt(tblSubject.getSelectedRow(), 0).toString();
                Boolean flag = true;
                if (acc.getRole() > 2) {
                    String nameSql = """
                               SELECT DE_TAI.IDSinhVien from DE_TAI 
                               WHERE DE_TAI.TenDeTai=?""";
                    String idStudent = "";
                    try {
                        ConnectDatabase myConnection = new ConnectDatabase();
                        Connection conn = myConnection.openConnection();
                        PreparedStatement p = conn.prepareStatement(nameSql);
                        p.setString(1, selectedValue);
                        ResultSet r = p.executeQuery();
                        if (r.next()) {
                            idStudent = r.getString(1);
                            System.out.println(idStudent);
                            System.out.println(acc.getUserName());
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!acc.getUserName().equals(idStudent)) {
                        JOptionPane.showMessageDialog(parent, "Chọn đúng đề tài của mình để tải xuống");
                        flag = false;
                    }
                    if (!flag) {
                        return;
                    }
                }
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
                Boolean flag = true;
                if (acc.getRole() < 3) {
                    JOptionPane.showMessageDialog(parent, "Chỉ có sinh viên mới được phép nộp đề tài");
                    flag = false;
                }
                if (!flag) {
                    return;
                }
                String selectedValue = tblSubject.getModel().getValueAt(tblSubject.getSelectedRow(), 0).toString();
//                System.out.println(selectedValue);
                String nameSql = """
                               SELECT DE_TAI.IDSinhVien from DE_TAI 
                               WHERE DE_TAI.TenDeTai=?""";
                String idStudent = "";
                try {
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(nameSql);
                    p.setString(1, selectedValue);
                    ResultSet r = p.executeQuery();
                    if (r.next()) {
                        idStudent = r.getString(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!acc.getUserName().equals(idStudent)) {
                    JOptionPane.showMessageDialog(parent, "Chọn đúng đề tài của mình để tải lên");
                    flag = false;
                }
                if (!flag) {
                    return;
                }
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
                Boolean flag = true;
                if (acc.getRole() > 2) {
                    String nameSql = """
                               SELECT DE_TAI.IDSinhVien from DE_TAI 
                               WHERE DE_TAI.TenDeTai=?""";
                    String idStudent = "";
                    try {
                        ConnectDatabase myConnection = new ConnectDatabase();
                        Connection conn = myConnection.openConnection();
                        PreparedStatement p = conn.prepareStatement(nameSql);
                        p.setString(1, selectedValue);
                        ResultSet r = p.executeQuery();
                        if (r.next()) {
                            idStudent = r.getString(1);
                            System.out.println(idStudent);
                            System.out.println(acc.getUserName());
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!acc.getUserName().equals(idStudent)) {
                        JOptionPane.showMessageDialog(parent, "Chọn đúng đề tài của mình để tải xuống");
                        flag = false;
                    }
                    if (!flag) {
                        return;
                    }
                }
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
                Boolean flag = true;
                if (acc.getRole() < 3) {
                    JOptionPane.showMessageDialog(parent, "Chỉ có sinh viên mới được phép nộp đề tài");
                    flag = false;
                }
                if (!flag) {
                    return;
                }
                String selectedValue = tblSubject.getModel().getValueAt(tblSubject.getSelectedRow(), 0).toString();
                System.out.println(selectedValue);
//                System.out.println(selectedValue);
                String nameSql = """
                               SELECT DE_TAI.IDSinhVien from DE_TAI 
                               WHERE DE_TAI.TenDeTai=?""";
                String idStudent = "";
                try {
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(nameSql);
                    p.setString(1, selectedValue);
                    ResultSet r = p.executeQuery();
                    if (r.next()) {
                        idStudent = r.getString(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!acc.getUserName().equals(idStudent)) {
                    JOptionPane.showMessageDialog(parent, "Chọn đúng đề tài của mình để tải lêns");
                    flag = false;
                }
                if (!flag) {
                    return;
                }
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
        tblSubject.getColumnModel().getColumn(11).setCellRenderer(new TableFillCellRender());
        tblSubject.getColumnModel().getColumn(11).setCellEditor(new TableFillEditor(event));
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
        tblSubject = new com.raven.swing.Table();
        insMark = new com.raven.swing.TextField();
        idInsTeacher = new com.raven.swing.TextField();
        nameSubject = new com.raven.swing.TextField();
        idStudent = new com.raven.swing.TextField();
        btnSearch = new com.raven.swing.Button();
        btnReset = new com.raven.swing.Button();
        idSubject = new com.raven.swing.TextField();
        idThesisTeacher = new com.raven.swing.TextField();
        idCommittee = new com.raven.swing.TextField();
        committeeMark = new com.raven.swing.TextField();
        thesisMark = new com.raven.swing.TextField();
        filter1 = new com.raven.view.Filter();

        setBackground(new java.awt.Color(204, 255, 255));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh sách đề tài");

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

        tblSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên đề tài", "Sinh viên thực hiện", "Bản mềm đồ án", "Sourcecode", "Giảng viên hướng dẫn", "Điểm hướng dẫn", "Giảng viên phản biện", "Điểm phản biện", "Hội đồng ", "Điểm hội dồng", "Nhận xét", "Chỉnh sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false, false, false, true
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

        insMark.setLabelText("Điểm hướng dẫn");
        insMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insMarkActionPerformed(evt);
            }
        });

        idInsTeacher.setLabelText("Mã giảng viên hướng dẫn");
        idInsTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idInsTeacherActionPerformed(evt);
            }
        });

        nameSubject.setLabelText("Tên đề tài");
        nameSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameSubjectActionPerformed(evt);
            }
        });

        idStudent.setLabelText("Mã sinh viên");
        idStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idStudentActionPerformed(evt);
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

        idSubject.setLabelText("Mã đề tài");
        idSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idSubjectActionPerformed(evt);
            }
        });

        idThesisTeacher.setLabelText("Mã giảng viên phản biện");
        idThesisTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idThesisTeacherActionPerformed(evt);
            }
        });

        idCommittee.setLabelText("Mã hội đồng");
        idCommittee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCommitteeActionPerformed(evt);
            }
        });

        committeeMark.setLabelText("Điểm hội đồng");
        committeeMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                committeeMarkActionPerformed(evt);
            }
        });

        thesisMark.setLabelText("Điểm phản biện");
        thesisMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thesisMarkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(insMark, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(idInsTeacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(idSubject, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(110, 110, 110)
                                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addGap(253, 253, 253))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(idThesisTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(nameSubject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(thesisMark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(133, 133, 133)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(idStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(idCommittee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(committeeMark, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filter1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idInsTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idThesisTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idCommittee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thesisMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(committeeMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        resetHelperText();
        boolean flag = true;
        if (this.role > 2) {
            JOptionPane.showMessageDialog(this, "Không có quyền");
            flag = false;

        }
        if (!flag) {
            return;
        }
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        boolean flag = true;
        if (this.role > 2) {
            JOptionPane.showMessageDialog(this, "Không có quyền");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String idSubject = this.idSubject.getText();
        String nameSubject = this.nameSubject.getText();
        String idStudent = this.idStudent.getText();
        String idInsTeacher = this.idInsTeacher.getText();
        String idThesisTeacher = this.idThesisTeacher.getText();
        String idCommittee = this.idCommittee.getText();

        if (idSubject.trim().equals("")) {
            this.idSubject.setHelperText("Không được bỏ trống mã đề tài");
            flag = false;
        }
        if (nameSubject.trim().equals("")) {
            this.nameSubject.setHelperText("Không được bỏ trống tên đề tài");
            flag = false;
        }
        if (idStudent.trim().equals("")) {
            this.idStudent.setHelperText("Không được bỏ trống mã sinh viên");
            flag = false;
        }
        if (idInsTeacher.trim().equals("")) {
            this.idInsTeacher.setHelperText("Không được bỏ trống mã giảng viên hướng dân");
            flag = false;
        }
        if (idThesisTeacher.trim().equals("")) {
            this.idThesisTeacher.setHelperText("Không được bỏ trống mã giảng viên phản biện");
            flag = false;
        }
        if (idCommittee.trim().equals("")) {
            this.idCommittee.setHelperText("Không được bỏ trống mã hội đồng");
            flag = false;
        }
        if (!flag) {
            return;
        }
        float insMark = this.insMark.getText().equals("") ? 0 : Float.parseFloat(this.insMark.getText());
        float thesisMark = this.thesisMark.getText().equals("") ? 0 : Float.parseFloat(this.thesisMark.getText());
        float committeeMark = this.committeeMark.getText().equals("") ? 0 : Float.parseFloat(this.committeeMark.getText());
        int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = """
                             BEGIN transaction
                             UPDATE DE_TAI SET idDeTai=?,TenDeTai=?,IDGiangVienHD=?,DiemHuongDan=?,IDGiangVienPhanBien=?,DiemPhanBien=?,IDHoiDong=?,DiemHoiDong=?,IDSinhVien=?
                             WHERE idDeTai=?
                             commit""";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idSubject);
                p.setString(2, nameSubject);
                p.setString(3, idInsTeacher);
                p.setFloat(4, insMark);
                p.setString(5, idThesisTeacher);
                p.setFloat(6, thesisMark);
                p.setString(7, idCommittee);
                p.setFloat(8, committeeMark);
                p.setString(9, idStudent);
                p.setString(10, idSubject);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                DefaultTableModel model = (DefaultTableModel) tblSubject.getModel();
                model.setRowCount(0);
                SubjectDAO subjectDAO = new SubjectDAO();
                List<Subject> subjectList = subjectDAO.getAll();
                for (Subject subject : subjectList) {
                    tblSubject.addRow(new Object[]{subject.getNameSubject(), subject.getStudent(), "", "", subject.getInstructor(), subject.getInstructorMark(), subject.getThesis_dissertation(), subject.getThesisMark(), subject.getCommittee(), subject.getCommitteeMark(), subject.getComment()});
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");

            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        boolean flag = true;
        if (this.role > 2) {
            JOptionPane.showMessageDialog(this, "Không có quyền");
            flag = false;

        }
        if (!flag) {
            return;
        }
        String idSubject = this.idSubject.getText();
        String nameSubject = this.nameSubject.getText();
        String idStudent = this.idStudent.getText();
        String idInsTeacher = this.idInsTeacher.getText();
        String idThesisTeacher = this.idThesisTeacher.getText();
        String idCommittee = this.idCommittee.getText();
        if (idSubject.trim().equals("")) {
            this.idSubject.setHelperText("Không được bỏ trống mã đề tài");
            flag = false;
        }
        if (nameSubject.trim().equals("")) {
            this.nameSubject.setHelperText("Không được bỏ trống tên đề tài");
            flag = false;
        }
        if (idStudent.trim().equals("")) {
            this.idStudent.setHelperText("Không được bỏ trống mã sinh viên");
            flag = false;
        }
        if (idInsTeacher.trim().equals("")) {
            this.idInsTeacher.setHelperText("Không được bỏ trống mã giảng viên hướng dân");
            flag = false;
        }
        if (idThesisTeacher.trim().equals("")) {
            this.idThesisTeacher.setHelperText("Không được bỏ trống mã giảng viên phản biện");
            flag = false;
        }
        if (idCommittee.trim().equals("")) {
            this.idCommittee.setHelperText("Không được bỏ trống mã hội đồng");
            flag = false;
        }
        if (filter1.getString().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Phải có điểm sàn trước khi thêm ");
            flag = false;
        }
        if (!flag) {
            return;
        }
        float gpa = 0;
        try {
            String SQLgpa = "SELECT GPA from SINH_VIEN WHERE IDSinhVien=?";
            ConnectDatabase myConnection = new ConnectDatabase();
            Connection conn = myConnection.openConnection();
            PreparedStatement stmt = conn.prepareStatement(SQLgpa);
            stmt.setString(1, idStudent);
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                gpa = r.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (gpa < Float.parseFloat(this.filter1.getString())) {
            JOptionPane.showMessageDialog(this, "Không đủ điều kiện thêm đề tài");
            flag = false;
        }
        if (!flag) {
            return;
        }
        float insMark = this.insMark.getText().equals("") ? 0 : Float.parseFloat(this.insMark.getText());
        float thesisMark = this.thesisMark.getText().equals("") ? 0 : Float.parseFloat(this.thesisMark.getText());
        float committeeMark = this.committeeMark.getText().equals("") ? 0 : Float.parseFloat(this.committeeMark.getText());
        if (insMark < 0 || insMark > 10) {
            this.insMark.setHelperText("GPA không được < 0 hoặc > 10");
            return;
        }
        if (thesisMark < 0 || insMark > 10) {
            this.thesisMark.setHelperText("GPA không được < 0 hoặc > 10");
            return;
        }
        if (committeeMark < 0 || insMark > 10) {
            this.committeeMark.setHelperText("GPA không được < 0 hoặc > 10");
            return;
        }
        int responeADD = JOptionPane.showConfirmDialog(this, "Bạn có chắc thêm không??", "Thêm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (responeADD == JOptionPane.YES_OPTION) {
            try {
                String sql = """
                             BEGIN transaction
                             INSERT INTO DE_TAI (idDeTai,TenDeTai,IDGiangVienHD,DiemHuongDan,IDGiangVienPhanBien,DiemPhanBien,IDHoiDong,DiemHoiDong,IDSinhVien)
                             VALUES(?,?,?,?,?,?,?,?,?)
                             commit""";
                ConnectDatabase myConnection = new ConnectDatabase();
                Connection conn = myConnection.openConnection();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, idSubject);
                p.setString(2, nameSubject);
                p.setString(3, idInsTeacher);
                p.setFloat(4, insMark);
                p.setString(5, idThesisTeacher);
                p.setFloat(6, thesisMark);
                p.setString(7, idCommittee);
                p.setFloat(8, committeeMark);
                p.setString(9, idStudent);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Đã thêm thành công");
                DefaultTableModel model = (DefaultTableModel) tblSubject.getModel();
                model.setRowCount(0);
                SubjectDAO subjectDAO = new SubjectDAO();
                List<Subject> subjectList = subjectDAO.getAll();
                for (Subject subject : subjectList) {
                    tblSubject.addRow(new Object[]{subject.getNameSubject(), subject.getStudent(), "", "", subject.getInstructor(), subject.getInstructorMark(), subject.getThesis_dissertation(), subject.getThesisMark(), subject.getCommittee(), subject.getCommitteeMark(), subject.getComment()});
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListSubject.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } else {
            return;
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void insMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insMarkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insMarkActionPerformed

    private void idInsTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idInsTeacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idInsTeacherActionPerformed

    private void nameSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameSubjectActionPerformed

    private void idStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idStudentActionPerformed

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
    private void resetHelperText() {
        idSubject.setHelperText("");
        nameSubject.setHelperText("");
        idStudent.setHelperText("");
        idInsTeacher.setHelperText("");
        idThesisTeacher.setHelperText("");
        idCommittee.setHelperText("");
        insMark.setHelperText("");
        thesisMark.setHelperText("");
        committeeMark.setHelperText("");

    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        idSubject.setText("");
        nameSubject.setText("");
        idStudent.setText("");
        idInsTeacher.setText("");
        idThesisTeacher.setText("");
        idCommittee.setText("");
        insMark.setText("");
        thesisMark.setText("");
        committeeMark.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void idSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idSubjectActionPerformed

    private void idThesisTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idThesisTeacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idThesisTeacherActionPerformed

    private void idCommitteeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCommitteeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCommitteeActionPerformed

    private void committeeMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_committeeMarkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_committeeMarkActionPerformed

    private void thesisMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thesisMarkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thesisMarkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btnAdd;
    private com.raven.swing.Button btnDelete;
    private com.raven.swing.Button btnReset;
    private com.raven.swing.Button btnSearch;
    private com.raven.swing.Button btnUpdate;
    private com.raven.swing.TextField committeeMark;
    private com.raven.view.Filter filter1;
    private com.raven.view.Header header1;
    private com.raven.swing.TextField idCommittee;
    private com.raven.swing.TextField idInsTeacher;
    private com.raven.swing.TextField idStudent;
    private com.raven.swing.TextField idSubject;
    private com.raven.swing.TextField idThesisTeacher;
    private com.raven.swing.TextField insMark;
    private javax.swing.JLabel jLabel1;
    private com.raven.swing.TextField nameSubject;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tblSubject;
    private com.raven.swing.TextField thesisMark;
    // End of variables declaration//GEN-END:variables
}
