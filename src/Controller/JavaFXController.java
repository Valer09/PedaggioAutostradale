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
    Label pedaggio;

    private void calcolaPedaggio(ActionEvent event){
        String targa = inputText.getText();
        Vehicle macchina = new Vehicle(targa);
        TollBoth startingTB =  new TollBoth(Tools.fileReader("ticket.txt"));
        TollBoth endingTB = new TollBoth("RM150");
        Highway autostrada = new Highway(DBManager.getHighwayByTollbooth(startingTB.getName()));
        double prezzo = TollCalculator.getToll(macchina, autostrada, startingTB, endingTB);
        pedaggio.setText(prezzo+"€");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submit.setOnAction(this::calcolaPedaggio);
    }
}
