/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import BaseModele.BaseModele;
import java.sql.Date;

/**
 *
 * @author LEGION
 */
public class Employe extends BaseModele{
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private Genre genre;
    private int salaire;
}
