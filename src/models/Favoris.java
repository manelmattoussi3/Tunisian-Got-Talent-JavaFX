/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Baleina
 */
public class Favoris {
    private int idFavoris;
    private String titre;
    private int utilisateur_id;
    private int actualite_id;

    public Favoris(int idFavoris, String titre) {
        this.idFavoris = idFavoris;
        this.titre = titre;
    }

    public Favoris(int idFavoris, String titre, int utilisateur_id, int actualite_id) {
        this.idFavoris = idFavoris;
        this.titre = titre;
        this.utilisateur_id = utilisateur_id;
        this.actualite_id = actualite_id;
    }
    

    public Favoris() {
    }

    public int getActualite_id() {
        return actualite_id;
    }

    public void setActualite_id(int actualite_id) {
        this.actualite_id = actualite_id;
    }
    

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }
    
    

    public int getIdFavoris() {
        return idFavoris;
    }

    public void setIdFavoris(int idFavoris) {
        this.idFavoris = idFavoris;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    

    @Override
    public String toString() {
        return "Favoris{" + "idFavoris=" + idFavoris + ", titre=" + titre + '}';
    }
        
}
