package Model;

import Model.Interfaces.IImposte;
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
public class ImposteGeneriche implements IImposte {
    private final StringProperty nomeImposta;
    private final DoubleProperty valoreImposta;

    /**
     * Costruttore di classe. Utilizza le property SimpleStringProperty e SimpleDoubleProperty ed imposta il loro valore al valore dei dati passati
     * @param nome nome dell'imposta
     * @param valore valore dell'imposta
     */
    public ImposteGeneriche(String nome, Double valore){
        this.nomeImposta=   new SimpleStringProperty(nome);
        this.valoreImposta= new SimpleDoubleProperty(valore);
    }

    /**
     * @return <b>String</b> - Restituisce il nome dell'imposta
     */
    @Override
    public String getNome() {
        return nomeImposta.getValue();
    }
    /**
     * @return <b>Double</b> - Restituisce il valore dell'imposta
     */
    @Override
    public double getValore() {
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



