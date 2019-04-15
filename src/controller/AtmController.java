package controller;

import model.Atm;
import model.Card;
import utils.Utils;

import java.util.*;

public class AtmController {
    private Calendar minimumDate = new GregorianCalendar(2090, Calendar.JANUARY, 1, 0, 0, 0);
    private List<Atm> minimumRoute;

    public List<Atm> getAtmsRoute() {
        List<Atm> atmList = initATMs();
        List<Card> cardList = initCards();
        int startingPointIndex = 0;
        Calendar currentDate = new GregorianCalendar(2019, Calendar.MARCH, 19, 11, 30, 0);
        int currentMoney = 0;
        List<Atm> currentPath = new ArrayList<>();

        getAllValidPaths(
                atmList,
                cardList,
                startingPointIndex,
                currentDate,
                currentMoney,
                currentPath
        );

        return minimumRoute;
    }

    private List<Atm> initATMs() {
        List<Atm> atmList = new ArrayList<>();

        atmList.add(new Atm(
                "ATM0",
                new GregorianCalendar(2019, Calendar.MARCH, 19, 1, 0, 0),
                new GregorianCalendar(2019, Calendar.MARCH, 19, 23, 0, 0),
                0,
                new int[]{0, 5, 60, 30, 45}
        ));

        atmList.add(new Atm(
                "ATM1",
                new GregorianCalendar(2019, Calendar.MARCH, 19, 12, 0, 0),
                new GregorianCalendar(2019, Calendar.MARCH, 19, 18, 0, 0),
                5000,
                new int[]{5, 0, 40, 40, 45}
        ));

        atmList.add(new Atm(
                "ATM2",
                new GregorianCalendar(2019, Calendar.MARCH, 19, 10, 0, 0),
                new GregorianCalendar(2019, Calendar.MARCH, 19, 17, 0, 0),
                5000,
                new int[]{60, 40, 0, 15, 30}
        ));

        atmList.add(new Atm(
                "ATM3",
                new GregorianCalendar(2019, Calendar.MARCH, 18, 22, 0, 0),
                new GregorianCalendar(2019, Calendar.MARCH, 19, 13, 0, 0),
                5000,
                new int[]{30, 40, 15, 0, 15}
        ));

        atmList.add(new Atm(
                "ATM4",
                new GregorianCalendar(2019, Calendar.MARCH, 19, 17, 0, 0),
                new GregorianCalendar(2019, Calendar.MARCH, 20, 1, 0, 0),
                5000,
                new int[]{45, 45, 30, 15, 0}
        ));

        return atmList;
    }

    private List<Card> initCards() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(20000));
        cardList.add(new Card(3000));

        return cardList;
    }

    private void getAllValidPaths(List<Atm> atmList, List<Card> cardList,
                                         int prevAtmIndex, Calendar currentDate, int currentMoney,
                                         List<Atm> currentPath) {

        if (currentMoney == 8000) {
            if (currentDate.compareTo(minimumDate) < 0) {
                minimumRoute = currentPath;
                minimumDate = currentDate;
            }
            return;
        }

        Atm previousAtm = atmList.get(prevAtmIndex);

        for (Atm atm : atmList) {
            int atmIndex = atmList.indexOf(atm);
            Calendar futureDate = (Calendar) currentDate.clone();
            futureDate.setTimeInMillis(currentDate.getTimeInMillis() + previousAtm.getDurationTo(atmIndex));

            if (atmIndex != prevAtmIndex && atm.willBeOpen(futureDate) && atm.hasMoney()) {
                boolean success = false;
                int withdrawnAmount = 0;

                List<Card> cardListCopy = Utils.resetCardList(cardList);
                for (Card card : cardListCopy) {
                    if (card.isEligibleAtATM(atmIndex)) {
                        withdrawnAmount += card.withdrawFromATM(atmIndex, currentMoney + withdrawnAmount);
                        success = true;
                    }
                }

                if (success) {
                    List<Atm> atmListCopy = Utils.resetAtmList(atmList);

                    Atm currentAtmCopy = atmListCopy.get(atmIndex);
                    currentAtmCopy.withdraw(withdrawnAmount);

                    List<Atm> currentPathCopy = Utils.resetPaths(currentPath);
                    currentPathCopy.add(currentAtmCopy);

                    getAllValidPaths(
                            atmListCopy,
                            cardListCopy,
                            atmIndex,
                            futureDate,
                            currentMoney + withdrawnAmount,
                            currentPathCopy);
                }
            }
        }
    }

    public Calendar getMinimumDate() {
        return minimumDate;
    }
}
