/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cinemamanager;

import Classes.*;
import Controllers.LoginController;
import Views.LoginView;
import Views.ManagerView;
import java.awt.EventQueue;


/**
 *
 * @author Lenovo
 */
public class CinemaManager {

//    public static Scanner sc = new Scanner(System.in);
//    public static Func func = new Func();
//    public static RapPhim rp = new RapPhim();

     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
//        in("TAO RAP PHIM");
//        in("Nhap ten rap:");
//        rp.setTenRap(sc.nextLine().toUpperCase());
//        rp.setDsPhim(func.readListPhims());
//        rp.setDsKhach(func.readListKhachs());
//        rp.setDsPhong(func.readListPhongs());
//        rp.setDsSuatChieu(func.readListSuatChieus());
//        rp.setDsVe(func.readListVes());
//        in("Thong tin rap phim:");
//        in(rp.toString());
//        boolean done = false;
//        while (!done) {
//            in("Menu:");
//            in("1. Tao phong\t2. Tao phim\t3. Tao suat chieu\t4. Nhap thong tin khach hang\t5. Thuc hien giao dich ban ve\t6. Nang cap hoi vien\t7.Save and Exit\t8. Exit without Save");
//            String option = sc.nextLine();
//            switch (option) {
//                case "1" ->
//                    taoPhong(rp);
//                case "2" ->
//                    taoPhim(rp, func);
//                case "3" ->
//                    taoSuatChieu(rp);
//                case "4" ->
//                    nhapKhachHang(rp, func);
//                case "5" ->
//                    giaoDichBanVe(rp);
//                case "6" ->
//                    nangCapHoiVien(rp);
//                case "7" -> {
//                    func.writeListKhachs(rp.getDsKhach());
//                    func.writeListPhims(rp.getDsPhim());
//                    in(rp.getDsPhong().get(0).getDsGheThuong().get("P1A2").getKhachId());
//                    func.writeListPhongs(rp.getDsPhong());
//                    func.writeListSuatChieus(rp.getDsSuatChieu());
//                    func.writeListVes(rp.getDsVe());
//                    done = true;
//                }
//                default ->
//                    done = true;
//            }
//        }
//
//    }
//
//    public static void in(Object item) {
//        System.out.println(item);
//    }
//
//    public static void inf(String format, Object... items) {
//        System.out.printf(format, items);
//    }
//
//    public static void taoPhong(RapPhim rp) {
//        in(rp.inDsPhong());
//        boolean done = false;
//        while (done == false) {
//            in("**************************************");
//            in("Tao phong:");
//            Phong p = new Phong();
//            in("Tao ghe:");
////            in("Nhap so ghe thuong:");
////            p.setSlThuong(Integer.parseInt(sc.nextLine()));
//            for (int i = 0; i < p.getSlThuong(); i++) {
//                GheThuong gt = new GheThuong();
//                p.themGhe(gt);
//            }
//            in("Nhap so ghe Vip:");
//            p.setSlVip(Integer.parseInt(sc.nextLine()));
//            for (int i = 0; i < p.getSlVip(); i++) {
//                GheVip gv = new GheVip();
//                p.themGhe(gv);
//            }
//            in("Nhap so ghe doi:");
//            p.setSlDoi(Integer.parseInt(sc.nextLine()));
//            for (int i = 0; i < p.getSlDoi(); i++) {
//                GheDoi gd = new GheDoi();
//                p.themGhe(gd);
//            }
////            in("Thong tin phong:");
////            in(p.toString());
////            rp.themPhong(p);
//            in("Tiep tuc tao phong?(y/n):");
//            String next = sc.nextLine();
//            if ("n".equals(next)) {
//                done = true;
//            }
//        }
//        in("Thong tin rap phim:");
//        in(rp);
//    }
//
//    private static void taoPhim(RapPhim rp, Func func) {
//        in(rp.inDsPhim());
//        boolean done = false;
//        while (!done) {
//            in("**************************************");
//            in("Tao phim");
////            in("Nhap ten phim:");
////            String ten = sc.nextLine();
////            in("Nhap thoi luong phim:");
////            long thoiLuong = Long.parseLong(sc.nextLine());
////            in("Nhap the loai:");
////            String theLoai = sc.nextLine();
////            in("Nhap do tuoi:");
////            int doTuoi = Integer.parseInt(sc.nextLine());
////            Phim ph = new Phim(ten, thoiLuong, theLoai, doTuoi);
////            int newId = rp.getDsPhim().size() + 1;
////            ph.setId(newId);
////            in("Nhap thoi gian khoi chieu(dd-MM-yyyy):");
////            ph.setTgKhoiChieu(sc.nextLine());
//            in("Thong tin phim:");
//            in(ph.toString());
//            rp.themPhim(ph);
//            in("Tiep tuc tao phim?(y/n):");
//            String next = sc.nextLine();
//            if ("n".equals(next)) {
//                done = true;
//            }
//        }
//
//        in("Danh sach phim hien co:");
//        in(rp.inDsPhim());
//    }
//
//    private static void taoSuatChieu(RapPhim rp) {
//        boolean done = false;
//        while (!done) {
//            in("**************************************");
//            in("Tao suat chieu:");
//            SuatChieu sch = new SuatChieu();
//            in("Them phim duoc chieu:");
//            in(rp.inDsPhim());
//            sch.setPhim(rp.getDsPhim().get(Integer.parseInt(sc.nextLine())));
//            in("Chon phong chieu:");
//            String noPhong = "Khong con phong trong";
//            for (int i = 0; i < rp.getDsPhong().size(); i++) {
//                if (rp.getDsPhong().get(i).getSuatChieu() == null) {
//                    noPhong = i + ". " + rp.getDsPhong().get(i).getId() + " - Suc chua: " + rp.getDsPhong().get(i).getDsGheTrong().size();
//                }
//            }
//            in(noPhong);
//            if (!"Khong con phong trong".equals(noPhong)) {
//                int pchoice = Integer.parseInt(sc.nextLine());
//                rp.getDsPhong().get(pchoice).setSuatChieu(sch);
//                sch.setPhong(rp.getDsPhong().get(pchoice));
//                in("Dat thoi gian chieu (dd-MM-yyyy HH:mm):");
//                sch.setThoiGianChieu(sc.nextLine());
//                in(rp.themSuatChieu(sch));
//                in("In thong tin suat chieu:");
//                in(sch.toString());
//            }
//            in("Tiep tuc tao suat chieu?(y/n):");
//            String next = sc.nextLine();
//            if ("n".equals(next)) {
//                done = true;
//            }
//        }
//        in("Cac suat chieu hien co:");
//        in(rp.inDsSuatChieu());
//    }
//
//    private static void nhapKhachHang(RapPhim rp, Func func) {
//        in(rp.inDsKhach());
//        boolean done = false;
//        while (!done) {
//            in("**************************************");
//            in("Nhap thong tin khach hang:");
//            in("Tao tai khoan khach hang:");
////            Khach k = new Khach();
////            int newId = rp.getDsKhach().size() + 1;
////            k.setId("K" + newId);
////            in("Nhap ho va ten:");
////            k.setHoTen(sc.nextLine());
////            in("Nhap gioi tinh:");
////            k.setGioiTinh(sc.nextLine());
////            in("Nhap ngay sinh(dd-MM-yyyy):");
////            k.setNgaySinh(sc.nextLine());
////            in("Thong tin khach hang:");
////            in(k.toString());
////            rp.themKhach(k);
//            in("Tiep tuc nhap thong tin khach hang?(y/n):");
//            String next = sc.nextLine();
//            if ("n".equals(next)) {
//                done = true;
//            }
//        }
//        in("Danh sach khach hang:");
//        in(rp.inDsKhach());
//
//    }
//
//    private static void giaoDichBanVe(RapPhim rp) {
//        Khach khachP;
//        HoiVien HvP;
//        boolean done = false;
//        while (!done) {
//            in("**************************************");
//            in("Thuc hien giao dich ban ve:");
//            in("Chon doi tuong mua ve: 0. Khach hang\t1. Hoi vien");
//            int choice = Integer.parseInt(sc.nextLine());
//            switch (choice) {
//                case 0 -> {
//                    in(rp.inDsKhach());
//                    try {
//                        khachP = rp.getDsKhach().get(Integer.parseInt(sc.nextLine()));
//                    } catch (NumberFormatException e) {
//                        throw e;
//                    }
//                    giaoDich(khachP, rp);
//                }
//                case 1 -> {
//                    in(rp.inDsHoiVien());
//                    try {
//                        HvP = rp.getDsHoiVien().get(Integer.parseInt(sc.nextLine()));
//                    } catch (NumberFormatException e) {
//                        throw e;
//                    }
//                    giaoDich(HvP, rp);
//                }
//                default ->
//                    in("Sai lua chon");
//            }
//            in("Tiep tuc giao dich?(y/n):");
//            String next = sc.nextLine();
//            if ("n".equals(next)) {
//                done = true;
//            }
//        }
//    }
//
//    public static void giaoDich(Khach khachP, RapPhim rp) {
//        in("Cac suat chieu hien co:");
//        in(rp.inDsSuatChieu());
//        SuatChieu schP = rp.getDsSuatChieu().get(Integer.parseInt(sc.nextLine()));
//        inf("Suat chieu %s co tai phong chieu %s", schP.getPhim(), schP.getPhong());
//        Phong phongP = schP.getPhong();
//        in("Chon vi tri:");
//        in(phongP.inDsGheTrong());
//        String vtchoice = sc.nextLine();
//        if (phongP.getDsGheThuong().containsKey(vtchoice)) {
//            GheThuong gheP = phongP.getDsGheThuong().get(vtchoice);
//            xuLyVe(gheP, schP, khachP);
//        }
//        if (phongP.getDsGheVip().containsKey(vtchoice)) {
//            GheVip gheP = phongP.getDsGheVip().get(vtchoice);
//            xuLyVe(gheP, schP, khachP);
//        }
//        if (phongP.getDsGheDoi().containsKey(vtchoice)) {
//            GheDoi gheP = phongP.getDsGheDoi().get(vtchoice);
//            xuLyVe(gheP, schP, khachP);
//        }
//        in("Khach hang da mua:");
//        in(khachP.toString());
//        for (Phong ph : rp.getDsPhong()) {
//            if (ph.getId() == null ? phongP.getId() == null : ph.getId().equals(phongP.getId())) {
//                ph.setDsGheThuong(phongP.getDsGheThuong());
//                ph.setDsGheVip(phongP.getDsGheVip());
//                ph.setDsGheDoi(phongP.getDsGheDoi());
//                break;
//            }
//        }
//    }
//
//    public static void xuLyVe(Ghe gheP, SuatChieu schP, Khach khachP) {
//        if (gheP.getKhachId() != null) {
//            in("Ghe da duoc mua, vui long chon ghe khac");
//        } else {
//            gheP.setThoiGianDat(LocalDateTime.now());
//            gheP.setKhachId(khachP.getId());
//            Ve ve = new Ve();
//            ve.setGhe(gheP);
//            ve.setSuat(schP);
//            in("Thong tin ve:");
//            in(ve.toString());
//            khachP.muaVe(ve);
//            rp.themVe(ve);
//        }
//    }
//
//    private static void nangCapHoiVien(RapPhim rp) {
//        Khach khachP;
//        boolean done = false;
//        while (!done) {
//            in("Nang cap hoi vien");
//            in("Lua chon khach hang de nang cap:");
//            in(rp.inDsKhach());
//            khachP = rp.getDsKhach().get(Integer.parseInt(sc.nextLine()));
//            in(rp.nangCapHV(khachP.getId()));
//            in("Danh sach khach hang:");
//            in(rp.inDsKhach());
//            in("Danh sach hoi vien:");
//            in(rp.inDsHoiVien());
//            in("Tiep tuc nang cap?(y/n):");
//            String next = sc.nextLine();
//            if ("n".equals(next)) {
//                done = true;
//            }
//        }
//    }

}
