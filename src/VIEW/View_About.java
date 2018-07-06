package VIEW;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class View_About extends VBox{
    private VBox root = new VBox();

    public View_About() {
        init();
        getChildren().add(root);
    }

    private void init(){
        //LABEL01
        Label labelAbout01 = new Label("\n\nABOUT\n");
        labelAbout01.setFont(new Font("Arial", 12));
        labelAbout01.setStyle("-fx-font-weight: bold");
        labelAbout01.setAlignment(Pos.CENTER);
        labelAbout01.setTextAlignment(TextAlignment.CENTER);

        //LABEL02
        Label labelAbout02 = new Label(
                    "\nArduino-Java-MySQL data exchange" +
                            "\n"+
                        "\nUsing jSerialComm(RXTX) and MySQL Connector/J libraries. " +
                            "\n"+
                        "\nReading data from temp/humidity(DHT22), Ultrasonic sensors, " +
                        "\nand sending readings to MySQL database." +
                            "\n"+
                        "\nReading parameters from MySQL and sending to arduino: " +
                        "\nactivating/deactivating motor180, motor360 and LEDs. "
                );
        labelAbout02.setTextAlignment(TextAlignment.CENTER);
        labelAbout02.setFont(new Font("Arial", 12));

        //LABEL03
        Label labelAbout03 = new Label("\n\n2018\n");
        labelAbout03.setFont(new Font("Arial", 12));
        labelAbout03.setAlignment(Pos.CENTER);
        labelAbout03.setTextAlignment(TextAlignment.CENTER);


        //..................................................................

        root.setMinWidth(350);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(labelAbout01, labelAbout02, labelAbout03);

        //..................................................................
    }

}
