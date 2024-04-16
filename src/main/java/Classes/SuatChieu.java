/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.Adaptaters.LocalDateTimeAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "suatChieu") // anotation xác định element được đọc vào file xml
@XmlAccessorType(XmlAccessType.FIELD) // đọc dưới dạng trường, nhận các thuộc tính là một element
public class SuatChieu {

    private static int currId;
    @XmlAttribute
    private String id;
    private Phim phim;
    private String phongId;
    private boolean full;
//    private Phong phong;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime thoiGianChieu;
    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private double dt;
    private boolean chieuXong;
    private Map<String, GheThuong> dsGheThuong = new HashMap<>();
    private Map<String, GheVip> dsGheVip = new HashMap<>();
    private Map<String, GheDoi> dsGheDoi = new HashMap<>();

    public SuatChieu() {
        currId++;
        this.id = "SCH" + currId;
        chieuXong = false;
    }

    public Phim getPhim() {
        return phim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String inPhim() {
        return this.phim.toString();
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

//    public Phong getPhong() {
//        return phong;
//    }
//
//    public void setPhong(Phong phong) {
//        this.phong = phong;
//        this.setPhongId();
//    }
//
//    public String inPhong() {
//        return this.phong.getId();
//    }
    public String getPhongId() {
        return phongId;
    }

    public void setPhongId(String phId) {
        this.phongId = phId;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean isFull) {
        this.full = isFull;
    }

    
    public LocalDateTime getThoiGianChieu() {
        return thoiGianChieu;
    }

    public String inThoiGianChieu() {
        return thoiGianChieu.format(formatDateTime);
    }

    public void setThoiGianChieu(CharSequence ngayGio) {
        try {
            this.thoiGianChieu = LocalDateTime.parse(ngayGio, formatDateTime);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang ngay hoac gio");
        }
    }

//    public int getSlVeDat() {
//        return phong.getSucChua() - phong.getDsGheTrong().size();
//    }
    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public void themDt(double dt) {
        this.dt += dt;
    }

    public boolean isChieuXong() {
        if (LocalDateTime.now().isAfter(thoiGianChieu.plusMinutes(phim.getThoiLuong().toMinutes()))) {
            chieuXong = true;
        }
        return chieuXong;
    }

    public void setChieuXong(boolean chieuXong) {
        this.chieuXong = chieuXong;
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

    public int inTongTrong() {
        int tong = 0;
        for (Map.Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                tong++;
            }
        }
        for (Map.Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                tong++;
            }
        }
        for (Map.Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
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
        for (Map.Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsTrongThuong += g.getKey() + ", ";
            }
        }
        for (Map.Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsTrongVip += g.getKey() + ", ";
            }
        }
        for (Map.Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
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
        for (Map.Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                ds.add(g.getValue());
            }
        }
        Collections.sort(ds, new Comparator<Ghe>() {
            @Override
            public int compare(Ghe o1, Ghe o2) {
                int o1Int = Integer.parseInt(o1.getViTri().split(phongId)[1].substring(1));
                int o2Int = Integer.parseInt(o2.getViTri().split(phongId)[1].substring(1));
                return o1Int - o2Int;
            }
        });
        List<Ghe> dsVip = new ArrayList<>();
        for (Map.Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsVip.add(g.getValue());
            }
        }
        Collections.sort(dsVip, new Comparator<Ghe>() {
            @Override
            public int compare(Ghe o1, Ghe o2) {
                int o1Int = Integer.parseInt(o1.getViTri().split(phongId)[1].substring(1));
                int o2Int = Integer.parseInt(o2.getViTri().split(phongId)[1].substring(1));
                return o1Int - o2Int;
            }
        });
        ds.addAll(dsVip);
        List<Ghe> dsDoi = new ArrayList<>();
        for (Map.Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsDoi.add(g.getValue());
            }
        }
        Collections.sort(dsDoi, new Comparator<Ghe>() {
            @Override
            public int compare(Ghe o1, Ghe o2) {
                int o1Int = Integer.parseInt(o1.getViTri().split(phongId)[1].substring(1));
                int o2Int = Integer.parseInt(o2.getViTri().split(phongId)[1].substring(1));
                return o1Int - o2Int;
            }
        });
        ds.addAll(dsDoi);

        return ds;
    }

    @Override
    public String toString() {
        return "SuatChieu{" + "id=" + id + ", phim=" + phim.getTen() + ", phong=" + this.getPhongId() + ", thoiGianChieu=" + inThoiGianChieu() + '}' + '\n';
    }
}
