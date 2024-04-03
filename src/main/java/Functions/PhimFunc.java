/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Phim;
import XML.PhimXML;
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

      

    /**
     * Lưu các đối tượng student vào file student.xml
     * 
     * @param phims
     */
    public void writeListPhims(List<Phim> phims) {
        PhimXML phimXML = new PhimXML();
        phimXML.setPhims(phims);
        FileUtils.writeXMLtoFile(PHIM_FILE_NAME, phimXML);
    }
}
