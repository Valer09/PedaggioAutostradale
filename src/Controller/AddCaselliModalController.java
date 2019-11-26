package Controller;

import Model.Highway;
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
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCaselliModalController implements Initializable {
    @FXML
    ChoiceBox autostradaList;
    @FXML
    TextField kmInput, nomeInput;
    @FXML
    Button addButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Highway> autostrade = DBManager.getHighways();
        ObservableList<String> list = FXCollections.observableArrayList();
        autostrade.forEach(autostrada -> {
            list.add(autostrada.getName());
        });
        autostradaList.setItems(list);
        addButton.setOnAction(this::aggiungi);
        // force the field to be numeric only
        kmInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    kmInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

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
