/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.*;

/**
 *
 * @author Lenovo
 */
public class Phong {

    private static int currId;
    private int id, slVip, slThuong, slDoi;
    private Map<Ghe, Khach> dsGhe = new HashMap<>();
    private List<String> gheIds = new ArrayList<>();
    private boolean isFull;
    private XuatChieu xuatChieu;
    private boolean isPlaying;

    public Phong() {
        currId++;
        this.id = currId;
    }

    public String getId() {
        return "P" + id;
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

    public Map<Ghe, Khach> getDsGhe() {
        return dsGhe;
    }

    public void themGhe(Ghe ghe, Khach khach) {
        String key = "";
        if (ghe instanceof GheThuong) {
            key = "A";
        } else if (ghe instanceof GheVip) {
            key = "B";
        } else {
            key = "C";
        }
        ghe.setViTri(String.format("%s%s%d", this.getId(), key, this.gheIds.size() + 1));
        this.gheIds.add(ghe.getViTri());
        dsGhe.put(ghe, khach);
    }

    public boolean isIsFull() {
        return isFull;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public XuatChieu getXuatChieu() {
        return xuatChieu;
    }

    public void setXuatChieu(XuatChieu xuatChieu) {
        this.xuatChieu = xuatChieu;
    }

    public boolean isIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public int inTongTrong() {
        int tong = 0;
        for (Ghe ghe : dsGhe.keySet()) {
            if (!ghe.IsTaken) {
                tong++;
            }
        }
        return tong;
    }

    public int inTongDat() {
        return dsGhe.size() - inTongTrong();
    }

    public String inThuongDat() {
        String dsThuongDat = "";
        for (Ghe ghe : dsGhe.keySet()) {
            if ("thuong".equals(ghe.getLoai().toLowerCase()) && ghe.IsTaken) {
                dsThuongDat += ghe.getViTri() + ", ";
            }
        }
        return dsThuongDat;
    }

    public String inVipDat() {
        String dsVipDat = "";
        for (Ghe ghe : dsGhe.keySet()) {
            if ("vip".equals(ghe.getLoai().toLowerCase()) && ghe.IsTaken) {
                dsVipDat += ghe.getViTri() + ", ";
            }
        }
        return dsVipDat;
    }

    public String inDoiDat() {
        String dsDoiDat = "";
        for (Ghe ghe : dsGhe.keySet()) {
            if ("doi".equals(ghe.getLoai().toLowerCase()) && ghe.IsTaken) {
                dsDoiDat += ghe.getViTri() + ", ";
            }
        }
        return dsDoiDat;
    }

    public String inDsGheTrong() {
        String dsTrongThuong = "";
        String dsTrongVip = "";
        String dsTrongDoi = "";
        for (Ghe ghe : dsGhe.keySet()) {
            if (!ghe.IsTaken) {
                switch (ghe.getLoai().toLowerCase()) {
                    case "thuong":
                        dsTrongThuong += ghe.getViTri() + ", ";
                    case "vip":
                        dsTrongVip += ghe.getViTri() + ", ";
                    case "doi":
                        dsTrongDoi += ghe.getViTri() + ", ";
                }
            }
        }
        return dsTrongThuong + dsTrongVip + dsTrongDoi;
    }

    public String inDsGhe() {
        String dsGheStr = "";
        for (int i = 0; i < gheIds.size(); i++) {
            dsGheStr += gheIds.get(i) + ", ";
        }
        return dsGheStr;
    }

    @Override
    public String toString() {
        return "Phong{" + "id=" + getId() + ", slVip=" + slVip + ", slThuong=" + slThuong + ", slDoi=" + slDoi + ", dsGhe=" + inDsGhe() + ", isFull=" + isFull + ", xuatChieu=" + xuatChieu + ", isPlaying=" + isPlaying + '}' + '\n';
    }

}
