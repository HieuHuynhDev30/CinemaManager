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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
// Lớp định nghĩa các hành vi làm việc với danh sách suất chiếu
public class SuatChieuFunc {

    private static final String SCH_FILE_NAME = "xml/SuatChieu.xml";
    private static final String PHONG_FILE_NAME = "xml/Phong.xml";
    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private List<SuatChieu> suatChieuList;
    private Map<String[], LocalDateTime[]> schIntervals;
    private PhongFunc phongFunc;

    public SuatChieuFunc() {
        this.suatChieuList = this.readListSuatChieus();
        phongFunc = new PhongFunc();
        this.schIntervals = new HashMap<>();
        for (SuatChieu sch : this.suatChieuList) {
            LocalDateTime tgKetThuc = sch.getThoiGianChieu().plusMinutes(sch.getPhim().getThoiLuong().toMinutes());
            this.schIntervals.put(new String[]{sch.getPhongId(), sch.getId()}, new LocalDateTime[]{sch.getThoiGianChieu(), tgKetThuc});
        }
    }

    // hành vi viết vào file xml
    public void writeListSuatChieus(List<SuatChieu> schs) {
        SuatChieuListXML schXML = new SuatChieuListXML();
        schXML.setSuatChieu(schs);
        FileUtils.writeXMLtoFile(SCH_FILE_NAME, schXML);
    }

    // hành vi đọc từ file xml
    public List<SuatChieu> readListSuatChieus() {
        List<SuatChieu> list = new ArrayList<>();
        SuatChieuListXML schListXML = (SuatChieuListXML) FileUtils.readXMLFile(
                SCH_FILE_NAME, SuatChieuListXML.class);
        if (schListXML != null && schListXML.getSuatChieu() != null) {
            list = schListXML.getSuatChieu();
        }
        return list;
    }

    public SuatChieu taoSuatChieu(Phim phim, String phId, String tgChieu) {
        LocalDateTime TgChieu = LocalDateTime.parse(tgChieu);
        if (checkTrungTg(TgChieu, phId, phim)) {
            return null;
        }
        SuatChieu sch = new SuatChieu();
        sch.setPhim(phim);
        sch.setPhongId(phId);
        List<Phong> phongList = phongFunc.getPhongList();
        for (Phong ph : phongList) {
            if (phId.equals(ph.getId())) {
                sch.setDsGheThuong(ph.getDsGheThuong());
                sch.setDsGheVip(ph.getDsGheVip());
                sch.setDsGheDoi(ph.getDsGheDoi());
                break;
            }
        }
        sch.setThoiGianChieu(TgChieu.format(formatDateTime));
        LocalDateTime tgKetThuc = TgChieu.plusMinutes(sch.getPhim().getThoiLuong().toMinutes());
        this.schIntervals.put(new String[]{sch.getPhongId(), sch.getId()}, new LocalDateTime[]{sch.getThoiGianChieu(), tgKetThuc});
        return sch;
    }

    public boolean checkTrungTg(LocalDateTime TgChieu, String phId, Phim phim) {
        for (Map.Entry<String[], LocalDateTime[]> dt : this.schIntervals.entrySet()) {
            if (dt.getKey()[0].equals(phId)) {
                if ((TgChieu.isEqual(dt.getValue()[0])) || (TgChieu.isAfter(dt.getValue()[0]) && TgChieu.isBefore(dt.getValue()[1].plusMinutes(30)))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void themSuatChieu(SuatChieu sch) {
        this.suatChieuList.add(sch);
        writeListSuatChieus(suatChieuList);
    }

    public void editSuatChieu(SuatChieu p) {
        for (int i = 0; i < this.suatChieuList.size(); i++) {
            SuatChieu ph = this.suatChieuList.get(i);
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                ph.setPhim(p.getPhim());

                ph.setPhongId(p.getPhongId());
                ph.setThoiGianChieu((CharSequence) p.inThoiGianChieu());

                ph.setDsGheThuong(p.getDsGheThuong());
                ph.setDsGheVip(p.getDsGheVip());
                ph.setDsGheDoi(p.getDsGheDoi());

                addSchIntervals(ph);

                ph.setDt(p.getDt());
            }
        }
        this.writeListSuatChieus(suatChieuList);
    }

    public void removeSchIntervals(SuatChieu sch) {
        Map<String[], LocalDateTime[]> newMap = new HashMap<>();
        newMap = this.schIntervals;
        String[] keyToRemove = new String[2];
        LocalDateTime[] valueToRemove = new LocalDateTime[2];
        for (Map.Entry<String[], LocalDateTime[]> dt : this.schIntervals.entrySet()) {
            if (dt.getKey()[0].equals(sch.getPhongId()) && dt.getKey()[1].equals(sch.getId())) {
                keyToRemove = dt.getKey();
                valueToRemove = dt.getValue();
                break;
            }
        }
        newMap.remove(keyToRemove, valueToRemove);
        this.setSchIntervals(newMap);
    }

    public void addSchIntervals(SuatChieu sch) {
        LocalDateTime tgKetThuc = sch.getThoiGianChieu().plusMinutes(sch.getPhim().getThoiLuong().toMinutes());
        this.getSchIntervals().put(new String[]{sch.getPhongId(), sch.getId()}, new LocalDateTime[]{sch.getThoiGianChieu(), tgKetThuc});
    }

    public void xoaSuatChieu(SuatChieu p) {
        String pId = p.getId();
        List<SuatChieu> list = new ArrayList<>();
        list = this.getSuatChieuList();
        SuatChieu sch = new SuatChieu();
        for (SuatChieu ph : list) {
            if (ph.getId() == null ? pId == null : ph.getId().equals(pId)) {
                sch = ph;
                break;
            }
        }
        removeSchIntervals(sch);
        list.remove(sch);
        this.setSuatChieuList(list);
        this.writeListSuatChieus(suatChieuList);
    }

    public Map<String[], LocalDateTime[]> getSchIntervals() {
        return schIntervals;
    }

    public void setSchIntervals(Map<String[], LocalDateTime[]> schIntervals) {
        this.schIntervals = schIntervals;
    }

    public class SortIdSch implements Comparator<SuatChieu> {

        private boolean beLon;

        public SortIdSch(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(SuatChieu o1, SuatChieu o2) {
            if (beLon == true) {
                return o1.getId().compareTo(o2.getId());
            } else {
                System.out.println(beLon);
                return o2.getId().compareTo(o1.getId());
            }
        }

    }

    public class SortDtSch implements Comparator<SuatChieu> {

        private boolean beLon;

        public SortDtSch(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(SuatChieu o1, SuatChieu o2) {
            if (beLon) {
                return (int) o1.getDt() - (int) o2.getDt();
            } else {
                return (int) o2.getDt() - (int) o1.getDt();
            }
        }
    }

    public class SortTgSch implements Comparator<SuatChieu> {

        private boolean beLon;

        public SortTgSch(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(SuatChieu o1, SuatChieu o2) {
            int num;
            if (o1.getThoiGianChieu().isBefore(o2.getThoiGianChieu())) {
                num = -1;
            } else if (o2.getThoiGianChieu().isBefore(o1.getThoiGianChieu())) {
                num = 1;
            } else {
                return 0;
            }
            if (beLon) {
                return num;
            } else {
                return -num;
            }
        }
    }

    public class SortSchLd implements Comparator<SuatChieu> {

        private boolean beLon;

        public SortSchLd(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(SuatChieu o1, SuatChieu o2) {
            double rate1 = (o1.inTongDat() * 100) / (o1.inTongDat() + o1.inTongTrong());
            double rate2 = (o2.inTongDat() * 100) / (o1.inTongDat() + o1.inTongTrong());
            double diff = rate1 - rate2;
            if (beLon) {
//                if (o1.isFull() == true) {
//                    return o2.isFull() == false ? 1 : 0;
//                } else {
//                    return o2.isFull() == true ? -1 : 0;
//                }
                return (int) diff;
            } else {
//                if (o1.isFull() == false) {
//                    return o2.isFull() == true ? 1 : 0;
//                } else {
//                    return o2.isFull() == false ? -1 : 0;
//                }
                return (int) -diff;
            }
        }
    }

    public ArrayList<SuatChieu> sapXepSuatChieu(ArrayList<SuatChieu> list, String tieuChi, boolean beLon) {
        if ("id".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new SortIdSch(beLon));
        }
        if ("doanhthu".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new SortDtSch(beLon));
        }
        if ("thời gian chiếu".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new SortTgSch(beLon));
        }
        if ("lắp đầy".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new SortSchLd(beLon));
        }
        this.writeListSuatChieus(list);
        return list;
    }

    public void checkIsFull() {
        if (this.getSuatChieuList() != null) {
            for (SuatChieu sch : this.getSuatChieuList()) {
                if (sch.getDsGheTrong().isEmpty()) {
                    sch.setFull(true);
                }
            }
        }
        this.writeListSuatChieus(this.suatChieuList);
    }

    public void checkChieuXong() {
        for (SuatChieu sch : this.suatChieuList) {
            if (sch.isChieuXong()) {
                this.xoaSuatChieu(sch);
            }
        }
        this.writeListSuatChieus(this.getSuatChieuList());
    }

    public List<SuatChieu> getSuatChieuList() {
        return this.readListSuatChieus();
    }

    public void setSuatChieuList(List<SuatChieu> suatChieuList) {
        this.suatChieuList = suatChieuList;
    }

}
