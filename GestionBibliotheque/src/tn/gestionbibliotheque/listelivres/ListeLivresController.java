/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.listelivres;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tn.gestionbibliotheque.classes.Livre;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListeLivresController implements Initializable {

    ObservableList<Livre> list = FXCollections.observableArrayList();
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Livre> tableView;
    @FXML
    private TableColumn<Livre, String> idCol;
    @FXML
    private TableColumn<Livre, String> titreCol;
    @FXML
    private TableColumn<Livre, String> AuteurCol;
    @FXML
    private TableColumn<Livre, String> EditeurCol;
    @FXML
    private TableColumn<Livre, Boolean> DispoCol;

    /**
     * Initializes the controller class.
     */
        @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            initCol();
            readLivresFile();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ListeLivresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
     private void readLivresFile() throws IOException{

        Livre.getBooks(list);
        tableView.getItems().setAll(list);
        //return list;
    }
     
        private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("id"));
        titreCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        AuteurCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
        EditeurCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("editeur"));
        DispoCol.setCellValueFactory(new PropertyValueFactory<Livre, Boolean>("estDisponible"));
    }

    @FXML
    private void suppressionLivre(ActionEvent event) throws IOException {
        Livre Asupprimer = tableView.getSelectionModel().getSelectedItem();
        ObservableList<Livre> listSupp = FXCollections.observableArrayList();
         try {
           FileReader file = new FileReader("BaseLivres.txt");
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
   
           String Gtitre = text.substring(0, pos[0]);
           String Gid = text.substring(pos[0]+1, pos[1]);
	   String Gauteur = text.substring(pos[1]+1, pos[2]);
           String Gediteur = text.substring(pos[2]+1, pos[3]);
	   String Gdispo = text.substring(pos[3]+1);
           System.out.println(Gtitre+"--"+Gid+"--"+Gauteur+"--"+Gediteur+"--"+Gdispo);
           Boolean Gdispox = false;
           if(Gdispo.contentEquals("1")|| Gdispo.contentEquals("true")) Gdispox = true;
           if(Asupprimer.getId().contentEquals(Gid)){
               if(Gdispox == false){
                   Alert alert2 = new Alert(Alert.AlertType.ERROR);
                   alert2.setTitle("Erruer");
                   alert2.setHeaderText(null);
                   alert2.setContentText("Livre Indisponible");
                   alert2.showAndWait();
                   listSupp.add(new Livre(Gtitre, Gid, Gauteur, Gediteur, Gdispox));
               }else{
                   Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                   alert3.setTitle("Confirmation");
                   alert3.setHeaderText(null);
                   alert3.setContentText("Succés");
                   Optional<ButtonType> response3 = alert3.showAndWait();
               }
               
           }else{
           listSupp.add(new Livre(Gtitre, Gid, Gauteur, Gediteur, Gdispox));}

           
           }while(1 == 1);
           reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeLivresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.getItems().clear();
        tableView.getItems().setAll(listSupp);
        
        //Erase BaseLivres.txt content****************************
            PrintWriter pw = new PrintWriter("BaseLivres.txt");
            pw.close();
            //********************************************************
            
            //Rewrite data to BaseLivres.txt
            File file3 = new File("BaseLivres.txt");
            FileWriter W = new FileWriter(file3, true);
            
            for(int i =0; i<listSupp.size(); i++){
            W.write(listSupp.get(i).toString());
            W.write(System.lineSeparator());
            }
            W.close();
            
    }
    

    
    
}
