/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.Adaptaters.LocalDateAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Lenovo
 */
// Lớp Khach được dùng như lớp thành viên trong chương trình, khách vãng lai có thể đặt vé nhưng được ghi nhận với id và họ tên là "no_mem"
// và không được lưu vào xml của khach
@XmlRootElement(name = "khach") // anotation xác định element được đọc vào file xml
@XmlAccessorType(XmlAccessType.FIELD) // đọc dưới dạng trường, nhận các thuộc tính là một element
public class Khach {

    private static int currId;
    private String hoTen, gioiTinh;
    @XmlJavaTypeAdapter(LocalDateAdapter.class) // gán adapter cho thuộc tính dạng ngày giờ để có thể đọc được vào trong file xml
    private LocalDate ngaySinh;
    private int slVeDat;
    private double tongTien;
    private double diem;
    @XmlAttribute // xác định cách đọc thuộc tính dưới dạng thuộc tính của element mẹ
    private String id;
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private List<Ve> dsVe = new ArrayList<>();

    public Khach() {
        this.diem = 0;
        taoId();
    }
    
    public void taoId() {
        currId++;
        this.id = "K" + currId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTuoi() {
        return LocalDate.now().getYear() - ngaySinh.getYear();
    }

    public int getSlVeDat() {
        return this.dsVe.size();
    }

    public double getTongTien() {
        int tong = 0;
        if (!this.dsVe.isEmpty()) {
            for (int i = 0; i < this.dsVe.size(); i++) {
                tong += this.dsVe.get(i).getGhe().getGia();
            }
        }
        this.tongTien = tong;
        return tong;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public void congDiem(double diem) {
        this.diem += diem;
    }

    public void doiDiem(double diem) {
        this.diem -= diem;
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

    public String inNgaySinh() {
        return this.ngaySinh.format(dateFormat);
    }

    public void setNgaySinh(CharSequence ngay) {
        try {
            ngaySinh = LocalDate.parse(ngay, dateFormat);
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

    public void setDsVe(List<Ve> dsVe) {
        this.dsVe = dsVe;
    }

    public void muaVe(Ve ve) {
        this.dsVe.add(ve);
//        this.slVeDat++;
//        this.tongTien += ve.getGhe().getGia();
    }

    public String inDsVe() {
        String dsve = "";
        if (!this.dsVe.isEmpty()) {
            for (int i = 0; i < this.dsVe.size(); i++) {
                dsve += this.dsVe.get(i).toString() + "\n";
            }
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

//    @Override
//    public String toString() {
//        return "Khach{" + "id=" + id + ", tuoi=" + getTuoi() + ", slVeDat=" + getSlVeDat() + ", tongTien=" + getTongTien() + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + toStringNS() + ", dsVe=" + inDsVe() + '}';
//    }
}
