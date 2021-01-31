import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.scene.control.*;
import javafx.stage.Stage;

public class ControllerStage1 implements Initializable {

    public Button searchButton;
    public TextField cedulaTextField;
    public Label rcname1;
    public Label rcname2;
    public Label rcname3;
    public Label rcname4;
    public Label rcname5;
    public Label rcid1;
    public Label rcid2;
    public Label rcid3;
    public Label rcid4;
    public Label rcid5;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Load recent Customers

        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;


        try {
            myStmt = myConn.prepareStatement("select Customer_ID from recentcustomers ORDER BY Time DESC");
            myRs = myStmt.executeQuery();
            int counter =0;
            while(myRs.next()){

                Customer customer = new Customer(myRs.getInt("Customer_ID"));
                counter++;

                if(counter==1){
                    rcname1.setText(customer.getFirstName()+" "+ customer.getLastName());
                    rcid1.setText(customer.getCedula());
                }
                if(counter==2){
                    rcname2.setText(customer.getFirstName()+" "+ customer.getLastName());
                    rcid2.setText(customer.getCedula());
                }
                if(counter==3){
                    rcname3.setText(customer.getFirstName()+" "+ customer.getLastName());
                    rcid3.setText(customer.getCedula());
                }
                if(counter==4){
                    rcname4.setText(customer.getFirstName()+" "+ customer.getLastName());
                    rcid4.setText(customer.getCedula());
                }
                if(counter==5){
                    rcname5.setText(customer.getFirstName()+" "+ customer.getLastName());
                    rcid5.setText(customer.getCedula());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
    }

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



    private void recentClicked(String cedula){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage6.fxml"));
            Parent root = (Parent) loader.load();

            ControllerStage6 controllerStage6 = loader.getController();
            controllerStage6.setCustomer(cedula);

            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.setScene(new Scene(root));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rcid1Clicked(){
        String cedula = rcid1.getText();
        if(cedula.equals("Cedula")){

        }
        else{
            recentClicked(cedula);
        }
    }
    public void rcid2Clicked(){
        String cedula = rcid2.getText();
        if(cedula.equals("Cedula")){

        }
        else{
            recentClicked(cedula);
        }
    }
    public void rcid3Clicked(){
        String cedula = rcid3.getText();
        if(cedula.equals("Cedula")){

        }
        else{
            recentClicked(cedula);
        }
    }
    public void rcid4Clicked(){
        String cedula = rcid4.getText();
        if(cedula.equals("Cedula")){

        }
        else{
            recentClicked(cedula);
        }
    }
    public void rcid5Clicked(){
        String cedula = rcid5.getText();
        if(cedula.equals("Cedula")){

        }
        else{
            recentClicked(cedula);
        }
    }
}
