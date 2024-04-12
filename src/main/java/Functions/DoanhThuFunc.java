/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Khach;
import Classes.Phong;
import Classes.SuatChieu;
import Classes.Ve;

/**
 *
 * @author Lenovo
 */
public class DoanhThuFunc {
    
    private VeFunc veFunc;

    public DoanhThuFunc() {
        veFunc = new VeFunc();
    }
    
    
    public double doanhThu(String tieuChi, Object ...obs){
        double doanhThu = 0;
        if ("tong".equals(tieuChi.toLowerCase())) {
            for (Ve ve : veFunc.getVeList()) {
                doanhThu += ve.getGhe().getGia();
            }
        }
        if ("khach".equals(tieuChi.toLowerCase())) {
            for (Khach ob : (Khach[]) obs) {
                doanhThu += ob.getTongTien();
            }
        }
        if ("phim".equals(tieuChi.toLowerCase())) {
            for (Ve ve : veFunc.getVeList()) {
                for (String phimId : (String[]) obs) {
                    if (ve.getSuat().getPhim().getId() == null ? phimId == null : ve.getSuat().getPhim().getId().equals(phimId)) {
                        doanhThu += ve.getGhe().getGia();
                    }
                }
            }
        }
        if ("phong".equals(tieuChi.toLowerCase())) {
            for (Phong ph : (Phong[]) obs) {
                doanhThu += ph.getDt();
            }
        }
        
//        if ("suatchieu".equals(tieuChi.toLowerCase())) {
//            for (SuatChieu sch : (SuatChieu[]) obs) {
//                doanhThu += sch.getDoanhThu();
//            }
//        }
        
        return doanhThu;
    }
}
