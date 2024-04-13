/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Classes.Ghe;
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
import java.time.LocalDateTime;
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

    public ManagerController(ManagerView managerView) {
        this.managerView = managerView;
        phongFunc = new PhongFunc();
        phimFunc = new PhimFunc();
        suatChieuFunc = new SuatChieuFunc();
        khachFunc = new KhachFunc();
        doanhThuFunc = new DoanhThuFunc();
        veFunc = new VeFunc();
        managerView.addListSuatChieuSelectionListener(new ListSuatChieuSelectionListener());
        managerView.addEditSuatChieuListener(new EditSuatChieuListener());
        managerView.addAddSuatChieuListener(new AddSuatChieuListener());
        managerView.addClearSchFieldListener(new ClearSuatChieuListener());
        managerView.addDeleteSuatChieuListener(new DeleteSuatChieuListener());
        managerView.addSortSuatChieuListener(new SapXepSuatChieuListener());
//        managerView.addAddSuatChieuListener(new SapXepDtSchListener());
        managerView.addSchDateTimePickerListener(new ShowSchDateTimeListener());

        managerView.addListPhongSelectionListener(new ListPhongSelectionListener());
        managerView.addEditPhongListener(new EditPhongListener());
        managerView.addClearPhongFieldListener(new ClearPhongListener());
        managerView.addDeletePhongListener(new DeletePhongListener());
        managerView.addSortPhongListener(new SapXepPhongListener());

        managerView.addSortDoanhThuPhimListener(new SapXepDtPhimListener());
        managerView.addSortDoanhThuSchListener(new SapXepDtSchListener());
        managerView.addAddPhongListener(new AddPhongListener());

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

        managerView.addSearchVeListener(new SearchVeListener());
        managerView.addDeleteVeListener(new DeleteVeListener());
        managerView.addSortNameVeListener(new SortByNameVeListener());
        managerView.addSortPhimVeListener(new SortByPhimVeListener());
//   
//        managerView.addAddVeListener(new AddVeListener());
        managerView.addDatVeListener(new DatVeListener());
        managerView.addListSuatChieuSelectionListener1(new ListSuatChieuDatVeSelectionListener());
        managerView.addListGheSelectionListener(new ListGheSelectionListener());
//        managerView.addSearchVeListener(new SearchVeListener());
        managerView.addselectSeatListener(new SelectSeatListener());
        managerView.addListGheSelectionListener(new ListGheSelectionListener());
        managerView.addReloadListener(new ReloadListener());
        managerView.addResetDoanhThuListener(new ResetDtListner());
    }

    public void showManagerView() {
        this.managerView.setVisible(true);
        managerView.clearScrollList();
        managerView.showScrollPhimList(phimFunc.getPhimList());
        managerView.showScrollPhongList(phongFunc.getPhongList());
        managerView.showScrollSuatList(suatChieuFunc.getSuatChieuList());

        phongFunc.checkPlaying();
        phongFunc.checkIsFull();
        phongFunc.reSetPhong();
        suatChieuFunc.checkChieuXong();

        managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
        managerView.setPhongSortCombo(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());

        managerView.showListPhim(phimFunc.getPhimList());
        managerView.setTheLoaiComboAndDoTuoiSpinner();

        managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
        managerView.setSuatChieuCombo(phimFunc.getPhimList(), phongFunc.getPhongList());
        suatChieuFunc.checkChieuXong();

        managerView.showListKhach(khachFunc.getKhachList());
        managerView.setGioiTinhCombo();

        managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
        managerView.setVeCombo(suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList(), khachFunc.getKhachList());
        managerView.showListSuatChieuDatVe(suatChieuFunc.getSuatChieuList());

        managerView.showDoanhThu(doanhThuFunc.doanhThu("tong", (Object) null));
        managerView.showDoanhThuPhim(phimFunc.getPhimList(), veFunc.getVeList());
        managerView.showDoanhThuSch(suatChieuFunc.getSuatChieuList(), veFunc.getVeList());
        managerView.showDtPhongList(phongFunc.getPhongList(), veFunc.getVeList());

    }

    class ReloadListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.setVisible(false);
            showManagerView();
        }
    }

    class ListSuatChieuSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillSuatChieuFromSelectedRow(phimFunc.getPhimList());
        }
    }

    class EditSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SuatChieu sch = managerView.getSchInfo(phimFunc.getPhimList(), phongFunc.getPhongList());
            if (sch != null) {
                for (Phong ph : phongFunc.getPhongList()) {
                    if (ph.getId().equals(sch.getPhongId())) {
                        if (ph.getIsPlaying()) {
                            managerView.showMessage("Phòng chưa rảnh");
                            return;
                        }
                        ph.setSuatChieu(sch);
                        phongFunc.editPhong(ph);
                    }
                }
                suatChieuFunc.editSuatChieu(sch);
                managerView.showSuatChieu(sch);
                managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class AddSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("check sch");
            SuatChieu infoSch = managerView.getSchInfo(phimFunc.getPhimList(), phongFunc.getPhongList());
            if (infoSch == null) {
                return;
            }
            SuatChieu newSch = suatChieuFunc.taoSuatChieu(infoSch.getPhim(), infoSch.getPhong(), infoSch.getThoiGianChieu().toString());
            if (newSch == null) {
                managerView.showMessage("Thời gian chiếu trùng với suất chiếu khác");
                return;
            }
            if (phongFunc.getPhongList() != null) {
                for (Phong ph : phongFunc.getPhongList()) {
                    if (ph.getId().equals(newSch.getPhongId())) {
//                        if (ph.getSuatChieu() != null) {
//                            managerView.showMessage("Phòng đã đặt suất chiếu khác");
//                            return;
//                        }
                        if (ph.getIsPlaying()) {
                            managerView.showMessage("Phòng chưa rảnh");
                            return;
                        }
                        if (ph.getSuatChieu() == null) {
                            ph.setSuatChieu(newSch);
                        } else {
                            if (ph.getSuatChieu().isChieuXong()) {
                                ph.setSuatChieu(newSch);
                            }
                        }
                        phongFunc.editPhong(ph);
                    }
                }
            }
            suatChieuFunc.themSuatChieu(newSch);
            managerView.showSuatChieu(newSch);
            managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
            managerView.showMessage("Thêm thành công!");
        }
    }

    class ClearSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.clearSuatChieuInfo();
        }
    }

    class DeleteSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SuatChieu sch = managerView.getSchInfo(phimFunc.getPhimList(), phongFunc.getPhongList());
            if (sch != null) {
                if (phongFunc.getPhongList() != null) {
                    for (Phong ph : phongFunc.getPhongList()) {
                        if (ph.getId().equals(sch.getPhongId())) {
                            ph.setSuatChieu(null);
                            ph.setIsPlaying(false);
                            phongFunc.editPhong(ph);
                        }
                    }
                }
                suatChieuFunc.xoaSuatChieu(sch);
                managerView.clearSuatChieuInfo();
                managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    class SapXepSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            suatChieuFunc.setSuatChieuList(suatChieuFunc.sapXepSuatChieu((ArrayList) suatChieuFunc.getSuatChieuList(), managerView.getTieuChiSxSch(), managerView.getSchTangDan()));
            managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
            suatChieuFunc.writeListSuatChieus(suatChieuFunc.getSuatChieuList());
        }
    }

    class SapXepDtPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            phimFunc.setPhimList(phimFunc.sapXepPhim((ArrayList) phimFunc.getPhimList(), "doanhthu", managerView.getDtPhimTangDan()));
            managerView.clearDoanhThuPhim();
            managerView.showDoanhThuPhim(phimFunc.getPhimList(), veFunc.getVeList());
            phimFunc.writeListPhims(phimFunc.getPhimList());
        }
    }

    class SapXepDtSchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            suatChieuFunc.setSuatChieuList(suatChieuFunc.sapXepSuatChieu((ArrayList) suatChieuFunc.getSuatChieuList(), "doanhthu", managerView.getSchTangDan()));
            managerView.clearDoanhThuSch();
            managerView.showDoanhThuSch(suatChieuFunc.getSuatChieuList(), veFunc.getVeList());
            suatChieuFunc.writeListSuatChieus((ArrayList) suatChieuFunc.getSuatChieuList());
        }
    }

    class SapXepDtPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            phongFunc.setPhongList(phongFunc.sapXepPhong((ArrayList) phongFunc.getPhongList(), "doanhthu", managerView.getDtPhimTangDan()));
            managerView.showDoanhThuPhim(phimFunc.getPhimList(), veFunc.getVeList());
            phongFunc.writeListPhongs((ArrayList) phongFunc.getPhongList());
        }
    }

    class ResetDtListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            doanhThuFunc.resetDt(phimFunc.getPhimList(), suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList());
            veFunc.setVeList(new ArrayList<>());
            veFunc.writeListVes(veFunc.getVeList());
            phimFunc.writeListPhims(phimFunc.getPhimList());
            suatChieuFunc.writeListSuatChieus(suatChieuFunc.getSuatChieuList());
            phongFunc.writeListPhongs(phongFunc.getPhongList());
            new ReloadListener().actionPerformed(e);
        }

    }

    // An listener
    class AddPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phim phim = managerView.getPhimInfo();
            if (phim != null) {
                phim.setPosterLink("/Images/no_poster.jpg");
                phimFunc.themPhim(phim);
                managerView.showPhim(phim);
                managerView.showListPhim(phimFunc.getPhimList());
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
                for (SuatChieu sc : suatChieuFunc.getSuatChieuList()) {
                    if (sc.getPhim().getId() == null ? phim.getId() == null : sc.getPhim().getId().equals(phim.getId())) {
                        suatChieuFunc.xoaSuatChieu(sc);
                    }
                }
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
            String link = "/Images/no_poster.jpg";
            for (Phim ph : phimFunc.getPhimList()) {
                if (ph.getId() == null ? managerView.getIDPhimField() == null : ph.getId().equals(managerView.getIDPhimField())) {
                    link = ph.getPosterLink();
                    break;
                }
            }
            managerView.fillPhimFromSelectedRow(link);
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
                managerView.setVeCombo(suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList(), khachFunc.getKhachList());
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
                managerView.setVeCombo(suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList(), khachFunc.getKhachList());
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
                managerView.setVeCombo(suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList(), khachFunc.getKhachList());
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
            managerView.fillPhongFromSelectedRow(phimFunc.getPhimList());
        }
    }

    class AddPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phong phong = managerView.getPhongInfo(phongFunc.getPhongList());
            if (phong != null) {
                // khachDao = new KhachDao();
                phongFunc.themPhong(phong);
                managerView.showPhong(phong);
                managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
                //studentView.showListStudents(studentDao.getListStudents());
//                managerView.setVeCombo(schList, phongList, khachList);
                managerView.showMessage("Thêm thành công!");
            }
        }
    }

    class EditPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phong phong = managerView.getPhongInfo(phongFunc.getPhongList());
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
                managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
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
            Phong phong = managerView.getPhongInfo(phongFunc.getPhongList());
            if (phong != null) {
                phongFunc.xoaPhong(phong);
                if (phong.getSuatChieu() != null) {
                    suatChieuFunc.xoaSuatChieu(phong.getSuatChieu());
                }
                managerView.clearPhongInfo();
                managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    class SapXepPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            phimFunc.setPhimList(phongFunc.sapXepPhong((ArrayList) phongFunc.getPhongList(), managerView.getTieuChiSxPhong(), managerView.getPhongTangDan()));
            managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
            phongFunc.writeListPhongs(phongFunc.getPhongList());
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

//thao tác với vé
//    class AddVeListener implements ActionListener {
//
//        public void actionPerformed(ActionEvent e) {
//            Ve ve = new Ve();
//            Ghe ghe = new Ghe();
//            Khach khach = managerView.getKhachInfor(khachFunc.getKhachList());
//            SuatChieu Sch = managerView.getDatVeSchInfo(suatChieuFunc.getSuatChieuList());
//            //SuatChieu Sch = managerView.getSch1Info(phimList, phongList);
//            ghe.setKhachId(khach.getId());
//            ve.setSuat(Sch);
//            //ve.setGhe(ghe);
//            //veFunc.themVe(ve);
//            // managerView.showListVe(veList, khachList);
//            managerView.showListGhe(Sch.getPhong());
//        }
//    }
    class DatVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //SuatChieu Sch = managerView.getSch1Info(phimList, phongList);
            SuatChieu Sch = managerView.getDatVeSchInfo(suatChieuFunc.getSuatChieuList());
            Ghe ghe = managerView.getGheInfor(Sch.getPhong());

            Ve ve = new Ve();
            Khach khach = managerView.getKhachInfor(khachFunc.getKhachList());
            ghe.setKhachId(khach.getId());
            ve.setSuat(Sch);

            ve.setGhe(ghe);
            khachFunc.muaVe(khach, ve);
            veFunc.themVe(ve);
            // veFunc.editVe(ve1);
            for (Phong ph : phongFunc.getPhongList()) {
                if (ph.getId() == null ? ve.getSuat().getPhongId() == null : ph.getId().equals(ve.getSuat().getPhongId())) {
                    if ("Thuong".equals(ghe.getLoai())) {
                        ph.getDsGheThuong().get(ghe.getViTri()).setKhachId(ghe.getKhachId());
                        ph.getDsGheThuong().get(ghe.getViTri()).setThoiGianDat(LocalDateTime.now());
                    }
                    if ("Vip".equals(ghe.getLoai())) {
                        ph.getDsGheVip().get(ghe.getViTri()).setKhachId(ghe.getKhachId());
                        ph.getDsGheVip().get(ghe.getViTri()).setThoiGianDat(LocalDateTime.now());
                    }
                    if ("Doi".equals(ghe.getLoai())) {
                        ph.getDsGheDoi().get(ghe.getViTri()).setKhachId(ghe.getKhachId());
                        ph.getDsGheDoi().get(ghe.getViTri()).setThoiGianDat(LocalDateTime.now());
                    }
                    ph.themDt(ghe.getGia());
                    phongFunc.editPhong(ph);
                    break;
                }
            }
            Sch.themDt(ve.getGhe().getGia());
            suatChieuFunc.editSuatChieu(Sch);
            managerView.closeSeatDialog();
            managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
            managerView.showListKhach(khachFunc.getKhachList());
            managerView.showMessage("Đặt vé thành công!");
            managerView.clearSuatChieuDatVeInfo();
            managerView.showSuatChieuDatVe(Sch);
            managerView.clearDoanhThuPhim();
            managerView.clearDoanhThuSch();
            managerView.showDoanhThu(doanhThuFunc.doanhThu("tong", (Object) null));
            managerView.showDoanhThuPhim(phimFunc.getPhimList(), veFunc.getVeList());
            managerView.showDtPhongList(phongFunc.getPhongList(), veFunc.getVeList());
            managerView.showDoanhThuSch(suatChieuFunc.getSuatChieuList(), veFunc.getVeList());
        }
    }

    class ListSuatChieuDatVeSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillSuatChieuDatVeFromSelectedRow(phimFunc.getPhimList());

        }
    }

    class ListGheSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillGheFromSelectedRow();
        }
    }

    class SelectSeatListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Ve ve = new Ve();
            Ghe ghe = new Ghe();
            Khach khach = managerView.getKhachInfor(khachFunc.getKhachList());
            SuatChieu Sch = managerView.getDatVeSchInfo(suatChieuFunc.getSuatChieuList());
            //SuatChieu Sch = managerView.getSch1Info(phimList, phongList);
            if (Sch.getPhong() != null) {
                ghe.setKhachId(khach.getId());
                ve.setSuat(Sch);
                //ve.setGhe(ghe);
                //veFunc.themVe(ve);
                // managerView.showListVe(veList, khachList);
                managerView.showListGhe(Sch.getPhong());
                managerView.fillGheFromSelectedRow();
                managerView.showSeatDialog();
            } else {
                managerView.showMessage("Chọn suất chiếu trước khi chọn ghế");
            }
        }
    }

    class SearchVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int k = managerView.luachonVeTK();
            String s = managerView.inforVeSearch();
            switch (k) {
                case 0:
                     managerView.showListVe(veFunc.SearchIdVe(s), khachFunc.getKhachList());
                    break;
                case 1:
                    managerView.showListVe(veFunc.SearchKhachVe(s), khachFunc.getKhachList());
                    break;
                case 2:
                     managerView.showListVe(veFunc.SearchVitriVe(s), khachFunc.getKhachList());
                    break;
                case 3:
                    managerView.showListVe(veFunc.SearchLoaiGhe(s), khachFunc.getKhachList());
                    break;
                case 4:
                    managerView.showListVe(veFunc.SearchTenPhim(s), khachFunc.getKhachList());
                    break;
                case 5:
                    managerView.showListVe(veFunc.SearchPhong(s), khachFunc.getKhachList());
                    break;
                case 6:
                    managerView.showListVe(veFunc.SearchThoiGian(s), khachFunc.getKhachList());
                    break;
                default:
                    managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
                    break;
            }

        }
    }

    class DeleteVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String s = managerView.getIDVe();
            veFunc.xoaVe(s);
            managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
            khachFunc.writeListKhachs(khachFunc.getKhachList());
            managerView.showListKhach(khachFunc.getKhachList());

        }
    }

    class SortByNameVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            veFunc.sortVeByName(khachFunc.getKhachList());
            managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());

        }
    }

    class SortByPhimVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            veFunc.sortVeByPhim(khachFunc.getKhachList());
            managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
        }
    }
}
