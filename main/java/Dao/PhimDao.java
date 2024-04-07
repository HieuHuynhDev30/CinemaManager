/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Classes.Khach;
import Classes.Phim;
import XML.KhachListXML;
import XML.PhimListXML;
import java.util.ArrayList;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author ACER
 */
public class PhimDao {
    private List<Phim> listPhim = new  ArrayList<>();
    private static final String PHIM_FILE_NAME = "D:\\OOPJAVA\\CinemaManager\\CinemaManager\\src\\main\\java\\XML\\Phim.xml";
    
    public PhimDao(){
        this.listPhim = readListPhims();
        if (listPhim == null) {
            listPhim = new ArrayList<>();
        }
       
    }
    
//    
    public void delete(Phim phim) {
        boolean isFound = false;
        int size = listPhim.size();
        for (int i = 0; i < size; i++) {
            if ( listPhim.get(i).getId().equals(phim.getId())) {
                phim = listPhim.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listPhim.remove(phim);           
            writeListPhims(listPhim);
         //   return true;
        }
     //   return false;
    }
    
    public void edit(Phim phim) {
        int size = listPhim.size();
        for (int i = 0; i < size; i++) {
            if (listPhim.get(i).getId().equals(phim.getId())) {
                listPhim.get(i).setTen(phim.getTen());
                listPhim.get(i).setDoTuoi(phim.getDoTuoi());               
                listPhim.get(i).setTheLoai(phim.getTheLoai());
                listPhim.get(i).setThoiLuong(phim.inThoiLuong());
                listPhim.get(i).setTgKhoiChieu(phim.inTgKhoiChieu());
                writeListPhims(listPhim);
                break;
            }
        }
    }
    
     public void writeListPhims(List<Phim> phims) {
        PhimListXML phimXML = new PhimListXML();
        phimXML.setPhims(phims);
        FileUtils.writeXMLtoFile(PHIM_FILE_NAME, phimXML);
    }

    public List<Phim> readListPhims() {
        List<Phim> list = new ArrayList<>();
        PhimListXML phimListXML = (PhimListXML) FileUtils.readXMLFile(PHIM_FILE_NAME, PhimListXML.class);
        if (phimListXML != null) {
            list = phimListXML.getPhims();
        }
        return list;
    }
    
    
    
    
    
    
    
    public void add(Phim phim) {
        

        listPhim.add(phim);
        writeListPhims(listPhim);
    }
    
    public List<Phim> getListPhim() {
        return listPhim;
    }
    
}
