/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;
import obj.*;
import connex.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author PC
 */
public class Specialisation extends Funcobj{
    int idSpecialisation;
    int idEmp;
    int idSpecialite;

    String[] spec;

    public Specialisation( int idEmp, String[] spec) {
        this.idEmp = idEmp;
        this.spec = spec;
    }

    public String[] getSpec() {
        return spec;
    }

    public void setSpec(String[] spec) {
        this.spec = spec;
    }
    public Specialisation() {
    }

    public Specialisation(int idEmp, int idSpecialite) {
        this.idEmp = idEmp;
        this.idSpecialite = idSpecialite;
    }

    public int getIdSpecialisation() {
        return idSpecialisation;
    }

    public void setIdSpecialisation(int idSpecialisation) {
        this.idSpecialisation = idSpecialisation;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(int idSpecialite) {
        this.idSpecialite = idSpecialite;
    }
    
    public void ajoutSpecialisation() throws Exception{
        Connexion c = new Connexion();
        Connection con = c.connect();
        //try {   
        for(int i=0;i<this.spec.length;i++){
            String sql = "INSERT INTO SPECIALISATION (idemp, idspecialite) values('"+this.getIdEmp()+"','"+this.getSpec()[i]+"')";
            Statement stat = con.createStatement();
            int x = stat.executeUpdate(sql);
            // String query = "INSERT INTO specialisation (idemp,idspecialite) values (?,?)"; 
            // PreparedStatement ps = con.prepareStatement(query);
            // ps.setInt(1,this.getIdEmp());
            // ps.setInt(2,Integer.parseInt(this.getSpec()[i]));
            // ps.executeQuery();
            // ps.close();
            }
            // } catch (Exception e) {
            // }
        con.close();
    }
}
