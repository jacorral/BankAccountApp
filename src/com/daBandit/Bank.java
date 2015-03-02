/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 *
 * @author jacorral
 */
public class Bank {
    private final ObservableList<Holder> observableList = 
            FXCollections.observableArrayList();
    
    private final ObservableMap<Long, Holder> observableMap =
            FXCollections.observableHashMap();
    
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
    
    public void addListener(MapChangeListener<? super Long, ? super Holder> ml){
        observableMap.addListener(ml);
    }
    
    public void addHolder(Holder h){
        Holder holder = new Holder(h);
        observableMap.put(holder.id, holder);
    }
    
    public void updateHolder(Holder h){
        Holder holder = new Holder(h);
        observableMap.put(holder.id, holder);
    }
    
    public void deleteHolder(Holder h){
        observableMap.remove(h.id);
    }
    /*
    public void addListener(ListChangeListener<? super Holder> hl){
        observableList.addListener(hl);
    } */
    
    
    public void addHolder(String fn, String ln){
        Holder ah = new Holder(fn, ln);
        observableMap.put(ah.id, ah);
    }
    
    public List<Holder> getAllHolders(){
        List<Holder> copyList = new ArrayList<>();
        observableMap.values().stream().forEach((h) ->
            copyList.add(new Holder(h)));    
        
        return copyList;
    }
}
