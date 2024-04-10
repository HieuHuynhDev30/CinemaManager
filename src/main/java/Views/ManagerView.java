/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Classes.Ghe;
import Classes.Khach;
import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import Classes.Ve;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ManagerView extends javax.swing.JFrame {

    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String[] columnNames1 = new String[]{
        "ID", "Name", "Thể Loại", "Độ tuổi", "Thời lượng", "Ngày khởi chiếu"};

    private String[] columnNames = new String[]{
        "ID", "Name", "Ngày sinh", "Giới tính", "Số lượng vé", "Tổng tiền"};

    private String[] columnNames2 = new String[]{
        "ID", "Họ tên khách", "Vị trí ghế", "Loại ghế", "Tên phim", "Thời lượng", "Phòng", "Thời gian chiếu"};
    
    private String[] columnNames3 = new String[]{
        "ID", "Vị trí ghế", "Loại ghế", "Phòng"};

    /**
     * Creates new form ManagerWindow
     */
    public ManagerView() {
        initComponents();
    }

    public ImageIcon getImage(String imagePath, JLabel label) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        return imageIcon;
    }

//    public void addToScrollBar(JPanel panel, JScrollPane scrollPane) {
//        scrollPane.add(panel);
//        scrollPane.setPreferredSize(new Dimension(this.Homepage.getWidth(), this.Homepage.getHeight()));
//    }
    public void showScrollPhimList(List<Phim> list) {
        for (Phim ph : list) {
            JPanel phimPanel = new JPanel();
            JLabel phimLabel = new JLabel();
            phimLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            phimLabel.setText(ph.getTen());
            phimLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            phimLabel.setName(""); // 
            JLabel posterLabel = new javax.swing.JLabel();
            posterLabel.setSize(200, 250);
            posterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            ImageIcon imageIcon = this.getImage(ph.getPosterLink(), posterLabel);
            posterLabel.setIcon(imageIcon);
            posterLabel.setLabelFor(phimPanel);
            posterLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            phimPanel = new javax.swing.JPanel();
            phimPanel.setAutoscrolls(true);
            phimPanel.setFocusTraversalPolicyProvider(true);
            phimPanel.setPreferredSize(new java.awt.Dimension(200, DsPhimScrollPane.getHeight()));
            phimPanel.add(phimLabel);
            phimPanel.add(posterLabel);
            DsPhimPane.add(phimPanel);
        }

    }

    public void showScrollPhongList(List<Phong> list) {
        for (Phong ph : list) {
            JPanel phongPanel = new javax.swing.JPanel();
            JLabel phongLabel = new JLabel();
            JLabel sucChuaLabel = new JLabel();
            JLabel phimChieuLabel = new JLabel();
            JLabel dangChieuLabel = new JLabel();
            JLabel gheTrongLabel = new javax.swing.JLabel();
            phongLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            phongLabel.setText(ph.getId());
            sucChuaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            sucChuaLabel.setText(ph.getSucChua() + " ghế");
            phimChieuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            phimChieuLabel.setText(ph.getSuatChieu().getPhim().getTen());
            dangChieuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            dangChieuLabel.setText(ph.getIsPlaying() ? "đang chiếu" : "đang rảnh");
            gheTrongLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gheTrongLabel.setText(ph.getDsGheTrong().size() + " ghế trống");
            phongPanel.setLayout(new java.awt.GridLayout(5, 0));
            phongPanel.add(phongLabel);
            phongPanel.add(sucChuaLabel);
            phongPanel.add(gheTrongLabel);
            phongPanel.add(phimChieuLabel);
            phongPanel.add(dangChieuLabel);
            phongPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            DsPhongPane.add(phongPanel);
        }
    }

    public void showScrollSuatList(List<SuatChieu> list) {
        for (SuatChieu ph : list) {
            JPanel suatPanel = new javax.swing.JPanel();
            JLabel posterLabel = new javax.swing.JLabel();
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new java.awt.GridLayout(3, 0));
            posterLabel.setSize(200, 250);
            posterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            ImageIcon poster = this.getImage(ph.getPhim().getPosterLink(), posterLabel);
            posterLabel.setIcon(poster);
            JLabel phim = new javax.swing.JLabel();
            phim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            phim.setText(ph.getPhim().getTen());
            JLabel thoiGian = new javax.swing.JLabel();
            thoiGian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            thoiGian.setText(ph.getThoiGianChieu().toString());
            JLabel phong = new javax.swing.JLabel();
            phong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            phong.setText(ph.getPhongId());
            suatPanel.setLayout(new java.awt.GridLayout(0, 2));
            infoPanel.add(phim);
            infoPanel.add(thoiGian);
            infoPanel.add(phong);
            suatPanel.add(posterLabel);
            suatPanel.add(infoPanel);
            DsSuatPane.add(suatPanel);
        }

    }

    public void showListSuatChieu(List<SuatChieu> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object[][] schs = new Object[size][4];
        for (int i = 0; i < size; i++) {
            schs[i][0] = list.get(i).getId();
            schs[i][1] = list.get(i).getPhim().getTen();
            schs[i][2] = list.get(i).getPhongId();
            schs[i][3] = list.get(i).getThoiGianChieu().format(formatDateTime).toString();
        }
        String[] columnNames = new String[4];
        for (int i = 0; i < 4; i++) {
            columnNames[i] = suatChieuTable.getColumnName(i);
        }
        this.suatChieuTable.setModel(new DefaultTableModel(schs, columnNames));
    }

    // Doanh thu
    public void showDoanhThu(double dt) {
        this.tongDoanhThu.setText(dt + "đ");
    }

    public void showDoanhThuPhim(List<Phim> list, List<Ve> veList) {
        for (Phim ph : list) {
            JPanel dtPhimPanel = new javax.swing.JPanel();
            dtPhimPanel.setLayout(new java.awt.GridLayout(1, 2));
            JPanel infoPhimPanel = new javax.swing.JPanel();
            JLabel posterLabel = new javax.swing.JLabel();
            infoPhimPanel.setLayout(new java.awt.GridLayout(3, 1));
            JLabel ten = new javax.swing.JLabel();
            JLabel slve = new javax.swing.JLabel();
            JLabel dt = new javax.swing.JLabel();

            posterLabel.setSize(125, 175);
            ImageIcon imageIcon = this.getImage(ph.getPosterLink(), posterLabel);
            posterLabel.setIcon(imageIcon);
            posterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            ten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            ten.setText(ph.getTen());

            int tongVe = 0;
            int tongDt = 0;
            for (Ve ve : veList) {
                if (ve.getSuat().getPhim().getId() == null ? ph.getId() == null : ve.getSuat().getPhim().getId().equals(ph.getId())) {
                    tongVe++;
                    tongDt += ph.getDt();
                    System.out.println(ph.getDt());
                }
            }
            slve.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            slve.setText(Integer.toString(tongVe));

            dt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            dt.setText(tongDt + "đ");

            infoPhimPanel.add(ten);
            infoPhimPanel.add(slve);
            infoPhimPanel.add(dt);
            dtPhimPanel.add(posterLabel);
            dtPhimPanel.add(infoPhimPanel);
            this.doanhThuPhim.add(dtPhimPanel);
        }
    }

    public void clearDoanhThuPhim() {
        this.doanhThuPhim.removeAll();
    }

    // ket thuc Doanh thu
    public void showSuatChieu(SuatChieu sch) {
        this.IdField.setText("" + sch.getId());
        this.phimCombo.setSelectedItem(sch.getPhim().getTen());
        this.phongCombo.setSelectedItem(sch.getPhong().getId());
        this.tgChieuField.setText(sch.getThoiGianChieu().format(formatDateTime));
        // enable Edit and Delete buttons
        editButton.setEnabled(true);
        deleteButton.setEnabled(true);
//        // disable Add button
        addButton.setEnabled(false);
    }

    public void setSuatChieuCombo(List<Phim> phimList, List<Phong> phongList) {
        String[] phimStr = new String[phimList.size()];
        for (int i = 0; i < phimStr.length; i++) {
            phimStr[i] = phimList.get(i).getTen();
        }
        phimCombo.setModel(new javax.swing.DefaultComboBoxModel<>(phimStr));
//        quanLySuatChieu.add(phimCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 160, 20));

        String[] phongStr = new String[phongList.size()];
        for (int i = 0; i < phongStr.length; i++) {
            phongStr[i] = phongList.get(i).getId();
        }
        phongCombo.setModel(new javax.swing.DefaultComboBoxModel<>(phongStr));
//        quanLySuatChieu.add(phongCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 150, -1));
//        String[] tieuChiStr = {}
        tieuChiSchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Id", "soluongve"}));
    }

    public void fillSuatChieuFromSelectedRow(List<Phim> phimList) {
        // lấy chỉ số của hàng được chọn 
        int row = this.suatChieuTable.getSelectedRow();
        if (row >= 0) {
            this.IdField.setText(suatChieuTable.getModel().getValueAt(row, 0).toString());
            this.phimCombo.setSelectedItem(suatChieuTable.getModel().getValueAt(row, 1).toString());
            this.phongCombo.setSelectedItem(suatChieuTable.getModel().getValueAt(row, 2).toString());
            this.tgChieuField.setText(suatChieuTable.getModel().getValueAt(row, 3).toString());
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
            addButton.setEnabled(false);
        }
    }

    public SuatChieu getSchInfo(List<Phim> phimList, List<Phong> phongList) {
        // validate student
//        if (!validateName() || !validateAge() || !validateAddress() || !validateGPA()) {
//            return null;
//        }
        try {
            SuatChieu sch = new SuatChieu();
            sch.setId(this.IdField.getText());
            for (Phim ph : phimList) {
                if (this.phimCombo.getSelectedItem().toString() == null ? ph.getTen() == null : this.phimCombo.getSelectedItem().toString().equals(ph.getTen())) {
                    sch.setPhim(ph);
                }
            }
            for (Phong ph : phongList) {
                if (this.phongCombo.getSelectedItem().toString() == null ? ph.getId() == null : this.phongCombo.getSelectedItem().toString().equals(ph.getId())) {
                    sch.setPhong(ph);
                    sch.setPhongId();
                }
            }
            sch.setThoiGianChieu(this.tgChieuField.getText());
            return sch;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public void clearSuatChieuInfo() {
        this.IdField.setText("");
        this.phimCombo.setSelectedIndex(0);
        this.phimCombo.setSelectedIndex(0);
        this.tgChieuField.setText("");
        // disable Edit and Delete buttons
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        // enable Add button
        addButton.setEnabled(true);
    }

    public String getTieuChiSxSch() {
        return this.tieuChiSchCombo.getSelectedItem().toString();
    }

    public JPanel getDsPhimPane() {
        return DsPhimPane;
    }

    public void setDsPhimPane(JPanel DsPhimPane) {
        this.DsPhimPane = DsPhimPane;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public boolean getSchTangDan() {
        return this.tangDan.isSelected();
    }

    public boolean getDtPhimTangDan() {
        return this.tieuChiDtPhim.isSelected();
    }

    // Phim method cua An
    public void showListPhim(List<Phim> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object[][] phim = new Object[size][6];
        for (int i = 0; i < size; i++) {
            phim[i][0] = list.get(i).getId();
            phim[i][1] = list.get(i).getTen();
            phim[i][2] = list.get(i).getTheLoai();
            phim[i][3] = list.get(i).getDoTuoi();
            phim[i][4] = list.get(i).inThoiLuong();
            phim[i][5] = list.get(i).inTgKhoiChieu();

        }
        BangPhim.setModel(new DefaultTableModel(phim, columnNames1));
    }

    public void fillPhimFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = BangPhim.getSelectedRow();
        if (row >= 0) {

            IDPhimField.setText(BangPhim.getModel().getValueAt(row, 0).toString());
            TenPhimField.setText(BangPhim.getModel().getValueAt(row, 1).toString());
            TheLoaiField.setText(BangPhim.getModel().getValueAt(row, 2).toString());
            DoTuoiField.setText(BangPhim.getModel().getValueAt(row, 3).toString());
            ThoiLuongField.setText(BangPhim.getModel().getValueAt(row, 4).toString());
            NgayKhoiChieuField.setText(BangPhim.getModel().getValueAt(row, 5).toString());
            // enable Edit and Delete buttons
            EditPhim.setEnabled(true);
            DeletePhim.setEnabled(true);
            // disable Add button
            AddPhim.setEnabled(false);
        }
    }

    public Phim getPhimInfo() {
        // validate student
//        if (!validateID() || !validateAge() || !validateAddress() || !validateGPA()) {
//            return null;
//        }
//        if (!validateName() || !validateGioiTinh() || !validateSLV()){
//            return null;
//        }
        try {
            Phim phim = new Phim();
            if (TenPhimField.getText() != null) {

            }
            if (IDPhimField.getText() != null && !"".equals(IDPhimField.getText())) {
                phim.setId(Integer.parseInt(IDPhimField.getText().trim()));
            }

            phim.setTen(TenPhimField.getText().trim());
            phim.setTheLoai(TheLoaiField.getText().trim());
            phim.setDoTuoi(Byte.parseByte(DoTuoiField.getText().trim()));
            phim.setThoiLuong(Integer.parseInt(ThoiLuongField.getText().trim()));
            phim.setTgKhoiChieu(NgayKhoiChieuField.getText().trim());
            //   

            return phim;
        } catch (NumberFormatException e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public void showPhim(Phim phim) {
        IDPhimField.setText("" + phim.getId());
        TenPhimField.setText("" + phim.getTen());
        DoTuoiField.setText("" + phim.getDoTuoi());
        TheLoaiField.setText("" + phim.getTheLoai());
        ThoiLuongField.setText("" + phim.inThoiLuong());
        NgayKhoiChieuField.setText("" + phim.inTgKhoiChieu());
//        gpaField.setText("" + student.getGpa()); 

        // enable Edit and Delete buttons
        //  editStudentBtn.setEnabled(true);
        // deleteStudentBtn.setEnabled(true);
        // disable Add button
        AddPhim.setEnabled(false);
    }

    public void clearPhimInfo() {
        IDPhimField.setText("");
        TenPhimField.setText("");
        DoTuoiField.setText("");
        TheLoaiField.setText("");

        ThoiLuongField.setText("");
        NgayKhoiChieuField.setText("");
        // disable Edit and Delete buttons
        // editStudentBtn.setEnabled(false);
        // deleteStudentBtn.setEnabled(false);
        // enable Add button
        AddPhim.setEnabled(true);
    }

    public int luachonPhimTK() {
        if (TimKiemPhimField.getText().equals("") || TimKiemPhimField.getText() == null) {
            return -1;
        }
        int k;
        k = LuaChonPhim.getSelectedIndex();
        return k;
    }

    public String inforPhimSearch() {
        String s = TimKiemPhimField.getText().trim();

        return s;
    }

// Khach method cua An
/// Thao tac voi khach hang
    public void showListKhach(List<Khach> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object[][] khach = new Object[size][6];
        for (int i = 0; i < size; i++) {
            khach[i][0] = list.get(i).getId();
            khach[i][1] = list.get(i).getHoTen();
            khach[i][2] = list.get(i).getNgaySinh();
            khach[i][3] = list.get(i).getGioiTinh();
            khach[i][4] = list.get(i).getSlVeDat();
            khach[i][5] = list.get(i).getTongTien();

        }
        BangKhachHang.setModel(new DefaultTableModel(khach, columnNames));
    }

    //
    public void fillKhachFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = BangKhachHang.getSelectedRow();
        if (row >= 0) {
            IDField.setText(BangKhachHang.getModel().getValueAt(row, 0).toString());
            HoTenField.setText(BangKhachHang.getModel().getValueAt(row, 1).toString());
            NgaySinhField.setText(BangKhachHang.getModel().getValueAt(row, 2).toString());
            GioiTinhField.setText(BangKhachHang.getModel().getValueAt(row, 3).toString());
            SlvField.setText(BangKhachHang.getModel().getValueAt(row, 4).toString());
            // enable Edit and Delete buttons
            EditKhach.setEnabled(true);
            DeleteKhach.setEnabled(true);
            // disable Add button
            AddKhach.setEnabled(false);
        }
    }

    public Khach getKhachInfo() {
        // validate student
//        if (!validateID() || !validateAge() || !validateAddress() || !validateGPA()) {
//            return null;
//        }
        if (!validateName() || !validateGioiTinh() || !validateSLV()) {
            return null;
        }
        try {
            Khach khach = new Khach();
            if (HoTenField.getText() != null) {

            }
            if (IDField.getText() != null && !"".equals(IDField.getText())) {
                khach.setId(IDField.getText().trim());
            }

            khach.setHoTen(HoTenField.getText().trim());
            khach.setGioiTinh(GioiTinhField.getText().trim());
            khach.setNgaySinh(NgaySinhField.getText().trim());
            khach.setSlVeDat(Byte.parseByte(SlvField.getText().trim()));

            return khach;
        } catch (NumberFormatException e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public void showKhach(Khach khach) {
        IDField.setText("" + khach.getId());
        HoTenField.setText("" + khach.getHoTen());
        GioiTinhField.setText("" + khach.getGioiTinh());
        NgaySinhField.setText("" + khach.getNgaySinh());
        SlvField.setText("" + khach.getSlVeDat());
//        gpaField.setText("" + student.getGpa()); 

        // enable Edit and Delete buttons
        EditKhach.setEnabled(true);
        DeleteKhach.setEnabled(true);
        // disable Add button
        AddKhach.setEnabled(false);
    }

    /**
     * xóa thông tin student
     */
    public void clearKhachInfo() {
        IDField.setText("");
        HoTenField.setText("");
        GioiTinhField.setText("");
        NgaySinhField.setText("");

        SlvField.setText("");
        // disable Edit and Delete buttons
        EditKhach.setEnabled(false);
        DeleteKhach.setEnabled(false);
        // enable Add button
        AddKhach.setEnabled(true);
    }

    private boolean validateName() {
        String name = HoTenField.getText();
        if (name == null || "".equals(name.trim())) {
            HoTenField.requestFocus();
            showMessage("Name không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateGioiTinh() {
        String gioitinh = GioiTinhField.getText();
        if (gioitinh == null || "".equals(gioitinh.trim())) {
            GioiTinhField.requestFocus();
            showMessage("Gioi tinh không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateSLV() {
        try {
            Byte slv = Byte.parseByte(SlvField.getText().trim());
            if (slv < 0 || slv > 10) {
                SlvField.requestFocus();
                showMessage("số lượng vé không hợp lệ, age nên trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            SlvField.requestFocus();
            showMessage("Số lượng vé không hợp lệ!");
            return false;
        }
        return true;
    }

    public int luachonKhachTK() {
        if (TimKiemKhachField.getText().equals("") || TimKiemKhachField.getText() == null) {
            return -1;
        }
        int k;
        k = LuaChonKhach.getSelectedIndex();
        return k;
    }

    public String inforKhachSearch() {
        String s = TimKiemKhachField.getText().trim();

        return s;
    }

    // Method Ve cua An
    public void showListVe(List<Ve> list, List<Khach> khachs) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5

        Object[][] ve = new Object[size][8];
        for (int i = 0; i < size; i++) {
            ve[i][0] = list.get(i).getId();
            for (Khach kh : khachs) {
                if (kh.getId() == null ? list.get(i).getGhe().getKhachId() == null : kh.getId().equals(list.get(i).getGhe().getKhachId())) {
                    ve[i][1] = kh.getHoTen();
                    break;
                }
            }
            ve[i][2] = list.get(i).getGhe().InVitri();
            ve[i][3] = list.get(i).getGhe().getLoai();
            ve[i][4] = list.get(i).getSuat().getPhim().getTen();
            ve[i][5] = list.get(i).getSuat().getPhim().inThoiLuong();
            ve[i][6] = list.get(i).getSuat().getPhongId();
            ve[i][7] = list.get(i).getSuat().inThoiGianChieu();

        }
        BangVe.setModel(new DefaultTableModel(ve, columnNames2));
    }
    //

    // Khach addListener
    public void addAddKhachListener(ActionListener listener) {
        AddKhach.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearKhach.addActionListener(listener);
    }

    public void addListKhachSelectionListener(ListSelectionListener listener) {
        BangKhachHang.getSelectionModel().addListSelectionListener(listener);
    }

    public void addDeleteKhachListener(ActionListener listener) {
        DeleteKhach.addActionListener(listener);
    }

    public void addEditKhachListener(ActionListener listener) {
        EditKhach.addActionListener(listener);
    }

    // ket thuc
// ketthuc    
    public void addAddPhimListener(ActionListener listener) {
        AddPhim.addActionListener(listener);
    }

    public void addClearPhimListener(ActionListener listener) {
        ClearPhim.addActionListener(listener);
    }

    public void addListPhimSelectionListener(ListSelectionListener listener) {
        BangPhim.getSelectionModel().addListSelectionListener(listener);
    }

    public void addDeletePhimListener(ActionListener listener) {
        DeletePhim.addActionListener(listener);
    }

    public void addEditPhimListener(ActionListener listener) {
        EditPhim.addActionListener(listener);
    }

    public void addSearchPhimListener(ActionListener listener) {
        TimKiemBtn.addActionListener(listener);
    }

    public void addSearchKhachListener(ActionListener listener) {
        TimKhachbtn.addActionListener(listener);
    }

    // Het phim method
    
    // Ve method
    public void setVeCombo(List<SuatChieu> suatchieuList, List<Phong> phongList, List<Khach> khachList) {
        String[] khachIDStr = new String[khachList.size()];
        String[] phimStr = new String[suatchieuList.size()];
        String[] phongStr = new String[phongList.size()];
        
        String[] thoiluongStr = new String[phongList.size()];
        String[] thoigianchieuStr = new String[suatchieuList.size()];
        
        for (int i = 0; i < khachIDStr.length; i++) {
            khachIDStr[i] = khachList.get(i).getHoTen();
        }
        for (int i = 0; i < phimStr.length; i++) {
            phimStr[i] = suatchieuList.get(i).getPhim().getTen();
        }

        for (int i = 0; i < phongStr.length; i++) {
            phongStr[i] = phongList.get(i).getId();
            
        }
        for (int i = 0; i < phongStr.length; i++) {
            thoiluongStr[i] ="" + phongList.get(i).getSuatChieu().getPhim().inThoiLuong();
            
        }
        for (int i = 0; i < thoigianchieuStr.length; i++) {
            thoigianchieuStr[i] =suatchieuList.get(i).inThoiGianChieu();
            
        }
        
        
  
        ListKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(khachIDStr));
        phimCombo1.setModel(new javax.swing.DefaultComboBoxModel<>(phimStr));
        
//        quanLySuatChieu.add(phimCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 160, 20));

      
        
       phongCombo1.setModel(new javax.swing.DefaultComboBoxModel<>(phongStr));
        //ListViTri.setMaximumRowCount(100);
   //     ListViTri.setModel(new javax.swing.DefaultComboBoxModel<>(gheStr));
//        ListThoiLuong.setModel(new javax.swing.DefaultComboBoxModel<>(thoiluongStr));
 //       ListThoiGianChieu.setModel(new javax.swing.DefaultComboBoxModel<>(thoigianchieuStr));
//        quanLySuatChieu.add(phongCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 150, -1));
//        String[] tieuChiStr = {}
        //tieuChiSchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Id", "soluongve"}));
    }
    
//    public SuatChieu getSch1Info(List<Phim> phimList, List<Phong> phongList) {
//        // validate student
////        if (!validateName() || !validateAge() || !validateAddress() || !validateGPA()) {
////            return null;
////        }
//        try {
//            SuatChieu sch = new SuatChieu();
//            //sch.setId(this.IdField.getText());
//            for (Phim ph : phimList) {
//                if (this.ListPhim.getSelectedItem().toString() == null ? ph.getTen() == null : this.ListPhim.getSelectedItem().toString().equals(ph.getTen())) {
//                    sch.setPhim(ph);
//                }
//            }
//            for (Phong ph : phongList) {
//                if (this.ListPhong.getSelectedItem().toString() == null ? ph.getId() == null : this.ListPhong.getSelectedItem().toString().equals(ph.getId())) {
//                    sch.setPhong(ph);
//                    sch.setPhongId();
//                }
//            }
//          sch.setThoiGianChieu(ListThoiGianChieu.getSelectedItem().toString());
//            return sch;
//        } catch (Exception e) {
//            showMessage(e.getMessage());
//        }
//        return null;
//    }
    
    public Khach getKhachInfor(List<Khach> khachs){
        try{
           Khach khach = new Khach();
           khach.setHoTen(ListKhachHang.getSelectedItem().toString());
           for (Khach kh : khachs){
               if(kh.getHoTen().equals(khach.getHoTen())){
                   return kh;
                   
               }
           }
           
        }catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    public Ghe getGheInfor(Phong phong){
        try{
           List<Ghe> list = phong.getDsGheTrong();
        
           Ghe ghe = new Ghe();
           ghe.setViTri(ViTriField.getText());
           
           for (Ghe gh : list){
               if(ghe.InVitri().equals(gh.InVitri())){
                   return gh;
                   
               }
           }
           
        }catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    public void showListGhe(Phong phong) {
        List<Ghe> list = new ArrayList<>();
        list = phong.getDsGheTrong();
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5

        Object[][] ghe = new Object[size][4];
        for (int i = 0; i < size; i++) {
          //  ghe[i][0] = list.get(i).getKhachId();
            
                
            ghe[i][1] = list.get(i).InVitri();
                    
             
           
            ghe[i][2] = list.get(i).getLoai();
            ghe[i][3] = phong.getId();
            

        }
        BangGhe.setModel(new DefaultTableModel(ghe, columnNames3));
    }
    
    
    public void addAddVeListener(ActionListener listener) {
        AddVe.addActionListener(listener);
    }
    
     public void addDatVeListener(ActionListener listener) {
        DatVebtn.addActionListener(listener);
    }
     
     
     public void showListSuatChieu1(List<SuatChieu> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object[][] schs = new Object[size][4];
        for (int i = 0; i < size; i++) {
            schs[i][0] = list.get(i).getId();
            schs[i][1] = list.get(i).getPhim().getTen();
            schs[i][2] = list.get(i).getPhongId();
            schs[i][3] = list.get(i).getThoiGianChieu().format(formatDateTime).toString();
        }
        String[] columnNames = new String[4];
        for (int i = 0; i < 4; i++) {
            columnNames[i] = suatChieuTable2.getColumnName(i);
        }
        this.suatChieuTable2.setModel(new DefaultTableModel(schs, columnNames));
    }
     
     public void showSuatChieu1(SuatChieu sch) {
        this.IdField1.setText("" + sch.getId());
        this.phimCombo1.setSelectedItem(sch.getPhim().getTen());
        this.phongCombo1.setSelectedItem(sch.getPhong().getId());
        this.tgChieuField1.setText(sch.getThoiGianChieu().format(formatDateTime));
        // enable Edit and Delete buttons

        
//        // disable Add button
  
    }

   

    public void fillSuatChieuFromSelectedRow1(List<Phim> phimList) {
        // lấy chỉ số của hàng được chọn 
        int row = this.suatChieuTable2.getSelectedRow();
        if (row >= 0) {
            this.IdField1.setText(suatChieuTable2.getModel().getValueAt(row, 0).toString());
            this.phimCombo1.setSelectedItem(suatChieuTable2.getModel().getValueAt(row, 1).toString());
            this.phongCombo1.setSelectedItem(suatChieuTable2.getModel().getValueAt(row, 2).toString());
            this.tgChieuField1.setText(suatChieuTable2.getModel().getValueAt(row, 3).toString());
        
            
           
        }
    }
    
      public SuatChieu getSch1Info(List<SuatChieu> list) {
        // validate student
//        if (!validateName() || !validateAge() || !validateAddress() || !validateGPA()) {
//            return null;
//        }
        try {
            SuatChieu sch = new SuatChieu();
            sch.setId(this.IdField1.getText());
            for (SuatChieu sc : list){
                if (sch.getId().equals(sc.getId())) {
                   return sc;
                }
            }
//            for (Phim ph : phimList) {
//                if (this.phimCombo1.getSelectedItem().toString() == null ? ph.getTen() == null : this.phimCombo1.getSelectedItem().toString().equals(ph.getTen())) {
//                    sch.setPhim(ph);
//                }
//            }
//            for (Phong ph : phongList) {
//                if (this.phongCombo1.getSelectedItem().toString() == null ? ph.getId() == null : this.phongCombo1.getSelectedItem().toString().equals(ph.getId())) {
//                    sch.setPhong(ph);
//                    sch.setPhongId();
//                }
//            }
//            sch.setThoiGianChieu(this.tgChieuField1.getText());
            return sch;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

//    public SuatChieu getSch1Info(List<Phim> phimList, List<Phong> phongList) {
//        // validate student
////        if (!validateName() || !validateAge() || !validateAddress() || !validateGPA()) {
////            return null;
////        }
//        try {
//            SuatChieu sch = new SuatChieu();
//            sch.setId(this.IdField1.getText());
//            for (Phim ph : phimList) {
//                if (this.phimCombo1.getSelectedItem().toString() == null ? ph.getTen() == null : this.phimCombo1.getSelectedItem().toString().equals(ph.getTen())) {
//                    sch.setPhim(ph);
//                }
//            }
//            for (Phong ph : phongList) {
//                if (this.phongCombo1.getSelectedItem().toString() == null ? ph.getId() == null : this.phongCombo1.getSelectedItem().toString().equals(ph.getId())) {
//                    sch.setPhong(ph);
//                    sch.setPhongId();
//                }
//            }
//            sch.setThoiGianChieu(this.tgChieuField1.getText());
//            return sch;
//        } catch (Exception e) {
//            showMessage(e.getMessage());
//        }
//        return null;
//    }

    public void clearSuatChieuInfo1() {
        this.IdField1.setText("");
        this.phimCombo1.setSelectedIndex(0);
        this.phimCombo1.setSelectedIndex(0);
        this.tgChieuField1.setText("");
        this.ViTriField.setText("");
        // disable Edit and Delete buttons
   
        
        // enable Add button
     
    }
    public void fillGheFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = BangGhe.getSelectedRow();
        if (row >= 0) {
              ViTriField.setText(BangGhe.getModel().getValueAt(row, 1).toString());
        }
    }
    
    
    
    


    // Het ve method
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        Homepage = new javax.swing.JLayeredPane();
        HomepageTitle = new javax.swing.JLabel();
        DsPhimScrollPane = new javax.swing.JScrollPane();
        DsPhimPane = new javax.swing.JPanel();
        DsSuatScrollPane = new javax.swing.JScrollPane();
        DsSuatPane = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        DsPhongScrollPane = new javax.swing.JScrollPane();
        DsPhongPane = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        quanLySuatChieu = new javax.swing.JLayeredPane();
        SuatChieuManager = new javax.swing.JLabel();
        suatChieuScroll = new javax.swing.JScrollPane();
        suatChieuTable = new javax.swing.JTable();
        Id = new javax.swing.JLabel();
        IdField = new javax.swing.JTextField();
        phimChieu = new javax.swing.JLabel();
        phongChieu = new javax.swing.JLabel();
        tgChieu = new javax.swing.JLabel();
        tgChieuField = new javax.swing.JTextField();
        phimCombo = new javax.swing.JComboBox<>();
        phongCombo = new javax.swing.JComboBox<>();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        sortButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tieuChiSchCombo = new javax.swing.JComboBox<>();
        tangDan = new javax.swing.JCheckBox();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel4 = new javax.swing.JPanel();
        tongDoanhThu = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        doanhThuPhim = new javax.swing.JPanel();
        sortDtPhimButton = new javax.swing.JButton();
        tieuChiDtPhim = new javax.swing.JCheckBox();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        Qlphim = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        TimKiemPhimField = new javax.swing.JTextField();
        LuaChonPhim = new javax.swing.JComboBox<>();
        TimKiemBtn = new javax.swing.JButton();
        IDPhimField = new javax.swing.JTextField();
        ThoiLuongField = new javax.swing.JTextField();
        DoTuoiField = new javax.swing.JTextField();
        TheLoaiField = new javax.swing.JTextField();
        TenPhimField = new javax.swing.JTextField();
        AddPhim = new javax.swing.JButton();
        ClearPhim = new javax.swing.JButton();
        DeletePhim = new javax.swing.JButton();
        EditPhim = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        NgayKhoiChieuField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        BangPhim = new javax.swing.JTable();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        QlKhachHang = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        LuaChonKhach = new javax.swing.JComboBox<>();
        TimKiemKhachField = new javax.swing.JTextField();
        TimKhachbtn = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        HoTenField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NgaySinhField = new javax.swing.JTextField();
        GioiTinhField = new javax.swing.JTextField();
        AddKhach = new javax.swing.JButton();
        EditKhach = new javax.swing.JButton();
        DeleteKhach = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        SlvField = new javax.swing.JTextField();
        clearKhach = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        IDField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        BangKhachHang = new javax.swing.JTable();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        QlDatVe = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        BangVe = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        ChonSuatChieu = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        AddVe = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        ListKhachHang = new javax.swing.JComboBox<>();
        phongCombo1 = new javax.swing.JComboBox<>();
        phimCombo1 = new javax.swing.JComboBox<>();
        tgChieuField1 = new javax.swing.JTextField();
        Id1 = new javax.swing.JLabel();
        IdField1 = new javax.swing.JTextField();
        quanLySuatChieu1 = new javax.swing.JLayeredPane();
        SuatChieuManager1 = new javax.swing.JLabel();
        suatChieuScroll2 = new javax.swing.JScrollPane();
        suatChieuTable2 = new javax.swing.JTable();
        ChonGhe = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        DatVebtn = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        ViTriField = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        BangGhe = new javax.swing.JTable();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jTabbedPane5.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane5.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        Homepage.setAutoscrolls(true);
        Homepage.setPreferredSize(new java.awt.Dimension(528, 10));

        HomepageTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        HomepageTitle.setText("CINEVERSE CINEMA");
        HomepageTitle.setDisplayedMnemonicIndex(5);

        DsPhimScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phim", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        DsPhimScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        DsPhimScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        DsPhimScrollPane.setAlignmentX(0.0F);
        DsPhimScrollPane.setAlignmentY(0.0F);
        DsPhimScrollPane.setAutoscrolls(true);
        DsPhimScrollPane.setName(""); // NOI18N

        DsPhimPane.setAlignmentX(0.0F);
        DsPhimPane.setAlignmentY(0.0F);
        DsPhimPane.setAutoscrolls(true);
        DsPhimPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DsPhimPane.setLayout(new java.awt.GridLayout(1, 0));
        DsPhimScrollPane.setViewportView(DsPhimPane);

        DsSuatScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách suất chiếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        DsSuatScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        DsSuatScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        DsSuatScrollPane.setAlignmentX(0.0F);
        DsSuatScrollPane.setAlignmentY(0.0F);
        DsSuatScrollPane.setAutoscrolls(true);
        DsSuatScrollPane.setName(""); // NOI18N

        DsSuatPane.setAlignmentX(0.0F);
        DsSuatPane.setAlignmentY(0.0F);
        DsSuatPane.setAutoscrolls(true);
        DsSuatPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DsSuatPane.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setLayout(new java.awt.GridLayout(0, 2));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2);

        jPanel3.setLayout(new java.awt.GridLayout(3, 0));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("jLabel5");
        jPanel3.add(jLabel5);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("jLabel4");
        jPanel3.add(jLabel4);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");
        jPanel3.add(jLabel3);

        jPanel2.add(jPanel3);

        DsSuatPane.add(jPanel2);

        DsSuatScrollPane.setViewportView(DsSuatPane);

        DsPhongScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        DsPhongScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        DsPhongScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        DsPhongScrollPane.setAlignmentX(0.0F);
        DsPhongScrollPane.setAlignmentY(0.0F);
        DsPhongScrollPane.setAutoscrolls(true);
        DsPhongScrollPane.setName(""); // NOI18N

        DsPhongPane.setLayout(new java.awt.GridLayout(1, 0));
        DsPhongScrollPane.setViewportView(DsPhongPane);

        Homepage.setLayer(HomepageTitle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Homepage.setLayer(DsPhimScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Homepage.setLayer(DsSuatScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Homepage.setLayer(DsPhongScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout HomepageLayout = new javax.swing.GroupLayout(Homepage);
        Homepage.setLayout(HomepageLayout);
        HomepageLayout.setHorizontalGroup(
            HomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomepageLayout.createSequentialGroup()
                .addGroup(HomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomepageLayout.createSequentialGroup()
                        .addComponent(HomepageTitle)
                        .addGap(0, 719, Short.MAX_VALUE))
                    .addComponent(DsPhimScrollPane)
                    .addComponent(DsSuatScrollPane)
                    .addComponent(DsPhongScrollPane))
                .addContainerGap())
        );
        HomepageLayout.setVerticalGroup(
            HomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomepageLayout.createSequentialGroup()
                .addComponent(HomepageTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsPhimScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsSuatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsPhongScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("", Homepage);

        jTabbedPane1.addTab("Trang chủ", null, jTabbedPane5, "");

        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        quanLySuatChieu.setAutoscrolls(true);
        quanLySuatChieu.setPreferredSize(new java.awt.Dimension(528, 10));
        quanLySuatChieu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SuatChieuManager.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SuatChieuManager.setText("Quản lý suất chiếu");
        quanLySuatChieu.add(SuatChieuManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 40));

        suatChieuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Phim", "Phòng", "Thời gian chiếu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        suatChieuTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suatChieuTableMouseClicked(evt);
            }
        });
        suatChieuScroll.setViewportView(suatChieuTable);

        quanLySuatChieu.add(suatChieuScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 530, 810));

        Id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Id.setLabelFor(IdField);
        Id.setText("Id suất chiếu");
        quanLySuatChieu.add(Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        IdField.setEditable(false);
        IdField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        quanLySuatChieu.add(IdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 150, -1));

        phimChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phimChieu.setLabelFor(phimCombo);
        phimChieu.setText("Phim được chiếu");
        quanLySuatChieu.add(phimChieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, -1, 20));

        phongChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phongChieu.setLabelFor(phongCombo);
        phongChieu.setText("Phòng");
        quanLySuatChieu.add(phongChieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 80, -1));

        tgChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tgChieu.setLabelFor(tgChieu);
        tgChieu.setText("Thời gian chiếu");
        quanLySuatChieu.add(tgChieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, -1, -1));
        quanLySuatChieu.add(tgChieuField, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, 190, 30));

        phimCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        phimCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phimComboActionPerformed(evt);
            }
        });
        quanLySuatChieu.add(phimCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, 190, 30));

        phongCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phongCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        quanLySuatChieu.add(phongCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 150, -1));

        editButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editButton.setText("Cập nhật");
        quanLySuatChieu.add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, -1, -1));

        addButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addButton.setText("Thêm");
        quanLySuatChieu.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, -1, -1));

        clearButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        clearButton.setText("Làm trống");
        quanLySuatChieu.add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, -1, -1));

        deleteButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        deleteButton.setText("Xóa");
        quanLySuatChieu.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, -1, -1));

        sortButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sortButton.setText("Sắp xếp");
        quanLySuatChieu.add(sortButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tiêu chí");
        quanLySuatChieu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 90, 40));

        tieuChiSchCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tieuChiSchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        quanLySuatChieu.add(tieuChiSchCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 100, 30));

        tangDan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tangDan.setText("Tăng dần ");
        quanLySuatChieu.add(tangDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, -1, -1));

        jTabbedPane4.addTab("", quanLySuatChieu);

        jTabbedPane1.addTab("Quản lý suất chiếu", jTabbedPane4);

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jLayeredPane1.setLayout(new java.awt.GridLayout(4, 0));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng doanh thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tongDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tongDoanhThu.setText("jLabel6");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(801, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanel4);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo phim", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        doanhThuPhim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        doanhThuPhim.setToolTipText("");
        doanhThuPhim.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane1.setViewportView(doanhThuPhim);

        sortDtPhimButton.setText("Sắp xếp");

        tieuChiDtPhim.setText("Tăng dần");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortDtPhimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tieuChiDtPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(749, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortDtPhimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tieuChiDtPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLayeredPane1.add(jPanel6);

        jTabbedPane2.addTab("", jLayeredPane1);

        jTabbedPane1.addTab("Doanh thu", jTabbedPane2);

        Qlphim.setBackground(new java.awt.Color(204, 204, 204));
        Qlphim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QlphimMouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(247, 247, 247));
        jPanel5.setForeground(new java.awt.Color(204, 204, 204));

        TimKiemPhimField.setText("Tìm kiếm ");
        TimKiemPhimField.setDisabledTextColor(new java.awt.Color(204, 204, 204));

        LuaChonPhim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên Phim", "Thể loại", "Độ tuổi" }));

        TimKiemBtn.setBackground(new java.awt.Color(196, 23, 23));
        TimKiemBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        TimKiemBtn.setForeground(new java.awt.Color(255, 255, 255));
        TimKiemBtn.setText("Tìm kiếm");

        AddPhim.setText("Add");

        ClearPhim.setText("Clear");

        DeletePhim.setText("Delete");

        EditPhim.setText("Edit");

        jLabel13.setText("ID");

        jLabel14.setText("Thời lượng");

        jLabel15.setText("Tên phim");

        jLabel16.setText("Thể loại");

        jLabel17.setText("Độ tuổi");

        jLabel18.setText("Ngày khởi chiếu");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(75, 75, 75)
                                    .addComponent(AddPhim)
                                    .addGap(18, 18, 18)
                                    .addComponent(EditPhim)
                                    .addGap(18, 18, 18)
                                    .addComponent(DeletePhim))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TenPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ThoiLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(IDPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(11, 11, 11)))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ClearPhim))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)))))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NgayKhoiChieuField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TheLoaiField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DoTuoiField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LuaChonPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimKiemPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(TimKiemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TimKiemPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IDPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(TheLoaiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LuaChonPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DoTuoiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(TenPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimKiemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ThoiLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(NgayKhoiChieuField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPhim)
                    .addComponent(DeletePhim)
                    .addComponent(EditPhim)
                    .addComponent(ClearPhim))
                .addGap(62, 62, 62))
        );

        IDPhimField.setEditable(false);

        BangPhim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Phim", "Thời lượng", "Độ tuổi", "Thể loại", "Ngày khơi chiếu"
            }
        ));
        jScrollPane2.setViewportView(BangPhim);

        javax.swing.GroupLayout QlphimLayout = new javax.swing.GroupLayout(Qlphim);
        Qlphim.setLayout(QlphimLayout);
        QlphimLayout.setHorizontalGroup(
            QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlphimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
        );
        QlphimLayout.setVerticalGroup(
            QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlphimLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane3.addTab("Quản lý phim", Qlphim);

        jTabbedPane1.addTab("Quản lý phim", jTabbedPane3);

        QlKhachHang.setBackground(new java.awt.Color(204, 204, 204));

        jPanel7.setBackground(new java.awt.Color(247, 247, 247));

        jPanel12.setBackground(new java.awt.Color(247, 247, 247));

        LuaChonKhach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Số lượng vé" }));

        TimKiemKhachField.setText("Tìm kiếm");
        TimKiemKhachField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemKhachFieldActionPerformed(evt);
            }
        });

        TimKhachbtn.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(TimKiemKhachField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LuaChonKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TimKhachbtn))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(TimKiemKhachField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LuaChonKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(TimKhachbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(247, 247, 247));

        jLabel9.setText("Ngày Sinh");

        jLabel8.setText("Họ tên");

        jLabel6.setText("Giới tính");

        NgaySinhField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NgaySinhFieldActionPerformed(evt);
            }
        });

        AddKhach.setText("Add");
        AddKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddKhachMouseClicked(evt);
            }
        });
        AddKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddKhachActionPerformed(evt);
            }
        });

        EditKhach.setText("Edit");
        EditKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditKhachMouseClicked(evt);
            }
        });
        EditKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditKhachActionPerformed(evt);
            }
        });

        DeleteKhach.setText("Delete");
        DeleteKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteKhachMouseClicked(evt);
            }
        });
        DeleteKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteKhachActionPerformed(evt);
            }
        });

        jLabel31.setText("Số lượng vé");

        clearKhach.setText("Clear");

        jLabel12.setText("ID");

        IDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HoTenField, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(SlvField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(DeleteKhach)
                                .addGap(18, 18, 18)
                                .addComponent(EditKhach)
                                .addGap(26, 26, 26)
                                .addComponent(AddKhach)
                                .addGap(18, 18, 18)
                                .addComponent(clearKhach))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GioiTinhField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NgaySinhField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SlvField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HoTenField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NgaySinhField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GioiTinhField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeleteKhach)
                    .addComponent(EditKhach)
                    .addComponent(AddKhach)
                    .addComponent(clearKhach))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        IDField.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(96, 96, 96))
        );

        BangKhachHang.setAutoCreateRowSorter(true);
        BangKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Năm sinh", "Giới tính", "Số lượng vé", "Tổng tiền"
            }
        ));
        jScrollPane3.setViewportView(BangKhachHang);

        javax.swing.GroupLayout QlKhachHangLayout = new javax.swing.GroupLayout(QlKhachHang);
        QlKhachHang.setLayout(QlKhachHangLayout);
        QlKhachHangLayout.setHorizontalGroup(
            QlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlKhachHangLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(QlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        QlKhachHangLayout.setVerticalGroup(
            QlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlKhachHangLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Quản lý khách hàng", QlKhachHang);

        jTabbedPane1.addTab("Quản lý khách hàng", jTabbedPane6);

        QlDatVe.setBackground(new java.awt.Color(204, 204, 204));

        BangVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Khách Hàng", "Vị trí ghế", "Loại ghế", "Tên phim", "Thời lượng", "Phòng", "Thời gian chiếu"
            }
        ));
        jScrollPane4.setViewportView(BangVe);

        jTextField1.setText("Tìm kiếm");

        jButton1.setText("Tìm kiếm");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("SortbyName");

        jButton3.setText("SortbyPhong");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(231, 231, 231))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout QlDatVeLayout = new javax.swing.GroupLayout(QlDatVe);
        QlDatVe.setLayout(QlDatVeLayout);
        QlDatVeLayout.setHorizontalGroup(
            QlDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlDatVeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QlDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addGroup(QlDatVeLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        QlDatVeLayout.setVerticalGroup(
            QlDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlDatVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                .addGap(127, 127, 127))
        );

        jTabbedPane7.addTab("Quản lý vé ", QlDatVe);

        jTabbedPane1.addTab("Quản lý vé", jTabbedPane7);

        jPanel8.setBackground(new java.awt.Color(247, 247, 247));
        jPanel8.setForeground(new java.awt.Color(204, 204, 204));

        AddVe.setText("Next");

        jLabel48.setText("Tên phim");

        jLabel51.setText("Phòng");

        jLabel53.setText("Thời gian chiếu");

        jLabel55.setText("Tên Khách Hàng");

        ListKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListKhachHangActionPerformed(evt);
            }
        });

        phongCombo1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phongCombo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        phimCombo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        phimCombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phimCombo1ActionPerformed(evt);
            }
        });

        Id1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Id1.setLabelFor(IdField);
        Id1.setText("Id suất chiếu");

        IdField1.setEditable(false);
        IdField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddVe, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(358, 358, 358))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Id1)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(IdField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(271, 271, 271)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(phimCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel53)))
                            .addComponent(ListKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phongCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tgChieuField1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 126, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(phongCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Id1)
                            .addComponent(IdField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(tgChieuField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(ListKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phimCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48))))
                .addGap(7, 7, 7)
                .addComponent(AddVe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        quanLySuatChieu1.setAutoscrolls(true);
        quanLySuatChieu1.setPreferredSize(new java.awt.Dimension(528, 10));
        quanLySuatChieu1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SuatChieuManager1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SuatChieuManager1.setText("Danh sách suất chiếu hiện có");
        quanLySuatChieu1.add(SuatChieuManager1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 40));

        suatChieuTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Phim", "Phòng", "Thời gian chiếu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        suatChieuTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suatChieuTable2MouseClicked(evt);
            }
        });
        suatChieuScroll2.setViewportView(suatChieuTable2);

        quanLySuatChieu1.add(suatChieuScroll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 960, 810));

        javax.swing.GroupLayout ChonSuatChieuLayout = new javax.swing.GroupLayout(ChonSuatChieu);
        ChonSuatChieu.setLayout(ChonSuatChieuLayout);
        ChonSuatChieuLayout.setHorizontalGroup(
            ChonSuatChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChonSuatChieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(ChonSuatChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ChonSuatChieuLayout.createSequentialGroup()
                    .addComponent(quanLySuatChieu1, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        ChonSuatChieuLayout.setVerticalGroup(
            ChonSuatChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChonSuatChieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(841, Short.MAX_VALUE))
            .addGroup(ChonSuatChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChonSuatChieuLayout.createSequentialGroup()
                    .addContainerGap(226, Short.MAX_VALUE)
                    .addComponent(quanLySuatChieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(142, Short.MAX_VALUE)))
        );

        jTabbedPane8.addTab("Chọn suất chiếu", ChonSuatChieu);

        jPanel10.setBackground(new java.awt.Color(247, 247, 247));
        jPanel10.setForeground(new java.awt.Color(204, 204, 204));

        DatVebtn.setBackground(new java.awt.Color(196, 23, 23));
        DatVebtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        DatVebtn.setForeground(new java.awt.Color(255, 255, 255));
        DatVebtn.setText("Đặt vé");

        jLabel32.setText("Vị trí ghế");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(DatVebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(ViTriField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(467, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(ViTriField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(DatVebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        BangGhe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(BangGhe);

        javax.swing.GroupLayout ChonGheLayout = new javax.swing.GroupLayout(ChonGhe);
        ChonGhe.setLayout(ChonGheLayout);
        ChonGheLayout.setHorizontalGroup(
            ChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChonGheLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addGroup(ChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ChonGheLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(120, Short.MAX_VALUE)))
        );
        ChonGheLayout.setVerticalGroup(
            ChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChonGheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(766, Short.MAX_VALUE))
            .addGroup(ChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChonGheLayout.createSequentialGroup()
                    .addContainerGap(332, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane8.addTab("Chọn ghế", ChonGhe);

        jTabbedPane1.addTab("Đặt vé", jTabbedPane8);

        getContentPane().add(jTabbedPane1);

        getAccessibleContext().setAccessibleName("frame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void suatChieuTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suatChieuTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_suatChieuTableMouseClicked

    private void QlphimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QlphimMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QlphimMouseClicked

    private void TimKiemKhachFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemKhachFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemKhachFieldActionPerformed

    private void NgaySinhFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NgaySinhFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NgaySinhFieldActionPerformed

    private void AddKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddKhachMouseClicked

    }//GEN-LAST:event_AddKhachMouseClicked

    private void AddKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddKhachActionPerformed

    private void EditKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditKhachMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EditKhachMouseClicked

    private void EditKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditKhachActionPerformed

    private void DeleteKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteKhachMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteKhachMouseClicked

    private void DeleteKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteKhachActionPerformed

    private void IDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDFieldActionPerformed

    private void phimComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phimComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phimComboActionPerformed

    private void ListKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListKhachHangActionPerformed

    private void suatChieuTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suatChieuTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_suatChieuTable2MouseClicked

    private void phimCombo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phimCombo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phimCombo1ActionPerformed

    public void addListSuatChieuSelectionListener(ListSelectionListener listener) {
        this.suatChieuTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void addEditSuatChieuListener(ActionListener listener) {
        this.editButton.addActionListener(listener);
    }

    public void addAddSuatChieuListener(ActionListener listener) {
        this.addButton.addActionListener(listener);
    }

    public void addClearSchFieldListener(ActionListener listener) {
        this.clearButton.addActionListener(listener);
    }

    public void addDeleteSuatChieuListener(ActionListener listener) {
        this.deleteButton.addActionListener(listener);
    }

    public void addSortSuatChieuListener(ActionListener listener) {
        this.sortButton.addActionListener(listener);
    }

    public void addSortDoanhThuPhimListener(ActionListener listener) {
        this.sortDtPhimButton.addActionListener(listener);
    }
    public void addListSuatChieuSelectionListener1(ListSelectionListener listener) {
        this.suatChieuTable2.getSelectionModel().addListSelectionListener(listener);
    }
    
     public void addListGheSelectionListener(ListSelectionListener listener) {
        this.BangGhe.getSelectionModel().addListSelectionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddKhach;
    private javax.swing.JButton AddPhim;
    private javax.swing.JButton AddVe;
    private javax.swing.JTable BangGhe;
    private javax.swing.JTable BangKhachHang;
    private javax.swing.JTable BangPhim;
    private javax.swing.JTable BangVe;
    private javax.swing.JPanel ChonGhe;
    private javax.swing.JPanel ChonSuatChieu;
    private javax.swing.JButton ClearPhim;
    private javax.swing.JButton DatVebtn;
    private javax.swing.JButton DeleteKhach;
    private javax.swing.JButton DeletePhim;
    private javax.swing.JTextField DoTuoiField;
    private javax.swing.JPanel DsPhimPane;
    private javax.swing.JScrollPane DsPhimScrollPane;
    private javax.swing.JPanel DsPhongPane;
    private javax.swing.JScrollPane DsPhongScrollPane;
    private javax.swing.JPanel DsSuatPane;
    private javax.swing.JScrollPane DsSuatScrollPane;
    private javax.swing.JButton EditKhach;
    private javax.swing.JButton EditPhim;
    private javax.swing.JTextField GioiTinhField;
    private javax.swing.JTextField HoTenField;
    private javax.swing.JLayeredPane Homepage;
    private javax.swing.JLabel HomepageTitle;
    private javax.swing.JTextField IDField;
    private javax.swing.JTextField IDPhimField;
    private javax.swing.JLabel Id;
    private javax.swing.JLabel Id1;
    private javax.swing.JTextField IdField;
    private javax.swing.JTextField IdField1;
    private javax.swing.JComboBox<String> ListKhachHang;
    private javax.swing.JComboBox<String> LuaChonKhach;
    private javax.swing.JComboBox<String> LuaChonPhim;
    private javax.swing.JTextField NgayKhoiChieuField;
    private javax.swing.JTextField NgaySinhField;
    private javax.swing.JPanel QlDatVe;
    private javax.swing.JPanel QlKhachHang;
    private javax.swing.JPanel Qlphim;
    private javax.swing.JTextField SlvField;
    private javax.swing.JLabel SuatChieuManager;
    private javax.swing.JLabel SuatChieuManager1;
    private javax.swing.JTextField TenPhimField;
    private javax.swing.JTextField TheLoaiField;
    private javax.swing.JTextField ThoiLuongField;
    private javax.swing.JButton TimKhachbtn;
    private javax.swing.JButton TimKiemBtn;
    private javax.swing.JTextField TimKiemKhachField;
    private javax.swing.JTextField TimKiemPhimField;
    private javax.swing.JTextField ViTriField;
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton clearKhach;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel doanhThuPhim;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel phimChieu;
    private javax.swing.JComboBox<String> phimCombo;
    private javax.swing.JComboBox<String> phimCombo1;
    private javax.swing.JLabel phongChieu;
    private javax.swing.JComboBox<String> phongCombo;
    private javax.swing.JComboBox<String> phongCombo1;
    private javax.swing.JLayeredPane quanLySuatChieu;
    private javax.swing.JLayeredPane quanLySuatChieu1;
    private javax.swing.JButton sortButton;
    private javax.swing.JButton sortDtPhimButton;
    private javax.swing.JScrollPane suatChieuScroll;
    private javax.swing.JScrollPane suatChieuScroll2;
    private javax.swing.JTable suatChieuTable;
    private javax.swing.JTable suatChieuTable2;
    private javax.swing.JCheckBox tangDan;
    private javax.swing.JLabel tgChieu;
    private javax.swing.JTextField tgChieuField;
    private javax.swing.JTextField tgChieuField1;
    private javax.swing.JCheckBox tieuChiDtPhim;
    private javax.swing.JComboBox<String> tieuChiSchCombo;
    private javax.swing.JLabel tongDoanhThu;
    // End of variables declaration//GEN-END:variables
}
