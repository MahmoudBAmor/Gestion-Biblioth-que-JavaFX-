/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.parametres;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SettingsController implements Initializable {

    @FXML
    private JFXTextField nJoursPret;
    @FXML
    private JFXTextField penaliteParJour;
    @FXML
    private JFXTextField nomUtilisateur;
    @FXML
    private JFXPasswordField motdpasse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initValParDefaut();
    }    

    @FXML
    private void actionEnregistrer(ActionEvent event) {
        Preferences preference = Preferences.getPreferences();
        preference.setNbJoursPretMax(Integer.parseInt(nJoursPret.getText()));
        preference.setPenaliteParJour(Float.parseFloat(penaliteParJour.getText()));
        preference.setNomUtilisateur(nomUtilisateur.getText());
        preference.setMotDePasse(motdpasse.getText());
        
        Preferences.ecrirePreferencesFichier(preference);
        
    }

    @FXML
    private void ActionAnnuler(ActionEvent event) {
        ((Stage)nJoursPret.getScene().getWindow()).close();
    }

    private void initValParDefaut() {
        Preferences preferences = Preferences.getPreferences();
        nJoursPret.setText(String.valueOf(preferences.getNbJoursPretMax()));
        penaliteParJour.setText(String.valueOf(preferences.getPenaliteParJour()));
        nomUtilisateur.setText(String.valueOf(preferences.getNomUtilisateur()));
        //motdpasse.setText(String.valueOf(preferences.getMotDePasse()));
    }
    
}
