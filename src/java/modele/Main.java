/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author LEGION
 */
public class Main {
    public static void main(String[] args) throws ParseException{
        /* LocalDate date1 = LocalDate.now();
        //System.out.println(date1);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date apres =  format.parse("01/25/2021");
        Date apres1 = format.parse("01/26/2021");
        System.out.println(apres + " " + apres1);
        long difference = apres1.getTime() - apres.getTime();
        long val = difference/(1000*60*60*24*30*12);
        System.out.println(val);
        Date date = new Date();
        String str= format.format(date);
        Date Daty = format.parse(str);
        System.out.println( Daty);
        long diff = apres1.getTime() - Daty.getTime();
        long valy = difference/(1000*60*60*24);
        System.out.println(valy);*/
        
        Date date = new Date();
        SimpleDateFormat fr = new SimpleDateFormat("dd/MM/yyyy");
        date = fr.parse("25/12/2021");
        System.out.println(date);
        
    }
}
