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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Desktop;
import java.io.File;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;
import models.Conseil;
import models.ConseilModel;
import service.Serviceconseil;
import utils.DbConnection;
import models.ConseilModel;
import models.SponsoringModel;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class ConseilViewController implements Initializable {


    @FXML
    private ImageView I1;
    @FXML
    private ImageView I6;
    @FXML
    private ImageView I8;
    @FXML
    private ImageView I16;
    @FXML
    private ImageView I17;
    
    @FXML
    private TableView<ConseilModel> tableconseil;
    @FXML
    private TableColumn<ConseilModel, String> descriptionco;
    @FXML
    private TableColumn<ConseilModel, String> categori;
    @FXML
    private TableColumn<ConseilModel, String> categori1;
    @FXML
    private TableColumn<ConseilModel, String> coa;
    @FXML
    private TableColumn<ConseilModel, String> image;
    public ObservableList<ConseilModel> List;
    private DbConnection dc;
    @FXML
    private Button minimiseButton;

   
    private FileChooser filechooser;
    private File file;
    private Stage stage;
    @FXML
    private AnchorPane anchorpane;
    private Desktop desktop = Desktop.getDesktop();
    private Image image1;
    private ImageView imageView;
    @FXML
    private Label nomutlisateur;
    @FXML
    private Button btndeconnextion;
    public ConseilModel conseilmodel;
    public ObservableList<Conseil> Listconseil;
    @FXML
    private Label reclamation;
    @FXML
    private TextField textfieldsearch;
    @FXML
    private TextField textfieldimage;
    @FXML
    private Label label1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // search by
        dc = new DbConnection();
        AfficheConseil();

        //c pour le liste de choix categorie
       /* choicebox.getItems().add("Musique");
        choicebox.getItems().add("Peinture");
        choicebox.getItems().add("Danse");
        //pour coach
        combobox2.getItems().add("Mohamed Ali Tounsi");
        combobox2.getItems().add("Amel Safouni");
        combobox2.getItems().add("Yassine Ibrahim");
        //pour candidat
        combobox1.getItems().add("imran khan");
        combobox1.getItems().add("Achrekt ahmed");
        //combobox.getItems().add("Majed Mohandes");*/
        dc = new DbConnection();
        Image minimizeimg;
        try {
            minimizeimg = new Image(new FileInputStream("src\\img\\26.png"));
            minimiseButton.setGraphic(new ImageView(minimizeimg));
        } catch (FileNotFoundException e) {
        }
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac")
        );

    }

    public void AfficheConseil() {
        try {
            Connection conn = dc.Connect();
            List = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select Co.id ,Coa.nom as nomCoach,C.nom as nomCandidat,Co.description ,Co.categoricons, Co.image  FROM conseil as Co , Candidat as C , Coach as Coa where  Co.Candidat_id=C.id AND Co.Coach_id=Coa.id");
            while (rs.next()) {
                List.add(new ConseilModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }

        } catch (SQLException ex) {
            System.out.println("erreur" + ex);
        }
        coa.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
        categori.setCellValueFactory(new PropertyValueFactory<>("nomCandidat"));
        descriptionco.setCellValueFactory(new PropertyValueFactory<>("descriptioncons"));

        categori1.setCellValueFactory(new PropertyValueFactory<>("Categoricons"));
        image.setCellValueFactory(new PropertyValueFactory<>("Imagecons"));
        tableconseil.setItems(null);
        tableconseil.setItems(List);
        tableconseil.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConseilModel>() {

            @Override
            public void changed(ObservableValue<? extends ConseilModel> observable, ConseilModel oldValue, ConseilModel newValue) {
                conseilmodel = tableconseil.getSelectionModel().getSelectedItem();
            }
        }
        );
    }

    public void myFunction(String Text) {
        nomutlisateur.setText(Text);
    }

    @FXML
    public void AjouterConseil(ActionEvent event) {
        /*if (validatefield()) {
            String descriptioncons = textfielddescription.getText();
            String Imagecons = textfieldimage.getText();
            String Categoricons = (String) choicebox.getValue();
            Conseil cons  = new Conseil();

            cons.setDescriptioncons(descriptioncons);
            cons.setImagecons(Imagecons);
            // con.setCategoricons(Categoricons);

            //int status = Serviceconseil.save(conseilmodel);

            //if (status > 0) {
               /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajouter Conseil");
                alert.setHeaderText("Information");
                alert.setContentText("Conseil bien ajouté");
                alert.showAndWait();
            //} else {

               /* Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter Conseil");
                alert.setHeaderText("Information");
                alert.setContentText("Conseil non ajouté");
                alert.showAndWait();*/

        // }
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AjouterConseil.fxml"));

        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Ajouter conseil");
        AjouterConseilController pac = loader.getController();
        primaryStage.show();
    }

    //retour vers formation 
    @FXML
    public void passeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/ExampleTableView.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    //retour vers Accueil

    @FXML
    public void modifeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/Accueil.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();

    }

    //retour vers statistique 
    @FXML
    public void StatistiqueScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/StatistiqueView.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    //retour vers evaluation 

    @FXML
    public void DashBord(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }

    /*  @FXML
    private void ViderConseil(ActionEvent event) {
        textfielddescription.clear();
        combobox1.setValue(null);
        choicebox.setValue(null);
        combobox2.setValue(null);

        textfieldimage.clear();
    }*/
    @FXML
    private void handleButtonAction(ActionEvent event) {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

  /*  public void getConseil(ActionEvent event) throws IOException, ParseException {
        String sid = textfieldid.getText();
        int idcons = Integer.parseInt(sid);

        Conseil con = Serviceconseil.getConseilid(idcons);
        textfielddescription.setText(con.getDescriptioncons());

        textfieldimage.setText(con.getImagecons());

        // choicebox.setValue(con.getCategoricons());
    }
*/
    @FXML
    public void deleteConseil(ActionEvent event) throws IOException {
        /*String sid = textfieldid.getText();
        int idcons=0;*/

        // if (Status > 0) {
        if (conseilmodel != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Voulez-vous vraiment supprimer ce conseil ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                Serviceconseil serviceconseil = new Serviceconseil();
                serviceconseil.delete(conseilmodel.getIdcons());
                AfficheConseil();
            }
            // Status =serviceconseil.delete(conseilmodel.getIdcons());
            //if (Status > 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Supprimer Conseil");
            alert1.setHeaderText("Information");
            alert1.setContentText(" Conseil bien supprimé");
            alert1.showAndWait();
            /*try {
                        String sql = "DELETE FROM `conseil` WHERE id='" + idcons + "'";
                        Connection conn = Serviceconseil.Connect();
                        PreparedStatement stat;
                        stat = (PreparedStatement) conn.prepareStatement(sql);
                        //stat.setInt(1, idcons);
                        st = stat.executeUpdate();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

                /*} else {

           /* Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Supprimer Conseil");
            alert.setHeaderText("Information");
            alert.setContentText("Conseil non supprimé");
            alert.showAndWait();

        }*/
        }
    }

  /*  private boolean validatefield() {
        if (textfielddescription.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Validate Fields");
            alert1.setHeaderText(null);
            alert1.setContentText("Please Enter Into The Fields");
            alert1.showAndWait();

            return false;
        }

        {

            return true;

        }

    }*/

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

    @FXML
    private void handleButton6Action(MouseEvent event) {
    }

    @FXML
    private void btnCandidatAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ListeCandidats.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        ListeCandidatsController listeCadidatsController = loader.getController();
        listeCadidatsController.AfficheCandidat();
        //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
        primaryStage.setTitle("Liste des Candidats");
        primaryStage.show();
    }

    @FXML
    private void btnChercherAction(ActionEvent event) {
        if (!textfieldsearch.getText().isEmpty()) {
            coa.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
            categori.setCellValueFactory(new PropertyValueFactory<>("nomCandidat"));
            descriptionco.setCellValueFactory(new PropertyValueFactory<>("descriptioncons"));
            image.setCellValueFactory(new PropertyValueFactory<>("Categoricons"));
            categori1.setCellValueFactory(new PropertyValueFactory<>("Imagecons"));

            Serviceconseil sc = new Serviceconseil();
            List = sc.chercherConseil(textfieldsearch.getText());
            if (List != null) {
                tableconseil.setItems(List);
                tableconseil.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText(textfieldsearch.getText() + " n'existe pas !");
                textfieldsearch.clear();
                alert.showAndWait();
                tableconseil.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez taper un nom d'un organisme");
            textfieldsearch.clear();
            alert.showAndWait();
            tableconseil.refresh();
        }
    }

    @FXML
    private void btnActualiserAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ConseilView.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        ConseilViewController etp = loader.getController();
        etp.AfficheConseil();
        //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
        primaryStage.setTitle("conseil");
        primaryStage.show();
    }

    @FXML
    private void handleButton9Action(MouseEvent event) throws MessagingException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditUserCoatch.fxml"));

            Parent root = (Parent) loader.load();
            EditUserCoatchController utcontroller = loader.getController();
            utcontroller.myFunction(nomutlisateur.getText());

            System.out.println(nomutlisateur.getText());

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @FXML
    private void handleButton10Action(MouseEvent event) throws MessagingException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReclamationCoatch.fxml"));

            Parent root = (Parent) loader.load();
            reclamationCoatchController utcontroller = loader.getController();
            utcontroller.myFunction(nomutlisateur.getText());

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(reclamationCoatchController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void Chat(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/serveurchat.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }

}
