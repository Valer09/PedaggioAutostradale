package Model;

import Controller.DBManager;
import Controller.VarGetter;
import java.util.HashMap;

public class Highway {
    private double TU;
    private HashMap <String, Double> tollbooths = null;

    public Highway(String highway){
        DBManager db=new DBManager();
        TU = db.getHighwayTU(highway);
        tollbooths = db.getHighwayTollbooths(highway);

    }

    public double getTU(){
        return TU;
    }

    public HashMap <String, Double> getTollbooths(){
        return tollbooths;
    }

    public void setTollbooths(HashMap<String, Double> tollbooths) {
        this.tollbooths = tollbooths;
    }

    public void setTU(double TU) {
        this.TU = TU;
    }
}
