package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static List<Airplan> airplanList;

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));

        Scene scene = new Scene(root, 300, 550);
        stage.setTitle("Hi!");
        stage.setScene(scene);
        stage.show();

        airplanList = new ArrayList<>();

    }
    public static String printPlanes (){
        String s = "";
        for (Airplan airplan : airplanList){
            s += airplan.toString()+'\n';
        }
        return s;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
