package modele;

import obj.*;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.Calendar;
import utils.Connex;

public class Emp extends Funcobj {

    int idE;
    String nom;
    String prenom;
    int idGenre;
    Date dateNaissance;
    int idNiveauEtude;
    int salaire;

    public Emp() {
    }

    public Emp(String nom, String prenom, int genre, Date dateNaissance, int niveauEtude,int sal) throws Exception{
        this.nom = nom;
        this.prenom = prenom;
        this.setIdGenre(genre);
        this.setDateNaissance(dateNaissance);
        this.setIdNiveauEtude(niveauEtude);
        this.setSalaire(sal);
    }

    
    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
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

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int genre) {
        this.idGenre=genre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) throws Exception{
        if(estMajeur(dateNaissance)==true){
            this.dateNaissance = dateNaissance;
        }else{
            throw new Exception ("mbola mineur");
        }
    }

    public int getIdNiveauEtude() {
        return idNiveauEtude;
    }

    public void setIdNiveauEtude(int niveauEtude) {
        this.idNiveauEtude=niveauEtude;
    }
    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }
    
    public void ajoutEmp() {
        try {
        Connection con = Connex.getConnex();
        String query = "INSERT INTO employe(nom,prenom,idGenre,naissance,idNiveauEtude,salaire) values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,this.getNom());
        ps.setString(2,this.getPrenom());
        ps.setInt(3,this.getIdGenre());
        ps.setDate(4,new java.sql.Date(this.getDateNaissance().getYear(),this.getDateNaissance().getMonth(),this.getDateNaissance().getDay()));
        ps.setInt(5,this.getIdNiveauEtude());
        ps.setInt(6,this.getSalaire());
        ps.executeQuery();
        ps.close();
        con.close();
        } catch (Exception e) {
            
        }
        
    }
    public boolean estMajeur(Date d1){
        Calendar c = Calendar.getInstance();
        Date d = c.getTime();
        if(d.getYear()-d1.getYear()>=18){
            return true;
        }
        return false;
    }
    public int getId() throws Exception{
        Connection c = Connex.getConnex();
        Statement stat = c.createStatement();
        ResultSet result = stat.executeQuery("select max(idemp) idemp from employe");
        int i = 0;
        while(result.next()){
            i = result.getInt(1);
        }
        return i;
    }
}
