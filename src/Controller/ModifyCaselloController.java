package Controller;

import Model.Highway;
import Model.TollBoth;
import javafx.beans.value.ChangeListener;
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

    String tbName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setto il metodo per la gestione dell'evento click
        saveButton.setOnAction(this::salva);
        //ottengo la lista delle autostrade e le inserisco nella choiceBox
        ArrayList<Highway> autostrade = DBManager.getHighways();
        ObservableList<String> list = FXCollections.observableArrayList();
        autostrade.forEach(autostrada -> {
            list.add(autostrada.getName());
        });
        autostradaList.setItems(list);

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
        DBManager.setTollbothHigway(tbName, autostrada);
        DBManager.setTollbothKM(tbName, km);
        DBManager.setTollbothName(tbName, nome);
        //Chiudo il modale
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void setTbName(String name){
        System.out.println("Pare che sta dentro");
        this.tbName = name;
        //instanzio un nuovo oggetto di tipo casello
        TollBoth casello = new TollBoth(tbName);
        System.out.println(tbName);
        //setto i valori del casello
        autostradaList.setValue(DBManager.getHighwayByTollbooth(casello.getName()));
        nomeInput.setText(casello.getName());
        kmInput.setText(Double.toString(casello.getKm()));
    }

}
