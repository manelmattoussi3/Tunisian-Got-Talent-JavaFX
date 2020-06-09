/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdfjet.Letter;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Candidat;
import models.Conseil;
import models.Formation;
import org.controlsfx.control.Rating;
import models.Rate;
import service.DbConnectinfo;

//import piiii.PDFGeneration;
import sun.security.action.OpenFileInputStreamAction;
import utils.DbConnection;
/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class EspaceFormationController implements Initializable {
    
    @FXML
    private MediaView mediaview;
    @FXML
    private ImageView m2;
    @FXML
    private ImageView m3;
    @FXML
    private ImageView m1;
    private MediaPlayer mediaplayer;
    private Media media;
    @FXML
    private Slider slider;
    Pagination pagination;
    Stage stage;
    private FileChooser filechooser;
    @FXML
    private Rating rating;
    int Value;
    @FXML
    private Label msg;
    public Candidat can;
  //  public Formation fo;
     private FileChooser fc = new FileChooser();
    @FXML
    private TextField inputExportName;
     private DbConnection dc;
private Formation f ;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         dc = new DbConnection();
        filechooser = new FileChooser();
         String path = new File("src/Video/girlsaloud guitar lesson.mp4.mp4").getAbsolutePath();
       
        media = new Media(new File(path).toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaview.setMediaPlayer(mediaplayer);
        mediaplayer.setAutoPlay(true);
        slider.setValue(mediaplayer.getVolume() * 100);
        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaplayer.setVolume(slider.getValue() / 100);
                
            }
        });
       rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                msg.setText("Rating: " + newValue);
              // Value=newValue.intValue();
               // System.out.println(Value);
            }
            
        });
     // PDFGeneration pdfCreator =new PDFGeneration();
       // pdfCreator.pdfGeneration("fiii","tes");*/
    }
    
    @FXML
    public void DashBord(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    
    @FXML
    private void onclickbtn_play(ActionEvent event) {
        
        mediaplayer.play();
        
    }
    
    @FXML
    private void onclickbtn_pause(ActionEvent event) {
        mediaplayer.pause();
    }
    
    @FXML
    private void onclickbtn_stop(ActionEvent event) {
        mediaplayer.seek(mediaplayer.getStopTime());
        mediaplayer.stop();
    }
    
    @FXML
    public void quiter(ActionEvent event) {
        System.exit(0);
        
    }

  /*  @FXML
    private void openpdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException, InterruptedException, SQLException
{
       Document document=new Document();
       Formation fod = new Formation();
        /*try {
            Desktop.getDesktop().open(new File("C:\\Users\\DevelopAndroid\\Desktop\\Cours PLSQL.pdf"));
        } catch (Exception e) {
            
            e.printStackTrace();
        }*/
        /* fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf file","*.pdf"));
        fc.setTitle("save to pdf");
        fc.setInitialFileName("unitle.pdf");
        
            File file = fc.showSaveDialog(stage);
               Paragraph po = new Paragraph(fod.getCategorie());
               document.open();
      document.add(po);
            PdfWriter.getInstance(document, new FileOutputStream(file+".pdf"));
         
               
            if(file!=null){
            String str =file.getAbsolutePath();
                try {
                    FileOutputStream fos = new FileOutputStream(str);
                    PDF pdf = new PDF(fos);
                    
                    Page page = new Page(pdf,Letter.LANDSCAPE);
                    
                    pdf.close();
                    fos.flush();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                    
            }}
     /*  PdfWriter.getInstance(document,new FileOutputStream("filename "+".pdf"));
            document.open();
            Image image =Image.getInstance(getClass().getResource("/m1.png"));
            image.scaleToFit(200,86);
            image.setAbsolutePosition(200f,750f);
            document.add(image);
            document.add(new Paragraph("\n\n\n\n\n\n\n\n"+"text", FontFactory.getFont("plusplus",BaseFont.IDENTITY_H,BaseFont.EMBEDDED)));
            Chunk signature = new Chunk("\n\n la formation sur les instruments commence aujourdhuit");
            Paragraph base = new Paragraph(signature);
            document.add(base);
        
    }
/*
    @FXML
    private void addrate(MouseEvent event) throws SQLException{
        Rate r =new Rate();
        DbConnectinfo dbc = new  DbConnectinfo();
        dbc.rate(r);
        
        r.getId();
        //r.setCandidat_id(can.getId());
        r.setFormation_id(fo.getId());
        r.setAvis(Value);
    }*/
@FXML
    public void Voirplus(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/VoirPlus.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
     @FXML
    public void Evaluation(MouseEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/EvaluationView.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    public void setFILES(String Body, String Body1, String Body2, String Body3) {
       
        try {

            OutputStream file = new FileOutputStream(new File("formation.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file);

            document.open();
            document.addTitle("Cour");

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
    private void pdfpdfaction(ActionEvent event) throws SQLException,NullPointerException {
        
   // String desc = fo.getDescription();
    Connection conn = dc.Connect();
   DbConnectinfo dbc = new DbConnectinfo();
   Formation fo = new Formation();
   dbc.listerNomFormation();
    String Body = "Bonjour Mr/Mme  ---- je vous souhaite la bienvenue ";
                                    String Body1 = "Au sein de notre Formation"+fo.getCategorie();
                                    String Body2 = "Qui aura lieu le  "+fo.getDate_Debut();
                                    String Body3 = "";
                                    try {
                                        btnPDF(Body, Body1, Body2, Body3);
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    }
    }
    
}


   

