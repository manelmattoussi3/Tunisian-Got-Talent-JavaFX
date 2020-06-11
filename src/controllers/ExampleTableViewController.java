/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import service.DbConnectinfo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import static java.util.Arrays.stream;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.DbConnection;
import models.Formation;

//import Serveur.ServeurchatController;
//import Serveur.ServeurCode;
/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class ExampleTableViewController implements Initializable {
    
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
    private ChoiceBox choicebox;
    @FXML
    private Label label1;
    //@FXML
   // private ComboBox combobox1;
    @FXML
    private TextField textfieldsearch;
    private DatePicker dp;
    private DatePicker dp1;
    //configuration de table
    @FXML
    private TableView<Formation> table;
    
    @FXML
    private TableColumn<Formation, String> description;
    @FXML
    private TableColumn<Formation, String> date_debut;
    @FXML
    private TableColumn<Formation, String> date_fin;
    @FXML
    private TableColumn<Formation, Integer> nbr_place;
    @FXML
    private TableColumn<Formation, String> Categorie;
     @FXML
    private TableColumn<Formation, String> coach;
    public ObservableList<Formation> data;
    private DbConnection dc;
    @FXML
    private Button minimiseButton;
    Stage stage;
   
    @FXML
    private TextField idf;
    private FileChooser filechooser;
    private ExtensionFilter filter;
    private MediaPlayer mediaplayer;
    private Media media;
    private MediaView mediaview;
    @FXML
    private Label nomutlisateur;
    @FXML
    private Button btndeconnextion;
    Formation formation = new Formation();
    
    
    public void ShowDate(ActionEvent event) {
        LocalDate ld = dp.getValue();
        LocalDate ld1 = dp1.getValue();
    }
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
        loadScreenButton();
        //c pour le liste de choix categorie
       /* choicebox.getItems().add("Musique");
        choicebox.getItems().add("Peinture");
        choicebox.getItems().add("Danse");
        //pour la liste de choix de searchby

        //pour coach
      /*  combobox1.getItems().add("Mohamed Ali Tounsi");
        combobox1.getItems().add("Amel Safouni");
        combobox1.getItems().add("Yassine Ibrahim");*/
        //setup the columns in the table

        Image minimizeimg;
        try {
            minimizeimg = new Image(new FileInputStream("src\\img\\26.png"));
            minimiseButton.setGraphic(new ImageView(minimizeimg));
        } catch (FileNotFoundException e) {
        }
        
    }
 public void myFunction(String Text) {
        nomutlisateur.setText(Text);
    }
 /*   public void loadformation(int id) {
        System.out.println(id);
        DbConnectinfo dci = new DbConnectinfo();
        
        formation = DbConnectinfo.getFormation(id);
    }
*/
    public void loadScreenButton() {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select F.id,Coa.nom as nomCoach,F.description,null, F.date_debut,F.date_fin,F.nbr_place,F.categorie  from formation as F,Coach as Coa where F.Coach_id=Coa.id");
            //"select Co.id ,C.nom as nomCandidat,Coa.nom as nomCoach,Co.description ,Co.categoricons, Co.image  FROM conseil as Co , Candidat as C , Coach as Coa where  Co.Candidat_id=C.id AND Co.Coach_id=Coa.id"
            while (rs.next()) {
                data.add(new Formation(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur" + ex);
        }
        
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
        nbr_place.setCellValueFactory(new PropertyValueFactory<>("Nbr_Place"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        coach.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
        table.setItems(null);
        table.setItems(data);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formation>() {
            
            @Override
            public void changed(ObservableValue<? extends Formation> observable, Formation oldValue, Formation newValue) {
                formation = table.getSelectionModel().getSelectedItem();
            }
        }
        );
    }

    //interface accueil
    @FXML
    public void modifeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/Accueil.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
        
    }
//pour interface conseil

    @FXML
    public void passeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/ConseilView.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    //interface statistique

   
    //pour interface evaluation

    @FXML
    public void DashBord(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    
    @FXML
    public void InsertScreenButtonPushed(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AjouterFormation.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ajout d'une formation");
        stage.show();
    }
    
    @FXML
    public void updateScreenButtonPushed(ActionEvent event)  {
       if (formation != null) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/UpdateDelete.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            primaryStage.setTitle("Modifier Formation");
            UpdateDeleteController pac =loader.getController();
            pac.loadFormation(formation.getId());
            primaryStage.show();
        }
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    /*public void getFormation(ActionEvent event) throws IOException, ParseException {
        // String sid = idf.getText();
        //int Id = Integer.parseInt(sid);
        String Description = null;
        Formation fom = DbConnectinfo.getFormationdescription(Description);
        LocalDate Date_Debut = dp.getValue();
        LocalDate Date_Fin = dp1.getValue();
        //String Categorie = choicebox.getValue();
        String date = Date_Debut.toString();
        String date1 = Date_Fin.toString();
        textfielddescription.setText(fom.getDescription());
        fom.setDate_Debut(date);
        fom.setDate_Fin(date1);
        textfieldnbr.setText(fom.getNbr_Place());
        Categorie.setText(fom.getCategorie());

    }*/
    
    public void openfile(ActionEvent event) throws Exception {
        filechooser = new FileChooser();
        filter = new ExtensionFilter("Choisir un Video", "*.mp4");
        filechooser.setSelectedExtensionFilter(filter);
        File file = filechooser.showOpenDialog(null);
        if (file != null) {
            mediaplayer.stop();
            media = new Media(file.toURI().toURL().toExternalForm());
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
        }
        
    }
  /*  
    private void handleButton8Action(MouseEvent event)throws MessagingException 
    {
         try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/Recamation.fxml")); 

                    Parent root = (Parent) loader.load();
                    reclamationController utcontroller = loader.getController(); 
                    utcontroller.myFunction(nomutlisateur.getText());
               
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                               
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               
                } catch (IOException ex) {
                    Logger.getLogger(reclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }*/
     @FXML
    private void handleButton9Action(MouseEvent event) throws MessagingException 
    {
          try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/editUser.fxml")); 

                    Parent root = (Parent) loader.load();
                    editUserController utcontroller = loader.getController(); 
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
    private void handleButton6Action(MouseEvent event) {
         try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }
    
    @FXML
    private void btnCandidatAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/accueilcoach.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        //ListeCandidatsController listeCadidatsController = loader.getController();
        //.AfficheCandidat();
        //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
        primaryStage.setTitle("Liste des Candidats");
        primaryStage.show();
    }
    
    @FXML
    private void btnActualiserAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ExampleTableView.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        ExampleTableViewController etp = loader.getController();
        etp.loadScreenButton();
        //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
        primaryStage.setTitle("formation");
        primaryStage.show();
    }
    
    /*@FXML
    private void btnChercherAction(ActionEvent event) {
        if (!textfieldsearch.getText().isEmpty()) {
            description.setCellValueFactory(new PropertyValueFactory<Formation, String>("Description"));
            date_debut.setCellValueFactory(new PropertyValueFactory<Formation, String>("Date_Debut"));
            date_fin.setCellValueFactory(new PropertyValueFactory<Formation, String>("Date_Fin"));
            nbr_place.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("Nbr_Place"));
            
            DbConnectinfo sc = new DbConnectinfo();
            data = sc.chercherFormation(textfieldsearch.getText());
            if (data != null) {
                table.setItems(data);
                table.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText(textfieldsearch.getText() + " n'existe pas !");
                textfieldsearch.clear();
                alert.showAndWait();
                table.refresh();
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez taper un nom d'un organisme");
            textfieldsearch.clear();
            alert.showAndWait();
            table.refresh();
        }
    }
    */
    @FXML
    public void deleteFormation(ActionEvent event)  {
        /*String sid = textfieldid.getText();
        int idcons=0;*/

        // if (Status > 0) {
       if (formation != null) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                DbConnectinfo serviceformation = new DbConnectinfo();
                serviceformation.delete(formation.getId());
                loadScreenButton();
            }
            // Status =serviceconseil.delete(conseilmodel.getIdcons());
            //if (Status > 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Supprimer Formation");
            alert1.setHeaderText("Information");
            alert1.setContentText(" Formation bien supprimé");
            alert1.showAndWait();
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

    @FXML
    private void btnChercherAction(ActionEvent event) {
        if (!textfieldsearch.getText().isEmpty()) {
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
        nbr_place.setCellValueFactory(new PropertyValueFactory<>("Nbr_Place"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        //coach.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
          
            DbConnectinfo cs = new DbConnectinfo();
            data= cs.chercherFormation(textfieldsearch.getText());
            if (data != null) {
                table.setItems(data);
                table.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText(textfieldsearch.getText() + " n'existe pas !");
                textfieldsearch.clear();
                alert.showAndWait();
                table.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez taper un nom d'un organisme");
            textfieldsearch.clear();
            alert.showAndWait();
            table.refresh();
        }
        
    }
   /*public void setFILES(String Body, String Body1, String Body2, String Body3) {
       
        try {

            OutputStream file = new FileOutputStream(new File("jil.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file);

            document.open();
            document.addTitle("Ticket");

            //com.itextpdf.text.Image img;
            //img = com.itextpdf.text.Image.getInstance("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/louay.jpg");
            //com.itextpdf.text.Image.getInstance(img);
            //document.add(img);
            document.add(new Paragraph("                    "));
            document.add(new Paragraph("                    "));

            document.add(new Paragraph(Body));
            document.add(new Paragraph(Body1));
            document.add(new Paragraph(Body2));
            document.add(new Paragraph(Body3));
            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();

        }}
         

    
    public void btnPDF(String Body, String Body1, String Body2, String Body3) throws IOException, SQLException {
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Button button2 = new Button();
        button2.setStyle("-fx-background-color: #00ff00");
        alert.setTitle("PDF ");
        alert.setContentText(" vous voulez exporter votre Cour en PDF ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            setFILES(Body, Body1, Body2, Body3);

        } else {

        }
    }

    @FXML
    private void pdfpdfaction(ActionEvent event) throws SQLException {
        
   // String desc = fo.getDescription();
    Connection conn = dc.Connect();
   DbConnectinfo dbc = new DbConnectinfo();
  // Formation fo = new Formation();
   //dbc.listerNomFormation();
    String Body = "Bonjour Mr/Mme  ---- je vous souhaite la bienvenue "+formation.getCategorie();
                                    String Body1 = "Au sein de notre Formation"+formation.getCategorie();
                                    String Body2 = "Qui aura lieu le  "+formation.getDate_Debut();
                                    String Body3 = "";
                                    try {
                                        btnPDF(Body, Body1, Body2, Body3);
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    }
    }*/

 @FXML
    private void imprimer(ActionEvent event) throws   ClassNotFoundException, SQLException, DocumentException, BadElementException, IOException, URISyntaxException {
   
         try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/frame","root","");
      java.sql.PreparedStatement pt = con.prepareStatement("select * from formation");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document();

                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using.pdf"));
                       
                        my_pdf_report.open();  
                       my_pdf_report.add(new Paragraph(new Date().toString()));
                           com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("c:/ab_pic.jpg");
                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("Listes des Formations"));
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(7);
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
                       
                                       table_cell=new PdfPCell(new Phrase(" id"));
                                      table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Description"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Date_Debut"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Date_Fin"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Nbr_Place"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Categorie"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Coach_id"));
                                       
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);

                                      while(rs.next()){
                                      
                                       String id = rs.getString("Id");
                                       table_cell=new PdfPCell(new Phrase(id));
                                       my_report_table.addCell(table_cell);
                                       String Description=rs.getString("Description");
                                       table_cell=new PdfPCell(new Phrase(Description));
                                       my_report_table.addCell(table_cell);
                                       String ds=rs.getString("Date_Debut");
                                       table_cell=new PdfPCell(new Phrase(ds));
                                       my_report_table.addCell(table_cell);
                                       String dd=rs.getString("Date_Fin");
                                       table_cell=new PdfPCell(new Phrase(dd));
                                       my_report_table.addCell(table_cell);
                                        String df = rs.getString("Nbr_Place");
                                       table_cell=new PdfPCell(new Phrase(df));
                                       my_report_table.addCell(table_cell);
                                        String dr = rs.getString("Categorie");
                                       table_cell=new PdfPCell(new Phrase(dr));
                                       my_report_table.addCell(table_cell); 
                                       String Coach_id = rs.getString("Coach_id");
                                       table_cell=new PdfPCell(new Phrase(Coach_id));
                                       my_report_table.addCell(table_cell);
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
             System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "imprimer avec succes");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
    }
   
    
} 

