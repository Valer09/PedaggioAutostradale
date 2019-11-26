package Controller;

import Model.Highway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionaleFXController implements Initializable {

    @FXML
    ListView lista;

    public static final ObservableList data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList <Highway> highways = DBManager.getHighways();
        System.out.println(highways.get(1).getName());
        System.out.println(highways.get(1).getTollbooths());
        System.out.println(highways.get(1).getTU());
        highways.forEach(autostrada->{
            data.add(autostrada.getName());
        });
        lista.setItems(data);

    }
}
