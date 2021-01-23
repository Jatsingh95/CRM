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



public class ControllerStage2 implements Initializable {

    //Create variables for buttons
    public Button createButton;
    public Button cancelButton;
    public Label cedulaLabel;

    public void createButtonClicked(){

        Customer currCustomer = new Customer(){};
        String cedula = cedulaLabel.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stage3.fxml"));
            Parent root = (Parent) loader.load();

            ControllerStage3 controllerStage3 = loader.getController();
            controllerStage3.setCustomer(cedula);

            Stage stage = (Stage) createButton.getScene().getWindow();
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
        cedulaLabel.setText(cedula);
    }
}
