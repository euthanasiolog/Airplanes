package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Airplanes.Airplan;
import logic.Connection.AirplanesConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static List<Airplan> airplanList;



    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Hi!");
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add((getClass().getResource("../style/style.css")).toExternalForm());
        airplanList = new ArrayList<>();
        AirplanesConnection ac = new AirplanesConnection();
        ac.getStatement();
       ac.bomberFromDB();
       ac.airlinersFromDB();

    }

    public static String printPlanes() {
        String s = "";
        for (Airplan airplan : airplanList) {
            s += airplan.toString() + '\n';
        }
        return s;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
