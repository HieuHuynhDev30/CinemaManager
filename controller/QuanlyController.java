/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Classes.Khach;
import Dao.KhachDao;
import View.Quanly;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ACER
 */
public class QuanlyController {
    private Quanly quanly;
    private KhachDao khachDao = new KhachDao();
   // khachDao = new KhachDao();

    public QuanlyController(Quanly quanly) {
        this.quanly = quanly;
        quanly.addAddKhachListener(new AddKhachListener());
        quanly.addClearListener(new ClearKhachListener());
        quanly.addListKhachSelectionListener(new ListKhachSelectionListener());
        quanly.addDeleteKhachListener(new DeleteKhachListener());
        quanly.addEditKhachListener(new EditKhachListener());
    }
    
    
    
    public void showQuanly(){
        List<Khach> khachList = khachDao.getListKhach();                
        quanly.setVisible(true);
        quanly.showListKhach(khachList);
    }
    
    class AddKhachListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Khach khach = quanly.getKhachInfo();
            if (khach != null) {
               // khachDao = new KhachDao();
                khachDao.add(khach);
                quanly.showKhach(khach);
                quanly.showListKhach(khachDao.getListKhach());
                //studentView.showListStudents(studentDao.getListStudents());
               quanly.showMessage("Thêm thành công!");
            }
        }
    }
    
    /**
     * Lớp ClearStudentListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     * 
     * @author viettuts.vn
     */
    class ClearKhachListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            quanly.clearKhachInfo();
        }
    }
    
    /**
     * Lớp DeleteStudentListener 
     * chứa cài đặt cho sự kiện click button "Delete"
     * 
     * @author viettuts.vn
     */
    class DeleteKhachListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Khach khach = quanly.getKhachInfo();
            if (khach != null) {
                khachDao.delete(khach);
                quanly.clearKhachInfo();
                quanly.showListKhach(khachDao.getListKhach());
                quanly.showMessage("Xóa thành công!");
            }
        }
    }
    
    
    
    /**
     * Lớp EditStudentListener 
     * chứa cài đặt cho sự kiện click button "Edit"
     * 
     * @author viettuts.vn
     */
    class EditKhachListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Khach khach = quanly.getKhachInfo();
            if (khach != null) {
                khachDao.edit(khach);
                quanly.showKhach(khach);
                quanly.showListKhach(khachDao.getListKhach());
                quanly.showMessage("Cập nhật thành công!");
            }
        }
    }
    
    /**
     * Lớp ListStudentSelectionListener 
     * chứa cài đặt cho sự kiện chọn student trong bảng student
     * 
     * @author viettuts.vn
     */
    class ListKhachSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            quanly.fillKhachFromSelectedRow();
        }
    }
}
