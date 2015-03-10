/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import com.daBandit.Bank;
import com.daBandit.Holder;
import com.daBandit.HolderListWrapper;
import com.daBandit.InsufficientFundsException;
import com.daBandit.InvalidAmountException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author jacorral
 */
public class FXMLAccountsViewController implements Initializable {

    public final Bank bank = Bank.getInstance();

    protected Holder theHolder = null;
    static Holder newHolder;
    private ObservableList<Holder> holderList = FXCollections.observableArrayList();

    private Currency currentCurrency;
    private NumberFormat currencyFormatter;

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
    private Button updateHolder;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            buildBank();
        } catch (InvalidAmountException ex) {
            Logger.getLogger(FXMLAccountsViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InsufficientFundsException ex) {
            Logger.getLogger(FXMLAccountsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        reportTextArea.setVisible(false);
        //testPrint();

        TreeItem<Holder> rootNode = new TreeItem<>(
                new Holder("Account Holders", ""));
        buildTreeView(rootNode);
        holderTreeView.setRoot(rootNode);
        holderTreeView.getRoot().setExpanded(true);
        holderTreeView.getSelectionModel().selectedItemProperty()
                .addListener(treeSelectionListener);
        // Holder newHolder = new Holder(theHolder);
    }
    

    public static Holder getHolder() {
        // Holder newHolder = new Holder(theHolder);
        return newHolder;
    }
//  Test/Working data
    public void buildBank() throws InvalidAmountException, InsufficientFundsException {
        Holder h1 = new Holder("Mickey", "Mouse");
        Holder h2 = new Holder("Angel", "Corral");
        Holder h3 = new Holder("Donald", "Duck");

        h1.getChecking().deposit(11234.56);
        h1.getSavings().deposit(24680.99);
        //h1.getSavings().withdrawl(100.00);
        h1.getChecking().withdrawl(3456.33);

        h2.getChecking().deposit(9876.12);
        h2.getSavings().deposit(8945.23);

        h3.getChecking().deposit(8945.12);
        h3.getSavings().deposit(8645.89);

        bank.addHolder(h1);
        bank.addHolder(h2);
        bank.addHolder(h3);
        bank.addHolder(new Holder("Minnie", "Mouse"));

    }

    public void testPrint() throws InvalidAmountException, InsufficientFundsException {
        List<Holder> hl = bank.getAllHolders();
        Holder th = null;
        for (int i = 0; i < hl.size(); i++) {
            th = hl.get(i);
            if (th.getFirstname() == "Mickey") {
                th.getChecking().deposit(35.56);
                th.getSavings().deposit(45.56);
            } else if (th.getFirstname() == "Angel") {
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
            System.out.println("checking balance:  " + th.getChecking().getBalance());
            System.out.println("savings balance:  " + th.getSavings().getBalance());
            System.out.println("Get all trans:  " + th.getChecking().getAllTransactions());
            System.out.println("Get all trans:  " + th.getSavings().getAllTransactions());
        }

        buildView(th);

    }

    //Method to build the main view
    private void buildView(Holder h) {
        Locale locale = new Locale("en", "US");
        StringConverter sc = new DoubleStringConverter();
        currentCurrency = Currency.getInstance(locale);
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        idTextField.setText(Long.toString(h.id));
        firstnameTextField.textProperty().bindBidirectional(h.firstnameProperty());
        lastnameTextField.textProperty().bindBidirectional(h.lastnameProperty());
        checkingBalanceTextField.textProperty().bindBidirectional
            ((new SimpleDoubleProperty(h.getChecking().getBalance())), currencyFormatter);
        savingsBalanceTextField.textProperty().bindBidirectional
            ((new SimpleDoubleProperty(h.getSavings().getBalance())), currencyFormatter);
        reportTextArea.setVisible(false);

    }

    private void buildTreeView(TreeItem<Holder> root) {
        bank.addListener(holderTreeListener);

        bank.getAllHolders().stream().forEach((h) -> {
            root.getChildren().add(new TreeItem<>(h));
            System.out.println("Tree Item:  " + h.getFirstname() + " " + h.getSavings().getAllTransactions());
        });

    }

    private final MapChangeListener<Long, Holder> holderTreeListener
            = (change) -> {
                if (change.getValueAdded() != null) {
                    for (TreeItem<Holder> node : holderTreeView.getRoot().getChildren()) {
                        if (change.getKey().equals(node.getValue().id)) {
                            node.setValue(change.getValueAdded());
                            return;
                        }
                    }
                }

            };
    //Listener for when a different holder is selected in the tree view
    private final ChangeListener<TreeItem<Holder>> treeSelectionListener
            = (ov, oldValue, newValue) -> {
                TreeItem<Holder> treeItem = newValue;
                theHolder = new Holder(treeItem.getValue());
                newHolder = new Holder(theHolder);  //new selected holder to send to Withdrawal/Deposit view
                System.out.println("Name:  " + treeItem.getValue().getFirstname() + " "
                        + treeItem.getValue().getLastname());
                System.out.println("Savings balance:  " + treeItem.getValue().getSavings().getBalance()
                        + "  Transactions" + treeItem.getValue().getSavings().getAllTransactions());
                buildView(theHolder);
                reportTextArea.clear();

            };

    //Building the Summary for each account
    @FXML
    private void summaryAction(ActionEvent event) throws IOException {
        System.out.println("Pressed Summary");

        String textSavings = ("Savings Transaction History"
                + "\nTransaction" + "\t\tAmount\n");
        reportTextArea.setText(textSavings);
        ArrayList<Double> sList = theHolder.getSavings().getAllTransactions();
        int sc = theHolder.getSavings().getAllTransactions().size();
        for (int i = 0; i < sc; i++) {
            System.out.println(i + "\t\t" + currencyFormatter.format(sList.get(i)));
            String transactions = (i + "\t\t\t\t" + currencyFormatter.format(sList.get(i)) + "\n");
            reportTextArea.appendText(transactions);
        }
        String savingsBalance = ("\nSavings balance = "
                + currencyFormatter.format(theHolder.getSavings().getBalance()));
        reportTextArea.appendText(savingsBalance);

        String textChecking = ("\n\nChecking Transaction History"
                + "\nTransaction" + "\t\tAmount\n");
        reportTextArea.appendText(textChecking);
        ArrayList<Double> cList = theHolder.getChecking().getAllTransactions();
        int cc = theHolder.getChecking().getAllTransactions().size();
        for (int i = 0; i < cc; i++) {
            System.out.println(i + "\t\t" + currencyFormatter.format(cList.get(i)));
            String transactions = (i + "\t\t\t\t" + currencyFormatter.format(cList.get(i)) + "\n");
            reportTextArea.appendText(transactions);
        }
        String checkingBalance = ("\nChecking balance = "
                + currencyFormatter.format(theHolder.getChecking().getBalance()));
        reportTextArea.appendText(checkingBalance);
        reportTextArea.setVisible(true);
       
    }

    //Load Withdrawal/Deposit window
    @FXML
    private void withdrawlAction(ActionEvent event) throws IOException {
        FXMLLoader loadView = new FXMLLoader(getClass().getResource("FXMLWithdrawl.fxml"));
        Parent root = loadView.load(getClass().getResource("FXMLWithdrawl.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Deposit/Withdrawal");
        stage.setScene(scene);
        stage.show();
        buildView(theHolder);
    }

    @FXML
    private void updateHolder(ActionEvent event) {
        bank.updateHolder(theHolder);
    }

}
