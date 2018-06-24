package VIEW;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class View_Graphs extends VBox {
    private VBox root = new VBox();

    public View_Graphs(){
        init();
        getChildren().add(root);

    }

    private void init(){

        //CHOICE BOX....................................................................................................
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList("Sensor 1 ", "Sensor 2 "));
            choiceBox.setMinWidth(100);

        VBox vbox01 = new VBox();
            vbox01.setPadding(new Insets(5,0,0,5));
        vbox01.getChildren().add(choiceBox);

        //CHARTS TEMPERATURE............................................................................................
        final NumberAxis xAxis01 = new NumberAxis();
            //xAxis01.setLabel("Time ");

        final NumberAxis yAxis01 = new NumberAxis();
            //yAxis01.setLabel("℃ ");

        final LineChart<Number, Number> chartTemp = new LineChart<>(xAxis01, yAxis01);
            chartTemp.setPadding(new Insets(10,5,0,5));
            chartTemp.setTitle("Temperature ");

        XYChart.Series seriesTemp = new XYChart.Series();
            seriesTemp.setName("seriesTemp");

        /**TEST ***********************/
        seriesTemp.getData().add(new XYChart.Data(1,22));
        seriesTemp.getData().add(new XYChart.Data(2,18));
        seriesTemp.getData().add(new XYChart.Data(3,25));
        seriesTemp.getData().add(new XYChart.Data(4,16));
        seriesTemp.getData().add(new XYChart.Data(5,17));
        /****************************/
        chartTemp.getData().add(seriesTemp);


        //CHART HUMIDITY................................................................................................
        final NumberAxis xAxis02 = new NumberAxis();
            //xAxis02.setLabel("Time ");

        final NumberAxis yAxis02 = new NumberAxis();
            //yAxis02.setLabel("Rh ");

        final LineChart<Number, Number> chartHum = new LineChart<>(xAxis02, yAxis02);
            chartHum.setPadding(new Insets(10,5,0,5));
            chartHum.setTitle("Humidity ");

        XYChart.Series seriesHum = new XYChart.Series();
            seriesHum.setName("seriesHumidity");

        /**TEST ***********************/
        seriesHum.getData().add(new XYChart.Data(1,62));
        seriesHum.getData().add(new XYChart.Data(3,55));
        seriesHum.getData().add(new XYChart.Data(5,40));
        seriesHum.getData().add(new XYChart.Data(7,70));
        seriesHum.getData().add(new XYChart.Data(9,50));
        /****************************/
        chartHum.getData().add(seriesHum);

        //SQL TABLE VIEW................................................................................................
        TableView tableView = new TableView();
        tableView.setPadding(new Insets(10,5,5,5));

        //..............................................................................................................
        root.getChildren().add(vbox01);
        root.getChildren().add(chartTemp);
        root.getChildren().add(chartHum);
        root.getChildren().add(tableView);



    }
}
