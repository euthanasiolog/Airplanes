package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Button buttonBomber = new Button();
        buttonBomber.setPrefWidth(100);
        buttonBomber.setPrefHeight(50);
        buttonBomber.setText("Создать бомбардировщик!");
        buttonBomber.setOnAction(event ->{
                    Luftvaffe.makeBomber();
                }
        );
    stage.setTitle("Hi!");
    stage.setScene(new Scene(root, 300, 400));
    stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
