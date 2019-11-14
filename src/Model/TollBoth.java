package Model;

import Controller.DBManager;

public class TollBoth {
    String name;
    int Km;

    public TollBoth(String name) {
        this.name = name;
        DBManager db=new DBManager();

    }

    public int getKm() {
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
