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
            Khach khachAo = new Khach();
            khachAo.setHoTen("empty");
            in("Nhap so ghe thuong:");
            p.setSlThuong(Integer.parseInt(sc.nextLine()));
            for (int i = 0; i < p.getSlThuong(); i++) {
                GheThuong gt = new GheThuong();
                gt.setKhach(khachAo);
                p.themGhe(gt, khachAo);
            }
            in("Nhap so ghe Vip:");
            p.setSlVip(Integer.parseInt(sc.nextLine()));
            for (int i = 0; i < p.getSlVip(); i++) {
                GheVip gv = new GheVip();
                gv.setKhach(khachAo);
                p.themGhe(gv, khachAo);
            }
            in("Nhap so ghe doi:");
            p.setSlDoi(Integer.parseInt(sc.nextLine()));
            for (int i = 0; i < p.getSlDoi(); i++) {
                GheDoi gd = new GheDoi();
                gd.setKhach(khachAo);
                p.themGhe(gd, khachAo);
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
    }
    
    public static void in(Object item) {
        System.out.println(item);
    }

    public static void inf(String format, Object... items) {
        System.out.printf(format, items);
    }
}
