package logic.Airplanes;

import logic.Interfaces.DrainFuel;
import logic.Interfaces.RefuelAirplan;

/**
 * Created by Piatrok on 03.11.16.
 */
public class Airplan implements RefuelAirplan, DrainFuel {

    /**
     * вес пустого самолета (вес топлива не учитывается, у нас проработана физика четко)
     * а ещё мы забили на разницу между весом и массой, прости, Ньютон!
     */
    int weight;

    /**
     * вес на борту
     */
    int weightOnBoard;

    /**
     * максимальный объем бака
     */
    int maxTankVolume;

    /**
     * гетеры и сетеры для всего
     */
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxTankVolume() {
        return maxTankVolume;
    }

    public void setMaxTankVolume(int maxTankVolume) {
        this.maxTankVolume = maxTankVolume;
    }

    public int getWeightOnBoard() {
        return weightOnBoard;
    }

    public void setWeightOnBoard(int weightOnBoard) {
        this.weightOnBoard = weightOnBoard;
    }

    public int getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(int fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public String getIsFly() {
        return isFly;
    }

    public void setIsFly(String isFly) {
        this.isFly = isFly;
    }

    /**
     * дистанция полета
     */
    protected int flyDistance;

    public int getFlyDistance() {
        return flyDistance;
    }

    /**
     * Текущее количество топлива
     */
    private int fuelVolume;

    /**
     * Состояния самолета "Fly" либо "NotFly"
     */
    private String isFly="noT fly";

    /**
     * Заставляет самолет полететь, не знаю как это будет работать и зачем это надо(
     * @return состояние самолета
     */
    StringBuilder str = new StringBuilder();
    public String fly (){
        str.append("Fly");
        return str.toString();
    }

    public String notFly (){
        str.append("NotFly");
        return str.toString();
    }
    /**
     * Устанавливаем количество топлива равным нулю
     */
    @Override
    public void drainFuel() {
        this.fuelVolume = 0;
    }
    /**
     * добавляем в бак топливо
     * @param n - количество добавляемого топлива
     */
    @Override
    public void refuel(int n) {
        this.fuelVolume += n;
        if (fuelVolume>maxTankVolume){
            fuelVolume=maxTankVolume;
        }
    }
}
