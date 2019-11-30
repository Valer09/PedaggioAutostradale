package Controller;

import Model.Highway;
import Model.TollBoth;
import Model.User;
import Model.Imposte;
import javafx.beans.Observable;
import javafx.beans.property.Property;
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
    Button backButton ,addAutostrada, deleteAutostrada, modifyAutostrada, addCasello, modifyCasello, deleteCasello, addUt, deleteUt, modifyUt,editImporti,eraseButton,nuovoImporto;
    @FXML
    TableColumn <Imposte, String>key;
    @FXML
    TableColumn <Imposte, Double>value;
    @FXML
    TableView <Imposte> classesTable;
    ObservableList <Imposte> imposte;
    ObservableList <String> autostradeLista, caselliLista, utentiLista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnAction(this::goBack);

        //Caselli
        addCasello.setOnAction(this::aggiungiCasello);
        deleteCasello.setOnAction(this::rimuoviCasello);
        modifyCasello.setOnAction(this::modificaCasello);
        ArrayList<TollBoth> caselli = DBManager.getTollBoths();
        caselliLista = FXCollections.observableArrayList();
        caselli.forEach(casello -> {
            caselliLista.add(casello.getName());
        });
        caselliList.setItems(caselliLista);

        //Classi e imposte
        eraseButton.setOnAction(this::eliminaImporto);
        editImporti.setOnAction(this::modificaImposta);
        nuovoImporto.setOnAction(this::creaImposta);
        refreshImposte();

        //Autostrade
        addAutostrada.setOnAction(this::aggiungiAutostrada);
        deleteAutostrada.setOnAction(this::rimuoviAutostrada);
        modifyAutostrada.setOnAction(this::modificaAutostrada);
        autostradeLista = FXCollections.observableArrayList();
        ArrayList <Highway> highways = DBManager.getHighways();
        highways.forEach(autostrada -> {
            autostradeLista.add(autostrada.getName());
        });
        autostradeList.setItems(autostradeLista);

        //Utenti
        addUt.setOnAction(this::aggiungiUtente);
        modifyUt.setOnAction(this::modificaUtente);
        deleteUt.setOnAction(this::rimuoviUtente);
        createUserList();
    }

    private void eliminaImporto(ActionEvent actionEvent) {
        String importo =  classesTable.getSelectionModel().getSelectedItem().getNomeImposta();
        DBManager.delImposta(importo);
        refreshImposte();
    }

    private void modificaImposta(ActionEvent actionEvent) {
        String nomeimporto;
        Double valoreimporto ;
        Imposte selected = classesTable.getSelectionModel().getSelectedItem();
        nomeimporto=selected.getNomeImposta();
        valoreimporto=selected.getValoreImposta();
        System.out.println(nomeimporto+ " "+ valoreimporto );

        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/modifyImposte.fxml"));
        try {
            root = loader.load();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ModifyImposteFXController controller = loader.<ModifyImposteFXController>getController();
        controller.setReason(false);
        controller.setFields(nomeimporto,valoreimporto);

        stage.setScene(new Scene(root));
        stage.setTitle("Crea Nuovo Imposta");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) actionEvent.getSource()).getScene().getWindow() );

        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshImposte();
        });


    }
    private void creaImposta(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/modifyImposte.fxml"));
        try {
            root = loader.load();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ModifyImposteFXController controller = loader.<ModifyImposteFXController>getController();
        controller.setReason(true);
        System.out.println(controller.creating+"1");
        stage.setScene(new Scene(root));
        stage.setTitle("Crea Nuovo Imposta");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) actionEvent.getSource()).getScene().getWindow() );

        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshImposte();
        });

    }

    private void refreshImposte(){
        imposte  = FXCollections.observableArrayList();
        HashMap<String, Double> classes = DBManager.getClasses();
        classes.forEach((K,V) -> {
            imposte.add(new Imposte(K,V));
        });
        key.setCellValueFactory(cellData ->  cellData.getValue().getNomeImpostaProperty());
        value.setCellValueFactory(cellData ->  cellData.getValue().getvaloreImpostaProperty().asObject());
        classesTable.setItems(imposte);
        classesTable.getSortOrder().add(key);
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
        controller.setTb(casello, autostradeLista);
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
        utentiLista = FXCollections.observableArrayList();
        utenti.forEach(utente ->{
            utentiLista.add(utente);
        });
        listUser.setItems(utentiLista);
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
