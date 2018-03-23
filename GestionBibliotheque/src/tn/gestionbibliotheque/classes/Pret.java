/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestionbibliotheque.classes;

import java.text.SimpleDateFormat;

/**
 *
 * @author ASUS
 */
public class Pret {
    
    private String IDMembre;
    private String IDLivre;
    private String datePret = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    private String nbRenouvlement = "0";
    
    public Pret(){}
    
    public Pret(String IDMembre, String IDLivre){
        this.IDLivre = IDLivre;
        this.IDMembre = IDMembre;
    }
    
    public Pret(String IDMembre, String IDLivre, String nbRenouvlement){
        this.IDLivre = IDLivre;
        this.IDMembre = IDMembre;
        this.nbRenouvlement = nbRenouvlement;
    }
    
    public Pret(String IDMembre, String IDLivre, String datePret, String nbRenouvlement){
        this.IDLivre = IDLivre;
        this.IDMembre = IDMembre;
        this.datePret = datePret;
        this.nbRenouvlement = nbRenouvlement;
    }
    
    @Override
    public String toString() {
        return IDMembre + "," + IDLivre + "," + datePret + "," + nbRenouvlement;
    }
    
    
}
