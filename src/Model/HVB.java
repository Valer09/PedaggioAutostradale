package Model;

/**
 * Class for Heavy Vehicles class B
 *
 **/
public class HVB extends HV {

    private static final int axes = 2;
    private static final char vclass = 'B';

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
     * @param EURO Classe di inquinamento <b>int</>
     */
    public HVB(  String brand,
                 String model,
                 String VLI,
                 int EURO
                )

    {
        super(brand,model,VLI,EURO);

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
     * @param EURO Classe di inquinamento <b>int</>
     */
    public HVB(String brand,
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
     * Costruttore classe HVB.
     * Inizializzazione dei dati del veicolo pesante classe B con cilindrata, peso e altezza.
     *
     * @param model Modello veicolo <b>(String)</b>
     * @param brand Marca veicolo  <b>(String)</b>
     * @param VLI Targa Veicolo <b>(string)</b>
     * @param cc Cilindrata veicolo <b>(int)</b>
     * @param height Altezza veicolo <b>(double)</b>
     * @param weight Peso veicolo <b>(double)</b>
     * @param EURO Classe di inquinamento <b>int</>
     */
    public HVB(String brand,
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

