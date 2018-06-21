package SENSORS;

import VIEW.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/** Main FX VIEW object.
 *  Stage stage 🡪 Scene scene 🡪 BorderPane root
 *                                  🡫
 *                              TOP:    MenuBar menuBar
 *                                      FILE        |   VIEW    |   HELP
 *                                      Settings..      Graphs      About
 *                                      Exit
 *
 *                              CENTER: Dynamic content from VIEW package 🡪
 *                              BOTTOM: x
 *                              LEFT:   x
 *                              RIGHT:  x
 */

public class VIEW extends Application {
    private BorderPane root = new BorderPane();//Main window

    private View_Controller view_controller = new View_Controller();
    private View_About view_about = new View_About();
    private View_Settings  view_settings = new View_Settings();

//......................................................................................................................
    public static void main(String[] args) {
        launch(args);
    }
//......................................................................................................................
    @Override
    public void start(Stage stage) throws Exception {

            //Menu Bar 🡪 File
            Menu fileMenu = new Menu("File");

                MenuItem menuItemController = new MenuItem("Controller");
                    menuItemController.setOnAction ( e-> root.setCenter(view_controller));

                MenuItem menuItemSettings = new MenuItem("Settings...");
                    menuItemSettings.setOnAction ( e-> root.setCenter(view_settings));

                MenuItem menuItemExit = new MenuItem("Exit");
                    menuItemExit.setOnAction ( e-> stage.close());

            fileMenu.getItems().add(menuItemController);
            fileMenu.getItems().add(menuItemSettings);
            fileMenu.getItems().add(new SeparatorMenuItem());
            fileMenu.getItems().add(menuItemExit);

            //Menu Bar 🡪 Help.
            Menu helpMenu = new Menu("Help");

                MenuItem menuItemAbout = new MenuItem("About");
                    menuItemAbout.setOnAction ( e-> root.setCenter(view_about));

            helpMenu.getItems().add(menuItemAbout);

        //MENU BAR......................................................................................................
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        menuBar.setPadding (new Insets(0,0,0,0));



        //BORDER PANE...................................................................................................
        root.setTop(menuBar);
        //root.setLeft();
        //root.setRight();
        //root.setBottom();
        root.setCenter(view_controller);



        //SCENE.........................................................................................................
        Scene scene = new Scene(root, 350, 600);


        //STAGE.........................................................................................................
        stage.setTitle("Sensors (Controller)");
        stage.getIcons().add(new Image("IMAGES/icon.png")); // src/IMAGES/icon.png
        stage.setMinWidth(350);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

}