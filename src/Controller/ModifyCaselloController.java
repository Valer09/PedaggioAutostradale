package Controller;

import Model.TollBoth;
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
    /**
     * Questo Metodo viene chiamato all'inizio della creazione della finestra. Imposta l'azione
     */
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

    /**
     * Questo metodo prende i valori dei due TextField e della ChoiceBox ed effettua un update dei vari campi del casello
     * ed infine
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
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

    /**
     * Questo metodo prende in input un oggetto di tipo casello e un ObservableList di Stringhe che contiene la lista dei nomi delle autostrade.
     * Assegna i parametri in input alle variabili della classe, assegna la lista passata in input alla ListView
     * e infine popola i TextField con il nome del casello, il KM e inposta l'autostrada associata al casello come valore selezionato nella ChoiceBox
     * @param casello Parametro di tipo TollBoth che contiene l'istanza di un casello
     * @param autostrade Parametro di tipo ObservableList di tipo String che contiene tutti i nomi delle autostrade
     */
    public void setTb(TollBoth casello, ObservableList<String> autostrade){
        this.casello = casello;
        this.autostrade = autostrade;
        autostradaList.setItems(autostrade);
        //setto i valori del casello
        autostradaList.setValue(casello.getAutostrada());
        nomeInput.setText(casello.getName());
        kmInput.setText(Double.toString(casello.getKm()));
    }

}
