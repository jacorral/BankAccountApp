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
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

/**
 *
 * @author jacorral
 */
public class FXMLAccountsViewController implements Initializable {
private final Bank bank = Bank.getInstance();
private Holder theHolder = null;
    
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
    private TreeView<Holder> holderTreeView;
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
        
        TreeItem<Holder> rootNode = new TreeItem<>(
            new Holder("Account Holders", ""));
        buildTreeView(rootNode);
        holderTreeView.setRoot(rootNode);
        holderTreeView.getRoot().setExpanded(true);
        holderTreeView.getSelectionModel().selectedItemProperty()
                .addListener(treeSelectionListener);
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
          th.getChecking().deposit(100.0);
          th.getChecking().withdrawl(75.00);
          th.getSavings().deposit(100.0);
          th.getSavings().withdrawl(50.0);
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
   
   private void buildTreeView(TreeItem<Holder> root){
      // bank.addListener(holderTreeListener);
       
       bank.getAllHolders().stream().forEach((h) ->{
           root.getChildren().add(new TreeItem<>(h));
       });
   }
   private final ChangeListener<TreeItem<Holder>> treeSelectionListener =
           (ov, oldValue, newValue) -> {
           TreeItem<Holder> treeItem = newValue;
           if (treeItem == null || treeItem.equals(holderTreeView.getRoot())){
            //clearForm();
            return;
            }
           theHolder = new Holder(treeItem.getValue());
           buildView(theHolder);
           
           };

    @FXML
    private void summaryAction(ActionEvent event) {
        System.out.println("Pressed Summary");
    }

    @FXML
    private void withdrawlAction(ActionEvent event) {
        System.out.println("Pressed Withdrawl");
    }

    @FXML
    private void depostAction(ActionEvent event) {
        System.out.println("Pressed Deposit");
    }
}
