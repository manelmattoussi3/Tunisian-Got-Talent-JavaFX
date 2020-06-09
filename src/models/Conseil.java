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
public class Conseil {
    private int idcons;
    private String descriptioncons;
    private String Imagecons ;
    private String Categoricons;
    private int Candidat_id;
 private int Coach_id;
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

    public int getCandidat_id() {
        return Candidat_id;
    }

    public void setCandidat_id(int Candidat_id) {
        this.Candidat_id = Candidat_id;
    }

    public String getImagecons() {
        return Imagecons;
    }

    public void setImagecons(String Imagecons) {
        this.Imagecons = Imagecons;
    }

    public String getCategoricons() {
        return Categoricons;
    }

    public void setCategoricons(String Categoricons) {
        this.Categoricons = Categoricons;
    }

    public int getCoach_id() {
        return Coach_id;
    }

    public void setCoach_id(int Coach_id) {
        this.Coach_id = Coach_id;
    }

    public Conseil() {
    }

    public Conseil( int idcons,String descriptioncons ,String Imagecons,int Candidat_id,int Coach_id,String Categoricons) {
        this.idcons = idcons;
        this.descriptioncons = descriptioncons;
       this.Imagecons = Imagecons;
       this. Candidat_id= Candidat_id;
       this.Coach_id=Coach_id;
       this.Categoricons=Categoricons;
    }

    public Conseil(int idcons, String descriptioncons, String Imagecons, String Categoricons) {
        this.idcons = idcons;
        this.descriptioncons = descriptioncons;
        this.Imagecons = Imagecons;
        this.Categoricons = Categoricons;
    }

    @Override
    public String toString() {
        return "Conseil{" + "idcons=" + idcons + ", descriptioncons=" + descriptioncons + ", Imagecons=" + Imagecons + ", Categoricons=" + Categoricons + ", Candidat_id=" + Candidat_id + ", Coach_id=" + Coach_id + '}';
    }

    

    
   
    }
    

