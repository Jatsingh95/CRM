import java.awt.*;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.scene.control.Button;
import javafx.stage.Stage;

public class StageSwitcher {

    Stage stage;
    Parent root;

    public StageSwitcher(Button clicked, String stagename){

        //Get oldStage from button argument passed
        stage = (Stage) clicked.getScene().getWindow();

        try {
            root = FXMLLoader.load(getClass().getResource(stagename));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void switchStages(){
            stage.setScene(new Scene(root));
    }
}
