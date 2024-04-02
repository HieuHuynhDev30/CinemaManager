/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Lenovo
 */
public class GheDoi extends Ghe {

    public GheDoi() {
    }

    public GheDoi(String Loai, Phong Phong, String ViTri, double Gia, boolean IsTaken, Khach khach, CharSequence ngaygio) {
        super(Loai, Phong, ViTri, Gia, IsTaken, khach, ngaygio);
    }
    
    public void setLoai() {
        this.Loai = "Doi";
    }

    public void setGia() {
        this.Gia = 200.000;
    }

    @Override
    public String toString() {
        setLoai();
        setGia();
        return "GheDoi {" + "Loai= " + Loai + ", Phong= " + Phong + ", ViTri= " + ViTri + ", Gia= " + Gia + ", IsTaken= " + IsTaken + ", khach=" + khach + ", ThoiGianDat= " + ThoiGianDat + '}';
    }
    
}
