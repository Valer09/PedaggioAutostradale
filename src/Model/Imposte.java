package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Imposte{
    private final StringProperty nomeImposta;
    private final DoubleProperty valoreImposta;

    public Imposte(String nome, Double valore){
        this.nomeImposta=   new SimpleStringProperty(nome);
        this.valoreImposta= new SimpleDoubleProperty(valore);
    }


    public String getNomeImposta() {
        return nomeImposta.getValue();
    }
    public Double getValoreImposta() {
        return valoreImposta.getValue();
    }

    public StringProperty getNomeImpostaProperty(){
        return nomeImposta;
    }
    public DoubleProperty getvaloreImpostaProperty(){
        return valoreImposta;
    }

}



