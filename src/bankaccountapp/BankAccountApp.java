/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import com.daBandit.Holder;
import com.daBandit.HolderListWrapper;
import com.daBandit.Bank;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author jacorral
 */
public class BankAccountApp extends Application {
    public Stage stage;
    private Bank bank;
    public BorderPane rootLayout;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLBankView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Bank Account App");
        stage.setScene(scene);
        stage.show();
    }
    
    
    public void initRootLayout(){
        try{
            //load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BankAccountApp.class.getResource("FXMLBankView.fxml"));
            rootLayout = (BorderPane)loader.load();
            
            Scene scene = new Scene (rootLayout);
            stage.setScene(scene);
            
            FXMLAccountsViewController controller = loader.getController();
        //    controller.setMainApp(this);
            stage.show();;
        }catch (IOException e){
            e.printStackTrace();
        }
        
        File file = getHolderFilePath();
        if (file != null){
            loadHolderDataFromFile(file);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void loadHolderDataFromFile(File file){
     try{
         JAXBContext context = JAXBContext.newInstance(HolderListWrapper.class);
         Unmarshaller um = context.createUnmarshaller();
         
         //Reading XML from the file and unmarshalling
         HolderListWrapper wrapper = (HolderListWrapper) um.unmarshal(file);
         int count = wrapper.getHolders().size();
         List<Holder> holderList = new ArrayList<>(wrapper.getHolders());
         for (int i = 0; i < count; i++ ){
             bank.addHolder(holderList.get(i));
         }
         
         //bank.addHolder(wrapper.getHolders());
         
         setHolderFilePath(file);
        
     }   catch (Exception e){
         System.out.println("Exception " + e.getMessage());
         /*Dialogs.create()
                 .title("Error")
                 .masthead("Could not load data from file:\n" + file.getPath());
                 */
     }
    }
    
    public void saveHolderDataToFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(HolderListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //wrapping Holder data
            HolderListWrapper wrapper = new HolderListWrapper();
            wrapper.setHolders(bank.getAllHolders());
            //Marshalling and saving XML to the file
            m.marshal(wrapper, file);
            //Save the file path to the registry
            
            setHolderFilePath(file);
        }catch(Exception e){
            System.out.println("Exception " + e.getMessage());
            //Dialogs.create().title("Error")
        }
    }
    
    
    /**
 * Returns the person file preference, i.e. the file that was last opened.
 * The preference is read from the OS specific registry. If no such
 * preference can be found, null is returned.
 * 
 * @return
 */
    public File getHolderFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(BankAccountApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null){
            return new File(filePath);
        }else{
            return null;
        }
        
    }
    
    /**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
    public void setHolderFilePath(File file){
        Preferences prefs = Preferences.userNodeForPackage(BankAccountApp.class);
        if (file != null){
            prefs.put("filePath", file.getPath());
            
            //Update the stage title
            stage.setTitle("BankAccount App - " + file.getName());
        }else{
            prefs.remove("filePath");
            
            //update the stage title
            stage.setTitle("BankAccountApp");
        }
    }
    
}
