/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cinemamanager;

import Classes.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class CinemaManager {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        in("TAO RAP PHIM");
        RapPhim rp = new RapPhim();
        in("Nhap ten rap:");
        rp.setTenRap(sc.nextLine().toUpperCase());
        boolean done = false;
        while (done == false) {
            in("**************************************");
            in("Tao phong:");
            Phong p = new Phong();
            in("Tao ghe:");
            in("Nhap so ghe thuong:");
            p.setSlThuong(Integer.parseInt(sc.nextLine()));
            for (int i = 0; i < p.getSlThuong(); i++) {
                GheThuong gt = new GheThuong();
                p.themGhe(gt);
            }
            in("Nhap so ghe Vip:");
            p.setSlVip(Integer.parseInt(sc.nextLine()));
            for (int i = 0; i < p.getSlVip(); i++) {
                GheVip gv = new GheVip();
                p.themGhe(gv);
            }
            in("Nhap so ghe doi:");
            p.setSlDoi(Integer.parseInt(sc.nextLine()));
            for (int i = 0; i < p.getSlDoi(); i++) {
                GheDoi gd = new GheDoi();
                p.themGhe(gd);
            }
            in("Thong tin phong:");
            in(p.toString());
            rp.themPhong(p);
            in("Tiep tuc tao phong?(y/n):");
            String next = sc.nextLine();
            if ("n".equals(next)) {
                done = true;
            }
        }
        in("Thong tin rap phim:");
        in(rp);
        done = false;
        while (!done) {
            in("**************************************");
            in("Tao suat chieu:");
            SuatChieu sch = new SuatChieu();
            in("Them phim duoc chieu:");
            sch.setPhim(sc.nextLine().toUpperCase());
            in("Chon phong chieu:");
            for (int i = 0; i < rp.getDsPhong().size(); i++) {
                in(i + ". " + rp.getDsPhong().get(i).getId() + " - Suc chua: " + rp.getDsPhong().get(i).getDsGhe().size());
            }
            int pchoice = Integer.parseInt(sc.nextLine());
            sch.setPhong(rp.getDsPhong().get(pchoice));
            in("Dat thoi gian chieu (dd-MM-yyyy HH:mm):");
            sch.setThoiGianChieu(sc.nextLine());
            in("In thong tin suat chieu:");
            in(sch.toString());
            rp.themSuatChieu(sch);
            in("Tiep tuc tao suat chieu?(y/n):");
            String next = sc.nextLine();
            if ("n".equals(next)) {
                done = true;
            }
        }
        in("Cac suat chieu hien co:");
        in(rp.inDsSuatChieu());

        done = false;
        while (!done) {
            in("**************************************");
            in("Nhap thong tin khach hang:");
            in("Tao tai khoan khach hang:");
            Khach k = new Khach();
            in("Nhap ho va ten:");
            k.setHoTen(sc.nextLine());
            in("Nhap gioi tinh:");
            k.setGioiTinh(sc.nextLine());
            in("Nhap ngay sinh(dd-MM-yyyy):");
            k.setNgaySinh(sc.nextLine());
            in("Thong tin khach hang:");
            in(k.toString());
            rp.themKhach(k);
            in("Tiep tuc nhap thong tin khach hang?(y/n):");
            String next = sc.nextLine();
            if ("n".equals(next)) {
                done = true;
            }
        }
        in("Danh sach khach hang:");
        in(rp.inDsKhach());

        done = false;
        Khach khachP;
        HoiVien HvP;

        while (!done) {
            in("**************************************");
            in("Thuc hien giao dich ban ve:");
            in("Chon doi tuong mua ve: 0. Khach hang\t1. Hoi vien");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0 -> {
                    in(rp.inDsKhach());
                    try {
                        khachP = rp.getDsKhach().get(Integer.parseInt(sc.nextLine()));
                    } catch (NumberFormatException e) {
                        throw e;
                    }   giaoDich(khachP, rp);
                }
                case 1 -> {
                    in(rp.inDsHoiVien());
                    try {
                        HvP = rp.getDsHoiVien().get(Integer.parseInt(sc.nextLine()));
                    } catch (NumberFormatException e) {
                        throw e;
                    }   giaoDich(HvP, rp);
                }
                default -> in("Sai lua chon");
            }
            in("Tiep tuc giao dich?(y/n):");
            String next = sc.nextLine();
            if ("n".equals(next)) {
                done = true;
            }
        }

        done = false;
        while (!done) {
            in("Nang cap hoi vien");
            in("Lua chon khach hang de nang cap:");
            in(rp.inDsKhach());
            khachP = rp.getDsKhach().get(Integer.parseInt(sc.nextLine()));
            in(rp.nangCapHV(khachP.getId()));
            in("Danh sach khach hang:");
            in(rp.inDsKhach());
            in("Danh sach hoi vien:");
            in(rp.inDsHoiVien());
            in("Tiep tuc nang cap?(y/n):");
            String next = sc.nextLine();
            if ("n".equals(next)) {
                done = true;
            }
        }
    }

    public static void in(Object item) {
        System.out.println(item);
    }

    public static void inf(String format, Object... items) {
        System.out.printf(format, items);
    }

    public static void giaoDich(Khach khachP, RapPhim rp) {
        in("Cac suat chieu hien co:");
        in(rp.inDsSuatChieu());
        SuatChieu schP = rp.getDsSuatChieu().get(Integer.parseInt(sc.nextLine()));
        inf("Suat chieu %s co tai phong chieu %s", schP.getPhim(), schP.getPhong());
        Phong phongP = schP.getPhong();
        in("Chon vi tri:");
        in(phongP.inDsGheTrong());
        String vtchoice = sc.nextLine();
        Ghe gheP = phongP.getDsGhe().get(vtchoice);
        if (gheP.getIsTaken()) {
            in("Ghe da duoc mua, vui long chon ghe khac");
        } else {
            gheP.setIsTaken();
            Ve ve = new Ve();
            ve.setGhe(gheP);
            ve.setSuat(schP);
            khachP.muaVe(ve);
            in("Thong tin ve:");
            in(ve.toString());
        }

        in("Khach hang da mua:");
        in(khachP.toString());
    }
}
