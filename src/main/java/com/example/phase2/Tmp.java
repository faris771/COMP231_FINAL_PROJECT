package com.example.phase2;

import javafx.application.Application;

import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;


public class Tmp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
/*        Scene newCustomerScene;
        GridPane deleteCustomerGridPane = new GridPane();
        deleteCustomerGridPane.setStyle("-fx-background-color: #383838");

        deleteCustomerGridPane.setPadding(new Insets(5));
        deleteCustomerGridPane.setVgap(10);
        deleteCustomerGridPane.setHgap(20);

        Label delLabel = new Label("Customer ID:");
        delLabel.setFont(new Font("Roboto",20));
        delLabel.setMinSize(200,100);
        delLabel.setTextFill(Color.WHITE);
        TextField delTextField = new TextField();
        delTextField.setMinSize(200,25);
        // GridPane.setConstraints(delTextField,1,0);



        Label delLabel1 = new Label("Customer Name:");
        delLabel1.setFont(new Font("Roboto",20));
        delLabel1.setMinSize(200,100);
        delLabel1.setTextFill(Color.WHITE);
        TextField delTextField1 = new TextField();
        delTextField1.setMinSize(200,25);

        Label delLabel2 = new Label("Customer Address:");
        delLabel2.setFont(new Font("Roboto",20));
        delLabel2.setMinSize(200,100);
        delLabel2.setTextFill(Color.WHITE);
        TextField delTextField2 = new TextField();
        delTextField2.setMinSize(200,25);

        Label delLabel3 = new Label("Customer Mobile:");
        delLabel3.setFont(new Font("Roboto",20));
        delLabel3.setTextFill(Color.WHITE);
        delLabel3.setMinSize(200,100);
        TextField delTextField3 = new TextField();
        delTextField3.setMinSize(200,25);

        Button finalDelteButton = new Button("Add" );
        finalDelteButton.setMinWidth(300);
        finalDelteButton.setMaxHeight(50);
        finalDelteButton.setGraphic();
        //GridPane.setConstraints(addNewCustomerButton2,0,4);

        Button backButton = new Button("back");
        backButton.setMinWidth(300);
        backButton.setMinHeight(10);
        backButton.setGraphic(returnToMenuView);
        backButton.setOnAction(e -> {
            stage.setScene(customerMenuScene);

        });
        // GridPane.setConstraints(backButton,1,4);

        deleteCustomerGridPane.addRow(0,delLabel,delTextField);
        deleteCustomerGridPane.addRow(1,delLabel1,delTextField1);
        deleteCustomerGridPane.addRow(2,delLabel2,delTextField2);
        deleteCustomerGridPane.addRow(3,delLabel3,delTextField3);

        deleteCustomerGridPane.setAlignment(Pos.CENTER);
        deleteCustomerGridPane.setMinSize(400,200);


        HBox finalDeleteHbox = new HBox(20);
        finalDeleteHbox.setStyle("-fx-background-color: #383838");
        finalDeleteHbox.setAlignment(Pos.CENTER);
        finalDeleteHbox.getChildren().addAll(finalDelteButton,backButton);

        BorderPane finalDeleteCustomerGridPane = new BorderPane();
        finalDeleteCustomerGridPane.setCenter(deleteCustomerGridPane);
        finalDeleteCustomerGridPane.setBottom(finalDeleteHbox);
        newCustomerScene = new Scene(finalDeleteCustomerGridPane);

        */

    }
}
