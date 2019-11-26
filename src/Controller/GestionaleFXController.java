package Controller;

import Model.Highway;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionaleFXController implements Initializable {

    @FXML
    ListView lista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList <Highway> highways = DBManager.getHighways();
        System.out.println(highways.get(1).toString());

    }
}
