/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;



/**
 *
 * @author Jose Corral
 */
public class Account implements Serializable {
    
   public enum Type{
       CHECKING, SAVINGS
   }
    private final LongProperty balance = new SimpleLongProperty(this, "balance", 0);
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>(this, "type");

    protected Account() {
    }
    
    List<Double> transactions = new ArrayList<>();
    
    public Account (Account.Type type){
        this.type.set(type);
    }
    
    
    private long getBalance() {
        return balance.get();
    }

    private void setBalance(long value) {
        balance.set(value);
    }

    private LongProperty balanceProperty() {
        return balance;
    }
    
      


    private Type getType() {
        return type.get();
    }

    private void setType(Type value) {
        type.set(value);
    }

    private ObjectProperty typeProperty() {
        return type;
    }
    
   private void deposit(Double amt){
       transactions.add(amt);
       balance.add(amt);
       
   }
   
   public void withdrawl(Double amt){
       transactions.add(amt);
       balance.subtract(amt);
   }
   
}
