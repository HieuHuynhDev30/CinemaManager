/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Classes.GheDoi;
import Classes.GheThuong;
import Classes.GheVip;
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
    private List<SuatChieu> schList;
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
        managerView.addAddSuatChieuListener(new SapXepDtSchListener());
        managerView.addSchDateTimePickerListener(new ShowSchDateTimeListener());
        
        managerView.addListPhongSelectionListener(new ListPhongSelectionListener());
        managerView.addEditPhongListener(new EditPhongListener());
        managerView.addClearPhongFieldListener(new ClearPhongListener());
        managerView.addDeletePhongListener(new DeletePhongListener());
        managerView.addSortPhongListener(new SapXepPhongListener());
        
        managerView.addSortDoanhThuPhimListener(new SapXepDtPhimListener());
        managerView.addSortDoanhThuSchListener(new SapXepDtSchListener());
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
        managerView.addNSDatePickerListener(new ShowNSDateListener());
        //ket thuc
        
        
    }
    
    public void showManagerView() {
        List<Phong> phongList = phongFunc.getPhongList();
        List<SuatChieu> suatList = suatChieuFunc.getSuatChieuList();
        this.managerView.setVisible(true);
        managerView.showScrollPhimList(phimList);
        managerView.showScrollPhongList(phongList);
        managerView.showScrollSuatList(suatList);
        
        managerView.showListSuatChieu(suatList);
        managerView.setSuatChieuCombo(phimList, phongList);
        
        managerView.showDoanhThu(doanhThuFunc.doanhThu("tong", (Object) null));
        managerView.showDoanhThuPhim(phimList, veList);
        managerView.showDoanhThuSch(schList, veList);
        managerView.showDtPhongList(phongList, suatList);
        
        managerView.showListPhim(phimList);
        managerView.setTheLoaiComboAndDoTuoiSpinner();
        
        managerView.showListKhach(khachList);
        managerView.setGioiTinhCombo();
        
        managerView.showListVe(veList, khachList);
        
        managerView.showListPhong(phongList, suatList);
        managerView.setPhongSortCombo(phongList, suatList);
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
            schList = suatChieuFunc.sapXepSuatChieu((ArrayList) schList, managerView.getTieuChiSxSch(), managerView.getSchTangDan());
            managerView.showListSuatChieu(schList);
            suatChieuFunc.writeListSuatChieus(schList);
        }
    }
    
    class SapXepDtPhimListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            phimList = phimFunc.sapXepPhim((ArrayList) phimList, "doanhthu", managerView.getDtPhimTangDan());
            managerView.clearDoanhThuPhim();
            managerView.showDoanhThuPhim(phimList, veList);
            phimFunc.writeListPhims(phimList);
        }
    }
    
    class SapXepDtSchListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            schList = suatChieuFunc.sapXepSuatChieu((ArrayList) schList, "doanhthu", managerView.getSchTangDan());
            managerView.clearDoanhThuSch();
            managerView.showDoanhThuSch(schList, veList);
            suatChieuFunc.writeListSuatChieus((ArrayList) schList);
        }
    }
    
    class SapXepDtPhongListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            phongList = phongFunc.sapXepPhong((ArrayList) phongList, "doanhthu", managerView.getDtPhimTangDan());
            managerView.showDoanhThuPhim(phimList, veList);
            phongFunc.writeListPhongs((ArrayList) phongList);
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
                khachFunc.editKhach(khach);
                managerView.showKhach(khach);
                managerView.showListKhach(khachFunc.getKhachList());
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
            if (k == 0) {
                managerView.showListKhach(khachFunc.searchTen(s));
            } else if (k == 1) {
                managerView.showListKhach(khachFunc.searchSLv(s));
            } else if (k == -1) {
                managerView.showListKhach(khachFunc.getKhachList());
            }
            
        }
        
    }
    // ketthuc

    //// Quản lý Phòng listener
    class ListPhongSelectionListener implements ListSelectionListener {
        
        public void valueChanged(ListSelectionEvent e) {
            managerView.fillPhongFromSelectedRow(phimList);
        }
    }
    
    class EditPhongListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            Phong phong = managerView.getPhongInfo(phongList);
            for (int i = 0; i < phong.getSlThuong(); i++) {
                GheThuong gt = new GheThuong();
                phong.themGhe(gt);
            }
            for (int i = 0; i < phong.getSlVip(); i++) {
                GheVip gv = new GheVip();
                phong.themGhe(gv);
            }
            for (int i = 0; i < phong.getSlDoi(); i++) {
                GheDoi gd = new GheDoi();
                phong.themGhe(gd);
            }
            if (phong != null) {
                phongFunc.editPhong(phong);
                managerView.showPhong(phong);
                managerView.showListPhong(phongFunc.getPhongList(), schList);
                managerView.showMessage("Cập nhật thành công!\nTất cả các ghế đều trống");
            }
        }
    }
    
    class ClearPhongListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            managerView.clearPhongInfo();
        }
    }
    
    class DeletePhongListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            Phong phong = managerView.getPhongInfo(phongList);
            if (phong != null) {
                phongFunc.xoaPhong(phong);
                managerView.clearPhongInfo();
                managerView.showListPhong(phongList, schList);
                managerView.showMessage("Xóa thành công!");
            }
        }
    }
    
    class SapXepPhongListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {            
            phongList = phongFunc.sapXepPhong((ArrayList) phongList, managerView.getTieuChiSxPhong(), managerView.getPhongTangDan());
            managerView.showListPhong(phongList, schList);
            phongFunc.writeListPhongs(phongList);
        }
    }
    ///// end Quản lý phòng listener
    
    class ShowSchDateTimeListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {            
            managerView.confirmSchDateTimePicker(managerView.getSchDateTimePicker());
        }
    }
    
    class ShowNSDateListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {            
            managerView.confirmNSPicker(managerView.getNgaySinhPicker());
        }
    }
}
