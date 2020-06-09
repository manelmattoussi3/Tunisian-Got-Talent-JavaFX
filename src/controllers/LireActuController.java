/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.UserSession;
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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import service.DbConnectinfo;

/**
 * FXML Controller class
 *
 * @author Baleina
 */
public class LireActuController implements Initializable {

    @FXML
    private ListView<String> listviewA;

    @FXML
    private Button valider;

    int idA, idU;

    private ObservableList<String> items = FXCollections.observableArrayList();
    // private ObservableList<String> items1 = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */

    // Connection con,con1;
    @FXML
    private Button tfbutton2;
    @FXML
    private TextField rechercher;
    @FXML
    private TextArea txtlireActu;
    String textfield;

    /*  public LireActuController()
    {
        con = Conn.getInstance().getCnx();
      
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void recupereIdActualite() {
        try {
            textfield = rechercher.getText();

            // System.out.println(textfield);
            java.sql.Connection conn = DbConnectinfo.Connect();
            String query = "select * from categorie where nomCategorie=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, textfield);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                idA = rs.getInt(1);
                System.out.println(idA);
                //  titre = rs.getString("titre");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onTableItemSelect(MouseEvent event) {
    }

    @FXML
    private void consulterInformation(MouseEvent event) {
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            String query = "select * from actualite where titre=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, (String) listviewA.getSelectionModel().getSelectedItem());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                idU = rs.getInt(1);
                System.out.println(idU);
                //titre = rs.getString("titre");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            java.sql.Connection conn = DbConnectinfo.Connect();

            List<models.Actualite> ls = new ArrayList<models.Actualite>();
            PreparedStatement pt = conn.prepareStatement("SELECT contenu FROM actualite WHERE idActualite='" + idU + "'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                // System.out.println(rs.getString("contenu"));
                txtlireActu.setText(rs.getString("contenu"));

            }

            for (models.Actualite a : ls) {
                System.out.println(a.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void selectionIdA(MouseEvent event) {
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            String query = "select * from actualite where titre=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, (String) listviewA.getSelectionModel().getSelectedItem());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                idA = rs.getInt(1);
                System.out.println(idA);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void validerNote(ActionEvent event) {
    }

    @FXML
    private void rechercherActualite(ActionEvent event) {
        if (rechercher.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vérification");

            // alert.setHeaderText("Results:");
            alert.setContentText("Le champs rechercher doit contenir une valeur");

            alert.showAndWait();
        } else {
            items.clear();
            recupereIdActualite();

            try {
                java.sql.Connection conn = DbConnectinfo.Connect();
                PreparedStatement pt = conn.prepareStatement("SELECT actualite.titre FROM actualite,categorie WHERE categorie.idcategorie = actualite.idcategorie AND categorie.idcategorie=" + idA + "");
                ResultSet rs = pt.executeQuery();
                while (rs.next()) {
                    //items.add((rs.getString(1)));
                    items.add((rs.getString(1)));
                    listviewA.setItems(items);

                }

                pt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
