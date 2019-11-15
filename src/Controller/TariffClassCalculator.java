package Controller;

import Model.Vehicle;

public class TariffClassCalculator {

    private String tariff_class;

    public TariffClassCalculator(Vehicle veicolo){
        System.out.println("Calcolo classe tariffaria...");
        if(veicolo.getHeight() <= 1.30){
            //Classe A
            this.tariff_class = "SURA";
        }
        else{
            switch (veicolo.getAxes()){
                case 2:
                    this.tariff_class = "SURB";
                break;
                case 3:
                    this.tariff_class = "SUR3";
                break;
                case 4:
                    this.tariff_class = "SUR4";
                break;
                case 5:
                    this.tariff_class = "SUR5";
                break;
                default:
            }
        }

    }

    public String getTariff_class() {
        return tariff_class;
    }
}
