/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codehub.eshoped.jsonmanagement.model;

import lombok.Data;

/**
 *
 * @author DimitrisIracleous
 */
@Data
public class Line {
     private int line_id;
     private String play_name;
     private String speech_number;
     private String line_number;
     private String speaker;
     private String text_entry;     
}
