/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;
import tn.gestionbibliotheque.main.FXMLDocumentController;
import tn.gestionbibliotheque.parametres.Preferences;
import tn.gestionbibliotheque.util.GestionBibliothequeUtil;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField nomutil;
    @FXML
    private JFXPasswordField motpass;

    Preferences preference;
    @FXML
    private Label libelleTitre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preference = Preferences.getPreferences();
    }    

    @FXML
    private void ActionLogin(ActionEvent event) {
        libelleTitre.setText("Gestion Bibliothèque");
        libelleTitre.setStyle("-fx-background-color: black; -fx-text-fill: white");
        String uname = nomutil.getText();
        String pword = DigestUtils.sha1Hex(motpass.getText());
        
        if(uname.equals(preference.getNomUtilisateur()) && pword.equals(preference.getMotDePasse())){
            loadMain();
            closeStage();
        }else{
            libelleTitre.setText("Données Invalides");
            
            libelleTitre.setStyle("-fx-background-color: black; -fx-text-fill: #d32f2f");
            //libelleTitre.setStyle("-fx-text-fill: #d32f2f");
        }
        
    }

    @FXML
    private void ActionAnnuler(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void closeStage() {
        ((Stage)nomutil.getScene().getWindow()).close();
    }
    
        void loadMain(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/gestionbibliotheque/main/FXMLDocument.fxml"));
            loader.setController(new FXMLDocumentController());
            Parent parent = loader.load(getClass().getResource("/tn/gestionbibliotheque/main/FXMLDocument.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Gestion Bibliothèque");
            stage.setScene(new Scene(parent));
            stage.show();
            
            GestionBibliothequeUtil.setStageIcon(stage);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
