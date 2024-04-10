/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Ghe;
import Classes.GheDoi;
import Classes.GheThuong;
import Classes.GheVip;
import Classes.Phong;
import Classes.SuatChieu;
import Classes.Ve;
import XML.VeListXML;
import java.util.ArrayList;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class VeFunc {

    private static final String VE_FILE_NAME = "D:\\Downloads\\CinemaManager-master2\\CinemaManager-master2\\src\\main\\java\\XML\\Ve.xml";
    private List<Ve> veList;

    public VeFunc() {
        this.veList = this.readListVes();
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

    public void xoaVe(Ve ve) {
        String veId = ve.getId();
        for (Ve V : this.veList) {
            if (V.getId() == null ? veId == null : V.getId().equals(veId)) {
                this.veList.remove(V);
                this.writeListVes(veList);
            }
        }
        this.writeListVes(veList);
         
          
          
    }
    
    public Ve SearchVe(Ve ve){
        String vePhongID = ve.getSuat().getPhongId();
        String veTenPhim = ve.getSuat().inPhim();
        for (Ve V : this.veList) {
            if (V.getSuat().getPhongId() == null ? vePhongID == null : V.getSuat().getPhongId().equals(vePhongID) && V.getSuat().inPhim().equals(veTenPhim) ) {
                
                return V;
            }
        }
        return null;
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

    public List<Ve> getVeList() {
        return veList;
    }

    public void setVeList(List<Ve> veList) {
        this.veList = veList;
    }
    
    

}
