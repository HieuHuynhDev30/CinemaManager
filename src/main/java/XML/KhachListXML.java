package XML;


import Classes.Khach;
import java.util.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "khachs")
@XmlAccessorType(XmlAccessType.FIELD)
public class KhachListXML {
    private List<Khach> khach;

    public KhachListXML() {
    }

    public List<Khach> getKhach() {
        return khach;
    }

    public void setKhach(List<Khach> khach) {
        this.khach = khach;
    }
    
    
}
