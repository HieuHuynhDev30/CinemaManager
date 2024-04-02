/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.*;
import java.time.format.DateTimeFormatter;


public class Ghe {

    String Loai;
    Phong Phong;
    String ViTri;
    Double Gia;
    boolean IsTaken;
    Khach khach;
    LocalDateTime ThoiGianDat;
    final static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Ghe() {
    }

    public Ghe(String Loai, Phong Phong, String ViTri, double Gia, boolean IsTaken, Khach khach, CharSequence ngaygio) {
        this.Loai = Loai;
        this.Phong = Phong;
        this.ViTri = ViTri;
        this.Gia = Gia;
        this.IsTaken = IsTaken;
        this.khach = khach;
        setThoiGianDat(ngaygio);
    }

    public void setLoai(String Loai) {
        this.Loai = Loai;
    }

    public void setPhong(Phong Phong) {
        this.Phong = Phong;
    }

    public void setViTri(String ViTri) {
        this.ViTri = ViTri;
    }

    public void setGia(Double Gia) {
        this.Gia = Gia;
    }

    public void setIsTaken(boolean IsTaken) {
        this.IsTaken = IsTaken;
    }

    public void setKhach(Khach khach) {
        this.khach = khach;
    }

    public void setThoiGianDat(CharSequence ngayGio) {
        try {
            this.ThoiGianDat = LocalDateTime.parse(ngayGio, myFormatObj);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang ngay hoac gio");
        }
    }

    public String getLoai() {
        return Loai;
    }

    public Phong getPhong() {
        return Phong;
    }

    public String getViTri() {
        return ViTri;
    }

    public Double getGia() {
        return Gia;
    }

    public boolean isIsTaken() {
        return IsTaken;
    }

    public Khach getKhach() {
        return khach;
    }

    public LocalDateTime getThoiGianDat() {

        return ThoiGianDat;
    }

    public void InVitri() {
        System.out.println("Vi tri ghe: " + this.ViTri);
    }

    @Override
    public String toString() {
        return "Ghe{" + "Loai= " + Loai + ", Phong= " + Phong + ", ViTri= " + ViTri + ", Gia= " + Gia + ", IsTaken= " + IsTaken + ", khach=" + khach + ", ThoiGianDat= " + ThoiGianDat + '}';
    }
}
