package Model;

import Model.Interfaces.ITariffClass;

public class TariffClass5 implements ITariffClass {
    private String name;
    private double tariff;

    public TariffClass5(String name, double tariff){

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
