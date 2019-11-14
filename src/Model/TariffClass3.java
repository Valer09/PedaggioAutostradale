package Model;

import Model.Interfaces.ITariffClass;

public class TariffClass3 implements ITariffClass {
    String name;
    double tariff;

    @Override
    public double getTariff() {
        return 0;
    }

    @Override
    public double getValueFormDB() {
        return 0;
    }
}
