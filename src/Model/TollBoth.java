package Model;

import Controller.DBManager;

public class TollBoth {
    private String name;
    private double Km;
    private String autostrada;

    public TollBoth(String name, double km, String autostrada) {
        this.name = name;
        this.Km = km;
        this.autostrada = autostrada;
    }

    public double getKm() {
        return Km;
    }

    public String getName() {
        return name;
    }

    public String getAutostrada(){
        return this.autostrada;
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
