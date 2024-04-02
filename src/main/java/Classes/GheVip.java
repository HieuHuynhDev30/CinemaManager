/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Lenovo
 */
public class GheVip extends Ghe {

    public GheVip() {
    }

    public GheVip(String Loai, Phong Phong, String ViTri, Float Gia, boolean IsTaken, Khach khach, CharSequence ngaygio) {
        super(Loai, Phong, ViTri, Gia, IsTaken, khach, ngaygio);
    }
    
    public void setLoai() {
        this.Loai = "VIP";
    }

    public void setGia() {
        this.Gia = (float) 120.000;
    }

    @Override
    public String toString() {
        setLoai();
        setGia();
        return "GheVip{" + "Loai= " + Loai + ", Phong= " + Phong + ", ViTri= " + ViTri + ", Gia= " + Gia + ", IsTaken= " + IsTaken + ", khach=" + khach + ", ThoiGianDat= " + ThoiGianDat + '}';
    }
    
}
