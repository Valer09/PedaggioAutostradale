package Controller;

import Model.Highway;
import Model.TollBoth;
import Model.User;
import Model.Imposte;
import javafx.beans.Observable;
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
    ListView caselliList, autostradeList, listUser;
    @FXML
    Button backButton ,addAutostrada, deleteAutostrada, modifyAutostrada, addCasello, modifyCasello, deleteCasello, addUt, deleteUt, modifyUt;
    @FXML
    TableColumn <Imposte, String>key;
    @FXML
    TableColumn <Imposte, Double>value;
    @FXML
    TableView <Imposte> classesTable;
    ObservableList <Imposte> imposte;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnAction(this::goBack);

        //Caselli
        addCasello.setOnAction(this::aggiungiCasello);
        deleteCasello.setOnAction(this::rimuoviCasello);
        modifyCasello.setOnAction(this::modificaCasello);
        ArrayList<TollBoth> caselli = DBManager.getTollBoths();
        ObservableList<String> list = FXCollections.observableArrayList();
        caselli.forEach(casello -> {
            list.add(casello.getName());
        });
        caselliList.setItems(list);

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
        addAutostrada.setOnAction(this::aggiungiAutostrada);

        addUt.setOnAction(this::aggiungiUtente);
        modifyUt.setOnAction(this::modificaUtente);
        ObservableList data = FXCollections.observableArrayList();
        ArrayList <Highway> highways = DBManager.getHighways();
        deleteAutostrada.setOnAction(this::rimuoviAutostrada);
        modifyAutostrada.setOnAction(this::modificaAutostrada);

        highways.forEach(autostrada -> {
            data.add(autostrada.getName());
        });
        autostradeList.setItems(data);

        createUserList();
        deleteUt.setOnAction(this::rimuoviUtente);
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
        stage.setOnHiding((WindowEvent event1) -> {
            System.out.println("Chiuso");
            this.refreshAutostrade();
        });
    }
    private void refreshAutostrade(){
        ArrayList<Highway> highways = DBManager.getHighways();
        ObservableList<String> data = FXCollections.observableArrayList();
        highways.forEach(autostrada -> {
            data.add(autostrada.getName());
        });
        autostradeList.setItems(data);
    }
    private void rimuoviAutostrada(ActionEvent e){
        String autostrada = (String) autostradeList.getSelectionModel().getSelectedItem();
        DBManager.delHighway(autostrada);
        this.refreshAutostrade();
    }
    private void modificaAutostrada(ActionEvent e){
        String autostrada = (String) autostradeList.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/modifyAutostrada.fxml"));
        try {
            if (loader == null) System.out.println("Vuoto");
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Modifica Autostrada");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        ModifyAutostradaController controller = loader.getController();
        controller.setHWname(autostrada);
        stage.show();
        stage.setOnHiding((WindowEvent event1) -> {
            System.out.println("Chiuso");
            this.refreshAutostrade();
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
    private void aggiungiUtente(ActionEvent e){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    AddUtentiModalController.class.getResource("../View/modaleUtenti.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Utente");
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

    private void rimuoviCasello(ActionEvent e){
        String casello = (String) caselliList.getSelectionModel().getSelectedItem();
        DBManager.delTollboth(casello);
        System.out.println("Eliminato il casello: "+casello);
        this.refreshCaselli();
    }

    private void modificaCasello(ActionEvent e){
        //ottengo il nome del casello dalla selezione
        TollBoth casello = DBManager.getTollBoth((String) caselliList.getSelectionModel().getSelectedItem());
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
        controller.setTb(casello);
        stage.setScene(new Scene(root));
        //mostro il modale
        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshCaselli();
        });
    }
    public void createUserList(){
        System.out.println("Refresh");
        ArrayList <String> utenti = DBManager.userList();
        ObservableList<String> item = FXCollections.observableArrayList();
        utenti.forEach(utente ->{
            item.add(utente);
        });
        listUser.setItems(item);
    }

    public void rimuoviUtente(ActionEvent e){
        String utente = (String) listUser.getSelectionModel().getSelectedItem();
        DBManager.delUser(utente);
        System.out.println("Utente eliminato: "+utente);
        createUserList();

    }
    private void modificaUtente(ActionEvent e){
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/modifyUtenti.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Modifica Utente");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        ModifyUtentiController controller = loader.getController();
        String utente = (String) listUser.getSelectionModel().getSelectedItem();
        controller.setUser(utente);
        stage.show();
        stage.setOnHiding((WindowEvent event1) -> {
            System.out.println("Chiuso");
            createUserList();
        });
    }

    private void goBack(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/home.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
    }
}
