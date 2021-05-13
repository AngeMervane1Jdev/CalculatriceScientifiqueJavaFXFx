package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/vues/principalView.fxml"));
        primaryStage.setTitle("Calculatrice");
        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();


    }

    public static Stage getStage(){
        return stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
