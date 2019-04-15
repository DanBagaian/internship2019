package model;

import utils.Utils;

import java.util.Calendar;

public class Atm {
    private String name;
    private Calendar openingDate;
    private Calendar closingDate;
    private int[] durationTo;
    private int money;

    public Atm(String name, Calendar openingDate, Calendar closingDate, int money, int[] durationTo) {
        this.name = name;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.money = money;
        this.durationTo = durationTo;
    }

    public Atm(Atm prevAtm) {
        this.name = prevAtm.name;
        this.openingDate = prevAtm.openingDate;
        this.closingDate = prevAtm.closingDate;
        this.money = prevAtm.money;

        this.durationTo = new int[5];
        System.arraycopy(prevAtm.durationTo, 0, durationTo, 0, 5);
    }

    public void withdraw(int amount) {
        money -= amount;
    }

    public long getDurationTo(int index) {
        return durationTo[index] * 60000L;
    }

    public boolean willBeOpen(Calendar time) {
        return time.after(openingDate) && time.before(closingDate);
    }

    public boolean hasMoney() {
        return money > 0;
    }

    @Override
    public String toString() {
        return "ATM Name: " + name + ", Opens: " + Utils.formatDate(openingDate.getTime()) + ", Closes: "
                + Utils.formatDate(closingDate.getTime()) + ", Money Left: " + money;
    }
}
