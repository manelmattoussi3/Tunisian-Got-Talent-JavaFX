/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Khoubaib
 */
public class DemandeSponsoringReponse {
    private int idDemandeSponsoring;
    private String description;
    private String type;
    private String nomCandidat;  
    private String date ;
        
    public DemandeSponsoringReponse(){}

    public DemandeSponsoringReponse(int idDemandeSponsoring, String description, String type, String nomCandidat, String date) {
        this.idDemandeSponsoring = idDemandeSponsoring;
        this.description = description;
        this.type = type;
        
        this.nomCandidat = nomCandidat;
        this.date = date;
        
    }

    public int getIdDemandeSponsoring() {
        return idDemandeSponsoring;
    }

    public void setIdDemandeSponsoring(int idDemandeSponsoring) {
        this.idDemandeSponsoring = idDemandeSponsoring;
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

    public String getNomCandidat() {
        return nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DemandeSponsoringReponse{" + "idDemandeSponsoring=" + idDemandeSponsoring + ", description=" + description + ", type=" + type + ", nomCandidat=" + nomCandidat + ", date=" + date + '}';
    }

   
    }


