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
public class Etudiant extends Membre{
    
    private String Specialite; 
    private String Groupe;
    private static String typeM = "Etudiant";
    
    public Etudiant(){}
    public Etudiant(String NomPrenom, String ID, String Tel, String Email, String Specialite, String Groupe){
        super(NomPrenom, ID, Tel, Email);
        this.Specialite = Specialite;
        this.Groupe = Groupe;
    }

    public String getSpecialite() {
        return Specialite;
    }

    public String getGroupe() {
        return Groupe;
    }

    public static String getType() {
        return typeM;
    }
    
    public String toString(){
        return super.toString()+","+Specialite+","+Groupe;
    }
    
    
}
