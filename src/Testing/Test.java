package Testing;
import Controller.DBManager;
import Controller.Tools;
import Controller.VarGetter;
import Model.*;

import java.sql.*;

/**
 *
 * @author Vale09
 * @author Jacic
 * @author Mattu
 * @author Bob
 *
 */
public class Test {
    public static void main(String []args){
        Constant.setParams();


//TESTING CONSTANTS INITIALIZATION
        System.out.println(Constant.getVar("IVA"));
        System.out.println(Constant.getVar("SURA"));
        System.out.println(Constant.getVar("SURB"));
        System.out.println(Constant.getVar("SUR3"));
        System.out.println(Constant.getVar("SUR4"));
        System.out.println(Constant.getVar("SUR5"));
        System.out.println(Constant.getVar("EUR1"));
        System.out.println(Constant.getVar("EUR2"));
        System.out.println(Constant.getVar("EUR3"));
        System.out.println(Constant.getVar("EUR4"));
        System.out.println(Constant.getVar("EUR5"));
        System.out.println(Constant.getVar("EUR6"));

//TESTING VARGETTER CLASS AND METHODS
        System.out.println(VarGetter.getSurcharge('3')); //TEST GESURCHARGE METHOD
        System.out.println(VarGetter.getIVA()); //TEST GETIVA METHOD
        System.out.println(VarGetter.getTU("A24")); //TEST GETTU METHOD
        System.out.println(VarGetter.getTU("A1")); //TEST GETTU METHOD

//TESTING VEHICLES AND HIGHWAY AND TOLL
        Highway a24= new Highway("A24");
        HV4 camion= new HV4("IVECO","HHH77", "CR8776TT");
        Toll a24toll= new Toll(a24,camion);
        System.out.println(a24toll.calculateToll());

        HVB ibiza= new HVB("SEAT","IBIZA", "CR984RT");
        a24toll=new Toll(a24,ibiza);
        System.out.println(a24toll.calculateToll());

//TESTING TOOLS
        System.out.println(Tools.LPCheck("CR8776TT")); //FALSE
        System.out.println(Tools.LPCheck("CR984RT"));  //TRUE. DA COMPLETARE












    }
}
