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

/**
 *
 * @author angel
 */
public class Holder implements Serializable {
    public final long id;
    private static long count = 1000;
    private final StringProperty firstname = new SimpleStringProperty(this, "firstname", "");
    private final StringProperty lastname = new SimpleStringProperty(this, "lastname", "");
    
    public final ObjectProperty<Account> savings =
            new SimpleObjectProperty<>(this, "savings", new Account(Account.Type.SAVINGS));
    private final ObjectProperty<Account> checking =
            new SimpleObjectProperty<>(this, "checking", new Account(Account.Type.CHECKING));
   
    
    public Holder(Holder holder){
        this.firstname.set(holder.getFirstname());
        this.lastname.set(holder.getLastname());
        this.id = count;
        count++;
    }
   
    
    public Holder(String fn, String ln){
        this.firstname.set(fn);
        this.lastname.set(ln);
        this.id = count;
        count++;
    }
    
    
  

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

    public ObjectProperty<Account> savingsProperty() {
        return savings;
    }
    

    public Account getChecking() {
        return checking.get();
    }

    public void setChecking(Account value) {
        checking.set(value);
    }

    public ObjectProperty<Account> checkingProperty() {
        return checking;
    }
    
    
}
