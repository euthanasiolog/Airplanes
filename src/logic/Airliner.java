package logic;

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

    /**
     * разгружаю пассажиров по одному с самолета
     */
    @Override
    public void passengersGetOut (){
        int i = getNumberOfPassengers();
        do {
            numberOfPassengers = i - 1;
        }while (i > 0);
    }


    @Override
    public String toString() {
        return "Airliner{" +
                "maxNumberOfPassengers=" + maxNumberOfPassengers +
                ", numberOfPassengers=" + numberOfPassengers +
                " , weight= " + weight +
                " , weight on board=" + weightOnBoard +
                " , max tank volume=" + maxTankVolume +
                " , fly distance=" + flyDistance+
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
        maxNumberOfPassengers = weightOnBoard/100;
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
        int j = getMaxNumberOfPassengers();
        do {
            numberOfPassengers = j+1;
        }while (numberOfPassengers < maxNumberOfPassengers);

    }

}
