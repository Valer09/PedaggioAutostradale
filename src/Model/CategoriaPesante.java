package Model;
import Model.Interfaces.*;
import Controller.DBManager;


public class CategoriaPesante  implements ICategoria {

    private String classe;
    private double valore;

    public CategoriaPesante(int assi) {

        if (assi==2)
            classe="CLASSE_B";
        else
            classe= "CLASSE_"+assi;

        valore = DBManager.getClassValue(classe); // QUERY AL DB per la classe clacolata

    }

    public String getNomeCategoria() {
        return this.classe;
    }

    ;

    public double getValoreCategoria() {
        return this.valore;
    }
}

