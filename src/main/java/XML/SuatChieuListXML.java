/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XML;

import Classes.SuatChieu;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "suatChieus")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuatChieuListXML {
    private List<SuatChieu> suatChieu;

    public SuatChieuListXML() {
    }

    public List<SuatChieu> getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(List<SuatChieu> suatChieu) {
        this.suatChieu = suatChieu;
    }
    
}
