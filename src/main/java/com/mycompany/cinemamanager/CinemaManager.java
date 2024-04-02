/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cinemamanager;

import Classes.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Lenovo
 */
public class CinemaManager {

    public static void main(String[] args) {
        Khach k1 = new Khach();
        k1.setHoTen("Nguyen A");
        k1.setGioiTinh("Nam");
        String newNS = "01-01-1999";
        k1.setNgaySinh(newNS);
        k1.setTuoi(20);
        System.out.println(k1.toString());
        HoiVien hv1 = new HoiVien();
        hv1.setHoTen("Tran B");
        hv1.setGioiTinh("Nam");
        hv1.setNgaySinh(newNS);
        hv1.setNgayDk("10-03-2024 10:10:10");
        hv1.setTuoi(21);
        System.out.println(hv1.toString());
    }
}
