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
public class SponsoringReponse {
    private int idSponsoring;
    private String description;
    private String type;
    private String nomCandidat;  
    private String image ;

    public SponsoringReponse(){}
    public SponsoringReponse(int idSponsoring ,String description, String type, String nomCandidat, String image) {
        this.idSponsoring=idSponsoring;
        this.description = description;
        this.type = type;
        this.nomCandidat = nomCandidat;
        this.image = image;
    }

    public int getIdSponsoring() {
        return idSponsoring;
    }

    public void setIdSponsoring(int idSponsoring) {
        this.idSponsoring = idSponsoring;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "SponsoringReponse{" + "description=" + description + ", type=" + type + ", nomCandidat=" + nomCandidat + ", image=" + image + '}';
    }
    
    
    
    
}
