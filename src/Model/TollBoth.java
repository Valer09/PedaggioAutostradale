package Model;

import Controller.DB_Controller.DBManager;

public class TollBoth {
    private String name;
    private double Km;
    private String autostrada;

    /**
     * Costruttore della classe User, prende in input 3 valori quali, il nome, il km del casello ed l'autostrada dove è posizionato
     * @param name
     * @param km
     * @param autostrada
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
     * @param tollbooth
     * @param KM
     */
    public static void setKM(String tollbooth, double KM) {
        DBManager.setTollbothKM(tollbooth,KM);
    }

    /**
     * Viene cancellato dal database il casello
     * @param tollbooth
     */
    public static void delTollBooth(String tollbooth){
        DBManager.delTollboth(tollbooth);
    }

    /**
     * Viene modificato il nome del casello sul database
     * @param toolbooth
     * @param newName
     */
    public static void setName(String toolbooth, String newName) {
        DBManager.setTollbothName(toolbooth, newName);
    }

}
