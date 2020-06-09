/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.Coach;

import service.CoachService;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class ListeCoachController implements Initializable {

    @FXML
    private AnchorPane label;
    @FXML
    private Button btnExit;
    @FXML
    private Label lbl;
    @FXML
    private Button btnBack1;
    @FXML
    private Button btnChercher;
    @FXML
    private TextField txtChercher;
    @FXML
    private Button btnRéinitialiser;
    @FXML
    private Label labelt;
    @FXML
    private TableColumn<Coach, String> textnom;
    @FXML
    private TableColumn<Coach, String> textprenom;
   
    @FXML
    private TableView<Coach> table;
    public ObservableList<Coach> listCoachs = FXCollections.observableArrayList();
    private DbConnection dc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficheCoach();
    }

    @FXML
    private void btnExitAction(ActionEvent event) {
        Stage primaryStage = (Stage) btnExit.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    private void btnChercherAction(ActionEvent event) {
        if (!txtChercher.getText().isEmpty()) {
            textnom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom"));
            textprenom.setCellValueFactory(new PropertyValueFactory<Coach, String>("prenom"));
             
           //  textspecialite.setCellValueFactory(new PropertyValueFactory<Coach, String>("specialite"));
          
            CoachService cs = new CoachService();
            listCoachs = cs.chercherCoach(txtChercher.getText());
            if (listCoachs != null) {
                table.setItems(listCoachs);
                table.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText(txtChercher.getText() + " n'existe pas !");
                txtChercher.clear();
                alert.showAndWait();
                table.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez taper un nom d'un organisme");
            txtChercher.clear();
            alert.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void btnRéinitialiserAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ListeCoach.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            ListeCoachController lcs = loader.getController();
            lcs.AfficheCoach();
            //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
            primaryStage.setTitle("Liste des Coachs");
            primaryStage.show();
    }
    

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/DashBoard.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Bienvenue");

        primaryStage.show();
    }

    public void AfficheCoach() {
        CoachService cs = new CoachService();
        ArrayList<Coach> listCoach = cs.afficherCoach();
        listCoachs = FXCollections.<Coach>observableList(listCoach);
        textnom.setCellValueFactory(new PropertyValueFactory("nom"));
        textprenom.setCellValueFactory(new PropertyValueFactory("prenom"));
       
        table.setItems(listCoachs);

    }

}
