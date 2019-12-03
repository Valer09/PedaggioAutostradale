package Model;
import Model.Interfaces.*;
import Controller.DB_Controller.DBManager;

/**
 * Modello CategoriaLeggera: rappresenta i dati delle categorie leggere di veicoli
 * @author Valerio Marchitelli
 * @author Jacopo Cicoria
 * @author Antonio Angelini
 * @author Mattia Lenza
 *
 */

public class CategoriaLeggera implements ICategoria{
    private String classe;
    private double valore;

    /**
     * Cotruttore per il modello Categoria Leggera.
     */
    public CategoriaLeggera(){

        classe="CLASSE_A"; // OPPURE QUERY AL DB
        valore = DBManager.getClassValue(classe); // QUERY AL DB per la classe clacolata

    }

    /**
     * @return <b>String</> - restituisce il nome della categoria veicolo
     */
    public String getNomeCategoria(){
        return this.classe;
    }

    /**
     * @return <b>double</> - Restituisce il valore di imposta della categoria veicolo
     */
    public double getValoreCategoria(){
        return this.valore;
    };


}

