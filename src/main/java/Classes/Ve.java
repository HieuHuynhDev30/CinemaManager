/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Lenovo
 */
public class Ve {
    private static int currId;
    private int id;
    private Ghe ghe;
    private SuatChieu suat;

    public Ve() {
        currId++;
        this.id = currId;
    }

    public int getId() {
        return id;
    }

    public Ghe getGhe() {
        return ghe;
    }

    public void setGhe(Ghe ghe) {
        this.ghe = ghe;
    }

    public SuatChieu getSuat() {
        return suat;
    }

    public void setSuat(SuatChieu suat) {
        this.suat = suat;
    }

    @Override
    public String toString() {
        String veStr = ghe.viTri;
        return "Ve{" + "id=" + id + veStr  + "\n, xuat=" + suat.toString() + '}';
    }
    
}
