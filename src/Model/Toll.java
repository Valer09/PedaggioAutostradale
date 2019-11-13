package Model;
import Model.Interfaces.*;
import Controller.*;
import org.jetbrains.annotations.*;



/**
 * This class rapresents unitary toll, calculated basing on Highway TU and  vehicle class
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
    public Toll(@NotNull Highway highway, @NotNull IVehicle vehicle){

        vclass=vehicle.getVclass();
        TU=highway.getTU();

    }

    /**
     *  calculateTool calculates unitary toll from both TU and surcharge applied to vehicle class.
     *  So it get surgcharge through TaxtGetter controller, which interacts to DB
     * @return <b>double</>: unitary tool
     */
    public double calculateToll(){

        return TU+(TU*VarGetter.getSurcharge(vclass));


    }
}
