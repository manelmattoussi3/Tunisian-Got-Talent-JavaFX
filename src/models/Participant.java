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
public class Participant {
private int id;
  private  String Nom;
   private String Prenom;
   private String Mail;
   private String Proposition;

    public Participant(String Nom, String Prenom, String Mail, String Proposition) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Proposition = Proposition;
    }

    public Participant(int id, String Nom, String Prenom, String Mail, String Proposition) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Proposition = Proposition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getProposition() {
        return Proposition;
    }

    public void setProposition(String Proposition) {
        this.Proposition = Proposition;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    @Override
    public String toString() {
        return "Participant{" + "Nom=" + Nom + ", Prenom=" + Prenom + ", Mail=" + Mail + ", Proposition=" + Proposition + '}';
    }

}
