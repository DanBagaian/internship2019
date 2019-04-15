package view;

import controller.AtmController;
import model.Atm;
import utils.Utils;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AtmController atmController = new AtmController();

        List<Atm> minimumAtmRoute = atmController.getAtmsRoute();

        System.out.println("The route which takes the least time is: \n");
        for (Atm atm : minimumAtmRoute) {
            System.out.println(atm);
        }

        System.out.println("\nIt will be finished by: " + Utils.formatDate(atmController.getMinimumDate().getTime()));
    }
}
