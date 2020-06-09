/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.AjouterConseilController.id;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Candidat;
import models.Coach;
import models.Conseil;
import models.ConseilModel;
import service.CandidatService;
import service.CoachService;
import service.Serviceconseil;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class AjouterConseilController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtType;

    @FXML
    private ComboBox CmbCandidat;
    @FXML
    private ComboBox CmbCoach;
    @FXML
    private Button btnChoisirImage;
    private File file;
    List l;
    int condidatId;
    int coachId;
    public Candidat candidat;
    public Coach coach;
    public static int id;
    ObservableList<String> listNomCondidat;
    List<Candidat> listCondidat;
    ObservableList<String> listNomCoach;
    List<Coach> listCoach;
    @FXML
    private Label leb1;
    private Image image;
    String destinationUrl = "";
    @FXML
    private Label theLabel1;
    @FXML
    private Label leb11;
    @FXML
    private Label leb31;
    @FXML
    private Label leb51;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtimg;

    @FXML
    private Label leb311;
 List<Coach> listCoachs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DropShadow shadow = new DropShadow();
        loadData();
        loadCoach();
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
    public void btnAjouterAction(ActionEvent event) throws IOException {
        if(validatefield()&&validate()){
        Candidat selectedCandidat = listCondidat.get(CmbCandidat.getSelectionModel().getSelectedIndex());
        Coach selectedCoach = listCoachs.get(CmbCoach.getSelectionModel().getSelectedIndex());
        Conseil cons = new Conseil(id,txtDescription.getText(), txtType.getText(), selectedCandidat.getId(),selectedCoach.getIdcoach(),destinationUrl);
        Serviceconseil serviceconseil = new Serviceconseil();
        serviceconseil.save(cons);
        //int status = serviceconseil.save(cons);

        //  if (status > 0) {
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter Conseil");
        alert.setHeaderText("Information");
        alert.setContentText("Conseil bien ajouté");
        alert.showAndWait();*/

        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ConseilView.fxml"));
        AnchorPane frame = loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        ConseilViewController AfficheConseilController = loader.getController();

        AfficheConseilController.AfficheConseil();
        primaryStage.setTitle("Liste des Conseils");
        primaryStage.show();
    }
    }
     private boolean validatefield() {
        if (txtDescription.getText().isEmpty() | txtType.getText().isEmpty() |CmbCoach.getSelectionModel().isEmpty()|  CmbCandidat.getSelectionModel().isEmpty()) {
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
    public void loadData() {
        CandidatService candidatService = new CandidatService();
        listCondidat = candidatService.listerNomCandidat();
        listNomCondidat = FXCollections.observableArrayList();
        listNomCondidat.addAll(listCondidat.stream().map(condidat -> condidat.getNom()).collect(Collectors.toList()));
        CmbCandidat.setItems(listNomCondidat);
    }

    public void loadCoach() {
        CoachService coachservice = new CoachService();
        listCoachs = coachservice.listerNomCoach();
        listNomCoach = FXCollections.observableArrayList();
        listNomCoach.addAll(listCoachs.stream().map(coach -> coach.getNom()).collect(Collectors.toList()));
        CmbCoach.setItems(listNomCoach);
    }

    @FXML
    private void btnChoisirImageAction(ActionEvent event) throws IOException {
        Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder prefix = new StringBuilder("");
        for (int i = 0; i < 5; i++) {
            prefix.append(letters.charAt(random.nextInt(letters.length())));
        }
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            fileChooser.setTitle("Open resource file");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png"));
            File path = selectedFile.getAbsoluteFile();
            destinationUrl = "src/Images/" + prefix + selectedFile.getName();
            Path destinationPath = Paths.get(destinationUrl);
            Path sourcePath = Paths.get(selectedFile.getAbsolutePath());
            Files.copy(sourcePath, destinationPath);
        }
    }

    /* @FXML
    public void btnAnullerAction(ActionEvent event) {
        txtDescription.setText("");
        txtType.setText("");
        btnChoisirImage.setText("Browser");
    }*/
    @FXML
    private void btnExitAction(ActionEvent event) {
    }

    @FXML
    private void ViderConseil(ActionEvent event) {
        txtDescription.clear();
        CmbCandidat.setValue(null);

        txtType.clear();
    }
     private boolean validate() {
         if( (txtDescription.getText().length() < 2 )|(txtType.getText().length() < 2) ){
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

}
