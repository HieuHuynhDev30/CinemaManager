/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

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
import Views.QuanLy;
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
        managerView.addListSuatChieuSelectionListener(new ListSuatChieuSelectionListener());
        managerView.addEditSuatChieuListener(new EditSuatChieuListener());
        managerView.addAddSuatChieuListener(new AddSuatChieuListener());
        managerView.addClearSchFieldListener(new ClearSuatChieuListener());
        managerView.addDeleteSuatChieuListener(new DeleteSuatChieuListener());
        managerView.addSortSuatChieuListener(new SapXepSuatChieuListener());
        managerView.addSortDoanhThuPhimListener(new SapXepDtPhimListener());
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
}
