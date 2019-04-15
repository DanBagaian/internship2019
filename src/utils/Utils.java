package utils;

import model.Atm;
import model.Card;

import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    public static List<Atm> resetAtmList(List<Atm> originalList) {
        List<Atm> newList = new ArrayList<>();

        for (Atm element : originalList)
            newList.add(new Atm(element));

        return newList;
    }

    public static List<Card> resetCardList(List<Card> originalList) {
        List<Card> newList = new ArrayList<>();

        for (Card element : originalList)
            newList.add(new Card(element));

        return newList;
    }

    public static List<Atm> resetPaths(List<Atm> originalList) {
        return resetAtmList(originalList);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("MMM-dd-yyyy HH:mm");

        return localDateFormat.format(date);
    }
}
