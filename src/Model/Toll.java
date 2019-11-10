package Model;
import Controller.*;

import java.awt.desktop.SystemEventListener;

/**
 * This class calculates unitary toll, based on Highway TU and  vehicle class
 */
public class Toll {

    private double unit_toll=0;
    private double TU;
    private char vclass;


    /**
     * Constructor
     * @param highway
     * @param vehicle
     */
    public Toll(Highway highway, IVehicle vehicle){

        vclass=vehicle.getVclass();
        TU=highway.getTU();

    }

    /**
     *  calculateTool calculates unitary toll from TU and surcharge applied to vehicle class.
     *  So it get surgcharge through TaxtGetter controller, which interacts to DB
     * @return <b>double</>: unitary tool
     */
    public double calculateToll(){
        VarGetter vg = new VarGetter();


        return TU+vg.getSurcharge(vclass);


    }
}
