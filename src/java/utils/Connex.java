/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LEGION
 */
public class Connex {
     public static Connection getConnex() throws Exception{
        Connection con = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dentiste1", "postgres", "root");
        }
        catch(Exception e){
            throw e;
        }
        return con;
    }
}
