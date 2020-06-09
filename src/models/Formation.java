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
public class Formation {

    private int Id;
    private String Description;
    private String Date_Debut;
    private String Date_Fin;
    private String Nbr_Place;
    private String Categorie;
     private String pdf;
      private String nomCoach;
 private int Coach_id;
    public Formation() {
    }

    public Formation(int Id,String nomCoach, String Description, String pdf,String Date_Debut, String Date_Fin, String Nbr_Place , String Categorie) {
        this.Id=Id;
        this.nomCoach=nomCoach;
        this.Description = Description;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.Nbr_Place = Nbr_Place;
       this.Categorie=Categorie;
       this.pdf=pdf;
       
    }

    public Formation(int Id, String Description, String Date_Debut, String Date_Fin, String Nbr_Place, String Categorie, String pdf, int Coach_id) {
        this.Id = Id;
        this.Description = Description;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.Nbr_Place = Nbr_Place;
        this.Categorie = Categorie;
        this.pdf = pdf;
        this.Coach_id = Coach_id;
    }

    public Formation(int Id, int Coach_id,String Description, String Date_Debut, String Date_Fin, String Nbr_Place, String Categorie) {
        this.Id = Id;
         this.Coach_id = Coach_id;
        this.Description = Description;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.Nbr_Place = Nbr_Place;
        this.Categorie = Categorie;
    }

    public int getCoach_id() {
        return Coach_id;
    }

    public void setCoach_id(int Coach_id) {
        this.Coach_id = Coach_id;
    }

    public String getNomCoach() {
        return nomCoach;
    }

    public void setNomCoach(String nomCoach) {
        this.nomCoach = nomCoach;
    }

    

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    

    

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(String Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public String getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(String Date_Fin) {
        this.Date_Fin = Date_Fin;
    }

    public String getNbr_Place() {
        return Nbr_Place;
    }

    public void setNbr_Place(String Nbr_Place) {
        this.Nbr_Place = Nbr_Place;
    }

    @Override
    public String toString() {
        return "Formation{" + "Id=" + Id + ", Description=" + Description + ", Date_Debut=" + Date_Debut + ", Date_Fin=" + Date_Fin + ", Nbr_Place=" + Nbr_Place + ", Categorie=" + Categorie + ", pdf=" + pdf + ", nomCoach=" + nomCoach + '}';
    }

   

   


    

}
