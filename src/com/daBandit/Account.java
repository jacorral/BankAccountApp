/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Jose Corral
 */
public class Account implements Serializable {

    public enum Type {

        CHECKING, SAVINGS
    }
    private final DoubleProperty balance = new SimpleDoubleProperty(this, "balance");
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>(this, "type");

    protected Account() {
    }

    List<Double> transactions = new ArrayList<>();

    //constructor sets the initial balance of the account to $50
    public Account(Account.Type type) {
        this.type.set(type);
        this.setBalance(50.0);
        this.transactions.add(this.getBalance());
        
    }

    public Double getBalance() {
        return balance.get();
    }

    public void setBalance(Double value) {
        balance.set(value);
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    public Type getType() {
        return type.get();
    }

    public void setType(Type value) {
        type.set(value);
    }

    public ObjectProperty typeProperty() {
        return type;
    }

    /*  Method to deposit amount specified
        Record amount on transactions list
     */
    public void deposit(Double amt) {
        if (amt < 0){
        System.out.println("Please enter a valid amount!");
            
        } else{
        transactions.add(amt);
        Double tmp = this.balance.get();
        tmp = tmp + amt;
        this.balance.set(tmp);
        } 
    }

    /*  Method to withdrawl specified amount
        Record amount on transactons list
   
     */
    public void withdrawl(Double amt) {
        
        Double tmp = this.balance.get();
        tmp = tmp - amt;
        if (tmp < 0){
            System.out.println("OUT OF FUNDS!!! "
                    + "Please enter a differnt amount.  Overdrawn by:  " + (-tmp));
        }else{
        this.balance.set(tmp);
        transactions.add(-amt);
        }
    }

    /*   Method that returns all of the transactions
     in an array list for the account created
     */
    public  ArrayList<Double> getAllTransactions() {
        ArrayList<Double> copyList = new ArrayList<>();
        this.transactions.forEach((amt) -> {
            copyList.add(amt);
        });
        return copyList;
    }
}
