/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

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

    public void showSuatChieu(SuatChieu sch) {
        this.IdField.setText("" + sch.getId());
        this.phimCombo.setSelectedItem(sch.getPhim().getTen());
        this.phongCombo.setSelectedItem(sch.getPhong().getId());
        this.tgChieuField.setText(sch.getThoiGianChieu().format(formatDateTime).toString());
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
                        .addGap(0, 709, Short.MAX_VALUE))
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
                .addContainerGap(808, Short.MAX_VALUE))
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
        doanhThuPhim.setLayout(new java.awt.GridLayout());
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
                .addContainerGap(756, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
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

        getContentPane().add(jTabbedPane1);

        getAccessibleContext().setAccessibleName("frame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void suatChieuTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suatChieuTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_suatChieuTableMouseClicked

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DsPhimPane;
    private javax.swing.JScrollPane DsPhimScrollPane;
    private javax.swing.JPanel DsPhongPane;
    private javax.swing.JScrollPane DsPhongScrollPane;
    private javax.swing.JPanel DsSuatPane;
    private javax.swing.JScrollPane DsSuatScrollPane;
    private javax.swing.JLayeredPane Homepage;
    private javax.swing.JLabel HomepageTitle;
    private javax.swing.JLabel Id;
    private javax.swing.JTextField IdField;
    private javax.swing.JLabel SuatChieuManager;
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel doanhThuPhim;
    private javax.swing.JButton editButton;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JLabel phimChieu;
    private javax.swing.JComboBox<String> phimCombo;
    private javax.swing.JLabel phongChieu;
    private javax.swing.JComboBox<String> phongCombo;
    private javax.swing.JLayeredPane quanLySuatChieu;
    private javax.swing.JButton sortButton;
    private javax.swing.JButton sortDtPhimButton;
    private javax.swing.JScrollPane suatChieuScroll;
    private javax.swing.JTable suatChieuTable;
    private javax.swing.JCheckBox tangDan;
    private javax.swing.JLabel tgChieu;
    private javax.swing.JTextField tgChieuField;
    private javax.swing.JCheckBox tieuChiDtPhim;
    private javax.swing.JComboBox<String> tieuChiSchCombo;
    private javax.swing.JLabel tongDoanhThu;
    // End of variables declaration//GEN-END:variables
}
