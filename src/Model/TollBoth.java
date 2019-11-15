package Model;

import Controller.DBManager;

public class TollBoth {
    private String name;
    private double Km;

    public TollBoth(String name) {
        this.name = name;
        DBManager db=new DBManager();
        this.Km = db.getTollBothKm(name);
    }

    public double getKm() {
        return Km;
    }

    public String getName() {
        return name;
    }

    public void setKm(int km) {
        Km = km;
    }

    public void setName(String name) {
        this.name = name;
    }


}
