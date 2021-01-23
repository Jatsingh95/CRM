import javafx.scene.control.Button;

public class ControllerStage10 {

    public Button LogInButton;

    public void loginButtonClicked(){
        System.out.println("Success!");
        LogInButton.setText("Stop touching me");
    }
}
