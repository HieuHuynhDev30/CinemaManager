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
    private int slVip, slThuong, slDoi;
    private String id;
    private Map<String, Ghe> dsGhe = new HashMap<>();
    public List<Ghe> GheList = new ArrayList<>();
    private boolean isFull;
    private SuatChieu xuatChieu;
    private boolean isPlaying;

    public Phong() {
        currId++;
        this.id = "P" + currId;
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

    public Map<String, Ghe> getDsGhe() {
        return dsGhe;
    }

    public void themGhe(Ghe ghe) {
        String key = "";
        if (ghe instanceof GheThuong) {
            key = "A";
        } else if (ghe instanceof GheVip) {
            key = "B";
        } else {
            key = "C";
        }
        ghe.setViTri(String.format("%s%s%d", this.getId(), key, this.dsGhe.size() + 1));
        this.GheList.add(ghe);
        dsGhe.put(ghe.getViTri(), ghe);
    }

    public boolean isIsFull() {
        return isFull;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public SuatChieu getXuatChieu() {
        return xuatChieu;
    }

    public void setXuatChieu(SuatChieu xuatChieu) {
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
        for (Ghe ghe : GheList) {
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
        for (Ghe ghe : GheList) {
            if ("thuong".equals(ghe.getLoai().toLowerCase()) && ghe.IsTaken) {
                dsThuongDat += ghe.getViTri() + ", ";
            }
        }
        return dsThuongDat;
    }

    public String inVipDat() {
        String dsVipDat = "";
        for (Ghe ghe : GheList) {
            if ("vip".equals(ghe.getLoai().toLowerCase()) && ghe.IsTaken) {
                dsVipDat += ghe.getViTri() + ", ";
            }
        }
        return dsVipDat;
    }

    public String inDoiDat() {
        String dsDoiDat = "";
        for (Ghe ghe : GheList) {
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
        for (Ghe ghe : GheList) {
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
        for (int i = 0; i < GheList.size(); i++) {
            dsGheStr += GheList.get(i).InVitri() + ", ";
        }
        return dsGheStr;
    }

    @Override
    public String toString() {
        return "Phong{" + "id=" + getId() + ", slVip=" + slVip + ", slThuong=" + slThuong + ", slDoi=" + slDoi + ", dsGhe=" + inDsGhe() + ", isFull=" + isFull + ", xuatChieu=" + xuatChieu + ", isPlaying=" + isPlaying + '}' + '\n';
    }

}
