/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Lenovo
 */
class Ve {
    private int id;
    private Khach khach;
    private Ghe ghe;
    private XuatChieu xuat;

    public Ve() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Khach getKhach() {
        return khach;
    }

    public void setKhach(Khach khach) {
        this.khach = khach;
    }

    public Ghe getGhe() {
        return ghe;
    }

    public void setGhe(Ghe ghe) {
        this.ghe = ghe;
    }

    public XuatChieu getXuat() {
        return xuat;
    }

    public void setXuat(XuatChieu xuat) {
        this.xuat = xuat;
    }

    @Override
    public String toString() {
        return "Ve{" + "id=" + id + ", khach=" + khach + ", ghe=" + ghe + ", xuat=" + xuat + '}';
    }
    
}
