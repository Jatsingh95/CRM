import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerStage5 implements Initializable {
    public Button acceptButton;
    public Button cancelButton;
    public Label cedulaLabel;
    public Label nameLabel;

    public void acceptButtonClicked(){
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

    public void cancelButtonClicked(){
        StageSwitcher stageSwitcher = new StageSwitcher(cancelButton,"Stage1.fxml");
        stageSwitcher.switchStages();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setCustomer(String cedula){
        Customer currCustomer = new Customer(cedula);
        nameLabel.setText(currCustomer.getFirstName() + " " + currCustomer.getLastName());
        cedulaLabel.setText(currCustomer.getCedula());
    }
}