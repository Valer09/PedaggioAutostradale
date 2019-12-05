package Controller.View_Controller;

import Controller.DB_Controller.DBManager;
import Controller.TollCalculator;
import Controller.Tools;
import Model.Highway;
import Model.Vehicle;
import Model.TollBoth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import Exception.WrongLPException;

public class OperatoreFxController implements Initializable {
    private File ticket;
    @FXML
    TextField inputText, filePathValue;
    @FXML
    Button submit, sfoglia, backButton;
    @FXML
    Label pedaggio, targaValue, modelloValue, marcaValue, pesoValue, altezzaValue, cilindrataValue, annoValue, assiValue, ctValue, caValue;
    @FXML
    Label autostradaValue, startingTBValue, endingTBValue, routeValue;
    @FXML
    TitledPane details;
    @FXML
    ChoiceBox tbSelect;
    @FXML
    HBox caselloSelect;

    /**
     * Nei seguenti bottoni vengono impostati tre metodi. I quali servono rispettivamenete per calcolare il pedaggio autostradale,
     * la selezione di un file "txt" che simula il biglietto di entrata al casello di uscita ed
     * "goBack" è il metodo che permette di tornare alla view precedente
     * @param url - url
     * @param resourceBundle - local specific object
     */
        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            submit.setOnAction(this::calcolaPedaggio);
            sfoglia.setOnAction(this::scegliFile);
            backButton.setOnAction(this::goBack);
    }

    /**
     * Metodo che si occupa del Calcolo del Pedaggio. Legge il file ticket e il casello di uscita selezionato. Dopo aver parsato dal formato JSON il contenuto del ticket
     * ne estrae la targa del veicolo e il nome del casello di entrata. Dopo aver ottenuto questi dati chiama il metodo getToll del Controller TollCalculator che si occupa del calcolo del pedaggio.
     * Infine chiama i metodi setVehicleValues e setRouteValues per visualizzare i dettagli del veicolo e del percorso.
     * @param event Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void calcolaPedaggio(ActionEvent event){
            if (this.ticket != null){
                String fileContent = Tools.fileReader(this.ticket.getPath());
                JSONObject obj = new JSONObject(fileContent.toString());
                String targa = obj.getString("vehicle_vlp");
                try {
                    Tools.LPCheck(targa);
                } catch (WrongLPException e) {
                    return;
                }
                Vehicle macchina = new Vehicle(targa);
                String startingTBName = obj.getString("startingTB");
                TollBoth startingTB = DBManager.getTollBoth(startingTBName);
                if (tbSelect.getValue() == null){
                    System.out.println("Nessun casello di uscita selezionato");
                    return;
                }
                else{
                    TollBoth endingTB = DBManager.getTollBoth((String) tbSelect.getValue());
                    Highway autostrada = DBManager.getHighway(startingTB.getAutostrada());
                    double prezzo = TollCalculator.getToll(macchina, autostrada, startingTB, endingTB);
                    setVehicleValues(macchina);
                    setRouteValues(autostrada, startingTB, endingTB);
                    details.setVisible(true);
                    pedaggio.setText(prezzo+"€");
                }
            }
            else{
                System.out.println("Nessun file selezionato");
                return;
            }

    }

    /**
     * Metodo che si occupa di stampare i dettagli del veicolo nella sezione Dettagli.
     * Visualizza la targa del veicolo, il modello, la marca, il peso, l'altezza, la cilindrata, l'anno, il numero di assi, la classe tariffaria e la classe ambientale
     * @param macchina Parametro di tipo Veichle che rapresenta il veicolo
     */
    private void setVehicleValues(Vehicle macchina){
            targaValue.setText(macchina.getVlp());
            modelloValue.setText(macchina.getModel());
            marcaValue.setText(macchina.getBrand());
            pesoValue.setText(macchina.getWeight().toString()+" Kg");
            altezzaValue.setText(macchina.getHeight().toString()+" m");
            String tmp = Integer.toString(macchina.getCylinder_capacity());
            cilindrataValue.setText(tmp+" cc");
            tmp = Integer.toString(macchina.getYear());
            annoValue.setText(tmp);
            tmp = Integer.toString(macchina.getAxes());
            assiValue.setText(tmp);
            ctValue.setText(macchina.getTariff_class());
            caValue.setText(macchina.getAmbiental_class());
    }

    /**
     * Metodo che si occupa di stampare i dettagli del precorso nella sezione Dettagli.
     * Visualizza il nome dell'autostrada, il casello di entrata, il casello di uscita e il numero di km percorsi
     * @param autostrada Parametro di tipo Highway che rappresenta l'autostrada
     * @param startingTB Parametro di tipo TollBoth che rappresenta il casello di entrata
     * @param endingTB Parametro di tipo TollBoth che rappresenta il casello di uscita
     */
    private void setRouteValues(Highway autostrada, TollBoth startingTB, TollBoth endingTB){
        autostradaValue.setText(autostrada.getName());
        startingTBValue.setText(startingTB.getName());
        endingTBValue.setText(endingTB.getName());
        double route = TollCalculator.getRoute(startingTB.getKm(), endingTB.getKm());
        String tmp = Double.toString(route);
        routeValue.setText(tmp+" km");
    }

    /**
     * Questo metodo si occupa di aprire la finestra di Dialogo per la selezione del File. Successivamente legge il contenuto del file, estrae il nome del casello di entrata,
     * trova l'autostrada associata a quel casello e successivamente trova la lista dei caselli di quell'autostrada. Infine imposta la ChoiceBox con quest'ultima lista
     * @param event Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void scegliFile(ActionEvent event){
        Stage stage = (Stage) sfoglia.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        this.ticket = fileChooser.showOpenDialog(stage);
        System.out.println(this.ticket.getPath());
        filePathValue.setText(this.ticket.getPath());
        System.out.println(this.ticket);

        String fileContent = Tools.fileReader(this.ticket.getPath());
        JSONObject obj = new JSONObject(fileContent.toString());
        String startingTBname = obj.getString("startingTB");
        TollBoth casello = DBManager.getTollBoth(startingTBname);
        Highway autostrada = DBManager.getHighway(casello.getAutostrada());
        HashMap <String, Double> tollboths = autostrada.getTollbooths();
        ObservableList<String> list = FXCollections.observableArrayList();
        tollboths.forEach((k,v) ->{
            if (!k.equals(startingTBname)){
                list.add(k);
            }
        });
        tbSelect.setItems(list);
        caselloSelect.setVisible(true);
    }

    /**
     * Viene caricata la schermata home, attraverso il bottone "backButton"
     * @param event Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void goBack(ActionEvent event ){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/home.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
    }
}
