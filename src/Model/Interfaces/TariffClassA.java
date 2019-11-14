package Model.Interfaces;

public class TariffClassA implements ITariffClass{
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
