package ddp2.b02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    Scene dataInputScene;
    Scene compareScene;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Data Input Page
        URL dataInputPageUrl = new File("src/main/java/ddp2/b02/DataInputPage.fxml").toURI().toURL();
        FXMLLoader dataInputFxmlLoader = new FXMLLoader(dataInputPageUrl);
        Parent dataInputRoot = dataInputFxmlLoader.load();
        dataInputScene = new Scene(dataInputRoot,800,600);

        //Compare Page
        URL comparePageUrl = new File("src/main/java/ddp2/b02/ComparePage.fxml").toURI().toURL();
        FXMLLoader comparePageFxmlLoader = new FXMLLoader(comparePageUrl);
        Parent comparePageRoot = comparePageFxmlLoader.load();
        compareScene = new Scene(comparePageRoot,800,600);

        //Passing data to DataInputController
        ((DataInputPageController) dataInputFxmlLoader.getController()).setStage(primaryStage);
        ((DataInputPageController) dataInputFxmlLoader.getController()).getScene(compareScene);

        //Passing data to ComparePageController
        ((ComparePageController) comparePageFxmlLoader.getController()).setStage(primaryStage);
        ((ComparePageController) comparePageFxmlLoader.getController()).getScene(dataInputScene);

        //Set Scene
        primaryStage.setScene(dataInputScene);
        primaryStage.show();
    }
}
