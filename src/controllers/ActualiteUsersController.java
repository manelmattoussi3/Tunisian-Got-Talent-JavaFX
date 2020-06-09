/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Utilisateur;
//import Service.Conn;
import service.ServiceActualite;
import service.ServiceCategorie;
import service.ServiceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author Baleina
 */
public class ActualiteUsersController implements Initializable {

    //Connection con;
    @FXML
    private TextArea txtlireActu;
    @FXML
    private AnchorPane note;
    @FXML
    private ListView<String> listview1;
    @FXML
    private Slider slidebar;
    private final Integer INT_VALUE = 0;
    @FXML
    private Label test;
    String titre;
    int rec;
    PreparedStatement pst;
    @FXML
    private Button tfbutton1;
    @FXML
    private Button tfbutton2;
    @FXML
    private Button tfbutton12;
    @FXML
    private Button valider;
    @FXML
    private Button tfbutton21;

    /* public ActualiteUsersController()
    {
        con = Conn.getInstance().getCnx();
    }*/
    @FXML
    private ListView<String> listview;
    private ObservableList<String> items = FXCollections.observableArrayList();
    private ObservableList<String> items1 = FXCollections.observableArrayList();
    public int idA;
    private DbConnection dc;

    /**
     * Initializes the controller class.
     */
    public void recupereIdActualite() {
        try {
            Connection conn = dc.Connect();
            String query = "select * from actualite where titre=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, (String) listview.getSelectionModel().getSelectedItem());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                idA = rs.getInt(1);
                System.out.println(idA);
                titre = rs.getString("titre");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Connection conn = dc.Connect();
            List<models.Actualite> ls = new ArrayList<models.Actualite>();
            PreparedStatement pt = conn.prepareStatement("SELECT contenu FROM actualite WHERE idActualite='" + idA + "'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                // System.out.println(rs.getString("contenu"));
                txtlireActu.setText(rs.getString("contenu"));

            }

            for (models.Actualite a : ls) {
                System.out.println(a.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }

        ServiceUtilisateur su = new ServiceUtilisateur();
        Utilisateur u = new Utilisateur();

        u.setIdUtilisateur(5);

        su.ajouterHistorique(idA, u.getIdUtilisateur(), titre);
    }

    public void listerActualite() {
        try {
            Connection conn = dc.Connect();
            PreparedStatement pt = conn.prepareStatement("SELECT * FROM actualite");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                //items.add((rs.getString(1)));
                items.add((rs.getString("titre")));

                //System.out.println(rs.getString(2));
            }

            pt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void lireActualite(int idactualite) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        listview.setItems(items);
        listview1.setItems(items1);
        listerActualite();
        listerMeilleursNotes();
        slidebar.setValue(INT_VALUE);

        test.setText(new Integer(INT_VALUE).toString());

        test.textProperty().bindBidirectional(slidebar.valueProperty(), NumberFormat.getNumberInstance());

    }

    @FXML
    private void onTableItemSelect(MouseEvent event) {
    }

    @FXML
    private void consulterInformation(MouseEvent event) {

        recupereIdActualite();
    }

    private void AfficherInformation(ActionEvent event) {

        try {
            Connection conn = dc.Connect();
            List<models.Actualite> ls = new ArrayList<models.Actualite>();
            PreparedStatement pt = conn.prepareStatement("SELECT contenu FROM actualite WHERE idActualite='" + idA + "'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                // System.out.println(rs.getString("contenu"));
                txtlireActu.setText(rs.getString("contenu"));

            }

            for (models.Actualite a : ls) {
                System.out.println(a.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionNote(ActionEvent event) throws IOException {
        {
            Stage note = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/lireActu.fxml"));
            Scene scene = new Scene(root);
            note.setScene(scene);
            note.show();
        }
    }

    @FXML
    private void AfficherCommentaire(ActionEvent event) throws Exception {
        Stage comment = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/listeCommentaire.fxml"));
        Scene scene = new Scene(root);
        comment.setScene(scene);
        comment.show();
    }

    public void listerMeilleursNotes() {
        try {
            Connection conn = dc.Connect();
            PreparedStatement pt = conn.prepareStatement("SELECT titre, SUM(valeur) valeur FROM actualite, note WHERE actualite.idActualite = note.actualite_id GROUP BY titre and valeur > 20 ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                //items.add((rs.getString(1)));
                items1.add((rs.getString("titre")));

                //System.out.println(rs.getString(2));
            }

            pt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterFavoris(ActionEvent event) {

        if (listview.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout aux favoris");
            alert.setHeaderText(null);
            alert.setContentText("Bien vouloir sélectionner une actualité");
            alert.showAndWait();
        } else {
            try {
                Connection conn = dc.Connect();
                PreparedStatement pt = conn.prepareStatement("SELECT * FROM favoris");
                ResultSet rs = pt.executeQuery();
                while (rs.next()) {

                    rec = rs.getInt(4);

                }

                pt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (rec != idA) {
                TextInputDialog textinput = new TextInputDialog();

                textinput.setTitle("Entrez le titre de l'historique");

                textinput.getDialogPane().setContentText("Titre");

                Optional<String> result = textinput.showAndWait();

                result.ifPresent((name) -> {

                    TextField input = textinput.getEditor();
                    ServiceUtilisateur su = new ServiceUtilisateur();

                    Utilisateur u = new Utilisateur();

                    u.setIdUtilisateur(5);
                    su.ajouterActuFavoris(idA, input.getText(), u.getIdUtilisateur());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ajout aux favoris");
                    alert.setHeaderText(null);
                    alert.setContentText("Favoris ajouté avec succès");
                    alert.showAndWait();
                });

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout aux favoris");
                alert.setHeaderText(null);
                alert.setContentText("Article déjà existant dans les favoris");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void validerNote(ActionEvent event) {
        ServiceUtilisateur su = new ServiceUtilisateur();

        Utilisateur u = new Utilisateur();

        u.setIdUtilisateur(5);

        su.noter(idA, slidebar.getValue(), u.getIdUtilisateur());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout d'une note");
        alert.setHeaderText(null);
        alert.setContentText("Note ajouté avec succès");
        alert.showAndWait();

    }

    @FXML
    private void AfficherHistoFavo(ActionEvent event) throws IOException {
        Stage histoFavo = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/HistoFavo.fxml"));
        Scene scene = new Scene(root);
        histoFavo.setScene(scene);
        histoFavo.show();
    }
     @FXML
    public void modifeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/Accueil.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
        
    }

}
