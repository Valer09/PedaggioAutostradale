package Controller;
import Model.Highway;
import Model.TollBoth;
import Model.User;
import Model.Imposte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GestionaleFXController implements Initializable {
    User user;
    @FXML
    ListView caselliList;
    @FXML
    Button addBtn,deleteBtn, modifyBtn,addButton, modifyButton, deleteButton;
    @FXML
    ListView lista;
    @FXML
    TableColumn <Imposte, String>key;
    @FXML
    TableColumn <Imposte, Double>value;
    @FXML
    TableView <Imposte> classesTable;
    ObservableList <Imposte> imposte;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addButton.setOnAction(this::aggiungiCasello);
        addBtn.setOnAction(this::aggiungiAutostrada);

        //Classi e imposte
        imposte  = FXCollections.observableArrayList();
        HashMap<String, Double> classes = DBManager.getClasses();
        classes.forEach((K,V) -> {
            imposte.add(new Imposte(K,V));
        });
        key.setCellValueFactory(cellData ->  cellData.getValue().getNomeImpostaProperty());
        value.setCellValueFactory(cellData ->  cellData.getValue().getvaloreImpostaProperty().asObject());
        classesTable.setItems(imposte);
        classesTable.getSortOrder().add(key);

        //Autostrade
        ObservableList data = FXCollections.observableArrayList();
        ArrayList <Highway> highways = DBManager.getHighways();
        highways.forEach(autostrada -> {
            data.add(autostrada.getName());
        });
        lista.setItems(data);

        //Caselli
        ArrayList<TollBoth> caselli = DBManager.getTollBoths();
        ObservableList<String> list = FXCollections.observableArrayList();
        caselli.forEach(casello -> {
            list.add(casello.getName());
        });
        caselliList.setItems(list);

    }

    public ObservableList<Imposte> getDatiImposte() {
        return imposte;
    }


    public void setUser(User user){
        this.user=user;
        System.out.println(user.getUsername());
    }

    private void aggiungiAutostrada(ActionEvent e){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    AddAutostradeModalController.class.getResource("../View/modaleAutostrade.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Autostrada");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        stage.show();
        stage.setOnCloseRequest((WindowEvent event1) -> {
            System.out.println("Chiuso");
        });
    }

    private void aggiungiCasello(ActionEvent e){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    AddCaselliModalController.class.getResource("../View/modaleCaselli.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Casello");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        stage.show();
        stage.setOnCloseRequest((WindowEvent event1) -> {
            System.out.println("Chiuso");
        });
    }

    private void refreshCaselli(){
        ArrayList<TollBoth> caselli = DBManager.getTollBoths();
        ObservableList<String> list = FXCollections.observableArrayList();
        caselli.forEach(casello -> {
            list.add(casello.getName());
        });
        caselliList.setItems(list);
    }
}
