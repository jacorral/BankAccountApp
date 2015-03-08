/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author angel
 */
public class FXMLWithdrawlController implements Initializable {
    @FXML
    private TextField withdrawlSavings;
    @FXML
    private TextField withdrawlChecking;
    @FXML
    private Button withdrawlProcess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void process(ActionEvent event) {
        String amtChecking = withdrawlChecking.getText();
        System.out.println("Checking amount: " + amtChecking);
        
         String amtSavings = withdrawlChecking.getText();
        System.out.println("Savings amount: " + amtSavings);
        
    }
    
}
