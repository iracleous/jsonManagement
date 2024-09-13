/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gr.codehub.eshoped.jsonmanagement;

import gr.codehub.eshoped.jsonmanagement.service.UrlReader;
import java.io.IOException;

/**
 *
 * @author DimitrisIracleous
 */
public class JsonManagement {

    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
        
        System.out.println("Hello World!");
        UrlReader.Reader();
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Total time = " + (endTime-startTime) /1000. + " sec");
        
    }
}
