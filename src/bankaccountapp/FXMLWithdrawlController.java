/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import com.daBandit.Holder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author angel
 */
public class FXMLWithdrawlController implements Initializable {
    private Holder newHolder;
    @FXML
    private TextField withdrawlSavings;
    @FXML
    private TextField withdrawlChecking;
    @FXML
    private Button deposit;
    @FXML
    private Button withdrawal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Holder newHolder = new Holder(FXMLAccountsViewController.getHolder());
        //Holder newHolder = new Holder(Node.getUserData());
      System.out.println("Holder checking: " + newHolder.getChecking().getBalance());
      System.out.println("Holder savings: " + newHolder.getSavings().getBalance());
    }    

    

    @FXML
    private void processDeposit(ActionEvent event) {
        Holder newHolder = new Holder(FXMLAccountsViewController.getHolder());
        // Process Checking Deposit
        String amtChecking = withdrawlChecking.getText();
        System.out.println("Checking amount: " + amtChecking);
        System.out.println("Holder checking balance: " + newHolder.getChecking().getBalance());
        Double amtCheck = Double.parseDouble(amtChecking);
        newHolder.getChecking().deposit(amtCheck);
        System.out.println("Checking amount: " + amtChecking);
        System.out.println("Holder checking balance: " + newHolder.getChecking().getBalance());
        
        //Process Savings Deposit
        String amtSavings = withdrawlSavings.getText();
        Double amtSav = Double.parseDouble(amtSavings);
        newHolder.getSavings().deposit(amtSav);
        
    }

    @FXML
    private void processWithdrawal(ActionEvent event) {
        Holder newHolder = new Holder(FXMLAccountsViewController.getHolder());
        //Process Checking Withdrawal
        String amtChecking = withdrawlChecking.getText();
        Double amtCheck = Double.parseDouble(amtChecking);
        newHolder.getChecking().withdrawl(amtCheck);
        //Process Savings Withdrawal
          
          String amtSavings = withdrawlSavings.getText();
          Double amt = Double.parseDouble(amtSavings);
          newHolder.getSavings().withdrawl(amt);
    }
    
}
