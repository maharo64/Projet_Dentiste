/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author LEGION
 */
public class Fonct {
    public long verifDate(String date)throws Exception{
        long comparaison = 0;
        SimpleDateFormat formate = null;
        Date date1 = null;
        Date dateCurrent = null;
        String dateZao = null;
        try{
            formate  = new SimpleDateFormat("MM/dd/yyyy");
            date1 = formate.parse(date);
            dateCurrent = new Date();
            dateZao= formate.format(dateCurrent);
            dateCurrent = formate.parse(dateZao);
            comparaison = (dateCurrent.getTime()-date1.getTime())/(1000*60*60*24*30*12);
        }
        catch(Exception e){
            throw e;
        }
        return comparaison;
    }
    
}
