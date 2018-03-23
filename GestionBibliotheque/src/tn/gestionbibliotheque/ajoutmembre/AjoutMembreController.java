/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.ajoutmembre;

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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.gestionbibliotheque.ajoutlivre.AjoutLivreController;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutMembreController implements Initializable {

    private int increment; 
    
    @FXML
    private TabPane rootPane;
    
    @FXML
    private JFXTextField nomprenomprof;
    @FXML
    private JFXTextField idprof;
    @FXML
    private JFXTextField departementprof;
    @FXML
    private JFXTextField telprof;
    @FXML
    private JFXTextField emailprof;
    @FXML
    private JFXButton BoutonEnregistrerProf;
    @FXML
    private JFXButton BoutonAnnuler1;
    @FXML
    private AnchorPane rootPane1;
    @FXML
    private JFXTextField nomprenometd;
    @FXML
    private JFXTextField idetd;
    @FXML
    private JFXTextField specialiteetd;
    @FXML
    private JFXTextField groupeetd;
    @FXML
    private JFXTextField teletd;
    @FXML
    private JFXTextField emailetd;
    @FXML
    private JFXButton BoutonEnregistrerEtd;
    @FXML
    private JFXButton BoutonAnnuler11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    




    @FXML
    private void ajouterProf(ActionEvent event) throws IOException {
        
        FileReader fileinc;
        try {
            
            fileinc = new FileReader("incrementProfs.txt");
            BufferedReader reader = new BufferedReader(fileinc);
            String text; 
            text = reader.readLine();
            increment = Integer.parseInt(text);
            reader.close();
            PrintWriter pw = new PrintWriter("incrementProfs.txt");
            pw.close();


        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjoutMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String nomprenommem = nomprenomprof.getText();
        String idmem = idprof.getText();
        String telmem = telprof.getText();
        String emailmem = emailprof.getText();
        String departementmem = departementprof.getText();
        
        boolean flag = nomprenommem.isEmpty()||idmem.isEmpty()||telmem.isEmpty()||emailmem.isEmpty()||departementmem.isEmpty();
         if (flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }
         
          StringBuilder sb = new StringBuilder();
        sb.append(nomprenommem+",");
        sb.append(idmem+increment+",");
        increment++;
        sb.append(departementmem+",");
        sb.append(telmem+",");
        sb.append(emailmem);
        sb.append(System.lineSeparator());
         
         File file = new File("BaseProfesseurs.txt");
        try {
            FileWriter W = new FileWriter(file, true);
            W.write(sb.toString());
            W.close();
        } catch (IOException ex) {
            Logger.getLogger(AjoutLivreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Saving the increment value***************
        StringBuilder sb1 = new StringBuilder();
        sb1.append("");
        sb1.append(increment);
        String strI = sb1.toString();
        File filesav = new File("incrementProfs.txt");
        try {
            FileWriter W1 = new FileWriter(filesav, true);
            W1.write(strI);
            W1.close();
        } catch (IOException ex) {
            Logger.getLogger(AjoutMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*****************************************
        
        nomprenomprof.clear();
        idprof.clear();
        telprof.clear();
        emailprof.clear();
        departementprof.clear();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Professeur Ajouté");
        Optional<ButtonType> response = alert.showAndWait();
    }


    @FXML
    private void ajouterEtudiant(ActionEvent event) throws IOException {
        
        FileReader fileinc1;
        try {
            
            fileinc1 = new FileReader("incrementEtudiants.txt");
            BufferedReader reader = new BufferedReader(fileinc1);
            String text; 
            text = reader.readLine();
            increment = Integer.parseInt(text);
            reader.close();
            PrintWriter pw = new PrintWriter("incrementEtudiants.txt");
            pw.close();


        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjoutMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String nomprenommem = nomprenometd.getText();
        String idmem = idetd.getText();
        String telmem = teletd.getText();
        String emailmem = emailetd.getText();
        String groupemem = groupeetd.getText();
        String specialitemem = specialiteetd.getText();
        
        boolean flag = nomprenommem.isEmpty()||idmem.isEmpty()||telmem.isEmpty()||emailmem.isEmpty()||groupemem.isEmpty()||specialitemem.isEmpty();
         if (flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }
         
        StringBuilder sb = new StringBuilder();
        sb.append(nomprenommem+",");
        sb.append(idmem+increment+",");
        increment++;
        sb.append(specialitemem+",");
        sb.append(groupemem+",");
        sb.append(telmem+",");
        sb.append(emailmem);
        sb.append(System.lineSeparator());
         
         File file = new File("BaseEtudiants.txt");
        try {
            FileWriter W = new FileWriter(file, true);
            W.write(sb.toString());
            W.close();
        } catch (IOException ex) {
            Logger.getLogger(AjoutMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Saving the increment value***************
        StringBuilder sb1 = new StringBuilder();
        sb1.append("");
        sb1.append(increment);
        String strI = sb1.toString();
        File filesav = new File("incrementEtudiants.txt");
        try {
            FileWriter W1 = new FileWriter(filesav, true);
            W1.write(strI);
            W1.close();
        } catch (IOException ex) {
            Logger.getLogger(AjoutMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*****************************************
        
        nomprenometd.clear();
        idetd.clear();
        teletd.clear();
        emailetd.clear();
        specialiteetd.clear();
        groupeetd.clear();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etudiant Ajouté");
        Optional<ButtonType> response = alert.showAndWait();
    }
        @FXML
    private void annuler1(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    @FXML
    
    private void annuler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
