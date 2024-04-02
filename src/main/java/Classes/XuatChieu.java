/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import static Classes.Ghe.formatDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Lenovo
 */
public class XuatChieu {
    private static int currId;
    private int id;
    private String phim;
    private Phong phong;
    LocalDateTime thoiGianChieu;
    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public XuatChieu() {
        currId++;
        this.id = currId;
    }

    public String getPhim() {
        return phim;
    }

    public void setPhim(String phim) {
        this.phim = phim;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
    
    public LocalDateTime getThoiGianChieu() {
        return thoiGianChieu;
    }

    public String inThoiGianChieu() {
        return thoiGianChieu.format(formatDateTime);
    }

    public void setThoiGianChieu(CharSequence ngayGio) {
        try {
            this.thoiGianChieu = LocalDateTime.parse(ngayGio, formatDateTime);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang ngay hoac gio");
        }
    }

    @Override
    public String toString() {
        return "XuatChieu{" + "id=" + id + ", phim=" + phim + "\n, phong=" + phong.toString() + ", thoiGianChieu=" + inThoiGianChieu() + '}' + '\n';
    }
}
