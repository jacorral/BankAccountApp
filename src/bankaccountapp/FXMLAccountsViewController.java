/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import com.daBandit.Bank;
import com.daBandit.Holder;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
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
private ObservableList<Holder> holderList = FXCollections.observableArrayList();
    
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
        //testPrint();
        
        TreeItem<Holder> rootNode = new TreeItem<>(
            new Holder("Account Holders", ""));
        buildTreeView(rootNode);
        holderTreeView.setRoot(rootNode);
        holderTreeView.getRoot().setExpanded(true);
        holderTreeView.getSelectionModel().selectedItemProperty()
                .addListener(treeSelectionListener);
    }    
    
    
    public void buildBank(){
        Holder h1 = new Holder("Mickey", "Mouse");
        Holder h2 = new Holder("Angel", "Corral");
        Holder h3 = new Holder("Donald", "Duck");
        
        h1.getChecking().deposit(11234.56);
        h1.getSavings().deposit(24680.99);
        h1.getSavings().withdrawl(100.45);
        h1.getChecking().withdrawl(3456.33);
        
        h2.getChecking().deposit(9876.12);
        h2.getSavings().deposit(8945.23);
        
        h3.getChecking().deposit(8945.12);
        h3.getSavings().deposit(8645.89);
        
        bank.addHolder(h1);
        bank.addHolder(h2);
        bank.addHolder(h3);
        
    }
    public void testPrint(){
        List<Holder> hl = bank.getAllHolders();
        Holder th = null;
        for (int i = 0; i < hl.size(); i++){
          th =  hl.get(i);
          if(th.getFirstname()== "Mickey"){
              th.getChecking().deposit(35.56);
              th.getSavings().deposit(45.56);
          }else if(th.getFirstname() == "Angel"){
              th.getChecking().deposit(1456.24);
              th.getSavings().deposit(7893.90);
          }
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
       //savingsBalanceTextField.textProperty().bindBidirectional(h.getSavings().balanceProperty(), sc);
       //checkingBalanceTextField.textProperty().bindBidirectional(h.getChecking().balanceProperty(), sc);
       checkingBalanceTextField.textProperty().bindBidirectional
                (new SimpleDoubleProperty(h.getChecking().getBalance()), sc);
       savingsBalanceTextField.textProperty().bindBidirectional
               ((new SimpleDoubleProperty(h.getSavings().getBalance())), sc);
      
      // System.out.println("Savings balance: " + h.getSavings().getBalance());
   }
   
   private void buildTreeView(TreeItem<Holder> root){
       bank.addListener(holderTreeListener);
       
       bank.getAllHolders().stream().forEach((h)-> {
        root.getChildren().add(new TreeItem<>(h));
        System.out.println("Tree Item:  "+ h.getFirstname()+ " " + h.getSavings().getAllTransactions());
       });
       
   }
   
   private final MapChangeListener<Long,Holder> holderTreeListener =
           (change) -> {
               if (change.getValueAdded() != null){
                   for (TreeItem<Holder> node : holderTreeView.getRoot().getChildren()){
                       if (change.getKey().equals(node.getValue().id)){
                           node.setValue(change.getValueAdded());
                           return;
                       }
                   }
               }
               
           };
   
   
   private final ChangeListener<TreeItem<Holder>> treeSelectionListener =
           (ov, oldValue, newValue) -> {
           TreeItem<Holder> treeItem = newValue;
          // System.out.println("New tree Item" + newValue.getValue().getChecking().getAllTransactions());
          
           theHolder =  new Holder(treeItem.getValue());
            System.out.println("Name:  " + treeItem.getValue().getFirstname() + " " +
                        treeItem.getValue().getLastname());
            System.out.println("Savings balance:  " + treeItem.getValue().getSavings().getBalance() +
                   "  Transactions" + treeItem.getValue().getSavings().getAllTransactions()); 
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
