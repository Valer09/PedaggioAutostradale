package Controller;

import Model.Highway;
import Model.TollBoth;
import Model.Vehicle;

import java.util.Calendar;


public class TollCalculator {

    public static double getToll(Vehicle vehicle, Highway highway, TollBoth startingTB, TollBoth endingTB ){
        double TU = highway.getTU();
        double ICTV = vehicle.getIncrementoCT();
        double ICAV = vehicle.getIncrementoCA();

        double startingKM=startingTB.getKm();
        double arrivalKM=endingTB.getKm();
        double route = getRoute(startingKM, arrivalKM);

        String categoria = vehicle.getCategoriaName();


        System.out.println("Km Percorsi: "+route);
        System.out.println("Entrato da: "+startingTB.getName());
        System.out.println("Uscito a: "+endingTB.getName());
        System.out.println("TU: "+TU);
        System.out.println("Modificatore classe tariffaria: "+ICTV);
        System.out.println("Categoria: "+categoria);
        int year = Calendar.getInstance().get(Calendar.YEAR);


        if(year >= 2026){
            System.out.println("Modificatore classe ambientale: "+ICAV);
            return calculateToll(route, ICTV, ICAV,TU);
        }
        else if(year >= 2021 && categoria != "CLASSE_A"){
            System.out.println("Modificatore classe ambientale: "+ICAV);
            return calculateToll(route, ICTV, ICAV,TU);
        }

        return calculateToll(route, ICTV, TU);
    }

    private static double calculateToll(double route, double ICTV, double TU){
        double amount = 0;
        amount=TU*route;
        amount+= ICTV*amount;
        amount+=(amount*DBManager.getIVA());
        amount=Math.round(amount * 10) / 10.0;
        amount=Math.round(amount * 100.0) / 100.0;

        return amount;
    }

    private static double calculateToll(double route, double ICTV, double ICAV, double TU){
        double amount = 0;
        amount=TU*route;
        amount+= ICTV*amount;
        amount+=(amount*ICAV);
        amount+=(amount*DBManager.getIVA());
        amount=Math.round(amount * 10) / 10.0;
        amount=Math.round(amount * 100.0) / 100.0;

        return amount;
    }

    public static double getRoute(double startingKM, double arrivalKM){
        double route = 0;
        if (arrivalKM > startingKM) {
            route = arrivalKM - startingKM;
        }
        else {
            route = startingKM - arrivalKM;
        }
        return route;
    }
}
