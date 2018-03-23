/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Wrapper pour les parametres
package tn.gestionbibliotheque.parametres;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.apache.commons.codec.digest.DigestUtils;

public class Preferences {
    int nbJoursPretMax;
    float penaliteParJour;
    String NomUtilisateur;
    String motDePasse;
    
    public Preferences(){
        nbJoursPretMax = 14;
        penaliteParJour = 1;
        NomUtilisateur = "admin";
        setMotDePasse("admin");
    }

    public int getNbJoursPretMax() {
        return nbJoursPretMax;
    }

    public float getPenaliteParJour() {
        return penaliteParJour;
    }

    public String getNomUtilisateur() {
        return NomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNbJoursPretMax(int nbJoursPretMax) {
        this.nbJoursPretMax = nbJoursPretMax;
    }

    public void setPenaliteParJour(float penaliteParJour) {
        this.penaliteParJour = penaliteParJour;
    }

    public void setNomUtilisateur(String NomUtilisateur) {
        this.NomUtilisateur = NomUtilisateur;
    }

    public void setMotDePasse(String motDePasse) {
        if(motDePasse.length()<16){
        this.motDePasse = DigestUtils.sha1Hex(motDePasse);
        }else{
            this.motDePasse = motDePasse;
        }
    }
    
    public static void initConfig(){
        Preferences preference = new Preferences();
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter("config.txt");
            gson.toJson(preference, writer);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Preferences getPreferences(){
        Preferences preferences = new Preferences();
        Gson gson = new Gson();
        try {
            preferences = gson.fromJson(new FileReader("config.txt"), Preferences.class);
                    } catch (FileNotFoundException ex) {
                        initConfig();
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preferences;
    }
    
    public static void ecrirePreferencesFichier(Preferences preference){
          
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter("config.txt");
            gson.toJson(preference, writer);
            writer.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Modifications Enregistrées");
            alert.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Problème de mise à jour");
            alert.showAndWait();
        }
    }
}
