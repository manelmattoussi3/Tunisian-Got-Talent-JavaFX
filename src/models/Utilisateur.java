/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Baleina
 */
public class Utilisateur {
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String sexe;
    private int numTel;
    private String email;
    private int idUtilisateur;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String dateNaissance, String sexe, int numTel, String email, int idUtilisateur) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.numTel = numTel;
        this.email = email;
        this.idUtilisateur = idUtilisateur;
    }

    

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public int getNumTel() {
        return numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    

    @Override
    public String toString() {
        return "Utilisateur{" + "nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe + ", numTel=" + numTel + ", email=" + email + '}';
    }
    
     public String toString(Double d) {
        return d.toString();
    }
    
    
}
