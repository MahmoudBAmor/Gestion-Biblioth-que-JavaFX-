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
public abstract class Membre {
    
    private String NomPrenom;
    private String ID;
    private String Tel;
    private String Email;
    
    public Membre(){}
    public Membre(String NomPrenom, String ID, String Tel, String Email){
        this.NomPrenom = NomPrenom;
        this.ID = ID;
        this.Tel = Tel;
        this.Email = Email;
    }

    public String getNomPrenom() {
        return NomPrenom;
    }

    public String getID() {
        return ID;
    }

    public String getTel() {
        return Tel;
    }

    public String getEmail() {
        return Email;
    }
    
    public String toString(){
        return NomPrenom+","+ID+","+Tel+","+Email;
    }
}
