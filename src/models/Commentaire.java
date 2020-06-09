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
public class Commentaire {
    private int idCommentaire;
    private String titre;
    private String contenu;
    private String dateAjout;
    private int actualite_id;
    private int utilisateur;

    public Commentaire() {
    }

    public Commentaire(int idCommentaire, String titre, String contenu, String dateAjout) {
        this.idCommentaire +=1;
        this.titre = titre;
        this.contenu = contenu;
        this.dateAjout = dateAjout;
        
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public int getActualite_id() {
        return actualite_id;
    }
    

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setTitre(String titre) {
        
        
            this.titre = titre;
       
        
    }

    public void setContenu(String contenu) {
        
            this.contenu = contenu;
       
        
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public void setActualite_id(int actualite_id) {
        this.actualite_id = actualite_id;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", titre=" + titre + ", contenu=" + contenu + ", dateAjout=" + dateAjout + ", actualite_id=" + actualite_id + '}';
    }
    
    

   
}
