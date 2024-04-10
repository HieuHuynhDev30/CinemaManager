/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Classes.Ghe;
import Classes.Khach;
import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import Classes.Ve;
import Functions.DoanhThuFunc;
import Functions.KhachFunc;
import Functions.PhimFunc;
import Functions.PhongFunc;
import Functions.SuatChieuFunc;
import Functions.VeFunc;
import Views.ManagerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Lenovo
 */
public class ManagerController {

    private ManagerView managerView;
    private PhongFunc phongFunc;
    private PhimFunc phimFunc;
    private SuatChieuFunc suatChieuFunc;
    private KhachFunc khachFunc;
    private VeFunc veFunc;
    private DoanhThuFunc doanhThuFunc;
    private List<Phim> phimList;
    private List<Phong> phongList;
    private ArrayList<SuatChieu> schList;
    private List<Ve> veList;
    private List<Khach> khachList;
   
    

    public ManagerController(ManagerView managerView) {
        this.managerView = managerView;
        phongFunc = new PhongFunc();
        phimFunc = new PhimFunc();
        suatChieuFunc = new SuatChieuFunc();
        khachFunc = new KhachFunc();
        doanhThuFunc = new DoanhThuFunc();
        veFunc = new VeFunc();
        this.phimList = phimFunc.getPhimList();
        this.phongList = phongFunc.getPhongList();
        this.schList = (ArrayList) suatChieuFunc.getSuatChieuList();
        this.veList = veFunc.getVeList();
        this.khachList = khachFunc.getKhachList();
        managerView.addListSuatChieuSelectionListener(new ListSuatChieuSelectionListener());
        managerView.addEditSuatChieuListener(new EditSuatChieuListener());
        managerView.addAddSuatChieuListener(new AddSuatChieuListener());
        managerView.addClearSchFieldListener(new ClearSuatChieuListener());
        managerView.addDeleteSuatChieuListener(new DeleteSuatChieuListener());
        managerView.addSortSuatChieuListener(new SapXepSuatChieuListener());
        managerView.addSortDoanhThuPhimListener(new SapXepDtPhimListener());

        //An addListener
        managerView.addAddPhimListener(new AddPhimListener());
        managerView.addClearPhimListener(new ClearPhimListener());
        managerView.addListPhimSelectionListener(new ListPhimSelectionListener());
        managerView.addDeletePhimListener(new DeletePhimListener());
        managerView.addEditPhimListener(new EditPhimListener());
        managerView.addSearchPhimListener(new SearchPhimListener());

        managerView.addAddKhachListener(new AddKhachListener());
        managerView.addClearListener(new ClearKhachListener());
        managerView.addListKhachSelectionListener(new ListKhachSelectionListener());
        managerView.addDeleteKhachListener(new DeleteKhachListener());
        managerView.addEditKhachListener(new EditKhachListener());
        managerView.addSearchKhachListener(new SearchKhachListener());
        //ket thuc
        managerView.addAddVeListener(new AddVeListener());
        managerView.addDatVeListener(new DatVeListener());
        
        managerView.addListSuatChieuSelectionListener1(new ListSuatChieuSelectionListener1());
    }

    public void showManagerView() {
       
        
        this.managerView.setVisible(true);
        managerView.showScrollPhimList(phimList);
        managerView.showScrollPhongList(phongList);
        managerView.showScrollSuatList(schList);
        managerView.showListSuatChieu(schList);
        managerView.setSuatChieuCombo(phimList, phongList);
        managerView.showDoanhThu(doanhThuFunc.doanhThu("tong", (Object) null));
        managerView.showDoanhThuPhim(phimList, veList);
        managerView.showListPhim(phimList);
        managerView.showListKhach(khachList);
        managerView.showListVe(veList, khachList);
        //
        managerView.setVeCombo(schList, phongList, khachList);
        managerView.showListSuatChieu1(schList);
    }

    class ListSuatChieuSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillSuatChieuFromSelectedRow(phimList);
        }
    }

    class EditSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SuatChieu sch = managerView.getSchInfo(phimList, phongList);
            if (sch != null) {
                suatChieuFunc.editSuatChieu(sch);
                managerView.showSuatChieu(sch);
                managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class AddSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SuatChieu sch = new SuatChieu();
            SuatChieu newSch = managerView.getSchInfo(phimList, phongList);
            if (newSch != null) {
                newSch.setId(sch.getId());
                suatChieuFunc.themSuatChieu(newSch);
                managerView.showSuatChieu(newSch);
                managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Thêm thành công!");
            }
        }
    }

    class ClearSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.clearSuatChieuInfo();
        }
    }

    class DeleteSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SuatChieu sch = managerView.getSchInfo(phimList, phongList);
            if (sch != null) {
                suatChieuFunc.xoaSuatChieu(sch);
                managerView.clearSuatChieuInfo();
                managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    class SapXepSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            schList = suatChieuFunc.sapXepSuatChieu(schList, managerView.getTieuChiSxSch(), managerView.getSchTangDan());
            managerView.showListSuatChieu(schList);
            suatChieuFunc.writeListSuatChieus(schList);
        }
    }

    class SapXepDtPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            phimList = phimFunc.sapXepPhim((ArrayList) phimList, "doanhthu", managerView.getDtPhimTangDan());
            managerView.clearDoanhThuPhim();
            managerView.showDoanhThuPhim(phimList, veList);
            System.out.println("da show");
            phimFunc.writeListPhims(phimList);
        }
    }

    // An listener
    class AddPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phim phim = managerView.getPhimInfo();
            if (phim != null) {
                // khachDao = new KhachDao();
                phimFunc.themPhim(phim);
                managerView.showPhim(phim);
                managerView.showListPhim(phimFunc.getPhimList());
               // managerView.setSuatChieuCombo(phimList, phongList);
                //studentView.showListStudents(studentDao.getListStudents());
                managerView.showMessage("Thêm thành công!");
            }
        }
    }

    class ClearPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.clearPhimInfo();
        }
    }

    class DeletePhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phim phim = managerView.getPhimInfo();
            if (phim != null) {
                phimFunc.xoaPhim(phim);
                managerView.clearPhimInfo();
                managerView.showListPhim(phimFunc.getPhimList());
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    class EditPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phim phim = managerView.getPhimInfo();
            if (phim != null) {
                phimFunc.edit(phim);
                managerView.showPhim(phim);
                managerView.showListPhim(phimFunc.getPhimList());
                managerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class ListPhimSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillPhimFromSelectedRow();
        }
    }

    class SearchPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int k = managerView.luachonPhimTK();
            String s = managerView.inforPhimSearch();
            switch (k) {
                case 0:
                    managerView.showListPhim(phimFunc.searchTen(s));
                    break;
                case 1:
                    managerView.showListPhim(phimFunc.searchTheLoai(s));
                    break;
                case -1:
                    managerView.showListPhim(phimFunc.getPhimList());
                    break;
                case 2:
                    managerView.showListPhim(phimFunc.searchDoTuoi(s));
                    break;
                default:
                    break;
            }

        }

    }

    //ket thuc
    // Khach listner cua An
    class AddKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Khach khach = managerView.getKhachInfo();
            if (khach != null) {
                // khachDao = new KhachDao();
                khachFunc.themKhach(khach);
                managerView.showKhach(khach);
                managerView.showListKhach(khachFunc.getKhachList());
                //studentView.showListStudents(studentDao.getListStudents());
                managerView.setVeCombo(schList, phongList, khachList);
                managerView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp ClearStudentListener chứa cài đặt cho sự kiện click button "Clear"
     *
     * @author viettuts.vn
     */
    class ClearKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.clearKhachInfo();
        }
    }

    /**
     * Lớp DeleteStudentListener chứa cài đặt cho sự kiện click button "Delete"
     *
     * @author viettuts.vn
     */
    class DeleteKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Khach khach = managerView.getKhachInfo();
            if (khach != null) {
                khachFunc.xoaKhach(khach);
                managerView.clearKhachInfo();
                managerView.showListKhach(khachFunc.getKhachList());
                managerView.setVeCombo(schList, phongList, khachList);
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp EditStudentListener chứa cài đặt cho sự kiện click button "Edit"
     *
     * @author viettuts.vn
     */
    class EditKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Khach khach = managerView.getKhachInfo();
            if (khach != null) {
                khachFunc.edit(khach);
                managerView.showKhach(khach);
                managerView.showListKhach(khachFunc.getKhachList());
                managerView.setVeCombo(schList, phongList, khachList);
                managerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp ListStudentSelectionListener chứa cài đặt cho sự kiện chọn student
     * trong bảng student
     *
     * @author viettuts.vn
     */
    class ListKhachSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillKhachFromSelectedRow();
        }
    }
    
    class SearchKhachListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int k = managerView.luachonKhachTK();
            String s = managerView.inforKhachSearch();
            if (k == 0){
                managerView.showListKhach(khachFunc.searchTen(s));
            } else if (k == 1){
                managerView.showListKhach(khachFunc.searchSLv(s));
            } else if (k == -1){
               managerView.showListKhach(khachFunc.getKhachList());
            } 
                
        }
        
    }
    // ketthuc
    
    
    //thao tác với vé
    class AddVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Ve ve = new Ve();
            Ghe ghe = new Ghe();
            Khach khach = managerView.getKhachInfor(khachList);
            SuatChieu Sch = managerView.getSch1Info(schList);
            //SuatChieu Sch = managerView.getSch1Info(phimList, phongList);
            ghe.setKhachId(khach.getId());
            ve.setSuat(Sch);
            //ve.setGhe(ghe);
            //veFunc.themVe(ve);
           // managerView.showListVe(veList, khachList);
            managerView.showListGhe(Sch.getPhong());
                     
            
         
        }
    }
    
    class DatVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //SuatChieu Sch = managerView.getSch1Info(phimList, phongList);
            SuatChieu Sch = managerView.getSch1Info(schList);
            Ghe ghe = managerView.getGheInfor(Sch.getPhong());
            Ve ve = new Ve();
            Khach khach = managerView.getKhachInfor(khachList);            
            ghe.setKhachId(khach.getId());
            ve.setSuat(Sch);
         
            //Ve ve1 = veFunc.SearchVe(ve);
           // veFunc.xoaVe(ve1);
            ve.setGhe(ghe);
            khachFunc.muaVe(khach, ve);
            veFunc.themVe(ve);
          // veFunc.editVe(ve1);
            managerView.showListVe(veList, khachList);
            managerView.showListKhach(khachFunc.getKhachList());         
            managerView.showMessage("Đặt vé thành công!");
            managerView.showDoanhThuPhim(phimList, veList);
            managerView.clearSuatChieuInfo1();
            managerView.showDoanhThu(doanhThuFunc.doanhThu("tong", (Object) null));
         
        }
    }
    
    class ListSuatChieuSelectionListener1 implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillSuatChieuFromSelectedRow1(phimList);
        }
    }
    //ketthuc
    
}
