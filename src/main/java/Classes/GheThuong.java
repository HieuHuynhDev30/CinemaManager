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
        super.setGia(80000);
        super.setLoai("Thuong");
    }
    
    public GheThuong(Khach khach) {
        super.setKhach(khach);
        super.setGia(80000);
        super.setLoai("Thuong");
    }

    @Override
    public String toString() {
        return super.toString() + "khach:" + super.getKhach().getHoTen();
    }
}
