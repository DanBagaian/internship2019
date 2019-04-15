package model;

public class Card {
    private int amount;
    private boolean[] eligibleATMs;

    public Card(int amount) {
        this.amount = amount;
        eligibleATMs = new boolean[5];
        for (int i = 0; i < 5; i++)
            eligibleATMs[i] = true;
    }

    public Card(Card card) {
        this.amount = card.amount;
        eligibleATMs = new boolean[5];

        int i = 0;
        for (boolean eligible : card.eligibleATMs) {
            eligibleATMs[i] = eligible;
            i++;
        }
    }

    public int withdrawFromATM(int atmIndex, int currentMoney) {
        eligibleATMs[atmIndex] = false;

        int withdrawnAmount = getMaxAvailableAmount();
        if (withdrawnAmount + currentMoney > 8000)
            withdrawnAmount = 8000 - currentMoney;
        amount -= withdrawnAmount;

        return withdrawnAmount;
    }

    public boolean isEligibleAtATM(int index) {
        return eligibleATMs[index];
    }

    private int getMaxAvailableAmount() {
        if (amount >= 2000)
            return 2000;
        return amount;
    }
}
