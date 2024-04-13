/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Ghe;
import Classes.GheDoi;
import Classes.GheThuong;
import Classes.GheVip;
import Classes.Khach;
import Classes.SuatChieu;
import Classes.Ve;
import XML.VeListXML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class VeFunc {

    private static final String VE_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Ve.xml";
    private List<Ve> veList;
    KhachFunc khachFunc;

    public VeFunc() {
        this.veList = this.readListVes();
        this.khachFunc = new KhachFunc();
        validateListXML();
    }
     public final void validateListXML() {
        if (veList == null) {
            veList = new ArrayList<>();
        }
    }

    public Ve taoVe(Ghe ghe, SuatChieu suat) {
        Ve ve = new Ve();
        if (ghe instanceof GheThuong gheThuong) {
            ve.setGhe(gheThuong);
        }
        if (ghe instanceof GheVip gheVip) {
            ve.setGhe(gheVip);
        }
        if (ghe instanceof GheDoi gheDoi) {
            ve.setGhe(gheDoi);
        }
        ve.setSuat(suat);
        return ve;
    }

    public void themVe(Ve p) {
        this.veList.add(p);
        writeListVes(veList);
    }

    public void editVe(Ve p) {
        for (Ve ph : this.veList) {
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                ph.setGhe(p.getGhe());
                ph.setSuat(p.getSuat());
            }
        }
    }

    public boolean xoaVe(Ve ve) {
        String veId = ve.getId();
        for (Ve V : this.veList) {
            if (V.getId() == null ? veId == null : V.getId().equals(veId)) {
                this.veList.remove(V);
                return true;
            }
        }
        return false;
    }
    
    public void xoaVe(String s) {
        String veId = s;
        for (Ve V : this.veList) {
            if (V.getId() == null ? veId == null : V.getId().equals(veId)) {
                this.veList.remove(V);
                this.writeListVes(veList);
            }
        }    
    }

    public void writeListVes(List<Ve> ves) {
        VeListXML veXML = new VeListXML();
        veXML.setVe(ves);
        FileUtils.writeXMLtoFile(VE_FILE_NAME, veXML);
    }

    public List<Ve> readListVes() {
        List<Ve> list = new ArrayList<>();
        VeListXML veListXML = (VeListXML) FileUtils.readXMLFile(
                VE_FILE_NAME, VeListXML.class);
        if (veListXML != null) {
            System.out.println("error");
            list = veListXML.getVe();
        }
        return list;
    }
    
    public List<Ve> SearchTenPhim(String s){
        List<Ve> list = new ArrayList<>();
        for (Ve V : this.veList) {
            
            if(V.getSuat().getPhim().getTen().contains(s)){
                    list.add(V);
                
            }
           
        }
        return list;
    }
    public List<Ve> SearchPhong(String s){
        List<Ve> list = new ArrayList<>();
        for (Ve V : this.veList) {
            if(V.getPhong().contains(s)){
                    list.add(V);
                
            }
           
        }
        return list;
    }
    public List<Ve> SearchIdVe(String s){
        List<Ve> list = new ArrayList<>();
        for (Ve v: this.veList) {
            if (v.getId().contains(s)) {
                list.add(v);
            }
        }
        return list;
    }
    
    
     public List<Ve> SearchKhachVe(String s){
        List<Ve> list = new ArrayList<>();
        List <Khach> list1 = khachFunc.searchTen(s);
        for (Ve V : this.veList) {
            for (Khach kh : list1){
                if(V.getGhe().getKhachId().equals(kh.getId())){
                    list.add(V);
                }
            }
            
        }
        return list;
    }
     
     public List<Ve> SearchVitriVe(String s){
        List<Ve> list = new ArrayList<>();
        for (Ve v : this.veList) {
           if (v.getGhe().getViTri().contains(s)) {
               list.add(v);
           }
        }
        return list;
    }
     
      public List<Ve> SearchLoaiGhe(String s){
        List<Ve> list = new ArrayList<>();
        for (Ve V : this.veList) {
            if (V.getGhe().getLoai().contains(s) ) {               
                list.add(V);
            }
        }
        return list;
    }
      
      public List<Ve> SearchThoiGian(String s){
        List<Ve> list = new ArrayList<>();
        for (Ve v : this.veList) {
            if (v.getSuat().inThoiGianChieu().contains(s)) {               
                list.add(v);
            }
        }
        return list;
    }
      
      
      
       //sort
    public void sortVeByName(List<Khach> list) {
        Collections.sort(veList, new Comparator<Ve>() {
            public int compare(Ve ve1, Ve ve2) {
                
                return ve1.getKhachName(khachFunc.getKhachList()).compareTo(ve2.getKhachName(khachFunc.getKhachList()));
            }
        });
    }
    public void sortVeByPhim(List<Khach> list) {
        Collections.sort(veList, new Comparator<Ve>() {
            public int compare(Ve ve1, Ve ve2) {
                
                return ve1.getSuat().inPhim().compareTo(ve2.getSuat().inPhim());
            }
        });
    }

    public List<Ve> getVeList() {
        return veList;
    }

    public void setVeList(List<Ve> veList) {
        this.veList = veList;
    }

}
