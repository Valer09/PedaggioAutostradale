package Controller;

import Model.Highway;
import Model.TollBoth;
import Model.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXController implements Initializable {
    @FXML
    TextField inputText;
    @FXML
    Button submit;
    @FXML
    Label pedaggio, targaValue, modelloValue, marcaValue, pesoValue, altezzaValue, cilindrataValue, annoValue, assiValue, ctValue, caValue;
    @FXML
    Label autostradaValue, startingTBValue, endingTBValue, routeValue;
    @FXML
    TitledPane details;

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submit.setOnAction(this::calcolaPedaggio);
    }

    private void calcolaPedaggio(ActionEvent event){
        String targa = inputText.getText();
        Vehicle macchina = new Vehicle(targa);
        TollBoth startingTB =  new TollBoth(Tools.fileReader("ticket.txt"));
        TollBoth endingTB = new TollBoth("RM150");
        Highway autostrada = new Highway(DBManager.getHighwayByTollbooth(startingTB.getName()));
        double prezzo = TollCalculator.getToll(macchina, autostrada, startingTB, endingTB);
        setVehicleValues(macchina);
        setRouteValues(autostrada, startingTB, endingTB);
        details.setVisible(true);
        pedaggio.setText(prezzo+"€");

    }

    public void setVehicleValues(Vehicle macchina){
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

    public void setRouteValues(Highway autostrada, TollBoth startingTB, TollBoth endingTB){
        autostradaValue.setText(autostrada.getName());
        startingTBValue.setText(startingTB.getName());
        endingTBValue.setText(endingTB.getName());
        double route = TollCalculator.getRoute(startingTB.getKm(), endingTB.getKm());
        String tmp = Double.toString(route);
        routeValue.setText(tmp+" km");
    }
}
