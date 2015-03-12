/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import com.daBandit.Holder;
import com.daBandit.InsufficientFundsException;
import com.daBandit.InvalidAmountException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

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
    @FXML
    private TextArea exceptionText;
    private NumberFormatException nfe;
    private Exception e;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Holder newHolder = new Holder(FXMLAccountsViewController.getHolder());
      exceptionText.setVisible(false);
        //Holder newHolder = new Holder(Node.getUserData());
      System.out.println("Holder checking: " + newHolder.getChecking().getBalance());
      System.out.println("Holder savings: " + newHolder.getSavings().getBalance());
      
    } 

    

    @FXML
    private void processDeposit(ActionEvent event) throws InvalidAmountException, NumberFormatException {
        Holder newHolder = new Holder(FXMLAccountsViewController.getHolder());
        // Process Checking Deposit
        String amtChecking = withdrawlChecking.getText();
        
        //Process Savings Deposit
        
        String amtSavings = withdrawlSavings.getText();
       
        
        
        try{
            Double amtCheck = Double.parseDouble(amtChecking);
            newHolder.getChecking().deposit(amtCheck);
            Double amtSav = Double.parseDouble(amtSavings);
            newHolder.getSavings().deposit(amtSav);
            ((Node)(event.getSource())).getScene().getWindow().hide();
         }catch (InvalidAmountException e){
             exceptionText.clear();
             exceptionText.setVisible(true);
            exceptionText.setText(e.getMessage());
        } catch (NumberFormatException nfe){
            exceptionText.clear();
             exceptionText.setVisible(true);
             exceptionText.setText("NOT A NUMBER!!\n" + "Please enter a valid amount!!\n" +
                     "For Savings/Checking account!\n");
            exceptionText.appendText(nfe.getMessage());
            
        }
       
      // ((Node)(event.getSource())).getScene().getWindow().hide();
      
    }

    @FXML
    private void processWithdrawal(ActionEvent event) throws InsufficientFundsException, InvalidAmountException, NumberFormatException{
        Holder newHolder = new Holder(FXMLAccountsViewController.getHolder());
        //Process Checking Withdrawal
       
       
        
        //Process Savings Withdrawal
          
          
          try{
            String amtChecking = withdrawlChecking.getText();
            Double amtCheck = Double.parseDouble(amtChecking);
            newHolder.getChecking().withdrawl(amtCheck);
            String amtSavings = withdrawlSavings.getText();
            Double amt = Double.parseDouble(amtSavings);
            newHolder.getSavings().withdrawl(amt);
            ((Node)(event.getSource())).getScene().getWindow().hide();
          }catch (InsufficientFundsException | InvalidAmountException iae){
                exceptionText.clear();
                exceptionText.setVisible(true);
                exceptionText.setText(iae.getMessage());
            
          }catch (NumberFormatException nfe){
            exceptionText.clear();
            exceptionText.setVisible(true);
            exceptionText.setText("NOT A NUMBER!!\n" + "Please enter a valid amount!!\n" +
                    "For Savings account\n");
            exceptionText.appendText(nfe.getMessage());
          }
          
    }
    
}
