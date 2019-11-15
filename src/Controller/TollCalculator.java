package Controller;

import Model.Highway;
import Model.Toll;
import Model.TollBoth;
import Model.Vehicle;


public class TollCalculator {
    private double amount=0;

    public TollCalculator(Vehicle vehicle, Highway highway, String endingTB, String ticket){
        double toll = 0;
        double startingKM;
        double arrivalKM;

        double route;
        double eurSur;
        double tariffSur;


        Toll tl= new Toll(highway, vehicle);
        toll= tl.calculateToll();
        System.out.println("Pedaggio Unitario: "+toll);
        System.out.println("Entrato da: "+Tools.fileReader(ticket));
        TollBoth startingTlb=  new TollBoth(Tools.fileReader(ticket));
        TollBoth arrivalTB = new TollBoth(endingTB);
        System.out.println("Uscito a: "+endingTB);

        DBManager db= new DBManager();

        startingKM=startingTlb.getKm();
        arrivalKM=arrivalTB.getKm();

        if (arrivalKM > startingKM) {
            route = arrivalKM - startingKM;
        }
        else {
            route = startingKM - arrivalKM;
        }
        System.out.println("Km Percorsi: "+route);

        eurSur=Constant.getVar(vehicle.getAmbiental_class());
        System.out.println("Modificatore classe ambientale: "+eurSur);

        tariffSur=Constant.getVar(vehicle.getTariff_class());
        System.out.println("Modificatore classe tariffaria: "+tariffSur);

        amount=toll*route;
        amount+=(amount*eurSur);
        amount+=(amount*tariffSur);
        amount+=Constant.getVar("IVA");
        amount=Math.round(amount * 10) / 10.0;
        amount=Math.round(amount * 100.0) / 100.0;

    }
    public double getAmount(){
        return this.amount;
    }


}
