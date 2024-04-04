/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Phim;
import XML.PhimListXML;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class PhimFunc {
    private static final String PHIM_FILE_NAME = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\CinemaManager\\src\\main\\java\\XML\\Phim.xml";

    public PhimFunc() {
    }

    public void writeListPhims(List<Phim> phims) {
        PhimListXML phimXML = new PhimListXML();
        phimXML.setPhims(phims);
        FileUtils.writeXMLtoFile(PHIM_FILE_NAME, phimXML);
    }
}
