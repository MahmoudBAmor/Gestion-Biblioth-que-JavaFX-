/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.classes;

/**
 *
 * @author ASUS
 */
public class Professeur extends Membre {
    
    private String Departement; 
    
    public Professeur(){}
    public Professeur(String NomPrenom, String ID, String Tel, String Email, String Departement){
        super(NomPrenom, ID, Tel, Email);
        this.Departement = Departement;
    }

    public String getDepartement() {
        return Departement;
    }

    @Override
    public String toString() {
        return super.toString()+","+Departement;
    }
    
    
    
}
