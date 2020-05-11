package ddp2.b02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class DataInputPageController implements Initializable {
    private Stage primaryStage;
    private Scene compareScene;

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void getScene(Scene compareScene) {
        this.compareScene = compareScene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changePage(ActionEvent actionEvent) {
        this.primaryStage.setScene(this.compareScene);
    }
}
