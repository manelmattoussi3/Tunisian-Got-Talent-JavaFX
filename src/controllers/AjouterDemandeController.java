/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Candidat;
import models.DemandeSponsoring;
import service.CandidatService;
import service.DemandeService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class AjouterDemandeController implements Initializable {
     List l;
    int condidatId;
    int sponsorId;
    public Candidat candidat;
    public static int id;
    ObservableList<String> listNomCondidat;
    List<Candidat> listCondidat;
    
    @FXML
    private Label leb1;
    @FXML
    private Label leb2;
    @FXML
    private Label leb3;
    @FXML
    private Label leb5;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtDescription;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtType;
    @FXML
    private ComboBox CmbCandidat;
    @FXML
    private Label leb;
    @FXML
    private Button btnAjouterDemande;
    @FXML
    private Button btnAnnulerDemande;
   
    @FXML
    private Button btnBack;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            loadData();
       
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
    private void btnAjouterDemandeAction(ActionEvent event) throws IOException {
     Candidat selectedCandidat = listCondidat.get(CmbCandidat.getSelectionModel().getSelectedIndex());
        DemandeSponsoring sponsoring = new DemandeSponsoring(id, txtDescription.getText(), txtType.getText(),0, txtDate.getValue().toString(), selectedCandidat.getId());
        DemandeService demandeService = new DemandeService();
        demandeService.addDemande(sponsoring);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Demande de publicité");
        alert.setHeaderText(null);
        alert.setContentText("Votre demande a été envoyer ");
        alert.showAndWait();
       
    }
    
     public void loadData(){
        CandidatService candidatService = new CandidatService();
        listCondidat = candidatService.listerNomCandidat();
        listNomCondidat = FXCollections.observableArrayList();
        listNomCondidat.addAll(listCondidat.stream().map(condidat -> condidat.getNom()).collect(Collectors.toList()));
        CmbCandidat.setItems(listNomCondidat);
     }
     @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AfficherDemande.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        AfficherDemandeController afficherDemandeController = loader.getController();
        afficherDemandeController.AfficheDemande();
        primaryStage.setTitle("Lister Demande");
        primaryStage.show();
    }

    @FXML
    private void btnAnnulerDemandeAction(ActionEvent event) {
        txtDescription.setText("");
        txtType.setText("");
        
    }
}
