/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import tn.gestionbibliotheque.listelivres.ListeLivresController;

/**
 *
 * @author ASUS
 */
public class Livre {
    
    private String titre;
    private String id;
    private String auteur;
    private String editeur;
    private Boolean estDisponible;
    private static int increment = 1;
    
    public Livre(){}
    
    public Livre(String titre, String id, String auteur, String editeur){
        this.titre = titre; 
        this.id = id+increment; 
        this.auteur = auteur; 
        this.editeur = editeur; 
        this.estDisponible = true;
    }
    
      public Livre(String titre, String id, String auteur, String editeur , Boolean estDisponible){
        this.titre = titre; 
        this.id = id; 
        this.auteur = auteur; 
        this.editeur = editeur; 
        this.estDisponible = estDisponible;
    }

    @Override
    public String toString() {
        return  titre +","+ id+"," + auteur+","+ editeur+"," + estDisponible;
    }

    public String getTitre() {
        return titre;
    }

    public String getId() {
        return id;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public Boolean getEstDisponible() {
        return estDisponible;
    }

    public static void getBooks(ObservableList<Livre> list) throws IOException{
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
           Boolean Gdispox = false;
           if(Gdispo.contentEquals("1")|| Gdispo.contentEquals("true")) Gdispox = true;
           list.add(new Livre(Gtitre, Gid, Gauteur, Gediteur, Gdispox));
           }while(1 == 1);
           reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeLivresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }  
      
}
