package logic.Airplanes;

import logic.Interfaces.LoadPassengers;
import logic.Interfaces.PassengersGetOut;
import logic.Main;

/**
 * Created by Piatrok on 03.11.16.
 */
public class Airliner extends Airplan implements PassengersGetOut, LoadPassengers
{
    private int maxNumberOfPassengers;
    private int numberOfPassengers;

    //getters and setters
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }


    @Override
    public String toString() {
        return "Пссажирский №"+ (Main.airplanList.indexOf(this)+1) + "{" +
                " максимально пассажиров: " + maxNumberOfPassengers +
                ", пассажиров на борту: " + numberOfPassengers +
                " , вес: " + weight +
                " , дальность полёта: " + flyDistance+
                '}';
    }

    /**public void destroyTwinTowers (LocalDate date)
     {
     if()
     }
     */

    public Airliner(int weight, int weightOnBoard, int maxTankVolume) {
        this.weight = weight;
        this.weightOnBoard = weightOnBoard;
        this.maxTankVolume = maxTankVolume;
        /**
         * максимальное число высчитываем из средней массы пассажира(с грузом)
         */
        maxNumberOfPassengers = weightOnBoard/100;
        /**
         * а вот дальность полета рассчитывается по секретной авиационной формуле
         */
        flyDistance = (maxTankVolume*1000)/(weight+weightOnBoard+1);
    }

    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    @Override
    public void loadPassengers (int n) {
        numberOfPassengers += n;
        if (numberOfPassengers > maxNumberOfPassengers) {
            numberOfPassengers = maxNumberOfPassengers;
        }
    }

    @Override
    public void loadPassengers (){
        int j = getNumberOfPassengers();
        do {
            numberOfPassengers = j + 1;
        }while (numberOfPassengers == maxNumberOfPassengers);
    }
    @Override
    /**
     * разгружаю пассажиров по одному с самолета
     */

    public void passengersGetOut (){
        int i = getNumberOfPassengers();
        do {
            numberOfPassengers = i - 1;
        }while (i == 0);
    }
}