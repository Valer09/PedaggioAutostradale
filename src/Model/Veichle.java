package Model;

import Controller.DBManager;

import java.sql.ResultSet;

public class Veichle {

    private String vlp;
    private String model;
    private String brand;
    private int year;
    private int axes;
    private Float weight;
    private Float height;
    private int cylinder_capacity;
    private String tariff_class;
    private String ambiental_class;

    public Veichle(String vlp){
        this.vlp = vlp;
        DBManager db=new DBManager();
        //ResultSet result = db.getVeichleInfo();

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


    public void setAmbiental_class(String ambiental_class) {
        this.ambiental_class = ambiental_class;
    }

    public void setAxes(int axes) {
        this.axes = axes;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCylinder_capacity(int cylinder_capacity) {
        this.cylinder_capacity = cylinder_capacity;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTariff_class(String tariff_class) {
        this.tariff_class = tariff_class;
    }

    public void setVlp(String vlp) {
        this.vlp = vlp;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
