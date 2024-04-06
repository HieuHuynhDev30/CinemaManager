/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.LocalDateTime;

/**
 *
 * @author Lenovo
 */
public class GheVip extends Ghe {

    public GheVip() {
    }

    public GheVip(String loai, String viTri, double gia, boolean IsTaken, Khach khach, LocalDateTime thoiGianDat) {
        super(loai, viTri, gia, IsTaken, khach, thoiGianDat);
    }
    
    public void setLoai() {
        this.loai = "VIP";
    }

    public void setGia() {
        this.gia =  120.000;
    }

    @Override
    public String toString() {
        setLoai();
        setGia();
        return "GheVip{" + "Loai= " + loai + ", ViTri= " + viTri + ", Gia= " + gia + ", IsTaken= " + IsTaken + ", khach=" + khach + ", ThoiGianDat= " + thoiGianDat + '}';
    }
    
}
