package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAutostradeModalController implements Initializable {
    @FXML
    TextField nomeInput,TU;
    @FXML
    Button addButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addButton.setOnAction(this::aggiungi);

        TU.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    TU.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void aggiungi(ActionEvent actionEvent) {
        String nome = nomeInput.getText();
        double tu = Double.parseDouble(TU.getText());
        //Aggiungo al database
        DBManager.addHighway(nome, tu);
        //Chiudo il modale
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

}
