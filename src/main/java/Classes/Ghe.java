/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.*;
import java.time.format.DateTimeFormatter;


public abstract class Ghe {

    
    String loai;
    String viTri;
    double gia;
    boolean IsTaken;
    Khach khach;
    LocalDateTime thoiGianDat;
    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Ghe() {
    }

    public void setLoai(String Loai) {
        this.loai = Loai;
    }


    public void setViTri(String ViTri) {
        this.viTri = ViTri;
    }

    public void setGia(double Gia) {
        this.gia = Gia;
    }

    public void setIsTaken() {
        this.IsTaken = !this.IsTaken;
    }

    
    public void setKhach(Khach khach) {
        this.khach = khach;
    }

    public void setThoiGianDat(CharSequence ngayGio) {
        try {
            this.thoiGianDat = LocalDateTime.parse(ngayGio, formatDateTime);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang ngay hoac gio");
        }
    }

    public String getLoai() {
        return loai;
    }


    public String getViTri() {
        return viTri;
    }

    public Double getGia() {
        return gia;
    }

    public boolean getIsTaken() {
        return IsTaken;
    }
    
    public void setTrong () {
        this.IsTaken = false;
    }

    public Khach getKhach() {
        return khach;
    }

    public LocalDateTime getThoiGianDat() {

        return thoiGianDat;
    }
    
    public String inThoiGianDat() {

        return LocalDateTime.now().format(formatDateTime);
    }

    public String InVitri() {
        return this.viTri;
    }

    @Override
    public String toString() {
        return "Ghe{" + "loai=" + loai + ", viTri=" + viTri + ", gia=" + gia + ", isTaken=" + IsTaken  + ", thoiGianDat=" + inThoiGianDat() + '}' + '\n';
    }

   
}
