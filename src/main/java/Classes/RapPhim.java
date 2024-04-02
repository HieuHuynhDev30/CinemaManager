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
public class RapPhim {
    private String tenRap;
    private List<Phong> dsPhong = new ArrayList<>();
    private List<Khach> dsKhach = new ArrayList<>();
    private List<HoiVien> dsHoiVien = new ArrayList<>();
    private Set<String> dsLoaiGhe = new HashSet<>();
    private int sucChua;

    public RapPhim() {
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public int getSoPhong() {
        return dsPhong.size();
    }

    public List<Phong> getDsPhong() {
        return dsPhong;
    }
    
    public String inDsPhong() {
        String ds = "";
        for (Phong p: dsPhong) {
            ds += p.getId();
        }
        return ds;
    }

    public void themPhong(Phong p) {
        this.dsPhong.add(p);
    }

    public List<Khach> getDsKhach() {
        return dsKhach;
    }
    
    public String inDsKhach() {
        String ds = "";
        for (Khach k: dsKhach) {
            ds += k.getHoTen();
        }
        return ds;
    }

    public void themKhach(Khach k) {
        this.dsKhach.add(k);
        if (k instanceof HoiVien) {
            this.dsHoiVien.add((HoiVien) k);
        }
    }

    public List<HoiVien> getDsHoiVien() {
        return dsHoiVien;
    }
    
    public String inDsHoiVien() {
        String ds = "";
        for (Khach k: dsHoiVien) {
            ds += k.getHoTen();
        }
        return ds;
    }

    public Set<String> getDsLoaiGhe() {
        return dsLoaiGhe;
    }

    public void themLoaiGhe(String loai) {
        this.dsLoaiGhe.add(loai);
    }
    
    public String timKhach(String ten) {
        for (Khach k : dsKhach) {
            if (k.getHoTen().contains(ten)) {
                return k.toString();
            }
        }
        return "Khong tim thay";
    }
    
    public String timIdKhach(int id) {
        for (Khach k : dsKhach) {
            if (k.getId() == id) {
                return k.toString();
            }
        }
        return "Khong tim thay";
    }
    public int getSucChua() {
        int tong = 0;
        for (Phong p: dsPhong) {
            tong += p.getSlDoi() + p.getSlThuong() + p.getSlVip();
        }
        return tong;
    }

    @Override
    public String toString() {
        return "RapPhim{" + "tenRap=" + tenRap + ", soPhong=" + getSoPhong() + ", dsPhong=" + inDsPhong() + ", dsKhach=" + inDsKhach() + ", dsHoiVien=" + inDsHoiVien() + ", dsLoaiGhe=" + dsLoaiGhe + ", sucChua=" + getSucChua() + '}';
    }
    
    
}
