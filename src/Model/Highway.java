package Model;

import Controller.DBManager;
import Controller.VarGetter;
import java.util.HashMap;

public class Highway {
    protected double TU;
    protected HashMap <String, Double> tollbooths = null;

    public Highway(String highway){

        TU = VarGetter.getTU(highway);
        DBManager db=new DBManager();
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
