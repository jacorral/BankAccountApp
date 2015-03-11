/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author angel
 */
public class Holder implements Serializable {
    public final long id;
   // public final String uid;
    private static long count = 1000;
    private final StringProperty firstname = new SimpleStringProperty(this, "firstname", "");
    private final StringProperty lastname = new SimpleStringProperty(this, "lastname", "");
    private final StringProperty userId = new SimpleStringProperty(this, "userId", "");
   
    
    public transient final ObjectProperty<Account> savings =
            new SimpleObjectProperty<>(this, "savings", new Account(Account.Type.SAVINGS));
    private transient final ObjectProperty<Account> checking =
            new SimpleObjectProperty<>(this, "checking", new Account(Account.Type.CHECKING));
    
    private final StringBinding fullNameBinding = new StringBinding() {
        {
            super.bind(firstname, lastname);
        }
        
          @Override
            protected String computeValue() {
                StringBuilder sb = new StringBuilder();
                if (!firstname.get().isEmpty()){
                    sb.append(firstname.get());
                }
                if (!lastname.get().isEmpty()){
                    sb.append(" ").append(lastname.get());
                }
                
                return sb.toString();
            }
    };       
    /*
    Define/implement a user id by concatinating the firstname and the id
    via a string builder using a StringBinding 
    */
    private final StringBinding UID = new StringBinding(){
        {
            super.bind(firstname, userId);
        }
        @Override
        protected String computeValue(){
            StringBuilder sb = new StringBuilder();
            if (!firstname.get().isEmpty()){
                    sb.append(firstname.get());
                }
                if (!lastname.get().isEmpty()){
                    sb.append(" ").append(lastname.get());
                }
                
                    
                    
        return sb.toString();
        }
    };
    protected Holder(){
        this.id = count;
    }
   
    
    public Holder(Holder holder){
        this.firstname.set(holder.getFirstname());
        this.lastname.set(holder.getLastname());
        this.checking.set((holder.getChecking()));
        this.savings.set(holder.getSavings());
        this.id = holder.id;
        
    }
   
    
    public Holder(String fn, String ln){
        this.firstname.set(fn);
        this.lastname.set(ln);
        this.id = count;
        count++;
    }
    
     private final ReadOnlyStringWrapper fullname =
           new ReadOnlyStringWrapper(this, "fullname");
     
     public final ReadOnlyStringProperty fullnameProperty(){
         return fullname.getReadOnlyProperty();
     }
     
     public final String getFullname(){
         return fullname.get();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
                if (!firstname.get().isEmpty()){
                    sb.append(firstname.get());
                }
                if (!lastname.get().isEmpty()){
                    sb.append(" ").append(lastname.get());
                }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.firstname);
        hash = 37 * hash + Objects.hashCode(this.lastname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Holder other = (Holder) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
