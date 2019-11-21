package Testing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controller.*;

/**
 *
 * @author Vale09
 * @author Jacic
 * @author Mattu
 * @author Bob
 *
 */
public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        boolean gestionale = true;
        if(gestionale){
            Parent root = FXMLLoader.load(getClass().getResource("../View/home.fxml"));
            primaryStage.setTitle("Pedaggio Autostradale");
            primaryStage.setScene(new Scene(root, 300, 350));
            primaryStage.show();
        }
        else {

        }

    }

    public static void main(String []args){

        //OBBLIGATORIO PER INIZIALIZZARE DB E COSTANTI
        DBManager.initializeConnection();

        //TESTING VEHICLES AND HIGHWAY AND TOLL
        /*System.out.println("Autostrada A24");
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


        String autostrada = DBManager.getHighwayByTollbooth("AQ50");
        System.out.println(autostrada); */

        //Highway.addTollboth("A24","CACCA",600);
        //Highway.addHighway("A1000",0.33);
        //Highway.setTU("A24",0.93);
        //Highway.setName("A244","A24");
        //Highway.delHighway("A1000");
        //TollBoth.setName("TE00","TE0");
        //TollBoth.setKM("TE0",0);
        //TollBoth.delTollBooth("TE0");
        //test13

        //USER TESTED
        //Tools.login();

        launch(args);







    }
}
