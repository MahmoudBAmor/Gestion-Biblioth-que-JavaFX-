/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.listemembres;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tn.gestionbibliotheque.classes.Etudiant;
import tn.gestionbibliotheque.classes.Livre;
import tn.gestionbibliotheque.classes.Membre;
import tn.gestionbibliotheque.classes.Professeur;
import tn.gestionbibliotheque.listelivres.ListeLivresController;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListeMembresController implements Initializable {

    ObservableList<Professeur> listProf = FXCollections.observableArrayList();
    ObservableList<Etudiant> listEtd = FXCollections.observableArrayList();
    
    @FXML
    private TabPane rootPane;
    @FXML
    private Tab tabProf;
    @FXML
    private AnchorPane paneProf;
    @FXML
    private TableColumn<Professeur, String> idCol;
    @FXML
    private TableColumn<Professeur, String> nomprenomCol;
    @FXML
    private TableColumn<Professeur, String> departementCol;
    @FXML
    private TableColumn<Professeur, String> telCol;
    @FXML
    private TableColumn<Professeur, String> emailCol;
    @FXML
    private Tab tabEtd;
    @FXML
    private AnchorPane paneEtd;
    @FXML
    private TableColumn<Etudiant, String> idColEtd;
    @FXML
    private TableColumn<Etudiant, String> nomprenomColEtd;
    @FXML
    private TableColumn<Etudiant, String> specialiteColEtd;
    @FXML
    private TableColumn<Etudiant, String> groupeColEtd;
    @FXML
    private TableColumn<Etudiant, String> telColEtd;
    @FXML
    private TableColumn<Etudiant, String> emailColEtd;
    @FXML
    private TableView<Professeur> tableViewProf;
    @FXML
    private TableView<Etudiant> tableViewEtd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initCol();
            readProfesseurFile();
            readEtudiantFile();
        } catch (IOException ex) {
            Logger.getLogger(ListeMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void readProfesseurFile() throws IOException{
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
   
           String Gnom = text.substring(0, pos[0]);
           String Gid = text.substring(pos[0]+1, pos[1]);
	   String Gdepartement = text.substring(pos[1]+1, pos[2]);
           String Gtel = text.substring(pos[2]+1, pos[3]);
	   String Gmail = text.substring(pos[3]+1);
           System.out.println(Gnom+"--"+Gid+"--"+Gdepartement+"--"+Gtel+"--"+Gmail);
           
           listProf.add(new Professeur(Gnom, Gid, Gtel, Gmail, Gdepartement));
//           System.out.println("-----------------------");
//           System.out.println(list.get(0).toString());
//           System.out.println("-----------------------");
           
           }while(1 == 1);
           reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableViewProf.getItems().setAll(listProf);
        //return list;
    }
    
    
    private void readEtudiantFile() throws IOException{
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
   
           String Gnom = text.substring(0, pos[0]);
           String Gid = text.substring(pos[0]+1, pos[1]);
	   String Gspecialite = text.substring(pos[1]+1, pos[2]);
           String Ggroupe = text.substring(pos[2]+1, pos[3]);
	   String Gtel = text.substring(pos[3]+1,pos[4]);
	   String Gmail = text.substring(pos[4]+1);
           System.out.println(Gnom+"--"+Gid+"--"+Gspecialite+"--"+Ggroupe+"--"+Gtel+"--"+Gmail);
           
           listEtd.add(new Etudiant(Gnom, Gid, Gtel, Gmail, Gspecialite, Ggroupe));
//           System.out.println("-----------------------");
//           System.out.println(list.get(0).toString());
//           System.out.println("-----------------------");
           
           }while(1 == 1);
           reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableViewEtd.getItems().setAll(listEtd);
        //return list;
    }
    
            private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<Professeur, String>("ID"));
        nomprenomCol.setCellValueFactory(new PropertyValueFactory<Professeur, String>("NomPrenom"));
        departementCol.setCellValueFactory(new PropertyValueFactory<Professeur, String>("Departement"));
        telCol.setCellValueFactory(new PropertyValueFactory<Professeur, String>("Tel"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Professeur, String>("Email"));
        
        idColEtd.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("ID"));
        nomprenomColEtd.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("NomPrenom"));
        specialiteColEtd.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Specialite"));
        telColEtd.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Tel"));
        emailColEtd.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Email"));
        groupeColEtd.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Groupe"));
    }

    @FXML
    private void supprimerProf(ActionEvent event) throws IOException {
        Professeur Asupprimer = tableViewProf.getSelectionModel().getSelectedItem();
        ObservableList<Professeur> listSupp = FXCollections.observableArrayList();
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
           String Gnom = text.substring(0, pos[0]);
	   String Gdepartement = text.substring(pos[1]+1, pos[2]);
           String Gtel = text.substring(pos[2]+1, pos[3]);
	   String Gmail = text.substring(pos[3]+1);
           System.out.println(Gnom+"--"+Gid+"--"+Gdepartement+"--"+Gtel+"--"+Gmail);
           
           if(Asupprimer.getID().contentEquals(Gid)){

                   Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                   alert3.setTitle("Confirmation");
                   alert3.setHeaderText(null);
                   alert3.setContentText("Succés");
                   Optional<ButtonType> response3 = alert3.showAndWait();
               
           }else{
           listSupp.add(new Professeur(Gnom, Gid, Gtel, Gmail, Gdepartement));}

           
           }while(1 == 1);
           reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeLivresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableViewProf.getItems().clear();
        tableViewProf.getItems().setAll(listSupp);
        
        //Erase BaseLivres.txt content****************************
            PrintWriter pw = new PrintWriter("BaseProfesseurs.txt");
            pw.close();
            //********************************************************
            
            //Rewrite data to BaseLivres.txt
            File file3 = new File("BaseProfesseurs.txt");
            FileWriter W = new FileWriter(file3, true);
            
            for(int i =0; i<listSupp.size(); i++){
            W.write(listSupp.get(i).toString());
            W.write(System.lineSeparator());
            }
            W.close();
    }

    @FXML
    private void supprimerEtd(ActionEvent event) throws FileNotFoundException, IOException {
        Etudiant Asupprimer = tableViewEtd.getSelectionModel().getSelectedItem();
        ObservableList<Etudiant> listSupp = FXCollections.observableArrayList();
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
           String Gnom = text.substring(0, pos[0]);
	   String Gspecialite = text.substring(pos[1]+1, pos[2]);
           String Ggroupe = text.substring(pos[2]+1, pos[3]);
	   String Gtel = text.substring(pos[3]+1,pos[4]);
	   String Gmail = text.substring(pos[4]+1);
           System.out.println(Gnom+"--"+Gid+"--"+Gspecialite+"--"+Ggroupe+"--"+Gtel+"--"+Gmail);
           
           if(Asupprimer.getID().contentEquals(Gid)){

                   Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                   alert3.setTitle("Confirmation");
                   alert3.setHeaderText(null);
                   alert3.setContentText("Succés");
                   Optional<ButtonType> response3 = alert3.showAndWait();
               
           }else{
           listSupp.add(new Etudiant(Gnom, Gid, Gtel, Gmail, Gspecialite, Ggroupe));}

           
           }while(1 == 1);
           reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeLivresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableViewEtd.getItems().clear();
        tableViewEtd.getItems().setAll(listSupp);
        
        //Erase BaseLivres.txt content****************************
            PrintWriter pw = new PrintWriter("BaseEtudiants.txt");
            pw.close();
            //********************************************************
            
            //Rewrite data to BaseLivres.txt
            File file3 = new File("BaseEtudiants.txt");
            FileWriter W = new FileWriter(file3, true);
            
            for(int i =0; i<listSupp.size(); i++){
            W.write(listSupp.get(i).toString());
            W.write(System.lineSeparator());
            }
            W.close();
    }
    }

