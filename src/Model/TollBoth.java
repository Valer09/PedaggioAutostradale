package Model;

import Controller.DBManager;

public class TollBoth {
    private String name;
    private double Km;

    public TollBoth(String name, double km) {
        this.name = name;
        this.Km = km;
    }

    public double getKm() {
        return Km;
    }

    public String getName() {
        return name;
    }

    public static void setKM(String tollbooth, double KM) {
        DBManager.setTollbothKM(tollbooth,KM);
    }

    public static void delTollBooth(String tollbooth){
        DBManager.delTollboth(tollbooth);
    }

    public static void setName(String toolbooth, String newName) {
        DBManager.setTollbothName(toolbooth, newName);
    }

}
