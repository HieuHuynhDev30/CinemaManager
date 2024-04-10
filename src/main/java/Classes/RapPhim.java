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
    private List<Ve> dsVe = new ArrayList<>();
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

    public List<Ve> getDsVe() {
        return dsVe;
    }

    public void setDsVe(List<Ve> dsVe) {
        this.dsVe = dsVe;
    }

    public void themVe(Ve ve) {
        this.dsVe.add(ve);
    }

    public String inDsPhong() {
        String ds = "";
        for (Phong p : dsPhong) {
            ds += p.getId() + " sucChua: " + p.getSucChua() + "\n";
        }
        return ds;
    }

    public void setDsPhong(List<Phong> dsPhong) {
        this.dsPhong = dsPhong;
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

    public void setDsSuatChieu(List<SuatChieu> dsSuatChieu) {
        this.dsSuatChieu = dsSuatChieu;
    }

    public String themSuatChieu(SuatChieu suatChieu) {
        for (SuatChieu sc : this.getDsSuatChieu()) {
            if (sc.getPhong().getId() == null ? suatChieu.getPhong().getId() == null : sc.getPhong().getId().equals(suatChieu.getPhong().getId())) {
                Duration interval = Duration.between(sc.getThoiGianChieu(), suatChieu.getThoiGianChieu());
                long intervalAbs = Math.abs(interval.toMinutes());
                if (intervalAbs <= suatChieu.getPhim().getThoiLuong().toMinutes() + 30 || intervalAbs <= sc.getPhim().getThoiLuong().toMinutes() + 30) {
                    System.out.println(interval.toMinutes());
                    System.out.println(suatChieu.getPhim().getThoiLuong().toMinutes() + 30);
                    System.out.println(sc.getPhim().getThoiLuong().toMinutes() + 30);
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

    public String nangCapHV(String id) {
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

    public void xoaKhach(String khachId) {
        for (Khach kh : this.getDsKhach()) {
            if (kh.getId() == null ? khachId == null : kh.getId().equals(khachId)) {
                this.getDsKhach().remove(kh);
                break;
            }
        }

    }

    public void xoaPhim(String phimId) {
        for (Phim ph : this.getDsPhim()) {
            if (ph.getId() == null ? phimId == null : ph.getId().equals(phimId)) {
                this.getDsPhim().remove(ph);
                break;
            }
        }
        for (SuatChieu sch : this.getDsSuatChieu()) {
            if (sch.getPhim().getId() == null ? phimId == null : sch.getPhim().getId().equals(phimId)) {
                this.getDsSuatChieu().remove(sch);
            }
        }
    }

    public void xoaSuatChieu(String schId) {
        for (SuatChieu sch : this.getDsSuatChieu()) {
            if (sch.getId() == null ? schId == null : sch.getId().equals(schId)) {
                this.getDsSuatChieu().remove(sch);
                break;
            }
        }
    }

//    public void xoaPhong(String pId) {
//        for (Phong p : this.getDsPhong()) {
//            if (p.getId() == null ? pId == null : p.getId().equals(pId)) {
//                this.getDsPhong().remove(p);
//                break;
//            }
//        }
//        for (SuatChieu sch : this.getDsSuatChieu()) {
//            if (sch.getPhong().getId() == null ? pId == null : sch.getPhong().getId().equals(pId)) {
//                this.getDsSuatChieu().remove(sch);
//            }
//        }
//    }

    public void xoaVe(String veId) {
        for (Ve ve : this.getDsVe()) {
            if (ve.getId() == null ? veId == null : ve.getId().equals(veId)) {
                this.getDsVe().remove(ve);
                break;
            }
        }
    }

    public void sapXepKhach(ArrayList<Khach> list, String tieuChi, boolean beLon) {
        if ("id".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Khach o1, Khach o2) -> {
                int intId1 = Integer.parseInt(o1.getId().substring(1));
                int intId2 = Integer.parseInt(o1.getId().substring(1));
                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
                    return 1;
                }
                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
                    return -1;
                }
                return 0;
            });
        }
        if ("tongtien".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Khach o1, Khach o2) -> {
                int intId1 = o1.getTongTien();
                int intId2 = o2.getTongTien();
                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
                    return 1;
                }
                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
                    return -1;
                }
                return 0;
            });
        }
        if ("slvedat".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, (Khach o1, Khach o2) -> {
                int intId1 = o1.getSlVeDat();
                int intId2 = o2.getSlVeDat();
                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
                    return 1;
                }
                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
                    return -1;
                }
                return 0;
            });
        }
    }

//    public void sapXepPhong(ArrayList<Phong> list, String tieuChi, boolean beLon) {
//        if ("id".equals(tieuChi.toLowerCase())) {
//            Collections.sort(list, (Phong o1, Phong o2) -> {
//                int intId1 = Integer.parseInt(o1.getId().substring(1));
//                int intId2 = Integer.parseInt(o1.getId().substring(1));
//                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
//                    return 1;
//                }
//                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
//                    return -1;
//                }
//                return 0;
//            });
//        }
//        if ("succhua".equals(tieuChi.toLowerCase())) {
//            Collections.sort(list, (Phong o1, Phong o2) -> {
//                int intId1 = o1.getSucChua();
//                int intId2 = o2.getSucChua();
//                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
//                    return 1;
//                }
//                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
//                    return -1;
//                }
//                return 0;
//            });
//        }
//    }

//    public void sapXepSuatChieu(ArrayList<SuatChieu> list, String tieuChi, boolean beLon) {
//        if ("id".equals(tieuChi.toLowerCase())) {
//            Collections.sort(list, (SuatChieu o1, SuatChieu o2) -> {
//                int intId1 = Integer.parseInt(o1.getId().substring(3));
//                int intId2 = Integer.parseInt(o1.getId().substring(3));
//                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
//                    return 1;
//                }
//                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
//                    return -1;
//                }
//                return 0;
//            });
//        }
//        if ("soluongve".equals(tieuChi.toLowerCase())) {
//            Collections.sort(list, (SuatChieu o1, SuatChieu o2) -> {
//                int intId1 = o1.getSlVeDat();
//                int intId2 = o2.getSlVeDat();
//                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
//                    return 1;
//                }
//                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
//                    return -1;
//                }
//                return 0;
//            });
//        }
//    }
    
//    public void sapXepPhim(ArrayList<Phim> list, String tieuChi, boolean beLon) {
//        if ("id".equals(tieuChi.toLowerCase())) {
//            Collections.sort(list, (Phim o1, Phim o2) -> {
//                int intId1 = Integer.parseInt(o1.getId().substring(2));
//                int intId2 = Integer.parseInt(o1.getId().substring(2));
//                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
//                    return 1;
//                }
//                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
//                    return -1;
//                }
//                return 0;
//            });
//        }
//        if ("thoiluong".equals(tieuChi.toLowerCase())) {
//            Collections.sort(list, (Phim o1, Phim o2) -> {
//                long intId1 = o1.getThoiLuong().toMinutes();
//                long intId2 = o2.getThoiLuong().toMinutes();
//                if ((beLon && intId1 < intId2) || (!beLon && intId1 > intId2)) {
//                    return 1;
//                }
//                if ((beLon && intId1 > intId2) || (!beLon && intId1 < intId2)) {
//                    return -1;
//                }
//                return 0;
//            });
//        }
//    }
    
    public double doanhThu(String tieuChi, Object ...obs){
        double doanhThu = 0;
        if ("tong".equals(tieuChi.toLowerCase())) {
            for (Ve ve : this.getDsVe()) {
                doanhThu += ve.getGhe().getGia();
            }
        }
        if ("khach".equals(tieuChi.toLowerCase())) {
            for (Khach ob : (Khach[]) obs) {
                doanhThu += ob.getTongTien();
            }
        }
        if ("phim".equals(tieuChi.toLowerCase())) {
            for (Ve ve: this.getDsVe()) {
                for (String phimId : (String[]) obs) {
                    if (ve.getSuat().getPhim().getId() == null ? phimId == null : ve.getSuat().getPhim().getId().equals(phimId)) {
                        doanhThu += ve.getGhe().getGia();
                    }
                }
            }
        }
        
//        if ("suatchieu".equals(tieuChi.toLowerCase())) {
//            for (SuatChieu sch : (SuatChieu[]) obs) {
//                doanhThu += sch.get;
//            }
//        }
//        
        return doanhThu;
    }

    @Override
    public String toString() {
        return "RapPhim{" + "tenRap=" + tenRap + ", soPhong=" + getSoPhong() + ", dsPhong=" + inDsPhong() + ", dsKhach=" + inDsKhach() + ", dsHoiVien=" + inDsHoiVien() + ", dsLoaiGhe=" + dsLoaiGhe + ", sucChua=" + getSucChua() + '}';
    }

}
