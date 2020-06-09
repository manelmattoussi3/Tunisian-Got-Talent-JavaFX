/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.DemandeSponsoring;
import models.DemandeSponsoringReponse;
import service.DemandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class AfficherDemandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnExit;
    @FXML
    private Button btnBack;
    @FXML
    private TableColumn<DemandeSponsoringReponse, String> descriptionColumn;
    @FXML
    private TableColumn<DemandeSponsoringReponse, String> typeColumn;
    @FXML
    private TableColumn<DemandeSponsoringReponse, String> candidatsColumn;
    @FXML
    private TableColumn<DemandeSponsoringReponse, String> dateColumn;
    @FXML
    private TableView<DemandeSponsoringReponse> table ; 
    @FXML
    private Label leb;
    @FXML
    private Button btnSupprimerDemande;
    @FXML
    private Button btnAjouterDemande;
    DemandeSponsoringReponse demandeSponsoringReponse;
     public DemandeSponsoring DemandeSponsoring ; 
   
    DemandeSponsoringReponse DemandeSponsoringReponse;
    @FXML
    private Button btnInvitation;
     private DbConnection dc;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {  
        dc = new DbConnection();
        AfficheDemande();
         DropShadow shadow = new DropShadow();

        btnExit.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                shadow.setColor(Color.RED);
                btnExit.setEffect(shadow);
            }
        });

        btnExit.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                btnExit.setEffect(null);
            }
        });
     

    }

    @FXML
    private void btnExitAction(ActionEvent event) {
        Stage primaryStage = (Stage) btnExit.getScene().getWindow();
        primaryStage.close();
  
    } 
    
    
     @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AccueilFXML.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Bienvenue");
        
        primaryStage.show();
    }
   public void AfficheDemande() {
       Connection conn = dc.Connect();
        DemandeService dc = new DemandeService();
        ArrayList<DemandeSponsoringReponse> listDemandeReponse = dc.afficherDemande();
        ObservableList<DemandeSponsoringReponse> list = FXCollections.<DemandeSponsoringReponse>observableList(listDemandeReponse);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        candidatsColumn.setCellValueFactory(new PropertyValueFactory("nomCandidat"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        table.setItems(list);
        
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DemandeSponsoringReponse>() {
            
        @Override
        public void changed(ObservableValue<? extends DemandeSponsoringReponse> observable, DemandeSponsoringReponse oldValue, DemandeSponsoringReponse newValue) {
        demandeSponsoringReponse = table.getSelectionModel().getSelectedItem();
            }} 
        );
   }
 

    @FXML
    private void btnAjouterDemandeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AjouterDemande.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Ajouter Demande");
        AjouterDemandeController pac =loader.getController();
        primaryStage.show();
        
    }
    
    
    
     public void loadDemandeSponsoring(int idDemandeSponsoring) {
         System.out.println(idDemandeSponsoring);
        DemandeService demandeSponsoringService = new DemandeService();
        DemandeSponsoring = demandeSponsoringService.getDemandeSponsoring(idDemandeSponsoring); 
   }  
     @FXML
    private void btnSupprimerDemandeAction(ActionEvent event) {
            if(demandeSponsoringReponse != null){
            DemandeService DemandeSponsoringService = new DemandeService();
            DemandeSponsoringService.removeDemande(demandeSponsoringReponse.getIdDemandeSponsoring());
            AfficheDemande();
       }
    }  

    @FXML
    private void btnInvitationAction(ActionEvent event) {
         ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Invitation.fxml"));
        
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Invitation");
        InvitationController pac =loader.getController();
        primaryStage.show();
    }
}

