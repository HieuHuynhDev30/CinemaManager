/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Classes.Khach;
import XML.KhachListXML;
import java.util.ArrayList;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author ACER
 */
public final class KhachDao {
    private List<Khach> listKhach = new  ArrayList<>();
    private static final String KHACH_FILE_NAME = "D:\\OOPJAVA\\CinemaManager\\CinemaManager\\src\\main\\java\\XML\\Khach.xml";
    
    public KhachDao(){
        this.listKhach = readListKhachs();
        if (listKhach == null) {
            listKhach = new ArrayList<>();
        }
       
    }
    
//    public void writeListKhach(List<Khach> khach) {
//        KhachListXML khachXML = new KhachListXML();
//        khachXML.setKhach(khach);
//        FileUtils.writeXMLtoFile(  KHACH_FILE_NAME, khachXML);
//    }

    /**
     * Đọc các đối tượng student từ file student.xml
     * 
     * @param khachs
     */
//    public List<Khach> readListKhach() {
//        List<Khach> list = new ArrayList<Khach>();
//        KhachListXML khachXML = (KhachListXML) FileUtils.readXMLFile(
//                KHACH_FILE_NAME, KhachListXML.class);
//        if (khachXML != null) {
//            list = khachXML.getKhach();
//        }
//        return list;
//    }
    
    /**
     * xóa student từ listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void delete(Khach khach) {
        boolean isFound = false;
        int size = listKhach.size();
        for (int i = 0; i < size; i++) {
            if ( listKhach.get(i).getId().equals(khach.getId())) {
                khach = listKhach.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listKhach.remove(khach);           
            writeListKhachs(listKhach);
         //   return true;
        }
     //   return false;
    }
    
    public void edit(Khach khach) {
        int size = listKhach.size();
        for (int i = 0; i < size; i++) {
            if (listKhach.get(i).getId().equals(khach.getId())) {
                listKhach.get(i).setHoTen(khach.getHoTen());
                listKhach.get(i).setGioiTinh(khach.getGioiTinh());
                //listKhach.get(i).setNgaySinh(khach.getNgaySinh().toString());
                listKhach.get(i).setSLV(khach.getSlVeDat());
                writeListKhachs(listKhach);
                break;
            }
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
    
    
    
    
    
    
    
    public void add(Khach khach) {
        

        listKhach.add(khach);
        writeListKhachs(listKhach);
    }
    
    public List<Khach> getListKhach() {
        return listKhach;
    }
    
    
    
}
