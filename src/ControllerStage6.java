import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.InputEvent;
import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerStage6 implements Initializable {

    public Button newPrescriptionButton;
    public Button editCustomerButton;
    public Button exitButton;
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label cedulaLabel;
    public Label dobLabel;
    public Label phone1Label;
    public Label phone2Label;

    public TableView<Prescription> prescriptionTable;
    public TableColumn<Prescription, Date> dateColumn;
    public TableColumn<Prescription, Double> odEsfColumn;
    public TableColumn<Prescription, Double> odCilColumn;
    public TableColumn<Prescription, Double> odEjeColumn;
    public TableColumn<Prescription, Double> odAdColumn;
    public TableColumn<Prescription, Double> oiEsfColumn;
    public TableColumn<Prescription, Double> oiCilColumn;
    public TableColumn<Prescription, Double> oiEjeColumn;
    public TableColumn<Prescription, Double> oiAdColumn;
    public TableColumn viewButtonColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void newPrescriptionButtonClicked(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage7.fxml"));
            Parent root = (Parent) loader.load();

            ControllerStage7 controllerStage7 = loader.getController();
            controllerStage7.setCustomer(cedulaLabel.getText());


            Stage stage = (Stage) newPrescriptionButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editCustomerButtonClicked(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage4.fxml"));
            Parent root = (Parent) loader.load();

            ControllerStage4 controllerStage4 = loader.getController();
            controllerStage4.setCustomer(cedulaLabel.getText());

            Stage stage = (Stage) editCustomerButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewButtonClicked(int prescriptionID){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage9.fxml"));
            Parent root = (Parent) loader.load();

            ControllerStage9 controllerStage9 = loader.getController();
            controllerStage9.setPrescription(prescriptionID);

            Stage stage = (Stage) editCustomerButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCustomer(String cedula){
        loadLabelValues(cedula);
        loadPrescriptions(cedula);
    }

    private void loadLabelValues(String cedula){
        cedulaLabel.setText(cedula);
        Customer currCust = new Customer(cedula);
        firstNameLabel.setText(currCust.getFirstName());
        lastNameLabel.setText(currCust.getLastName());
        phone1Label.setText(String.valueOf(currCust.getPhone1()));
        dobLabel.setText((currCust.getDateOfBirth().toString()));


        if(currCust.getPhone2()==0) {
            phone2Label.setText("");
        }
        else{
            phone2Label.setText(String.valueOf(currCust.getPhone2()));
        }
    }

    private void loadPrescriptions(String cedula){
        Customer customer = new Customer(cedula);
        Prescription prescription = new Prescription();

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        odEsfColumn.setCellValueFactory(new PropertyValueFactory<>("rodEsf"));
        odCilColumn.setCellValueFactory(new PropertyValueFactory<>("rodCil"));
        odEjeColumn.setCellValueFactory(new PropertyValueFactory<>("rodEje"));
        odAdColumn.setCellValueFactory(new PropertyValueFactory<>("rodAd"));
        oiEsfColumn.setCellValueFactory(new PropertyValueFactory<>("roiEsf"));
        oiCilColumn.setCellValueFactory(new PropertyValueFactory<>("roiCil"));
        oiEjeColumn.setCellValueFactory(new PropertyValueFactory<>("roiEje"));
        oiAdColumn.setCellValueFactory(new PropertyValueFactory<>("roiAd"));


        //Lets create a cell factory to insert a button in every row
        Callback<TableColumn<Prescription,String>, TableCell<Prescription,String>> cellFactory= (param) ->{
            //Make the tablecell containing button
            final TableCell<Prescription,String> cell = new TableCell<Prescription,String>(){
                //override updateItem method

                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item,empty);

                    //ensure that cell is created only on non-empty rows
                    if(empty){
                        setGraphic(null);;
                        setText(null);
                    }
                    else{
                        //Create the Button
                        final Button viewButton = new Button("Ver");
                        //Attach listerner on button
                        viewButton.setOnAction(event -> {
                            //Extract the clicked Prescription Object
                            Prescription prescription1 = getTableView().getItems().get(getIndex());
                            //Run the viewButtonClicked method and pass the prescription ID
                            viewButtonClicked(prescription1.getPrescriptionID());
                        });

                        //Set the Created Button to Cell
                        setGraphic(viewButton);
                        setText(null);
                    }
                }

            };

            //return the cell created
            return cell;
        };

        //Set custom factory  to the correct column
        viewButtonColumn.setCellFactory(cellFactory);

        //Set items in all column
        prescriptionTable.setItems(prescription.getAllPrescriptions(customer.getCustomerID()));
    }

    public void exitButtonClicked(){
        StageSwitcher stageSwitcher = new StageSwitcher(exitButton,"Stage1.fxml");
        stageSwitcher.switchStages();
    }

}
