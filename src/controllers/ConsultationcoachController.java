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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Participant;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class ConsultationcoachController implements Initializable {

    @FXML
    private TableView<Participant> table;
    @FXML
    private TableColumn<Participant,String> txtnom;
    @FXML
    private TableColumn<Participant,String> txtprenom;
    @FXML
    private TableColumn<Participant,String> txtmail;
    @FXML
    private TableColumn<Participant,String> txtproposition;
  public ObservableList<Participant> data;
   public DbConnection dc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        loadScreenButton();
    }    
   public void loadScreenButton() {
            
        try {
           Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT `nom`, `prenom`, `mail`, `contenu` FROM `proposition` ");
            //"select Co.id ,C.nom as nomCandidat,Coa.nom as nomCoach,Co.description ,Co.categoricons, Co.image  FROM conseil as Co , Candidat as C , Coach as Coa where  Co.Candidat_id=C.id AND Co.Coach_id=Coa.id"
            while (rs.next()) {
                data.add(new Participant(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
                
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur" + ex);
        }
        
        txtnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        txtprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        txtmail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
        txtproposition.setCellValueFactory(new PropertyValueFactory<>("Proposition"));
       
        table.setItems(null);
        table.setItems(data);
      
    } 
    @FXML
    private void btnCandidatAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/accueilcoach.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
       // ListeCandidatsController listeCadidatsController = loader.getController();
       // listeCadidatsController.AfficheCandidat();
        //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
        primaryStage.setTitle("Liste des Candidats");
        primaryStage.show();
    }
}
