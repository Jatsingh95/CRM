import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.scene.control.*;
import javafx.stage.Stage;

public class ControllerStage1 {

    public Button searchButton;
    public TextField cedulaTextField;

    public void searchButtonClicked(){
            Customer currCustomer = new Customer(){};
            String cedula = cedulaTextField.getText();

            //Check if the cedula entered exists
            if(currCustomer.checkIfCustomerExists(cedula)){
                //If it exists Open Stage 5 and pass in the customer cedula value. In stage5 user can check if customer name is correct before accessing customer page
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage5.fxml"));
                    Parent root = (Parent) loader.load();

                    ControllerStage5 controllerStage5 = loader.getController();
                    controllerStage5.setCustomer(cedula);

                    Stage stage = (Stage) searchButton.getScene().getWindow();
                    stage.setScene(new Scene(root));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                //If it does not exist, open Stage 2. In stage 2 user will be notified of non existing cedula and will be asked if new customer needs to be created.
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage2.fxml"));
                    Parent root = (Parent) loader.load();

                    ControllerStage2 controllerStage2 = loader.getController();
                    controllerStage2.setCustomer(cedula);

                    Stage stage = (Stage) searchButton.getScene().getWindow();
                    stage.setScene(new Scene(root));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
