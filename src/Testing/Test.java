package Testing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        primaryStage.setTitle("Hello World");
        StackPane layout = new StackPane();

        Label label1 = new Label("Inserire Targa Veicolo:");
        TextField textField = new TextField ();
        HBox hb = new HBox();
        Button b = new Button();
        b.setText("Calcola Pedaggio");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String targa = textField.getText();
                System.out.println(targa);
                Vehicle macchina = new Vehicle(targa);
                TollBoth startingTB =  new TollBoth(Tools.fileReader("ticket.txt"));
                TollBoth endingTB = new TollBoth("RM150");
                Highway autostrada = new Highway(DBManager.getHighwayByTollbooth(startingTB.getName()));
                double prezzo = TollCalculator.getToll(macchina, autostrada, startingTB, endingTB);
                System.out.println("Pedaggio: "+prezzo+" Euro");
            }
        });


        VBox vb = new VBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);
        vb.getChildren().addAll(hb, b);
        vb.setSpacing(10);
        layout.getChildren().add(vb);

        primaryStage.setScene(new Scene(layout, 600, 400));
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
        //Test3
        launch(args);
    }
}
