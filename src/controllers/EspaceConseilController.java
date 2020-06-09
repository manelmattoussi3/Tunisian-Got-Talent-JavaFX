/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import service.Serviceconseil;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import models.Coach;
import models.Conseil;
import models.ConseilModel;

//import piiii.ConsulterController;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class EspaceConseilController implements Initializable {

    private FileChooser filechooser;
    private File file;
    private Stage stage;
    @FXML
    private AnchorPane anchorpane;
    private Desktop desktop = Desktop.getDesktop();
    private Image image1;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView i2;
    @FXML
    private ImageView i1;
    @FXML
    private ImageView i3;
    @FXML
    private Button notifbutton;

    @FXML
    private TableView<ConseilModel> tableconseil;
    @FXML
    private TableColumn<ConseilModel, String> descriptionco;
    @FXML
    private TableColumn<ConseilModel, String> categori;
    @FXML
    private TableColumn<ConseilModel, String> image;
    @FXML
    private TableColumn<ConseilModel, String> coa;
    public ObservableList<ConseilModel> List;
    private DbConnection dc;
    public Conseil cons;
public ConseilModel conseilmodel;
    @FXML
    private Button btnConsulter;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
        AfficheConseil();
        filechooser = new FileChooser();
       filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac")
        );
        Image minimizeimg;
        try {
            minimizeimg = new Image(new FileInputStream("src\\img\\bell.png"));
            notifbutton.setGraphic(new ImageView(minimizeimg));
        } catch (FileNotFoundException e) {
        }

    }
 public void loadConseil(int idcons) {
        System.out.println(idcons);
        Serviceconseil conseilService = new Serviceconseil();
     
   cons= conseilService.getConseil(idcons);}
    @FXML
    public void DashBord(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    private void handleBrowser(ActionEvent event) {
        stage = (Stage) anchorpane.getScene().getWindow();
        file = filechooser.showOpenDialog(stage);
        /*  try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(ConseilViewController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        if (file != null) {
            System.out.println("" + file.getAbsolutePath());
            image1 = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), true, true);
            imageView.setImage(image1);
            imageView.setPreserveRatio(true);
        }

    }

    /* public void Envoyermsg(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Envoyer un message");
        alert.setHeaderText("Information");
        alert.setContentText("Message Envoyé");
        alert.showAndWait();

    }*/
    @FXML
    public void quiter(ActionEvent event) {
        System.exit(0);

    }
    @FXML
    private void Notifiactionaction(ActionEvent event) {
        Notifications.create()
                .title("Notification")
                .text("vous avez un nouveau conseil").darkStyle().position(Pos.TOP_CENTER)
                .showWarning();
    }

     @FXML
    private void btnConsulterAction(ActionEvent event) {

        if (conseilmodel!= null) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Consulterconseil.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Parent p = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.setTitle("consulter conseil");
            ConsulterconseilController pac = loader.getController();
            pac.loadConseil(conseilmodel.getIdcons());
            stage.show();
        }
    }
   
    public void AfficheConseil() {
        try {
            Connection conn = dc.Connect();
            List = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select Co.id ,C.nom as nomCandidat,Co.image,Co.description ,Co.categoricons, Coa.nom as nomCoach  FROM conseil as Co , Candidat as C , Coach as Coa where  Co.Candidat_id=C.id AND Co.Coach_id=Coa.id");
            while (rs.next()) {
                List.add(new ConseilModel(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6)));

            }

        } catch (SQLException ex) {
            System.out.println("erreur" + ex);
        }
        coa.setCellValueFactory(new PropertyValueFactory<>("nomCandidat"));
        //categori1.setCellValueFactory(new PropertyValueFactory<>("Imagecons"));
        descriptionco.setCellValueFactory(new PropertyValueFactory<>("descriptioncons"));
        image.setCellValueFactory(new PropertyValueFactory<>("Imagecons"));
        categori.setCellValueFactory(new PropertyValueFactory<>("Categoricons"));
        tableconseil.setItems(null);
        tableconseil.setItems(List);
        tableconseil.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConseilModel>() {
            
            @Override
            public void changed(ObservableValue<? extends ConseilModel> observable, ConseilModel oldValue, ConseilModel newValue) {
               conseilmodel  =  tableconseil.getSelectionModel().getSelectedItem();
            }} 
        );
    }

    /*@FXML
    public void passeScreenButtonPushed(ActionEvent event) throws IOException {
        if (conseilmodel != null) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Consulterconseil.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            primaryStage.setTitle("Consulter Conseil");
            ConsulterconseilController pac =loader.getController();
            pac.loadConseil(conseilmodel.getIdcons());
            primaryStage.show();

        }
    }*/

    @FXML
    public void Chat(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/Client.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
}
