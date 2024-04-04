/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Khach;
import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import Classes.Ve;
import XML.KhachListXML;
import XML.PhimListXML;
import XML.PhongListXML;
import XML.SuatChieuListXML;
import XML.VeListXML;
import java.util.ArrayList;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class Func {

    private static final String PHIM_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Phim.xml";
    private static final String KHACH_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Khach.xml";
    private static final String VE_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Ve.xml";
    private static final String PHONG_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Phong.xml";
    private static final String SCH_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\SuatChieu.xml";
    
    
    public Func() {
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
            list = veListXML.getVe();
        }
        return list;
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
            for (Phong phong : list) {
                phong.setDsGhe();
            }
        }
        return list;
    }
    
    public void writeListSuatChieus(List<SuatChieu> schs) {
        SuatChieuListXML schXML = new SuatChieuListXML();
        schXML.setSuatChieu(schs);
        FileUtils.writeXMLtoFile(SCH_FILE_NAME, schXML);
    }

    public List<SuatChieu> readListSuatChieus() {
        List<SuatChieu> list = new ArrayList<>();
        SuatChieuListXML schListXML = (SuatChieuListXML) FileUtils.readXMLFile(
                SCH_FILE_NAME, SuatChieuListXML.class);
        if (schListXML != null) {
            list = schListXML.getSuatChieu();
            List<Phong> phongList = readListPhongs();
            for (SuatChieu sch : list) {
                for (Phong ph: phongList) {
                    if (sch.getPhongId() == null ? ph.getId() == null : sch.getPhongId().equals(ph.getId())) {
                        sch.setPhong(ph);
                    }
                }
            }
        }
        return list;
    }
}
