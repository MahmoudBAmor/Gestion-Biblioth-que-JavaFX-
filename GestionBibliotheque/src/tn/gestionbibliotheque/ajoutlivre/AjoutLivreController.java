/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.ajoutlivre;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.gestionbibliotheque.classes.Livre;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutLivreController implements Initializable {

    private int increment;
    
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField auteur;
    @FXML
    private JFXTextField editeur;
    @FXML
    private JFXButton BoutonEnregistrer;
    @FXML
    private JFXButton BoutonAnnuler;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void ajouterLivre(ActionEvent event) throws IOException {
        
        String idliv = id.getText();
        String auteurliv = auteur.getText();
        String titreliv = titre.getText();
        String editeurliv = editeur.getText();

        if (idliv.isEmpty() || auteurliv.isEmpty() || titreliv.isEmpty() || editeurliv.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }
        else{
        FileReader fileinc;
        try {
            
            fileinc = new FileReader("incrementLivres.txt");
            BufferedReader reader = new BufferedReader(fileinc);
            String text; 
            text = reader.readLine();
            increment = Integer.parseInt(text);
            reader.close();
            PrintWriter pw = new PrintWriter("incrementLivres.txt");
            pw.close();


        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        StringBuilder sb = new StringBuilder();
        sb.append(titreliv+",");
        sb.append(idliv+increment+",");
        increment ++;
        sb.append(auteurliv+",");
        sb.append(editeurliv+",");
        sb.append("1");
        sb.append(System.lineSeparator());
        
        //Writing data to book database******************
        File file = new File("BaseLivres.txt");
        try {
            FileWriter W = new FileWriter(file, true);
            W.write(sb.toString());
            W.close();
        } catch (IOException ex) {
            Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*****************************************
        //Saving the increment value***************
        StringBuilder sb1 = new StringBuilder();
        sb1.append("");
        sb1.append(increment);
        String strI = sb1.toString();
        File filesav = new File("incrementLivres.txt");
        try {
            FileWriter W1 = new FileWriter(filesav, true);
            W1.write(strI);
            W1.close();
        } catch (IOException ex) {
            Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*****************************************
        
        titre.clear();
        id.clear();
        auteur.clear();
        editeur.clear();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Livre Ajouté");
        Optional<ButtonType> response = alert.showAndWait();
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
