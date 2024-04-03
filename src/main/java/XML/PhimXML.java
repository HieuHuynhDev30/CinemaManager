/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XML;

import Classes.Phim;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "phims")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhimXML {
    private List<Phim> phims;

    public PhimXML() {
    }

    public List<Phim> getPhims() {
        return phims;
    }

    public void setPhims(List<Phim> phims) {
        this.phims = phims;
    }
    
    
}
