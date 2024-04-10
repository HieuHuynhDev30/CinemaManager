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
import java.util.Comparator;
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
        for (int i = 0; i < slGVip; i++) {
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
                ph.setSlThuong(p.getSlThuong());
                ph.setSlVip(p.getSlVip());
                ph.setSlDoi(p.getSlDoi());
                ph.setDsGheThuong(p.getDsGheThuong());
                ph.setDsGheVip(p.getDsGheVip());
                ph.setDsGheDoi(p.getDsGheDoi());
                ph.setIsFull(p.getIsFull());
                ph.setSuatChieu(p.getSuatChieu());
                ph.setIsPlaying(p.getIsPlaying());
            }
        }
        this.writeListPhongs(phongList);
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

    public class SortDtPhong implements Comparator<Phong> {

        private boolean beLon;

        public SortDtPhong(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                return o1.getDt() - o2.getDt();
            } else {
                return o2.getDt() - o1.getDt();
            }
        }
    }

    public class SortPhongSc implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongSc(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                return o1.getSucChua() - o2.getSucChua();
            } else {
                return o2.getSucChua() - o1.getSucChua();
            }
        }
    }

    public class SortPhongTsg implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongTsg(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                return o1.getTongGhe() - o2.getTongGhe();
            } else {
                return o2.getTongGhe() - o1.getTongGhe();
            }
        }
    }

    public class SortPhongLd implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongLd(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                if (o1.getIsFull() == true) {
                    return o2.getIsFull() == false ? 1 : 0;
                } else {
                    return o2.getIsFull() == true ? -1 : 0;
                }
            } else {
                if (o1.getIsFull() == false) {
                    return o2.getIsFull() == true ? 1 : 0;
                } else {
                    return o2.getIsFull() == false ? -1 : 0;
                }
            }
        }
    }

    public class SortPhongPhim implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongPhim(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (!beLon) {
                return o1.getSuatChieu().getPhim().getTen().compareTo(o2.getSuatChieu().getPhim().getTen());
            } else {
                return o2.getSuatChieu().getPhim().getTen().compareTo(o1.getSuatChieu().getPhim().getTen());
            }
        }
    }

    public class SortPhongTt implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongTt(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                if (o1.getIsPlaying() == true) {
                    return o2.getIsPlaying() == false ? 1 : 0;
                } else {
                    return o2.getIsPlaying() == true ? -1 : 0;
                }
            } else {
                if (o1.getIsPlaying() == false) {
                    return o2.getIsPlaying() == true ? 1 : 0;
                } else {
                    return o2.getIsPlaying() == false ? -1 : 0;
                }
            }
        }
    }

    public ArrayList<Phong> sapXepPhong(ArrayList<Phong> list, String tieuChi, boolean beLon) {
        if ("tổng số ghế".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongTsg(beLon));
        }
        if ("sức chứa".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongSc(beLon));
        }
        if ("doanhthu".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortDtPhong(beLon));
        }
        if ("lấp đầy".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongLd(beLon));
        }
        if ("phim chiếu".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongPhim(beLon));
        }
        if ("tình trạng".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongTt(beLon));
        }
        return list;
    }

    public List<Phong> getPhongList() {
        return phongList;
    }

    public void setPhongList(List<Phong> phongList) {
        this.phongList = phongList;
    }
}
