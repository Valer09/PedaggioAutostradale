package Model;

import Controller.DBManager;

import java.util.HashMap;

public class Highway {
    private double TU;
    private String name;
    private HashMap <String, Double> tollbooths = null;

    public Highway(String highway){
        DBManager db=new DBManager();
        TU = db.getHighwayTU(highway);
        this.name = highway;
        tollbooths = db.getHighwayTollbooths(highway);

    }


    public double getTU(){
        return TU;
    }

    public HashMap <String, Double> getTollbooths(){
        return tollbooths;
    }

    public static void addTollboth(String highway, String name, double KM){
        DBManager.addTollboth(highway,name,KM);
    }
    public static void addHighway(String name, double TU){
        DBManager.addHighway(name,TU);
    }

    public static void setTU(String highway,double TU) {
        DBManager.setTU(highway,TU);
    }

    public static void setName(String highway, String newName){
        DBManager.setHighwayName(highway, newName);

    }

    public static void delHighway(String highway){
        DBManager.delHighway(highway);
    }

    public String getName() {
        return name;
    }
}
