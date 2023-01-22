
package obj;
import obj.*;
import connex.*;
import java.sql.*;
public class Specialite extends Funcobj{
    int id;
    String nom;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
    public Specialite() {
    }

    public Specialite(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    public Object[] getSpe() throws Exception{
        Connexion c = new Connexion();
        Connection con = c.connect();
        Specialite s = new Specialite();
        Object[] result = s.selectMethod(null,con);
        Specialite[] tab = new Specialite[result.length];
        for (int i=0;i< result.length;i++){
            tab[i] = (Specialite) result[i];
        }
        return tab;
    }
}
