package Model;

/**
 * This abstract class HV, represents all kind of heavy vehicles (class 3to5)
 * Differently to Light Vehicles, Heavy Vehicles differs by category.
 * So, this HV abstract class will be implemented in classes which refers to proper vehicle classes
 */

public abstract class HV implements IVehicle {

    //private static final int axes = 2;
    //private static final char vclass = 'A';
    protected static final double limit = 1.30;
    protected double height=1.31;
    protected static final String type = "HEAVY";
    protected double weight=3100;
    protected int cc=2000,year=1900;
    protected String model,brand,VLI;

    @Override
    abstract public char getVclass();

    @Override
    abstract public int getAxes();

    @Override
    abstract public double getHeight();

    @Override
    public void setHeight(double height) {

    }

    @Override
    public void setWeight(double weight) {

    }

    @Override
    public void setYear(int year) {

    }

    @Override
    public void setVLI(String VLI) {

    }

    @Override
    public void setBrand(String brand) {

    }

    @Override
    public void setModel(String model) {

    }

    public int getYear(){
        return this.year;
    }

    public double getWeight(){
        return this.weight;
    }

    public String getVLI(){

        return this.VLI;
    }

    public String getModel(){
        return this.model;
    }

    public String getBrand(){
        return this.brand;
    }


    /**
     * Costruttore classe HV.
     * Inizializzazione dei dati del veicolo pesante.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     */
    HV(      String brand,
              String model,
              String VLI
    )

    {
        this.model=model;
        this.brand=brand;
        this.VLI=VLI;

    }

    /**
     * Costruttore classe HV.
     * Inizializzazione dei dati del pesante classe con cilindrata e peso.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     */
    HV(String brand,
        String model,
        String VLI,
        int cc,
        double height
    )
    {

        if (height < limit)
            System.out.println("ERRORE ALTEZZA"); // GESTIRE ECCEZIONE
        else
        {
            this.model=model;
            this.brand=brand;
            this.VLI=VLI;
            this.height=height;
        }

    }


    /**
     * Costruttore classe HV.
     * Inizializzazione dei dati del veicolo pesante classe con cilindrata, peso e altezza.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     * @param weight Peso veicolo <b>(double)</b>
     */
    HV(String brand,
        String model,
        String VLI,
        int cc,
        double height,
        double weight)

    {
        if (height < limit)
            System.out.println("ERRORE ALTEZZA"); // GESTIRE ECCEZIONE
        else
        {
            this.height=height;
            this.model=model;
            this.brand=brand;
            this.VLI=VLI;
            this.height=height;
            this.weight=weight;
        }




    }



}
