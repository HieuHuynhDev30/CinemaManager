/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.Duration;
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
    private List<SuatChieu> dsSuatChieu = new ArrayList<>();
    private List<Phim> dsPhim = new ArrayList<>();
    private Set<String> dsLoaiGhe = new HashSet<>();
    private int sucChua;

    public RapPhim() {
        dsLoaiGhe.add("Ghe thuong");
        dsLoaiGhe.add("Ghe Vip");
        dsLoaiGhe.add("Ghe Doi");
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
        for (Phong p : dsPhong) {
            ds += p.getId() + ", ";
        }
        return ds;
    }

    public void themPhong(Phong p) {
        this.dsPhong.add(p);
    }

    public List<Khach> getDsKhach() {
        return dsKhach;
    }

    public void setDsKhach(List<Khach> dsKhach) {
        this.dsKhach = dsKhach;
    }
    

    public String inDsKhach() {
        String ds = "";
        for (Khach k : dsKhach) {
            ds += k.toString() + "\n";
        }
        return ds;
    }

    public void themKhach(Khach k) {
        this.dsKhach.add(k);
    }

    public List<HoiVien> getDsHoiVien() {
        return dsHoiVien;
    }

    public String inDsHoiVien() {
        String ds = "";
        for (Khach k : dsHoiVien) {
            ds += k.toString() + "\n";
        }
        return ds;
    }

    public Set<String> getDsLoaiGhe() {
        return dsLoaiGhe;
    }

    public void themLoaiGhe(String loai) {
        this.dsLoaiGhe.add(loai);
    }

    public List<SuatChieu> getDsSuatChieu() {
        return dsSuatChieu;
    }

    public String themSuatChieu(SuatChieu suatChieu) {
       for (SuatChieu sc : this.getDsSuatChieu()) {
           if(sc.getPhong().getId() == null ? suatChieu.getPhong().getId() == null : sc.getPhong().getId().equals(suatChieu.getPhong().getId())) {
               Duration interval = Duration.between(sc.getThoiGianChieu(), suatChieu.getThoiGianChieu());
               if (interval.toMinutes() <= suatChieu.getPhim().getThoiLuong().toMinutes() + 30 || interval.toMinutes() <= sc.getPhim().getThoiLuong().toMinutes() + 30) {
                   return "Suat chieu sau can bat dau sau khi suat chieu truoc ket thuc it nhat 30 phut";
               } 
           }
       }
        this.dsSuatChieu.add(suatChieu);
        return "Them suat chieu thanh cong";
    }

    public List<Phim> getDsPhim() {
        return dsPhim;
    }

    public void setDsPhim(List<Phim> dsPhim) {
        this.dsPhim = dsPhim;
    }
    
    public String inDsPhim() {
        String ds = "";
        for (Phim ph : dsPhim) {
            ds += ph.toString() + "\n";
        }
        return ds;
    }
    
    public void themPhim(Phim phim) {
        this.dsPhim.add(phim);
    }

    public String inDsSuatChieu() {
        String ds = "";
        for (SuatChieu sch : dsSuatChieu) {
            ds += sch.toString() + "\n";
        }
        return ds;
    }

    public String timKhach(String ten) {
        for (Khach k : dsKhach) {
            if (k.getHoTen().contains(ten)) {
                return k.toString();
            }
        }
        return "Khong tim thay";
    }

    public String timIdKhach(String id) {
        for (Khach k : dsKhach) {
            if (k.getId() == null ? id == null : k.getId().equals(id)) {
                return k.toString();
            }
        }
        return "Khong tim thay";
    }

    public int getSucChua() {
        int tong = 0;
        for (Phong p : dsPhong) {
            tong += p.getSlDoi() * 2 + p.getSlThuong() + p.getSlVip();
        }
        return tong;
    }

    public String nangCapHV(String id){
        for (Khach k : this.getDsKhach()) {
            if (k.getId() == null ? id == null : k.getId().equals(id)) {
                HoiVien hv = new HoiVien();
                hv.setHoTen(k.getHoTen());
                hv.setGioiTinh(k.getGioiTinh());
                hv.setNgaySinh(k.getNgaySinh().format(k.dateFormat));
                hv.setDsVe(k.getDsVe());
                this.getDsHoiVien().add(hv);
                this.getDsKhach().remove(k);
                return "Nang cap hoi vien thanh cong";
            }
        }
        return "Nang cap hoi vien khong thanh cong";
    }
    
    @Override
    public String toString() {
        return "RapPhim{" + "tenRap=" + tenRap + ", soPhong=" + getSoPhong() + ", dsPhong=" + inDsPhong() + ", dsKhach=" + inDsKhach() + ", dsHoiVien=" + inDsHoiVien() + ", dsLoaiGhe=" + dsLoaiGhe + ", sucChua=" + getSucChua() + '}';
    }

}
