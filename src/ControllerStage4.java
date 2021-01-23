import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ControllerStage4 implements Initializable {

    //Create variables for buttons
    public Button confirmButton;
    public Button cancelButton;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField cedulaField;
    public DatePicker dobDatePicker;
    public TextField addressField;
    public TextField occupationField;
    public TextField phone1Field;
    public TextField phone2Field;
    public Label warningLabel;

    private int customerID;
    private String firstName;
    private String lastName;
    private String cedula;
    private Date dateOfBirth;
    private String address;
    private String occupation;
    private int phone1;
    private int phone2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setCustomer(String cedula){
        cedulaField.setText(cedula);
        Customer currCust = new Customer(cedula);
        firstNameField.setText(currCust.getFirstName());
        lastNameField.setText(currCust.getLastName());
        dobDatePicker.setValue((currCust.getDateOfBirth().toLocalDate()));
        addressField.setText(currCust.getAddress());
        occupationField.setText(currCust.getOccupation());
        phone1Field.setText(String.valueOf(currCust.getPhone1()));
        customerID=currCust.getCustomerID();

        if(currCust.getPhone2()==0) {
            phone2Field.setText("");
        }
        else{
            phone2Field.setText(String.valueOf(currCust.getPhone2()));
        }
    }

    public void cancelButtonClicked(){
        try {
            Customer customer = new Customer(customerID);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage6.fxml"));
            Parent root = (Parent) loader.load();

            ControllerStage6 controllerStage6 = loader.getController();
            controllerStage6.setCustomer(customer.getCedula());


            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void confirmButtonClicked(){

        //Check if the cedula entered already exists for another customer
        Customer customer = new Customer();
        if(customer.checkIfCedulaExists(cedulaField.getText(),customerID)){
            //If it exists show warning label
            warningLabel.setText("La cedula ingresada ya existe");
            warningLabel.setVisible(true);
        }
        else {
            //Continue with data validation
            if(validateInputData()) {
                //If all fields are okay create customer in database
                Customer newCust = new Customer(firstName,lastName,cedula,dateOfBirth,
                        address,occupation,phone1,phone2);
                newCust.setCustomerID(customerID);
                newCust.updateDatabase();

                // proceed to stage 6
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage6.fxml"));
                    Parent root = (Parent) loader.load();

                    ControllerStage6 controllerStage6 = loader.getController();
                    controllerStage6.setCustomer(cedula);



                    Stage stage = (Stage) confirmButton.getScene().getWindow();
                    stage.setScene(new Scene(root));


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    private boolean validateInputData(){
        if(validateFirstName()&&validateLastName()&&validateCedula()&&
                validateDOB()&&validateAddress()&&validateOccupation()&&
                validatePhone1()&&validatePhone2()){
            return true;

        }
        return false;
    }

    private boolean validateFirstName(){
        firstName=firstNameField.getText();
        firstName=firstName.trim().toUpperCase();

        if(firstName.length()==0){
            warningLabel.setText("Por favor ingrese el nombre");
            warningLabel.setVisible(true);
            return false;
        }

        for(int i = 0; i <firstName.length();i++){
            char currch =firstName.charAt(i);
            if(Character.isLetter(currch) || currch == ' '){
                continue;
            }
            else{
                warningLabel.setText("Nombre invalido");
                warningLabel.setVisible(true);
                return false;
            }
        }
        return true;
    }

    private boolean validateLastName(){
        lastName=lastNameField.getText();
        lastName=lastName.trim().toUpperCase();

        if(lastName.length()==0){
            warningLabel.setText("Por favor ingrese el apellido");
            warningLabel.setVisible(true);
            return false;
        }

        for(int i = 0; i <lastName.length();i++){
            char currch =lastName.charAt(i);
            if(Character.isLetter(currch) || currch == ' '){
                continue;
            }
            else{
                warningLabel.setText("Apellido invalido");
                warningLabel.setVisible(true);
                return false;
            }
        }
        return true;
    }

    private boolean validateCedula(){
        cedula=cedulaField.getText();
        if(cedula.length()==0){
            warningLabel.setText("Favor ingrese la cedula");
            warningLabel.setVisible(true);
            return false;
        }
        return true;
    }

    private boolean validateDOB(){
        if(dobDatePicker.getValue()==null){
            warningLabel.setText("Favor ingrese la fecha de nacimiento");
            warningLabel.setVisible(true);
            return false;
        }
        dateOfBirth=java.sql.Date.valueOf(dobDatePicker.getValue());
        return true;
    }

    private boolean validateAddress(){
        address=addressField.getText();
        return true;
    }

    private boolean validateOccupation(){
        occupation=occupationField.getText();
        return true;
    }

    private boolean validatePhone1(){
        if(phone1Field.getText().length()==0){
            warningLabel.setText("Favor ingrese un celular");
            warningLabel.setVisible(true);
            return false;
        }
        try{
            phone1=Integer.parseInt(phone1Field.getText());
        }
        catch (NumberFormatException e){
            warningLabel.setText("Favor ingrese un celular valido (solo numeros)");
            warningLabel.setVisible(true);
            return false;
        }
        if(phone1Field.getText().length()!=8){
            warningLabel.setText("Favor ingrese un celular valido (8 digitos)");
            warningLabel.setVisible(true);
            return false;
        }
        return true;
    }

    private boolean validatePhone2(){
        if(phone2Field.getText().length()==0){
            phone2=0;
            return true;
        }
        try{
            phone2=Integer.parseInt(phone2Field.getText());
        }
        catch (NumberFormatException e){
            warningLabel.setText("Favor ingrese un Celular2/Telefono valido (solo numeros)");
            warningLabel.setVisible(true);
            return false;
        }
        return true;
    }


}
