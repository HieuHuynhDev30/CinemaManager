/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Khach;
import Classes.Phim;
import Classes.SuatChieu;
import XML.KhachListXML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class KhachFunc {

    private static final String KHACH_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Khach.xml";
    private List<Khach> khachList;

    public KhachFunc() {
        this.khachList = this.readListKhachs();
    }

    public void writeListKhachs(List<Khach> khachs) {
        KhachListXML khachXML = new KhachListXML();
        khachXML.setKhach(khachs);
        FileUtils.writeXMLtoFile(KHACH_FILE_NAME, khachXML);
    }

    public List<Khach> readListKhachs() {
        List<Khach> list = new ArrayList<>();
        KhachListXML khachListXML = (KhachListXML) FileUtils.readXMLFile(
                KHACH_FILE_NAME, KhachListXML.class);
        if (khachListXML != null) {
            list = khachListXML.getKhach();
        }
        return list;
    }

    public Khach taoKhach(String hoTen, String gioiTinh, String ns) {
        Khach k = new Khach();
        k.setHoTen(hoTen);
        k.setGioiTinh(gioiTinh.toLowerCase());
        k.setNgaySinh(ns);
        return k;
    }

    public void themKhach(Khach kh) {
        this.khachList.add(kh);
    }

    public void editKhach(Khach p) {
        for (Khach ph : this.khachList) {
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                ph.setHoTen(p.getHoTen());
                ph.setGioiTinh(p.getHoTen());
                ph.setNgaySinh(p.getNgaySinh().toString());
                ph.setSlVeDat(p.getSlVeDat());
                ph.setTongTien(p.getTongTien());
            }
        }
    }

    public boolean xoaKhach(Khach p) {
        String khachId = p.getId();
        for (Khach ph : this.khachList) {
            if (ph.getId() == null ? khachId == null : ph.getId().equals(khachId)) {
                this.khachList.remove(ph);
                this.writeListKhachs(khachList);
                return true;
            }
        }
        return false;
    }

    public void sapXepKhach(ArrayList<Khach> list, String tieuChi, boolean beLon) {
        if ("id".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Khach o1, Khach o2) -> {
                int intId1 = Integer.parseInt(o1.getId().substring(1));
                int intId2 = Integer.parseInt(o1.getId().substring(1));
                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
                    return 1;
                }
                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
                    return -1;
                }
                return 0;
            });
        }
        if ("tongtien".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Khach o1, Khach o2) -> {
                int intId1 = o1.getTongTien();
                int intId2 = o2.getTongTien();
                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
                    return 1;
                }
                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
                    return -1;
                }
                return 0;
            });
        }
        if ("slvedat".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Khach o1, Khach o2) -> {
                int intId1 = o1.getSlVeDat();
                int intId2 = o2.getSlVeDat();
                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
                    return 1;
                }
                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
                    return -1;
                }
                return 0;
            });
        }
    }

    public List<Khach> getKhachList() {
        return khachList;
    }

    public void setKhachList(List<Khach> khachList) {
        this.khachList = khachList;
    }

}
