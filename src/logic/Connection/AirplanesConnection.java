package logic.Connection;
import logic.Airplanes.Airliner;
import logic.Airplanes.Bomber;
import logic.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Created by Piatrok on 05.12.2016.
 */
public class AirplanesConnection {

    private static final String url = "jdbc:mysql://localhost:3306/airplanes";
    private static final String user = "root";
    private static final String password = "root";
    String queryAirliners = "SELECT FROM airplanes.airliners weight, weight on board, max tank volume";
    String queryBomber = "SELECT FROM airplanes.bombers weight, weight on board, max tank volume";

    private ResultSet resultSetA;
    private ResultSet resultSetB;
    private Connection connection;
    private Statement statement;
    public AirplanesConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public Statement getStatement () {
        statement = null;
        try {
            statement = connection.createStatement();
            }  catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
    public void airlinersFromDB () throws SQLException {
        resultSetA = statement.executeQuery(queryAirliners);
        while (resultSetA.next()){
            int weight = resultSetA.getInt(2);
            int weightOnBoard = resultSetA.getInt(3);
            int maxTankVolume = resultSetA.getInt(4);
            Main.airplanList.add(new Airliner(weight, weightOnBoard, maxTankVolume));
        }
    }

    public void bomberFromDB () throws SQLException {
        resultSetB = statement.executeQuery(queryBomber);
        while (resultSetB.next()){
            int weight = resultSetB.getInt(2);
            int weightOnBoard = resultSetB.getInt(3);
            int maxTankVolume = resultSetB.getInt(4);
            Main.airplanList.add(new Bomber(weight, weightOnBoard, maxTankVolume));
        }
    }
    public void insertAirliner(String insertAirliner) throws SQLException {
        statement.executeUpdate(insertAirliner);
    }
    public void insertBomber (String insertBomber) throws SQLException {
        statement.executeUpdate(insertBomber);
    }
}
