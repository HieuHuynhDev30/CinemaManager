/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import Functions.KhachFunc;
import Functions.PhimFunc;
import Functions.PhongFunc;
import Functions.SuatChieuFunc;
import Functions.VeFunc;
import Views.ManagerView;
import Views.QuanLy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    public ManagerController(ManagerView managerView) {
        this.managerView = managerView;
        phongFunc = new PhongFunc();
        phimFunc = new PhimFunc();
        suatChieuFunc = new SuatChieuFunc();
        khachFunc =  new KhachFunc();
        veFunc = new VeFunc();
    }
    
    public void showManagerView() {
        List<Phim> phimList = phimFunc.getPhimList();
        List<Phong> phongList = phongFunc.getPhongList();
        List<SuatChieu> suatList = suatChieuFunc.getSuatChieuList();
        this.managerView.setVisible(true);
        managerView.showScrollPhimList(phimList);
        managerView.showScrollPhongList(phongList);
        managerView.showScrollSuatList(suatList);
    }
}
