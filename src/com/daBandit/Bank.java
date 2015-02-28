/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

import java.util.ArrayList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author jacorral
 */
public class Bank {
    public static Bank instance = null;
    
    ArrayList<Holder> holders = new ArrayList<>();
    

    protected Bank(){
    }
    
    public static Bank getInstance(){
        if (instance == null){
            instance = new Bank();
        }
        return instance;
    }
    
    
    
    public void addHolder(String fn, String ln){
        Holder ah = new Holder(fn, ln);
        holders.add(ah);
    }
    
    public ArrayList<Holder> getAllHolders(){
        ArrayList<Holder> copyList = new ArrayList<>();
        holders.forEach((p) ->{
            copyList.add(p);
        });
        return copyList;
    }
}
