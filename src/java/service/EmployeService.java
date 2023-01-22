/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.EmployeDao;
import dao.SpecialDao;
import modele.Employe;
import modele.Special;

/**
 *
 * @author LEGION
 */
public class EmployeService {
       public static void insertEmploye(Employe employe) throws Exception{
        try{
            EmployeDao.addEmploye(employe);
        }
        catch(Exception e){
           throw e; 
        }
    }
}
