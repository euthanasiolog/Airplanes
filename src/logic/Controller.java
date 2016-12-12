package logic;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.Airplanes.Airliner;
import logic.Airplanes.Airplan;
import logic.Airplanes.Bomber;
import logic.Connection.AirplanesConnection;

import java.sql.ResultSet;
import java.sql.*;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by Piatrok on 04.11.16.
 */


public class Controller extends Thread {
    @FXML public TextField weight;
    public TextField weightOnBoard;
    public TextField maxTankVolume;
    public TextField weightA;
    public TextField maxTankVolumeA;
    public TextField weightOnBoardA;
    public Text planes;
    public static GridPane gridPane;
    public Text bombingOk;
    public Text flyOk;
    public TextField numberOfPlaneBombing;
    public TextField numberOfPlaneFly;

    private AirplanesConnection ac;


    /**
     * создаю бомбардировщик
     */
    @FXML protected void createBomber(ActionEvent x)
    {        try {
        Main.airplanList.add(new Bomber(Integer.parseInt(weight.getCharacters().toString()),
                Integer.parseInt(weightOnBoard.getCharacters().toString()),
                Integer.parseInt(maxTankVolume.getCharacters().toString())));
        planes.setText(Main.printPlanes());
        String insertBomber = new String("INSERT INTO airplanes.bombers (weight, weight on board, max tank volume)"
                + "VALUES (" + weight.getCharacters().toString() + "," + weightOnBoard.getCharacters().toString()
                + "," + maxTankVolume.getCharacters().toString()+");");
        AirplanesConnection ac = new AirplanesConnection();
        ac.getStatement();
        ac.insertBomber(insertBomber);
        } catch (Exception e){
        weight.setText("Введите корректное значение");
        }
    }
    /**
     * создаю пассажирский самолет
     */
    @FXML protected void createAirliner(ActionEvent e)
    {
        try {
               Main.airplanList.add(new Airliner(Integer.parseInt(weightA.getCharacters().toString()),
                    Integer.parseInt(weightOnBoardA.getCharacters().toString()),
                    Integer.parseInt(maxTankVolumeA.getCharacters().toString())));
            planes.setText(Main.printPlanes());
            String insertAirliner = new String("INSERT INTO airplanes.airliners (weight, weight on board, max tank volume)"
                    + "VALUES (" + weightA.getCharacters().toString() + "," + weightOnBoardA.getCharacters().toString()
            + "," + maxTankVolumeA.getCharacters().toString()+");");
            AirplanesConnection ac = new AirplanesConnection();
            ac.getStatement();
            ac.insertAirliner(insertAirliner);
        } catch (Exception e1){
            weightA.setText("Введите корректное значение");
        }
    }
    /**
     * сортировка самолетов по дальности полета
     */
    @FXML protected void sortAirplans(ActionEvent n)
    {
        Collections.sort(Main.airplanList, (o1, o2) -> {
            if (o1.getFlyDistance() > o2.getFlyDistance())
            {return 1;}
            else if (o1.getFlyDistance() < o2.getFlyDistance())
            {return -1;}
            else {return 0;}
        });
        planes.setText(Main.printPlanes());
    }
    /**
     * метод для условной бомбардировки
     * только вот поток нормально не работает, только последний текст печатает
     */

    @FXML     protected void bombing(ActionEvent c)
        {
                Platform.runLater(() -> {try
                {
                    int i = Integer.parseInt(numberOfPlaneBombing.getCharacters().toString());
                    Main.airplanList.get(i - 1).refuel(Main.airplanList.get(i - 1).getMaxTankVolume());
                        if (Main.airplanList.get(i - 1) instanceof Bomber) {
                            bombingOk.setText("Ну что, будем бомбить?");
                            System.out.println("Ну что, будем бомбить?");
                            ((Bomber) Main.airplanList.get(i - 1)).loadBombs();
                            try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            bombingOk.setText("Бомбы загружены!");
                            System.out.println("Бомбы загружены!");
                            Main.airplanList.get(i - 1).fly();
                            try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            bombingOk.setText("Полетели!");
                            System.out.println("Полетели!");
                            ((Bomber) Main.airplanList.get(i - 1)).boom();
                            try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            bombingOk.setText("Враги разбомблены!");
                            System.out.println("Враги разбомблены!");
                            Main.airplanList.get(i - 1).notFly();
                            try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            bombingOk.setText("Прилетели!");
                            System.out.println("Прилетели!");
                            Main.airplanList.get(i - 1).drainFuel();
                            try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            bombingOk.setText("Топливо слито!");
                            System.out.println("Топливо слито!");
                            try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            bombingOk.setText("Готов к новым вылетам!");
                        } else bombingOk.setText("Моя не может бомбить");
                } catch (Exception ee){
                bombingOk.setText("Введите корректное значение");
            }
                });
        }
    /**
     * метод для условной перевозки пассажиров
     */
    @FXML protected void flyWithPassengrs(ActionEvent d)
    {
            Platform.runLater(() -> {
                try
                {
                int j = Integer.parseInt(numberOfPlaneFly.getCharacters().toString());
                Main.airplanList.get(j-1).refuel(Main.airplanList.get(j-1).getMaxTankVolume());
                if (Main.airplanList.get(j-1) instanceof Airliner)
                {
                    ((Airliner) Main.airplanList.get(j-1)).loadPassengers();
                    System.out.println("Пассажиры загружены!");
                    try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                    Main.airplanList.get(j-1).fly();
                    System.out.println("Полетели!");
                    try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                    ((Airliner) Main.airplanList.get(j-1)).passengersGetOut();
                    System.out.println("Пассажиры высажены!");
                    try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                    Main.airplanList.get(j-1).notFly();
                    System.out.println("Прилетели!");
                    try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                    Main.airplanList.get(j-1).drainFuel();
                    System.out.println("Топливо слито!");
                    try {sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                    flyOk.setText("пассажиры перевезены!");
                } else flyOk.setText("Моя не может возить человеков");
                }catch (Exception e) {flyOk.setText("Введите корректное значение");}
            });
    }
}
