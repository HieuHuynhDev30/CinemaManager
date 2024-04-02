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
        super.setGia(120000);
        super.setLoai("Vip");
    }

    public GheVip(Khach khach) {
        super.setKhach(khach);
        super.setGia(120000);
        super.setLoai("Vip");
    }

    @Override
    public String toString() {
        return super.toString() + "khach: " + super.getKhach().getHoTen();
    }
    
}
