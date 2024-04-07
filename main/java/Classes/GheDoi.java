/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Lenovo
 */
public class GheDoi extends Ghe {

    private final Set<Khach> khachDoi = new HashSet<>();

    public GheDoi() {
        super.setGia(200000);
        super.setLoai("Doi");
    }
    
    public GheDoi(Khach khach) {
        super.setKhach(khach);
        this.setKhachDoi(khach);
        super.setGia(200000);
        super.setLoai("Doi");
    }

    public Set<Khach> getKhachDoi() {
        return khachDoi;
    }

    public final String setKhachDoi(Khach khach) {
        for (int i = 0; i < 2; i++) {
            try {
                khachDoi.add(khach);
                return "Da them khach vao ghe doi";
            } catch(Exception e) {
                return "Them vao ghe doi khong thanh cong";
            }
        }
        return "";
    }
    
    public String inKhachDoi() {
        String dsKhach = "";
        for (Khach khachOb : khachDoi) {
            dsKhach += khachOb.getHoTen() + ", ";
        }
        return dsKhach;
    }

    @Override
    public String toString() {
        return super.toString() + "khach=" + inKhachDoi() + '}';
    }
}
