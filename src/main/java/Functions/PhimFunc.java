/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import XML.PhimListXML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class PhimFunc {

    private static final String PHIM_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Phim.xml";
    private List<Phim> phimList;
    private SuatChieuFunc schFunc;

    public PhimFunc() {
        this.phimList = readListPhims();
    }

    public void writeListPhims(List<Phim> phims) {
        PhimListXML phimXML = new PhimListXML();
        phimXML.setPhims(phims);
        FileUtils.writeXMLtoFile(PHIM_FILE_NAME, phimXML);
    }

    public List<Phim> readListPhims() {
        List<Phim> list = new ArrayList<>();
        PhimListXML phimListXML = (PhimListXML) FileUtils.readXMLFile(
                PHIM_FILE_NAME, PhimListXML.class);
        if (phimListXML != null) {
            list = phimListXML.getPhims();
        }
        return list;
    }

    public Phim taoPhim(String ten, Long thoiLuong, String theLoai, int doTuoi, String tgKhoiChieu) {
        Phim ph = new Phim(ten, thoiLuong, theLoai, doTuoi);
        ph.setTgKhoiChieu(tgKhoiChieu);
        return ph;
    }

    public void themPhim(Phim ph) {
        this.phimList.add(ph);
    }

    public void editPhim(Phim p) {
        for (Phim ph : this.phimList) {
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                ph.setDoTuoi(p.getDoTuoi());
                ph.setTen(p.getTen());
                ph.setTgKhoiChieu(p.getTgKhoiChieu().toString());
                ph.setTheLoai(p.getTheLoai());
                ph.setThoiLuong(p.getThoiLuong().toMinutes());
            }
        }
    }

    public boolean xoaPhim(Phim p) {
        String phimId = p.getId();
        for (Phim ph : this.phimList) {
            if (ph.getId() == null ? phimId == null : ph.getId().equals(phimId)) {
                this.phimList.remove(ph);
                List<SuatChieu> schList = schFunc.readListSuatChieus();
                for (SuatChieu sch : schList) {
                    if (sch.getPhim().getId() == null ? ph.getId() == null : sch.getPhim().getId().equals(ph.getId())) {
                        schList.remove(sch);
                        schFunc.writeListSuatChieus(schList);
                    }
                }
                this.writeListPhims(phimList);
                return true;
            }
        }
        return false;
    }

    public void sapXepPhim(ArrayList<Phim> list, String tieuChi, boolean beLon) {
        if ("id".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Phim o1, Phim o2) -> {
                int intId1 = Integer.parseInt(o1.getId().substring(2));
                int intId2 = Integer.parseInt(o1.getId().substring(2));
                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
                    return 1;
                }
                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
                    return -1;
                }
                return 0;
            });
        }
        if ("thoiluong".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Phim o1, Phim o2) -> {
                long intId1 = o1.getThoiLuong().toMinutes();
                long intId2 = o2.getThoiLuong().toMinutes();
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

    public List<Phim> getPhimList() {
        return phimList;
    }

    public void setPhimList(List<Phim> phimList) {
        this.phimList = phimList;
    }

}
