package ddp2.b02;

import connectivity.Connectivity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ComparePageController implements Initializable {
    private Stage primaryStage;
    private Scene dataInputScene;
    

    //Set DB Connectivity
    private Connectivity connectivity = new Connectivity();
    private Connection connection = connectivity.getConnection();
    private Statement statement = connection.createStatement();

    // From date picker
    @FXML
    private DatePicker fromDatePick;

    // To date picker
    @FXML
    private DatePicker toDatePick;

    // Line chart
    @FXML
    private LineChart lineChart;

    public ComparePageController() throws SQLException {
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void getScene(Scene dataInputScene) {
        this.dataInputScene = dataInputScene;
    }

    public void changePage(ActionEvent actionEvent) {
        this.primaryStage.setScene(this.dataInputScene);
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

    

    // Event handler when a from date is selected
    fromDatePick.setOnAction(event -> {
        if (toDatePick.getValue() == null) return;
        if (toDatePick.isBefore(fromDatePick)) return;
        
        // Clear line chart
        lineChart.getData().remove(0);

        // Get from and to dates
        LocalDate fromLocalDate = fromDatePick.getValue();
        LocalDate toLocalDate = toDatePick.getValue();

        // Setup the series
        XYChart.Series series = new XYChart.Series();
        series.setName("Expenses from %s until %s", fromLocalDate.toString(), toLocalDate.toString());

        // Get total expenses per day, from start date to end date
        for (LocalDate date = fromLocalDate; date.isBefore(toLocalDate.plusDays(1)); date = date.plusDays(1)) {
            // Query statement
            String queryStatement;
            queryStatement = String.format("SELECT * FROM expenses_tracker_db WHERE date=%s", date.toString());
            
            // Getting the data
            totalExpenses = 0;
            try {
                ResultSet rs;
                rs = statement.executeQuery(queryStatement);
                while (rs.next()) { // Iterate through all the data recieved
                    totalExpenses += rs.getInt("value");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Add this date total expenses to the series
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); // Date formatter
            series.getData().add(new XYChart.Data(formatter.format(date), totalExpenses));
        }

        // Add the new series to the line chart
        lineChart.getData().add(series);
    });


    
    // Event handler when a to date is selected
    toDatePick.setOnAction(event -> {
        if (fromDatePick.getValue() == null) return;
        if (toDatePick.isBefore(fromDatePick)) return;
        
        // Clear line chart
        lineChart.getData().remove(0);

        // Get from and to dates
        LocalDate fromLocalDate = fromDatePick.getValue();
        LocalDate toLocalDate = toDatePick.getValue();

        // Setup the series
        XYChart.Series series = new XYChart.Series();
        series.setName("Expenses from %s until %s", fromLocalDate.toString(), toLocalDate.toString());

        // Get total expenses per day, from start date to end date
        for (LocalDate date = fromLocalDate; date.isBefore(toLocalDate.plusDays(1)); date = date.plusDays(1)) {
            // Query statement
            String queryStatement;
            queryStatement = String.format("SELECT * FROM expenses_tracker_db WHERE date=%s", date.toString());
            
            // Getting the data
            totalExpenses = 0;
            try {
                ResultSet rs;
                rs = statement.executeQuery(queryStatement);
                while (rs.next()) { // Iterate through all the data recieved
                    totalExpenses += rs.getInt("value");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Add this date total expenses to the series
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); // Date formatter
            series.getData().add(new XYChart.Data(formatter.format(date), totalExpenses));
        }

        // Add the new series to the line chart
        lineChart.getData().add(series);
    });
}