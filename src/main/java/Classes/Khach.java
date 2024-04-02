/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Khach {

    private static int currId;
    private int id, tuoi, slVeDat, tongTien;
    private String hoTen, gioiTinh;
    private LocalDate ngaySinh;
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private List<Ve> dsVe = new ArrayList<>();

    public Khach() {
        currId++;
        this.id = currId;
    }

    public int getId() {
        return id;
    }

    public int getTuoi() {
        return LocalDate.now().getYear() - ngaySinh.getYear();
    }

    public int getSlVeDat() {
        return this.dsVe.size();
    }

    public int getTongTien() {
        int tong = 0;
        for (int i = 0; i < this.dsVe.size(); i++) {
            tong += this.dsVe.get(i).getGhe().getGia();
        }
        return tong;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(CharSequence ngay) {
        try {
           this.ngaySinh = LocalDate.parse(ngay, dateFormat);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang ngay");
        }
    }

    public String toStringNS() {
        String formatNS = this.ngaySinh.format(dateFormat);
        return formatNS;
    }

    public List<Ve> getDsVe() {
        return dsVe;
    }

    public void muaVe(Ve ve) {
        this.dsVe.add(ve);
    }

    public String inDsVe() {
        String dsve = "";
        for (int i = 0; i < this.dsVe.size(); i++) {
            dsve += this.dsVe.get(i).toString() + "\n";
        }
        return dsve;
    }

    public String xoaVe(int... ids) {
        int id = 0;
        String dsXoa = "";
        while (id < ids.length) {
            try {
                this.dsVe.remove(ids[id]);
                dsXoa += String.format("Xoa ve %d thanh cong\n", ids[id]);
            } catch (Exception e) {
                dsXoa += String.format("Ve %d khong ton tai trong danh sach\n", ids[id]);
            }
            id++;
        }
        return dsXoa;
    }

    public void chinhSuaTk(String hoTen, int tuoi, CharSequence ns) {
        this.setHoTen(hoTen);
        this.setNgaySinh(ns);
    }

    @Override
    public String toString() {
        String ve = dsVe.get(0).toString();
        return "Khach{" + "id=" + id + ", tuoi=" + tuoi + ", slVeDat=" + slVeDat + ", tongTien=" + getTongTien() + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + toStringNS() + "\n, dsVe=" + ve + '}';
    }
}
