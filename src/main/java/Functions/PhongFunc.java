/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.GheDoi;
import Classes.GheThuong;
import Classes.GheVip;
import Classes.Phong;
import Classes.SuatChieu;
import XML.PhongListXML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class PhongFunc {

    private static final String PHONG_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Phong.xml";
    private List<Phong> phongList;
    private SuatChieuFunc suatChieuFunc;

    public PhongFunc() {
        this.phongList = readListPhongs();
    }

    public void writeListPhongs(List<Phong> phongs) {
        PhongListXML phongXML = new PhongListXML();
        phongXML.setPhong(phongs);
        FileUtils.writeXMLtoFile(PHONG_FILE_NAME, phongXML);
    }

    public List<Phong> readListPhongs() {
        List<Phong> list = new ArrayList<>();
        PhongListXML phongListXML = (PhongListXML) FileUtils.readXMLFile(
                PHONG_FILE_NAME, PhongListXML.class);
        if (phongListXML != null) {
            list = phongListXML.getPhong();
        }
        return list;
    }

    public Phong taoPhong(int slGT, int slGVip, int slGDoi) {
        Phong p = new Phong();
        for (int i = 0; i < slGT; i++) {
            GheThuong gt = new GheThuong();
            p.themGhe(gt);
        }
        for (int i = 0; i <  slGVip; i++) {
            GheVip gv = new GheVip();
            p.themGhe(gv);
        }
        for (int i = 0; i < slGDoi; i++) {
            GheDoi gd = new GheDoi();
            p.themGhe(gd);
        }
        return p;
    }

    public void themPhong(Phong p) {
        this.phongList.add(p);
        writeListPhongs(phongList);
    }

    public void editPhong(Phong p) {
        for (Phong ph : this.phongList) {
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                ph.setDsGheThuong(p.getDsGheThuong());
                ph.setDsGheVip(p.getDsGheVip());
                ph.setDsGheDoi(p.getDsGheDoi());
                ph.setIsFull(p.getIsFull());
                ph.setSuatChieu(p.getSuatChieu());
                ph.setIsPlaying(p.getIsPlaying());
            }
        }
    }

    public boolean xoaPhong(Phong p) {
        String pId = p.getId();
        for (Phong ph : this.phongList) {
            if (ph.getId() == null ? pId == null : ph.getId().equals(pId)) {
                this.phongList.remove(ph);
                if (p.getSuatChieu() != null) {
                    boolean xoaSch = suatChieuFunc.xoaSuatChieu(p.getSuatChieu());
                }
                this.writeListPhongs(phongList);
                return true;
            }
        }
        return false; 
    }

    public void sapXepPhong(ArrayList<Phong> list, String tieuChi, boolean beLon) {
        if ("id".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Phong o1, Phong o2) -> {
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
        if ("succhua".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Phong o1, Phong o2) -> {
                int intId1 = o1.getSucChua();
                int intId2 = o2.getSucChua();
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

    public List<Phong> getPhongList() {
        return phongList;
    }

    public void setPhongList(List<Phong> phongList) {
        this.phongList = phongList;
    }
}
