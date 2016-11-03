package logic;

/**
 * Created by Piatrok on 03.11.16.
 */
public class Bomber extends Airplan implements boom, LoadBombs
{
    private int numberOfBombs;



    private int maxNumberOfBombs;

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    /**
     * скидываю по одной бомбы
     */
    @Override
    public void boom(){
        int i = getNumberOfBombs();
        do{
            numberOfBombs = i - 1;
        }while (i>0);
    }

    Bomber(int weight, int weightOnBoard, int maxTankVolume) {
        this.weight = weight;
        this.weightOnBoard = weightOnBoard;
        this.maxTankVolume = maxTankVolume;
        this.maxNumberOfBombs = weightOnBoard/50;
        this.numberOfBombs = 0;
    }
    int getMaxNumberOfBombs() {
        return maxNumberOfBombs;
    }
    @Override
    public void loadBombs (int n){
        numberOfBombs +=n;
        if(numberOfBombs>maxNumberOfBombs){
            numberOfBombs=maxNumberOfBombs;
        }
    }

    @Override
    public void loadBombs() {
        int j = getNumberOfBombs();
        do {
            numberOfBombs = j+1;
        }while (numberOfBombs < maxNumberOfBombs);
    }
}
