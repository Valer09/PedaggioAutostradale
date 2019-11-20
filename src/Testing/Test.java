package Testing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Controller.*;
import Model.*;



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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Pedaggio Autostradale");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
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
        launch(args);
    }
}
