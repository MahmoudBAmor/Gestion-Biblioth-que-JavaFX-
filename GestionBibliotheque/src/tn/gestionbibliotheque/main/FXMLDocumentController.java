/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.main;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Objects;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.gestionbibliotheque.ajoutlivre.AjoutLivreController;
import tn.gestionbibliotheque.classes.Etudiant;
import tn.gestionbibliotheque.classes.Livre;
import tn.gestionbibliotheque.classes.Pret;
import tn.gestionbibliotheque.classes.Professeur;
import tn.gestionbibliotheque.listelivres.ListeLivresController;
import tn.gestionbibliotheque.listemembres.ListeMembresController;
import tn.gestionbibliotheque.util.GestionBibliothequeUtil;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
   
    ObservableList<Livre> list = FXCollections.observableArrayList();
    
    private Label label;
    @FXML
    private HBox info_livre;
    @FXML
    private HBox info_membre;
    @FXML
    private TextField IDlivreInput;
    @FXML
    private Text titreLivre;
    @FXML
    private Text auteurLivre;
    @FXML
    private Text etatLivre;
    @FXML
    private TextField IDmembreInput;
    @FXML
    private Text nomMembre;
    @FXML
    private Text telMembre;
    @FXML
    private Text emailMembre;
    @FXML
    private JFXTextField livreID;
    @FXML
    private ListView<String> listeInfoPret;
    @FXML
    private StackPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(info_livre, 1);
        JFXDepthManager.setDepth(info_membre, 1);
    }    

    @FXML
    private void loadAjoutMembre(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/ajoutmembre/AjoutMembre.fxml", "Ajout Membre");
    }

    @FXML
    private void loadAjoutLivre(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/ajoutlivre/AjoutLivre.fxml", "Ajout Livre");
    }

    @FXML
    private void loadTableMembres(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/listemembres/ListeMembres.fxml", "Liste Membres");
    }

    @FXML
    private void loadTableLivres(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/listelivres/ListeLivres.fxml", "Liste Livres");
    }
    
        @FXML
    private void loadParametres(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/parametres/Settings.fxml", "Paramètres");
    }
    
    void loadWindow(String loc, String title){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            loader.setController(new FXMLDocumentController());
            Parent parent = loader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            GestionBibliothequeUtil.setStageIcon(stage);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chargerInfoLivre(ActionEvent event) throws IOException {
        String id = IDlivreInput.getText();
        Boolean flag = false, Gdispox; 
        
        try {
            FileReader file = null;
            file = new FileReader("BaseLivres.txt");
            BufferedReader reader = new BufferedReader(file);
            String text;
            do{
                
                if((text= reader.readLine())== null) break ;
                System.out.println(text);
                
                int[] pos = new int[4];int j = 0;
                for(int i =0; i<text.length();i++){
                    if(j>4) break;
                    if(text.charAt(i) == ','){
                        pos[j] = i;
                        j++;
                        
                    }
                    
                }
                
                
                String Gid = text.substring(pos[0]+1, pos[1]);
                if(Gid.contentEquals(id)){
                String Gtitre = text.substring(0, pos[0]);
                String Gauteur = text.substring(pos[1]+1, pos[2]);
                String Gediteur = text.substring(pos[2]+1, pos[3]);
                String Gdispo = text.substring(pos[3]+1);
                System.out.println(Gtitre+"--"+Gid+"--"+Gauteur+"--"+Gediteur+"--"+Gdispo);
                
                if(Gdispo.contentEquals("1")||Gdispo.contentEquals("true")) Gdispox = true;
                else Gdispox = false;
                flag = true;
                titreLivre.setText(Gtitre);
                auteurLivre.setText(Gauteur);
                if(Gdispox)
                etatLivre.setText("Disponible");
                else etatLivre.setText("Indisponible");
                break;
                }
                
             
            }while(1 == 1);
            reader.close();
            if(!flag){
                titreLivre.setText("Introuvable");
                auteurLivre.setText("Introuvable");
                etatLivre.setText("Introuvable");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    @FXML
    private void chargerInfoMembre(ActionEvent event) throws IOException {
        String id = IDmembreInput.getText();
        Boolean flag = false;
        
            try {
           FileReader file = new FileReader("BaseProfesseurs.txt");
           BufferedReader reader = new BufferedReader(file);
           String text;
           do{
           
           if((text= reader.readLine())== null) break ;
           System.out.println(text);
           
           int[] pos = new int[4];int j = 0;
           for(int i =0; i<text.length();i++){
			if(j>4) break;	
               if(text.charAt(i) == ','){
                   pos[j] = i;
                   j++;
                   
               }
               
           }
           
           String Gid = text.substring(pos[0]+1, pos[1]);
           if(Gid.contentEquals(id)){
           String Gnom = text.substring(0, pos[0]);
	   String Gdepartement = text.substring(pos[1]+1, pos[2]);
           String Gtel = text.substring(pos[2]+1, pos[3]);
	   String Gmail = text.substring(pos[3]+1);
           System.out.println(Gnom+"--"+Gid+"--"+Gdepartement+"--"+Gtel+"--"+Gmail);
           
           nomMembre.setText(Gnom);
           telMembre.setText(Gtel);
           emailMembre.setText(Gmail);
           flag = true; 
           break;
           }
      
           }while(1 == 1);
           reader.close();
           if(!flag){
                nomMembre.setText("Introuvable");
                telMembre.setText("Introuvable");
                emailMembre.setText("Introuvable");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        try {
           FileReader file = new FileReader("BaseEtudiants.txt");
           BufferedReader reader = new BufferedReader(file);
           String text;
           do{
           
           if((text= reader.readLine())== null) break ;
           System.out.println(text);
           
           int[] pos = new int[5];int j = 0;
           for(int i =0; i<text.length();i++){
			if(j>5) break;	
               if(text.charAt(i) == ','){
                   pos[j] = i;
                   j++;
                   
               }
               
           }
           String Gid = text.substring(pos[0]+1, pos[1]);
           if(Gid.contentEquals(id)){
               
           String Gnom = text.substring(0, pos[0]);
	   String Gspecialite = text.substring(pos[1]+1, pos[2]);
           String Ggroupe = text.substring(pos[2]+1, pos[3]);
	   String Gtel = text.substring(pos[3]+1,pos[4]);
	   String Gmail = text.substring(pos[4]+1);
           System.out.println(Gnom+"--"+Gid+"--"+Gspecialite+"--"+Ggroupe+"--"+Gtel+"--"+Gmail);
           
           nomMembre.setText(Gnom);
           telMembre.setText(Gtel);
           emailMembre.setText(Gmail);
           flag = true; 
           break;
           }
           
           }while(1 == 1);
           reader.close();
           
           if(!flag){
                nomMembre.setText("Introuvable");
                telMembre.setText("Introuvable");
                emailMembre.setText("Introuvable");
            }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }    
            
    }

    @FXML
    private void chargerOperationPreter(ActionEvent event) throws IOException {
        String IDmembre = IDmembreInput.getText();
        String IDlivre = IDlivreInput.getText();
        
        if(!nomMembre.getText().contentEquals("Nom & Prenom") && !titreLivre.getText().contentEquals("Titre") && !IDmembre.contentEquals("")&& !IDlivre.contentEquals("") && !nomMembre.getText().contentEquals("Introuvable") && !titreLivre.getText().contentEquals("Introuvable")){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Prêter le livre "+titreLivre.getText()+" à "+nomMembre.getText()+" ?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){
            //!!!!! implementer le prêt du livre!!!
        
        //*********Writing data to pret file    
        File file = new File("BasePret.txt");
        try {
            FileWriter W = new FileWriter(file, true);
            W.write(new Pret(IDmembreInput.getText(), IDlivreInput.getText()).toString());
            W.write(System.lineSeparator());
            W.close();
        } catch (IOException ex) {
            Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            //*********Updating book availibility
        String id = IDlivreInput.getText();
         
        try {
            FileReader file2 = null;
            file2 = new FileReader("BaseLivres.txt");
            BufferedReader reader = new BufferedReader(file2);
            String text;
            Boolean Gdispox;
            do{
                
                if((text= reader.readLine())== null) break ;
                System.out.println(text);
                
                int[] pos = new int[4];int j = 0;
                for(int i =0; i<text.length();i++){
                    if(j>4) break;
                    if(text.charAt(i) == ','){
                        pos[j] = i;
                        j++;
                        
                    }
                    
                }
                
                
                String Gid = text.substring(pos[0]+1, pos[1]);
                String Gtitre = text.substring(0, pos[0]);
                String Gauteur = text.substring(pos[1]+1, pos[2]);
                String Gediteur = text.substring(pos[2]+1, pos[3]);
                String Gdispo = text.substring(pos[3]+1);
                System.out.println(Gtitre+"--"+Gid+"--"+Gauteur+"--"+Gediteur+"--"+Gdispo);
                if(Gdispo.contentEquals("1")||Gdispo.contentEquals("true")) Gdispox = true;
                else Gdispox = false;
                if(Gid.contentEquals(IDlivreInput.getText())){
                    list.add(new Livre(Gtitre, Gid, Gauteur, Gediteur, false));
                }
                else list.add(new Livre(Gtitre, Gid, Gauteur, Gediteur, Gdispox));
                
            }while(1 == 1);
            reader.close();
            //Erase BaseLivres.txt content****************************
            PrintWriter pw = new PrintWriter("BaseLivres.txt");
            pw.close();
            //********************************************************
            
            //Rewrite data to BaseLivres.txt
            File file3 = new File("BaseLivres.txt");
            FileWriter W = new FileWriter(file3, true);
            
            for(int i =0; i<list.size(); i++){
            W.write(list.get(i).toString());
            W.write(System.lineSeparator());
            }
            W.close();
            
            Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
            alert3.setTitle("Confirmation");
            alert3.setHeaderText(null);
            alert3.setContentText("Succés");
            Optional<ButtonType> response3 = alert3.showAndWait();
            
        }catch (IOException ex) {
            Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);}
           
        }}else{
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Erruer");
            alert2.setHeaderText(null);
            alert2.setContentText("Veuillez remplir tous les champs");
            Optional<ButtonType> response2 = alert2.showAndWait();
        }
       
    }

    @FXML
    private void chargerInfoLivre2(ActionEvent event) throws IOException {
        String id = livreID.getText();
        ObservableList<String> infoLivre = FXCollections.observableArrayList();
        
         
        try {
            FileReader file = null;
            file = new FileReader("BasePret.txt");
            BufferedReader reader = new BufferedReader(file);
            String text;
            do{
                
                if((text= reader.readLine())== null) break ;
                System.out.println(text);
                
                int[] pos = new int[3];int j = 0;
                for(int i =0; i<text.length();i++){
                    if(j>3) break;
                    if(text.charAt(i) == ','){
                        pos[j] = i;
                        j++;
                        
                    }
                    
                }
                String Gidmem = text.substring(0, pos[0]);
                String Gidlivre = text.substring(pos[0]+1, pos[1]);
                
                
                
                String Gdatepret = text.substring(pos[1]+1, pos[2]);
                String Gnbrenouv = text.substring(pos[2]+1);
                if(Gidlivre.contentEquals(id)){
                System.out.println(Gidmem+"--"+Gidlivre+"--"+Gdatepret+"--"+Gnbrenouv);
                
                infoLivre.add("Date & Heure Acquisition : " + Gdatepret);
                infoLivre.add("Nombre de renouvlements : " + Gnbrenouv);
                
                
        
        
                infoLivre.add("------------Informations sur le Livre-------------");
                
                //try {
                FileReader file1 = new FileReader("BaseLivres.txt");
                BufferedReader reader1 = new BufferedReader(file1);
                String text1;
                do{

                if((text1= reader1.readLine())== null) break ;
                System.out.println(text1);

                int[] pos1 = new int[4];int j1 = 0;
                for(int i =0; i<text1.length();i++){
                             if(j1>4) break;	
                    if(text1.charAt(i) == ','){
                        pos1[j1] = i;
                        j1++;

                    }

                }
                String Gid = text1.substring(pos1[0]+1, pos1[1]);
                
                String Gtitre = text1.substring(0, pos1[0]);
                
                String Gauteur = text1.substring(pos1[1]+1, pos1[2]);
                String Gediteur = text1.substring(pos1[2]+1, pos1[3]);
                String Gdispo = text1.substring(pos1[3]+1);
                if(id.contentEquals(Gid)){
                System.out.println(Gtitre+"--"+Gid+"--"+Gauteur+"--"+Gediteur+"--"+Gdispo);
               
                infoLivre.add("Titre : " + Gtitre);
                infoLivre.add("Auteur : " + Gauteur);
                infoLivre.add("Editeur : " + Gediteur);
                }

                }while(1 == 1);
                reader1.close();
//                } catch (FileNotFoundException ex) {
//                 Logger.getLogger(ListeLivresController.class.getName()).log(Level.SEVERE, null, ex);
//                }
                
                infoLivre.add("------------Informations sur le Membre------------");
                
               // try {
                FileReader file2 = new FileReader("BaseProfesseurs.txt");
                BufferedReader reader2 = new BufferedReader(file2);
                String text2;
                do{

                if((text2= reader2.readLine())== null) break ;
                System.out.println(text2);

                int[] pos2 = new int[4];int j2 = 0;
                for(int i =0; i<text2.length();i++){
                             if(j2>4) break;	
                    if(text2.charAt(i) == ','){
                        pos2[j2] = i;
                        j2++;

                    }

                }
                String Gidprof = text2.substring(pos2[0]+1, pos2[1]);
                
                
                String Gnomprof = text2.substring(0, pos2[0]);
                
                String Gdepartementprof = text2.substring(pos2[1]+1, pos2[2]);
                String Gtelprof = text2.substring(pos2[2]+1, pos2[3]);
                String Gmailprof = text2.substring(pos2[3]+1);
                if(Gidprof.contentEquals(Gidmem)){
                System.out.println(Gnomprof+"--"+Gidprof+"--"+Gdepartementprof+"--"+Gtelprof+"--"+Gmailprof);
                
                infoLivre.add("Professeur : "+Gnomprof);
                infoLivre.add("Département : " + Gdepartementprof);
                infoLivre.add("Tel : " + Gtelprof);
                infoLivre.add("Email : " + Gmailprof);
                }


                }while(1 == 1);
                reader2.close();

                //ou*******************
                
                FileReader file3 = new FileReader("BaseEtudiants.txt");
                BufferedReader reader3 = new BufferedReader(file3);
                String text3;
                do{

                if((text3= reader3.readLine())== null) break ;
                System.out.println(text3);

                int[] pos3 = new int[5];int j3 = 0;
                for(int i =0; i<text3.length();i++){
                             if(j3>5) break;	
                    if(text3.charAt(i) == ','){
                        pos3[j3] = i;
                        j3++;

                    }

                }
                String Gidetd = text3.substring(pos3[0]+1, pos3[1]);
                
                String Gnometd = text3.substring(0, pos3[0]);
                
                
                String Gspecialiteetd = text3.substring(pos3[1]+1, pos3[2]);
                String Ggroupeetd = text3.substring(pos3[2]+1, pos3[3]);
                String Gteletd = text3.substring(pos3[3]+1,pos3[4]);
                String Gmailetd = text3.substring(pos3[4]+1);
                if(Gidetd.contentEquals(Gidmem)){
                System.out.println(Gnometd+"--"+Gidetd+"--"+Gspecialiteetd+"--"+Ggroupeetd+"--"+Gteletd+"--"+Gmailetd);
                
                infoLivre.add("Etudiant : "+Gnometd);
                infoLivre.add("Spécialité : "+Gspecialiteetd);
                infoLivre.add("Groupe : "+Ggroupeetd);
                infoLivre.add("Tel : "+Gteletd);
                infoLivre.add("Email : "+Gmailetd);
                }



                }while(1 == 1);
                reader3.close();
                }
             //****fin iter    
             }while(1 == 1);
                reader.close();}
            catch (IOException ex) {
                Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);}
        listeInfoPret.getItems().setAll(infoLivre);
    }

    private void chargerInfoLivre2() throws IOException {
        String id = livreID.getText();
        ObservableList<String> infoLivre = FXCollections.observableArrayList();
        
         
        try {
            FileReader file = null;
            file = new FileReader("BasePret.txt");
            BufferedReader reader = new BufferedReader(file);
            String text;
            do{
                
                if((text= reader.readLine())== null) break ;
                System.out.println(text);
                
                int[] pos = new int[3];int j = 0;
                for(int i =0; i<text.length();i++){
                    if(j>3) break;
                    if(text.charAt(i) == ','){
                        pos[j] = i;
                        j++;
                        
                    }
                    
                }
                String Gidmem = text.substring(0, pos[0]);
                String Gidlivre = text.substring(pos[0]+1, pos[1]);
                
                
                
                String Gdatepret = text.substring(pos[1]+1, pos[2]);
                String Gnbrenouv = text.substring(pos[2]+1);
                if(Gidlivre.contentEquals(id)){
                System.out.println(Gidmem+"--"+Gidlivre+"--"+Gdatepret+"--"+Gnbrenouv);
                
                infoLivre.add("Date & Heure Acquisition : " + Gdatepret);
                infoLivre.add("Nombre de renouvlements : " + Gnbrenouv);
                
                
        
        
                infoLivre.add("------------Informations sur le Livre-------------");
                
                //try {
                FileReader file1 = new FileReader("BaseLivres.txt");
                BufferedReader reader1 = new BufferedReader(file1);
                String text1;
                do{

                if((text1= reader1.readLine())== null) break ;
                System.out.println(text1);

                int[] pos1 = new int[4];int j1 = 0;
                for(int i =0; i<text1.length();i++){
                             if(j1>4) break;	
                    if(text1.charAt(i) == ','){
                        pos1[j1] = i;
                        j1++;

                    }

                }
                String Gid = text1.substring(pos1[0]+1, pos1[1]);
                
                String Gtitre = text1.substring(0, pos1[0]);
                
                String Gauteur = text1.substring(pos1[1]+1, pos1[2]);
                String Gediteur = text1.substring(pos1[2]+1, pos1[3]);
                String Gdispo = text1.substring(pos1[3]+1);
                if(id.contentEquals(Gid)){
                System.out.println(Gtitre+"--"+Gid+"--"+Gauteur+"--"+Gediteur+"--"+Gdispo);
               
                infoLivre.add("Titre : " + Gtitre);
                infoLivre.add("Auteur : " + Gauteur);
                infoLivre.add("Editeur : " + Gediteur);
                }

                }while(1 == 1);
                reader1.close();
//                } catch (FileNotFoundException ex) {
//                 Logger.getLogger(ListeLivresController.class.getName()).log(Level.SEVERE, null, ex);
//                }
                
                infoLivre.add("------------Informations sur le Membre------------");
                
               // try {
                FileReader file2 = new FileReader("BaseProfesseurs.txt");
                BufferedReader reader2 = new BufferedReader(file2);
                String text2;
                do{

                if((text2= reader2.readLine())== null) break ;
                System.out.println(text2);

                int[] pos2 = new int[4];int j2 = 0;
                for(int i =0; i<text2.length();i++){
                             if(j2>4) break;	
                    if(text2.charAt(i) == ','){
                        pos2[j2] = i;
                        j2++;

                    }

                }
                String Gidprof = text2.substring(pos2[0]+1, pos2[1]);
                
                
                String Gnomprof = text2.substring(0, pos2[0]);
                
                String Gdepartementprof = text2.substring(pos2[1]+1, pos2[2]);
                String Gtelprof = text2.substring(pos2[2]+1, pos2[3]);
                String Gmailprof = text2.substring(pos2[3]+1);
                if(Gidprof.contentEquals(Gidmem)){
                System.out.println(Gnomprof+"--"+Gidprof+"--"+Gdepartementprof+"--"+Gtelprof+"--"+Gmailprof);
                
                infoLivre.add("Professeur : "+Gnomprof);
                infoLivre.add("Département : " + Gdepartementprof);
                infoLivre.add("Tel : " + Gtelprof);
                infoLivre.add("Email : " + Gmailprof);
                }


                }while(1 == 1);
                reader2.close();

                //ou*******************
                
                FileReader file3 = new FileReader("BaseEtudiants.txt");
                BufferedReader reader3 = new BufferedReader(file3);
                String text3;
                do{

                if((text3= reader3.readLine())== null) break ;
                System.out.println(text3);

                int[] pos3 = new int[5];int j3 = 0;
                for(int i =0; i<text3.length();i++){
                             if(j3>5) break;	
                    if(text3.charAt(i) == ','){
                        pos3[j3] = i;
                        j3++;

                    }

                }
                String Gidetd = text3.substring(pos3[0]+1, pos3[1]);
                
                String Gnometd = text3.substring(0, pos3[0]);
                
                
                String Gspecialiteetd = text3.substring(pos3[1]+1, pos3[2]);
                String Ggroupeetd = text3.substring(pos3[2]+1, pos3[3]);
                String Gteletd = text3.substring(pos3[3]+1,pos3[4]);
                String Gmailetd = text3.substring(pos3[4]+1);
                if(Gidetd.contentEquals(Gidmem)){
                System.out.println(Gnometd+"--"+Gidetd+"--"+Gspecialiteetd+"--"+Ggroupeetd+"--"+Gteletd+"--"+Gmailetd);
                
                infoLivre.add("Etudiant : "+Gnometd);
                infoLivre.add("Spécialité : "+Gspecialiteetd);
                infoLivre.add("Groupe : "+Ggroupeetd);
                infoLivre.add("Tel : "+Gteletd);
                infoLivre.add("Email : "+Gmailetd);
                }



                }while(1 == 1);
                reader3.close();
                }
             //****fin iter    
             }while(1 == 1);
                reader.close();}
            catch (IOException ex) {
                Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);}
        listeInfoPret.getItems().setAll(infoLivre);
    }
    
    @FXML
    private void chargerOperationRenouvler(ActionEvent event) throws FileNotFoundException, IOException {
        String id = livreID.getText();
        ObservableList<Pret> tabPret = FXCollections.observableArrayList();
        
        try {
   
            FileReader file = new FileReader("BasePret.txt");
            BufferedReader reader = new BufferedReader(file);
            String text;
            do{
                
                if((text = reader.readLine()) == null) break ;
                System.out.println(text);
                
                int[] pos = new int[3];int j = 0;
                for(int i =0; i<text.length();i++){
                    if(j>3) break;
                    if(text.charAt(i) == ','){
                        pos[j] = i;
                        j++;
                        
                    }
                    
                }
                String Gidmem = text.substring(0, pos[0]);
                String Gidlivre = text.substring(pos[0]+1, pos[1]);
                String Gdatepret = text.substring(pos[1]+1, pos[2]);
                String Gnbrenouv = text.substring(pos[2]+1);
                
                if(id.contentEquals(Gidlivre) && Gnbrenouv.contentEquals("1")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Nombre maximum de renouvellement atteint");
                    Optional<ButtonType> response = alert.showAndWait();
                    System.out.println(Gidmem+"--"+Gidlivre+"--"+Gdatepret+"--"+Gnbrenouv);
                    tabPret.add(new Pret(Gidmem, Gidlivre, Gdatepret,"1"));
                }
                else if(id.contentEquals(Gidlivre)){
                    System.out.println(Gidmem+"--"+Gidlivre+"--"+Gdatepret+"--"+Gnbrenouv);
                    tabPret.add(new Pret(Gidmem, Gidlivre, "1"));
                }else {tabPret.add(new Pret(Gidmem, Gidlivre ,Gdatepret, Gnbrenouv));}
                }while(1 == 1);
                reader.close();
                file.close();
                
                PrintWriter pw = new PrintWriter("BasePret.txt");
                pw.close();
                
                File file1 = new File("BasePret.txt");
                FileWriter W = new FileWriter(file1, true);
                for(int i =0; i<tabPret.size(); i++){
                W.write(tabPret.get(i).toString());
                W.write(System.lineSeparator());}
                W.close();
        }
        catch (IOException ex) {
                Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);}
        listeInfoPret.getItems().clear();
        chargerInfoLivre2();

    }

    @FXML
    private void chargerOperationRetourner(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Retourner le livre ?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){
        String id = livreID.getText();
        ObservableList<Livre> list1 = FXCollections.observableArrayList();
        ObservableList<Pret> tabPret = FXCollections.observableArrayList();
        
        try {
   
            FileReader file = new FileReader("BasePret.txt");
            BufferedReader reader = new BufferedReader(file);
            String text;
            do{
                
                if((text = reader.readLine()) == null) break ;
                System.out.println(text);
                
                int[] pos = new int[3];int j = 0;
                for(int i =0; i<text.length();i++){
                    if(j>3) break;
                    if(text.charAt(i) == ','){
                        pos[j] = i;
                        j++;
                        
                    }
                    
                }
                String Gidmem = text.substring(0, pos[0]);
                String Gidlivre = text.substring(pos[0]+1, pos[1]);
                String Gdatepret = text.substring(pos[1]+1, pos[2]);
                String Gnbrenouv = text.substring(pos[2]+1);
                
                
                if(!id.contentEquals(Gidlivre)){
                    System.out.println(Gidmem+"--"+Gidlivre+"--"+Gdatepret+"--"+Gnbrenouv);
                    tabPret.add(new Pret(Gidmem, Gidlivre, Gdatepret, Gnbrenouv));}
                }while(1 == 1);
                reader.close();
                file.close();
                
                PrintWriter pw = new PrintWriter("BasePret.txt");
                pw.close();
                
                File file1 = new File("BasePret.txt");
                FileWriter W = new FileWriter(file1, true);
                for(int i =0; i<tabPret.size(); i++){
                W.write(tabPret.get(i).toString());
                W.write(System.lineSeparator());}
                W.close();
                
                
                
        }
        catch (IOException ex) {
                Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);}
        
        try {
            FileReader file2 = null;
            file2 = new FileReader("BaseLivres.txt");
            BufferedReader reader = new BufferedReader(file2);
            String text;
            Boolean Gdispox;
            do{
                
                if((text= reader.readLine())== null) break ;
                System.out.println(text);
                
                int[] pos = new int[4];int j = 0;
                for(int i =0; i<text.length();i++){
                    if(j>4) break;
                    if(text.charAt(i) == ','){
                        pos[j] = i;
                        j++;
                        
                    }
                    
                }
                
                
                String Gid = text.substring(pos[0]+1, pos[1]);
                String Gtitre = text.substring(0, pos[0]);
                String Gauteur = text.substring(pos[1]+1, pos[2]);
                String Gediteur = text.substring(pos[2]+1, pos[3]);
                String Gdispo = text.substring(pos[3]+1);
                System.out.println(Gtitre+"--"+Gid+"--"+Gauteur+"--"+Gediteur+"--"+Gdispo);
                if(Gdispo.contentEquals("1")||Gdispo.contentEquals("true")) Gdispox = true;
                else Gdispox = false;
                if(Gid.contentEquals(id)){
                    list1.add(new Livre(Gtitre, Gid, Gauteur, Gediteur, true));
                }
                else list1.add(new Livre(Gtitre, Gid, Gauteur, Gediteur, Gdispox));
                
            }while(1 == 1);
            reader.close();
            //Erase BaseLivres.txt content****************************
            PrintWriter pw = new PrintWriter("BaseLivres.txt");
            pw.close();
            //********************************************************
            
            //Rewrite data to BaseLivres.txt
            File file3 = new File("BaseLivres.txt");
            FileWriter W = new FileWriter(file3, true);
            
            for(int i =0; i<list1.size(); i++){
            W.write(list1.get(i).toString());
            W.write(System.lineSeparator());
            }
            W.close();
            
            Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
            alert3.setTitle("Confirmation");
            alert3.setHeaderText(null);
            alert3.setContentText("Succés");
            Optional<ButtonType> response3 = alert3.showAndWait();
            
            chargerInfoLivre2();
            
        }catch (IOException ex) {
            Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);}
           
        }}

    @FXML
    private void ActionClose(ActionEvent event) {
        ((Stage)rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void MenuAjoutLivre(ActionEvent event) {
        
        loadWindow("/tn/gestionbibliotheque/ajoutlivre/AjoutLivre.fxml", "Ajout Livre");
    }

    @FXML
    private void MenuAjoutMembre(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/ajoutmembre/AjoutMembre.fxml", "Ajout Membre");
    }

    @FXML
    private void MenuConsultLivres(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/listelivres/ListeLivres.fxml", "Liste Livres");
    }

    @FXML
    private void MenuConsultMembres(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/listemembres/ListeMembres.fxml", "Liste Membres");
    }

    @FXML
    private void MenuConsultPleinEcran(ActionEvent event) {
        Stage stage = ((Stage)rootPane.getScene().getWindow());
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    private void ChargerAbout(ActionEvent event) {
        loadWindow("/tn/gestionbibliotheque/about/About.fxml", "About Me");
    }


    }

    
    


                


