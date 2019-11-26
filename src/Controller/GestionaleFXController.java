package Controller;

import Model.Highway;
import Model.TollBoth;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionaleFXController implements Initializable {
    User user;
    @FXML
    ListView caselliList;
    @FXML
    Button addAutostrada, deleteAutostrada, modifyAutostrada, addCasello, modifyCasello, deleteCasello;

    @FXML
    ListView autostradeList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Caselli
        addCasello.setOnAction(this::aggiungiCasello);
        deleteCasello.setOnAction(this::rimuoviCasello);
        modifyCasello.setOnAction(this::modificaCasello);
        //Autostrade
        addAutostrada.setOnAction(this::aggiungiAutostrada);

        ObservableList data = FXCollections.observableArrayList();
        ArrayList <Highway> highways = DBManager.getHighways();
        highways.forEach(autostrada -> {
            data.add(autostrada.getName());
        });
        autostradeList.setItems(data);

        System.out.println(highways);
        ArrayList<TollBoth> caselli = DBManager.getTollBoths();
        ObservableList<String> list = FXCollections.observableArrayList();
        caselli.forEach(casello -> {
            list.add(casello.getName());
        });
        caselliList.setItems(list);
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
                    getClass().getResource("../View/addCasello.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Casello");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshCaselli();
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

    private void rimuoviCasello(ActionEvent e){
        String casello = (String) caselliList.getSelectionModel().getSelectedItem();
        DBManager.delTollboth(casello);
        System.out.println("Eliminato il casello: "+casello);
        this.refreshCaselli();
    }

    private void modificaCasello(ActionEvent e){
        //ottengo il nome del casello dalla selezione
        String casello = (String) caselliList.getSelectionModel().getSelectedItem();
        System.out.println(casello);
        //Creo il modale per la modifica del casello
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/modifyCasello.fxml"));
        try {
            if (loader == null) System.out.println("Vuoto");
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle("Modifica Casello "+casello);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        //Passo il nome del casello al modale
        ModifyCaselloController controller = loader.getController();
        controller.setTbName(casello);
        stage.setScene(new Scene(root));
        //mostro il modale
        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshCaselli();
        });
    }
}
