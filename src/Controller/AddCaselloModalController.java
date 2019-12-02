package Controller;

import Model.Highway;
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

public class AddCaselloModalController implements Initializable {
    @FXML
    ChoiceBox autostradaList;
    @FXML
    TextField kmInput, nomeInput;
    @FXML
    Button addButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Ottengo la lista delle autostrade dal DB e l'assegno alla List View
        ArrayList<Highway> autostrade = DBManager.getHighways();
        ObservableList<String> list = FXCollections.observableArrayList();
        autostrade.forEach(autostrada -> {
            list.add(autostrada.getName());
        });
        autostradaList.setItems(list);
        //Assegno il metodo aggiungi al bottone
        addButton.setOnAction(this::aggiungi);
        // Impongo al textField per i KM di accettare solo numeri in input
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        kmInput.setTextFormatter(formatter);

    }

    /**
     * Metodo che al click del pulsante Aggiungi prende i valori dei due TextField e della ChoiceBox e li passa come parametri al metodo addTollBoth.
     * Il metodo addTollBoth aggiunge sul DB un nuovo casello.
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void aggiungi(ActionEvent e){
        //prendo in input i dati
        String autostrada = (String) autostradaList.getValue();
        String nome = nomeInput.getText();
        double km = Double.parseDouble(kmInput.getText());
        //Aggiungo al database
        DBManager.addTollboth(autostrada, nome, km);
        //Chiudo il modale
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

}
