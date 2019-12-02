package Controller;

import Model.Highway;
import Model.TollBoth;
import Model.Vehicle;

import java.util.Calendar;

/**
 * Controller per la la gestione e logica del calcolo del pedaggio totale
 * @author Valerio Marchitelli
 * @author Jacopo Cicoria
 * @author Antonio Angelini
 * @author Mattia Lenza
 *
 */

public class TollCalculator {

    /**
     * Il metodo restituisce il prezzo totale da pagare alla view operatore.fxml.
     * Si basa sui dati importati dal veicolo, l'autostrada e i caselli coinvolti ed implementa la logica per il calcolo su di essi.
     * Tiene conto di:
     * - Tariffa unitaria (<b>TU</b>)
     * - Imposte categoria veicolo (<b>ICTV</b>)
     * - Imposte classe ambientale veicolo (<b>ICAV</b>)
     * - Km percorsi, calcolati in base ai caselli (<b>route</b>)
     * Il metodo tiene conto anche delle imposte per classi ambientali,
     * e del fatto che bisogna tener conto di esse soltanto dal 2021 per i mezzi di trasporto pesanti e dal 2026 per il resto dei veicoli.
     *
     * Questa differenziazione avviene attraverso un overload del metodo <b>calculateToll</b> che ha signature differenti per consentire
     * di prendere in considerazione oppure no la classe ambientale, e del metodo <b>getRoute</b> che calcola il chilometraggio riferendo ai caselli.
     * @param vehicle - Istanza del veicolo coinvolto al pagamento
     * @param highway - Istanza dell'autostrada coinvolta nel pagamento
     * @param startingTB - Istanza del casello di partenza dell'utente
     * @param endingTB - Istanza del casello di arrivo dell'utente
     * @return <b>double</b> - Restituisce il totale del pedaggio
     */
    public static double getToll(Vehicle vehicle, Highway highway, TollBoth startingTB, TollBoth endingTB ){
        double TU = highway.getTU();
        double ICTV = vehicle.getIncrementoCT();
        double ICAV = vehicle.getIncrementoCA();

        double startingKM=startingTB.getKm();
        double arrivalKM=endingTB.getKm();
        double route = getRoute(startingKM, arrivalKM);

        String categoria = vehicle.getCategoriaName();

        /*
        System.out.println("Km Percorsi: "+route);
        System.out.println("Entrato da: "+startingTB.getName());
        System.out.println("Uscito a: "+endingTB.getName());
        System.out.println("TU: "+TU);
        System.out.println("Modificatore classe tariffaria: "+ICTV);
        System.out.println("Categoria: "+categoria); */
        int year = Calendar.getInstance().get(Calendar.YEAR);


        if(year >= 2026){
            //System.out.println("Modificatore classe ambientale: "+ICAV);
            return calculateToll(route, ICTV, ICAV,TU);
        }
        else if(year >= 2021 && categoria != "CLASSE_A"){
            //System.out.println("Modificatore classe ambientale: "+ICAV);
            return calculateToll(route, ICTV, ICAV,TU);
        }

        return calculateToll(route, ICTV, TU);
    }

    private static double calculateToll(double route, double ICTV, double TU){
        double amount = 0;
        amount=TU*route;
        amount+= ICTV*amount;
        amount+=(amount*DBManager.getIVA());
        amount=Tools.roundUp(amount);

        return amount;
    }

    private static double calculateToll(double route, double ICTV, double ICAV, double TU){
        double amount = 0;
        amount=TU*route;
        amount+= ICTV*amount;
        amount+=(amount*ICAV);
        amount+=(amount*DBManager.getIVA());
        amount=Tools.roundUp(amount);

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
