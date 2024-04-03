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
public class Phim {
    private static int CurrId;
    private String id, ten, theLoai;
    private int doTuoi;
    private LocalTime thoiLuong;
    private LocalDate TgKhoiChieu;
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}
