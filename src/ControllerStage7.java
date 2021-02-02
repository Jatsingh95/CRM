import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.Date;

public class ControllerStage7 implements Initializable {

    public Button acceptButton;
    public Button cancelButton;
    public Label cedulaLabel;

    public TextField rodEsfLabel;
    public TextField rodCilLabel;
    public TextField rodEjeLabel;
    public TextField rodAdLabel;

    public TextField roiEsfLabel;
    public TextField roiCilLabel;
    public TextField roiEjeLabel;
    public TextField roiAdLabel;

    public TextField pd1Label;
    public TextField pd2Label;
    public TextField avodLabel;
    public TextField avoiLabel;

    public TextField rlcodEsfLabel;
    public TextField rlcodCilLabel;
    public TextField rlcodEjeLabel;
    public TextField rlcodAdLabel;

    public TextField rlcoiEsfLabel;
    public TextField rlcoiCilLabel;
    public TextField rlcoiEjeLabel;
    public TextField rlcoiAdLabel;

    public TextField rpodEsfLabel;
    public TextField rpodCilLabel;
    public TextField rpodEjeLabel;
    public TextField rpodAdLabel;

    public TextField rpoiEsfLabel;
    public TextField rpoiCilLabel;
    public TextField rpoiEjeLabel;
    public TextField rpoiAdLabel;

    public TextArea notasLabel;
    public Label warningLabel;
    public ComboBox lensTypeComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LensType lensType = new LensType();
        ArrayList<String> lenstypes = lensType.getAllLensTypes();
        for(String x : lenstypes){
            lensTypeComboBox.getItems().add(x);
        }
    }

    public void setCustomer(String Cedula){
        cedulaLabel.setText(Cedula);
    }

    public void acceptButtonClicked(){
        if(createPrescription()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage6.fxml"));
                Parent root = (Parent) loader.load();

                ControllerStage6 controllerStage6 = loader.getController();
                controllerStage6.setCustomer(cedulaLabel.getText());


                Stage stage = (Stage) acceptButton.getScene().getWindow();
                stage.setScene(new Scene(root));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean createPrescription(){
        Prescription prescription = new Prescription();  //Create Prescription Object
        if(checkValues()){ //If all Values are acceptable

            //Set all user inputs to correct variables
            Customer currCust = new Customer(cedulaLabel.getText());
            prescription.setCustomerID(currCust.getCustomerID());

            prescription.setRodEsf(Double.parseDouble(rodEsfLabel.getText()));
            prescription.setRodCil(Double.parseDouble(rodCilLabel.getText()));
            prescription.setRodEje(Double.parseDouble(rodEjeLabel.getText()));
            prescription.setRodAd(Double.parseDouble(rodAdLabel.getText()));

            prescription.setRoiEsf(Double.parseDouble(roiEsfLabel.getText()));
            prescription.setRoiCil(Double.parseDouble(roiCilLabel.getText()));
            prescription.setRoiEje(Double.parseDouble(roiEjeLabel.getText()));
            prescription.setRoiAd(Double.parseDouble(roiAdLabel.getText()));

            prescription.setPd1(Double.parseDouble(pd1Label.getText()));
            prescription.setPd2(Double.parseDouble(pd2Label.getText()));
            prescription.setAvod(Double.parseDouble(avodLabel.getText()));
            prescription.setAvoi(Double.parseDouble(avoiLabel.getText()));

            prescription.setRlcodEsf(Double.parseDouble(rlcodEsfLabel.getText()));
            prescription.setRlcodCil(Double.parseDouble(rlcodCilLabel.getText()));
            prescription.setRlcodEje(Double.parseDouble(rlcodEjeLabel.getText()));
            prescription.setRlcodAd(Double.parseDouble(rlcodAdLabel.getText()));

            prescription.setRlcoiEsf(Double.parseDouble(rlcoiEsfLabel.getText()));
            prescription.setRlcoiCil(Double.parseDouble(rlcoiCilLabel.getText()));
            prescription.setRlcoiEje(Double.parseDouble(rlcoiEjeLabel.getText()));
            prescription.setRlcoiAd(Double.parseDouble(rlcoiAdLabel.getText()));

            prescription.setRpodEsf(Double.parseDouble(rpodEsfLabel.getText()));
            prescription.setRpodCil(Double.parseDouble(rpodCilLabel.getText()));
            prescription.setRpodEje(Double.parseDouble(rpodEjeLabel.getText()));
            prescription.setRpodAd(Double.parseDouble(rpodAdLabel.getText()));

            prescription.setRpoiEsf(Double.parseDouble(rpoiEsfLabel.getText()));
            prescription.setRpoiCil(Double.parseDouble(rpoiCilLabel.getText()));
            prescription.setRpoiEje(Double.parseDouble(rpoiEjeLabel.getText()));
            prescription.setRpoiAd(Double.parseDouble(rpoiAdLabel.getText()));

            prescription.setLensType((String)lensTypeComboBox.getValue());
            prescription.setNotas(notasLabel.getText());
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            prescription.setDate(date);

            prescription.addToDatabase(); //Add prescription to database

            return true;
        }
        else{
            //warningLabel.setText("Error");
            //warningLabel.setVisible(true);
            return false;
        }
    }

    private boolean checkValues(){
        if(checkifDouble(rodEsfLabel.getText(), rodEsfLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rodCilLabel.getText(), rodCilLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rodEjeLabel.getText(), rodEjeLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rodAdLabel.getText(), rodAdLabel)){
        }
        else {
            return false;
        }



        if(checkifDouble(roiEsfLabel.getText(), roiEsfLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(roiCilLabel.getText(), roiCilLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(roiEjeLabel.getText(), roiEjeLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(roiAdLabel.getText(), roiAdLabel)){
        }
        else {
            return false;
        }




        if(checkifDouble(pd1Label.getText(), pd1Label)){
        }
        else {
            return false;
        }
        if(checkifDouble(pd2Label.getText(), pd2Label)){
        }
        else {
            return false;
        }
        if(checkifDouble(avodLabel.getText(), avodLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(avoiLabel.getText(), avoiLabel)){
        }
        else {
            return false;
        }



        if(checkifDouble(rlcodEsfLabel.getText(), rlcodEsfLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcodCilLabel.getText(), rlcodCilLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcodEjeLabel.getText(), rlcodEjeLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcodAdLabel.getText(), rlcodAdLabel)){
        }
        else {
            return false;
        }



        if(checkifDouble(rlcoiEsfLabel.getText(), rlcoiEsfLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcoiCilLabel.getText(), rlcoiCilLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcoiEjeLabel.getText(), rlcoiEjeLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcoiAdLabel.getText(), rlcoiAdLabel)){
        }
        else {
            return false;
        }





        if(checkifDouble(rpodEsfLabel.getText(), rpodEsfLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rpodCilLabel.getText(), rpodCilLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rpodEjeLabel.getText(), rpodEjeLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rpodAdLabel.getText(), rpodAdLabel)){
        }
        else {
            return false;
        }



        if(checkifDouble(rpoiEsfLabel.getText(), rpoiEsfLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rpoiCilLabel.getText(), rpoiCilLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rpoiEjeLabel.getText(), rpoiEjeLabel)){
        }
        else {
            return false;
        }
        if(checkifDouble(rpoiAdLabel.getText(), rpoiAdLabel)){
        }
        else {
            return false;
        }




        return true;
    }

    private boolean checkifDouble(String Value, TextField textField) {
        if(Value.equals("")){
            textField.setText("0.0");
            return true;
        }
        else{
            try{
                Double.parseDouble(Value);
            }
            catch (NumberFormatException e){
                warningLabel.setText("Invalido");
                warningLabel.setVisible(true);
                return false;
            }
        }
        return true;
    }

    public void cancelButtonClicked(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage6.fxml"));
            Parent root = (Parent) loader.load();

            ControllerStage6 controllerStage6 = loader.getController();
            controllerStage6.setCustomer(cedulaLabel.getText());



            Stage stage = (Stage) acceptButton.getScene().getWindow();
            stage.setScene(new Scene(root));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
