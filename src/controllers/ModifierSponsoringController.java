/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Candidat;
import models.Sponsoring;
import service.CandidatService;
import service.SponsoringService;
import java.io.File;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.time.LocalDate;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class ModifierSponsoringController implements Initializable {

    private Candidat candidat;
   
    @FXML
    private Button btnBack;
    @FXML
    private Button btnExit;
    @FXML
   private Button btnChoisirImage; 
   private File file;
    @FXML
    private AnchorPane leb;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtType;
    @FXML
    private ComboBox CmbCandidat;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAnnuler;
     public static int id;
    ObservableList<String> listNomCondidat;
    List<Candidat> listCondidat;
    List l;
    String destinationUrl = "";
    public Sponsoring newSponsoring ; 
    public Sponsoring sponsoring ; 
    @FXML
    private Label er1;
    @FXML
    private Label er2;
    @FXML
    private Label er3;
    @FXML
    private Label er4;
    //private DatePicker txtDateDebut;
    //private DatePicker txtDateFin;
    @FXML
    private TextField txtdatedebut;
    @FXML
    private TextField txtdatefin;


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
    private void btnBackAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/test.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        TestController afficherSponsoringController = loader.getController();
        afficherSponsoringController.AfficheSponsoring();
        primaryStage.setTitle("Lister Publicité");
        primaryStage.show();
    }
       
        public void loadData(){
        CandidatService candidatService = new CandidatService();
        listCondidat = candidatService.listerNomCandidat();
        listNomCondidat = FXCollections.observableArrayList();
        listNomCondidat.addAll(listCondidat.stream().map(condidat -> condidat.getNom()).collect(Collectors.toList()));
        CmbCandidat.setItems(listNomCondidat);
    }

    @FXML
    private void btnChoisirImage(ActionEvent event) throws IOException {
          Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz";         
        StringBuilder prefix = new StringBuilder("") ;
        for(int i = 0; i < 5; i++) {
            prefix.append(letters.charAt(random.nextInt(letters.length())));
        } 
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            fileChooser.setTitle("Open resource file");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png"));
            File path = selectedFile.getAbsoluteFile();
            destinationUrl = "src/Photo/"+ prefix +selectedFile.getName();
            Path destinationPath = Paths.get(destinationUrl);
            Path sourcePath = Paths.get(selectedFile.getAbsolutePath());
            Files.copy(sourcePath , destinationPath);
        }
    }

    @FXML
    private void btnModifierAction(ActionEvent event) throws IOException {
     
         
      int test = 0;

        if (txtDescription.getText().isEmpty() || (txtDescription.getText().matches("[a-zA-Z]+")==false)) {
            er1.setText("Ce champ est obligatoire");
       // Alert alert = new Alert(Alert.AlertType.WARNING);
      // alert.setTitle("Description");
       // alert.setHeaderText(null);
       // alert.setContentText(" Ce champ est obligatoire");
        //alert.showAndWait();
            test -=1;
        } else {
            er1.setText("");
            test +=1;
        }
        if (txtType.getText().isEmpty() || (txtType.getText().matches("[a-zA-Z]+")==false)) {
            er2.setText("Ce champ est obligatoire");
       // Alert alert = new Alert(Alert.AlertType.WARNING);
       //alert.setTitle("Type");
        //alert.setHeaderText(null);
        //alert.setContentText(" Ce champ est obligatoire");
       // alert.showAndWait();
            test -=1;
        } else {
            er2.setText("");
            test +=1;
        }
        
        if (CmbCandidat.getSelectionModel().isEmpty()) {
           er3.setText("Ce champ est obligatoire");
      // Alert alert = new Alert(Alert.AlertType.WARNING);
      // alert.setTitle("Type");
       // alert.setHeaderText(null);
       // alert.setContentText("Nom Candidat");
       // alert.showAndWait();
            test -=1;
        } else {
           er3.setText("");
            test +=1;
        }
         
        if( btnChoisirImage.getText().isEmpty()){
              
            Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz";         
        StringBuilder prefix = new StringBuilder("browser") ;
        for(int i = 0; i < 5; i++) {
            prefix.append(letters.charAt(random.nextInt(letters.length())));
        } 
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            fileChooser.setTitle("Open resource file");
            
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png"));
            File path = selectedFile.getAbsoluteFile();
            destinationUrl = "src/Photo/"+ prefix +selectedFile.getName();
            Path destinationPath = Paths.get(destinationUrl);
            Path sourcePath = Paths.get(selectedFile.getAbsolutePath());
            Files.copy(sourcePath , destinationPath);
            } else {

                btnChoisirImage.setText("sélection d'image annulée.");

            }
           
        }     
       if( test == 3 ){  
           Sponsoring s=new Sponsoring();
            Candidat selectedCandidat = listCondidat.get(CmbCandidat.getSelectionModel().getSelectedIndex());
            Sponsoring newSponsoring = new Sponsoring(sponsoring.getId(), txtDescription.getText(), txtType.getText(), selectedCandidat.getId(), sponsoring.getId(), destinationUrl,sponsoring.getDate_debut(),sponsoring.getDate_fin() );
            SponsoringService sponsoringService = new SponsoringService();
            sponsoringService.update(newSponsoring);
             ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/test.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        TestController afficherSponsoringController = loader.getController();
        afficherSponsoringController.AfficheSponsoring();
        primaryStage.setTitle("Lister Publicité");
        primaryStage.show();
       }       
    }  
    
    
    @FXML
    private void btnAnnulerAction(ActionEvent event) {
        txtDescription.setText("");
        txtType.setText("");
        btnChoisirImage.setText("browser");
        
        
    }
    
    public void loadSponsoring(int idSpons) {
        System.out.println(idSpons);
        SponsoringService sponsoringService = new SponsoringService();
        sponsoring = sponsoringService.getSponsoring(idSpons);
        txtDescription.setText(sponsoring.getDescription());
        txtType.setText(sponsoring.getType());
       int indexOfCandidat = -1;
        for (int i = 0; i < listCondidat.size(); i++) {
            if(listCondidat.get(i).getId() == sponsoring.getCandidat_id()) {
                indexOfCandidat = i;
            }
        }
        if(indexOfCandidat != -1) {
            CmbCandidat.getSelectionModel().select(indexOfCandidat);
        }
        btnChoisirImage.setText(sponsoring.getImage());
      // txtdatedebut.setValue((sponsoring.getDate_debut()));
     // txtdatefin.setValue((sponsoring.getDate_fin()))
     String datedebut = txtdatedebut.getText();
        String datefin = txtdatefin.getText();
      sponsoring.setDate_debut(datedebut);
        sponsoring.setDate_fin(datefin);
    }
}


 