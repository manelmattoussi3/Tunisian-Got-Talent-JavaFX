/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Candidat;
import models.Sponsoring;
import models.SponsoringModel;
//import service.SponsoringService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Formation;
import service.SponsoringService;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class TestController implements Initializable {

    public Label candidat;
    SponsoringModel sponsoringModel;
    @FXML
    private TableColumn<SponsoringModel, String> description;
    @FXML
    private TableColumn<SponsoringModel, String> date_debut;
    @FXML
    private TableColumn<SponsoringModel, String> date_fin;
    public Sponsoring sponsoring;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnConsulter;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    AnchorPane leb;
    private ListView<Sponsoring> listView;
    private DbConnection dc;
    @FXML
    private TableView<SponsoringModel> table;
    /**
     * Initializes the controller class.
     */
    public ObservableList<SponsoringModel> data;
    @FXML
    private TableColumn<SponsoringModel, String> txtDateDebut;
    @FXML
    private TableColumn<SponsoringModel, String> txtDateFin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
        AfficheSponsoring();
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
    private void btnBackAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AccueilFXML.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Welcom !!");
        primaryStage.show();
    }

    // public void AfficheSponsoring() {
    /* // ListView<SponsoringModel> listView = new ListView<SponsoringModel>();
        SponsoringService dc = new SponsoringService();
        ArrayList<Sponsoring> listSponsoring = dc.afficherSponsoring();

        ObservableList<Sponsoring> list = FXCollections.<Sponsoring>observableArrayList(listSponsoring);
        // ListView<SponsoringModel> listView = new ListView<SponsoringModel>(list);

        listView.setItems(list);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sponsoring>() {
            @Override
            public void changed(ObservableValue<? extends Sponsoring> observable, Sponsoring oldValue, Sponsoring newValue) {
                sponsoring = listView.getSelectionModel().getSelectedItem();
            }
        }
        );

    }*/

  public void loadSponsoring(int idSpons) {
        System.out.println(idSpons);
        SponsoringService sponsoringService = new SponsoringService();
     
   sponsoring = sponsoringService.getSponsoring(idSpons);}
    public void AfficheSponsoring() {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select S.id, S.description , S.type , C.nom as nomCandidat ,  S.image ,S.date_debut,S.date_fin  from Sponsoring as S , Candidat as C where  S.Candidat_id=C.id ");
            while (rs.next()) {
                data.add(new SponsoringModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)));

            }

        } catch (SQLException ex) {
            System.out.println("erreur" + ex);
        }

        description.setCellValueFactory(new PropertyValueFactory<>("nomCandidat"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("type"));
        txtDateDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        txtDateFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        //table.setItems(null);
        table.setItems(data);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SponsoringModel>() {
            
            @Override
            public void changed(ObservableValue<? extends SponsoringModel> observable, SponsoringModel oldValue, SponsoringModel newValue) {
                sponsoringModel  = table.getSelectionModel().getSelectedItem();
            }} 
        );
    }

    @FXML
    private void btnConsulterAction(ActionEvent event) {
        if (sponsoringModel != null) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Consulter.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            primaryStage.setTitle("Consulter Publicité");
            ConsulterController pac =loader.getController();
            pac.loadSponsoring(sponsoringModel.getIdSpons());
            primaryStage.show();

        }
    }

    @FXML
    private void btnAjouterAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AjoutSponsoringFXL.fxml"));

        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Ajouter Publicité");
        AjoutSponsoringFXLController pac = loader.getController();
        primaryStage.show();
    }
 @FXML
    private void btnModifierAction(ActionEvent event) {
        if (sponsoringModel != null) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ModifierSponsoring.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            primaryStage.setTitle("Modifier Publicité");
            ModifierSponsoringController pac =loader.getController();
            pac.loadSponsoring(sponsoringModel.getIdSpons());
            primaryStage.show();
        }
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) {
        if (sponsoringModel != null) {
            SponsoringService sponsoringService = new SponsoringService();
            sponsoringService.removeSponsoring(sponsoringModel.getIdSpons());
            AfficheSponsoring();
        }
    }

     @FXML
    private void btnLineChartAction(ActionEvent event) {
          ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/LineChart.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Bar Chart");
        LineChartController pac =loader.getController();
        primaryStage.show();
    }
}
