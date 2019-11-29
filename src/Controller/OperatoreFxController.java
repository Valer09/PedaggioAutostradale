package Controller;

import Model.Highway;
import Model.TollBoth;
import Model.Vehicle;
import javafx.beans.Observable;
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
import java.util.Map;
import java.util.ResourceBundle;

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

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            submit.setOnAction(this::calcolaPedaggio);
            sfoglia.setOnAction(this::scegliFile);
            backButton.setOnAction(this::goBack);
    }

    private void calcolaPedaggio(ActionEvent event){
            if (this.ticket != null){
                String fileContent = Tools.fileReader(this.ticket.getPath());
                JSONObject obj = new JSONObject(fileContent.toString());
                String targa = obj.getString("vehicle_vlp");
                Vehicle macchina = new Vehicle(targa);
                String startingTBName = obj.getString("startingTB");
                TollBoth startingTB = DBManager.getTollBoth(startingTBName);
                if (tbSelect.getValue() == null){
                    System.out.println("Nessun casello di uscita selezionato");
                    return;
                }
                else{
                    TollBoth endingTB = DBManager.getTollBoth((String) tbSelect.getValue());
                    Highway autostrada = new Highway(DBManager.getHighwayByTollbooth(startingTB.getName()));
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

    private void setRouteValues(Highway autostrada, TollBoth startingTB, TollBoth endingTB){
        autostradaValue.setText(autostrada.getName());
        startingTBValue.setText(startingTB.getName());
        endingTBValue.setText(endingTB.getName());
        double route = TollCalculator.getRoute(startingTB.getKm(), endingTB.getKm());
        String tmp = Double.toString(route);
        routeValue.setText(tmp+" km");
    }

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
        Highway autostrada = new Highway (DBManager.getHighwayByTollbooth(startingTBname));
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

    private void goBack(ActionEvent event ){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/home.fxml"));
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
