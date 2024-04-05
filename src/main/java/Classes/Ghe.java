/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.Adaptaters.LocalDateTimeAdapter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


public class Ghe{

    
    String loai;
    String viTri;
    double gia;
    boolean IsTaken;
    String khachId;
    LocalDateTime thoiGianDat;
    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Ghe() {
        this.IsTaken = false;
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
        this.IsTaken = true;
    }

    public boolean getIsTaken() {
        return IsTaken;
    }


    public void setThoiGianDat(LocalDateTime thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }


    public String getLoai() {
        return loai;
    }


    public String getViTri() {
        return viTri;
    }

    public double getGia() {
        return gia;
    }
    
    public void setTrong () {
        this.IsTaken = false;
    }

    
     public String getKhachId() {
        return khachId;
    }

    public void setKhachId(String khachId) {
        this.khachId = khachId;
    }


    @XmlTransient
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
