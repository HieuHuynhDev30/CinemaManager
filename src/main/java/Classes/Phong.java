/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.*;
import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "phong")
@XmlAccessorType(XmlAccessType.FIELD)
public class Phong {

    private static int currId;
    private int slVip, slThuong, slDoi;
    @XmlAttribute
    private String id;
    private Map<String, GheThuong> dsGheThuong = new HashMap<>();
    private Map<String, GheVip> dsGheVip = new HashMap<>();
    private Map<String, GheDoi> dsGheDoi = new HashMap<>();
//    public List<Ghe> GheList = new ArrayList<>();
    private boolean isFull;
    private SuatChieu suatChieu;
    private boolean isPlaying;
    private int columns;
    private char rows;
    private int dt;

    public Phong() {
        currId++;
        this.id = "P" + currId;
        rows = 'A';
        columns = 1;
        this.isFull = false;
    }
    
    public void setId(String id) {
        this.id = id;
    } 
    

    public String getId() {
        return id;
    }

    public int getSlVip() {
        return slVip;
    }

    public void setSlVip(int slVip) {
        this.slVip = slVip;
    }

    public int getSlThuong() {
        return slThuong;
    }

    public void setSlThuong(int slThuong) {
        this.slThuong = slThuong;
    }

    public int getSlDoi() {
        return slDoi;
    }

    public void setSlDoi(int slDoi) {
        this.slDoi = slDoi;
    }

    public Map<String, GheThuong> getDsGheThuong() {
        return dsGheThuong;
    }

    public void setDsGheThuong(Map<String, GheThuong> dsGheThuong) {
        this.dsGheThuong = dsGheThuong;
    }

    public Map<String, GheVip> getDsGheVip() {
        return dsGheVip;
    }

    public void setDsGheVip(Map<String, GheVip> dsGheVip) {
        this.dsGheVip = dsGheVip;
    }

    public Map<String, GheDoi> getDsGheDoi() {
        return dsGheDoi;
    }

    public void setDsGheDoi(Map<String, GheDoi> dsGheDoi) {
        this.dsGheDoi = dsGheDoi;
    }

//    public Map<String, Ghe> getDsGhe() {
//        return dsGhe;
//    }
//
//    public void setDsGhe() {
//        for (Ghe ghe : this.GheList) {
//            this.dsGhe.put(ghe.viTri, ghe);
//        }
//    }
//
    public void themGhe(Ghe ghe) {
        ghe.setViTri(String.format("%s%s%d", this.getId(), rows, columns));
//        this.GheList.add(ghe);
        if (ghe instanceof GheThuong gheThuong) {
            dsGheThuong.put(ghe.getViTri(), gheThuong);
        }
        if (ghe instanceof GheVip gheVip) {
            dsGheVip.put(ghe.getViTri(), gheVip);
        }
        if (ghe instanceof GheDoi gheDoi) {
            dsGheDoi.put(ghe.getViTri(), gheDoi);
        }
        if (columns < 10) {
            columns++;
        } else {
            columns = 1;
            rows++;
        }
    }

    public boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public SuatChieu getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(SuatChieu xuatChieu) {
        this.suatChieu = xuatChieu;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public int inTongTrong() {
        int tong = 0;
        for (Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                tong++;
            }
        }
        for (Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                tong++;
            }
        }
        for (Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                tong++;
            }
        }
        return tong;
    }

    public int inTongDat() {
        return dsGheThuong.size() + dsGheVip.size() + dsGheDoi.size() - inTongTrong();
    }

    public String inThuongDat() {
        String dsThuongDat = "";
        for (GheThuong ghe : dsGheThuong.values()) {
            if ("thuong".equals(ghe.getLoai().toLowerCase()) && ghe.getKhachId() != null) {
                dsThuongDat += ghe.getViTri() + ", ";
            }
        }
        return dsThuongDat;
    }

    public String inVipDat() {
        String dsVipDat = "";
        for (GheVip ghe : dsGheVip.values()) {
            if ("vip".equals(ghe.getLoai().toLowerCase()) && ghe.getKhachId() != null) {
                dsVipDat += ghe.getViTri() + ", ";
            }
        }
        return dsVipDat;
    }

    public String inDoiDat() {
        String dsDoiDat = "";
        for (GheDoi ghe : dsGheDoi.values()) {
            if ("doi".equals(ghe.getLoai().toLowerCase()) && ghe.getKhachId() != null) {
                dsDoiDat += ghe.getViTri() + ", ";
            }
        }
        return dsDoiDat;
    }

    public String inDsGheTrong() {
        String dsTrongThuong = "";
        String dsTrongVip = "";
        String dsTrongDoi = "";
        for (Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsTrongThuong += g.getKey() + ", ";
            }
        }
        for (Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsTrongVip += g.getKey() + ", ";
            }
        }
        for (Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsTrongDoi += g.getKey() + ", ";
            }
        }
        String[] thuongArr = dsTrongThuong.split(",");
        String[] vipArr = dsTrongVip.split(",");
        String[] doiArr = dsTrongDoi.split(",");
        Arrays.sort(thuongArr);
        Arrays.sort(vipArr);
        Arrays.sort(doiArr);
        return "DsGheThuong= " + Arrays.toString(thuongArr) + "\n" + "DsGheVip= " + Arrays.toString(vipArr) + "\n" + "DsGheDoi= " + Arrays.toString(doiArr) + "\n";
    }

    public List<Ghe> getDsGheTrong() {
        List<Ghe> ds = new ArrayList<>();
        for (Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                ds.add(g.getValue());
            }
        }
        for (Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                ds.add(g.getValue());
            }
        }
        for (Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                ds.add(g.getValue());
            }
        }
        return ds;
    }

    public int getSucChua() {
        return this.slThuong + this.slVip + this.slDoi * 2;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void themDt (int dt) {
        this.dt += dt;
    }
    
    public int getTongGhe() {
        return slThuong + slVip + slDoi;
    }
    
    @Override
    public String toString() {
        return "Phong{" + "id=" + getId() + ", slVip=" + slVip + ", slThuong=" + slThuong + ", slDoi=" + slDoi + ", sucChua=" + getSucChua() + ", dsGhe=" + inDsGheTrong() + ", isFull=" + isFull + ", suatChieu=" + suatChieu + ", isPlaying=" + isPlaying + '}' + '\n';
    }

}
