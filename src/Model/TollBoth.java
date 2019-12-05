package Model;

import Controller.DB_Controller.DBManager;

public class TollBoth {
    private String name;
    private double Km;
    private String autostrada;

    /**
     * Costruttore della classe User, prende in input 3 valori quali, il nome, il km del casello ed l'autostrada dove è posizionato
     * @param name - nome casello
     * @param km - km casello
     * @param autostrada - Oggetto autostrada che gestisce il casello
     */
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

    /**
     * Vengono impostati nel database il casello e il km appartenente ad esso
     * @param tollbooth casello
     * @param KM - km del casello
     */
    public static void setKM(String tollbooth, double KM) {

        DBManager.setTollbothKM(tollbooth,KM);
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
     * Viene cancellato dal database il casello
     * @param tollbooth nome del casello
     */
    public static void delTollBooth(String tollbooth){

        DBManager.delTollboth(tollbooth);
    }

    /**
     * Viene modificato il nome del casello sul database
     * @param toolbooth - nome casello
     * @param newName - nuovo nome casello
     */
    public static void setName(String toolbooth, String newName) {

        DBManager.setTollbothName(toolbooth, newName);
    }

    /**
     * Metodo che setta l'autostrada associata al casello
     * @param nome Parametro di tipo Stringa che rappresenta il nome del casello
     * @param autostrada Parametro di tipo Stringa che rappresenta il nome dell'autostrada
     */
    public static void setHighway(String nome, String autostrada){
        DBManager.setTollbothHigway(nome, autostrada);
    }

}
