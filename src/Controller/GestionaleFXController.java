package Controller;

import Model.Highway;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionaleFXController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList <Highway> highways = DBManager.getHighways();
        System.out.println(highways);

    }
}
