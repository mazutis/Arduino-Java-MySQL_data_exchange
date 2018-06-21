package VIEW;

import SENSORS.VIEW;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/** Goes To SENSORS.VIEW
 * *                                    VBox vbox 🡪     HBox01,.... HBox09, HBox10
 *                                                      🡫           🡫
 *                                                      labels      subVBoxes
 *                                                                  🡫
 *                                                                  labels, pics, buttons
 */

public class View_Controller extends VBox {
    public View_Controller(){

        //LABELS (Static)............................................................
        Label labelSensor1 = new Label("Sensor 1:   ");
        labelSensor1.setFont(new Font("Arial", 12));

        Label labelSensor2 = new Label("Sensor 2:   ");
        labelSensor2.setFont(new Font("Arial", 12));

        Label labelUltrasonic1 = new Label("Ultrasonic 1:   ");
        labelUltrasonic1.setFont(new Font("Arial", 12));

        Label labelUltrasonic2 = new Label("Ultrasonic 2:   ");
        labelUltrasonic2.setFont(new Font("Arial", 12));

        Label labelC1 = new Label(" ℃   ");
        labelC1.setFont(new Font("Arial", 16));
        labelC1.setStyle("-fx-font-weight: bold");

        Label labelC2 = new Label(" ℃   ");
        labelC2.setFont(new Font("Arial", 16));
        labelC2.setStyle("-fx-font-weight: bold");

        Label labelRH1 = new Label(" RH   ");
        labelRH1.setFont(new Font("Arial", 16));
        labelRH1.setStyle("-fx-font-weight: bold");

        Label labelRH2 = new Label(" RH   ");
        labelRH2.setFont(new Font("Arial", 16));
        labelRH2.setStyle("-fx-font-weight: bold");

        Label labelCm1 = new Label(" cm   ");
        labelCm1.setFont(new Font("Arial", 16));
        labelCm1.setStyle("-fx-font-weight: bold");

        Label labelCm2 = new Label(" cm   ");
        labelCm2.setFont(new Font("Arial", 16));
        labelCm2.setStyle("-fx-font-weight: bold");



        Label labelLED01 = new Label("LED01 ");
        labelLED01.setFont(new Font("Arial", 16));
        labelLED01.setStyle("-fx-font-weight: bold");

        Label labelLED02 = new Label("LED02 ");
        labelLED02.setFont(new Font("Arial", 16));
        labelLED02.setStyle("-fx-font-weight: bold");

        Label labelLED03 = new Label("LED03 ");
        labelLED03.setFont(new Font("Arial", 16));
        labelLED03.setStyle("-fx-font-weight: bold");



        Label labelServo180 = new Label(" Motor      ");
        labelServo180.setFont(new Font("Arial", 16));
        labelServo180.setStyle("-fx-font-weight: bold");

        Label labelServo360 = new Label(" Motor      ");
        labelServo360.setFont(new Font("Arial", 16));
        labelServo360.setStyle("-fx-font-weight: bold");

        Label labelPosition1 = new Label(" Position 1,");
        labelPosition1.setFont(new Font("Arial", 8));

        Label labelPosition2 = new Label(" Position 2,");
        labelPosition2.setFont(new Font("Arial", 8));

        Label labelDuration1 = new Label(" Duration 1,");
        labelDuration1.setFont(new Font("Arial", 8));

        Label labelDuration2 = new Label(" Duration 2,");
        labelDuration2.setFont(new Font("Arial", 8));

        Label labelDuration3 = new Label(" Duration 1,");
        labelDuration3.setFont(new Font("Arial", 8));

        Label labelDuration4 = new Label(" Duration 2,");
        labelDuration4.setFont(new Font("Arial", 8));

        Label labelSpeed1 = new Label(" Speed 1,");
        labelSpeed1.setFont(new Font("Arial", 8));

        Label labelSpeed2 = new Label(" Speed 2,");
        labelSpeed2.setFont(new Font("Arial", 8));

        //..............................................................................................................
        //LABELS & TEXTFIELDS (Dynamic).................................................................................//DYNAMIC ELEMENTS
        Label labelTemp1 = new Label("-999");
        labelTemp1.setFont(new Font("Arial", 16));
        labelTemp1.setStyle("-fx-font-weight: bold");

        Label labelTemp2 = new Label("-999");
        labelTemp2.setFont(new Font("Arial", 16));
        labelTemp2.setStyle("-fx-font-weight: bold");

        Label labelHum1 = new Label("   -999");
        labelHum1.setFont(new Font("Arial", 16));
        labelHum1.setStyle("-fx-font-weight: bold");

        Label labelHum2 = new Label("   -999");
        labelHum2.setFont(new Font("Arial", 16));
        labelHum2.setStyle("-fx-font-weight: bold");

        Label labelSonic1 = new Label("-999");
        labelSonic1.setFont(new Font("Arial", 16));
        labelSonic1.setStyle("-fx-font-weight: bold");

        Label labelSonic2 = new Label("-999");
        labelSonic2.setFont(new Font("Arial", 16));
        labelSonic2.setStyle("-fx-font-weight: bold");


        TextField textField01 = new TextField();
        textField01.setMinWidth(42);

        TextField textField02 = new TextField();
        textField02.setMinWidth(42);

        TextField textField03 = new TextField();
        textField03.setMinWidth(42);

        TextField textField04 = new TextField();
        textField04.setMinWidth(42);

        TextField textField05 = new TextField();
        textField05.setMinWidth(42);

        TextField textField06 = new TextField();
        textField06.setMinWidth(42);

        TextField textField07 = new TextField();
        textField07.setMinWidth(42);

        TextField textField08 = new TextField();
        textField08.setMinWidth(42);


        Button btnServo1 = new Button("Set");
        btnServo1.setMinWidth(168);

        Button btnServo2 = new Button("Set");
        btnServo2.setMinWidth(168);


        ToggleSwitch btnLED01 = new ToggleSwitch();

        ToggleSwitch btnLED02 = new ToggleSwitch();

        ToggleSwitch btnLED03 = new ToggleSwitch();

        //..............................................................................................................


        //IMAGES.....................................................................
        Image image01 = new Image(View_Controller.class.getResourceAsStream("/IMAGES/servo180.jpg"));
        ImageView imageView01 = new ImageView(image01);
        imageView01.setFitHeight(75);
        imageView01.setFitWidth(75);
        imageView01.setPreserveRatio(true);

        Image image02 = new Image(View_Controller.class.getResourceAsStream("/IMAGES/servo360.jpg"));
        ImageView imageView02 = new ImageView(image02);
        imageView02.setFitHeight(75);
        imageView02.setFitWidth(75);
        imageView02.setPreserveRatio(true);
//......................................................................................................................


                //subHBOX for servos input and labels
                HBox subHbox1 = new HBox();
                    subHbox1.setPadding(new Insets(5,0,3,0));
                VBox subsub01 = new VBox();
                    subsub01.getChildren().addAll(labelPosition1,textField01);
                VBox subsub02 = new VBox();
                    subsub02.getChildren().addAll(labelDuration1,textField02);
                VBox subsub03 = new VBox();
                    subsub03.getChildren().addAll(labelPosition2,textField03);
                VBox subsub04 = new VBox();
                    subsub04.getChildren().addAll(labelDuration2,textField04);
                subHbox1.getChildren().addAll(subsub01,subsub02,subsub03,subsub04);

                HBox subHbox2 = new HBox();
                    subHbox2.setPadding(new Insets(5,0,3,0));
                VBox subsub05 = new VBox();
                    subsub05.getChildren().addAll(labelSpeed1, textField05);
                VBox subsub06 = new VBox();
                    subsub06.getChildren().addAll(labelDuration3, textField06);
                VBox subsub07 = new VBox();
                    subsub07.getChildren().addAll(labelSpeed2, textField07);
                VBox subsub08 = new VBox();
                    subsub08.getChildren().addAll(labelDuration4,textField08);
                subHbox2.getChildren().addAll(subsub05, subsub06, subsub07, subsub08);


            //subVBOX goes into HBOX09 HBOX10
            VBox subVbox01 = new VBox();
            subVbox01.setPadding(new Insets(0,20,0,20));
            subVbox01.setSpacing(10);
            subVbox01.setAlignment(Pos.CENTER);
            subVbox01.getChildren().add(labelLED01);
            subVbox01.getChildren().add(btnLED01);

            VBox subVbox02 = new VBox();
            subVbox02.setPadding(new Insets(0,20,0,20));
            subVbox02.setSpacing(10);
            subVbox02.setAlignment(Pos.CENTER);
            subVbox02.getChildren().add(labelLED02);
            subVbox02.getChildren().add(btnLED02);

            VBox subVbox03 = new VBox();
            subVbox03.setPadding(new Insets(0,20,0,20));
            subVbox03.setSpacing(10);
            subVbox03.setAlignment(Pos.CENTER);
            subVbox03.getChildren().add(labelLED03);
            subVbox03.getChildren().add(btnLED03);

            VBox subVbox04 = new VBox();
            subVbox04.setPrefWidth(170);
            subVbox04.setAlignment(Pos.CENTER);
            subVbox04.setPadding(new Insets(0,3,0,2));
            subVbox04.getChildren().add(labelServo180);
            subVbox04.getChildren().add(imageView01);
            subVbox04.getChildren().add(subHbox1);
            subVbox04.getChildren().add(btnServo1);

            VBox subVbox05 = new VBox();
            subVbox05.setPrefWidth(170);
            subVbox05.setAlignment(Pos.CENTER);
            subVbox05.setPadding(new Insets(0,2,0,3));
            subVbox05.getChildren().add(labelServo360);
            subVbox05.getChildren().add(imageView02);
            subVbox05.getChildren().add(subHbox2);
            subVbox05.getChildren().add(btnServo2);


        //HBOX goes to vbox.............................................................................................
        HBox hbox01 = new HBox();
        hbox01.setPadding(new Insets(15,0,0,5));
        hbox01.setAlignment(Pos.CENTER);
        hbox01.getChildren().addAll(labelSensor1);

        HBox hbox02 = new HBox();
        hbox02.setPadding(new Insets(0,0,0,5));
        hbox02.setAlignment(Pos.CENTER);
        hbox02.getChildren().addAll(labelTemp1, labelC1, labelHum1, labelRH1);

        HBox hbox03 = new HBox();
        hbox03.setPadding(new Insets(10,0,0,5));
        hbox03.setAlignment(Pos.CENTER);
        hbox03.getChildren().addAll(labelSensor2);

        HBox hbox04 = new HBox();
        hbox04.setPadding(new Insets(0,0,0,5));
        hbox04.setAlignment(Pos.CENTER);
        hbox04.getChildren().addAll(labelTemp2, labelC2, labelHum2, labelRH2);

        HBox hbox05 = new HBox();
        hbox05.setPadding(new Insets(10,0,0,5));
        hbox05.setAlignment(Pos.CENTER);
        hbox05.getChildren().addAll(labelUltrasonic1);

        HBox hbox06 = new HBox();
        hbox06.setPadding(new Insets(0,0,0,5));
        hbox06.setAlignment(Pos.CENTER);
        hbox06.getChildren().addAll(labelSonic1, labelCm1);

        HBox hbox07 =  new HBox();
        hbox07.setPadding(new Insets(10,0,0,5));
        hbox07.setAlignment(Pos.CENTER);
        hbox07.getChildren().addAll(labelUltrasonic2);

        HBox hbox08 = new HBox();
        hbox08.setPadding(new Insets(0,0,0,5));
        hbox08.setAlignment(Pos.CENTER);
        hbox08.getChildren().addAll(labelSonic2, labelCm2);

        HBox hbox09 = new HBox();
        hbox09.setPadding(new Insets(70,0,0,5));
        hbox09.setAlignment(Pos.CENTER);
        hbox09.getChildren().add(subVbox01);
        hbox09.getChildren().add(subVbox02);
        hbox09.getChildren().add(subVbox03);

        HBox hbox10 = new HBox();
        hbox10.setPadding(new Insets(40,0,0,0));
        hbox10.setAlignment(Pos.CENTER);
        hbox10.getChildren().add(subVbox04);
        hbox10.getChildren().add(subVbox05);

        //VBOX goes to VIEW.borderPane..................................................................................
        getChildren().add(hbox01);
        getChildren().add(hbox02);
        getChildren().add(hbox03);
        getChildren().add(hbox04);
        getChildren().add(hbox05);
        getChildren().add(hbox06);
        getChildren().add(hbox07);
        getChildren().add(hbox08);
        getChildren().add(hbox09);
        getChildren().add(hbox10);
    }

}
