/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Lenovo
 */
public class ManagerView extends javax.swing.JFrame {

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
            posterLabel.setSize(200, 250);
            ImageIcon poster = this.getImage(ph.getPhim().getPosterLink(), posterLabel);
            posterLabel.setIcon(poster);
            JLabel phim = new javax.swing.JLabel();
            phim.setText(ph.getPhim().getTen());
            JLabel thoiGian = new javax.swing.JLabel();
            thoiGian.setText(ph.getThoiGianChieu().toString());
            JLabel phong = new javax.swing.JLabel();
            phong.setText(ph.getPhongId());
            suatPanel.setLayout(new java.awt.GridLayout(4, 0));
            suatPanel.add(posterLabel);
            suatPanel.add(phim);
            suatPanel.add(thoiGian);
            suatPanel.add(phong);
            DsSuatPane.add(suatPanel);
        }

    }

    public JPanel getDsPhimPane() {
        return DsPhimPane;
    }

    public void setDsPhimPane(JPanel DsPhimPane) {
        this.DsPhimPane = DsPhimPane;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Navigator = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Homepage = new javax.swing.JLayeredPane();
        HomepageTitle = new javax.swing.JLabel();
        DsPhimScrollPane = new javax.swing.JScrollPane();
        DsPhimPane = new javax.swing.JPanel();
        DsSuatScrollPane = new javax.swing.JScrollPane();
        DsSuatPane = new javax.swing.JPanel();
        DsPhongScrollPane = new javax.swing.JScrollPane();
        DsPhongPane = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Navigator.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setText("Trang chủ");

        javax.swing.GroupLayout NavigatorLayout = new javax.swing.GroupLayout(Navigator);
        Navigator.setLayout(NavigatorLayout);
        NavigatorLayout.setHorizontalGroup(
            NavigatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigatorLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        NavigatorLayout.setVerticalGroup(
            NavigatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigatorLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        DsPhimPane.setLayout(new java.awt.GridLayout());
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
        DsSuatPane.setLayout(new java.awt.GridLayout());
        DsSuatScrollPane.setViewportView(DsSuatPane);

        DsPhongScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        DsPhongScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        DsPhongScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        DsPhongScrollPane.setAlignmentX(0.0F);
        DsPhongScrollPane.setAlignmentY(0.0F);
        DsPhongScrollPane.setAutoscrolls(true);
        DsPhongScrollPane.setName(""); // NOI18N

        DsPhongPane.setLayout(new java.awt.GridLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));
        DsPhongPane.add(jPanel1);

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
                    .addComponent(HomepageTitle)
                    .addComponent(DsPhimScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DsPhongScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DsSuatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        HomepageLayout.setVerticalGroup(
            HomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomepageLayout.createSequentialGroup()
                .addComponent(HomepageTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsPhimScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(DsSuatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsPhongScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Navigator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Homepage, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Navigator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Homepage, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("frame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DsPhimPane;
    private javax.swing.JScrollPane DsPhimScrollPane;
    private javax.swing.JPanel DsPhongPane;
    private javax.swing.JScrollPane DsPhongScrollPane;
    private javax.swing.JPanel DsSuatPane;
    private javax.swing.JScrollPane DsSuatScrollPane;
    private javax.swing.JLayeredPane Homepage;
    private javax.swing.JLabel HomepageTitle;
    private javax.swing.JPanel Navigator;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
