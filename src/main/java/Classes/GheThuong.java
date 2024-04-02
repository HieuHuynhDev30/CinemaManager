/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Lenovo
 */
public class GheThuong extends Ghe {

    public GheThuong() {
    }

    public GheThuong(String Loai, Phong Phong, String ViTri, boolean IsTaken, Khach khach, CharSequence ngaygio) {
        super(Loai, Phong, ViTri, 80000, IsTaken, khach, ngaygio);
    }

    public void setLoai() {
        this.Loai = "Thuong";
    }

    public void setGia(double gia) {
        this.Gia = gia;
    }

    @Override
    public String toString() {
        setLoai();
        super.getGia();
        return "GheThuong{" + "Loai= " + Loai + ", Phong= " + Phong + ", ViTri= " + ViTri + ", Gia= " + Gia + ", IsTaken= " + IsTaken + ", khach=" + khach + ", ThoiGianDat= " + ThoiGianDat + '}';
    }
