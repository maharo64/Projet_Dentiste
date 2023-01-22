/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import modele.Special;
import utils.Connex;

/**
 *
 * @author LEGION
 */
public class SpecialDao {
    
        public static void addSpecial(Special special, Connection con) throws Exception{
        PreparedStatement ps = null;
        String sql = null;
        try{
            sql = "INSERT INTO cspecial(idSpecialite, idEmploye)"
                    + "VALUES(?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, special.getSpecialite().getId());
            ps.setInt(2, special.getEmploye().getId());
            
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
    
    public static void addSpecial(Special special) throws Exception{
        Connection con = null;
        try{
            con = Connex.getConnex();
            addSpecial(special, con);
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
    
}
