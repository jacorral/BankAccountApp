/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author angel
 */
@XmlRootElement(name = "holders")
    public class HolderListWrapper{
        private List<Holder> holders;
        
        @XmlElement(name = "holder")
        public List<Holder> getHolders(){
            return holders;
        }
        public void setHolders(List<Holder> holders){
            this.holders = holders;
        }
        
    }
