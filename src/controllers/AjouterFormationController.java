/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Formation;
import service.DbConnectinfo;
import java.awt.Desktop;

import static java.awt.SystemColor.window;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import models.Candidat;
import models.Coach;
import service.CandidatService;
import sun.awt.DesktopBrowse;
import service.CoachService;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class AjouterFormationController implements Initializable {
    
    @FXML
    private TextField textfielddescription;
    @FXML
    private DatePicker dp1;
    @FXML
    private DatePicker dp;
    @FXML
    private TextField textfieldnbr;
    private ComboBox<String> combobox2;
    //@FXML
    // private ComboBox<String> combobox1;
    @FXML
    private Label labe11;
    @FXML
    private Label labe12;
    @FXML
    private Label labe13;
    @FXML
    private Label labe9;
    @FXML
    private Label labe14;
    @FXML
    private Label labe15;
    // private ObservableList<String> list = FXCollections.observableArrayList("Mohamed Ali Tounsi", "Amel Safouni", "Yassine Ibrahim");
  //  private ObservableList<String> list1 = FXCollections.observableArrayList("Musique", "Peinture", "Danse");
    private int Id;
    @FXML
    private Label label20;
    Stage stage;
    public Coach coach;
    public static int id;
    List<Coach> listCoachs;
    ObservableList<String> listNomCoach;
    List<Coach> listCo;
    @FXML
    private ComboBox CmbCoach;
    @FXML
    private TextField textfieldcategori;

    // private final Desktop desktop=new Desktop();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CmbCoach.setItems(listNomCoach);
       // combobox2.setItems(list1);
        loadData();
        
    }
    
    @FXML
    private void ShowDate(ActionEvent event) {
        LocalDate ld = dp.getValue();
        LocalDate ld1 = dp1.getValue();
    }
    
    public void insertDataAction(ActionEvent event) throws IOException{
        /* filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("All Files", "*.*")
        );
        browse = new Button("Browse");
        browse.setOnAction(e -> {
            file = filechooser.showOpenDialog(stage);
            if (file != null) {
                
            }

        });*/
    /*    
        if (validatefield()) {
            String Description = textfielddescription.getText();
            LocalDate Date_Debut = dp.getValue();
            LocalDate Date_Fin = dp1.getValue();
            // int Nbr_Place = Integer.parseInt(textfieldnbr.getText());
            String Nbr_Place = textfieldnbr.getText();
            String Categorie = combobox2.getValue();
            String nomCoach =(String) CmbCoach.getValue();
            String date = Date_Debut.toString();
            String date1 = Date_Fin.toString();
            
            Formation fom = new Formation();
            
            fom.setDescription(Description);
            fom.setDate_Debut(date);
            fom.setDate_Fin(date1);
            fom.setNbr_Place(Nbr_Place);
            fom.setCategorie(combobox2.getValue());
            int status = DbConnectinfo.save(fom);
            
            if (status > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajouter Formation");
                alert.setHeaderText("Information");
                alert.setContentText("Formation bien ajouté");
                alert.showAndWait();
            } else {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter Formation");
                alert.setHeaderText("Information");
                alert.setContentText("Formation non ajouté");
                alert.showAndWait();
                
            }
        }*/
   
   if (validatefield()&& validate()&&validatenbr()&&validatenbr7()) {
     Coach selectedCoach = listCoachs.get(CmbCoach.getSelectionModel().getSelectedIndex());
        Formation formation = new Formation(id, selectedCoach.getIdcoach(),textfielddescription.getText(),dp.getValue().toString(),dp1.getValue().toString(),textfieldnbr.getText(),textfieldcategori.getText());
        DbConnectinfo dbc = new  DbConnectinfo();
        dbc .save(formation);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajouter Formation");
                alert.setHeaderText("Information");
                alert.setContentText("Formation bien ajouté");
                alert.showAndWait();
           
     }
       /* ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ExampleTableView.fxml"));
       /// AnchorPane frame = loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
       // ExampleTableViewController afficherFormationController = loader.getController();

       // afficherFormationController.loadScreenButton();
        primaryStage.setTitle("Liste des formations");
        primaryStage.show();*/
    }
        
    
    
    @FXML
    public void vider() {
        // textfieldid.clear();
        textfielddescription.clear();
        dp.setValue(null);
        dp1.setValue(null);
        //combobox2.setValue(null);
        CmbCoach.setValue(null);
       textfieldnbr.clear();
       textfieldcategori.clear();
    }
    
    private boolean validatefield() {
        if (textfielddescription.getText().isEmpty() | textfieldnbr.getText().isEmpty() | dp.getEditor().getText().isEmpty() | dp1.getEditor().getText().isEmpty()| textfieldcategori.getText().isEmpty()|CmbCoach.getSelectionModel().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Valider les champs");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez remplir tous les champs");
            alert1.showAndWait();
            
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
     private boolean validate() {
         if( (textfielddescription.getText().length() < 2 )|(textfieldcategori.getText().length() < 2) ){
         Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("Veuillez remplir plus que 2 lettres");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
      private boolean validatenbr() {
     
         if(textfieldnbr.getText().matches("[a-z]") &&  !textfieldnbr.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+-")){
         Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("le nombre de place doit étre numerique");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
        private boolean validatenbr7() {
     
         if(!textfieldnbr.getText().matches("^[0-9]*$") ){
         Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("le nombre de place doit étre positif");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
    public void loadData() {
        CoachService coachservice = new CoachService();
        listCoachs = coachservice.listerNomCoach();
        listNomCoach = FXCollections.observableArrayList();
        listNomCoach.addAll(listCoachs.stream().map(coach -> coach.getNom()).collect(Collectors.toList()));
        CmbCoach.setItems(listNomCoach);
    }
    /* public void replaceText() {
        String oldValue = dp.getText;
        if (!text.matches("[a-z]") && !text.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) {
            super.replaceText(start, end, text);
        }
        if (getText().length() > 2 ) {
            setText(oldValue);
        }
    }*/

    @FXML
    private void insertData(ActionEvent event) {
    }
}
