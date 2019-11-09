package Model;

/**
 * Class for Heavy Vehicles class 5
 *
 **/

public class HV5 extends HVB {

    private static final int axes = 5;
    private static final char vclass = '5';
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


    /**
     * Costruttore classe HV5.
     * Inizializzazione dei dati del veicolo pesante classe 5
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     */
    public HV5(      String brand,
              String model,
              String VLI
    )

    {
        super(brand,model,VLI);

    }

    /**
     * Costruttore classe HV5.
     * Inizializzazione dei dati del pesante classe 5 con cilindrata e peso.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     */
    public HV5(String brand,
        String model,
        String VLI,
        int cc,
        double height
    )

    {
        super(brand,model,VLI,cc,height);

    }


    /**
     * Costruttore classe HV5.
     * Inizializzazione dei dati del veicolo pesante classe 5 con cilindrata, peso e altezza.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     * @param weight Peso veicolo <b>(double)</b>
     */
    public HV5(String brand,
        String model,
        String VLI,
        int cc,
        double height,
        double weight)

    {
        super(brand,model,VLI,cc,height,weight);


    }
}
