/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Khoubaib
 */
public class DemandeSponsoring {
private int id;
private String description;
private String type;
private int etat;
private String date;
private int candidat_id;
public DemandeSponsoring(){}

    public DemandeSponsoring(int id, String description, String type, int etat, String date, int candidat_id) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.etat = etat;
        this.date=date;
        this.candidat_id=candidat_id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getCandidat_id() {
        return candidat_id;
    }

    public void setCandidat_id(int candidat_id) {
        this.candidat_id = candidat_id;
    }

   

    @Override
    public String toString() {
        return "Demandesponsoring{" + "id=" + id + ", description=" + description + ", type=" + type + ", etat=" + etat + ", date=" + date + '}';
    }
   
}

