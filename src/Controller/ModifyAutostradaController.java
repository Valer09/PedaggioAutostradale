package Controller;

import Model.Highway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ModifyAutostradaController implements Initializable {
    @FXML
    TextField nomeInput,TU;
    @FXML
    Button modButton;

    String highway_name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        modButton.setOnAction(this::modifica);

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        TU.setTextFormatter(formatter);
    }

    public void modifica(ActionEvent e){
        String nome = nomeInput.getText();
        double TarUni= Double.parseDouble(TU.getText());

        DBManager.setTU(highway_name,TarUni);
        DBManager.setHighwayName(highway_name,nome);

        Stage stage = (Stage) modButton.getScene().getWindow();
        stage.close();
    }

    public void setHWname(String name){
        this.highway_name = name;
        Highway highway= DBManager.getHighway(this.highway_name);
        nomeInput.setText(highway.getName());
        TU.setText(Double.toString(highway.getTU()));
    }


}
