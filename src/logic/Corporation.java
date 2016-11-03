package logic;

/**
 * Created by Piatrok on 03.11.16.
 */
public abstract class Corporation {
    abstract void setName(String companyName);
    abstract void calculateProfit();
    abstract void SetIncome(int income);
    abstract void SetExpense(int expense);

    protected int income;
    protected int expense;
    protected String companyName;
    protected int profit;
}
