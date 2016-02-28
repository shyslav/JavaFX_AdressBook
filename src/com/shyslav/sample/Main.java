package com.shyslav.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    public static Locale DEFAULT_LOCALE ;
    @Override
    public void start(Stage primaryStage) throws Exception {
        if(DEFAULT_LOCALE==null)
        {
            DEFAULT_LOCALE = new Locale("en");
        }
        Locale.setDefault(DEFAULT_LOCALE);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/sample.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("com.shyslav.Locale.locale"));
        //Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));

        Parent root = fxmlLoader.load();
        Controller mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle(fxmlLoader.getResources().getString("adressBook"));
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(300);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void setDEFAULT_LOCALE(Locale DEFAULT_LOCALE) {
        Main.DEFAULT_LOCALE = DEFAULT_LOCALE;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
