/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author angel
 */
public class Holder implements Serializable {
    public final long id;
    private static long count = 1000;
    private final StringProperty firstname = new SimpleStringProperty(this, "firstname", "");
    private final StringProperty lastname = new SimpleStringProperty(this, "lastname", "");
    private final ObjectProperty<Account> savings = new SimpleObjectProperty<>(this, "savings");
    private final ObjectProperty<Account> checking = new SimpleObjectProperty<>(this, "checking");
   
    //Not sure of these will be needed here
    public final ObservableMap<String, Double> observableCheckingMap = 
            FXCollections.observableMap(new ConcurrentHashMap<String, Double>());
    public final ObservableMap<String, Double> observableSavingsMap =
            FXCollections.observableMap(new ConcurrentHashMap<String, Double>());
   
    
    
    
    public Holder(String fn, String ln){
        this.firstname.set(fn);
        this.lastname.set(ln);
        this.savings.set(new Account(Account.Type.SAVINGS));
        this.checking.set(new Account(Account.Type.CHECKING));
        this.id = count;
        count++;
    }
   /* Not sure if these will be needed
   public void addCheckingListener(MapChangeListener<? super String, ? super Double> ml){
       observableCheckingMap.addListener(ml);
   }
   public void addSavingsListener(MapChangeListener<? super String, ? super Double> ml){
       observableSavingsMap.addListener(ml);
   }*/

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String value) {
        firstname.set(value);
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }
    

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String value) {
        lastname.set(value);
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }
   

    public Account getSavings() {
        return savings.get();
    }

    public void setSavings(Account value) {
        savings.set(value);
    }

    public ObjectProperty savingsProperty() {
        return savings;
    }
    

    public Account getChecking() {
        return checking.get();
    }

    public void setChecking(Account value) {
        checking.set(value);
    }

    public ObjectProperty checkingProperty() {
        return checking;
    }
    
  
    
}
