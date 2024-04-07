/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import XML.PhongListXML;
import XML.SuatChieuListXML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class SuatChieuFunc {

    private static final String SCH_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\SuatChieu.xml";
    private static final String PHONG_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Phong.xml";
    private List<SuatChieu> suatChieuList;

    public SuatChieuFunc() {
        this.suatChieuList = this.readListSuatChieus();
    }

    public void writeListSuatChieus(List<SuatChieu> schs) {
        SuatChieuListXML schXML = new SuatChieuListXML();
        schXML.setSuatChieu(schs);
        FileUtils.writeXMLtoFile(SCH_FILE_NAME, schXML);
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

    public List<SuatChieu> readListSuatChieus() {
        List<SuatChieu> list = new ArrayList<>();
        SuatChieuListXML schListXML = (SuatChieuListXML) FileUtils.readXMLFile(
                SCH_FILE_NAME, SuatChieuListXML.class);
        if (schListXML != null) {
            list = schListXML.getSuatChieu();
            List<Phong> phongList = readListPhongs();
            for (SuatChieu sch : list) {
                for (Phong ph : phongList) {
                    if (sch.getPhongId() == null ? ph.getId() == null : sch.getPhongId().equals(ph.getId())) {
                        sch.setPhong(ph);
                    }
                }
            }
        }
        return list;
    }

    public SuatChieu taoSuatChieu(Phim phim, Phong ph, String tgChieu) {
        SuatChieu sch = new SuatChieu();
        sch.setPhim(phim);
        sch.setPhong(ph);
        sch.setThoiGianChieu(tgChieu);
        return sch;
    }

    public void themSuatChieu(SuatChieu sch) {
        this.suatChieuList.add(sch);
        writeListSuatChieus(suatChieuList);
    }

    public void editSuatChieu(SuatChieu p) {
        for (SuatChieu ph : this.suatChieuList) {
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                ph.setPhim(p.getPhim());
                ph.setPhong(p.getPhong());
                ph.setPhongId();
                ph.setThoiGianChieu(p.getThoiGianChieu().toString());
            }
        }
    }

    public boolean xoaSuatChieu(SuatChieu p) {
        String pId = p.getId();
        for (SuatChieu ph : this.suatChieuList) {
            if (ph.getId() == null ? pId == null : ph.getId().equals(pId)) {
                this.suatChieuList.remove(ph);
                this.writeListSuatChieus(suatChieuList);
                return true;
            }
        }
        return false;
    }

    public void sapXepSuatChieu(ArrayList<SuatChieu> list, String tieuChi, boolean beLon) {
        if ("id".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (SuatChieu o1, SuatChieu o2) -> {
                int intId1 = Integer.parseInt(o1.getId().substring(3));
                int intId2 = Integer.parseInt(o1.getId().substring(3));
                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
                    return 1;
                }
                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
                    return -1;
                }
                return 0;
            });
        }
        if ("soluongve".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (SuatChieu o1, SuatChieu o2) -> {
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

    public List<SuatChieu> getSuatChieuList() {
        return suatChieuList;
    }

    public void setSuatChieuList(List<SuatChieu> suatChieuList) {
        this.suatChieuList = suatChieuList;
    }

}
