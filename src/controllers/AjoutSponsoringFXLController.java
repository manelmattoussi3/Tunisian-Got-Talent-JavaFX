/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Candidat;
import models.Sponsoring;
import service.CandidatService;
import service.SponsorService;
import service.SponsoringService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class AjoutSponsoringFXLController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtType;

    @FXML
    private ComboBox CmbCandidat;

    @FXML
    private Button btnChoisirImage;
    private File file;
    List l;
    int condidatId;
    int sponsorId;
    public Candidat candidat;
    public static int id;
    ObservableList<String> listNomCondidat;
    List<Candidat> listCondidat;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label theLabel;
    @FXML
    private Label leb1;
    @FXML
    private Label leb2;
    @FXML
    private Label leb3;
    @FXML
    private Label leb5;
    @FXML
    private Label leb;
    private Image image;
    String destinationUrl = "";
    @FXML
    private Button btnAnnuler;
    @FXML
    private DatePicker txtDateDebut;
    @FXML
    private DatePicker txtDateFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DropShadow shadow = new DropShadow();
        loadData();
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

    @FXML
    public void btnAjouterAction(ActionEvent event) throws IOException {
        Candidat selectedCandidat = listCondidat.get(CmbCandidat.getSelectionModel().getSelectedIndex());
        Sponsoring sponsoring = new Sponsoring(id, txtDescription.getText(), txtType.getText(), selectedCandidat.getId(), 1, destinationUrl,txtDateDebut.getValue().toString(),txtDateFin.getValue().toString());
        SponsoringService sponsoringService = new SponsoringService();
        sponsoringService.add(sponsoring);

        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/test.fxml"));
        AnchorPane frame = loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        TestController afficherSponsoringController = loader.getController();

        afficherSponsoringController.AfficheSponsoring();
        primaryStage.setTitle("Liste des publicités");
        primaryStage.show();
    }

    public void loadData() {
        CandidatService candidatService = new CandidatService();
        listCondidat = candidatService.listerNomCandidat();
        listNomCondidat = FXCollections.observableArrayList();
        listNomCondidat.addAll(listCondidat.stream().map(condidat -> condidat.getNom()).collect(Collectors.toList()));
        CmbCandidat.setItems(listNomCondidat);
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

    @FXML
    public void btnAnullerAction(ActionEvent event) {
        txtDescription.setText("");
        txtType.setText("");
        btnChoisirImage.setText("Browser");
    }
}
