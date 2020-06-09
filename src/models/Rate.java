/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DevelopAndroid
 */
public class Rate {
    private int id;
     private int candidat_id;
      private int formation_id;
      private String avis;

    public Rate() {
    }

    public Rate(int id, int candidat_id, int formation_id, String avis) {
        this.id = id;
        this.candidat_id = candidat_id;
        this.formation_id = formation_id;
        this.avis = avis;
    }

    public Rate(int id, int candidat_id, int formation_id) {
        this.id = id;
        this.candidat_id = candidat_id;
        this.formation_id = formation_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandidat_id() {
        return candidat_id;
    }

    public void setCandidat_id(int candidat_id) {
        this.candidat_id = candidat_id;
    }

    public int getFormation_id() {
        return formation_id;
    }

    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", candidat_id=" + candidat_id + ", formation_id=" + formation_id + ", avis=" + avis + '}';
    }
    
}
