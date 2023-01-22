/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.SpecialDao;
import modele.Special;

/**
 *
 * @author LEGION
 */
public class SpecialService {
    public static void insertSpecial(Special special) throws Exception{
        try{
            SpecialDao.addSpecial(special);
        }
        catch(Exception e){
           throw e; 
        }
    }
}
