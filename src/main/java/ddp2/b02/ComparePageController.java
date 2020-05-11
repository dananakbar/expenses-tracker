package ddp2.b02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ComparePageController implements Initializable {
    private Stage primaryStage;
    private Scene dataInputScene;

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void getScene(Scene dataInputScene) {
        this.dataInputScene = dataInputScene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changePage(ActionEvent actionEvent) {
        this.primaryStage.setScene(this.dataInputScene);
    }
}
