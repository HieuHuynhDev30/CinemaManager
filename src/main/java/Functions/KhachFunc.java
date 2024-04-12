/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Khach;
import Classes.Phim;
import Classes.SuatChieu;
import Classes.Ve;
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
        validateListXML();
    }
     public final void validateListXML() {
        if (khachList == null) {
            khachList = new ArrayList<>();
        }
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
        for (int i = 0; i < this.khachList.size(); i++) {
            Khach ph = this.khachList.get(i);
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                System.out.print("updated");
                ph.setHoTen(p.getHoTen());
                ph.setGioiTinh(p.getGioiTinh());
                ph.setNgaySinh((CharSequence) p.inNgaySinh());
                ph.setSlVeDat(p.getSlVeDat());
                ph.setTongTien(p.getTongTien());
            }
        }
        this.writeListKhachs(khachList);
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
    
    // Khach method cua An
    
    public void edit(Khach khach) {
        int size = khachList.size();
        for (int i = 0; i < size; i++) {
            if (khachList.get(i).getId().equals(khach.getId())) {
                khachList.get(i).setHoTen(khach.getHoTen());
                khachList.get(i).setGioiTinh(khach.getGioiTinh());
                //listKhach.get(i).setNgaySinh(khach.getNgaySinh().toString());
                khachList.get(i).setSlVeDat(khach.getSlVeDat());
                writeListKhachs(khachList);
                break;
            }
        }
    }
    
    //
    public List<Khach> searchTen(String s){
        List<Khach> list = new ArrayList<>();
        int size = khachList.size();
        for (int i = 0; i < size; i++) {
            if ( khachList.get(i).getHoTen().contains(s)) {
                //phim = listPhim.get(i);
                list.add(khachList.get(i));
                
                
            }
        }
        return list;
    }
    
    public List<Khach> searchSLv(String s){
        List<Khach> list = new ArrayList<>();
        int size = khachList.size();
        int k = Integer.parseInt(s);
        for (int i = 0; i < size; i++) {
            if ( khachList.get(i).getSlVeDat() >= k) {
               // phim = listPhim.get(i);
                list.add(khachList.get(i));
                
                
            }
        }
        return list;
    }
    
    public String searchID(String s){
        Khach khach = new Khach();
        int size = khachList.size();
        
        for (int i = 0; i < size; i++) {
            if ( khachList.get(i).getId().equals(s)) {
               khach  = khachList.get(i);
                
                
                
            }
        }
        return khach.getHoTen();
    }
    
     public Khach searchID(Khach khach){
       
        int size = khachList.size();
        
        for (int i = 0; i < size; i++) {
            if ( khachList.get(i).getId().equals(khach.getId())) {
               khach  = khachList.get(i);
                
                
                
            }
        }
        return khach;
    }
    
     public void muaVe(Khach khach,Ve ve) {
//        khach.dsVe.add(ve);
//        khach.slVeDat++;
//        khach.tongTien += ve.getGhe().getGia();
          khach = searchID(khach);
          xoaKhach(searchID(khach));
          khach.muaVe(ve);
          khachList.add(khach);
          writeListKhachs(khachList);
    }
    
    //ket thuc

}
