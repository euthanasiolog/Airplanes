package logic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Piatrok on 03.11.16.
 */
public class Luftvaffe extends Corporation
{
    private static ArrayList<Airplan> geschwader;
    private static Scanner sc;
    private static int a, b, c;


    public static void makeBomber()
    {
        /**
         * создаем метод для создания бомбардировщика, параметры вводим вручную
         */
        sc = new Scanner(System.in);
        geschwader = new ArrayList<>();
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        Airplan bomber = new Bomber(a, b, c);
        geschwader.add(bomber);
    }
    /**
     * создаем метод для создания пассажирского самолета, параметры вводим вручную
     */
    public static void makeAirliner() {
        Airplan airliner = new Airliner(a, b, c);
        geschwader.add(airliner);
    }

    /**
     * тут написал сортировку по дальности полета.
     */
    int l = geschwader.size();

    public void sortArrayOfPlanes()
    {
        for (int i = 0; i < l - 1; i++)
        {
            int min = i;
            for (int j = i + 1; j < l; j++)
            {
                if (geschwader.get(j).getFlyDistance() < geschwader.get(i).getFlyDistance())
                {
                    min = j;
                }
            }
            geschwader.set(i, geschwader.get(min));
        }
    }

    /**
     * метод для условной бомбардировки
     */
    int i;
    public void bombLondon() {
        geschwader.get(i).refuel(geschwader.get(i).maxTankVolume);
        if (geschwader.get(i) instanceof Bomber) {
            ((Bomber) geschwader.get(i)).loadBombs();
            geschwader.get(i).fly();
            ((Bomber) geschwader.get(i)).boom();
            geschwader.get(i).notFly();
            geschwader.get(i).drainFuel();
        } else System.out.println("Моя не может бомбить");

    }

    /**
     * метод для условной перевозки пассажиров
     */
    int j;
    public void flyWithPassengers() {
        geschwader.get(i).refuel(geschwader.get(i).maxTankVolume);
        if (geschwader.get(j) instanceof Airliner) {
            ((Airliner) geschwader.get(j)).loadPassengers();
            geschwader.get(j).fly();
            ((Airliner) geschwader.get(j)).passengersGetOut();
            geschwader.get(j).notFly();
            geschwader.get(j).drainFuel();
        } else System.out.println("Моя не может возить человеков");

    }



    @Override
    void setName (String companyName){
        this.companyName = companyName;
    }

    @Override
    void calculateProfit () {
        profit = income - expense;
    }

    @Override
    void SetIncome ( int income){
        this.income = income;
    }

    @Override
    void SetExpense ( int expense){
        this.expense = expense;
    }
}
