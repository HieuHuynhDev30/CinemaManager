/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Classes.Khach;
import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import Classes.Ve;
import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ManagerView extends javax.swing.JFrame {

    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String[] columnNames1 = new String[]{
        "ID", "Name", "Thể Loại", "Độ tuổi", "Thời lượng", "Ngày khởi chiếu"};

    private String[] columnNames = new String[]{
        "ID", "Name", "Ngày sinh", "Giới tính", "Số lượng vé", "Tổng tiền"};

    private String[] columnNames2 = new String[]{
        "ID", "Họ tên khách", "Vị trí ghế", "Loại ghế", "Tên phim", "Thời lượng", "Phòng", "Thời gian chiếu"};

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

//////// Trang chủ
    public JPanel getDsPhimPane() {
        return DsPhimPane;
    }

    public void setDsPhimPane(JPanel DsPhimPane) {
        this.DsPhimPane = DsPhimPane;
    }

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

            suatPanel.setLayout(new java.awt.GridLayout(1, 2));
            suatPanel.setPreferredSize(new java.awt.Dimension(300, 250));
            infoPanel.setLayout(new java.awt.FlowLayout(5, 30, 20));
            infoPanel.setSize(100, 250);
            posterLabel.setSize(200, 250);

//            posterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            ImageIcon poster = this.getImage(ph.getPhim().getPosterLink(), posterLabel);
            posterLabel.setIcon(poster);
            JLabel phim = new javax.swing.JLabel();
//            phim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            phim.setText(ph.getPhim().getTen());
            JLabel thoiGian = new javax.swing.JLabel();
//            thoiGian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            thoiGian.setText(ph.getThoiGianChieu().format(formatDateTime));
            JLabel phong = new javax.swing.JLabel();
//            phong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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

//////// end Trang chủ
//////// Quản lý phòng
    public void showListPhong(List<Phong> list, List<SuatChieu> schList) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object[][] phs = new Object[size][9];
        for (int i = 0; i < size; i++) {
            phs[i][0] = list.get(i).getId();
            phs[i][1] = list.get(i).getSlThuong();
            phs[i][2] = list.get(i).getSlVip();
            phs[i][3] = list.get(i).getSlDoi();
            phs[i][4] = list.get(i).getSlThuong() + list.get(i).getSlVip() + list.get(i).getSlDoi();
            phs[i][5] = list.get(i).getIsFull() ? "Đã đầy" : "Chưa đầy";
            phs[i][6] = list.get(i).getSucChua();
            for (SuatChieu sch : schList) {
                if (sch.getPhong().getId() == null ? list.get(i).getId() == null : sch.getPhong().getId().equals(list.get(i).getId())) {
                    phs[i][7] = sch.getPhim().getTen();
                    break;
                }
            }
            phs[i][8] = list.get(i).getIsPlaying() ? "Đang chiếu" : "Đang rảnh";
        }
        String[] columnNames = new String[9];
        for (int i = 0; i < 9; i++) {
            columnNames[i] = this.PhongTable.getColumnName(i);
        }
        this.PhongTable.setModel(new DefaultTableModel(phs, columnNames));
    }

    public void showPhong(Phong ph) {
        this.IdPhongFiled.setText(ph.getId());
        this.SlgThuongSpinner.setValue(ph.getSlThuong());
        this.SlgVipSpinner.setValue(ph.getSlVip());
        this.SlgDoiSpinner.setValue(ph.getSlDoi());
        this.editPhongButton.setEnabled(true);
        this.deletePhongButton.setEnabled(true);
//        // disable Add button
        this.addPhongButton.setEnabled(false);
    }

    public void setPhongSortCombo(List<Phong> list, List<SuatChieu> schList) {
        String[] tieuChiStr = new String[5];
        tieuChiStr[0] = "Tổng số ghế";
        tieuChiStr[1] = "Lấp đầy";
        tieuChiStr[2] = "Sức chứa";
        tieuChiStr[3] = "Phim chiếu";
        tieuChiStr[4] = "Tình trạng";

        this.TieuChiSortPhongCombo.setModel(new javax.swing.DefaultComboBoxModel<>(tieuChiStr));
    }

    public void fillPhongFromSelectedRow(List<Phim> phimList) {
        // lấy chỉ số của hàng được chọn 
        int row = this.PhongTable.getSelectedRow();
        if (row >= 0) {
            this.IdPhongFiled.setText(PhongTable.getModel().getValueAt(row, 0).toString());
            this.SlgThuongSpinner.setValue(PhongTable.getModel().getValueAt(row, 1));
            this.SlgVipSpinner.setValue(PhongTable.getModel().getValueAt(row, 2));
            this.SlgDoiSpinner.setValue(PhongTable.getModel().getValueAt(row, 3));
            this.editPhongButton.setEnabled(true);
            this.deletePhongButton.setEnabled(true);
            this.addPhongButton.setEnabled(false);
        }
    }

    public void addListPhongSelectionListener(ListSelectionListener listener) {
        this.PhongTable.getSelectionModel().addListSelectionListener(listener);
    }

    public Phong getPhongInfo(List<Phong> phongList) {
        // validate student
//        if (!validateName() || !validateAge() || !validateAddress() || !validateGPA()) {
//            return null;
//        }
        try {
            Phong phong = new Phong();
            for (Phong ph : phongList) {
                if (ph.getId() == null ? this.IdPhongFiled.getText() == null : ph.getId().equals(this.IdPhongFiled.getText())) {
                    if (ph.getIsPlaying() == false) {
                        phong.setId(ph.getId());
                        phong.setDt(ph.getDt());
                        phong.setSuatChieu(ph.getSuatChieu());
                        phong.setSlThuong((Integer) this.SlgThuongSpinner.getValue());
                        phong.setSlDoi((Integer) this.SlgDoiSpinner.getValue());
                        phong.setSlVip((Integer) this.SlgVipSpinner.getValue());
                        if (phong.getTongGhe() > ph.inTongDat()) {
                            phong.setIsFull(false);
                        } else {
                            phong.setIsFull(true);
                        }
                    } else {
                        return null;
                    }
                }
            }

            return phong;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public void clearPhongInfo() {
        this.IdPhongFiled.setText("");
        this.SlgThuongSpinner.setValue(0);
        this.SlgVipSpinner.setValue(0);
        this.SlgDoiSpinner.setValue(0);
        // disable Edit and Delete buttons
        this.editPhongButton.setEnabled(false);
        this.deletePhongButton.setEnabled(false);
        // enable Add button
        this.addPhongButton.setEnabled(true);
    }

    public String getTieuChiSxPhong() {
        return this.TieuChiSortPhongCombo.getSelectedItem().toString();
    }

    public boolean getPhongTangDan() {
        return this.TchiTangPhong.isSelected();
    }

    public void addEditPhongListener(ActionListener listener) {
        this.editPhongButton.addActionListener(listener);
    }

    public void addAddPhongListener(ActionListener listener) {
        this.addPhongButton.addActionListener(listener);
    }

    public void addClearPhongFieldListener(ActionListener listener) {
        this.clearPhongInfoButtom.addActionListener(listener);
    }

    public void addDeletePhongListener(ActionListener listener) {
        this.deletePhongButton.addActionListener(listener);
    }

    public void addSortPhongListener(ActionListener listener) {
        this.SortPhongButton.addActionListener(listener);
    }

//////// end Quản lý phòng
//////// Quản lý suất chiếu
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
            schs[i][3] = list.get(i).getThoiGianChieu().format(formatDateTime);
        }
        String[] columnNames = new String[4];
        for (int i = 0; i < 4; i++) {
            columnNames[i] = suatChieuTable.getColumnName(i);
        }
        this.suatChieuTable.setModel(new DefaultTableModel(schs, columnNames));
    }

    public void showSuatChieu(SuatChieu sch) {
        this.IdField.setText("" + sch.getId());
        this.phimCombo.setSelectedItem(sch.getPhim().getTen());
        this.phongCombo.setSelectedItem(sch.getPhong().getId());
        this.schDateTimeField.setText(sch.getThoiGianChieu().format(formatDateTime));
        // enable Edit and Delete buttons
        editButton.setEnabled(true);
        deleteButton.setEnabled(true);
//        // disable Add button
        addButton.setEnabled(false);
    }

    public String getSchDateTimePicker() {
        int day = this.schCalendar.getDayChooser().getDay();
        int month = this.schCalendar.getMonthChooser().getMonth() + 1;
        int year = this.schCalendar.getYearChooser().getYear();
        CharSequence dateTime = String.format("%02d-%02d-%d %s", day, month, year, this.getSchTime());
        try {
            String date = LocalDateTime.parse(dateTime, this.formatDateTime).format(formatDateTime);
            return date;
        } catch (Exception e) {
            this.showMessage("Thiếu thông tin ngày giờ");
            return "";
        }
    }

    public String getSchTime() {
        int hour = Integer.parseInt(this.hourSchSpinner.getValue().toString());
        int min = Integer.parseInt(this.minsSchSpinner.getValue().toString());
        return String.format("%02d:%02d", hour, min);
    }

    public void confirmSchDateTimePicker(String date) {
        this.schDateTimeField.setText(date);
    }

    public void addSchDateTimePickerListener(ActionListener listener) {
        this.xacNhanSchDateBtn.addActionListener(listener);
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
            this.schDateTimeField.setText(suatChieuTable.getModel().getValueAt(row, 3).toString());
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
            sch.setThoiGianChieu(this.schDateTimeField.getText());
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
        this.schDateTimeField.setText("");
        // disable Edit and Delete buttons
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        // enable Add button
        addButton.setEnabled(true);
    }

    public String getTieuChiSxSch() {
        return this.tieuChiSchCombo.getSelectedItem().toString();
    }

    public boolean getSchTangDan() {
        return this.tChiSchtangDan.isSelected();
    }

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
        this.sortSchButton.addActionListener(listener);
    }

//////// end Quản lý suất chiếu
/////// Doanh thu
    public void showDoanhThu(double dt) {
        this.tongDoanhThu.setText(dt + "đ");
    }

    public void showDoanhThuPhim(List<Phim> list, List<Ve> veList) {
        for (Phim ph : list) {
            JPanel dtPhimPanel = new javax.swing.JPanel();
            dtPhimPanel.setPreferredSize(new java.awt.Dimension(200, 198));
            dtPhimPanel.setLayout(new java.awt.GridLayout(1, 2));

            JPanel infoPhimPanel = new javax.swing.JPanel();
            infoPhimPanel.setLayout(new java.awt.FlowLayout(0, 5, 20));
            infoPhimPanel.setSize(100, 198);

            JLabel posterLabel = new javax.swing.JLabel();
            posterLabel.setSize(100, 198);

            JTextArea ten = new javax.swing.JTextArea(2, 10);
            ten.setWrapStyleWord(true);
            ten.setLineWrap(true);
            ten.setOpaque(false);
            ten.setEditable(false);
            ten.setFocusable(false);
            JTextArea slve = new javax.swing.JTextArea(2, 10);
            slve.setWrapStyleWord(true);
            slve.setLineWrap(true);
            slve.setOpaque(false);
            slve.setEditable(false);
            slve.setFocusable(false);
            JTextArea dt = new javax.swing.JTextArea(2, 10);
            dt.setWrapStyleWord(true);
            dt.setLineWrap(true);
            dt.setOpaque(false);
            dt.setEditable(false);
            dt.setFocusable(false);

            infoPhimPanel.add(ten);
            infoPhimPanel.add(slve);
            infoPhimPanel.add(dt);

            posterLabel.setSize(100, 198);
            ImageIcon imageIcon = this.getImage(ph.getPosterLink(), posterLabel);
            posterLabel.setIcon(imageIcon);

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
            slve.setText(Integer.toString(tongVe) + " vé");

            dt.setText(tongDt + "đ");

            dtPhimPanel.add(posterLabel);
            dtPhimPanel.add(infoPhimPanel);
            this.doanhThuPhim.add(dtPhimPanel);
        }
    }

    public void clearDoanhThuPhim() {
        this.doanhThuPhim.removeAll();
    }

    public boolean getDtPhimTangDan() {
        return this.tieuChiDtPhim.isSelected();
    }

    public void showDoanhThuSch(List<SuatChieu> list, List<Ve> veList) {
        for (SuatChieu sch : list) {
            JPanel dtSchPanel = new javax.swing.JPanel();

            JPanel infoSchPanel = new javax.swing.JPanel();
            JLabel posterLabel = new javax.swing.JLabel();
            JTextArea schId = new javax.swing.JTextArea();
            schId.setOpaque(false);
            schId.setEditable(false);
            schId.setFocusable(false);
            JTextArea phimSch = new javax.swing.JTextArea(2, 10);
            phimSch.setWrapStyleWord(true);
            phimSch.setLineWrap(true);
            phimSch.setOpaque(false);
            phimSch.setEditable(false);
            phimSch.setFocusable(false);
            JTextArea phongSch = new javax.swing.JTextArea();
            phongSch.setOpaque(false);
            phongSch.setEditable(false);
            phongSch.setFocusable(false);
            JTextArea tgSch = new javax.swing.JTextArea(2, 10);
            tgSch.setWrapStyleWord(true);
            tgSch.setLineWrap(true);
            tgSch.setOpaque(false);
            tgSch.setEditable(false);
            tgSch.setFocusable(false);
            JTextArea dt = new javax.swing.JTextArea();
            dt.setWrapStyleWord(true);
            dt.setLineWrap(true);
            dt.setOpaque(false);
            dt.setEditable(false);
            dt.setFocusable(false);

            dtSchPanel.setLayout(new java.awt.GridLayout(1, 2));
            dtSchPanel.setPreferredSize(new java.awt.Dimension(200, 198));
            infoSchPanel.setLayout(new java.awt.FlowLayout(0, 5, 5));
            infoSchPanel.setSize(100, 198);
            posterLabel.setSize(100, 198);

            infoSchPanel.add(phimSch);
            infoSchPanel.add(dt);
            infoSchPanel.add(phongSch);
            infoSchPanel.add(tgSch);
            infoSchPanel.add(schId);

            ImageIcon imageIcon = this.getImage(sch.getPhim().getPosterLink(), posterLabel);
            posterLabel.setIcon(imageIcon);

            schId.setText(sch.getId());

            phimSch.setText(sch.getPhim().getTen());

            phongSch.setText(sch.getPhong().getId());

            tgSch.setText(sch.getThoiGianChieu().format(formatDateTime));

            int tongDt = 0;
            for (Ve ve : veList) {
                if (ve.getSuat().getId() == null ? sch.getId() == null : ve.getSuat().getId().equals(sch.getId())) {
                    tongDt += ve.getGhe().getGia();
                }
            }
            sch.setDt(tongDt);
            dt.setText(tongDt + "đ");

            infoSchPanel.add(schId);

            dtSchPanel.add(posterLabel);
            dtSchPanel.add(infoSchPanel);
            this.DoanhThuSch.add(dtSchPanel);
        }
    }

    public void clearDoanhThuSch() {
        this.DoanhThuSch.removeAll();
    }

    public void addSortDoanhThuPhimListener(ActionListener listener) {
        this.sortDtPhimButton.addActionListener(listener);
    }

    public void addSortDoanhThuSchListener(ActionListener listener) {
        this.sortDtSchButton.addActionListener(listener);
    }

    public void showDtPhongList(List<Phong> list, List<SuatChieu> schList) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object[][] phs = new Object[size][4];
        for (int i = 0; i < size; i++) {
            phs[i][0] = list.get(i).getId();
            phs[i][1] = list.get(i).getSucChua();
            int tong = 0;
            int dt = 0;
            for (SuatChieu sch : schList) {
                if (sch.getPhong().getId() == null ? list.get(i).getId() == null : sch.getPhong().getId().equals(list.get(i).getId())) {
                    tong++;
                    dt += sch.getDt();
                }
            }
            list.get(i).setDt(dt);
            phs[i][2] = tong;
            phs[i][3] = dt;
        }
        String[] columnNames = new String[4];
        for (int i = 0; i < 4; i++) {
            columnNames[i] = this.DtPhongTable.getColumnName(i);
        }
        this.DtPhongTable.setModel(new DefaultTableModel(phs, columnNames));
    }

    public boolean getDtPhongTangDan() {
        return this.tieuChiDtPhong.isSelected();
    }

    public void addSortDoanhThuPhongListener(ActionListener listener) {
        this.sortDtPhongButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

/////// Quản lý phim
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

    public void setTheLoaiComboAndDoTuoiSpinner() {
        doTuoiSpinner.setValue(13);
        theLoaiCombo.setModel(new javax.swing.DefaultComboBoxModel<>(Phim.dsTheLoai));
    }

    public void fillPhimFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = BangPhim.getSelectedRow();
        if (row >= 0) {
            IDPhimField.setText(BangPhim.getModel().getValueAt(row, 0).toString());
            TenPhimField.setText(BangPhim.getModel().getValueAt(row, 1).toString());
            this.theLoaiCombo.setSelectedItem(BangPhim.getModel().getValueAt(row, 2).toString());
            doTuoiSpinner.setValue(BangPhim.getModel().getValueAt(row, 3).toString());
            ThoiLuongField.setText(BangPhim.getModel().getValueAt(row, 4).toString());
            NgayKhoiChieuField.setText(BangPhim.getModel().getValueAt(row, 5).toString());
            // enable Edit and Delete buttons
            EditPhimButton.setEnabled(true);
            DeletePhimButton.setEnabled(true);
            // disable Add button
            AddPhimButton.setEnabled(false);
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
            phim.setTheLoai(theLoaiCombo.getSelectedItem().toString().trim());
            phim.setDoTuoi(Integer.parseInt(doTuoiSpinner.getValue().toString()));
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
        doTuoiSpinner.setValue(phim.getDoTuoi());
        theLoaiCombo.setSelectedItem(phim.getTheLoai());
        ThoiLuongField.setText("" + phim.inThoiLuong());
        NgayKhoiChieuField.setText("" + phim.inTgKhoiChieu());
        EditPhimButton.setEnabled(true);
        DeletePhimButton.setEnabled(true);
        AddPhimButton.setEnabled(false);
    }

    public void clearPhimInfo() {
        IDPhimField.setText("");
        TenPhimField.setText("");
        doTuoiSpinner.setValue(13);
        theLoaiCombo.setSelectedItem("Chọn một thể loại");

        ThoiLuongField.setText("");
        NgayKhoiChieuField.setText("");
        EditPhimButton.setEnabled(false);
        DeletePhimButton.setEnabled(false);
        AddPhimButton.setEnabled(true);
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

    public void addAddPhimListener(ActionListener listener) {
        AddPhimButton.addActionListener(listener);
    }

    public void addClearPhimListener(ActionListener listener) {
        ClearPhimButton.addActionListener(listener);
    }

    public void addListPhimSelectionListener(ListSelectionListener listener) {
        BangPhim.getSelectionModel().addListSelectionListener(listener);
    }

    public void addDeletePhimListener(ActionListener listener) {
        DeletePhimButton.addActionListener(listener);
    }

    public void addEditPhimListener(ActionListener listener) {
        EditPhimButton.addActionListener(listener);
    }

    public void addSearchPhimListener(ActionListener listener) {
        TimKiemBtn.addActionListener(listener);
    }

    ////// end Quản lý phim
//////// Quản lý khách
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
            khach[i][2] = list.get(i).getNgaySinh().format(dateFormat);
            khach[i][3] = list.get(i).getGioiTinh();
            khach[i][4] = list.get(i).getSlVeDat();
            khach[i][5] = list.get(i).getTongTien();

        }
        BangKhachHang.setModel(new DefaultTableModel(khach, columnNames));
    }

    public void setGioiTinhCombo() {
        this.gioiTinhCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Nam", "Nữ"}));
    }
    
     public String getNgaySinhPicker() {
        int day = this.NSCalendar.getDayChooser().getDay();
        int month = this.NSCalendar.getMonthChooser().getMonth() + 1;
        int year = this.NSCalendar.getYearChooser().getYear();
        CharSequence date = String.format("%02d-%02d-%d", day, month, year);
        try {
            String dateStr = LocalDate.parse(date, dateFormat).format(dateFormat);
            return dateStr;
        } catch (Exception e) {
            this.showMessage("Thiếu thông tin ngày");
            return "";
        }
    }


    public void confirmNSPicker(String date) {
        this.NgaySinhField.setText(date);
    }

    public void addNSDatePickerListener(ActionListener listener) {
        this.confirmNS.addActionListener(listener);
    }

    //
    public void fillKhachFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = BangKhachHang.getSelectedRow();
        if (row >= 0) {
            IDField.setText(BangKhachHang.getModel().getValueAt(row, 0).toString());
            HoTenField.setText(BangKhachHang.getModel().getValueAt(row, 1).toString());
            NgaySinhField.setText(BangKhachHang.getModel().getValueAt(row, 2).toString());
            gioiTinhCombo.setSelectedItem(BangKhachHang.getModel().getValueAt(row, 3).toString());
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
            khach.setGioiTinh(gioiTinhCombo.getSelectedItem().toString());
            khach.setNgaySinh(NgaySinhField.getText().trim());
            khach.setSlVeDat(Integer.parseInt(SlvField.getText().trim()));

            return khach;
        } catch (NumberFormatException e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public void showKhach(Khach khach) {
        IDField.setText("" + khach.getId());
        HoTenField.setText("" + khach.getHoTen());
        gioiTinhCombo.setSelectedItem(khach.getGioiTinh());
        NgaySinhField.setText("" + khach.getNgaySinh().format(dateFormat));
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
        gioiTinhCombo.setSelectedIndex(0);
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
        String gioitinh = gioiTinhCombo.getSelectedItem().toString();
        if (gioitinh == null || "".equals(gioitinh.trim())) {
            gioiTinhCombo.requestFocus();
            showMessage("Gioi tinh không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateSLV() {
        try {
            Byte slv = Byte.valueOf(SlvField.getText().trim());
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

    public void addSearchKhachListener(ActionListener listener) {
        TimKhachbtn.addActionListener(listener);
    }

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

    ////// end Quản lý khách
    ////// Quản lý vé
    public void showListVe(List<Ve> list, List<Khach> khachs) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5

        Object[][] ve = new Object[size][7];
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
            ve[i][4] = list.get(i).getSuat().inPhim();
            ve[i][5] = list.get(i).getSuat().getPhongId();
            ve[i][6] = list.get(i).getSuat().inThoiGianChieu();

        }
        BangVe.setModel(new DefaultTableModel(ve, columnNames2));
    }

/////// end Quản lý vé
    // Het phim method
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        Homepage = new javax.swing.JLayeredPane();
        HomepageTitle = new javax.swing.JLabel();
        DsPhimScrollPane = new javax.swing.JScrollPane();
        DsPhimPane = new javax.swing.JPanel();
        DsSuatScrollPane = new javax.swing.JScrollPane();
        DsSuatPane = new javax.swing.JPanel();
        DsPhongScrollPane = new javax.swing.JScrollPane();
        DsPhongPane = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        IdPhongFiled = new javax.swing.JTextField();
        SlgThuongLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SlgThuongSpinner = new javax.swing.JSpinner();
        SlgVipSpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        SlgDoiSpinner = new javax.swing.JSpinner();
        PhongIdLabel = new javax.swing.JLabel();
        editPhongButton = new javax.swing.JButton();
        addPhongButton = new javax.swing.JButton();
        clearPhongInfoButtom = new javax.swing.JButton();
        deletePhongButton = new javax.swing.JButton();
        TieuChiSortPhongCombo = new javax.swing.JComboBox<>();
        TchiTangPhong = new javax.swing.JCheckBox();
        SortPhongButton = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        PhongTable = new javax.swing.JTable();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        Qlphim = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        IDPhimField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        ThoiLuongField = new javax.swing.JTextField();
        TenPhimField = new javax.swing.JTextField();
        AddPhimButton = new javax.swing.JButton();
        ClearPhimButton = new javax.swing.JButton();
        DeletePhimButton = new javax.swing.JButton();
        EditPhimButton = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        doTuoiSpinner = new javax.swing.JSpinner();
        theLoaiCombo = new javax.swing.JComboBox<>();
        NgayKhoiChieuField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TimKiemPhimField = new javax.swing.JTextField();
        TimKiemBtn = new javax.swing.JButton();
        LuaChonPhim = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BangPhim = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        quanLySuatChieu = new javax.swing.JLayeredPane();
        SuatChieuManager = new javax.swing.JLabel();
        suatChieuScroll = new javax.swing.JScrollPane();
        suatChieuTable = new javax.swing.JTable();
        SuatChieuControllers = new javax.swing.JPanel();
        Id = new javax.swing.JLabel();
        IdField = new javax.swing.JTextField();
        phimChieu = new javax.swing.JLabel();
        phimCombo = new javax.swing.JComboBox<>();
        phongChieu = new javax.swing.JLabel();
        phongCombo = new javax.swing.JComboBox<>();
        tgChieu = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        xacNhanSchDateBtn = new javax.swing.JButton();
        schDateTimeField = new javax.swing.JTextField();
        schCalendar = new com.toedter.calendar.JCalendar();
        hourSchSpinner = new javax.swing.JSpinner();
        minsSchSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        sortSchButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tieuChiSchCombo = new javax.swing.JComboBox<>();
        tChiSchtangDan = new javax.swing.JCheckBox();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        QlKhachHang = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        HoTenField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NgaySinhField = new javax.swing.JTextField();
        AddKhach = new javax.swing.JButton();
        EditKhach = new javax.swing.JButton();
        DeleteKhach = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        SlvField = new javax.swing.JTextField();
        clearKhach = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        IDField = new javax.swing.JTextField();
        TimKhachbtn = new javax.swing.JButton();
        TimKiemKhachField = new javax.swing.JTextField();
        LuaChonKhach = new javax.swing.JComboBox<>();
        gioiTinhCombo = new javax.swing.JComboBox<>();
        NSCalendar = new com.toedter.calendar.JCalendar();
        confirmNS = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        BangKhachHang = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        QlDatVe = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        TimKiemPhimField1 = new javax.swing.JTextField();
        LuaChonPhim1 = new javax.swing.JComboBox<>();
        TimKiemBtn1 = new javax.swing.JButton();
        IDPhimField1 = new javax.swing.JTextField();
        AddPhim1 = new javax.swing.JButton();
        ClearPhim1 = new javax.swing.JButton();
        DeletePhim1 = new javax.swing.JButton();
        EditPhim1 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        LuaChonPhim2 = new javax.swing.JComboBox<>();
        LuaChonPhim3 = new javax.swing.JComboBox<>();
        LuaChonPhim4 = new javax.swing.JComboBox<>();
        LuaChonPhim5 = new javax.swing.JComboBox<>();
        LuaChonPhim6 = new javax.swing.JComboBox<>();
        LuaChonPhim7 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        TimKiemPhimField2 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        BangVe = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel4 = new javax.swing.JPanel();
        tongDoanhThu = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        doanhThuPhim = new javax.swing.JPanel();
        sortDtPhimButton = new javax.swing.JButton();
        tieuChiDtPhim = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        DoanhThuSch = new javax.swing.JPanel();
        tieuChiDtSch = new javax.swing.JCheckBox();
        sortDtSchButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        DtPhongTable = new javax.swing.JTable();
        sortDtPhongButton = new javax.swing.JButton();
        tieuChiDtPhong = new javax.swing.JCheckBox();
        jTabbedPane9 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(0, 0));

        jTabbedPane5.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane5.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        Homepage.setAutoscrolls(true);
        Homepage.setPreferredSize(new java.awt.Dimension(528, 10));

        HomepageTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        HomepageTitle.setText("CINEVERSE CINEMA");
        HomepageTitle.setDisplayedMnemonicIndex(5);

        DsPhimScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phim", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        DsPhimScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
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
        DsSuatScrollPane.setAlignmentX(0.0F);
        DsSuatScrollPane.setAlignmentY(0.0F);
        DsSuatScrollPane.setAutoscrolls(true);
        DsSuatScrollPane.setName(""); // NOI18N

        DsSuatPane.setAlignmentX(0.0F);
        DsSuatPane.setAlignmentY(0.0F);
        DsSuatPane.setAutoscrolls(true);
        DsSuatPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DsSuatPane.setLayout(new java.awt.GridLayout(1, 0));
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
                    .addComponent(DsSuatScrollPane)
                    .addComponent(DsPhongScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                    .addComponent(HomepageTitle)
                    .addComponent(DsPhimScrollPane))
                .addContainerGap())
        );
        HomepageLayout.setVerticalGroup(
            HomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomepageLayout.createSequentialGroup()
                .addComponent(HomepageTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsPhimScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsSuatScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsPhongScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("", Homepage);

        jTabbedPane1.addTab("Trang chủ", null, jTabbedPane5, "");

        jTabbedPane8.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý phòng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel14.setLayout(new java.awt.GridLayout(2, 1));

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        IdPhongFiled.setEditable(false);
        IdPhongFiled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdPhongFiledActionPerformed(evt);
            }
        });
        jPanel17.add(IdPhongFiled, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 16, 82, -1));

        SlgThuongLabel.setText("Số lượng ghế thường");
        jPanel17.add(SlgThuongLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 59, -1, -1));

        jLabel5.setText("Số lượng ghế Vip");
        jPanel17.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 104, -1, -1));
        jPanel17.add(SlgThuongSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 56, 82, -1));
        jPanel17.add(SlgVipSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 101, 82, -1));

        jLabel7.setText("Số lượng ghế đôi");
        jPanel17.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 144, -1, -1));
        jPanel17.add(SlgDoiSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 141, 82, -1));

        PhongIdLabel.setText("Id");
        jPanel17.add(PhongIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 16, 37, -1));

        editPhongButton.setText("Cập nhật");
        jPanel17.add(editPhongButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        addPhongButton.setText("Thêm");
        jPanel17.add(addPhongButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, -1));

        clearPhongInfoButtom.setText("Làm trống");
        jPanel17.add(clearPhongInfoButtom, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, -1, -1));

        deletePhongButton.setText("Xóa");
        jPanel17.add(deletePhongButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        TieuChiSortPhongCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel17.add(TieuChiSortPhongCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 57, 156, -1));

        TchiTangPhong.setText("Tăng dần");
        jPanel17.add(TchiTangPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 17, 85, -1));

        SortPhongButton.setText("Sắp xếp");
        jPanel17.add(SortPhongButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 16, -1, -1));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 426, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel16);

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PhongTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Số lượng ghế thường", "Số lượng ghế Vip", "Số lượng ghế đôi", "Tổng số ghế", "Lấp đầy", "Sức chứa", "Phim chiếu", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(PhongTable);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel15);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("", jPanel2);

        jTabbedPane1.addTab("Quản lý phòng", jTabbedPane8);

        jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        Qlphim.setBackground(new java.awt.Color(204, 204, 204));
        Qlphim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QlphimMouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(247, 247, 247));
        jPanel5.setForeground(new java.awt.Color(204, 204, 204));

        jLabel13.setText("Id");

        IDPhimField.setEditable(false);

        jLabel15.setText("Tên phim");

        AddPhimButton.setText("Thêm");

        ClearPhimButton.setText("Làm trống");

        DeletePhimButton.setText("Xóa");

        EditPhimButton.setText("Cập nhật");

        jLabel16.setText("Thể loại");

        jLabel17.setText("Độ tuổi");

        theLoaiCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("Thời lượng");

        jLabel18.setText("Ngày khởi chiếu");

        TimKiemPhimField.setDisabledTextColor(new java.awt.Color(204, 204, 204));

        TimKiemBtn.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        TimKiemBtn.setText("Tìm kiếm");

        LuaChonPhim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên Phim", "Thể loại", "Độ tuổi" }));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddPhimButton)
                .addGap(26, 26, 26)
                .addComponent(EditPhimButton)
                .addGap(18, 18, 18)
                .addComponent(ClearPhimButton)
                .addGap(28, 28, 28)
                .addComponent(DeletePhimButton)
                .addGap(182, 182, 182))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(doTuoiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(theLoaiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ThoiLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TenPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(TimKiemBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LuaChonPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TimKiemPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NgayKhoiChieuField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(TimKiemBtn)
                    .addComponent(LuaChonPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(TimKiemPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(theLoaiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doTuoiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThoiLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(NgayKhoiChieuField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPhimButton)
                    .addComponent(DeletePhimButton)
                    .addComponent(EditPhimButton)
                    .addComponent(ClearPhimButton))
                .addGap(23, 23, 23))
        );

        IDPhimField.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Quản lý phim");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
                "ID", "Tên Phim", "Thời lượng", "Độ tuổi", "Thể loại", "Ngày khởi chiếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(BangPhim);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout QlphimLayout = new javax.swing.GroupLayout(Qlphim);
        Qlphim.setLayout(QlphimLayout);
        QlphimLayout.setHorizontalGroup(
            QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlphimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(QlphimLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        QlphimLayout.setVerticalGroup(
            QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlphimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(QlphimLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("", Qlphim);

        jTabbedPane1.addTab("Quản lý phim", jTabbedPane3);

        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

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

        Id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Id.setLabelFor(IdField);
        Id.setText("Id suất chiếu");

        IdField.setEditable(false);
        IdField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        phimChieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phimChieu.setLabelFor(phimCombo);
        phimChieu.setText("Phim được chiếu");

        phimCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        phongChieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phongChieu.setLabelFor(phongCombo);
        phongChieu.setText("Phòng");

        phongCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phongCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tgChieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tgChieu.setLabelFor(tgChieu);
        tgChieu.setText("Thời gian chiếu");

        xacNhanSchDateBtn.setText("Xác nhận");

        schDateTimeField.setEditable(false);

        schCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schCalendarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                schCalendarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                schCalendarMouseReleased(evt);
            }
        });

        jLabel1.setText("giờ");

        jLabel11.setText("phút");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(schDateTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xacNhanSchDateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(hourSchSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(23, 23, 23)
                        .addComponent(minsSchSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schDateTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xacNhanSchDateBtn))
                .addGap(3, 3, 3)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourSchSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minsSchSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(schCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        editButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editButton.setText("Cập nhật");

        addButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addButton.setText("Thêm");

        clearButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        clearButton.setText("Làm trống");

        deleteButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteButton.setText("Xóa");

        sortSchButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sortSchButton.setText("Sắp xếp");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tiêu chí");

        tieuChiSchCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tieuChiSchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tChiSchtangDan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tChiSchtangDan.setText("Tăng dần ");

        javax.swing.GroupLayout SuatChieuControllersLayout = new javax.swing.GroupLayout(SuatChieuControllers);
        SuatChieuControllers.setLayout(SuatChieuControllersLayout);
        SuatChieuControllersLayout.setHorizontalGroup(
            SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                        .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Id)
                            .addComponent(phimChieu)
                            .addComponent(phongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tgChieu)
                            .addComponent(editButton))
                        .addGap(8, 8, 8)
                        .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(IdField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phimCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(phongCombo, 0, 97, Short.MAX_VALUE)))
                            .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(addButton)
                                .addGap(38, 38, 38)
                                .addComponent(clearButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteButton))))
                    .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                        .addComponent(sortSchButton)
                        .addGap(18, 18, 18)
                        .addComponent(tChiSchtangDan))
                    .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tieuChiSchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SuatChieuControllersLayout.setVerticalGroup(
            SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phimChieu)
                    .addComponent(phimCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phongChieu)
                    .addComponent(phongCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(tgChieu))
                    .addGroup(SuatChieuControllersLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editButton)
                        .addComponent(addButton))
                    .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clearButton)
                        .addComponent(deleteButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortSchButton)
                    .addComponent(tChiSchtangDan))
                .addGap(18, 18, 18)
                .addGroup(SuatChieuControllersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tieuChiSchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        quanLySuatChieu.add(SuatChieuControllers, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 470, 540));

        jTabbedPane4.addTab("", quanLySuatChieu);

        jTabbedPane1.addTab("Quản lý suất chiếu", jTabbedPane4);

        jTabbedPane6.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        QlKhachHang.setBackground(new java.awt.Color(204, 204, 204));

        jPanel7.setBackground(new java.awt.Color(247, 247, 247));

        jPanel13.setBackground(new java.awt.Color(247, 247, 247));

        jLabel9.setText("Ngày Sinh");

        jLabel8.setText("Họ và tên");

        jLabel6.setText("Giới tính");

        NgaySinhField.setEditable(false);
        NgaySinhField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NgaySinhFieldActionPerformed(evt);
            }
        });

        AddKhach.setText("Thêm");
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

        EditKhach.setText("Cập nhật");
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

        DeleteKhach.setText("Xóa");
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

        SlvField.setEditable(false);

        clearKhach.setText("Làm trống");

        jLabel12.setText("ID");

        IDField.setEditable(false);
        IDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDFieldActionPerformed(evt);
            }
        });

        TimKhachbtn.setText("Tìm Kiếm");

        TimKiemKhachField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemKhachFieldActionPerformed(evt);
            }
        });

        LuaChonKhach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Số lượng vé" }));
        LuaChonKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LuaChonKhachActionPerformed(evt);
            }
        });

        gioiTinhCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        confirmNS.setText("Xác nhận");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(HoTenField, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(gioiTinhCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                            .addComponent(AddKhach)
                            .addGap(18, 18, 18)
                            .addComponent(EditKhach)
                            .addGap(18, 18, 18)
                            .addComponent(clearKhach)
                            .addGap(18, 18, 18)
                            .addComponent(DeleteKhach)
                            .addGap(6, 6, 6)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(SlvField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NgaySinhField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NSCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmNS, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(TimKhachbtn)
                        .addGap(30, 30, 30)
                        .addComponent(LuaChonKhach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(TimKiemKhachField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NgaySinhField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuaChonKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimKhachbtn))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HoTenField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TimKiemKhachField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gioiTinhCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(SlvField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddKhach)
                            .addComponent(EditKhach)
                            .addComponent(clearKhach)
                            .addComponent(DeleteKhach))
                        .addGap(64, 64, 64))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(confirmNS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NSCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        IDField.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Quản lý khách");

        javax.swing.GroupLayout QlKhachHangLayout = new javax.swing.GroupLayout(QlKhachHang);
        QlKhachHang.setLayout(QlKhachHangLayout);
        QlKhachHangLayout.setHorizontalGroup(
            QlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(QlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        QlKhachHangLayout.setVerticalGroup(
            QlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane6.addTab("", QlKhachHang);

        jTabbedPane1.addTab("Quản lý khách", jTabbedPane6);

        jTabbedPane7.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        QlDatVe.setBackground(new java.awt.Color(204, 204, 204));

        jPanel8.setBackground(new java.awt.Color(247, 247, 247));
        jPanel8.setForeground(new java.awt.Color(204, 204, 204));

        TimKiemPhimField1.setText("Tìm kiếm ");
        TimKiemPhimField1.setDisabledTextColor(new java.awt.Color(204, 204, 204));

        LuaChonPhim1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên Phim", "Thể loại", "Độ tuổi" }));

        TimKiemBtn1.setBackground(new java.awt.Color(196, 23, 23));
        TimKiemBtn1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        TimKiemBtn1.setForeground(new java.awt.Color(255, 255, 255));
        TimKiemBtn1.setText("Tìm kiếm");

        AddPhim1.setText("Add");

        ClearPhim1.setText("Clear");

        DeletePhim1.setText("Delete");

        EditPhim1.setText("Edit");

        jLabel28.setText("ID");

        jLabel29.setText("Vị trí ghế");

        jLabel48.setText("Tên phim");

        jLabel49.setText("Tên Khách Hàng");

        jLabel50.setText("Thời Lượng");

        jLabel51.setText("Phòng");

        jLabel52.setText("Loại ghế");

        jLabel53.setText("Thời gian chiếu");

        TimKiemPhimField2.setDisabledTextColor(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(LuaChonPhim4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LuaChonPhim7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LuaChonPhim6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(IDPhimField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TimKiemPhimField2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LuaChonPhim3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LuaChonPhim5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LuaChonPhim2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(82, 82, 82)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TimKiemPhimField1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LuaChonPhim1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TimKiemBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(AddPhim1)
                .addGap(43, 43, 43)
                .addComponent(EditPhim1)
                .addGap(37, 37, 37)
                .addComponent(DeletePhim1)
                .addGap(39, 39, 39)
                .addComponent(ClearPhim1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(TimKiemPhimField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LuaChonPhim5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(IDPhimField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel50)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(LuaChonPhim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(LuaChonPhim3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(LuaChonPhim4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel51)
                                .addComponent(LuaChonPhim2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LuaChonPhim7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LuaChonPhim6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel53)
                                .addComponent(TimKiemPhimField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddPhim1)
                            .addComponent(EditPhim1)
                            .addComponent(DeletePhim1)
                            .addComponent(ClearPhim1))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(TimKiemBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
        );

        IDPhimField1.setEditable(false);

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

        javax.swing.GroupLayout QlDatVeLayout = new javax.swing.GroupLayout(QlDatVe);
        QlDatVe.setLayout(QlDatVeLayout);
        QlDatVeLayout.setHorizontalGroup(
            QlDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlDatVeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QlDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)))
        );
        QlDatVeLayout.setVerticalGroup(
            QlDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlDatVeLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane7.addTab("", QlDatVe);

        jTabbedPane1.addTab("Quản lý vé", jTabbedPane7);

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng doanh thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tongDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(821, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setLayout(new java.awt.GridLayout(1, 2));

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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(sortDtPhimButton)
                        .addGap(18, 18, 18)
                        .addComponent(tieuChiDtPhim)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortDtPhimButton)
                    .addComponent(tieuChiDtPhim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo suất chiếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        DoanhThuSch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane5.setViewportView(DoanhThuSch);

        tieuChiDtSch.setText("Tăng dần");
        tieuChiDtSch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tieuChiDtSchActionPerformed(evt);
            }
        });

        sortDtSchButton.setText("Sắp xếp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sortDtSchButton)
                        .addGap(18, 18, 18)
                        .addComponent(tieuChiDtSch)
                        .addGap(0, 310, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortDtSchButton)
                    .addComponent(tieuChiDtSch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel3);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        DtPhongTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Sức chứa", "Số lượng suất chiếu", "Doanh thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(DtPhongTable);

        sortDtPhongButton.setText("Sắp xếp");

        tieuChiDtPhong.setText("Tăng dần");
        tieuChiDtPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tieuChiDtPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortDtPhongButton)
                .addGap(18, 18, 18)
                .addComponent(tieuChiDtPhong)
                .addContainerGap(316, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortDtPhongButton)
                    .addComponent(tieuChiDtPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.add(jPanel9);

        jLayeredPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(491, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addGap(90, 90, 90)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane2.addTab("", jLayeredPane1);

        jTabbedPane1.addTab("Doanh thu", jTabbedPane2);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1002, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jTabbedPane9.addTab("tab1", jPanel12);

        jTabbedPane1.addTab("tab8", jTabbedPane9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );

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

    private void tieuChiDtSchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tieuChiDtSchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tieuChiDtSchActionPerformed

    private void tieuChiDtPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tieuChiDtPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tieuChiDtPhongActionPerformed

    private void IdPhongFiledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdPhongFiledActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdPhongFiledActionPerformed

    private void schCalendarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schCalendarMouseClicked

    }//GEN-LAST:event_schCalendarMouseClicked

    private void schCalendarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schCalendarMouseReleased

    }//GEN-LAST:event_schCalendarMouseReleased

    private void schCalendarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schCalendarMousePressed

    }//GEN-LAST:event_schCalendarMousePressed

    private void LuaChonKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LuaChonKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LuaChonKhachActionPerformed

    private void TimKiemKhachFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemKhachFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemKhachFieldActionPerformed

    private void IDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDFieldActionPerformed

    private void DeleteKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteKhachActionPerformed

    private void DeleteKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteKhachMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteKhachMouseClicked

    private void EditKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditKhachActionPerformed

    private void EditKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditKhachMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EditKhachMouseClicked

    private void AddKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddKhachActionPerformed

    private void AddKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddKhachMouseClicked

    }//GEN-LAST:event_AddKhachMouseClicked

    private void NgaySinhFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NgaySinhFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NgaySinhFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddKhach;
    private javax.swing.JButton AddPhim1;
    private javax.swing.JButton AddPhimButton;
    private javax.swing.JTable BangKhachHang;
    private javax.swing.JTable BangPhim;
    private javax.swing.JTable BangVe;
    private javax.swing.JButton ClearPhim1;
    private javax.swing.JButton ClearPhimButton;
    private javax.swing.JButton DeleteKhach;
    private javax.swing.JButton DeletePhim1;
    private javax.swing.JButton DeletePhimButton;
    private javax.swing.JPanel DoanhThuSch;
    private javax.swing.JPanel DsPhimPane;
    private javax.swing.JScrollPane DsPhimScrollPane;
    private javax.swing.JPanel DsPhongPane;
    private javax.swing.JScrollPane DsPhongScrollPane;
    private javax.swing.JPanel DsSuatPane;
    private javax.swing.JScrollPane DsSuatScrollPane;
    private javax.swing.JTable DtPhongTable;
    private javax.swing.JButton EditKhach;
    private javax.swing.JButton EditPhim1;
    private javax.swing.JButton EditPhimButton;
    private javax.swing.JTextField HoTenField;
    private javax.swing.JLayeredPane Homepage;
    private javax.swing.JLabel HomepageTitle;
    private javax.swing.JTextField IDField;
    private javax.swing.JTextField IDPhimField;
    private javax.swing.JTextField IDPhimField1;
    private javax.swing.JLabel Id;
    private javax.swing.JTextField IdField;
    private javax.swing.JTextField IdPhongFiled;
    private javax.swing.JComboBox<String> LuaChonKhach;
    private javax.swing.JComboBox<String> LuaChonPhim;
    private javax.swing.JComboBox<String> LuaChonPhim1;
    private javax.swing.JComboBox<String> LuaChonPhim2;
    private javax.swing.JComboBox<String> LuaChonPhim3;
    private javax.swing.JComboBox<String> LuaChonPhim4;
    private javax.swing.JComboBox<String> LuaChonPhim5;
    private javax.swing.JComboBox<String> LuaChonPhim6;
    private javax.swing.JComboBox<String> LuaChonPhim7;
    private com.toedter.calendar.JCalendar NSCalendar;
    private javax.swing.JTextField NgayKhoiChieuField;
    private javax.swing.JTextField NgaySinhField;
    private javax.swing.JLabel PhongIdLabel;
    private javax.swing.JTable PhongTable;
    private javax.swing.JPanel QlDatVe;
    private javax.swing.JPanel QlKhachHang;
    private javax.swing.JPanel Qlphim;
    private javax.swing.JSpinner SlgDoiSpinner;
    private javax.swing.JLabel SlgThuongLabel;
    private javax.swing.JSpinner SlgThuongSpinner;
    private javax.swing.JSpinner SlgVipSpinner;
    private javax.swing.JTextField SlvField;
    private javax.swing.JButton SortPhongButton;
    private javax.swing.JPanel SuatChieuControllers;
    private javax.swing.JLabel SuatChieuManager;
    private javax.swing.JCheckBox TchiTangPhong;
    private javax.swing.JTextField TenPhimField;
    private javax.swing.JTextField ThoiLuongField;
    private javax.swing.JComboBox<String> TieuChiSortPhongCombo;
    private javax.swing.JButton TimKhachbtn;
    private javax.swing.JButton TimKiemBtn;
    private javax.swing.JButton TimKiemBtn1;
    private javax.swing.JTextField TimKiemKhachField;
    private javax.swing.JTextField TimKiemPhimField;
    private javax.swing.JTextField TimKiemPhimField1;
    private javax.swing.JTextField TimKiemPhimField2;
    private javax.swing.JButton addButton;
    private javax.swing.JButton addPhongButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton clearKhach;
    private javax.swing.JButton clearPhongInfoButtom;
    private javax.swing.JButton confirmNS;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deletePhongButton;
    private javax.swing.JSpinner doTuoiSpinner;
    private javax.swing.JPanel doanhThuPhim;
    private javax.swing.JButton editButton;
    private javax.swing.JButton editPhongButton;
    private javax.swing.JComboBox<String> gioiTinhCombo;
    private javax.swing.JSpinner hourSchSpinner;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTabbedPane jTabbedPane9;
    private javax.swing.JSpinner minsSchSpinner;
    private javax.swing.JLabel phimChieu;
    private javax.swing.JComboBox<String> phimCombo;
    private javax.swing.JLabel phongChieu;
    private javax.swing.JComboBox<String> phongCombo;
    private javax.swing.JLayeredPane quanLySuatChieu;
    private com.toedter.calendar.JCalendar schCalendar;
    private javax.swing.JTextField schDateTimeField;
    private javax.swing.JButton sortDtPhimButton;
    private javax.swing.JButton sortDtPhongButton;
    private javax.swing.JButton sortDtSchButton;
    private javax.swing.JButton sortSchButton;
    private javax.swing.JScrollPane suatChieuScroll;
    private javax.swing.JTable suatChieuTable;
    private javax.swing.JCheckBox tChiSchtangDan;
    private javax.swing.JLabel tgChieu;
    private javax.swing.JComboBox<String> theLoaiCombo;
    private javax.swing.JCheckBox tieuChiDtPhim;
    private javax.swing.JCheckBox tieuChiDtPhong;
    private javax.swing.JCheckBox tieuChiDtSch;
    private javax.swing.JComboBox<String> tieuChiSchCombo;
    private javax.swing.JLabel tongDoanhThu;
    private javax.swing.JButton xacNhanSchDateBtn;
    // End of variables declaration//GEN-END:variables
}
