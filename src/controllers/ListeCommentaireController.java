/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import models.Commentaire;
import models.Utilisateur;
//import Service.Conn;
import service.ServiceActualite;
import service.ServiceCategorie;
import service.ServiceUtilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author Baleina
 */
public class ListeCommentaireController implements Initializable {

//Connection con;
    @FXML
    private ListView<String> listview;
    @FXML
    private Button tfbutton;

    int idA;
    @FXML
    private ListView<String> txtlireComm;
    @FXML
    private Button tfbutton2;
    @FXML
    private TextArea txtlireComm1;
    /*public ListeCommentaireController()
    {
        con = Conn.getInstance().getCnx();
    }*/
    private ObservableList<String> items = FXCollections.observableArrayList();
    private ObservableList<String> items1 = FXCollections.observableArrayList();
    private DbConnection dc;

    /**
     * Initializes the controller class.
     */
    public void listerCommentaire() {

        try {
            Connection conn = dc.Connect();
            PreparedStatement pt = conn.prepareStatement("SELECT * FROM actualite");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                //items.add((rs.getString(1)));
                items.add((rs.getString("titre")));

                //System.out.println(rs.getString(2));
            }

            pt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        listview.setItems(items);
        txtlireComm.setItems(items1);
        listerCommentaire();

    }

    @FXML
    private void onTableItemSelect(MouseEvent event) {
    }

    @FXML
    private void consulterInformation(MouseEvent event) {
    }

    @FXML
    private void consulterCommentaire(ActionEvent event) {

    }

    @FXML
    private void recupererId(MouseEvent event) {

        items1.clear();
        try {
            Connection conn = dc.Connect();
            String query = "select * from actualite where titre=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, (String) listview.getSelectionModel().getSelectedItem());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                idA = rs.getInt(1);
                System.out.println(idA);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection conn = dc.Connect();
            List<models.Actualite> ls = new ArrayList<models.Actualite>();
            PreparedStatement pt = conn.prepareStatement("SELECT contenu, dateAjout FROM commentaire1 WHERE actualite_id='" + idA + "'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                // System.out.println(rs.getString("contenu"));
                //items1.add((rs.getString("contenu")));
                items1.add((rs.getString("dateAjout")));
                items1.addAll(rs.getString("contenu"), rs.getString("dateAjout"));

            }

            for (models.Actualite a : ls) {
                System.out.println(a.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) {

        if (listview.getSelectionModel().isEmpty() || txtlireComm1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajouter les commentaires");
            alert.setHeaderText(null);
            alert.setContentText("Bien vouloir sélectionner une actualité ou alors saisir votre commentaire");
            alert.showAndWait();
        } else {
            Utilisateur u = new Utilisateur();

            // u.setIdUtilisateur(10);
            Commentaire c = new Commentaire();

            c.setUtilisateur(10);

            ServiceUtilisateur su = new ServiceUtilisateur();

            su.ajouterCommentaire(idA, txtlireComm1.getText(), c.getUtilisateur());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'un commentaire");
            alert.setHeaderText(null);
            alert.setContentText("Commentaire ajouté avec succès");
            alert.showAndWait();

            refreshList();
        }

    }

    public void refreshList() {

        items1.clear();
        listerC();
    }

    public void listerC() {
        try {
            Connection conn = dc.Connect();
            List<models.Actualite> ls = new ArrayList<models.Actualite>();
            PreparedStatement pt = conn.prepareStatement("SELECT contenu, dateAjout FROM commentaire1 WHERE actualite_id='" + idA + "'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                // System.out.println(rs.getString("contenu"));
                items1.add((rs.getString("contenu")));

            }

            for (models.Actualite a : ls) {
                System.out.println(a.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
