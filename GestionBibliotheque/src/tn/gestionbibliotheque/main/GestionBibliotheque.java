/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import tn.gestionbibliotheque.util.GestionBibliothequeUtil;

public class GestionBibliotheque extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/tn/gestionbibliotheque/login/Login.fxml"));
                
		Scene scene = new Scene(root);
                
		stage.setScene(scene);
		stage.show();
                stage.setTitle("Gestion Bibliothèque");
                
                GestionBibliothequeUtil.setStageIcon(stage);
	}

}

