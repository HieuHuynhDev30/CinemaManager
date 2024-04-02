/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Lenovo
 */
public class HoiVien extends Khach {

    private double diem;
    private LocalDateTime ngayDk;
    private static final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public HoiVien() {
    }

    public double getDiem() {
        return diem;
    }

    public void tichDiem(double diem) {
        this.diem += diem;
    }

    public LocalDateTime getNgayDk() {
        return ngayDk;
    }

    public void setNgayDk(CharSequence ngayGio) {
        try {
           this.ngayDk = LocalDateTime.parse(ngayGio, formatDateTime);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang ngay hoac gio");
        }
    }

    public String toStringNDK() {
        String formatNS = this.ngayDk.format(formatDateTime);
        return formatNS;
    }
    
    public String doiVe(Ve ...ves) {
        int ve = 0;
        String dsMua = "";
        while (ve < ves.length) {
            try {
                super.getDsVe().add(ves[ve]);
                dsMua += String.format("Mua ve %d thanh cong\n", ves[ve].getId());
                this.tichDiem(-ves[ve].getGhe().getGia());
            } catch (Exception e) {
                dsMua += String.format("Mua ve %d khong thanh cong\n", ves[ve].getId());
            }
            ve++;
        }
        return dsMua;
    }

    @Override
    public String toString() {
        String khachString = "";
        khachString += super.toString();
        return "HoiVien{" + khachString + "diem=" + diem + ", ngayDk=" + toStringNDK() + '}' + "\n";
    }
}
