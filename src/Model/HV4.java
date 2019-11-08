package Model;

/**
 * Class for Heavy Vehicles class 4.
 *
 **/

public class HV4 extends HVB {
    private static final int axes = 4;
    private static final char vclass = '4';
    //private static final double limit = 1.31;
    //protected double height
    //protected static final String type = "HEAVY";
    //protected double weight=3100;
    //protected int cc=2000,year=1900;
    //protected String model,brand,VLI;;


    @Override
    public char getVclass() {
        return vclass;
    }

    @Override
    public int getAxes() {
        return axes;
    }


    /**
     * Costruttore classe HV3.
     * Inizializzazione dei dati del veicolo pesante classe 3.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     */
    HV4(      String model,
              String brand,
              String VLI
    )

    {
        super(model,brand,VLI);

    }

    /**
     * Costruttore classe HV4.
     * Inizializzazione dei dati del pesante classe 4 con cilindrata e peso.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     */
    HV4(String model,
        String brand,
        String VLI,
        int cc,
        double height
    )

    {
        super(model,brand,VLI,cc,height);

    }


    /**
     * Costruttore classe HV4.
     * Inizializzazione dei dati del veicolo pesante classe 4 con cilindrata, peso e altezza.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     * @param weight Peso veicolo <b>(double)</b>
     */
    HV4(String model,
        String brand,
        String VLI,
        int cc,
        double height,
        double weight)

    {
        super(model,brand,VLI,cc,height,weight);


    }


}

