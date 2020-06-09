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
public class ConseilModel {

    private int idcons;
    private String descriptioncons;
    private String Imagecons;
    private String nomCandidat;
    private String nomCoach;
    private String Categoricons;

    public ConseilModel() {
    }

    public ConseilModel(int idcons, String nomCoach, String nomCandidat, String descriptioncons, String Categoricons, String Imagecons) {
        this.idcons = idcons;
        this.nomCoach = nomCoach;
        this.nomCandidat = nomCandidat;
        this.descriptioncons = descriptioncons;
        this.Categoricons = Categoricons;
        this.Imagecons = Imagecons;

    }

    public ConseilModel(int idcons, String descriptioncons, String Imagecons, String Categoricons) {
        this.idcons = idcons;
        this.descriptioncons = descriptioncons;
        this.Imagecons = Imagecons;
        this.Categoricons = Categoricons;
    }

    public String getCategoricons() {
        return Categoricons;
    }

    public void setCategoricons(String Categoricons) {
        this.Categoricons = Categoricons;
    }

    public String getNomCoach() {
        return nomCoach;
    }

    public void setNomCoach(String nomCoach) {
        this.nomCoach = nomCoach;
    }

    public int getIdcons() {
        return idcons;
    }

    public void setIdcons(int idcons) {
        this.idcons = idcons;
    }

    public String getDescriptioncons() {
        return descriptioncons;
    }

    public void setDescriptioncons(String descriptioncons) {
        this.descriptioncons = descriptioncons;
    }

    public String getImagecons() {
        return Imagecons;
    }

    public void setImagecons(String Imagecons) {
        this.Imagecons = Imagecons;
    }

    public String getNomCandidat() {
        return nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    @Override
    public String toString() {
        return "ConseilModel{" + "idcons=" + idcons + ", descriptioncons=" + descriptioncons + ", Imagecons=" + Imagecons + ", nomCandidat=" + nomCandidat + ", nomCoach=" + nomCoach + ", Categoricons=" + Categoricons + '}';
    }

    

}
