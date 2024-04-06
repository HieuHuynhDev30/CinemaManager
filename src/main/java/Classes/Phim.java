/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.Adaptaters.DurationAdapter;
import Classes.Adaptaters.LocalDateAdapter;
import static Classes.Khach.dateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "phim")
@XmlAccessorType(XmlAccessType.FIELD)
public class Phim {
    private static int currId;
    private String id, ten, theLoai;
    private int doTuoi;
    @XmlJavaTypeAdapter(DurationAdapter.class)
    private Duration thoiLuong;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate TgKhoiChieu;
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String[] dsTheLoai = {"Hai huoc", "Hanh dong", "Khoa hoc vien tuong", "Kinh di", "Tinh cam", "Lich su"};
    public static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("mm");

    public Phim() {
    }

    public Phim(String ten, long thoiLuong, String theLoai, int doTuoi) {
        currId++;
        id = "Ph" + currId;
        this.ten = ten;
        this.setThoiLuong(thoiLuong);
        this.theLoai = "Khong xac dinh";
        setTheLoai(theLoai);
        this.doTuoi = doTuoi;
    }

    public void setId(int id) {
        this.id = "Ph" + id;
    }

    
    public String getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public final String setTheLoai(String theLoai) {
        for (String dsTheLoai1 : dsTheLoai) {
            if (theLoai == null ? dsTheLoai1 == null : theLoai.equals(dsTheLoai1)) {
                this.theLoai = theLoai;
                return "Da thay doi the loai";
            }
        }
        return "The loai khong ton tai";
    }

    public int getDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(int doTuoi) {
        this.doTuoi = doTuoi;
    }

    public Duration getThoiLuong() {
        return thoiLuong;
    }

    public final long inThoiLuong() {
        return thoiLuong.toMinutes();
    }

    public final void setThoiLuong(long duration) {
        try {
            this.thoiLuong = Duration.ofMinutes(duration);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang phut");
        }
    }

    public LocalDate getTgKhoiChieu() {
        return TgKhoiChieu;
    }

    public void setTgKhoiChieu(CharSequence ngay) {
        try {
            TgKhoiChieu = LocalDate.parse(ngay, dateFormat);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang ngay");
        }
    }

    public String inTgKhoiChieu() {
        return TgKhoiChieu.format(dateFormat);
    }

    @Override
    public String toString() {
        return "Phim{" + "id=" + id + ", ten=" + ten + ", theLoai=" + theLoai + ", doTuoi=" + doTuoi + ", thoiLuong=" + inThoiLuong() + ", TgKhoiChieu=" + inTgKhoiChieu() + '}';
    }

}
