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
import java.util.Comparator;
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
        this.writeListPhims(phimList);
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

    public void xoaPhim(Phim p) {
        String phimId = p.getId();
        for (Phim ph : this.phimList) {
            if (ph.getId() == null ? phimId == null : ph.getId().equals(phimId)) {
                this.phimList.remove(ph);
//                List<SuatChieu> schList = schFunc.readListSuatChieus();
//                for (SuatChieu sch : schList) {
//                    if (sch.getPhim().getId() == null ? ph.getId() == null : sch.getPhim().getId().equals(ph.getId())) {
//                        schList.remove(sch);
//                        schFunc.writeListSuatChieus(schList);
//                    }
//                }
            }
        }
        this.writeListPhims(phimList);
    }

    public class SortDtPhim implements Comparator<Phim> {

        private boolean beLon;

        public SortDtPhim(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phim o1, Phim o2) {
            if (beLon) {
                return o1.getDt() - o2.getDt();
            } else {
                return o2.getDt() - o1.getDt();
            }
        }

    }

    public ArrayList<Phim> sapXepPhim(ArrayList<Phim> list, String tieuChi, boolean beLon) {
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
        if ("doanhthu".equals(tieuChi.toLowerCase())) {
            System.out.println("sap xep  dt");
            Collections.sort(list, new SortDtPhim(beLon));
        }
        return list;
    }

    public List<Phim> getPhimList() {
        return phimList;
    }

    public void setPhimList(List<Phim> phimList) {
        this.phimList = phimList;
    }

    // An method
    public List<Phim> searchTen(String s) {
        List<Phim> list = new ArrayList<>();
        int size = phimList.size();
        for (int i = 0; i < size; i++) {
            if (phimList.get(i).getTen().contains(s)) {
                //phim = listPhim.get(i);
                list.add(phimList.get(i));

            }
        }
        return list;
    }

    public List<Phim> searchTheLoai(String s) {
        List<Phim> list = new ArrayList<>();
        int size = phimList.size();
        for (int i = 0; i < size; i++) {
            if (phimList.get(i).getTheLoai().contains(s)) {
                // phim = listPhim.get(i);
                list.add(phimList.get(i));

            }
        }
        return list;
    }

    public List<Phim> searchDoTuoi(String s) {
        List<Phim> list = new ArrayList<>();
        int size = phimList.size();
        int k = Integer.parseInt(s);
        for (int i = 0; i < size; i++) {
            if (phimList.get(i).getDoTuoi() == k) {
                // phim = listPhim.get(i);
                list.add(phimList.get(i));
            }
        }
        return list;
    }

    public void edit(Phim phim) {
        int size = phimList.size();
        for (int i = 0; i < size; i++) {
            if (phimList.get(i).getId().equals(phim.getId())) {
                phimList.get(i).setTen(phim.getTen());
                phimList.get(i).setDoTuoi(phim.getDoTuoi());
                phimList.get(i).setTheLoai(phim.getTheLoai());
                phimList.get(i).setThoiLuong(phim.inThoiLuong());
                phimList.get(i).setTgKhoiChieu(phim.inTgKhoiChieu());
                writeListPhims(phimList);
                break;
            }
        }
    }

    // ket thuc
}
