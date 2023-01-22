 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author LEGION
 */
public class ServiceDepense {
    private Materiel materiel;
    private Service service;
    private int quantite;
    float pourcentage;
    float benefice;
    
    public ArrayList<ServiceDepense> getALLSERVICEDEPENSE(){
        ArrayList<ServiceDepense> sd = new ArrayList<>();
        ArrayList< 
    }
    }
    public void setBenefice(float benefice) {
        this.benefice = benefice;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public float getBenefice() {
        return benefice;
    }

    public float getPourcentage() {
        return pourcentage;
    }
    
    
    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    
}
