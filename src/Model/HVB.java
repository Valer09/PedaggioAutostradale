package Model;

/**
 * Class for Heavy Vehicles class B.
 *
 **/
public class HVB extends HV {

    private static final int axes = 2;
    private static final char vclass = 'B';
    //private static final double limit = 1.31;
    //protected double height
    //protected static final String type = "HEAVY";
    //protected double weight=3100;
    //protected int cc=2000,year=1900;
    //protected String model,brand,VLI;


    @Override
    public char getVclass() {
        return vclass;
    }

    @Override
    public int getAxes() {
        return axes;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public String prinfInfo(){
        return "Class: " + vclass + "; Axes: " + axes + "Height: " + this.height +"\n"
                + "Weight: " + this.weight + "cc: " + this.cc + "\n"
                + "Brand: " + this.brand + "Model: " + this.model + "\n"
                + "Year: " + this.year;
    }


    /**
     * Costruttore classe HVB.
     * Inizializzazione dei dati del veicolo pesante classe B.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     */
    public HVB(      String brand,
                 String model,
                 String VLI
                )

    {
        super(brand,model,VLI);

    }

    /**
     * Costruttore classe HVB.
     * Inizializzazione dei dati del pesante classe B con cilindrata e peso.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     */
    public HVB(String brand,
           String model,
           String VLI,
           int cc,
           double height
           )

    {
        super(brand,model,VLI,cc,height);

    }


    /**
     * Costruttore classe HVB.
     * Inizializzazione dei dati del veicolo pesante classe B con cilindrata, peso e altezza.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     * @param weight Peso veicolo <b>(double)</b>
     */
    public HVB(String brand,
                 String model,
                 String VLI,
                 int cc,
                 double height,
                 double weight)

    {
        super(brand,model,VLI,cc,height,weight);


    }




}

