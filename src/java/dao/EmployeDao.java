/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modele.Employe;
import modele.Special;
import utils.Connex;

/**
 *
 * @author LEGION
 */
public class EmployeDao {
            public static void addEmploye(Employe employe, Connection con) throws Exception{
        PreparedStatement ps = null;
        String sql = null;
        try{
            sql = "INSERT INTO employe(nom, prenom, dateNaissance, genre)"
                    + "VALUES(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, employe.getNom());
            ps.setString(2, employe.getPrenom());
            ps.setDate(3, employe.getDateNaissance());
            ps.setInt(4, employe.getGenre().getId());
            ps.executeUpdate();
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(ps!=null){
                ps.close();
            }
        }
    }
    
    public static void addEmploye(Employe employe) throws Exception{
        Connection con = null;
        try{
            con = Connex.getConnex();
            addEmploye(employe, con);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(con!=null){
                con.close();
            }
        }
    }
    
    
        public static int getLastIdEmploye(Connection con) throws Exception{
        int idEmploye  = 0;
        String sql = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try{
            sql = "SELECT id FROM employe ORDER BY id DESC LIMIT 1";
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            if(res.next()){
               idEmploye = res.getInt(1);
            }
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(res!=null){
                res.close();
            }
            if(ps!=null){
                ps.close();
            }
        }
        return idEmploye;
    }
    
    public static int getLastIdEmploye() throws Exception{
        int idEmploye = 0;
        Connection con = null;
        try{
            con = Connex.getConnex();
            idEmploye = getLastIdEmploye(con); 
        }
        catch(Exception e){
            throw e;
        }
        
        finally{
            if(con!=null){
                con.close();
            }
        }
        return idEmploye;
    }
    
    
}
