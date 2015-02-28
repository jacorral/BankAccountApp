/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import com.daBandit.Bank;
import com.daBandit.Holder;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author jacorral
 */
public class FXMLAccountsViewController implements Initializable {
private final Bank bank = Bank.getInstance();
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildBank();
        testPrint();
    }    
    
    
    public void buildBank(){
        bank.addHolder("Angel", "Corral");
        bank.addHolder("Jose", "Corral");
        
    }
    public void testPrint(){
        ArrayList<Holder> hl = bank.getAllHolders();
        Holder th;
        for (int i = 0; i < hl.size(); i++){
          th =  hl.get(i);
          th.getChecking().deposit(134.0);
          th.getSavings().withdrawl(20.0);
          System.out.println("ID:  " + th.id);
          System.out.println("First Name:  " + th.getFirstname());
         System.out.println("Last Name:  " + th.getLastname());
         System.out.println("checking balance:  "+ th.getChecking().getBalance());
         System.out.println("savings balance:  "+ th.getSavings().getBalance());
        }
        
       
    }
}
