package Testing;
import Controller.*;
import Model.*;

import java.util.HashMap;

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

        //OBBLIGATORIO PER INIZIALIZZARE DB E COSTANTI
        DBManager.initializeConnection();

        /*
        //TESTING CONSTANTS AND INITIALIZATION
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
        System.out.println(VarGetter.getSurcharge('3')); //TEST GETSURCHARGE METHOD
        System.out.println(VarGetter.getIVA());                //TEST GETIVA METHOD
        System.out.println(VarGetter.getTU("A24"));    //TEST GETTU METHOD
        System.out.println(VarGetter.getTU("A1"));     //TEST GETTU METHOD
        */
        //TESTING VEHICLES AND HIGHWAY AND TOLL
        System.out.println("Autostrada A24");
        Highway a24= new Highway("A24");
        System.out.println("Tariffa Unitaria: "+a24.getTU());
        HashMap caselli = a24.getTollbooths();
        System.out.println("Caselli: "+caselli);
        System.out.println("Nuovo Veicolo");
        Vehicle macchina = new Vehicle("EV467YT");
        System.out.println("Modello: "+macchina.getModel());
        System.out.println("Marca: "+macchina.getBrand());
        System.out.println("Anno: "+macchina.getYear());
        System.out.println("Assi: "+macchina.getAxes());
        System.out.println("Cilindrata: "+macchina.getCylinder_capacity());
        System.out.println("Peso: "+macchina.getWeight());
        System.out.println("Altezza: "+macchina.getHeight());
        System.out.println("Classe Tariffaria: "+macchina.getTariff_class());
        System.out.println("Classe Ambientale: "+macchina.getAmbiental_class());

        double prezzo = TollCalculator.getToll(macchina,a24,"TE0","ticket.txt");
        System.out.println("Pedaggio: "+prezzo+" Euro");


        //HV4 camion= new HV4("IVECO","HHH77", "CR8776TT",2);
/*      Toll a24toll= new Toll(a24,camion);
        System.out.println(a24toll.calculateToll());*/
        //HVB ibiza= new HVB("SEAT","IBIZA", "CR984RT",4);
/*
        a24toll=new Toll(a24,ibiza);
        System.out.println(a24toll.calculateToll());


*/
        /*
        //TESTING TOOLS
        System.out.println(Tools.LPCheck("CR8776TT")); //FALSE
        System.out.println(Tools.LPCheck("CR984RT"));  //TRUE. DA COMPLETARE

        //TESTING tollbooths in HIGHWAYS
        System.out.println(a24.getTollbooths());
        System.out.println(Tools.fileReader("ticket.txt"));

        //TESTING TOLL CALCULATOR
        TollCalculator tl= new TollCalculator(camion,a24,"RM150","ticket.txt");
        System.out.println(tl.getAmount());

        tl= new TollCalculator(ibiza,a24,"RM150","ticket.txt");
        System.out.println(tl.getAmount());

        tl= new TollCalculator(ibiza,a24,"TE0","ticket.txt");
        System.out.println(tl.getAmount());

        tl= new TollCalculator(ibiza,a24,"TE0","ticket.txt");
        System.out.println(tl.getAmount());
        */

        //Highway.addTollboth("A24","CACCA",600);
        //Highway.addHighway("A1000",0.33);
        //Highway.setTU("A24",0.93);
        //Highway.setName("A244","A24");
        //Highway.delHighway("A1000");
        //TollBoth.setName("TE00","TE0");
        //TollBoth.setKM("TE0",0);
        TollBoth.delTollBooth("TE0");















    }
}
