/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import static controllers.EvaluationViewController.id;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Candidat;
import models.Formation;
import models.Rate;
import service.CandidatService;
import service.DbConnectinfo;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class StatistiqueViewController implements Initializable {

    private ListView<String> listview;
    @FXML
    private Label nomutlisateur;
    @FXML
    private Button btndeconnextion;
    String destinationUrl = "";
    @FXML
    private JFXComboBox CmbCandidat;
    @FXML
    private JFXComboBox Cmbformation;
     public static int id;
     ObservableList<String> listNomCondidat;
    List<Candidat> listCondidat;
    public Candidat candidat;
     ObservableList<String> listNomFormation;
    List<Formation> listFormation;
    public Formation formation;
    @FXML
    private TextField avis;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      /*  Image minimizeimg;
        try {
            minimizeimg = new Image(new FileInputStream("src\\img\\new-file.png"));
            btn1.setGraphic(new ImageView(minimizeimg));
        } catch (FileNotFoundException e) {
        }
         Image img;
        try {
            minimizeimg = new Image(new FileInputStream("src\\img\\h.png"));
            btn2.setGraphic(new ImageView(minimizeimg));
        } catch (FileNotFoundException e) {
        }*/
      loadData();
       loadFormation(); 
    } 
    //retour vers formation
    
   //retour vers Accueil
    public void modifeScreenButtonPushed(ActionEvent event) throws IOException {
    Parent tableviewParent =FXMLLoader.load(getClass().getResource("/fxml/Accueil.fxml"));
    Scene tableviewScene =new Scene(tableviewParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(tableviewScene);
    window.show();
    
    } 
   /*
    @FXML
      public void Button1Action(ActionEvent event) throws IOException{
         /* FileChooser fc = new FileChooser();
          fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF File","*.pdf"));
          File selectedFile = fc.showOpenDialog(null);
          if(selectedFile!=null){
          listview.getItems().add(selectedFile.getName());
          }
          System.out.println("file is not valide ");*/
        /*   Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder prefix = new StringBuilder("");
        for (int i = 0; i < 5; i++) {
            prefix.append(letters.charAt(random.nextInt(letters.length())));
        }
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            fileChooser.setTitle("Open resource file");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(" Files PDF", "*.pdf"));
            File path = selectedFile.getAbsoluteFile();
            destinationUrl = "src/PDF/" + prefix + selectedFile.getName();
            Path destinationPath = Paths.get(destinationUrl);
            Path sourcePath = Paths.get(selectedFile.getAbsolutePath());
            Files.copy(sourcePath, destinationPath);
        }
          }*/
      
 /*   @FXML
      public void Button2Action(ActionEvent event){
       FileChooser fc = new FileChooser();
        List <File> selectedFile = fc.showOpenMultipleDialog(null);
          if(selectedFile!=null){
              for(int i=0;i<selectedFile.size();i++){
          listview.getItems().add(selectedFile.get(i).getAbsolutePath());
          }
          System.out.println("file is not valide ");
          }
      
      
      
      }*/

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void handleButton6Action(MouseEvent event) {
    }

       @FXML
    public void btnAjouterAction(ActionEvent event) throws IOException {
       // String av = avis.getText();
 
        Candidat selectedCandidat = listCondidat.get(CmbCandidat.getSelectionModel().getSelectedIndex());
        Formation selectedFormation = listFormation.get(Cmbformation.getSelectionModel().getSelectedIndex());
        Rate cons = new Rate(id,selectedCandidat.getId(),selectedFormation.getId(),avis.getText());
        DbConnectinfo dbc = new DbConnectinfo();
        dbc.rate(cons);
        //int status = serviceconseil.save(cons);

        //  if (status > 0) {
       
 
    
      /*  }else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Information");
        alert.setContentText("le note doit étre entre 0 et 10");
        alert.showAndWait();}
       */
      
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Participer à une Formation");
                alert.setHeaderText("Information");
                alert.setContentText("Félicitation vous étes participer à une Formation");
                alert.showAndWait();
    
 ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/DashBoard.fxml"));
        AnchorPane frame = loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
       // EspaceFormationController AfficheConseilController = loader.getController();

      //  AfficheConseilController.initialize(url, rb);
        primaryStage.setTitle("Liste des Evaluations");
        primaryStage.show();
         
       
        
        
}
    
    
     public void loadData() {
        CandidatService candidatService = new CandidatService();
        listCondidat = candidatService.listerNomCandidat();
        listNomCondidat = FXCollections.observableArrayList();
        listNomCondidat.addAll(listCondidat.stream().map(condidat -> condidat.getNom()).collect(Collectors.toList()));
        CmbCandidat.setItems(listNomCondidat);
    }
      public void loadFormation() {
        DbConnectinfo dbc = new DbConnectinfo();
       
        
        listFormation = dbc.listerNomFormation();
        listNomFormation = FXCollections.observableArrayList();
        listNomFormation.addAll(listFormation.stream().map(formation -> formation.getDescription()).collect(Collectors.toList()));
        Cmbformation.setItems(listNomFormation);
    }
  /*  private void addratioo(MouseEvent event) throws SQLException{
        Rate r =new Rate();
        DbConnectinfo dbc = new  DbConnectinfo();
        dbc.rate(r);
        
        r.getId();
        //r.setCandidat_id(can.getId());
        //r.setFormation_id(fo.getId());
        r.setAvis(Value);
    }*/
      private boolean validatefield() {
        if (Cmbformation.getValue().equals(null) |  CmbCandidat.getValue().equals(null)) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Valider les champs");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez entrer dans les champs");
            alert1.showAndWait();
            
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
}
      
      
       



