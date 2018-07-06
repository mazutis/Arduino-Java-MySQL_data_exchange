package VIEW;

import SENSORS.Xml;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View_Settings extends VBox{
    Xml xml = new Xml();
    private VBox root = new VBox();

    public View_Settings(){
        init();
        getChildren().add(root);
    }

    private void init(){
        Label label01 = new Label("MySQL SETTINGS: ");
            label01.setStyle("-fx-font-weight: bold");

        Label label02 = new Label("URL: ");

        Label label03 = new Label("USER NAME: ");

        Label label04 = new Label("PASSWORD: ");

        Label label05 = new Label("SSL: ");

        //..............................................................................................................DYNAMIC ELEMENTS
        TextField textField01 = new TextField(Xml.getURL());
            textField01.setMinWidth(220);

        TextField textField02 = new TextField(Xml.getUser());
            textField02.setMinWidth(220);

        TextField textField03 = new TextField(Xml.getPassword());
            textField03.setMinWidth(220);

        TextField textField04 = new TextField(Xml.getSSL());
            textField04.setMinWidth(220);

        Button btn01 = new Button("Test connection..");
        Button btn02 = new Button("Apply");
        //..............................................................................................................


            VBox vbox01 = new VBox();
                vbox01.setPadding(new Insets(0,0,0,5));
                vbox01.getChildren().add(label01);

            VBox vbox02 = new VBox();
                vbox02.setAlignment(Pos.CENTER_RIGHT);
                vbox02.setPadding(new Insets(0,5,0,5));
                vbox02.setSpacing(12);
                vbox02.getChildren().add(label02);
                vbox02.getChildren().add(label03);
                vbox02.getChildren().add(label04);
                vbox02.getChildren().add(label05);

            VBox vbox03 = new VBox();
                vbox03.setPadding(new Insets(0,5,0,5));
                vbox03.setSpacing(5);
                vbox03.getChildren().add(textField01);
                vbox03.getChildren().add(textField02);
                vbox03.getChildren().add(textField03);
                vbox03.getChildren().add(textField04);

        HBox hbox01 = new HBox();
            hbox01.setPadding(new Insets(20,5,0,0));
            hbox01.getChildren().add(vbox01);

        HBox hbox02 = new HBox();
            hbox02.setPadding(new Insets(20,5,0,0));
            hbox02.setAlignment(Pos.CENTER_LEFT);
            hbox02.getChildren().addAll(vbox02, vbox03);

        HBox hbox03 = new HBox();
            hbox03.setPadding(new Insets(20,0,20,0));
            hbox03.setSpacing(10);
            hbox03.setAlignment(Pos.CENTER);
            hbox03.getChildren().add(btn01);
            hbox03.getChildren().add(btn02);

        //..............................................................................................................
        root.getChildren().add(hbox01);
        root.getChildren().add(hbox02);
        root.getChildren().add(hbox03);
    }
}
