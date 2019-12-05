package Model.Interfaces;

/**
 *   Interfaccia Categorie
 *
 *   @author Valerio Marchitelli
 *   @author Jacopo Cicoria
 *   @author Antonio Angelini
 *   @author Mattia Lenza
 *
 * */
public interface IImposte {

    /**
     * @return - <b>String</b> Restituisce il nome della categoria veicolo
     */
    String getNome();

    /**
     * @return - <b>double</b> Restituisce il valore di imposta della categoria veicolo
     */
    double getValore();

}
