package Model;
import Model.Interfaces.*;
import Controller.DBManager;

public class CategoriaLeggera implements ICategoria{
    private String classe;
    private double valore;

    public CategoriaLeggera(){

        classe="CLASSE_A"; // OPPURE QUERY AL DB
        valore = DBManager.getClassValue(classe); // QUERY AL DB per la classe clacolata

    }

    public String getNomeClasse(){
        return this.classe;
    };
    public double getValoreClasse(){
        return this.valore;
    };


}

