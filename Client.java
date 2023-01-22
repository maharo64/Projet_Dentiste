package obj;
import obj.*;
import connex.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
public class Client extends Funcobj{
    int idClient;
    String nom;
    String prenom;
    String email;
    String numero;
    Date dateNaissance;

    public Client() {
    }

    public Client(int idClient, String nom, String prenom, String email, String numero, Date dateNaissance) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numero = numero;
        this.dateNaissance = dateNaissance;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public void ajoutClient(Connection con) throws Exception{
        Connexion c = new Connexion();
        con = c.connect();
        String query = "INSERT INTO client(nom,prenom,email,numero,dateNaissance) values (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,this.getNom());
        ps.setString(2,this.getPrenom());
        ps.setString(3,this.getEmail());
        ps.setString(4,this.getNumero());
        ps.setObject(5,this.getDateNaissance());
        ps.close();
        con.close();
    }
    
    public Object[] selectNouveauClient(Connection con,int idTadiavina) throws Exception{
        Connexion c = new Connexion();
        con = c.connect();
        this.setIdClient(idTadiavina);
        Object[] values = this.selectMethod(null, con);
        Client[] tab = new Client[values.length];
        for (int i = 0; i < values.length; i++) {
            tab[i] = (Client)values[i];
        }
        return tab;
    }
}
