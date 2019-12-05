package Model;

import Controller.DB_Controller.DBManager;
import Model.Interfaces.IImposte;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Vehicle {

    private String vlp;
    private String model;
    private String brand;
    private int year;
    private int axes;
    private Float weight;
    private Float height;
    private int cylinder_capacity;
    private IImposte cat;
    private String ambiental_class;

    /**
     * Costruttore della classe vehicle, dove viene istanziata la connessione al database per potersi prendere i valori associati alla tabella vehicle
     * @param vlp stringa contentente la targa veicolo
     */
    public Vehicle(String vlp){
        this.vlp = vlp;
        DBManager db=new DBManager();
        ResultSet result = db.getVeichleInfo(vlp);

        try {
            if (result.next()){
                this.model = result.getString("modello");
                this.brand = result.getString("marca");
                this.ambiental_class = result.getString("c_ambientale");
                this.year = result.getInt("anno");
                this.axes = result.getInt("assi");
                this.cylinder_capacity = result.getInt("cilindrata");
                this.weight = result.getFloat("peso");
                this.height = result.getFloat("altezza");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Decidere la classe tariffaria
        if(this.height <= 1.30){
            //Categoria Leggera = Classe Tariffaria A
            this.cat = new CategoriaLeggera();
        }
        else{
            //Categoria Pesante, Classe Tariffaria decisa dal numero di assi
            this.cat = new CategoriaPesante(this.axes);
        }
}

    public Float getHeight() {
        return height;
    }

    public Float getWeight() {
        return weight;
    }

    public int getAxes() {
        return axes;
    }

    public int getCylinder_capacity() {
        return cylinder_capacity;
    }

    public int getYear() {
        return year;
    }

    public String getAmbiental_class() {
        return ambiental_class;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getTariff_class() {
        return cat.getNome();
    }

    public String getVlp() {
        return vlp;
    }


   public String getCategoriaName(){
        return cat.getNome();
   }

   public double getIncrementoCT(){
        return cat.getValore();
   }

   public double getIncrementoCA(){
        return DBManager.getClassValue(this.ambiental_class);
   }
}
