package Model;

import Controller.DBManager;
import Model.Interfaces.ICategoria;

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
    private String tariff_class;
    private ICategoria cat;
    private String ambiental_class;

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
        return tariff_class;
    }

    public String getVlp() {
        return vlp;
    }


   public String getCategoriaName(){
        return cat.getNomeClasse();
   }

   public double getIncrementoCT(){
        return cat.getValoreClasse();
   }

   public double getIncrementoCA(){
        
        return 2.0;
   }
}
