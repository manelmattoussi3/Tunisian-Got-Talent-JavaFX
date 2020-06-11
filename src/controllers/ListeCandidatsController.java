/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Candidat;
import service.CandidatService;
//import dd.XLSXWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.XLSXWriter;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class ListeCandidatsController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    @FXML
    private TableColumn<Candidat, String> nomColumn;
    @FXML
    private TableColumn<Candidat, String> prenomColumn;
    @FXML
    private Button btnExit;
     @FXML
    private Button btnBack;
     @FXML
    private  TextField  txtChercher;
    public ObservableList<Candidat> listCandidat = FXCollections.observableArrayList();
     
    @FXML
    private TableView<Candidat> table ; 
    @FXML
    private AnchorPane label;
    @FXML
    private Label lbl;
    @FXML
    private Button btnChercher;
    @FXML
    private Button btnExport;
    @FXML
    private Button btnRéinitialiser;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        AfficheCandidat();
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
     @FXML
    public void passeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/accueilcoach.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    
    @FXML
    private void btnChercherAction(ActionEvent event) {
        if (!txtChercher.getText().isEmpty()) {
            nomColumn.setCellValueFactory(new PropertyValueFactory<Candidat, String>("nom"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<Candidat, String>("prenom"));
            
            CandidatService sc = new CandidatService();
            listCandidat = sc.chercherCandidat(txtChercher.getText());
            if (listCandidat != null) {
                table.setItems(listCandidat);
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
   
     public void AfficheCandidat() {
        CandidatService dc = new CandidatService();
        ArrayList<Candidat> listCandidats = dc.afficherCandidat();
        listCandidat = FXCollections.<Candidat>observableList(listCandidats);
        nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory("prenom"));
        table.setItems(listCandidat);
        
                   /* FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ViewFx/ListeCandidats.fxml"));
                    Parent p = loader.getRoot();
                    Stage primaryStage = new Stage();
                    primaryStage.setScene(new Scene(p));
                    primaryStage.setTitle("Affichage Des Candidats");
                    ListeCandidatsController pac = loader.getController();
                    pac.AfficheCandidat(candidat);
                    primaryStage.show();*/
      
     }

    @FXML
    private void btnExportAction(ActionEvent event) {
        Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz";         
        StringBuilder prefix = new StringBuilder("") ;
        for(int i = 0; i < 5; i++) {
            prefix.append(letters.charAt(random.nextInt(letters.length())));
        } 
        XLSXWriter.generateXLSX("src/Documents/" + prefix + "Candidats.xlsx", new ArrayList<Candidat>(listCandidat));
    }

    @FXML
    private void btnRéinitialiserAction(ActionEvent event) {
         ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ListeCandidats.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ListeCandidatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            ListeCandidatsController listeCadidatsController = loader.getController();
            listeCadidatsController.AfficheCandidat();
            //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
            primaryStage.setTitle("Liste des Candidats");
            primaryStage.show();
    }
}
