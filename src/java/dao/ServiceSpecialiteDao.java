/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modele.Employe;
import modele.Specialite;

/**
 *
 * @author LEGION
 */
public class ServiceSpecialiteDao {
    public ArrayList<Specialite> getAllService( Connection con, int duree ) throws Exception{
         int salaire =0;
        ArrayList<Specialite> specialite = new ArrayList<>();
        String sql = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try{
            sql = "SELECT salaire from v_specialiteService";
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while(res.next()){
                Specialite spe = new Specialite();
                spe.setId(res.getInt(1));
                spe.setSalaire(res.getInt(3));
                spe.setSpecialite(res.getString(2));
               specialite.add(spe);
               
            }
        }catch(Exception e){
             throw e;
         }
         return specialite;   
     }
     public int getSalaireSpecialiteService( Connection con, int duree ) throws Exception{
         int salaire =0;
         
          String sql = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try{
            sql = "SELECT salaire from v_specialiteService";
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            if(res.next()){
               salaire = (res.getInt(1)*duree)/(22*24*60) ;
               
            }
        }catch(Exception e){
             throw e;
         }
         return salaire;   
     }
     public int getSalaireSpecialiteService(int idService, int duree) throws Exception{
         int salaire = 0;
         try{
             salaire= getSalaireSpecialiteService(idService, duree);
         }
         catch(Exception e){
             throw e;
         }
         return salaire;
     }
     
     public int getMasokarena(int salaire, int piece, int pourcentage)throws Exception{
         int masokarena = 0;
         try{
            masokarena = (salaire + piece) + ((salaire+piece)*(pourcentage/100)) ; 
         }
         catch(Exception e){
             throw e;
         }
         return masokarena;
         
     }
}
