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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

/**
 *
 * @author jacorral
 */
public class FXMLAccountsViewController implements Initializable {
private final Bank bank = Bank.getInstance();
    
    private Label label;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField checkingBalanceTextField;
    @FXML
    private TextField savingsBalanceTextField;
    @FXML
    private TextArea reportTextArea;
    @FXML
    private TreeView<?> holderTreeView;
    @FXML
    private Button summaryButton;
    @FXML
    private Button withdrawlButton;
    @FXML
    private Button depositButton;
    
    
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
        Holder th = null;
        for (int i = 0; i < hl.size(); i++){
          th =  hl.get(i);
          th.getChecking().deposit(-100.0);
          th.getChecking().deposit(200.0);
          th.getSavings().deposit(100.0);
          th.getSavings().withdrawl(500.0);
          System.out.println("ID:  " + th.id);
          System.out.println("First Name:  " + th.getFirstname());
         System.out.println("Last Name:  " + th.getLastname());
         System.out.println("checking balance:  "+ th.getChecking().getBalance());
         System.out.println("savings balance:  "+ th.getSavings().getBalance());
         System.out.println("Get all trans:  " + th.getChecking().getAllTransactions());
         System.out.println("Get all trans:  " + th.getSavings().getAllTransactions());
        }
        buildView(th);
        
       
    }
    
   private void buildView(Holder h){
       StringConverter sc = new DoubleStringConverter();
       idTextField.setText(Long.toString(h.id));
       firstnameTextField.textProperty().bindBidirectional(h.firstnameProperty());
       lastnameTextField.textProperty().bindBidirectional(h.lastnameProperty());
       
       savingsBalanceTextField.textProperty().bindBidirectional(h.getSavings().balanceProperty(), sc);
       checkingBalanceTextField.textProperty().bindBidirectional(h.getChecking().balanceProperty(), sc);
       
   }

    @FXML
    private void summaryAction(ActionEvent event) {
    }

    @FXML
    private void withdrawlAction(ActionEvent event) {
    }

    @FXML
    private void depostAction(ActionEvent event) {
    }
}
