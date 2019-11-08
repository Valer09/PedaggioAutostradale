

package Model;

/**
 * Class for manage light Vehicle. It belongs to Class A Vehicle only
 *
**/
public class LV implements IVehicle {

    private static final int axes = 2;
    private static final double limit = 1.30;
    private static final char vclass = 'A';
    private static final String type = "LIGHT";
    private double height,weight=0;
    private int cc=50,year=1900;
    private String model,brand,VLI;


    public String getVLI(){

        return this.VLI;
    }

    public String getModel(){
        return this.model;
    }

    public String getBrand(){
        return this.brand;
    }

    public char getVclass(){
        return vclass;
    }

    public int getAxes(){
        return this.axes;
    }

    public int getYear(){
        return this.year;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getHeight(){
        return this.height;
    }

    @Override
    public void setHeight(double height) {
        if (height > limit)
            System.out.println("ERRORE ALTEZZA"); // GESTIRE ECCEZIONE
        else
            this.height=height;

    }

    @Override
    public void setWeight(double Weight) {
        this.weight=weight;

    }

    @Override
    public void setYear(int year) {
        this.year=year;

    }

    @Override
    public void setVLI(String VLI) {
        this.VLI=VLI;

    }

    @Override
    public void setBrand(String brand) {
        this.brand=brand;

    }

    @Override
    public void setModel(String model) {
        this.model=model;

    }

    public String prinfInfo(){
        return "Class: " + vclass + "; Axes: " + axes + "Height: " + this.height +"\n"
                + "Weight: " + this.weight + "cc: " + this.cc + "\n"
                + "Brand: " + this.brand + "Model: " + this.model + "\n"
                + "Year: " + this.year;
    }


    /**
     * Costruttore classe lightVehicle.
     * Inizializzazione dei dati del veicolo leggero.
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     */
    public LV(String brand,
       String model,
       String VLI
               )
    {
        this.model=model;
        this.brand=brand;
        this.VLI=VLI;
        this.height=limit;
        this.weight=0;


    }

    /**
     * Costruttore classe lightVehicle.
     * Inizializzazione dei dati del veicolo leggero.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     */
    public LV(
            String brand,
            String model,
            String VLI,
            int cc,
            double height
            )

    {
        if (height > limit)
            System.out.println("ERRORE ALTEZZA"); // GESTIRE ECCEZIONE
        else
        {
            this.height = height;
            this.model = model;
            this.brand = brand;
            this.VLI = VLI;
            this.height = height;
            this.weight = 0;
        }

    }

    /**
     * Costruttore classe lightVehicle.
     * Inizializzazione dei dati del veicolo leggero.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     * @param weight Peso veicolo <b>(double)</b>
     */
    public LV(String brand,
       String model,
       String VLI,
       int cc,
       double height,
       double weight)

    {
        if (height > limit)
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
