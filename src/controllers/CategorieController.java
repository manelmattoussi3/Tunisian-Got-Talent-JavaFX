/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Categorie;
//import Service.Conn;
import service.ServiceCategorie;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author Baleina
 */
public class CategorieController implements Initializable {

    ResultSet rs1;

    //Connection con;
    /* public CategorieController()
    {
        con = Conn.getInstance().getCnx();
    }*/
    @FXML
    private ListView<String> listview;
    @FXML
    private AnchorPane tfcategorie;
    @FXML
    private TextField ft;
    @FXML
    private TextField id;
    @FXML
    private Button tfbutton;
    @FXML
    private Button tfbutton2;
    @FXML
    private Button tfbutton11;
    private ObservableList<Categorie> data;
    private ObservableList<String> items = FXCollections.observableArrayList();
    String a, b;
    private DbConnection dc;
    /**
     * Initializes the controller class.
     */
    int idC;

    public void listerCategorie() {

        try {
             Connection conn = dc.Connect();
            this.data = FXCollections.observableArrayList();
            PreparedStatement pt = conn.prepareStatement("SELECT idCategorie,nomCategorie FROM categorie");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                //items.add((rs.getString(1)));
                items.add((rs.getString(2)));
                b = rs.getString(2);
                System.out.println(rs.getString(2));
            }

            pt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
        listview.setItems(items);
        listerCategorie();

    }

    @FXML
    private void onTableItemSelect(javafx.scene.input.MouseEvent event) {

        try {
             Connection conn = dc.Connect();
            String query = "select * from categorie where nomCategorie=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, (String) listview.getSelectionModel().getSelectedItem());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ft.setText(rs.getString("nomCategorie"));
                a = rs.getString("nomCategorie");
                idC = rs.getInt(1);;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clearData(ActionEvent event) {
        this.ft.setText("");

    }

    @FXML
    public void ajoutercategorie(ActionEvent event) {
        ServiceCategorie sc = new ServiceCategorie();

        Categorie c = new Categorie();

        if (ft.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Vérification");

            // alert.setHeaderText("Results:");
            alert.setContentText("Le champ catégorie doit contenir une valeur");

            alert.showAndWait();
        } else if (b.equals(ft.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'une nouvelle catégorie");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie existante");
            alert.showAndWait();
            clearData(event);
        } else {
            c.setNomCategorie(ft.getText());

            sc.ajouterCategorie(c);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'une nouvelle catégorie");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie ajouté avec succès");
            alert.showAndWait();
            clearData(event);

            refreshList();
        }

    }

    @FXML
    private void SupprimerCategorie(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression d'une catégorie");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer ?");
        // alert.showAndWait();
        clearData(event);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceCategorie sc = new ServiceCategorie();
            sc.supprimerCategorie(idC);
            refreshList();
        } else {

        }

    }

    @FXML
    private void updateCategorie(ActionEvent event) {

        if (ft.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Vérification");

            // alert.setHeaderText("Results:");
            alert.setContentText("Le champ catégorie doit contenir une valeur");

            alert.showAndWait();
        } else if (a.equals(ft.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mise à jour catégorie");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez effectué aucun changement");
            alert.showAndWait();
            clearData(event);
            refreshList();
        } else {
            ServiceCategorie sc = new ServiceCategorie();

            sc.modifierCategorie(ft.getText(), idC);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mise à jour catégorie");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie mis à jour avec succès");
            alert.showAndWait();
            clearData(event);
            refreshList();
        }

    }

    public void refreshList() {
        items.clear();
        listerCategorie();
    }

}
