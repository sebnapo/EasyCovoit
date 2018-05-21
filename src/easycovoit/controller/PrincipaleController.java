/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easycovoit.controller;

import easycovoit.model.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author anonymous
 */
public class PrincipaleController implements Initializable {

    @FXML
    TabPane myTabPane;
    @FXML
    TextField villeDepart,villeArrive,heureDepart,minuteDepart;
    @FXML
    DatePicker dateDepart;
    @FXML
    Button findTrajet;
    @FXML
    VBox vboxScrolled;
    private Database db;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myTabPane.setTabMinWidth(160);
        myTabPane.setTabMaxWidth(160);
        try {
            this.db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findTrajet.setOnMouseClicked(event -> {
            if(isInteger(minuteDepart.getText()) && Integer.parseInt(minuteDepart.getText()) >= 0 || Integer.parseInt(minuteDepart.getText()) < 60
                    && isInteger(heureDepart.getText()) && Integer.parseInt(heureDepart.getText()) >= 0 || Integer.parseInt(heureDepart.getText()) <= 24 ){
                // Les dates sont buggées, on fait -1900 car sinon on as + 1900 sur les dates, et on enleve 1 au mois car sinon on arrive au mois suivant
                Timestamp date = new Timestamp(dateDepart.valueProperty().get().getYear() - 1900,dateDepart.valueProperty().get().getMonth().getValue() - 1,dateDepart.valueProperty().get().getDayOfMonth(),Integer.parseInt(heureDepart.getText()),Integer.parseInt(minuteDepart.getText()),0,0);
                ResultSet res = this.db.getAllTrajet(villeDepart.getText(),villeArrive.getText(),date);
                if(res != null){
                    try {
                        // Traitement de toutes les réponses

                        while(res.next()){
                            GridPane gridPane = new GridPane();
                            Label labelNom = new Label("Salut");
                            Label labelVehicule = new Label("VOITUREEEE");
                            Label labelPlace = new Label("2");
                            Label labelDepart = new Label("Départ : Nantes - 00h00");
                            Label labelArrive = new Label("Arrive : Rennes - 01h00");
                            Label labelPrix = new Label("9€");
                            Button btnReserver = new Button("Reserver");

                            GridPane.setHalignment(labelNom, HPos.CENTER);
                            gridPane.add(labelNom,0,0);

                            GridPane.setHalignment(labelVehicule, HPos.CENTER);
                            gridPane.add(labelVehicule,0,1);

                            GridPane.setHalignment(labelPlace, HPos.CENTER);
                            gridPane.add(labelPlace,0,2);

                            GridPane.setHalignment(labelDepart, HPos.CENTER);
                            gridPane.add(labelDepart,1,0);

                            GridPane.setHalignment(labelArrive, HPos.CENTER);
                            gridPane.add(labelArrive,1,1);

                            GridPane.setHalignment(labelPrix, HPos.CENTER);
                            gridPane.add(labelPrix,2,0);

                            GridPane.setHalignment(btnReserver, HPos.CENTER);
                            gridPane.add(btnReserver,2,2);

                            gridPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

                            ColumnConstraints column1 = new ColumnConstraints();
                            column1.setPercentWidth(33);
                            gridPane.getColumnConstraints().add(column1);
                            ColumnConstraints column2 = new ColumnConstraints();
                            column2.setPercentWidth(33);
                            gridPane.getColumnConstraints().add(column2);
                            ColumnConstraints column3 = new ColumnConstraints();
                            column3.setPercentWidth(33);
                            gridPane.getColumnConstraints().add(column3);
                            vboxScrolled.getChildren().add(gridPane);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public boolean isInteger(String test){
        try{
            int num = Integer.parseInt(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
