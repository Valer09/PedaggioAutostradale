package Model;
import Model.Interfaces.*;
import Controller.DB_Controller.DBManager;

/**
 * Modello CategoriaPesante: rappresenta i dati delle categorie pesanti di veicoli
 * @author Valerio Marchitelli
 * @author Jacopo Cicoria
 * @author Antonio Angelini
 * @author Mattia Lenza
 *
 */

public class CategoriaPesante implements IImposte {

    private String classe;
    private double valore;

    /**
     * Cotruttore per il modello Categoria Pesante. In base al numero di assi, imposta il nome della classe ed il valore dell'imposta
     * @param assi
     */
    public CategoriaPesante(int assi) {

        if (assi==2)
            classe="CLASSE_B";
        else
            classe= "CLASSE_"+assi;

        valore = DBManager.getClassValue(classe); // QUERY AL DB per la classe clacolata

    }

    /**
     * @return <b>String</> - restituisce il nome della categoria veicolo
     */
    @Override
    public String getNome() {
        return this.classe;
    }

    /**
     * @return <b>double</> - Restituisce il valore di imposta della categoria veicolo
     */
    @Override
    public double getValore() {
        return this.valore;
    }
}

