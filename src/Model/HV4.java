package Model;

/**
 * Class for Heavy Vehicles class 4
 *
 **/

public class HV4 extends HVB {
    private static final int axes = 4;
    private static final char vclass = '4';

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
     * @param EURO Classe di inquinamento <b>int</>
     */
    public HV4(      String brand,
              String model,
              String VLI,
              int EURO
    )

    {
        super(brand,model,VLI,EURO);

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
     * @param EURO Classe di inquinamento <b>int</>
     */
    public HV4(String brand,
        String model,
        String VLI,
        int cc,
        double height,
        int EURO
    )

    {
        super(brand,model,VLI,cc,height,EURO);

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
     * @param EURO Classe di inquinamento <b>int</>
     */
    public HV4(String brand,
        String model,
        String VLI,
        int cc,
        double height,
        double weight,
        int EURO
    )

    {
        super(brand,model,VLI,cc,height,weight,EURO);


    }


}

