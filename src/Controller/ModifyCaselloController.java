package Controller;

import Model.Highway;
import Model.TollBoth;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ModifyCaselloController implements Initializable {

    @FXML
    ChoiceBox autostradaList;
    @FXML
    TextField kmInput, nomeInput;
    @FXML
    Button saveButton;

    TollBoth casello;
    ObservableList<String> autostrade;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setto il metodo per la gestione dell'evento click
        saveButton.setOnAction(this::salva);


        // Impongo al textField per i KM di accettare solo numeri in input
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        kmInput.setTextFormatter(formatter);


    }


    private void salva(ActionEvent e){
        //prendo in input i dati
        String autostrada = (String) autostradaList.getValue();
        String nome = nomeInput.getText();
        double km = Double.parseDouble(kmInput.getText());
        //Aggiungo al database
        DBManager.setTollbothHigway(casello.getName(), autostrada);
        DBManager.setTollbothKM(casello.getName(), km);
        DBManager.setTollbothName(casello.getName(), nome);
        //Chiudo il modale
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void setTb(TollBoth casello, ObservableList<String> autostrade){
        System.out.println("Pare che sta dentro");
        this.casello = casello;
        this.autostrade = autostrade;
        autostradaList.setItems(autostrade);
        System.out.println(casello.getName());
        //setto i valori del casello
        autostradaList.setValue(casello.getAutostrada());
        nomeInput.setText(casello.getName());
        kmInput.setText(Double.toString(casello.getKm()));
    }

}
