/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.Adaptaters.LocalDateTimeAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "suatChieu")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuatChieu {
    private static int currId;
    @XmlAttribute
    private String id;
    private Phim phim;
    private String phongId;
    @XmlTransient
    private Phong phong;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime thoiGianChieu;
    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public SuatChieu() {
        currId++;
        this.id = "SCH" + currId;
    }

    public Phim getPhim() {
        return phim;
    }
    
    public String inPhim() {
        return this.phim.toString();
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
        this.setPhongId();
    }
    
    public String inPhong() {
        return this.phong.getId();
    }

    public String getPhongId() {
        return phongId;
    }

    public void setPhongId() {
        this.phongId = phong.getId();
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
        return "SuatChieu{" + "id=" + id + ", phim=" + phim.getTen() + ", phong=" + inPhong() + ", thoiGianChieu=" + inThoiGianChieu() + '}' + '\n';
    }
}
