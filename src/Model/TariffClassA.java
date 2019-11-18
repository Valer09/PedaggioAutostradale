package Model;

import Model.Interfaces.ITariffClass;

public class TariffClassA implements ITariffClass {
    private String name = "SURA";
    private double tariff;

    public TariffClassA(String name, double tariff){

        this.name=name;
        this.tariff=tariff;

    }

    @Override
    public double getTariff() {
        return this.tariff;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
