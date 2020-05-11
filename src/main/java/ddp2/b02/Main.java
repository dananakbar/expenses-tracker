package ddp2.b02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Label("Hello World"), 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
