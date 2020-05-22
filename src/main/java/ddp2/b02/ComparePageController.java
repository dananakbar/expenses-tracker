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

    //Set DB Connectivity
    private Connectivity connectivity = new Connectivity();
    private Connection connection = connectivity.getConnection();
    private Statement statement = connection.createStatement();

    // Date formatter
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyy-MM-dd");

    // From date picker
    @FXML
    private DatePicker fromDatePick;

    // To date picker
    @FXML
    private DatePicker toDatePick;

    // Line chart
    @FXML
    private LineChart lineChart;

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void getScene(Scene dataInputScene) {
        this.dataInputScene = dataInputScene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Query statement
        String todayDate = ((LocalDate) LocalDate.now()).toString(); 
        String queryTodayData;
        queryTodayData = String.format("SELECT * FROM expenses_tracker_db WHERE date=%s", todayDate);

        // Getting the data
        int totalExpenses = 0;
        try {
            ResultSet rs;
            rs = statement.executeQuery(queryTodayData);
            while (rs.next()) { // Iterate through all the data recieved
                totalExpenses += rs.getInt("value");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Add today's expenses to the line chart
        XYChart.Series series = new XYChart.Series();
        series.setName("Today's Expenses");
        series.getData().add(new XYChart.Data(todayDate, totalExpenses));
        lineChart.getData().add(series);
    }

    public void changePage(ActionEvent actionEvent) {
        this.primaryStage.setScene(this.dataInputScene);
    }
}
