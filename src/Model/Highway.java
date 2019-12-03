package Model;
import Controller.DB_Controller.DBManager;

import java.util.HashMap;

/**
 * Modello Higway: rappresenta i dati delle autostrade
 * @author Valerio Marchitelli
 * @author Jacopo Cicoria
 * @author Antonio Angelini
 * @author Mattia Lenza
 *
 */

public class Highway {
    private double TU;
    private String name;
    private HashMap <String, Double> tollbooths = null;

    /**
     * Costruttore dell'Autostrada. Prende in input il nome e la tariffa unitaria e successivamente chiede al DB la lista dei caselli associata all'autotrada
     * @param name Parametro di tipo Stringa che rappresenta il nome dell'autostrada
     * @param TU Parametro di tipo double che rappresenta la tariffa unitaria associata all'autostrada
     */
    public Highway(String name, double TU){
        DBManager db=new DBManager();
        this.TU = TU;
        this.name = name;
        tollbooths = db.getHighwayTollbooths(name);
    }

    /**
     * @return Restituisce la Tariffa Unitaria dell'autostrada di tipo double
     */
    public double getTU(){
        return TU;
    }

    /**
     * @return Retituisce un HashMap contenente tutti i Caselli associati all'autostrada
     */
    public HashMap <String, Double> getTollbooths(){
        return tollbooths;
    }

    /**
     * @return Restituisce il nome dell'Autostrada
     */
    public String getName() {
        return name;
    }

    /**
     * Richiama il metodo addTollBoth del DBManager
     * @param highway Parametro di tipo Stringa che rappresenta il nome dell'autostrada
     * @param name Parametro di tipo Stringa che rappresenta il nome del casello
     * @param KM Parametro di tipo double che rappresenta il km dove è posizionato il casello
     */
    public static void addTollboth(String highway, String name, double KM){
        DBManager.addTollboth(highway,name,KM);
    }

    /**
     * Richiama il metodo addHigway del DBManager
     * @param name Parametro di tipo Stringa che rappresenta il nome dell'autostrada
     * @param TU Parametro di tipo double che rappresenta la Tariffa Unitaria dell'autostrada
     */
    public static void addHighway(String name, double TU){
        DBManager.addHighway(name,TU);
    }

    /**
     * Richiama il metodo del DBManager setTU
     * @param highway Parametro di tipo Stringa che rappresenta il nome dell'autostrada
     * @param TU Parametro di tipo double che rappresenta la Tariffa Unitaria dell'autostrada
     */
    public static void setTU(String highway, double TU) {
        DBManager.setTU(highway,TU);
    }

    /**
     * Richiama il metodo setName del DBManager
     * @param highway Parametro di tipo Stringa che rappresenta il nome dell'autostrada
     * @param newName Parametro di tipo Stringa che rappresenta il nuovo nome dell'autostrada
     */
    public static void setName(String highway, String newName){
        DBManager.setHighwayName(highway, newName);

    }

    /**
     * Richiam il metodo dleHigway del DBManager
     * @param highway Parametro di tipo Stringa che rappresenta il nome dell'autostrada
     */
    public static void delHighway(String highway){
        DBManager.delHighway(highway);
    }


}
