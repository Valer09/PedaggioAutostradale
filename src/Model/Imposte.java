package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modello Imposte: per la rappresentazione dei dati tabellari sulle imposte nella view gestionale
 *
 *   @author Valerio Marchitelli
 *   @author Jacopo Cicoria
 *   @author Antonio Angelini
 *   @author Mattia Lenza
 *
 * */
public class Imposte{
    private final StringProperty nomeImposta;
    private final DoubleProperty valoreImposta;

    /**
     * Costruttore di classe. Utilizza le property SimpleStringProperty e SimpleDoubleProperty ed imposta il loro valore al valore dei dati passati
     * @param nome nome dell'imposta
     * @param valore valore dell'imposta
     */
    public Imposte(String nome, Double valore){
        this.nomeImposta=   new SimpleStringProperty(nome);
        this.valoreImposta= new SimpleDoubleProperty(valore);
    }

    /**
     * @return <b>String</b> - restituisce il nome dell'imposta
     */
    public String getNomeImposta() {
        return nomeImposta.getValue();
    }

    /**
     * @return <b>Double</b> - restituisce il valore dell'imposta
     */
    public Double getValoreImposta() {
        return valoreImposta.getValue();
    }

    /**
     * @return <b>StringProperty</b> - restituisce la property
     */
    public StringProperty getNomeImpostaProperty(){
        return nomeImposta;
    }

    /**
     * @return <b>DoubleProperty</b> - restituisce la property
     */
    public DoubleProperty getvaloreImpostaProperty(){
        return valoreImposta;
    }

}



