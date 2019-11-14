package Controller;

import Model.Highway;
import Model.Toll;
import Model.Vehicle;


public class TollCalculator {
    private double amount=0;

    public TollCalculator(Vehicle vehicle, Highway highway, String startingTB, String ticket){
        double toll =0;
        double startingKM;
        double arrivalKM;
        double route;
        double eurSur;

        String tlb="";


        //Toll tl= new Toll(highway, vehicle);
        toll= tl.calculateToll();
        tlb=Tools.fileReader(ticket);

        DBManager dbist = new DBManager();

        startingKM=dbist.getKM(startingTB);
        arrivalKM=dbist.getKM(tlb);

        route=(arrivalKM-startingKM);
        if (route <0 )
            route=route + ((-2)*route);

        eurSur=Constant.getVar("EUR"+vehicle.getAmbiental_class());

        amount=toll*route;
        amount+=(amount*eurSur);
        amount+=Constant.getVar("IVA");
        amount=Math.round(amount * 10) / 10.0;
        amount=Math.round(amount * 100.0) / 100.0;


    }
    public double getAmount(){
        return this.amount;
    }


}
