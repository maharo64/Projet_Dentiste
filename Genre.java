/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;
import obj.*;
import connex.*;
import java.sql.*;
/**
 *
 * @author PC
 */
public class Genre extends Funcobj{
    
    int id;
    String sexe;

    public Genre() {
    }

    public Genre(int id, String sexe) {
        this.id = id;
        this.sexe = sexe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public Object[] getGenre() throws Exception{
        Connexion c = new Connexion();
        Connection con = c.connect();
        Genre g = new Genre();
        Object[] result = g.selectMethod(null,con);
        Genre[] tab = new Genre[result.length];
        for (int i=0;i< result.length;i++){
            tab[i] = (Genre) result[i];
        }
        return tab;
    }
    
}
