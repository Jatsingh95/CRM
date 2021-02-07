import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerStage9 implements Initializable {

    public Button editButton;
    public Button cancelButton;
    public Button saveButton;
    public Label prescriptionIDLabel;

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
    public Label lensTypeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LensType lensType = new LensType();
        ArrayList<String> lenstypes = lensType.getAllLensTypes();
        for(String x : lenstypes){
            lensTypeComboBox.getItems().add(x);
        }
        lensTypeComboBox.setVisible(false);
    }

    public void setPrescription(Integer id){
        prescriptionIDLabel.setText(id.toString());
        Prescription prescription = new Prescription(id);

        rodEsfLabel.setText(String.valueOf(prescription.getRodEsf()));
        rodCilLabel.setText(String.valueOf(prescription.getRodCil()));
        rodEjeLabel.setText(String.valueOf(prescription.getRodEje()));
        rodAdLabel.setText(String.valueOf(prescription.getRodAd()));

        roiEsfLabel.setText(String.valueOf(prescription.getRoiEsf()));
        roiCilLabel.setText(String.valueOf(prescription.getRoiCil()));
        roiEjeLabel.setText(String.valueOf(prescription.getRoiEje()));
        roiAdLabel.setText(String.valueOf(prescription.getRoiAd()));

        pd1Label.setText(String.valueOf(prescription.getPd1()));
        pd2Label.setText(String.valueOf(prescription.getPd2()));
        avodLabel.setText(String.valueOf(prescription.getAvod()));
        avoiLabel.setText(String.valueOf(prescription.getAvoi()));

        rlcodEsfLabel.setText(String.valueOf(prescription.getRlcodEsf()));
        rlcodCilLabel.setText(String.valueOf(prescription.getRlcodCil()));
        rlcodEjeLabel.setText(String.valueOf(prescription.getRlcodEje()));
        rlcodAdLabel.setText(String.valueOf(prescription.getRlcodAd()));

        rlcoiEsfLabel.setText(String.valueOf(prescription.getRlcoiEsf()));
        rlcoiCilLabel.setText(String.valueOf(prescription.getRlcoiCil()));
        rlcoiEjeLabel.setText(String.valueOf(prescription.getRlcoiEje()));
        rlcoiAdLabel.setText(String.valueOf(prescription.getRlcoiAd()));

        rpodEsfLabel.setText(String.valueOf(prescription.getRpodEsf()));
        rpodCilLabel.setText(String.valueOf(prescription.getRpodCil()));
        rpodEjeLabel.setText(String.valueOf(prescription.getRpodEje()));
        rpodAdLabel.setText(String.valueOf(prescription.getRpodAd()));

        rpoiEsfLabel.setText(String.valueOf(prescription.getRpoiEsf()));
        rpoiCilLabel.setText(String.valueOf(prescription.getRpoiCil()));
        rpoiEjeLabel.setText(String.valueOf(prescription.getRpoiEje()));
        rpoiAdLabel.setText(String.valueOf(prescription.getRpoiAd()));

        notasLabel.setText(String.valueOf(prescription.getNotas()));
        lensTypeComboBox.setValue(prescription.getLensType());
        lensTypeLabel.setText(prescription.getLensType());

    }

    public void editButtonClicked(){
        rodEsfLabel.setEditable(true);
        rodCilLabel.setEditable(true);
        rodEjeLabel.setEditable(true);
        rodAdLabel.setEditable(true);

        roiEsfLabel.setEditable(true);
        roiCilLabel.setEditable(true);
        roiEjeLabel.setEditable(true);
        roiAdLabel.setEditable(true);

        pd1Label.setEditable(true);
        pd2Label.setEditable(true);
        avodLabel.setEditable(true);
        avoiLabel.setEditable(true);

        rlcodEsfLabel.setEditable(true);
        rlcodCilLabel.setEditable(true);
        rlcodEjeLabel.setEditable(true);
        rlcodAdLabel.setEditable(true);

        rlcoiEsfLabel.setEditable(true);
        rlcoiCilLabel.setEditable(true);
        rlcoiEjeLabel.setEditable(true);
        rlcoiAdLabel.setEditable(true);

        rpodEsfLabel.setEditable(true);
        rpodCilLabel.setEditable(true);
        rpodEjeLabel.setEditable(true);
        rpodAdLabel.setEditable(true);

        rpoiEsfLabel.setEditable(true);
        rpoiCilLabel.setEditable(true);
        rpoiEjeLabel.setEditable(true);
        rpoiAdLabel.setEditable(true);

        notasLabel.setEditable(true);
        lensTypeComboBox.setDisable(false);

        editButton.setVisible(false);
        saveButton.setVisible(true);

        lensTypeLabel.setText("");
        lensTypeLabel.setVisible(false);
        lensTypeComboBox.setVisible(true);

    }

    public void cancelButtonClicked(){
        Prescription prescription = new Prescription(Integer.parseInt(prescriptionIDLabel.getText()));
        Customer customer = new Customer(prescription.getCustomerID());

        try {
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

    public void saveButtonClicked(){
        if(createPrescription()){
            Prescription prescription = new Prescription(Integer.parseInt(prescriptionIDLabel.getText()));
            Customer customer = new Customer(prescription.getCustomerID());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage6.fxml"));
                Parent root = (Parent) loader.load();

                ControllerStage6 controllerStage6 = loader.getController();
                controllerStage6.setCustomer(customer.getCedula());

                Stage stage = (Stage) saveButton.getScene().getWindow();
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
            prescription.setPrescriptionID(Integer.parseInt(prescriptionIDLabel.getText()));

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

            java.util.Date date = new java.util.Date();
            Object param = new java.sql.Timestamp(date.getTime());
            //long millis=System.currentTimeMillis();
            //java.sql.Timestamp date=new java.sql.Date(millis);
            prescription.setDate((Timestamp) param);

            prescription.updateDatabase(); //Add prescription to database

            return true;
        }
        else{
            warningLabel.setText("Error");
            warningLabel.setVisible(true);
            return false;
        }
    }

    private boolean checkValues(){
        if(checkifDouble(rodEsfLabel.getText(), "Receta Nueva - OD-Esf")){
        }
        else {
            return false;
        }
        if(checkifDouble(rodCilLabel.getText(), "Receta Nueva - OD-Cil")){
        }
        else {
            return false;
        }
        if(checkifDouble(rodEjeLabel.getText(), "Receta Nueva - OD-Eje")){
        }
        else {
            return false;
        }
        if(checkifDouble(rodAdLabel.getText(), "Receta Nueva - OD-Ad")){
        }
        else {
            return false;
        }



        if(checkifDouble(roiEsfLabel.getText(), "Receta Nueva - OI-Esf")){
        }
        else {
            return false;
        }
        if(checkifDouble(roiCilLabel.getText(), "Receta Nueva - OI-Cil")){
        }
        else {
            return false;
        }
        if(checkifDouble(roiEjeLabel.getText(), "Receta Nueva - OI-Eje")){
        }
        else {
            return false;
        }
        if(checkifDouble(roiAdLabel.getText(), "Receta Nueva - OI-Ad")){
        }
        else {
            return false;
        }




        if(checkifDouble(pd1Label.getText(), "Receta Nueva - Pd1")){
        }
        else {
            return false;
        }
        if(checkifDouble(pd2Label.getText(), "Receta Nueva - Pd2")){
        }
        else {
            return false;
        }
        if(checkifDouble(avodLabel.getText(), "Receta Nueva - OD-AV")){
        }
        else {
            return false;
        }
        if(checkifDouble(avoiLabel.getText(), "Receta Nueva - OI-AV")){
        }
        else {
            return false;
        }



        if(checkifDouble(rlcodEsfLabel.getText(), "Receta LC - OD-Esf")){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcodCilLabel.getText(), "Receta LC - OD-Cil")){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcodEjeLabel.getText(), "Receta LC - OD-Eje")){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcodAdLabel.getText(), "Receta LC - OD-Ad")){
        }
        else {
            return false;
        }



        if(checkifDouble(rlcoiEsfLabel.getText(), "Receta LC - OI-Esf")){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcoiCilLabel.getText(), "Receta LC - OI-Cil")){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcoiEjeLabel.getText(), "Receta LC - OI-Eje")){
        }
        else {
            return false;
        }
        if(checkifDouble(rlcoiAdLabel.getText(), "Receta LC - OI-Ad")){
        }
        else {
            return false;
        }





        if(checkifDouble(rpodEsfLabel.getText(), "Receta Previa - OD-Esf")){
        }
        else {
            return false;
        }
        if(checkifDouble(rpodCilLabel.getText(), "Receta Previa - OD-Cil")){
        }
        else {
            return false;
        }
        if(checkifDouble(rpodEjeLabel.getText(), "Receta Previa - OD-Eje")){
        }
        else {
            return false;
        }
        if(checkifDouble(rpodAdLabel.getText(), "Receta Previa - OD-Ad")){
        }
        else {
            return false;
        }



        if(checkifDouble(rpoiEsfLabel.getText(), "Receta Previa - OI-Esf")){
        }
        else {
            return false;
        }
        if(checkifDouble(rpoiCilLabel.getText(), "Receta Previa - OI-Cil")){
        }
        else {
            return false;
        }
        if(checkifDouble(rpoiEjeLabel.getText(), "Receta Previa - OI-Eje")){
        }
        else {
            return false;
        }
        if(checkifDouble(rpoiAdLabel.getText(), "Receta Previa - OI-Ad")){
        }
        else {
            return false;
        }




        return true;
    }

    private boolean checkifDouble(String Value, String Name) {
        try{
            Double.parseDouble(Value);
        }
        catch (NumberFormatException e){
            warningLabel.setText(Name + " invalido");
            warningLabel.setVisible(true);
            return false;
        }
        return true;
    }


}
