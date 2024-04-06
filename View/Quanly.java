/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Classes.Khach;
import Classes.Phim;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class Quanly extends javax.swing.JFrame {
    int width = 229;
    int height = 690;
    private String [] columnNames = new String [] {
            "ID", "Name", "Ngày sinh", "Giới tính", "Số lượng vé", "Tổng tiền"};
    
    private String [] columnNames1 = new String [] {
            "ID", "Name", "Thể loại", "Độ tuổi", "Thời lượng", "Ngày khởi chiếu"};

    /**
     * Creates new form Quanly
     */
    public Quanly() {
        initComponents();
        
    }
    
    void openMenuBar(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                for (int i = 0; i <= 229; i++){
                    Menu.setSize(i,690);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                    }).start();
        
    }
    
    void closeMenuBar(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                for (int i = 229; i >= 0; i--){
                    Menu.setSize(i,690);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                    }).start();
    }
    
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    
    /// Thao tac voi khach hang
    public void showListKhach(List<Khach> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object [][] khach = new Object[size][6];
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
        if (!validateName() || !validateGioiTinh() || !validateSLV()){
            return null;
        }
        try {
            Khach khach = new Khach();
            if (HoTenField.getText() != null ) {
                
            }
            if (IDField.getText() != null && !"".equals(IDField.getText())) {
                khach.setId(IDField.getText().trim());
            }

            
            khach.setHoTen(HoTenField.getText().trim());
            khach.setGioiTinh(GioiTinhField.getText().trim());
            khach.setNgaySinh(NgaySinhField.getText().trim());
            khach.setSLV(Byte.parseByte(SlvField.getText().trim()));
            
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
      
      
      
     ////
      //Thao tac voi phim
      
       public void showListPhim(List<Phim> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object [][] phim = new Object[size][6];
        for (int i = 0; i < size; i++) {
           phim[i][0] = list.get(i).getId();
           phim[i][1] = list.get(i).getTen();
           phim[i][2] = list.get(i).getDoTuoi();
           phim[i][3] = list.get(i).getTheLoai();
           phim[i][4] = list.get(i).inTgKhoiChieu();
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
           // editStudentBtn.setEnabled(true);
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
            if (TenPhimField.getText() != null ) {
                
            }
            if (IDField.getText() != null && !"".equals(IDField.getText())) {
                phim.setId(Byte.parseByte(IDPhimField.getText().trim()));
            }

            
            phim.setTen(TenPhimField.getText().trim());
            phim.setTheLoai(TheLoaiField.getText().trim());
            phim.setDoTuoi(Byte.parseByte(DoTuoiField.getText().trim()));
            phim.setThoiLuong(Byte.parseByte(ThoiLuongField.getText().trim()));
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
        ThoiLuongField.setText("" + phim.getThoiLuong().toString());
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
       
    public void addAddPhimListener(ActionListener listener) {
        AddPhim.addActionListener(listener);
    }
     
    public void addClearPhimListener(ActionListener listener) {
        ClearPhim.addActionListener(listener);
    }

    
      
      
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        Trangchu = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jPanel15 = new javax.swing.JPanel();
        jButton262 = new javax.swing.JButton();
        jButton263 = new javax.swing.JButton();
        jButton264 = new javax.swing.JButton();
        jButton271 = new javax.swing.JButton();
        jButton272 = new javax.swing.JButton();
        jButton273 = new javax.swing.JButton();
        Qlphim = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        BangPhim = new javax.swing.JTable();
        QlKhachHang = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        BangKhachHang = new javax.swing.JTable();
        Qlsuatchieu = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        Phong1 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel17 = new javax.swing.JPanel();
        P1A1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        P1A2 = new javax.swing.JButton();
        P1A3 = new javax.swing.JButton();
        P1A4 = new javax.swing.JButton();
        P1A5 = new javax.swing.JButton();
        P1A6 = new javax.swing.JButton();
        P1B1 = new javax.swing.JButton();
        P1A7 = new javax.swing.JButton();
        P1C1 = new javax.swing.JButton();
        P1D1 = new javax.swing.JButton();
        P1E1 = new javax.swing.JButton();
        P1B3 = new javax.swing.JButton();
        P1B2 = new javax.swing.JButton();
        P1B7 = new javax.swing.JButton();
        P1B4 = new javax.swing.JButton();
        P1B5 = new javax.swing.JButton();
        P1B6 = new javax.swing.JButton();
        P1C7 = new javax.swing.JButton();
        P1C6 = new javax.swing.JButton();
        P1C5 = new javax.swing.JButton();
        P1C4 = new javax.swing.JButton();
        P1C3 = new javax.swing.JButton();
        P1C2 = new javax.swing.JButton();
        P1E7 = new javax.swing.JButton();
        P1D7 = new javax.swing.JButton();
        P1D6 = new javax.swing.JButton();
        P1D5 = new javax.swing.JButton();
        P1D4 = new javax.swing.JButton();
        P1D3 = new javax.swing.JButton();
        P1D2 = new javax.swing.JButton();
        P1E6 = new javax.swing.JButton();
        P1E5 = new javax.swing.JButton();
        P1E4 = new javax.swing.JButton();
        P1E3 = new javax.swing.JButton();
        P1E2 = new javax.swing.JButton();
        P1A8 = new javax.swing.JButton();
        P1B8 = new javax.swing.JButton();
        P1C8 = new javax.swing.JButton();
        P1D8 = new javax.swing.JButton();
        P1E8 = new javax.swing.JButton();
        P1F8 = new javax.swing.JButton();
        P1F7 = new javax.swing.JButton();
        P1F6 = new javax.swing.JButton();
        P1F5 = new javax.swing.JButton();
        P1F4 = new javax.swing.JButton();
        P1F3 = new javax.swing.JButton();
        P1F2 = new javax.swing.JButton();
        P1F1 = new javax.swing.JButton();
        P1G8 = new javax.swing.JButton();
        P1G7 = new javax.swing.JButton();
        P1G6 = new javax.swing.JButton();
        P1G5 = new javax.swing.JButton();
        P1G4 = new javax.swing.JButton();
        P1G2 = new javax.swing.JButton();
        P1G3 = new javax.swing.JButton();
        P1G1 = new javax.swing.JButton();
        Phong2 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jTextField61 = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane7 = new javax.swing.JTextPane();
        jPanel42 = new javax.swing.JPanel();
        jButton348 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jButton349 = new javax.swing.JButton();
        jButton350 = new javax.swing.JButton();
        jButton351 = new javax.swing.JButton();
        jButton352 = new javax.swing.JButton();
        jButton353 = new javax.swing.JButton();
        jButton354 = new javax.swing.JButton();
        jButton355 = new javax.swing.JButton();
        jButton356 = new javax.swing.JButton();
        jButton357 = new javax.swing.JButton();
        jButton358 = new javax.swing.JButton();
        jButton359 = new javax.swing.JButton();
        jButton360 = new javax.swing.JButton();
        jButton361 = new javax.swing.JButton();
        jButton362 = new javax.swing.JButton();
        jButton363 = new javax.swing.JButton();
        jButton364 = new javax.swing.JButton();
        jButton365 = new javax.swing.JButton();
        jButton366 = new javax.swing.JButton();
        jButton367 = new javax.swing.JButton();
        jButton368 = new javax.swing.JButton();
        jButton369 = new javax.swing.JButton();
        jButton370 = new javax.swing.JButton();
        jButton371 = new javax.swing.JButton();
        jButton372 = new javax.swing.JButton();
        jButton373 = new javax.swing.JButton();
        jButton374 = new javax.swing.JButton();
        jButton375 = new javax.swing.JButton();
        jButton376 = new javax.swing.JButton();
        jButton377 = new javax.swing.JButton();
        jButton378 = new javax.swing.JButton();
        jButton379 = new javax.swing.JButton();
        jButton380 = new javax.swing.JButton();
        jButton381 = new javax.swing.JButton();
        jButton382 = new javax.swing.JButton();
        jButton383 = new javax.swing.JButton();
        jButton384 = new javax.swing.JButton();
        jButton385 = new javax.swing.JButton();
        jButton386 = new javax.swing.JButton();
        jButton387 = new javax.swing.JButton();
        jButton388 = new javax.swing.JButton();
        jButton389 = new javax.swing.JButton();
        jButton390 = new javax.swing.JButton();
        jButton391 = new javax.swing.JButton();
        jButton392 = new javax.swing.JButton();
        jButton393 = new javax.swing.JButton();
        jButton394 = new javax.swing.JButton();
        jButton395 = new javax.swing.JButton();
        jButton396 = new javax.swing.JButton();
        jButton397 = new javax.swing.JButton();
        jButton398 = new javax.swing.JButton();
        jButton399 = new javax.swing.JButton();
        jButton400 = new javax.swing.JButton();
        jButton401 = new javax.swing.JButton();
        jButton402 = new javax.swing.JButton();
        jButton403 = new javax.swing.JButton();
        Phong3 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        jTextField63 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jTextField66 = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextPane8 = new javax.swing.JTextPane();
        jPanel44 = new javax.swing.JPanel();
        jButton404 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jButton405 = new javax.swing.JButton();
        jButton406 = new javax.swing.JButton();
        jButton407 = new javax.swing.JButton();
        jButton408 = new javax.swing.JButton();
        jButton409 = new javax.swing.JButton();
        jButton410 = new javax.swing.JButton();
        jButton411 = new javax.swing.JButton();
        jButton412 = new javax.swing.JButton();
        jButton413 = new javax.swing.JButton();
        jButton414 = new javax.swing.JButton();
        jButton415 = new javax.swing.JButton();
        jButton416 = new javax.swing.JButton();
        jButton417 = new javax.swing.JButton();
        jButton418 = new javax.swing.JButton();
        jButton419 = new javax.swing.JButton();
        jButton420 = new javax.swing.JButton();
        jButton421 = new javax.swing.JButton();
        jButton422 = new javax.swing.JButton();
        jButton423 = new javax.swing.JButton();
        jButton424 = new javax.swing.JButton();
        jButton425 = new javax.swing.JButton();
        jButton426 = new javax.swing.JButton();
        jButton427 = new javax.swing.JButton();
        jButton428 = new javax.swing.JButton();
        jButton429 = new javax.swing.JButton();
        jButton430 = new javax.swing.JButton();
        jButton431 = new javax.swing.JButton();
        jButton432 = new javax.swing.JButton();
        jButton433 = new javax.swing.JButton();
        jButton434 = new javax.swing.JButton();
        jButton435 = new javax.swing.JButton();
        jButton436 = new javax.swing.JButton();
        jButton437 = new javax.swing.JButton();
        jButton438 = new javax.swing.JButton();
        jButton439 = new javax.swing.JButton();
        jButton440 = new javax.swing.JButton();
        jButton441 = new javax.swing.JButton();
        jButton442 = new javax.swing.JButton();
        jButton443 = new javax.swing.JButton();
        jButton444 = new javax.swing.JButton();
        jButton445 = new javax.swing.JButton();
        jButton446 = new javax.swing.JButton();
        jButton447 = new javax.swing.JButton();
        jButton448 = new javax.swing.JButton();
        jButton449 = new javax.swing.JButton();
        jButton450 = new javax.swing.JButton();
        jButton451 = new javax.swing.JButton();
        jButton452 = new javax.swing.JButton();
        jButton453 = new javax.swing.JButton();
        jButton454 = new javax.swing.JButton();
        jButton455 = new javax.swing.JButton();
        jButton456 = new javax.swing.JButton();
        jButton457 = new javax.swing.JButton();
        jButton458 = new javax.swing.JButton();
        jButton459 = new javax.swing.JButton();
        Phong4 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jTextField67 = new javax.swing.JTextField();
        jTextField68 = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        jTextField70 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPane9 = new javax.swing.JTextPane();
        jPanel46 = new javax.swing.JPanel();
        jButton460 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jButton461 = new javax.swing.JButton();
        jButton462 = new javax.swing.JButton();
        jButton463 = new javax.swing.JButton();
        jButton464 = new javax.swing.JButton();
        jButton465 = new javax.swing.JButton();
        jButton466 = new javax.swing.JButton();
        jButton467 = new javax.swing.JButton();
        jButton468 = new javax.swing.JButton();
        jButton469 = new javax.swing.JButton();
        jButton470 = new javax.swing.JButton();
        jButton471 = new javax.swing.JButton();
        jButton472 = new javax.swing.JButton();
        jButton473 = new javax.swing.JButton();
        jButton474 = new javax.swing.JButton();
        jButton475 = new javax.swing.JButton();
        jButton476 = new javax.swing.JButton();
        jButton477 = new javax.swing.JButton();
        jButton478 = new javax.swing.JButton();
        jButton479 = new javax.swing.JButton();
        jButton480 = new javax.swing.JButton();
        jButton481 = new javax.swing.JButton();
        jButton482 = new javax.swing.JButton();
        jButton483 = new javax.swing.JButton();
        jButton484 = new javax.swing.JButton();
        jButton485 = new javax.swing.JButton();
        jButton486 = new javax.swing.JButton();
        jButton487 = new javax.swing.JButton();
        jButton488 = new javax.swing.JButton();
        jButton489 = new javax.swing.JButton();
        jButton490 = new javax.swing.JButton();
        jButton491 = new javax.swing.JButton();
        jButton492 = new javax.swing.JButton();
        jButton493 = new javax.swing.JButton();
        jButton494 = new javax.swing.JButton();
        jButton495 = new javax.swing.JButton();
        jButton496 = new javax.swing.JButton();
        jButton497 = new javax.swing.JButton();
        jButton498 = new javax.swing.JButton();
        jButton499 = new javax.swing.JButton();
        jButton500 = new javax.swing.JButton();
        jButton501 = new javax.swing.JButton();
        jButton502 = new javax.swing.JButton();
        jButton503 = new javax.swing.JButton();
        jButton504 = new javax.swing.JButton();
        jButton505 = new javax.swing.JButton();
        jButton506 = new javax.swing.JButton();
        jButton507 = new javax.swing.JButton();
        jButton508 = new javax.swing.JButton();
        jButton509 = new javax.swing.JButton();
        jButton510 = new javax.swing.JButton();
        jButton511 = new javax.swing.JButton();
        jButton512 = new javax.swing.JButton();
        jButton513 = new javax.swing.JButton();
        jButton514 = new javax.swing.JButton();
        jButton515 = new javax.swing.JButton();
        Phong5 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jTextField72 = new javax.swing.JTextField();
        jTextField73 = new javax.swing.JTextField();
        jTextField74 = new javax.swing.JTextField();
        jTextField75 = new javax.swing.JTextField();
        jTextField76 = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextPane10 = new javax.swing.JTextPane();
        jPanel48 = new javax.swing.JPanel();
        jButton516 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jButton517 = new javax.swing.JButton();
        jButton518 = new javax.swing.JButton();
        jButton519 = new javax.swing.JButton();
        jButton520 = new javax.swing.JButton();
        jButton521 = new javax.swing.JButton();
        jButton522 = new javax.swing.JButton();
        jButton523 = new javax.swing.JButton();
        jButton524 = new javax.swing.JButton();
        jButton525 = new javax.swing.JButton();
        jButton526 = new javax.swing.JButton();
        jButton527 = new javax.swing.JButton();
        jButton528 = new javax.swing.JButton();
        jButton529 = new javax.swing.JButton();
        jButton530 = new javax.swing.JButton();
        jButton531 = new javax.swing.JButton();
        jButton532 = new javax.swing.JButton();
        jButton533 = new javax.swing.JButton();
        jButton534 = new javax.swing.JButton();
        jButton535 = new javax.swing.JButton();
        jButton536 = new javax.swing.JButton();
        jButton537 = new javax.swing.JButton();
        jButton538 = new javax.swing.JButton();
        jButton539 = new javax.swing.JButton();
        jButton540 = new javax.swing.JButton();
        jButton541 = new javax.swing.JButton();
        jButton542 = new javax.swing.JButton();
        jButton543 = new javax.swing.JButton();
        jButton544 = new javax.swing.JButton();
        jButton545 = new javax.swing.JButton();
        jButton546 = new javax.swing.JButton();
        jButton547 = new javax.swing.JButton();
        jButton548 = new javax.swing.JButton();
        jButton549 = new javax.swing.JButton();
        jButton550 = new javax.swing.JButton();
        jButton551 = new javax.swing.JButton();
        jButton552 = new javax.swing.JButton();
        jButton553 = new javax.swing.JButton();
        jButton554 = new javax.swing.JButton();
        jButton555 = new javax.swing.JButton();
        jButton556 = new javax.swing.JButton();
        jButton557 = new javax.swing.JButton();
        jButton558 = new javax.swing.JButton();
        jButton559 = new javax.swing.JButton();
        jButton560 = new javax.swing.JButton();
        jButton561 = new javax.swing.JButton();
        jButton562 = new javax.swing.JButton();
        jButton563 = new javax.swing.JButton();
        jButton564 = new javax.swing.JButton();
        jButton565 = new javax.swing.JButton();
        jButton566 = new javax.swing.JButton();
        jButton567 = new javax.swing.JButton();
        jButton568 = new javax.swing.JButton();
        jButton569 = new javax.swing.JButton();
        jButton570 = new javax.swing.JButton();
        jButton571 = new javax.swing.JButton();
        Phong6 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jTextField77 = new javax.swing.JTextField();
        jTextField78 = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        jTextField80 = new javax.swing.JTextField();
        jTextField81 = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextPane11 = new javax.swing.JTextPane();
        jPanel50 = new javax.swing.JPanel();
        jButton572 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jButton573 = new javax.swing.JButton();
        jButton574 = new javax.swing.JButton();
        jButton575 = new javax.swing.JButton();
        jButton576 = new javax.swing.JButton();
        jButton577 = new javax.swing.JButton();
        jButton578 = new javax.swing.JButton();
        jButton579 = new javax.swing.JButton();
        jButton580 = new javax.swing.JButton();
        jButton581 = new javax.swing.JButton();
        jButton582 = new javax.swing.JButton();
        jButton583 = new javax.swing.JButton();
        jButton584 = new javax.swing.JButton();
        jButton585 = new javax.swing.JButton();
        jButton586 = new javax.swing.JButton();
        jButton587 = new javax.swing.JButton();
        jButton588 = new javax.swing.JButton();
        jButton589 = new javax.swing.JButton();
        jButton590 = new javax.swing.JButton();
        jButton591 = new javax.swing.JButton();
        jButton592 = new javax.swing.JButton();
        jButton593 = new javax.swing.JButton();
        jButton594 = new javax.swing.JButton();
        jButton595 = new javax.swing.JButton();
        jButton596 = new javax.swing.JButton();
        jButton597 = new javax.swing.JButton();
        jButton598 = new javax.swing.JButton();
        jButton599 = new javax.swing.JButton();
        jButton600 = new javax.swing.JButton();
        jButton601 = new javax.swing.JButton();
        jButton602 = new javax.swing.JButton();
        jButton603 = new javax.swing.JButton();
        jButton604 = new javax.swing.JButton();
        jButton605 = new javax.swing.JButton();
        jButton606 = new javax.swing.JButton();
        jButton607 = new javax.swing.JButton();
        jButton608 = new javax.swing.JButton();
        jButton609 = new javax.swing.JButton();
        jButton610 = new javax.swing.JButton();
        jButton611 = new javax.swing.JButton();
        jButton612 = new javax.swing.JButton();
        jButton613 = new javax.swing.JButton();
        jButton614 = new javax.swing.JButton();
        jButton615 = new javax.swing.JButton();
        jButton616 = new javax.swing.JButton();
        jButton617 = new javax.swing.JButton();
        jButton618 = new javax.swing.JButton();
        jButton619 = new javax.swing.JButton();
        jButton620 = new javax.swing.JButton();
        jButton621 = new javax.swing.JButton();
        jButton622 = new javax.swing.JButton();
        jButton623 = new javax.swing.JButton();
        jButton624 = new javax.swing.JButton();
        jButton625 = new javax.swing.JButton();
        jButton626 = new javax.swing.JButton();
        jButton627 = new javax.swing.JButton();
        Phong7 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jTextField82 = new javax.swing.JTextField();
        jTextField83 = new javax.swing.JTextField();
        jTextField84 = new javax.swing.JTextField();
        jTextField85 = new javax.swing.JTextField();
        jTextField86 = new javax.swing.JTextField();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextPane12 = new javax.swing.JTextPane();
        jPanel52 = new javax.swing.JPanel();
        jButton628 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jButton629 = new javax.swing.JButton();
        jButton630 = new javax.swing.JButton();
        jButton631 = new javax.swing.JButton();
        jButton632 = new javax.swing.JButton();
        jButton633 = new javax.swing.JButton();
        jButton634 = new javax.swing.JButton();
        jButton635 = new javax.swing.JButton();
        jButton636 = new javax.swing.JButton();
        jButton637 = new javax.swing.JButton();
        jButton638 = new javax.swing.JButton();
        jButton639 = new javax.swing.JButton();
        jButton640 = new javax.swing.JButton();
        jButton641 = new javax.swing.JButton();
        jButton642 = new javax.swing.JButton();
        jButton643 = new javax.swing.JButton();
        jButton644 = new javax.swing.JButton();
        jButton645 = new javax.swing.JButton();
        jButton646 = new javax.swing.JButton();
        jButton647 = new javax.swing.JButton();
        jButton648 = new javax.swing.JButton();
        jButton649 = new javax.swing.JButton();
        jButton650 = new javax.swing.JButton();
        jButton651 = new javax.swing.JButton();
        jButton652 = new javax.swing.JButton();
        jButton653 = new javax.swing.JButton();
        jButton654 = new javax.swing.JButton();
        jButton655 = new javax.swing.JButton();
        jButton656 = new javax.swing.JButton();
        jButton657 = new javax.swing.JButton();
        jButton658 = new javax.swing.JButton();
        jButton659 = new javax.swing.JButton();
        jButton660 = new javax.swing.JButton();
        jButton661 = new javax.swing.JButton();
        jButton662 = new javax.swing.JButton();
        jButton663 = new javax.swing.JButton();
        jButton664 = new javax.swing.JButton();
        jButton665 = new javax.swing.JButton();
        jButton666 = new javax.swing.JButton();
        jButton667 = new javax.swing.JButton();
        jButton668 = new javax.swing.JButton();
        jButton669 = new javax.swing.JButton();
        jButton670 = new javax.swing.JButton();
        jButton671 = new javax.swing.JButton();
        jButton672 = new javax.swing.JButton();
        jButton673 = new javax.swing.JButton();
        jButton674 = new javax.swing.JButton();
        jButton675 = new javax.swing.JButton();
        jButton676 = new javax.swing.JButton();
        jButton677 = new javax.swing.JButton();
        jButton678 = new javax.swing.JButton();
        jButton679 = new javax.swing.JButton();
        jButton680 = new javax.swing.JButton();
        jButton681 = new javax.swing.JButton();
        jButton682 = new javax.swing.JButton();
        jButton683 = new javax.swing.JButton();
        Phong8 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextPane6 = new javax.swing.JTextPane();
        jPanel40 = new javax.swing.JPanel();
        jButton292 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jButton293 = new javax.swing.JButton();
        jButton294 = new javax.swing.JButton();
        jButton295 = new javax.swing.JButton();
        jButton296 = new javax.swing.JButton();
        jButton297 = new javax.swing.JButton();
        jButton298 = new javax.swing.JButton();
        jButton299 = new javax.swing.JButton();
        jButton300 = new javax.swing.JButton();
        jButton301 = new javax.swing.JButton();
        jButton302 = new javax.swing.JButton();
        jButton303 = new javax.swing.JButton();
        jButton304 = new javax.swing.JButton();
        jButton305 = new javax.swing.JButton();
        jButton306 = new javax.swing.JButton();
        jButton307 = new javax.swing.JButton();
        jButton308 = new javax.swing.JButton();
        jButton309 = new javax.swing.JButton();
        jButton310 = new javax.swing.JButton();
        jButton311 = new javax.swing.JButton();
        jButton312 = new javax.swing.JButton();
        jButton313 = new javax.swing.JButton();
        jButton314 = new javax.swing.JButton();
        jButton315 = new javax.swing.JButton();
        jButton316 = new javax.swing.JButton();
        jButton317 = new javax.swing.JButton();
        jButton318 = new javax.swing.JButton();
        jButton319 = new javax.swing.JButton();
        jButton320 = new javax.swing.JButton();
        jButton321 = new javax.swing.JButton();
        jButton322 = new javax.swing.JButton();
        jButton323 = new javax.swing.JButton();
        jButton324 = new javax.swing.JButton();
        jButton325 = new javax.swing.JButton();
        jButton326 = new javax.swing.JButton();
        jButton327 = new javax.swing.JButton();
        jButton328 = new javax.swing.JButton();
        jButton329 = new javax.swing.JButton();
        jButton330 = new javax.swing.JButton();
        jButton331 = new javax.swing.JButton();
        jButton332 = new javax.swing.JButton();
        jButton333 = new javax.swing.JButton();
        jButton334 = new javax.swing.JButton();
        jButton335 = new javax.swing.JButton();
        jButton336 = new javax.swing.JButton();
        jButton337 = new javax.swing.JButton();
        jButton338 = new javax.swing.JButton();
        jButton339 = new javax.swing.JButton();
        jButton340 = new javax.swing.JButton();
        jButton341 = new javax.swing.JButton();
        jButton342 = new javax.swing.JButton();
        jButton343 = new javax.swing.JButton();
        jButton344 = new javax.swing.JButton();
        jButton345 = new javax.swing.JButton();
        jButton346 = new javax.swing.JButton();
        jButton347 = new javax.swing.JButton();
        Phong15 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jTextField87 = new javax.swing.JTextField();
        jTextField88 = new javax.swing.JTextField();
        jTextField89 = new javax.swing.JTextField();
        jTextField90 = new javax.swing.JTextField();
        jTextField91 = new javax.swing.JTextField();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextPane13 = new javax.swing.JTextPane();
        jPanel54 = new javax.swing.JPanel();
        jButton684 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jButton685 = new javax.swing.JButton();
        jButton686 = new javax.swing.JButton();
        jButton687 = new javax.swing.JButton();
        jButton688 = new javax.swing.JButton();
        jButton689 = new javax.swing.JButton();
        jButton690 = new javax.swing.JButton();
        jButton691 = new javax.swing.JButton();
        jButton692 = new javax.swing.JButton();
        jButton693 = new javax.swing.JButton();
        jButton694 = new javax.swing.JButton();
        jButton695 = new javax.swing.JButton();
        jButton696 = new javax.swing.JButton();
        jButton697 = new javax.swing.JButton();
        jButton698 = new javax.swing.JButton();
        jButton699 = new javax.swing.JButton();
        jButton700 = new javax.swing.JButton();
        jButton701 = new javax.swing.JButton();
        jButton702 = new javax.swing.JButton();
        jButton703 = new javax.swing.JButton();
        jButton704 = new javax.swing.JButton();
        jButton705 = new javax.swing.JButton();
        jButton706 = new javax.swing.JButton();
        jButton707 = new javax.swing.JButton();
        jButton708 = new javax.swing.JButton();
        jButton709 = new javax.swing.JButton();
        jButton710 = new javax.swing.JButton();
        jButton711 = new javax.swing.JButton();
        jButton712 = new javax.swing.JButton();
        jButton713 = new javax.swing.JButton();
        jButton714 = new javax.swing.JButton();
        jButton715 = new javax.swing.JButton();
        jButton716 = new javax.swing.JButton();
        jButton717 = new javax.swing.JButton();
        jButton718 = new javax.swing.JButton();
        jButton719 = new javax.swing.JButton();
        jButton720 = new javax.swing.JButton();
        jButton721 = new javax.swing.JButton();
        jButton722 = new javax.swing.JButton();
        jButton723 = new javax.swing.JButton();
        jButton724 = new javax.swing.JButton();
        jButton725 = new javax.swing.JButton();
        jButton726 = new javax.swing.JButton();
        jButton727 = new javax.swing.JButton();
        jButton728 = new javax.swing.JButton();
        jButton729 = new javax.swing.JButton();
        jButton730 = new javax.swing.JButton();
        jButton731 = new javax.swing.JButton();
        jButton732 = new javax.swing.JButton();
        jButton733 = new javax.swing.JButton();
        jButton734 = new javax.swing.JButton();
        jButton735 = new javax.swing.JButton();
        jButton736 = new javax.swing.JButton();
        jButton737 = new javax.swing.JButton();
        jButton738 = new javax.swing.JButton();
        jButton739 = new javax.swing.JButton();
        QlHoivien = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jTextField46 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel33 = new javax.swing.JPanel();
        jTextField47 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jButton266 = new javax.swing.JButton();
        jButton267 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jTextField50 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jButton265 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        DoanhThu = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        BieuDo = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        Menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ RẠP CHIẾU PHIM");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(996, 700));
        jPanel4.setLayout(new java.awt.CardLayout());

        Trangchu.setBackground(new java.awt.Color(255, 204, 204));

        jTextPane4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTextPane4.setForeground(new java.awt.Color(255, 51, 51));
        jTextPane4.setText("\t\tHỆ THỐNG QUẢN LÝ RẠP CHIẾU PHIM SIÊU CẤP VŨ TRỤ");
        jScrollPane6.setViewportView(jTextPane4);

        jPanel15.setBackground(new java.awt.Color(255, 204, 204));

        jButton262.setText("QUẢN LÝ SUẤT CHIẾU");
        jButton262.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton262MouseClicked(evt);
            }
        });

        jButton263.setText("QUẢN LÝ PHIM");
        jButton263.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton263MouseClicked(evt);
            }
        });

        jButton264.setText("QUẢN LÝ KHÁCH HÀNG");
        jButton264.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton264MouseClicked(evt);
            }
        });

        jButton271.setText("DOANH THU");
        jButton271.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton271MouseClicked(evt);
            }
        });

        jButton272.setText("TRANG CHỦ");
        jButton272.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton272MouseClicked(evt);
            }
        });

        jButton273.setText("QUẢN LÝ HỘI VIÊN");
        jButton273.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton273MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton272, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jButton273, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton271, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton264, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton262, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton263, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton272, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton264, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton262, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton273, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton271, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton263, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TrangchuLayout = new javax.swing.GroupLayout(Trangchu);
        Trangchu.setLayout(TrangchuLayout);
        TrangchuLayout.setHorizontalGroup(
            TrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TrangchuLayout.setVerticalGroup(
            TrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrangchuLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(Trangchu, "card5");

        Qlphim.setBackground(new java.awt.Color(204, 204, 204));
        Qlphim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QlphimMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(247, 247, 247));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        jTextField1.setText("Tìm kiếm tên phim");
        jTextField1.setDisabledTextColor(new java.awt.Color(204, 204, 204));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang phát hành", "Ngưng phát hành" }));

        jButton1.setBackground(new java.awt.Color(196, 23, 23));
        jButton1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm kiếm");

        DoTuoiField.setText("Độ tuổi");

        TheLoaiField.setText("Thể loại");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(75, 75, 75)
                                    .addComponent(AddPhim)
                                    .addGap(18, 18, 18)
                                    .addComponent(EditPhim)
                                    .addGap(18, 18, 18)
                                    .addComponent(DeletePhim))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TenPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ThoiLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(IDPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(11, 11, 11)))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ClearPhim))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NgayKhoiChieuField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TheLoaiField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DoTuoiField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IDPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(TheLoaiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DoTuoiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(TenPhimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ThoiLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(NgayKhoiChieuField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPhim)
                    .addComponent(DeletePhim)
                    .addComponent(EditPhim)
                    .addComponent(ClearPhim))
                .addGap(62, 62, 62))
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
                "ID", "Tên Phim", "Thời lượng", "Độ tuổi", "Thể loại", "Ngày khơi chiếu"
            }
        ));
        jScrollPane1.setViewportView(BangPhim);
        BangPhim.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout QlphimLayout = new javax.swing.GroupLayout(Qlphim);
        Qlphim.setLayout(QlphimLayout);
        QlphimLayout.setHorizontalGroup(
            QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlphimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
        QlphimLayout.setVerticalGroup(
            QlphimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlphimLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.add(Qlphim, "card3");

        QlKhachHang.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(247, 247, 247));

        jPanel12.setBackground(new java.awt.Color(247, 247, 247));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo", "ID", "Name", "Ngày đăng ký" }));

        jTextField2.setText("Tìm kiếm");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
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
        jScrollPane2.setViewportView(BangKhachHang);

        javax.swing.GroupLayout QlKhachHangLayout = new javax.swing.GroupLayout(QlKhachHang);
        QlKhachHang.setLayout(QlKhachHangLayout);
        QlKhachHangLayout.setHorizontalGroup(
            QlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlKhachHangLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(QlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE))
                .addContainerGap())
        );
        QlKhachHangLayout.setVerticalGroup(
            QlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlKhachHangLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(QlKhachHang, "card4");

        Qlsuatchieu.setBackground(new java.awt.Color(204, 204, 204));
        Qlsuatchieu.setMinimumSize(new java.awt.Dimension(765, 592));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setForeground(new java.awt.Color(204, 204, 204));

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        jButton3.setText("Phòng 1");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel11.add(jButton3);

        jButton4.setText("Phòng 2");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jPanel11.add(jButton4);

        jButton5.setText("Phòng 3");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel11.add(jButton5);

        jButton6.setText("Phòng 4");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jPanel11.add(jButton6);

        jButton7.setText("Phòng 5");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton7);

        jButton8.setText("Phòng 6");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jPanel11.add(jButton8);

        jButton9.setText("Phòng 7");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jPanel11.add(jButton9);

        jButton10.setText("Phòng 8");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jPanel11.add(jButton10);

        jButton11.setText("Phòng 9");
        jPanel11.add(jButton11);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 154, Short.MAX_VALUE))
        );

        jPanel14.setLayout(new java.awt.CardLayout());

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setText("Ảnh phim");

        jTextField11.setText("Tên phim");

        jTextField12.setText("Tên phim");

        jTextField13.setText("Tên phim");

        jTextField14.setText("Tên phim");

        jTextField15.setText("Tên phim");

        jTextPane1.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane1.setText("Phòng 1");
        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel17.setBackground(new java.awt.Color(255, 204, 204));

        P1A1.setText("A1");

        jLabel11.setBackground(new java.awt.Color(255, 204, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("MÀN HÌNH CHIẾU");

        P1A2.setText("A2");

        P1A3.setText("A3");

        P1A4.setText("A4");

        P1A5.setText("A5");

        P1A6.setText("A6");

        P1B1.setText("B1");

        P1A7.setText("A7");
        P1A7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1A7ActionPerformed(evt);
            }
        });

        P1C1.setText("C1");

        P1D1.setText("D1");

        P1E1.setText("E1");

        P1B3.setText("B3");

        P1B2.setText("B2");

        P1B7.setText("B7");

        P1B4.setText("B4");

        P1B5.setText("B5");

        P1B6.setText("B6");

        P1C7.setText("C7");

        P1C6.setText("C6");

        P1C5.setText("C5");

        P1C4.setText("C4");

        P1C3.setText("C3");

        P1C2.setText("C2");

        P1E7.setText("E7");

        P1D7.setText("D7");

        P1D6.setText("D6");

        P1D5.setText("D5");

        P1D4.setText("D4");

        P1D3.setText("D3");

        P1D2.setText("D2");

        P1E6.setText("E6");

        P1E5.setText("E5");

        P1E4.setText("E4");

        P1E3.setText("E3");

        P1E2.setText("E2");

        P1A8.setText("A8");

        P1B8.setText("B8");

        P1C8.setText("C8");

        P1D8.setText("D8");

        P1E8.setText("E8");

        P1F8.setText("F8");

        P1F7.setText("F7");

        P1F6.setText("F6");

        P1F5.setText("F5");
        P1F5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1F5ActionPerformed(evt);
            }
        });

        P1F4.setText("F4");

        P1F3.setText("F3");

        P1F2.setText("F2");

        P1F1.setText("F1");

        P1G8.setText("G8");

        P1G7.setText("G7");

        P1G6.setText("G6");

        P1G5.setText("G5");

        P1G4.setText("G4");

        P1G2.setBackground(new java.awt.Color(255, 102, 102));
        P1G2.setText("G2");

        P1G3.setBackground(new java.awt.Color(255, 102, 102));
        P1G3.setText("G3");

        P1G1.setBackground(new java.awt.Color(255, 102, 102));
        P1G1.setText("G1");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P1A8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P1B8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P1C8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P1D8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P1E8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addComponent(P1A7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(P1A6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(P1A5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(P1A4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(P1A3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(P1A2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(P1C7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P1B7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P1D7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P1E7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(P1B6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P1C6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P1D6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P1E6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(P1E5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(P1E4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(P1D5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(P1D4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(P1B5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(P1B4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(P1C5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(P1C4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(P1B3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(P1B2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(P1C3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(P1C2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(P1D3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(P1D2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(P1E3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(P1E2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(P1E1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(P1D1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(P1C1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(P1B1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(P1A1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(P1G8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(P1G7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1G6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1G5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1G4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1G3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1G2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1G1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(P1F8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1F7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1F6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1F5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1F4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1F3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P1F2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1F1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P1A1)
                    .addComponent(P1A2)
                    .addComponent(P1A3)
                    .addComponent(P1A4)
                    .addComponent(P1A5)
                    .addComponent(P1A6)
                    .addComponent(P1A7)
                    .addComponent(P1A8))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P1B1)
                    .addComponent(P1B2)
                    .addComponent(P1B3)
                    .addComponent(P1B4)
                    .addComponent(P1B5)
                    .addComponent(P1B6)
                    .addComponent(P1B7)
                    .addComponent(P1B8))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P1C1)
                    .addComponent(P1C7)
                    .addComponent(P1C6)
                    .addComponent(P1C5)
                    .addComponent(P1C4)
                    .addComponent(P1C3)
                    .addComponent(P1C2)
                    .addComponent(P1C8))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P1D1)
                    .addComponent(P1D7)
                    .addComponent(P1D6)
                    .addComponent(P1D5)
                    .addComponent(P1D4)
                    .addComponent(P1D3)
                    .addComponent(P1D2)
                    .addComponent(P1D8))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P1E1)
                    .addComponent(P1E7)
                    .addComponent(P1E6)
                    .addComponent(P1E5)
                    .addComponent(P1E4)
                    .addComponent(P1E3)
                    .addComponent(P1E2)
                    .addComponent(P1E8))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P1F8)
                    .addComponent(P1F7)
                    .addComponent(P1F6)
                    .addComponent(P1F5)
                    .addComponent(P1F4)
                    .addComponent(P1F3)
                    .addComponent(P1F2)
                    .addComponent(P1F1))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P1G1)
                    .addComponent(P1G2)
                    .addComponent(P1G3)
                    .addComponent(P1G4)
                    .addComponent(P1G5)
                    .addComponent(P1G6)
                    .addComponent(P1G7)
                    .addComponent(P1G8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong1Layout = new javax.swing.GroupLayout(Phong1);
        Phong1.setLayout(Phong1Layout);
        Phong1Layout.setHorizontalGroup(
            Phong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong1Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong1Layout.setVerticalGroup(
            Phong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong1Layout.createSequentialGroup()
                .addGroup(Phong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong1, "card2");

        jPanel41.setBackground(new java.awt.Color(204, 204, 204));

        jLabel34.setText("Ảnh phim");

        jTextField57.setText("Tên phim");

        jTextField58.setText("Tên phim");

        jTextField59.setText("Tên phim");

        jTextField60.setText("Tên phim");

        jTextField61.setText("Tên phim");

        jTextPane7.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane7.setText("Phòng 2");
        jScrollPane11.setViewportView(jTextPane7);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel42.setBackground(new java.awt.Color(255, 204, 204));

        jButton348.setText("A1");

        jLabel35.setBackground(new java.awt.Color(255, 204, 204));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("MÀN HÌNH CHIẾU");

        jButton349.setText("A2");

        jButton350.setText("A3");

        jButton351.setText("A4");

        jButton352.setText("A5");

        jButton353.setText("A6");

        jButton354.setText("B1");

        jButton355.setText("A7");

        jButton356.setText("C1");

        jButton357.setText("D1");

        jButton358.setText("E1");

        jButton359.setText("B3");

        jButton360.setText("B2");

        jButton361.setText("B7");

        jButton362.setText("B4");

        jButton363.setText("B5");

        jButton364.setText("B6");

        jButton365.setText("C7");

        jButton366.setText("C6");

        jButton367.setText("C5");

        jButton368.setText("C4");

        jButton369.setText("C3");

        jButton370.setText("C2");

        jButton371.setText("E7");

        jButton372.setText("D7");

        jButton373.setText("D6");

        jButton374.setText("D5");

        jButton375.setText("D4");

        jButton376.setText("D3");

        jButton377.setText("D2");

        jButton378.setText("E6");

        jButton379.setText("E5");

        jButton380.setText("E4");

        jButton381.setText("E3");

        jButton382.setText("E2");

        jButton383.setText("A8");

        jButton384.setText("B8");

        jButton385.setText("C8");

        jButton386.setText("D8");

        jButton387.setText("E8");

        jButton388.setText("F8");

        jButton389.setText("F7");

        jButton390.setText("F6");

        jButton391.setText("F5");
        jButton391.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton391ActionPerformed(evt);
            }
        });

        jButton392.setText("F4");

        jButton393.setText("F3");

        jButton394.setText("F2");

        jButton395.setText("F1");

        jButton396.setText("G8");

        jButton397.setText("G7");

        jButton398.setText("G6");

        jButton399.setText("G5");

        jButton400.setText("G4");

        jButton401.setText("G2");

        jButton402.setText("G3");

        jButton403.setText("G1");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton387, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton386, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton385, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton384, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton383, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                                        .addComponent(jButton355, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton353, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton352, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton351, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton350, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton349, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton365, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton361, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton372, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton371, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton364, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton366, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton373, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton378, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jButton379, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton380, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jButton374, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton375, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jButton363, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton362, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jButton367, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton368, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jButton359, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton360, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jButton369, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton370, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jButton376, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton377, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jButton381, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton382, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton358, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton357, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton356, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton354, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton348, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addComponent(jButton396, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton397, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton398, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton399, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton400, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton402, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton401, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton403, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addComponent(jButton388, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton389, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton390, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton391, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton392, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton393, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton394, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton395, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton348)
                    .addComponent(jButton349)
                    .addComponent(jButton350)
                    .addComponent(jButton351)
                    .addComponent(jButton352)
                    .addComponent(jButton353)
                    .addComponent(jButton355)
                    .addComponent(jButton383))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton354)
                    .addComponent(jButton360)
                    .addComponent(jButton359)
                    .addComponent(jButton362)
                    .addComponent(jButton363)
                    .addComponent(jButton364)
                    .addComponent(jButton361)
                    .addComponent(jButton384))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton356)
                    .addComponent(jButton365)
                    .addComponent(jButton366)
                    .addComponent(jButton367)
                    .addComponent(jButton368)
                    .addComponent(jButton369)
                    .addComponent(jButton370)
                    .addComponent(jButton385))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton357)
                    .addComponent(jButton372)
                    .addComponent(jButton373)
                    .addComponent(jButton374)
                    .addComponent(jButton375)
                    .addComponent(jButton376)
                    .addComponent(jButton377)
                    .addComponent(jButton386))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton358)
                    .addComponent(jButton371)
                    .addComponent(jButton378)
                    .addComponent(jButton379)
                    .addComponent(jButton380)
                    .addComponent(jButton381)
                    .addComponent(jButton382)
                    .addComponent(jButton387))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton388)
                    .addComponent(jButton389)
                    .addComponent(jButton390)
                    .addComponent(jButton391)
                    .addComponent(jButton392)
                    .addComponent(jButton393)
                    .addComponent(jButton394)
                    .addComponent(jButton395))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton403)
                    .addComponent(jButton401)
                    .addComponent(jButton402)
                    .addComponent(jButton400)
                    .addComponent(jButton399)
                    .addComponent(jButton398)
                    .addComponent(jButton397)
                    .addComponent(jButton396))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong2Layout = new javax.swing.GroupLayout(Phong2);
        Phong2.setLayout(Phong2Layout);
        Phong2Layout.setHorizontalGroup(
            Phong2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong2Layout.createSequentialGroup()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong2Layout.setVerticalGroup(
            Phong2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong2Layout.createSequentialGroup()
                .addGroup(Phong2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong2, "card2");

        jPanel43.setBackground(new java.awt.Color(204, 204, 204));

        jLabel36.setText("Ảnh phim");

        jTextField62.setText("Tên phim");

        jTextField63.setText("Tên phim");

        jTextField64.setText("Tên phim");

        jTextField65.setText("Tên phim");

        jTextField66.setText("Tên phim");

        jTextPane8.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane8.setText("Phòng 3");
        jScrollPane12.setViewportView(jTextPane8);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel44.setBackground(new java.awt.Color(255, 204, 204));

        jButton404.setText("A1");

        jLabel37.setBackground(new java.awt.Color(255, 204, 204));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("MÀN HÌNH CHIẾU");

        jButton405.setText("A2");

        jButton406.setText("A3");

        jButton407.setText("A4");

        jButton408.setText("A5");

        jButton409.setText("A6");

        jButton410.setText("B1");

        jButton411.setText("A7");

        jButton412.setText("C1");

        jButton413.setText("D1");

        jButton414.setText("E1");

        jButton415.setText("B3");

        jButton416.setText("B2");

        jButton417.setText("B7");

        jButton418.setText("B4");

        jButton419.setText("B5");

        jButton420.setText("B6");

        jButton421.setText("C7");

        jButton422.setText("C6");

        jButton423.setText("C5");

        jButton424.setText("C4");

        jButton425.setText("C3");

        jButton426.setText("C2");

        jButton427.setText("E7");

        jButton428.setText("D7");

        jButton429.setText("D6");

        jButton430.setText("D5");

        jButton431.setText("D4");

        jButton432.setText("D3");

        jButton433.setText("D2");

        jButton434.setText("E6");

        jButton435.setText("E5");

        jButton436.setText("E4");

        jButton437.setText("E3");

        jButton438.setText("E2");

        jButton439.setText("A8");

        jButton440.setText("B8");

        jButton441.setText("C8");

        jButton442.setText("D8");

        jButton443.setText("E8");

        jButton444.setText("F8");

        jButton445.setText("F7");

        jButton446.setText("F6");

        jButton447.setText("F5");
        jButton447.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton447ActionPerformed(evt);
            }
        });

        jButton448.setText("F4");

        jButton449.setText("F3");

        jButton450.setText("F2");

        jButton451.setText("F1");

        jButton452.setText("G8");

        jButton453.setText("G7");

        jButton454.setText("G6");

        jButton455.setText("G5");

        jButton456.setText("G4");

        jButton457.setText("G2");

        jButton458.setText("G3");

        jButton459.setText("G1");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton440, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton442, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton443, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton441, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton439, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                                        .addComponent(jButton411, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton409, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton408, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton407, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton406, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton405, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton421, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton417, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton428, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton427, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton420, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton422, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton429, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton434, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel44Layout.createSequentialGroup()
                                                .addComponent(jButton435, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton436, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel44Layout.createSequentialGroup()
                                                .addComponent(jButton430, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton431, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel44Layout.createSequentialGroup()
                                                .addComponent(jButton419, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton418, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel44Layout.createSequentialGroup()
                                                .addComponent(jButton423, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton424, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel44Layout.createSequentialGroup()
                                                .addComponent(jButton415, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton416, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel44Layout.createSequentialGroup()
                                                .addComponent(jButton425, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton426, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel44Layout.createSequentialGroup()
                                                .addComponent(jButton432, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton433, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel44Layout.createSequentialGroup()
                                                .addComponent(jButton437, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton438, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton414, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton413, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton412, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton410, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton404, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jButton452, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton453, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton454, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton455, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton456, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton458, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton457, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton459, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jButton444, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton445, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton446, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton447, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton448, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton449, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton450, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton451, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton404)
                    .addComponent(jButton405)
                    .addComponent(jButton406)
                    .addComponent(jButton407)
                    .addComponent(jButton408)
                    .addComponent(jButton409)
                    .addComponent(jButton411)
                    .addComponent(jButton439))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton410)
                    .addComponent(jButton416)
                    .addComponent(jButton415)
                    .addComponent(jButton418)
                    .addComponent(jButton419)
                    .addComponent(jButton420)
                    .addComponent(jButton417)
                    .addComponent(jButton440))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton412)
                    .addComponent(jButton421)
                    .addComponent(jButton422)
                    .addComponent(jButton423)
                    .addComponent(jButton424)
                    .addComponent(jButton425)
                    .addComponent(jButton426)
                    .addComponent(jButton441))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton413)
                    .addComponent(jButton428)
                    .addComponent(jButton429)
                    .addComponent(jButton430)
                    .addComponent(jButton431)
                    .addComponent(jButton432)
                    .addComponent(jButton433)
                    .addComponent(jButton442))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton414)
                    .addComponent(jButton427)
                    .addComponent(jButton434)
                    .addComponent(jButton435)
                    .addComponent(jButton436)
                    .addComponent(jButton437)
                    .addComponent(jButton438)
                    .addComponent(jButton443))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton444)
                    .addComponent(jButton445)
                    .addComponent(jButton446)
                    .addComponent(jButton447)
                    .addComponent(jButton448)
                    .addComponent(jButton449)
                    .addComponent(jButton450)
                    .addComponent(jButton451))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton459)
                    .addComponent(jButton457)
                    .addComponent(jButton458)
                    .addComponent(jButton456)
                    .addComponent(jButton455)
                    .addComponent(jButton454)
                    .addComponent(jButton453)
                    .addComponent(jButton452))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong3Layout = new javax.swing.GroupLayout(Phong3);
        Phong3.setLayout(Phong3Layout);
        Phong3Layout.setHorizontalGroup(
            Phong3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong3Layout.createSequentialGroup()
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong3Layout.setVerticalGroup(
            Phong3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong3Layout.createSequentialGroup()
                .addGroup(Phong3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong3, "card2");

        jPanel45.setBackground(new java.awt.Color(204, 204, 204));

        jLabel38.setText("Ảnh phim");

        jTextField67.setText("Tên phim");

        jTextField68.setText("Tên phim");

        jTextField69.setText("Tên phim");

        jTextField70.setText("Tên phim");

        jTextField71.setText("Tên phim");

        jTextPane9.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane9.setText("Phòng 4");
        jScrollPane13.setViewportView(jTextPane9);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField69, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField68, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField71, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField70, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField67, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel46.setBackground(new java.awt.Color(255, 204, 204));

        jButton460.setText("A1");

        jLabel39.setBackground(new java.awt.Color(255, 204, 204));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("MÀN HÌNH CHIẾU");

        jButton461.setText("A2");

        jButton462.setText("A3");

        jButton463.setText("A4");

        jButton464.setText("A5");

        jButton465.setText("A6");

        jButton466.setText("B1");

        jButton467.setText("A7");

        jButton468.setText("C1");

        jButton469.setText("D1");

        jButton470.setText("E1");

        jButton471.setText("B3");

        jButton472.setText("B2");

        jButton473.setText("B7");

        jButton474.setText("B4");

        jButton475.setText("B5");

        jButton476.setText("B6");

        jButton477.setText("C7");

        jButton478.setText("C6");

        jButton479.setText("C5");

        jButton480.setText("C4");

        jButton481.setText("C3");

        jButton482.setText("C2");

        jButton483.setText("E7");

        jButton484.setText("D7");

        jButton485.setText("D6");

        jButton486.setText("D5");

        jButton487.setText("D4");

        jButton488.setText("D3");

        jButton489.setText("D2");

        jButton490.setText("E6");

        jButton491.setText("E5");

        jButton492.setText("E4");

        jButton493.setText("E3");

        jButton494.setText("E2");

        jButton495.setText("A8");

        jButton496.setText("B8");

        jButton497.setText("C8");

        jButton498.setText("D8");

        jButton499.setText("E8");

        jButton500.setText("F8");

        jButton501.setText("F7");

        jButton502.setText("F6");

        jButton503.setText("F5");
        jButton503.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton503ActionPerformed(evt);
            }
        });

        jButton504.setText("F4");

        jButton505.setText("F3");

        jButton506.setText("F2");

        jButton507.setText("F1");

        jButton508.setText("G8");

        jButton509.setText("G7");

        jButton510.setText("G6");

        jButton511.setText("G5");

        jButton512.setText("G4");

        jButton513.setText("G2");

        jButton514.setText("G3");

        jButton515.setText("G1");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton499, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton498, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton497, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton496, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton495, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                                        .addComponent(jButton467, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton465, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton464, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton463, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton462, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton461, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton477, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton473, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton484, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton483, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton476, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton478, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton485, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton490, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                .addComponent(jButton491, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton492, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                .addComponent(jButton486, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton487, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                .addComponent(jButton475, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton474, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                .addComponent(jButton479, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton480, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                .addComponent(jButton471, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton472, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                .addComponent(jButton481, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton482, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                .addComponent(jButton488, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton489, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                .addComponent(jButton493, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton494, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton470, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton469, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton468, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton466, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton460, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addComponent(jButton508, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton509, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton510, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton511, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton512, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton514, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton513, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton515, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addComponent(jButton500, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton501, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton502, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton503, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton504, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton505, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton506, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton507, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton460)
                    .addComponent(jButton461)
                    .addComponent(jButton462)
                    .addComponent(jButton463)
                    .addComponent(jButton464)
                    .addComponent(jButton465)
                    .addComponent(jButton467)
                    .addComponent(jButton495))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton466)
                    .addComponent(jButton472)
                    .addComponent(jButton471)
                    .addComponent(jButton474)
                    .addComponent(jButton475)
                    .addComponent(jButton476)
                    .addComponent(jButton473)
                    .addComponent(jButton496))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton468)
                    .addComponent(jButton477)
                    .addComponent(jButton478)
                    .addComponent(jButton479)
                    .addComponent(jButton480)
                    .addComponent(jButton481)
                    .addComponent(jButton482)
                    .addComponent(jButton497))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton469)
                    .addComponent(jButton484)
                    .addComponent(jButton485)
                    .addComponent(jButton486)
                    .addComponent(jButton487)
                    .addComponent(jButton488)
                    .addComponent(jButton489)
                    .addComponent(jButton498))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton470)
                    .addComponent(jButton483)
                    .addComponent(jButton490)
                    .addComponent(jButton491)
                    .addComponent(jButton492)
                    .addComponent(jButton493)
                    .addComponent(jButton494)
                    .addComponent(jButton499))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton500)
                    .addComponent(jButton501)
                    .addComponent(jButton502)
                    .addComponent(jButton503)
                    .addComponent(jButton504)
                    .addComponent(jButton505)
                    .addComponent(jButton506)
                    .addComponent(jButton507))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton515)
                    .addComponent(jButton513)
                    .addComponent(jButton514)
                    .addComponent(jButton512)
                    .addComponent(jButton511)
                    .addComponent(jButton510)
                    .addComponent(jButton509)
                    .addComponent(jButton508))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong4Layout = new javax.swing.GroupLayout(Phong4);
        Phong4.setLayout(Phong4Layout);
        Phong4Layout.setHorizontalGroup(
            Phong4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong4Layout.createSequentialGroup()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong4Layout.setVerticalGroup(
            Phong4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong4Layout.createSequentialGroup()
                .addGroup(Phong4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong4, "card2");

        jPanel47.setBackground(new java.awt.Color(204, 204, 204));

        jLabel40.setText("Ảnh phim");

        jTextField72.setText("Tên phim");

        jTextField73.setText("Tên phim");

        jTextField74.setText("Tên phim");

        jTextField75.setText("Tên phim");

        jTextField76.setText("Tên phim");

        jTextPane10.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane10.setText("Phòng 5");
        jScrollPane14.setViewportView(jTextPane10);

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField74, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField73, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField76, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField75, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField72, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel48.setBackground(new java.awt.Color(255, 204, 204));

        jButton516.setText("A1");

        jLabel41.setBackground(new java.awt.Color(255, 204, 204));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("MÀN HÌNH CHIẾU");

        jButton517.setText("A2");

        jButton518.setText("A3");

        jButton519.setText("A4");

        jButton520.setText("A5");

        jButton521.setText("A6");

        jButton522.setText("B1");

        jButton523.setText("A7");

        jButton524.setText("C1");

        jButton525.setText("D1");

        jButton526.setText("E1");

        jButton527.setText("B3");

        jButton528.setText("B2");

        jButton529.setText("B7");

        jButton530.setText("B4");

        jButton531.setText("B5");

        jButton532.setText("B6");

        jButton533.setText("C7");

        jButton534.setText("C6");

        jButton535.setText("C5");

        jButton536.setText("C4");

        jButton537.setText("C3");

        jButton538.setText("C2");

        jButton539.setText("E7");

        jButton540.setText("D7");

        jButton541.setText("D6");

        jButton542.setText("D5");

        jButton543.setText("D4");

        jButton544.setText("D3");

        jButton545.setText("D2");

        jButton546.setText("E6");

        jButton547.setText("E5");

        jButton548.setText("E4");

        jButton549.setText("E3");

        jButton550.setText("E2");

        jButton551.setText("A8");

        jButton552.setText("B8");

        jButton553.setText("C8");

        jButton554.setText("D8");

        jButton555.setText("E8");

        jButton556.setText("F8");

        jButton557.setText("F7");

        jButton558.setText("F6");

        jButton559.setText("F5");
        jButton559.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton559ActionPerformed(evt);
            }
        });

        jButton560.setText("F4");

        jButton561.setText("F3");

        jButton562.setText("F2");

        jButton563.setText("F1");

        jButton564.setText("G8");

        jButton565.setText("G7");

        jButton566.setText("G6");

        jButton567.setText("G5");

        jButton568.setText("G4");

        jButton569.setText("G2");

        jButton570.setText("G3");

        jButton571.setText("G1");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton555, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton554, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton553, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton552, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton551, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                                        .addComponent(jButton523, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton521, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton520, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton519, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton518, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton517, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton533, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton529, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton540, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton539, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton532, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton534, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton541, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton546, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                .addComponent(jButton547, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton548, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                .addComponent(jButton542, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton543, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                .addComponent(jButton531, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton530, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                .addComponent(jButton535, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton536, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                .addComponent(jButton527, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton528, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                .addComponent(jButton537, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton538, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                .addComponent(jButton544, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton545, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                .addComponent(jButton549, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton550, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton526, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton525, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton524, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton522, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton516, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addComponent(jButton564, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton565, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton566, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton567, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton568, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton570, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton569, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton571, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addComponent(jButton556, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton557, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton558, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton559, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton560, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton561, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton562, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton563, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton516)
                    .addComponent(jButton517)
                    .addComponent(jButton518)
                    .addComponent(jButton519)
                    .addComponent(jButton520)
                    .addComponent(jButton521)
                    .addComponent(jButton523)
                    .addComponent(jButton551))
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton522)
                    .addComponent(jButton528)
                    .addComponent(jButton527)
                    .addComponent(jButton530)
                    .addComponent(jButton531)
                    .addComponent(jButton532)
                    .addComponent(jButton529)
                    .addComponent(jButton552))
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton524)
                    .addComponent(jButton533)
                    .addComponent(jButton534)
                    .addComponent(jButton535)
                    .addComponent(jButton536)
                    .addComponent(jButton537)
                    .addComponent(jButton538)
                    .addComponent(jButton553))
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton525)
                    .addComponent(jButton540)
                    .addComponent(jButton541)
                    .addComponent(jButton542)
                    .addComponent(jButton543)
                    .addComponent(jButton544)
                    .addComponent(jButton545)
                    .addComponent(jButton554))
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton526)
                    .addComponent(jButton539)
                    .addComponent(jButton546)
                    .addComponent(jButton547)
                    .addComponent(jButton548)
                    .addComponent(jButton549)
                    .addComponent(jButton550)
                    .addComponent(jButton555))
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton556)
                    .addComponent(jButton557)
                    .addComponent(jButton558)
                    .addComponent(jButton559)
                    .addComponent(jButton560)
                    .addComponent(jButton561)
                    .addComponent(jButton562)
                    .addComponent(jButton563))
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton571)
                    .addComponent(jButton569)
                    .addComponent(jButton570)
                    .addComponent(jButton568)
                    .addComponent(jButton567)
                    .addComponent(jButton566)
                    .addComponent(jButton565)
                    .addComponent(jButton564))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong5Layout = new javax.swing.GroupLayout(Phong5);
        Phong5.setLayout(Phong5Layout);
        Phong5Layout.setHorizontalGroup(
            Phong5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong5Layout.createSequentialGroup()
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong5Layout.setVerticalGroup(
            Phong5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong5Layout.createSequentialGroup()
                .addGroup(Phong5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong5, "card2");

        jPanel49.setBackground(new java.awt.Color(204, 204, 204));

        jLabel42.setText("Ảnh phim");

        jTextField77.setText("Tên phim");

        jTextField78.setText("Tên phim");

        jTextField79.setText("Tên phim");

        jTextField80.setText("Tên phim");

        jTextField81.setText("Tên phim");

        jTextPane11.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane11.setText("Phòng 6");
        jScrollPane15.setViewportView(jTextPane11);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField79, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField78, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField81, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel50.setBackground(new java.awt.Color(255, 204, 204));

        jButton572.setText("A1");

        jLabel43.setBackground(new java.awt.Color(255, 204, 204));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("MÀN HÌNH CHIẾU");

        jButton573.setText("A2");

        jButton574.setText("A3");

        jButton575.setText("A4");

        jButton576.setText("A5");

        jButton577.setText("A6");

        jButton578.setText("B1");

        jButton579.setText("A7");

        jButton580.setText("C1");

        jButton581.setText("D1");

        jButton582.setText("E1");

        jButton583.setText("B3");

        jButton584.setText("B2");

        jButton585.setText("B7");

        jButton586.setText("B4");

        jButton587.setText("B5");

        jButton588.setText("B6");

        jButton589.setText("C7");

        jButton590.setText("C6");

        jButton591.setText("C5");

        jButton592.setText("C4");

        jButton593.setText("C3");

        jButton594.setText("C2");

        jButton595.setText("E7");

        jButton596.setText("D7");

        jButton597.setText("D6");

        jButton598.setText("D5");

        jButton599.setText("D4");

        jButton600.setText("D3");

        jButton601.setText("D2");

        jButton602.setText("E6");

        jButton603.setText("E5");

        jButton604.setText("E4");

        jButton605.setText("E3");

        jButton606.setText("E2");

        jButton607.setText("A8");

        jButton608.setText("B8");

        jButton609.setText("C8");

        jButton610.setText("D8");

        jButton611.setText("E8");

        jButton612.setText("F8");

        jButton613.setText("F7");

        jButton614.setText("F6");

        jButton615.setText("F5");
        jButton615.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton615ActionPerformed(evt);
            }
        });

        jButton616.setText("F4");

        jButton617.setText("F3");

        jButton618.setText("F2");

        jButton619.setText("F1");

        jButton620.setText("G8");

        jButton621.setText("G7");

        jButton622.setText("G6");

        jButton623.setText("G5");

        jButton624.setText("G4");

        jButton625.setText("G2");

        jButton626.setText("G3");

        jButton627.setText("G1");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton611, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton610, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton609, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton608, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton607, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                                        .addComponent(jButton579, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton577, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton576, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton575, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton574, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton573, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton589, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton585, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton596, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton595, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton588, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton590, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton597, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton602, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                .addComponent(jButton603, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton604, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                .addComponent(jButton598, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton599, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                .addComponent(jButton587, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton586, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                .addComponent(jButton591, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton592, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                .addComponent(jButton583, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton584, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                .addComponent(jButton593, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton594, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                .addComponent(jButton600, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton601, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                .addComponent(jButton605, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton606, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton582, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton581, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton580, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton578, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton572, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addComponent(jButton620, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton621, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton622, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton623, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton624, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton626, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton625, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton627, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addComponent(jButton612, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton613, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton614, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton615, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton616, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton617, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton618, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton619, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton572)
                    .addComponent(jButton573)
                    .addComponent(jButton574)
                    .addComponent(jButton575)
                    .addComponent(jButton576)
                    .addComponent(jButton577)
                    .addComponent(jButton579)
                    .addComponent(jButton607))
                .addGap(18, 18, 18)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton578)
                    .addComponent(jButton584)
                    .addComponent(jButton583)
                    .addComponent(jButton586)
                    .addComponent(jButton587)
                    .addComponent(jButton588)
                    .addComponent(jButton585)
                    .addComponent(jButton608))
                .addGap(18, 18, 18)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton580)
                    .addComponent(jButton589)
                    .addComponent(jButton590)
                    .addComponent(jButton591)
                    .addComponent(jButton592)
                    .addComponent(jButton593)
                    .addComponent(jButton594)
                    .addComponent(jButton609))
                .addGap(18, 18, 18)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton581)
                    .addComponent(jButton596)
                    .addComponent(jButton597)
                    .addComponent(jButton598)
                    .addComponent(jButton599)
                    .addComponent(jButton600)
                    .addComponent(jButton601)
                    .addComponent(jButton610))
                .addGap(18, 18, 18)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton582)
                    .addComponent(jButton595)
                    .addComponent(jButton602)
                    .addComponent(jButton603)
                    .addComponent(jButton604)
                    .addComponent(jButton605)
                    .addComponent(jButton606)
                    .addComponent(jButton611))
                .addGap(18, 18, 18)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton612)
                    .addComponent(jButton613)
                    .addComponent(jButton614)
                    .addComponent(jButton615)
                    .addComponent(jButton616)
                    .addComponent(jButton617)
                    .addComponent(jButton618)
                    .addComponent(jButton619))
                .addGap(18, 18, 18)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton627)
                    .addComponent(jButton625)
                    .addComponent(jButton626)
                    .addComponent(jButton624)
                    .addComponent(jButton623)
                    .addComponent(jButton622)
                    .addComponent(jButton621)
                    .addComponent(jButton620))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong6Layout = new javax.swing.GroupLayout(Phong6);
        Phong6.setLayout(Phong6Layout);
        Phong6Layout.setHorizontalGroup(
            Phong6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong6Layout.createSequentialGroup()
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong6Layout.setVerticalGroup(
            Phong6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong6Layout.createSequentialGroup()
                .addGroup(Phong6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong6, "card2");

        jPanel51.setBackground(new java.awt.Color(204, 204, 204));

        jLabel44.setText("Ảnh phim");

        jTextField82.setText("Tên phim");

        jTextField83.setText("Tên phim");

        jTextField84.setText("Tên phim");

        jTextField85.setText("Tên phim");

        jTextField86.setText("Tên phim");

        jTextPane12.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane12.setText("Phòng 7");
        jScrollPane16.setViewportView(jTextPane12);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField84, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField83, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField86, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField85, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField82, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel52.setBackground(new java.awt.Color(255, 204, 204));

        jButton628.setText("A1");

        jLabel45.setBackground(new java.awt.Color(255, 204, 204));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("MÀN HÌNH CHIẾU");

        jButton629.setText("A2");

        jButton630.setText("A3");

        jButton631.setText("A4");

        jButton632.setText("A5");

        jButton633.setText("A6");

        jButton634.setText("B1");

        jButton635.setText("A7");

        jButton636.setText("C1");

        jButton637.setText("D1");

        jButton638.setText("E1");

        jButton639.setText("B3");

        jButton640.setText("B2");

        jButton641.setText("B7");

        jButton642.setText("B4");

        jButton643.setText("B5");

        jButton644.setText("B6");

        jButton645.setText("C7");

        jButton646.setText("C6");

        jButton647.setText("C5");

        jButton648.setText("C4");

        jButton649.setText("C3");

        jButton650.setText("C2");

        jButton651.setText("E7");

        jButton652.setText("D7");

        jButton653.setText("D6");

        jButton654.setText("D5");

        jButton655.setText("D4");

        jButton656.setText("D3");

        jButton657.setText("D2");

        jButton658.setText("E6");

        jButton659.setText("E5");

        jButton660.setText("E4");

        jButton661.setText("E3");

        jButton662.setText("E2");

        jButton663.setText("A8");

        jButton664.setText("B8");

        jButton665.setText("C8");

        jButton666.setText("D8");

        jButton667.setText("E8");

        jButton668.setText("F8");

        jButton669.setText("F7");

        jButton670.setText("F6");

        jButton671.setText("F5");
        jButton671.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton671ActionPerformed(evt);
            }
        });

        jButton672.setText("F4");

        jButton673.setText("F3");

        jButton674.setText("F2");

        jButton675.setText("F1");

        jButton676.setText("G8");

        jButton677.setText("G7");

        jButton678.setText("G6");

        jButton679.setText("G5");

        jButton680.setText("G4");

        jButton681.setText("G2");

        jButton682.setText("G3");

        jButton683.setText("G1");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton665, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton667, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton666, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton664, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton663, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                                        .addComponent(jButton635, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton633, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton632, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton631, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton630, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton629, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton645, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton641, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton652, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton651, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton644, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton646, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton653, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton658, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel52Layout.createSequentialGroup()
                                                .addComponent(jButton659, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton660, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel52Layout.createSequentialGroup()
                                                .addComponent(jButton654, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton655, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel52Layout.createSequentialGroup()
                                                .addComponent(jButton643, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton642, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel52Layout.createSequentialGroup()
                                                .addComponent(jButton647, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton648, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel52Layout.createSequentialGroup()
                                                .addComponent(jButton639, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton640, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel52Layout.createSequentialGroup()
                                                .addComponent(jButton649, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton650, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel52Layout.createSequentialGroup()
                                                .addComponent(jButton656, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton657, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel52Layout.createSequentialGroup()
                                                .addComponent(jButton661, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton662, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton638, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton637, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton636, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton634, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton628, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addComponent(jButton676, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton677, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton678, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton679, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton680, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton682, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton681, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton683, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addComponent(jButton668, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton669, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton670, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton671, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton672, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton673, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton674, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton675, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton628)
                    .addComponent(jButton629)
                    .addComponent(jButton630)
                    .addComponent(jButton631)
                    .addComponent(jButton632)
                    .addComponent(jButton633)
                    .addComponent(jButton635)
                    .addComponent(jButton663))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton634)
                    .addComponent(jButton640)
                    .addComponent(jButton639)
                    .addComponent(jButton642)
                    .addComponent(jButton643)
                    .addComponent(jButton644)
                    .addComponent(jButton641)
                    .addComponent(jButton664))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton636)
                    .addComponent(jButton645)
                    .addComponent(jButton646)
                    .addComponent(jButton647)
                    .addComponent(jButton648)
                    .addComponent(jButton649)
                    .addComponent(jButton650)
                    .addComponent(jButton665))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton637)
                    .addComponent(jButton652)
                    .addComponent(jButton653)
                    .addComponent(jButton654)
                    .addComponent(jButton655)
                    .addComponent(jButton656)
                    .addComponent(jButton657)
                    .addComponent(jButton666))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton638)
                    .addComponent(jButton651)
                    .addComponent(jButton658)
                    .addComponent(jButton659)
                    .addComponent(jButton660)
                    .addComponent(jButton661)
                    .addComponent(jButton662)
                    .addComponent(jButton667))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton668)
                    .addComponent(jButton669)
                    .addComponent(jButton670)
                    .addComponent(jButton671)
                    .addComponent(jButton672)
                    .addComponent(jButton673)
                    .addComponent(jButton674)
                    .addComponent(jButton675))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton683)
                    .addComponent(jButton681)
                    .addComponent(jButton682)
                    .addComponent(jButton680)
                    .addComponent(jButton679)
                    .addComponent(jButton678)
                    .addComponent(jButton677)
                    .addComponent(jButton676))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong7Layout = new javax.swing.GroupLayout(Phong7);
        Phong7.setLayout(Phong7Layout);
        Phong7Layout.setHorizontalGroup(
            Phong7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong7Layout.createSequentialGroup()
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong7Layout.setVerticalGroup(
            Phong7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong7Layout.createSequentialGroup()
                .addGroup(Phong7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong7, "card2");

        jPanel39.setBackground(new java.awt.Color(204, 204, 204));

        jLabel32.setText("Ảnh phim");

        jTextField52.setText("Tên phim");

        jTextField53.setText("Tên phim");

        jTextField54.setText("Tên phim");

        jTextField55.setText("Tên phim");

        jTextField56.setText("Tên phim");

        jTextPane6.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane6.setText("Phòng 8");
        jScrollPane10.setViewportView(jTextPane6);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel40.setBackground(new java.awt.Color(255, 204, 204));

        jButton292.setText("A1");

        jLabel33.setBackground(new java.awt.Color(255, 204, 204));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("MÀN HÌNH CHIẾU");

        jButton293.setText("A2");

        jButton294.setText("A3");

        jButton295.setText("A4");

        jButton296.setText("A5");

        jButton297.setText("A6");

        jButton298.setText("B1");

        jButton299.setText("A7");

        jButton300.setText("C1");

        jButton301.setText("D1");

        jButton302.setText("E1");

        jButton303.setText("B3");

        jButton304.setText("B2");

        jButton305.setText("B7");

        jButton306.setText("B4");

        jButton307.setText("B5");

        jButton308.setText("B6");

        jButton309.setText("C7");

        jButton310.setText("C6");

        jButton311.setText("C5");

        jButton312.setText("C4");

        jButton313.setText("C3");

        jButton314.setText("C2");

        jButton315.setText("E7");

        jButton316.setText("D7");

        jButton317.setText("D6");

        jButton318.setText("D5");

        jButton319.setText("D4");

        jButton320.setText("D3");

        jButton321.setText("D2");

        jButton322.setText("E6");

        jButton323.setText("E5");

        jButton324.setText("E4");

        jButton325.setText("E3");

        jButton326.setText("E2");

        jButton327.setText("A8");

        jButton328.setText("B8");
        jButton328.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton328ActionPerformed(evt);
            }
        });

        jButton329.setText("C8");

        jButton330.setText("D8");

        jButton331.setText("E8");

        jButton332.setText("F8");

        jButton333.setText("F7");

        jButton334.setText("F6");

        jButton335.setText("F5");
        jButton335.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton335ActionPerformed(evt);
            }
        });

        jButton336.setText("F4");

        jButton337.setText("F3");

        jButton338.setText("F2");

        jButton339.setText("F1");

        jButton340.setText("G8");

        jButton341.setText("G7");

        jButton342.setText("G6");

        jButton343.setText("G5");

        jButton344.setText("G4");

        jButton345.setText("G2");

        jButton346.setText("G3");

        jButton347.setText("G1");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton331, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton330, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton329, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton328, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton327, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                                        .addComponent(jButton299, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton297, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton296, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton295, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton294, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton293, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton309, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton305, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton316, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton315, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton308, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton310, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton317, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton322, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addComponent(jButton323, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton324, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addComponent(jButton318, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton319, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addComponent(jButton307, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton306, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addComponent(jButton311, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton312, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addComponent(jButton303, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton304, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addComponent(jButton313, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton314, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addComponent(jButton320, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton321, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addComponent(jButton325, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton326, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton302, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton301, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton300, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton298, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton292, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addComponent(jButton340, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton341, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton342, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton343, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton344, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton346, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton345, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton347, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addComponent(jButton332, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton333, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton334, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton335, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton336, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton337, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton338, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton339, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton292)
                    .addComponent(jButton293)
                    .addComponent(jButton294)
                    .addComponent(jButton295)
                    .addComponent(jButton296)
                    .addComponent(jButton297)
                    .addComponent(jButton299)
                    .addComponent(jButton327))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton298)
                    .addComponent(jButton304)
                    .addComponent(jButton303)
                    .addComponent(jButton306)
                    .addComponent(jButton307)
                    .addComponent(jButton308)
                    .addComponent(jButton305)
                    .addComponent(jButton328))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton300)
                    .addComponent(jButton309)
                    .addComponent(jButton310)
                    .addComponent(jButton311)
                    .addComponent(jButton312)
                    .addComponent(jButton313)
                    .addComponent(jButton314)
                    .addComponent(jButton329))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton301)
                    .addComponent(jButton316)
                    .addComponent(jButton317)
                    .addComponent(jButton318)
                    .addComponent(jButton319)
                    .addComponent(jButton320)
                    .addComponent(jButton321)
                    .addComponent(jButton330))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton302)
                    .addComponent(jButton315)
                    .addComponent(jButton322)
                    .addComponent(jButton323)
                    .addComponent(jButton324)
                    .addComponent(jButton325)
                    .addComponent(jButton326)
                    .addComponent(jButton331))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton332)
                    .addComponent(jButton333)
                    .addComponent(jButton334)
                    .addComponent(jButton335)
                    .addComponent(jButton336)
                    .addComponent(jButton337)
                    .addComponent(jButton338)
                    .addComponent(jButton339))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton347)
                    .addComponent(jButton345)
                    .addComponent(jButton346)
                    .addComponent(jButton344)
                    .addComponent(jButton343)
                    .addComponent(jButton342)
                    .addComponent(jButton341)
                    .addComponent(jButton340))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong8Layout = new javax.swing.GroupLayout(Phong8);
        Phong8.setLayout(Phong8Layout);
        Phong8Layout.setHorizontalGroup(
            Phong8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong8Layout.createSequentialGroup()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong8Layout.setVerticalGroup(
            Phong8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong8Layout.createSequentialGroup()
                .addGroup(Phong8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong8, "card2");

        jPanel53.setBackground(new java.awt.Color(204, 204, 204));

        jLabel46.setText("Ảnh phim");

        jTextField87.setText("Tên phim");

        jTextField88.setText("Tên phim");

        jTextField89.setText("Tên phim");

        jTextField90.setText("Tên phim");

        jTextField91.setText("Tên phim");

        jTextPane13.setBackground(new java.awt.Color(255, 102, 102));
        jTextPane13.setText("Phòng 1");
        jScrollPane17.setViewportView(jTextPane13);

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField89, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField88, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField91, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField90, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField87, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextField90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanel54.setBackground(new java.awt.Color(255, 204, 204));

        jButton684.setText("A1");

        jLabel47.setBackground(new java.awt.Color(255, 204, 204));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("MÀN HÌNH CHIẾU");

        jButton685.setText("A2");

        jButton686.setText("A3");

        jButton687.setText("A4");

        jButton688.setText("A5");

        jButton689.setText("A6");

        jButton690.setText("B1");

        jButton691.setText("A7");

        jButton692.setText("C1");

        jButton693.setText("D1");

        jButton694.setText("E1");

        jButton695.setText("B3");

        jButton696.setText("B2");

        jButton697.setText("B7");

        jButton698.setText("B4");

        jButton699.setText("B5");

        jButton700.setText("B6");

        jButton701.setText("C7");

        jButton702.setText("C6");

        jButton703.setText("C5");

        jButton704.setText("C4");

        jButton705.setText("C3");

        jButton706.setText("C2");

        jButton707.setText("E7");

        jButton708.setText("D7");

        jButton709.setText("D6");

        jButton710.setText("D5");

        jButton711.setText("D4");

        jButton712.setText("D3");

        jButton713.setText("D2");

        jButton714.setText("E6");

        jButton715.setText("E5");

        jButton716.setText("E4");

        jButton717.setText("E3");

        jButton718.setText("E2");

        jButton719.setText("A8");

        jButton720.setText("B8");

        jButton721.setText("C8");

        jButton722.setText("D8");

        jButton723.setText("E8");

        jButton724.setText("F8");

        jButton725.setText("F7");

        jButton726.setText("F6");

        jButton727.setText("F5");
        jButton727.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton727ActionPerformed(evt);
            }
        });

        jButton728.setText("F4");

        jButton729.setText("F3");

        jButton730.setText("F2");

        jButton731.setText("F1");

        jButton732.setText("G8");

        jButton733.setText("G7");

        jButton734.setText("G6");

        jButton735.setText("G5");

        jButton736.setText("G4");

        jButton737.setText("G2");

        jButton738.setText("G3");

        jButton739.setText("G1");

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton719, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton720, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton721, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton722, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton723, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                                .addComponent(jButton691, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton689, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton688, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton687, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton686, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton685, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton701, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton697, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton708, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton707, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton700, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton702, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton709, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton714, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addComponent(jButton715, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton716, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addComponent(jButton710, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton711, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addComponent(jButton699, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton698, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addComponent(jButton703, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton704, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addComponent(jButton695, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton696, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addComponent(jButton705, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton706, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addComponent(jButton712, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton713, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addComponent(jButton717, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton718, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton694, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton693, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton692, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton690, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton684, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jButton732, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton733, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton734, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton735, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton736, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton738, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton737, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton739, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jButton724, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton725, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton726, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton727, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton728, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton729, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton730, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton731, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton684)
                    .addComponent(jButton685)
                    .addComponent(jButton686)
                    .addComponent(jButton687)
                    .addComponent(jButton688)
                    .addComponent(jButton689)
                    .addComponent(jButton691)
                    .addComponent(jButton719))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton690)
                    .addComponent(jButton696)
                    .addComponent(jButton695)
                    .addComponent(jButton698)
                    .addComponent(jButton699)
                    .addComponent(jButton700)
                    .addComponent(jButton697)
                    .addComponent(jButton720))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton692)
                    .addComponent(jButton701)
                    .addComponent(jButton702)
                    .addComponent(jButton703)
                    .addComponent(jButton704)
                    .addComponent(jButton705)
                    .addComponent(jButton706)
                    .addComponent(jButton721))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton693)
                    .addComponent(jButton708)
                    .addComponent(jButton709)
                    .addComponent(jButton710)
                    .addComponent(jButton711)
                    .addComponent(jButton712)
                    .addComponent(jButton713)
                    .addComponent(jButton722))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton694)
                    .addComponent(jButton707)
                    .addComponent(jButton714)
                    .addComponent(jButton715)
                    .addComponent(jButton716)
                    .addComponent(jButton717)
                    .addComponent(jButton718)
                    .addComponent(jButton723))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton724)
                    .addComponent(jButton725)
                    .addComponent(jButton726)
                    .addComponent(jButton727)
                    .addComponent(jButton728)
                    .addComponent(jButton729)
                    .addComponent(jButton730)
                    .addComponent(jButton731))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton739)
                    .addComponent(jButton737)
                    .addComponent(jButton738)
                    .addComponent(jButton736)
                    .addComponent(jButton735)
                    .addComponent(jButton734)
                    .addComponent(jButton733)
                    .addComponent(jButton732))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Phong15Layout = new javax.swing.GroupLayout(Phong15);
        Phong15.setLayout(Phong15Layout);
        Phong15Layout.setHorizontalGroup(
            Phong15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phong15Layout.createSequentialGroup()
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Phong15Layout.setVerticalGroup(
            Phong15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phong15Layout.createSequentialGroup()
                .addGroup(Phong15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.add(Phong15, "card2");

        javax.swing.GroupLayout QlsuatchieuLayout = new javax.swing.GroupLayout(Qlsuatchieu);
        Qlsuatchieu.setLayout(QlsuatchieuLayout);
        QlsuatchieuLayout.setHorizontalGroup(
            QlsuatchieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlsuatchieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QlsuatchieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(QlsuatchieuLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        QlsuatchieuLayout.setVerticalGroup(
            QlsuatchieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlsuatchieuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(Qlsuatchieu, "card5");

        QlHoivien.setBackground(new java.awt.Color(204, 204, 204));

        jPanel31.setBackground(new java.awt.Color(247, 247, 247));

        jPanel32.setBackground(new java.awt.Color(247, 247, 247));

        jTextField46.setText("Tìm kiếm");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo", "ID", "Name", "Ngày đăng ký" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel33.setBackground(new java.awt.Color(247, 247, 247));

        jLabel25.setText("SĐT");

        jLabel26.setText("Họ tên");

        jLabel27.setText("Email");

        jButton266.setText("Sửa");
        jButton266.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton266MouseClicked(evt);
            }
        });
        jButton266.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton266ActionPerformed(evt);
            }
        });

        jButton267.setText("Xóa");
        jButton267.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton267MouseClicked(evt);
            }
        });
        jButton267.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton267ActionPerformed(evt);
            }
        });

        jLabel28.setText("Năm sinh");

        jLabel29.setText("Ngày đăng ký");

        jButton265.setText("Thêm ");
        jButton265.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton265MouseClicked(evt);
            }
        });
        jButton265.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton265ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField51))
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                                .addComponent(jButton267)
                                .addGap(31, 31, 31)
                                .addComponent(jButton266)
                                .addGap(18, 18, 18)
                                .addComponent(jButton265))))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton266)
                            .addComponent(jButton265)
                            .addComponent(jButton267))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(96, 96, 96))
        );

        jPanel34.setBackground(new java.awt.Color(204, 204, 204));

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "SĐT", "Email", "Năm sinh", "Ngày đăng ký", "Điểm tích lũy"
            }
        ));
        jScrollPane7.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout QlHoivienLayout = new javax.swing.GroupLayout(QlHoivien);
        QlHoivien.setLayout(QlHoivienLayout);
        QlHoivienLayout.setHorizontalGroup(
            QlHoivienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlHoivienLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(QlHoivienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        QlHoivienLayout.setVerticalGroup(
            QlHoivienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QlHoivienLayout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(715, 715, 715))
        );

        jPanel4.add(QlHoivien, "card4");

        DoanhThu.setBackground(new java.awt.Color(255, 204, 204));

        jTextPane5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTextPane5.setForeground(new java.awt.Color(255, 51, 51));
        jTextPane5.setText("\t\t\t\t\tDOANH THU");
        jScrollPane8.setViewportView(jTextPane5);

        jPanel36.setBackground(new java.awt.Color(204, 204, 204));

        jPanel37.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout BieuDoLayout = new javax.swing.GroupLayout(BieuDo);
        BieuDo.setLayout(BieuDoLayout);
        BieuDoLayout.setHorizontalGroup(
            BieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 924, Short.MAX_VALUE)
        );
        BieuDoLayout.setVerticalGroup(
            BieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jPanel37.add(BieuDo, "card3");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên sản phẩm", "Giá tiền", "Số lượng", "Thời gian ", "Tổng tiền"
            }
        ));
        jScrollPane9.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel37.add(jPanel38, "card2");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DoanhThuLayout = new javax.swing.GroupLayout(DoanhThu);
        DoanhThu.setLayout(DoanhThuLayout);
        DoanhThuLayout.setHorizontalGroup(
            DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DoanhThuLayout.setVerticalGroup(
            DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoanhThuLayout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(DoanhThu, "card5");

        Menu.setBackground(new java.awt.Color(255, 255, 255));
        Menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Menu.setMinimumSize(new java.awt.Dimension(0, 1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Đăng xuất ");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel10.setPreferredSize(new java.awt.Dimension(229, 536));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Quản Lý Suất Chiếu");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Quản Lý Khách Hàng");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Quản Lý Phim");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Trang Chủ");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel30MouseClicked(evt);
            }
        });

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Quản Lý Hội Viên");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
        );

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel35MouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Doanh Thu");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
        );

        jLabel7.setText("X");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addGap(45, 45, 45)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Menu");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QlphimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QlphimMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QlphimMouseClicked

    private void AddKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddKhachMouseClicked
       
        
    }//GEN-LAST:event_AddKhachMouseClicked

    private void AddKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddKhachActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        openMenuBar();
    }//GEN-LAST:event_jMenu1MouseClicked

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

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        Phong1.setVisible(true);
        Phong2.setVisible(false);
        Phong3.setVisible(false);
        Phong4.setVisible(false);
        Phong5.setVisible(false);
        Phong6.setVisible(false);
        Phong7.setVisible(false);
        Phong8.setVisible(false);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        Phong1.setVisible(false);
        Phong2.setVisible(true);
        Phong3.setVisible(false);
        Phong4.setVisible(false);
        Phong5.setVisible(false);
        Phong6.setVisible(false);
        Phong7.setVisible(false);
        Phong8.setVisible(false);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        Phong1.setVisible(false);
        Phong2.setVisible(false);
        Phong3.setVisible(true);
        Phong4.setVisible(false);
        Phong5.setVisible(false);
        Phong6.setVisible(false);
        Phong7.setVisible(false);
        Phong8.setVisible(false);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton265MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton265MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton265MouseClicked

    private void jButton265ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton265ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton265ActionPerformed

    private void jButton266MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton266MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton266MouseClicked

    private void jButton266ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton266ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton266ActionPerformed

    private void jButton267MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton267MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton267MouseClicked

    private void jButton267ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton267ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton267ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        closeMenuBar();
        // jLabel6.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jPanel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MouseClicked
        // quản lý hội viên
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(true);
         DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(242, 242, 242));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

    }//GEN-LAST:event_jPanel30MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       //trang chu
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(true);
        QlHoivien.setVisible(false);
         DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // quan ly phim
        Qlphim.setVisible(true);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(242, 242, 242));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        //quan ly khach hang
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(true);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(242, 242, 242));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked

    }//GEN-LAST:event_jPanel9MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // quan ly suat chieu
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(true);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

    }//GEN-LAST:event_jLabel4MouseClicked

    private void jPanel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel35MouseClicked
        // Doanh thu
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(true);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(242, 242, 242));
    }//GEN-LAST:event_jPanel35MouseClicked

    private void jButton272MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton272MouseClicked
        // trang chu
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(true);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        
    }//GEN-LAST:event_jButton272MouseClicked

    private void jButton264MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton264MouseClicked
        // quan ly khach hang
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(true);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(242, 242, 242));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_jButton264MouseClicked

    private void jButton262MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton262MouseClicked
        // Quan ly suat chieu
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(true);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        
    }//GEN-LAST:event_jButton262MouseClicked

    private void jButton273MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton273MouseClicked
        // quan ly hoi vien
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(true);
         DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(242, 242, 242));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_jButton273MouseClicked

    private void jButton271MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton271MouseClicked
        // doanh thu
        Qlphim.setVisible(false);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(true);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(242, 242, 242));
    }//GEN-LAST:event_jButton271MouseClicked

    private void jButton263MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton263MouseClicked
        // quan ly phim
        Qlphim.setVisible(true);
        QlKhachHang.setVisible(false);
        Qlsuatchieu.setVisible(false);
        Trangchu.setVisible(false);
        QlHoivien.setVisible(false);
        DoanhThu.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(242, 242, 242));
        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_jButton263MouseClicked

    private void NgaySinhFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NgaySinhFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NgaySinhFieldActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void P1F5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1F5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_P1F5ActionPerformed

    private void jButton335ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton335ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton335ActionPerformed

    private void jButton391ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton391ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton391ActionPerformed

    private void jButton447ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton447ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton447ActionPerformed

    private void jButton503ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton503ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton503ActionPerformed

    private void jButton559ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton559ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton559ActionPerformed

    private void jButton615ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton615ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton615ActionPerformed

    private void jButton671ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton671ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton671ActionPerformed

    private void jButton727ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton727ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton727ActionPerformed

    private void jButton328ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton328ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton328ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        Phong1.setVisible(false);
        Phong2.setVisible(false);
        Phong3.setVisible(false);
        Phong4.setVisible(true);
        Phong5.setVisible(false);
        Phong6.setVisible(false);
        Phong7.setVisible(false);
        Phong8.setVisible(false);
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        Phong1.setVisible(false);
        Phong2.setVisible(false);
        Phong3.setVisible(false);
        Phong4.setVisible(false);
        Phong5.setVisible(true);
        Phong6.setVisible(false);
        Phong7.setVisible(false);
        Phong8.setVisible(false);
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        Phong1.setVisible(false);
        Phong2.setVisible(false);
        Phong3.setVisible(false);
        Phong4.setVisible(false);
        Phong5.setVisible(false);
        Phong6.setVisible(true);
        Phong7.setVisible(false);
        Phong8.setVisible(false);
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        Phong1.setVisible(false);
        Phong2.setVisible(false);
        Phong3.setVisible(false);
        Phong4.setVisible(false);
        Phong5.setVisible(false);
        Phong6.setVisible(false);
        Phong7.setVisible(true);
        Phong8.setVisible(false);
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        Phong1.setVisible(false);
        Phong2.setVisible(false);
        Phong3.setVisible(false);
        Phong4.setVisible(false);
        Phong5.setVisible(false);
        Phong6.setVisible(false);
        Phong7.setVisible(false);
        Phong8.setVisible(true);
    }//GEN-LAST:event_jButton10MouseClicked

    private void P1A7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1A7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_P1A7ActionPerformed

    private void IDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDFieldActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Quanly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Quanly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Quanly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Quanly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                
//                new Quanly().setVisible(true);
//                
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddKhach;
    private javax.swing.JButton AddPhim;
    private javax.swing.JTable BangKhachHang;
    private javax.swing.JTable BangPhim;
    private javax.swing.JPanel BieuDo;
    private javax.swing.JButton ClearPhim;
    private javax.swing.JButton DeleteKhach;
    private javax.swing.JButton DeletePhim;
    private javax.swing.JTextField DoTuoiField;
    private javax.swing.JPanel DoanhThu;
    private javax.swing.JButton EditKhach;
    private javax.swing.JButton EditPhim;
    private javax.swing.JTextField GioiTinhField;
    private javax.swing.JTextField HoTenField;
    private javax.swing.JTextField IDField;
    private javax.swing.JTextField IDPhimField;
    private javax.swing.JPanel Menu;
    private javax.swing.JTextField NgayKhoiChieuField;
    private javax.swing.JTextField NgaySinhField;
    private javax.swing.JButton P1A1;
    private javax.swing.JButton P1A2;
    private javax.swing.JButton P1A3;
    private javax.swing.JButton P1A4;
    private javax.swing.JButton P1A5;
    private javax.swing.JButton P1A6;
    private javax.swing.JButton P1A7;
    private javax.swing.JButton P1A8;
    private javax.swing.JButton P1B1;
    private javax.swing.JButton P1B2;
    private javax.swing.JButton P1B3;
    private javax.swing.JButton P1B4;
    private javax.swing.JButton P1B5;
    private javax.swing.JButton P1B6;
    private javax.swing.JButton P1B7;
    private javax.swing.JButton P1B8;
    private javax.swing.JButton P1C1;
    private javax.swing.JButton P1C2;
    private javax.swing.JButton P1C3;
    private javax.swing.JButton P1C4;
    private javax.swing.JButton P1C5;
    private javax.swing.JButton P1C6;
    private javax.swing.JButton P1C7;
    private javax.swing.JButton P1C8;
    private javax.swing.JButton P1D1;
    private javax.swing.JButton P1D2;
    private javax.swing.JButton P1D3;
    private javax.swing.JButton P1D4;
    private javax.swing.JButton P1D5;
    private javax.swing.JButton P1D6;
    private javax.swing.JButton P1D7;
    private javax.swing.JButton P1D8;
    private javax.swing.JButton P1E1;
    private javax.swing.JButton P1E2;
    private javax.swing.JButton P1E3;
    private javax.swing.JButton P1E4;
    private javax.swing.JButton P1E5;
    private javax.swing.JButton P1E6;
    private javax.swing.JButton P1E7;
    private javax.swing.JButton P1E8;
    private javax.swing.JButton P1F1;
    private javax.swing.JButton P1F2;
    private javax.swing.JButton P1F3;
    private javax.swing.JButton P1F4;
    private javax.swing.JButton P1F5;
    private javax.swing.JButton P1F6;
    private javax.swing.JButton P1F7;
    private javax.swing.JButton P1F8;
    private javax.swing.JButton P1G1;
    private javax.swing.JButton P1G2;
    private javax.swing.JButton P1G3;
    private javax.swing.JButton P1G4;
    private javax.swing.JButton P1G5;
    private javax.swing.JButton P1G6;
    private javax.swing.JButton P1G7;
    private javax.swing.JButton P1G8;
    private javax.swing.JPanel Phong1;
    private javax.swing.JPanel Phong15;
    private javax.swing.JPanel Phong2;
    private javax.swing.JPanel Phong3;
    private javax.swing.JPanel Phong4;
    private javax.swing.JPanel Phong5;
    private javax.swing.JPanel Phong6;
    private javax.swing.JPanel Phong7;
    private javax.swing.JPanel Phong8;
    private javax.swing.JPanel QlHoivien;
    private javax.swing.JPanel QlKhachHang;
    private javax.swing.JPanel Qlphim;
    private javax.swing.JPanel Qlsuatchieu;
    private javax.swing.JTextField SlvField;
    private javax.swing.JTextField TenPhimField;
    private javax.swing.JTextField TheLoaiField;
    private javax.swing.JTextField ThoiLuongField;
    private javax.swing.JPanel Trangchu;
    private javax.swing.JButton clearKhach;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton262;
    private javax.swing.JButton jButton263;
    private javax.swing.JButton jButton264;
    private javax.swing.JButton jButton265;
    private javax.swing.JButton jButton266;
    private javax.swing.JButton jButton267;
    private javax.swing.JButton jButton271;
    private javax.swing.JButton jButton272;
    private javax.swing.JButton jButton273;
    private javax.swing.JButton jButton292;
    private javax.swing.JButton jButton293;
    private javax.swing.JButton jButton294;
    private javax.swing.JButton jButton295;
    private javax.swing.JButton jButton296;
    private javax.swing.JButton jButton297;
    private javax.swing.JButton jButton298;
    private javax.swing.JButton jButton299;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton300;
    private javax.swing.JButton jButton301;
    private javax.swing.JButton jButton302;
    private javax.swing.JButton jButton303;
    private javax.swing.JButton jButton304;
    private javax.swing.JButton jButton305;
    private javax.swing.JButton jButton306;
    private javax.swing.JButton jButton307;
    private javax.swing.JButton jButton308;
    private javax.swing.JButton jButton309;
    private javax.swing.JButton jButton310;
    private javax.swing.JButton jButton311;
    private javax.swing.JButton jButton312;
    private javax.swing.JButton jButton313;
    private javax.swing.JButton jButton314;
    private javax.swing.JButton jButton315;
    private javax.swing.JButton jButton316;
    private javax.swing.JButton jButton317;
    private javax.swing.JButton jButton318;
    private javax.swing.JButton jButton319;
    private javax.swing.JButton jButton320;
    private javax.swing.JButton jButton321;
    private javax.swing.JButton jButton322;
    private javax.swing.JButton jButton323;
    private javax.swing.JButton jButton324;
    private javax.swing.JButton jButton325;
    private javax.swing.JButton jButton326;
    private javax.swing.JButton jButton327;
    private javax.swing.JButton jButton328;
    private javax.swing.JButton jButton329;
    private javax.swing.JButton jButton330;
    private javax.swing.JButton jButton331;
    private javax.swing.JButton jButton332;
    private javax.swing.JButton jButton333;
    private javax.swing.JButton jButton334;
    private javax.swing.JButton jButton335;
    private javax.swing.JButton jButton336;
    private javax.swing.JButton jButton337;
    private javax.swing.JButton jButton338;
    private javax.swing.JButton jButton339;
    private javax.swing.JButton jButton340;
    private javax.swing.JButton jButton341;
    private javax.swing.JButton jButton342;
    private javax.swing.JButton jButton343;
    private javax.swing.JButton jButton344;
    private javax.swing.JButton jButton345;
    private javax.swing.JButton jButton346;
    private javax.swing.JButton jButton347;
    private javax.swing.JButton jButton348;
    private javax.swing.JButton jButton349;
    private javax.swing.JButton jButton350;
    private javax.swing.JButton jButton351;
    private javax.swing.JButton jButton352;
    private javax.swing.JButton jButton353;
    private javax.swing.JButton jButton354;
    private javax.swing.JButton jButton355;
    private javax.swing.JButton jButton356;
    private javax.swing.JButton jButton357;
    private javax.swing.JButton jButton358;
    private javax.swing.JButton jButton359;
    private javax.swing.JButton jButton360;
    private javax.swing.JButton jButton361;
    private javax.swing.JButton jButton362;
    private javax.swing.JButton jButton363;
    private javax.swing.JButton jButton364;
    private javax.swing.JButton jButton365;
    private javax.swing.JButton jButton366;
    private javax.swing.JButton jButton367;
    private javax.swing.JButton jButton368;
    private javax.swing.JButton jButton369;
    private javax.swing.JButton jButton370;
    private javax.swing.JButton jButton371;
    private javax.swing.JButton jButton372;
    private javax.swing.JButton jButton373;
    private javax.swing.JButton jButton374;
    private javax.swing.JButton jButton375;
    private javax.swing.JButton jButton376;
    private javax.swing.JButton jButton377;
    private javax.swing.JButton jButton378;
    private javax.swing.JButton jButton379;
    private javax.swing.JButton jButton380;
    private javax.swing.JButton jButton381;
    private javax.swing.JButton jButton382;
    private javax.swing.JButton jButton383;
    private javax.swing.JButton jButton384;
    private javax.swing.JButton jButton385;
    private javax.swing.JButton jButton386;
    private javax.swing.JButton jButton387;
    private javax.swing.JButton jButton388;
    private javax.swing.JButton jButton389;
    private javax.swing.JButton jButton390;
    private javax.swing.JButton jButton391;
    private javax.swing.JButton jButton392;
    private javax.swing.JButton jButton393;
    private javax.swing.JButton jButton394;
    private javax.swing.JButton jButton395;
    private javax.swing.JButton jButton396;
    private javax.swing.JButton jButton397;
    private javax.swing.JButton jButton398;
    private javax.swing.JButton jButton399;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton400;
    private javax.swing.JButton jButton401;
    private javax.swing.JButton jButton402;
    private javax.swing.JButton jButton403;
    private javax.swing.JButton jButton404;
    private javax.swing.JButton jButton405;
    private javax.swing.JButton jButton406;
    private javax.swing.JButton jButton407;
    private javax.swing.JButton jButton408;
    private javax.swing.JButton jButton409;
    private javax.swing.JButton jButton410;
    private javax.swing.JButton jButton411;
    private javax.swing.JButton jButton412;
    private javax.swing.JButton jButton413;
    private javax.swing.JButton jButton414;
    private javax.swing.JButton jButton415;
    private javax.swing.JButton jButton416;
    private javax.swing.JButton jButton417;
    private javax.swing.JButton jButton418;
    private javax.swing.JButton jButton419;
    private javax.swing.JButton jButton420;
    private javax.swing.JButton jButton421;
    private javax.swing.JButton jButton422;
    private javax.swing.JButton jButton423;
    private javax.swing.JButton jButton424;
    private javax.swing.JButton jButton425;
    private javax.swing.JButton jButton426;
    private javax.swing.JButton jButton427;
    private javax.swing.JButton jButton428;
    private javax.swing.JButton jButton429;
    private javax.swing.JButton jButton430;
    private javax.swing.JButton jButton431;
    private javax.swing.JButton jButton432;
    private javax.swing.JButton jButton433;
    private javax.swing.JButton jButton434;
    private javax.swing.JButton jButton435;
    private javax.swing.JButton jButton436;
    private javax.swing.JButton jButton437;
    private javax.swing.JButton jButton438;
    private javax.swing.JButton jButton439;
    private javax.swing.JButton jButton440;
    private javax.swing.JButton jButton441;
    private javax.swing.JButton jButton442;
    private javax.swing.JButton jButton443;
    private javax.swing.JButton jButton444;
    private javax.swing.JButton jButton445;
    private javax.swing.JButton jButton446;
    private javax.swing.JButton jButton447;
    private javax.swing.JButton jButton448;
    private javax.swing.JButton jButton449;
    private javax.swing.JButton jButton450;
    private javax.swing.JButton jButton451;
    private javax.swing.JButton jButton452;
    private javax.swing.JButton jButton453;
    private javax.swing.JButton jButton454;
    private javax.swing.JButton jButton455;
    private javax.swing.JButton jButton456;
    private javax.swing.JButton jButton457;
    private javax.swing.JButton jButton458;
    private javax.swing.JButton jButton459;
    private javax.swing.JButton jButton460;
    private javax.swing.JButton jButton461;
    private javax.swing.JButton jButton462;
    private javax.swing.JButton jButton463;
    private javax.swing.JButton jButton464;
    private javax.swing.JButton jButton465;
    private javax.swing.JButton jButton466;
    private javax.swing.JButton jButton467;
    private javax.swing.JButton jButton468;
    private javax.swing.JButton jButton469;
    private javax.swing.JButton jButton470;
    private javax.swing.JButton jButton471;
    private javax.swing.JButton jButton472;
    private javax.swing.JButton jButton473;
    private javax.swing.JButton jButton474;
    private javax.swing.JButton jButton475;
    private javax.swing.JButton jButton476;
    private javax.swing.JButton jButton477;
    private javax.swing.JButton jButton478;
    private javax.swing.JButton jButton479;
    private javax.swing.JButton jButton480;
    private javax.swing.JButton jButton481;
    private javax.swing.JButton jButton482;
    private javax.swing.JButton jButton483;
    private javax.swing.JButton jButton484;
    private javax.swing.JButton jButton485;
    private javax.swing.JButton jButton486;
    private javax.swing.JButton jButton487;
    private javax.swing.JButton jButton488;
    private javax.swing.JButton jButton489;
    private javax.swing.JButton jButton490;
    private javax.swing.JButton jButton491;
    private javax.swing.JButton jButton492;
    private javax.swing.JButton jButton493;
    private javax.swing.JButton jButton494;
    private javax.swing.JButton jButton495;
    private javax.swing.JButton jButton496;
    private javax.swing.JButton jButton497;
    private javax.swing.JButton jButton498;
    private javax.swing.JButton jButton499;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton500;
    private javax.swing.JButton jButton501;
    private javax.swing.JButton jButton502;
    private javax.swing.JButton jButton503;
    private javax.swing.JButton jButton504;
    private javax.swing.JButton jButton505;
    private javax.swing.JButton jButton506;
    private javax.swing.JButton jButton507;
    private javax.swing.JButton jButton508;
    private javax.swing.JButton jButton509;
    private javax.swing.JButton jButton510;
    private javax.swing.JButton jButton511;
    private javax.swing.JButton jButton512;
    private javax.swing.JButton jButton513;
    private javax.swing.JButton jButton514;
    private javax.swing.JButton jButton515;
    private javax.swing.JButton jButton516;
    private javax.swing.JButton jButton517;
    private javax.swing.JButton jButton518;
    private javax.swing.JButton jButton519;
    private javax.swing.JButton jButton520;
    private javax.swing.JButton jButton521;
    private javax.swing.JButton jButton522;
    private javax.swing.JButton jButton523;
    private javax.swing.JButton jButton524;
    private javax.swing.JButton jButton525;
    private javax.swing.JButton jButton526;
    private javax.swing.JButton jButton527;
    private javax.swing.JButton jButton528;
    private javax.swing.JButton jButton529;
    private javax.swing.JButton jButton530;
    private javax.swing.JButton jButton531;
    private javax.swing.JButton jButton532;
    private javax.swing.JButton jButton533;
    private javax.swing.JButton jButton534;
    private javax.swing.JButton jButton535;
    private javax.swing.JButton jButton536;
    private javax.swing.JButton jButton537;
    private javax.swing.JButton jButton538;
    private javax.swing.JButton jButton539;
    private javax.swing.JButton jButton540;
    private javax.swing.JButton jButton541;
    private javax.swing.JButton jButton542;
    private javax.swing.JButton jButton543;
    private javax.swing.JButton jButton544;
    private javax.swing.JButton jButton545;
    private javax.swing.JButton jButton546;
    private javax.swing.JButton jButton547;
    private javax.swing.JButton jButton548;
    private javax.swing.JButton jButton549;
    private javax.swing.JButton jButton550;
    private javax.swing.JButton jButton551;
    private javax.swing.JButton jButton552;
    private javax.swing.JButton jButton553;
    private javax.swing.JButton jButton554;
    private javax.swing.JButton jButton555;
    private javax.swing.JButton jButton556;
    private javax.swing.JButton jButton557;
    private javax.swing.JButton jButton558;
    private javax.swing.JButton jButton559;
    private javax.swing.JButton jButton560;
    private javax.swing.JButton jButton561;
    private javax.swing.JButton jButton562;
    private javax.swing.JButton jButton563;
    private javax.swing.JButton jButton564;
    private javax.swing.JButton jButton565;
    private javax.swing.JButton jButton566;
    private javax.swing.JButton jButton567;
    private javax.swing.JButton jButton568;
    private javax.swing.JButton jButton569;
    private javax.swing.JButton jButton570;
    private javax.swing.JButton jButton571;
    private javax.swing.JButton jButton572;
    private javax.swing.JButton jButton573;
    private javax.swing.JButton jButton574;
    private javax.swing.JButton jButton575;
    private javax.swing.JButton jButton576;
    private javax.swing.JButton jButton577;
    private javax.swing.JButton jButton578;
    private javax.swing.JButton jButton579;
    private javax.swing.JButton jButton580;
    private javax.swing.JButton jButton581;
    private javax.swing.JButton jButton582;
    private javax.swing.JButton jButton583;
    private javax.swing.JButton jButton584;
    private javax.swing.JButton jButton585;
    private javax.swing.JButton jButton586;
    private javax.swing.JButton jButton587;
    private javax.swing.JButton jButton588;
    private javax.swing.JButton jButton589;
    private javax.swing.JButton jButton590;
    private javax.swing.JButton jButton591;
    private javax.swing.JButton jButton592;
    private javax.swing.JButton jButton593;
    private javax.swing.JButton jButton594;
    private javax.swing.JButton jButton595;
    private javax.swing.JButton jButton596;
    private javax.swing.JButton jButton597;
    private javax.swing.JButton jButton598;
    private javax.swing.JButton jButton599;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton600;
    private javax.swing.JButton jButton601;
    private javax.swing.JButton jButton602;
    private javax.swing.JButton jButton603;
    private javax.swing.JButton jButton604;
    private javax.swing.JButton jButton605;
    private javax.swing.JButton jButton606;
    private javax.swing.JButton jButton607;
    private javax.swing.JButton jButton608;
    private javax.swing.JButton jButton609;
    private javax.swing.JButton jButton610;
    private javax.swing.JButton jButton611;
    private javax.swing.JButton jButton612;
    private javax.swing.JButton jButton613;
    private javax.swing.JButton jButton614;
    private javax.swing.JButton jButton615;
    private javax.swing.JButton jButton616;
    private javax.swing.JButton jButton617;
    private javax.swing.JButton jButton618;
    private javax.swing.JButton jButton619;
    private javax.swing.JButton jButton620;
    private javax.swing.JButton jButton621;
    private javax.swing.JButton jButton622;
    private javax.swing.JButton jButton623;
    private javax.swing.JButton jButton624;
    private javax.swing.JButton jButton625;
    private javax.swing.JButton jButton626;
    private javax.swing.JButton jButton627;
    private javax.swing.JButton jButton628;
    private javax.swing.JButton jButton629;
    private javax.swing.JButton jButton630;
    private javax.swing.JButton jButton631;
    private javax.swing.JButton jButton632;
    private javax.swing.JButton jButton633;
    private javax.swing.JButton jButton634;
    private javax.swing.JButton jButton635;
    private javax.swing.JButton jButton636;
    private javax.swing.JButton jButton637;
    private javax.swing.JButton jButton638;
    private javax.swing.JButton jButton639;
    private javax.swing.JButton jButton640;
    private javax.swing.JButton jButton641;
    private javax.swing.JButton jButton642;
    private javax.swing.JButton jButton643;
    private javax.swing.JButton jButton644;
    private javax.swing.JButton jButton645;
    private javax.swing.JButton jButton646;
    private javax.swing.JButton jButton647;
    private javax.swing.JButton jButton648;
    private javax.swing.JButton jButton649;
    private javax.swing.JButton jButton650;
    private javax.swing.JButton jButton651;
    private javax.swing.JButton jButton652;
    private javax.swing.JButton jButton653;
    private javax.swing.JButton jButton654;
    private javax.swing.JButton jButton655;
    private javax.swing.JButton jButton656;
    private javax.swing.JButton jButton657;
    private javax.swing.JButton jButton658;
    private javax.swing.JButton jButton659;
    private javax.swing.JButton jButton660;
    private javax.swing.JButton jButton661;
    private javax.swing.JButton jButton662;
    private javax.swing.JButton jButton663;
    private javax.swing.JButton jButton664;
    private javax.swing.JButton jButton665;
    private javax.swing.JButton jButton666;
    private javax.swing.JButton jButton667;
    private javax.swing.JButton jButton668;
    private javax.swing.JButton jButton669;
    private javax.swing.JButton jButton670;
    private javax.swing.JButton jButton671;
    private javax.swing.JButton jButton672;
    private javax.swing.JButton jButton673;
    private javax.swing.JButton jButton674;
    private javax.swing.JButton jButton675;
    private javax.swing.JButton jButton676;
    private javax.swing.JButton jButton677;
    private javax.swing.JButton jButton678;
    private javax.swing.JButton jButton679;
    private javax.swing.JButton jButton680;
    private javax.swing.JButton jButton681;
    private javax.swing.JButton jButton682;
    private javax.swing.JButton jButton683;
    private javax.swing.JButton jButton684;
    private javax.swing.JButton jButton685;
    private javax.swing.JButton jButton686;
    private javax.swing.JButton jButton687;
    private javax.swing.JButton jButton688;
    private javax.swing.JButton jButton689;
    private javax.swing.JButton jButton690;
    private javax.swing.JButton jButton691;
    private javax.swing.JButton jButton692;
    private javax.swing.JButton jButton693;
    private javax.swing.JButton jButton694;
    private javax.swing.JButton jButton695;
    private javax.swing.JButton jButton696;
    private javax.swing.JButton jButton697;
    private javax.swing.JButton jButton698;
    private javax.swing.JButton jButton699;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton700;
    private javax.swing.JButton jButton701;
    private javax.swing.JButton jButton702;
    private javax.swing.JButton jButton703;
    private javax.swing.JButton jButton704;
    private javax.swing.JButton jButton705;
    private javax.swing.JButton jButton706;
    private javax.swing.JButton jButton707;
    private javax.swing.JButton jButton708;
    private javax.swing.JButton jButton709;
    private javax.swing.JButton jButton710;
    private javax.swing.JButton jButton711;
    private javax.swing.JButton jButton712;
    private javax.swing.JButton jButton713;
    private javax.swing.JButton jButton714;
    private javax.swing.JButton jButton715;
    private javax.swing.JButton jButton716;
    private javax.swing.JButton jButton717;
    private javax.swing.JButton jButton718;
    private javax.swing.JButton jButton719;
    private javax.swing.JButton jButton720;
    private javax.swing.JButton jButton721;
    private javax.swing.JButton jButton722;
    private javax.swing.JButton jButton723;
    private javax.swing.JButton jButton724;
    private javax.swing.JButton jButton725;
    private javax.swing.JButton jButton726;
    private javax.swing.JButton jButton727;
    private javax.swing.JButton jButton728;
    private javax.swing.JButton jButton729;
    private javax.swing.JButton jButton730;
    private javax.swing.JButton jButton731;
    private javax.swing.JButton jButton732;
    private javax.swing.JButton jButton733;
    private javax.swing.JButton jButton734;
    private javax.swing.JButton jButton735;
    private javax.swing.JButton jButton736;
    private javax.swing.JButton jButton737;
    private javax.swing.JButton jButton738;
    private javax.swing.JButton jButton739;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField74;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField78;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField80;
    private javax.swing.JTextField jTextField81;
    private javax.swing.JTextField jTextField82;
    private javax.swing.JTextField jTextField83;
    private javax.swing.JTextField jTextField84;
    private javax.swing.JTextField jTextField85;
    private javax.swing.JTextField jTextField86;
    private javax.swing.JTextField jTextField87;
    private javax.swing.JTextField jTextField88;
    private javax.swing.JTextField jTextField89;
    private javax.swing.JTextField jTextField90;
    private javax.swing.JTextField jTextField91;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane10;
    private javax.swing.JTextPane jTextPane11;
    private javax.swing.JTextPane jTextPane12;
    private javax.swing.JTextPane jTextPane13;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane jTextPane6;
    private javax.swing.JTextPane jTextPane7;
    private javax.swing.JTextPane jTextPane8;
    private javax.swing.JTextPane jTextPane9;
    // End of variables declaration//GEN-END:variables


}
