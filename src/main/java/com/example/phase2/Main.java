/*
*   Author: Faris Abufarha
*   ID: 1200546
*
*
*   Note: i use Linux, so if u face any problem, put it in consideration please
 */


package com.example.phase2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class Main extends Application {



    static File customerFile = new File("customer.txt");
    static File mediaFile = new File("media.txt");
    static File cartFile = new File("cart.txt");
    static File rentedFile = new File("rented.txt");

    static MediaRentalManager manager = new MediaRentalManager();


    public static void main(String[] args) throws IOException {



        getFromFile();
        launch(args);


    }


    @Override
    public void start(Stage stage) throws Exception {

        Scene mainScene;


        Scene customerMenuScene;
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #383838");

        VBox mainVbox1 = new VBox(65);
        VBox mainVbox2 = new VBox(30);


        Button customerButton = new Button("Customer");
        customerButton.setFont(new Font("Roboto", 16));
        FileInputStream customerFileStream = new FileInputStream("/home/hp/Desktop/phase2Pics/customersIcon.png");
        Image customersIcon = new Image(customerFileStream);
        ImageView customerIconView = new ImageView(customersIcon);
        customerButton.setGraphic(customerIconView);

        // action in line : 178


        Button mediaButton = new Button("Media");
        mediaButton.setFont(new Font("Roboto", 16));
        FileInputStream mediaFileStream = new FileInputStream("/home/hp/Desktop/phase2Pics/media6.png");
        Image mediaIcon = new Image(mediaFileStream);
        ImageView mediaIconView = new ImageView(mediaIcon);
        mediaButton.setGraphic(mediaIconView);

        Button rentButton = new Button("Rent");
        rentButton.setFont(new Font("Roboto", 16));
        FileInputStream rentFileStream = new FileInputStream("/home/hp/Desktop/phase2Pics/rent.png");
        Image rentIcon = new Image(rentFileStream);
        ImageView rentIconView = new ImageView(rentIcon);
        rentButton.setGraphic(rentIconView);


        mainVbox1.getChildren().addAll(customerButton, mediaButton, rentButton);
        mainVbox1.setAlignment(Pos.CENTER);

        Label systemLabel = new Label();
        systemLabel.setText("Rental Media System");
        systemLabel.setAlignment(Pos.CENTER);
        systemLabel.setTextFill(Color.WHITE);
        systemLabel.setFont(new Font("Oswald", 24));

        FileInputStream farisFileInputStream = new FileInputStream("/home/hp/Desktop/phase2Pics/FARISIX.jpg");
        Image farisixImage = new Image(farisFileInputStream);
        ImageView farisixView = new ImageView(farisixImage);


        mainVbox2.getChildren().addAll(farisixView, systemLabel);
        mainVbox2.setAlignment(Pos.CENTER);

        borderPane.setLeft(mainVbox1);
        borderPane.setCenter(mainVbox2);

        mainScene = new Scene(borderPane);

        stage.setTitle("Rental Media System");
        // stage.setScene(mainScene);


        //****************************************************** customer menu***********************************
        //make label visible

        BorderPane customerBorderPane = new BorderPane();
        customerBorderPane.setStyle("-fx-background-color: #383838");

        Button addNewCustomerButton = new Button("Add new Customer");
        addNewCustomerButton.setMaxSize(500, 10);
        addNewCustomerButton.setFont(new Font("Roboto", 16));
        FileInputStream newCustomerFileStream = new FileInputStream("/home/hp/Desktop/phase2Pics/addCustomer.png");
        Image addCustomerImage = new Image(newCustomerFileStream);
        ImageView addCustomerView = new ImageView(addCustomerImage);
        addNewCustomerButton.setGraphic(addCustomerView);
        addNewCustomerButton.setAlignment(Pos.CENTER);
        // action added in line:242

        Button deleteCustomerButton = new Button("Delete Customer");
        deleteCustomerButton.setMaxSize(500, 50);
        deleteCustomerButton.setFont(new Font("Roboto", 16));
        FileInputStream deleteCustomerFileStream = new FileInputStream("/home/hp/Desktop/phase2Pics/deleteCustomer.png");
        Image deleteCustomerImage = new Image(deleteCustomerFileStream);
        ImageView deleteCustomerView = new ImageView(deleteCustomerImage);
        deleteCustomerButton.setGraphic(deleteCustomerView);
        deleteCustomerButton.setAlignment(Pos.CENTER);

        Button updateCustomerInfoButton1 = makeButton("Update", "/home/hp/Desktop/phase2Pics/updateCust.png");
        updateCustomerInfoButton1.setMaxSize(500, 50);


        Button searchCustomerButton1 = makeButton("Seacrh", "/home/hp/Desktop/phase2Pics/searchCust.png");

        searchCustomerButton1.setMaxSize(500, 50);
        searchCustomerButton1.setFont(new Font("Roboto", 16));
        searchCustomerButton1.setAlignment(Pos.CENTER);

        Button returnToMenuButton = new Button("Return to main page");
        returnToMenuButton.setMaxSize(500, 10);
        returnToMenuButton.setFont(new Font("Roboto", 16));
        FileInputStream returnToMenuStream = new FileInputStream("/home/hp/Desktop/phase2Pics/return.png");
        Image returnToMenuImage = new Image(returnToMenuStream);
        ImageView returnToMenuView = new ImageView(returnToMenuImage);
        returnToMenuButton.setGraphic(returnToMenuView);
        returnToMenuButton.setAlignment(Pos.CENTER);
        returnToMenuButton.setOnAction(e -> stage.setScene(mainScene));


        VBox customerMenuVbox = new VBox(2);
        customerMenuVbox.setStyle("-fx-background-color: #383838");
        customerMenuVbox.getChildren().addAll(addNewCustomerButton, deleteCustomerButton, searchCustomerButton1, updateCustomerInfoButton1, returnToMenuButton);


        customerMenuVbox.setAlignment(Pos.CENTER);

        customerMenuScene = new Scene(customerMenuVbox);

        //buttons that lead to this scene:
        customerButton.setOnAction(e -> {
            stage.setScene(customerMenuScene);
        });


        //Add new customer scene *   *   *   *   *   *   *   *       *   *       *   *       *   *       *   *       *   *       *   *


        Scene newCustomerScene;
        GridPane newCustomerGridPane = new GridPane();
        newCustomerGridPane.setStyle("-fx-background-color: #383838");

        newCustomerGridPane.setPadding(new Insets(5));
        newCustomerGridPane.setVgap(10);
        newCustomerGridPane.setHgap(20);

        Label label = new Label("Customer ID:");
        label.setFont(new Font("Roboto", 20));
        label.setMinSize(200, 100);
        label.setTextFill(Color.WHITE);
        TextField textField = new TextField();


        textField.setMinSize(200, 25);


        Label label1 = new Label("Customer Name:");
        label1.setFont(new Font("Roboto", 20));
        label1.setMinSize(200, 100);
        label1.setTextFill(Color.WHITE);
        TextField textField1 = new TextField();
        textField1.setMinSize(200, 25);
        textField1.setDisable(true);

        // makes it disabled until u fill it
        textField.setOnKeyTyped(a -> {
            textField1.setDisable(false);
        });

        Label label2 = new Label("Mobile Number:");
        label2.setFont(new Font("Roboto", 20));
        label2.setMinSize(200, 100);
        label2.setTextFill(Color.WHITE);
        TextField textField2 = new TextField();
        textField2.setMinSize(200, 25);
        textField2.setDisable(true);

        //customerAddress = textField2.getText();

        textField1.setOnKeyTyped(a -> {
            textField2.setDisable(false);

        });

        Label label3 = new Label("Address");
        label3.setFont(new Font("Roboto", 20));
        label3.setTextFill(Color.WHITE);
        label3.setMinSize(200, 100);
        TextField textField3 = new TextField();
        textField3.setMinSize(200, 25);
        textField3.setDisable(true);

        //customerMobileNumber = textField3.getText();

        textField2.setOnKeyTyped(a -> {
            textField3.setDisable(false);
        });

        Label addCustomerPlanLabel = makeLabel();
        addCustomerPlanLabel.setText("Plan");
        TextField addCustomerPlanTextField = makeTextField();
        addCustomerPlanTextField.setMinSize(200, 25);
        addCustomerPlanTextField.setDisable(true);

        //customerPlan = addCustomerPlanTextField.getText();

        textField3.setOnKeyTyped(nn -> {
            addCustomerPlanTextField.setDisable(false);

        });


        Button addNewCustomerButton2 = makeButton("Add", "/home/hp/Desktop/phase2Pics/addCustomer.png");
        addNewCustomerButton2.setMinWidth(300);
        addNewCustomerButton2.setMaxHeight(50);
        //GridPane.setConstraints(addNewCustomerButton2,0,4);


        addNewCustomerButton2.setOnAction(bk -> {

            String customerId = textField.getText();
            String customerName  = textField1.getText();
            String customerMobileNumber  = textField2.getText();
            String customerAddress  = textField3.getText();
            String customerPlan  = addCustomerPlanTextField.getText();

            boolean isExist = false;
            for (int k = 0; k<MediaRentalManager.customerArrayList.size();k++){
                if (MediaRentalManager.customerArrayList.get(k).getId().equalsIgnoreCase(customerId)) {
                    isExist = true;
                    alertBox("user already exists!");
                }

            }
            if (!isExist) {
                manager.addCustomer(customerId, customerMobileNumber, customerName, customerAddress, customerPlan);

                try {
                    writeCustomer(); // we add it to the list, then we print to the files
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });


        Button backButton = makeButton("back", "/home/hp/Desktop/phase2Pics/return.png");
        backButton.setMinWidth(300);
        backButton.setMinHeight(10);
        backButton.setOnAction(e -> {
            stage.setScene(customerMenuScene);

        });

        newCustomerGridPane.addRow(0, label, textField);
        newCustomerGridPane.addRow(1, label1, textField1);
        newCustomerGridPane.addRow(2, label2, textField2);
        newCustomerGridPane.addRow(3, label3, textField3);
        newCustomerGridPane.addRow(4, addCustomerPlanLabel, addCustomerPlanTextField);


        newCustomerGridPane.setAlignment(Pos.CENTER);
        newCustomerGridPane.setMinSize(400, 200);


        HBox customerHbox = new HBox(20);
        customerHbox.setStyle("-fx-background-color: #383838");
        customerHbox.setAlignment(Pos.CENTER);
        customerHbox.getChildren().addAll(addNewCustomerButton2, backButton);

        BorderPane newCustomerBorderPane = new BorderPane();
        newCustomerBorderPane.setCenter(newCustomerGridPane);
        newCustomerBorderPane.setBottom(customerHbox);
        newCustomerScene = new Scene(newCustomerBorderPane);

        addNewCustomerButton.setOnAction(e -> stage.setScene(newCustomerScene));
        // *********    *********   *********   ********* delete customer menu              *********                   ************************************



        Scene deleteCustomerScene;

        GridPane deleteCustomerGridPane = new GridPane();
        deleteCustomerGridPane.setStyle("-fx-background-color: #383838");
        deleteCustomerGridPane.setPadding(new Insets(5));
        deleteCustomerGridPane.setVgap(10);
        deleteCustomerGridPane.setHgap(20);

        Label delLabel = new Label("Customer ID:");
        delLabel.setFont(new Font("Roboto", 20));
        delLabel.setMinSize(200, 100);
        delLabel.setTextFill(Color.WHITE);
        TextField delTextField = new TextField();


        delTextField.setMinSize(200, 25);

        Label delLabel1 = new Label("Customer Name:");
        delLabel1.setFont(new Font("Roboto", 20));
        delLabel1.setMinSize(200, 100);
        delLabel1.setTextFill(Color.WHITE);
        TextField delTextField1 = new TextField();
        delTextField1.setMinSize(200, 25);
        delTextField1.setDisable(true);

        delTextField.setOnKeyTyped(a -> {
            delTextField1.setDisable(false);
        });

        Label delLabel2 = new Label("Customer Address:");
        delLabel2.setFont(new Font("Roboto", 20));
        delLabel2.setMinSize(200, 100);
        delLabel2.setTextFill(Color.WHITE);
        TextField delTextField2 = new TextField();
        delTextField2.setMinSize(200, 25);
        delTextField2.setDisable(true);

        delTextField1.setOnKeyTyped(a -> {
            delTextField2.setDisable(false);
        });


        Label delLabel3 = new Label("Customer Mobile:");
        delLabel3.setFont(new Font("Roboto", 20));
        delLabel3.setTextFill(Color.WHITE);
        delLabel3.setMinSize(200, 100);
        TextField delTextField3 = new TextField();
        delTextField3.setMinSize(200, 25);
        delTextField3.setDisable(true);

        delTextField2.setOnKeyTyped(a -> {
            delTextField3.setDisable(false);
        });

        Button finalDelteButton = makeButton("Delete", "/home/hp/Desktop/phase2Pics/deleteCustomer.png");
        finalDelteButton.setMinWidth(300);
        finalDelteButton.setMaxHeight(50);

        Button delSearchCustomerButton = makeButton("search", "/home/hp/Desktop/phase2Pics/searchCust.png");
        delSearchCustomerButton.setMinWidth(300);
        delSearchCustomerButton.setMaxHeight(50);

        Button delReturnButton = makeButton("return", "/home/hp/Desktop/phase2Pics/return.png");
        delReturnButton.setMinSize(300, 50);
        delReturnButton.setOnAction(e -> stage.setScene(customerMenuScene));

        Label delteCustomerPlanLabel = makeLabel();
        delteCustomerPlanLabel.setText("Plan");
        TextField delteCustomerPlanTextField = makePlanTextField();
        delteCustomerPlanTextField.setDisable(true);

        delTextField3.setOnKeyTyped(e -> {
            delteCustomerPlanTextField.setDisable(false);

        });

        deleteCustomerGridPane.addRow(0, delLabel, delTextField);
        deleteCustomerGridPane.addRow(1, delLabel1, delTextField1);
        deleteCustomerGridPane.addRow(2, delLabel2, delTextField2);
        deleteCustomerGridPane.addRow(3, delLabel3, delTextField3);
        deleteCustomerGridPane.addRow(4, delteCustomerPlanLabel, delteCustomerPlanTextField);

        deleteCustomerGridPane.setAlignment(Pos.CENTER);
        deleteCustomerGridPane.setMinSize(400, 200);

        HBox finalDeleteHbox = new HBox(20);
        finalDeleteHbox.setStyle("-fx-background-color: #383838");
        finalDeleteHbox.setAlignment(Pos.CENTER);
        finalDeleteHbox.getChildren().addAll(delSearchCustomerButton, finalDelteButton, delReturnButton);

        BorderPane deleteCustomerBorderPane = new BorderPane();
        deleteCustomerBorderPane.setCenter(deleteCustomerGridPane);
        deleteCustomerBorderPane.setBottom(finalDeleteHbox);
        deleteCustomerScene = new Scene(deleteCustomerBorderPane);
        deleteCustomerButton.setOnAction(e -> stage.setScene(deleteCustomerScene));


        delSearchCustomerButton.setOnAction(bk -> {
            String[] arr = new String[4];
            arr = searchCustomer(delTextField.getText());
            delTextField1.setText(arr[0]);
            delTextField2.setText(arr[2]);
            delTextField3.setText(arr[1]);
            delteCustomerPlanTextField.setText(arr[3]);
        });

        finalDelteButton.setOnAction(bk -> {

            String customerDeleteId = delTextField.getText();
            String customerDeleteName = delTextField1.getText();
            String customerDeleteMobileNumber = delTextField2.getText();
            String customerDeleteAddress = delTextField3.getText();
            String customerDeletePlan = delteCustomerPlanTextField.getText();


            try {
                deleteCustomer(delTextField.getText()); // deletes recording to id
                writeCustomer(); // writes to files again after the changes
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        // ************************************************************** search Customer          *******************************************************************


        Scene searchCustomerMenuScene;
        Button searchCustomerMenuButton2 = makeButton("search", "/home/hp/Desktop/phase2Pics/searchCust.png");
        searchCustomerMenuButton2.setMinSize(300, 50);

        BorderPane searchCustomerMenuBorderPane = new BorderPane();

        Button searchReturnButton = makeButton("return", "/home/hp/Desktop/phase2Pics/return.png");
        searchReturnButton.setMinSize(300, 50);
        searchReturnButton.setOnAction(e -> stage.setScene(customerMenuScene));

        HBox searchCustomerMenuHbox = new HBox(20);
        searchCustomerMenuHbox.setStyle("-fx-background-color: #383838");
        searchCustomerMenuHbox.getChildren().addAll(searchCustomerMenuButton2, searchReturnButton);
        searchCustomerMenuHbox.setAlignment(Pos.CENTER);

        GridPane searchCustomerMenuGridPane = new GridPane();
        searchCustomerMenuGridPane.setStyle("-fx-background-color: #383838");

        searchCustomerMenuGridPane.setPadding(new Insets(5));
        searchCustomerMenuGridPane.setVgap(10);
        searchCustomerMenuGridPane.setHgap(20);

        Label searchLabel = new Label("Customer ID:");
        searchLabel.setFont(new Font("Roboto", 20));
        searchLabel.setMinSize(200, 100);
        searchLabel.setTextFill(Color.WHITE);
        TextField searchTextField = new TextField();

        searchTextField.setMinSize(200, 25);


        Label searchLabel1 = new Label("Customer Name:");
        searchLabel1.setFont(new Font("Roboto", 20));
        searchLabel1.setMinSize(200, 100);
        searchLabel1.setTextFill(Color.WHITE);
        TextField searchTextField1 = new TextField();
        searchTextField1.setMinSize(200, 25);
        searchTextField1.setDisable(true);
        /*searchTextField.setOnKeyTyped(a -> {
            searchTextField1.setDisable(false);
        });*/

        Label searchLabel2 = new Label("Customer Address:");
        searchLabel2.setFont(new Font("Roboto", 20));
        searchLabel2.setMinSize(200, 100);
        searchLabel2.setTextFill(Color.WHITE);
        TextField searchTextField2 = new TextField();
        searchTextField2.setMinSize(200, 25);
        searchTextField2.setDisable(true);
        /*searchTextField1.setOnKeyTyped(a -> {
            searchTextField2.setDisable(false);
        });*/


        Label searchLabel3 = new Label("Customer Mobile:");
        searchLabel3.setFont(new Font("Roboto", 20));
        searchLabel3.setTextFill(Color.WHITE);
        searchLabel3.setMinSize(200, 100);
        TextField searchTextField3 = new TextField();
        searchTextField3.setMinSize(200, 25);
        searchTextField3.setDisable(true);
        /*searchTextField2.setOnKeyTyped(a -> {
            searchTextField3.setDisable(false);

        });*/

        Label searchCustomerPlanLabel = makeLabel();
        searchCustomerPlanLabel.setText("Plan");
        TextField searchCustomerPlanTextField = makePlanTextField();
        searchCustomerPlanTextField.setDisable(true);
        /*searchTextField3.setOnKeyTyped(ee -> {
            searchCustomerPlanTextField.setDisable(false);

        });*/

        searchCustomerMenuGridPane.addRow(0, searchLabel, searchTextField);
        searchCustomerMenuGridPane.addRow(1, searchLabel1, searchTextField1);
        searchCustomerMenuGridPane.addRow(2, searchLabel2, searchTextField2);
        searchCustomerMenuGridPane.addRow(3, searchLabel3, searchTextField3);
        searchCustomerMenuGridPane.addRow(4, searchCustomerPlanLabel, searchCustomerPlanTextField);


        searchCustomerMenuGridPane.setAlignment(Pos.CENTER);
        searchCustomerMenuGridPane.setMinSize(400, 200);

        searchCustomerMenuBorderPane.setCenter(searchCustomerMenuGridPane);
        searchCustomerMenuBorderPane.setBottom(searchCustomerMenuHbox);
        searchCustomerMenuScene = new Scene(searchCustomerMenuBorderPane);

        searchCustomerButton1.setOnAction(e -> stage.setScene(searchCustomerMenuScene));

        searchCustomerMenuButton2.setOnAction(bk -> {

            String customerSearchId =searchTextField .getText();
            String customerSearchName = searchTextField1.getText();
            String customerSearchMobileNumber = searchTextField2.getText();
            String customerSearchAddress = searchTextField3.getText();
            String customerSearchPlan = searchCustomerPlanTextField.getText();

            String[] searchArray = searchCustomer(customerSearchId);
            searchTextField1.setText(searchArray[0]);
            searchTextField2.setText(searchArray[2]);
            searchTextField3.setText(searchArray[1]);
            searchCustomerPlanTextField.setText(searchArray[3]);

        });

        //******************************************UPDATE CUSTOMER MENU **********************************************



        Scene updateCustomerMenuScene;
        BorderPane updateCustomerMenuBorderPane = new BorderPane();
        GridPane updateCustomerMenuGridPane = new GridPane();


        Button updateCustomerMenuButton = makeButton("Update", "/home/hp/Desktop/phase2Pics/updateCust.png");
        updateCustomerMenuButton.setFont(new Font("Roboto", 20));
        updateCustomerMenuButton.setMinSize(300, 50);

        HBox updateCustomerMenuHbox = new HBox(20);
        updateCustomerMenuHbox.setStyle("-fx-background-color: #383838");
        updateCustomerMenuHbox.setAlignment(Pos.CENTER);

        Button updateReturnButton = makeButton("return", "/home/hp/Desktop/phase2Pics/return.png");
        updateReturnButton.setMinSize(300, 50);
        updateReturnButton.setOnAction(e -> stage.setScene(customerMenuScene));

        updateCustomerMenuHbox.getChildren().addAll(updateCustomerMenuButton, updateReturnButton);

        Label updateLabel111111 = new Label("Customer ID:");
        updateLabel111111.setFont(new Font("Roboto", 20));
        updateLabel111111.setMinSize(200, 100);
        updateLabel111111.setTextFill(Color.WHITE);
        TextField updateTextField11111 = new TextField();
        updateTextField11111.setMinSize(200, 25);


        Label updateLablel1 = new Label("new name:");
        updateLablel1.setFont(new Font("Roboto", 20));
        updateLablel1.setMinSize(200, 100);
        updateLablel1.setTextFill(Color.WHITE);
        TextField updateTextField1 = new TextField();
        updateTextField1.setMinSize(200, 25);
        updateTextField1.setDisable(true);

        updateTextField11111.setOnKeyTyped(a -> {
            updateTextField1.setDisable(false);
        });


        Label updateLablel2 = new Label("new address");
        updateLablel2.setFont(new Font("Roboto", 20));
        updateLablel2.setMinSize(200, 100);
        updateLablel2.setTextFill(Color.WHITE);
        TextField updateTextField2 = new TextField();
        updateTextField2.setMinSize(200, 25);
        updateTextField2.setDisable(true);

        updateTextField1.setOnKeyTyped(a -> {
            updateTextField2.setDisable(false);
        });


        Label updateLablel3 = new Label("new mobile");
        updateLablel3.setFont(new Font("Roboto", 20));
        updateLablel3.setMinSize(200, 100);
        updateLablel3.setTextFill(Color.WHITE);
        TextField updateTextField3 = new TextField();
        updateTextField3.setMinSize(200, 25);
        updateTextField3.setDisable(true);

        updateTextField2.setOnKeyTyped(a -> {
            updateTextField3.setDisable(false);
        });


        Label updateCustomerPlanLabel = makeLabel();
        updateCustomerPlanLabel.setText("Plan");
        TextField updateCustomerPlanTextField = makePlanTextField();
        updateCustomerPlanTextField.setDisable(true);

        updateTextField3.setOnKeyTyped(ee -> {
            updateCustomerPlanTextField.setDisable(false);

        });

        updateCustomerMenuGridPane.addRow(0, updateLabel111111, updateTextField11111);

        updateCustomerMenuGridPane.addRow(1, updateLablel1, updateTextField1);

        updateCustomerMenuGridPane.addRow(2, updateLablel2, updateTextField2);
        updateCustomerMenuGridPane.addRow(3, updateLablel3, updateTextField3);
        updateCustomerMenuGridPane.addRow(4, updateCustomerPlanLabel, updateCustomerPlanTextField);


        updateCustomerMenuGridPane.setAlignment(Pos.CENTER);
        updateCustomerMenuBorderPane.setStyle("-fx-background-color: #383838");
        updateCustomerMenuBorderPane.setCenter(updateCustomerMenuGridPane);
        updateCustomerMenuBorderPane.setBottom(updateCustomerMenuHbox);

        updateCustomerMenuScene = new Scene(updateCustomerMenuBorderPane);
        updateCustomerInfoButton1.setOnAction(e -> stage.setScene(updateCustomerMenuScene));

        updateCustomerMenuButton.setOnAction(bk -> {
            String customerUpdateId = updateTextField11111.getText();
            String customerUpdateName  = updateTextField1.getText();
            String customerUpdateMobileNumber = updateTextField2.getText();
            String customerUpdateAddress = updateTextField3.getText();
            String customerUpdatePlan = updateCustomerPlanTextField.getText();

            try {
                update(customerUpdateId, customerUpdateName, customerUpdateAddress, customerUpdateMobileNumber, updateCustomerPlanTextField.getText());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        //*********************************************** MEDIA MENU! ***************************************************
        Scene mediaMenuScene;
        BorderPane mediaMenuBorderPane = new BorderPane();
        VBox mediaMenuVbox = new VBox(50);
        HBox mediaMenuHboxTop = new HBox(100);
        HBox mediaMenuHboxBot = new HBox(100);


        Button mediaMenuAddButton = makeButton("Add Media", "/home/hp/Desktop/phase2Pics/greenAdd.png");
        mediaMenuAddButton.setAlignment(Pos.TOP_LEFT);

        Button mediaDeleteButton = makeButton("Delete Media", "/home/hp/Desktop/phase2Pics/delete.png");
        //mediaDeleteButton.setAlignment(Pos.CENTER);

        Button mediaUpdateButton = makeButton("Update Media", "/home/hp/Desktop/phase2Pics/updateCust.png");
        mediaUpdateButton.setAlignment(Pos.BOTTOM_RIGHT);

        Button mediaSearchButton = makeButton("Search", "/home/hp/Desktop/phase2Pics/mediaSearch.png");
        mediaSearchButton.setAlignment(Pos.CENTER);

        Button mediaPrintButton = makeButton("Print", "/home/hp/Desktop/phase2Pics/print.png");
        mediaPrintButton.setAlignment(Pos.CENTER);

        Button mediaMenuReturnButton = makeButton("Return", "/home/hp/Desktop/phase2Pics/return.png");
        mediaMenuReturnButton.setAlignment(Pos.CENTER);
        mediaMenuReturnButton.setOnAction(e -> {
            stage.setScene(mainScene);
        });


        mediaMenuVbox.getChildren().addAll(mediaPrintButton,
                mediaDeleteButton
        );

        mediaMenuVbox.setAlignment(Pos.CENTER);
        mediaMenuBorderPane.setStyle("-fx-background-color: #383838");
        mediaMenuVbox.setStyle("-fx-background-color: #383838");

        //mediaMenuBorderPane.setAlignment(mediaDeleteButton,Pos.BOTTOM_RIGHT);
        //mediaMenuBorderPane.setAlignment(mediaMenuAddButton,Pos.TOP_);
        mediaMenuHboxBot.getChildren().addAll(mediaMenuAddButton, mediaMenuReturnButton);
        mediaMenuHboxBot.setAlignment(Pos.CENTER);
        mediaMenuHboxTop.getChildren().addAll(mediaSearchButton, mediaUpdateButton);
        mediaMenuHboxTop.setAlignment(Pos.CENTER);

        mediaMenuBorderPane.setTop(mediaMenuHboxTop);
        mediaMenuBorderPane.setBottom(mediaMenuHboxBot);
        mediaMenuBorderPane.setCenter(mediaMenuVbox);
        mediaMenuScene = new Scene(mediaMenuBorderPane);


        // button in main menu
        mediaButton.setOnAction(e -> {

            stage.setScene(mediaMenuScene);

        });

        //****************************************** Add media combo box scene******************************
        BorderPane addMediaComboBoxBorderPane = new BorderPane();
        addMediaComboBoxBorderPane.setStyle("-fx-background-color: #383838");
        ComboBox<String> mediaComboBox = new ComboBox<>();
        mediaComboBox.getItems().addAll("Movie", "Album", "Game");
        mediaComboBox.setPromptText("Please Choose the type of media");
        mediaComboBox.setMinSize(200, 50);

        HBox addMediaComboBoxHbox = new HBox(30);
        addMediaComboBoxHbox.setStyle("-fx-background-color: #383838");
        addMediaComboBoxHbox.getChildren().addAll(mediaComboBox);
        addMediaComboBoxHbox.setAlignment(Pos.CENTER);


        Scene addMediaComboBoxScene;
        VBox addMediacomboBoxVbox = makeComboBoxVbox();
        Button addMediaComboBoxReturnButton = makeReturnButton();
        Button addMediaComboBoxAddButton = makeButton("Add", "/home/hp/Desktop/phase2Pics/greenAdd.png");


        HBox addMediaComboBoxHbox1 = new HBox(30);
        addMediaComboBoxHbox1.getChildren().addAll(addMediaComboBoxAddButton, addMediaComboBoxReturnButton);
        addMediaComboBoxHbox1.setAlignment(Pos.CENTER);

        addMediaComboBoxBorderPane.setTop(addMediaComboBoxHbox);
        addMediaComboBoxBorderPane.setBottom(addMediaComboBoxHbox1);
        addMediaComboBoxScene = new Scene(addMediaComboBoxBorderPane);
        // ******* COMBO BOX BRANCHES******
        GridPane comboBranch1GridPane = new GridPane();
        comboBranch1GridPane.setStyle("-fx-background-color: #383838");


        Label comboBranch1Label1 = makeLabel();
        comboBranch1Label1.setText("Movie code");
        TextField comboBranch1TextField1 = makeTextField();
        Label comboBranch1Label2 = makeLabel();
        comboBranch1Label2.setText("Movie title");
        TextField comboBranch1TextField2 = makeTextField();


        Label comboBranch1Label3 = makeLabel();
        comboBranch1Label3.setText("Number of Copies");
        comboBranch1Label3.setFont(new Font("Roboto", 18));
        TextField comboBranch1TextField3 = makeTextField();

        Label comboBranch1Label4 = makeLabel();
        comboBranch1Label4.setText("Movie Rating");
        ToggleGroup tg = new ToggleGroup();


        HBox radioButtonsHbox = new HBox(10);
        RadioButton dr = new RadioButton("DR");// DR
        dr.setTextFill(Color.WHITE);
        RadioButton hr = new RadioButton("HR");
        hr.setTextFill(Color.WHITE);
        RadioButton ac = new RadioButton("AC");
        ac.setTextFill(Color.WHITE);
        dr.setToggleGroup(tg);
        hr.setToggleGroup(tg);
        ac.setToggleGroup(tg);


        radioButtonsHbox.getChildren().addAll(dr, hr, ac);
        radioButtonsHbox.setAlignment(Pos.CENTER);

        comboBranch1GridPane.setAlignment(Pos.CENTER);

        comboBranch1GridPane.addRow(0, comboBranch1Label1, comboBranch1TextField1);
        comboBranch1GridPane.addRow(1, comboBranch1Label2, comboBranch1TextField2);
        comboBranch1GridPane.addRow(2, comboBranch1Label3, comboBranch1TextField3);
        comboBranch1GridPane.addRow(3, comboBranch1Label4, radioButtonsHbox);


        // branch 2

        GridPane comboBranch2GridPane = new GridPane();
        comboBranch2GridPane.setStyle("-fx-background-color: #383838");


        Label comboBranch2Label = makeLabel();
        comboBranch2Label.setText("Game code");
        TextField comboBranch2TextField1 = makeTextField();
        Label comboBranch2Label2 = makeLabel();
        comboBranch2Label2.setText("Game title");
        TextField comboBranch2TextField2 = makeTextField();


        Label comboBranch2Label3 = makeLabel();
        comboBranch2Label3.setText("Number of Copies");
        comboBranch2Label3.setFont(new Font("Roboto", 18));
        TextField comboBranch2TextField3 = makeTextField();

        Label comboBranch2Label4 = makeLabel();
        comboBranch2Label4.setText("Game weight");
        TextField comboBranch2TextField4 = makeTextField();

        comboBranch2GridPane.addRow(0, comboBranch2Label, comboBranch2TextField1);
        comboBranch2GridPane.addRow(1, comboBranch2Label2, comboBranch2TextField2);
        comboBranch2GridPane.addRow(2, comboBranch2Label3, comboBranch2TextField3);
        comboBranch2GridPane.addRow(3, comboBranch2Label4, comboBranch2TextField4);
        comboBranch2GridPane.setAlignment(Pos.CENTER);

        // branch 3

        GridPane comboBranch3GridPane = new GridPane();
        comboBranch3GridPane.setStyle("-fx-background-color: #383838");


        Label comboBranch3Label = makeLabel();
        comboBranch3Label.setText("Album code");
        TextField comboBranch3TextField1 = makeTextField();
        Label comboBranch3Label2 = makeLabel();
        comboBranch3Label2.setText("Album title");
        TextField comboBranch3TextField2 = makeTextField();


        Label comboBranch3Label3 = makeLabel();
        comboBranch3Label3.setText("Number of Copies");
        comboBranch3Label3.setFont(new Font("Roboto", 18));
        TextField comboBranch3TextField3 = makeTextField();

        Label comboBranch3Label4 = makeLabel();
        comboBranch3Label4.setText("Album Artist");
        TextField comboBranch3TextField4 = makeTextField();

        Label addMediaComboBoxSongsLabel = makeLabel();
        addMediaComboBoxSongsLabel.setText("Songs");
        TextField addMediaComboBoxSongsTextField = makeTextField();


        comboBranch3GridPane.addRow(0, comboBranch3Label, comboBranch3TextField1);
        comboBranch3GridPane.addRow(1, comboBranch3Label2, comboBranch3TextField2);
        comboBranch3GridPane.addRow(2, comboBranch3Label3, comboBranch3TextField3);
        comboBranch3GridPane.addRow(3, addMediaComboBoxSongsLabel, addMediaComboBoxSongsTextField);

        comboBranch3GridPane.addRow(4, comboBranch3Label4, comboBranch3TextField4);
        comboBranch3GridPane.setAlignment(Pos.CENTER);



        addMediaComboBoxAddButton.setOnAction(bkk -> {


            if (mediaComboBox.getValue().equalsIgnoreCase("Movie")) {

                String rating = "";
                if (ac.isSelected()){
                    rating = "AC";
                }
                else if (dr.isSelected()){
                    rating = "DR";
                }
                else if(hr.isSelected()){
                    rating = "HR";
                }
                // addMovie is not static so we needed an object to  call it
                manager.addMovie(comboBranch1TextField1.getText(), comboBranch1TextField2.getText(), Integer.parseInt( comboBranch1TextField3.getText()),rating);
                try {
                    writeMedia(); // we added a  media so now we need to update the files so we re-write what inside the the list
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (mediaComboBox.getValue().equalsIgnoreCase("Album")) {
                manager.addAlbum(comboBranch3TextField1.getText(), comboBranch3TextField2.getText(), Integer.parseInt(comboBranch3TextField3.getText()), addMediaComboBoxSongsTextField.getText(), comboBranch3TextField4.getText());
                try {
                    writeMedia();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                manager.addGame(comboBranch2TextField1.getText(), comboBranch2TextField2.getText(), Integer.parseInt(comboBranch2TextField3.getText()), Double.parseDouble(comboBranch2TextField4.getText()));
                try {
                    writeMedia();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }


        });

        mediaComboBox.setOnAction(l -> {
            if (mediaComboBox.getValue().equalsIgnoreCase("Movie")) {
                addMediaComboBoxBorderPane.setCenter(comboBranch1GridPane);

            } else if (mediaComboBox.getValue().equalsIgnoreCase("Game")) {
                addMediaComboBoxBorderPane.setCenter(comboBranch2GridPane);

            } else {
                addMediaComboBoxBorderPane.setCenter(comboBranch3GridPane);

            }
        });

        addMediaComboBoxReturnButton.setOnAction(c -> {
            stage.setScene(mediaMenuScene);
        });

        mediaMenuAddButton.setOnAction(d -> {
            stage.setScene(addMediaComboBoxScene);
        });

        //****************************************************delete Media combo box*********************************************
        Scene deleteMediaComboBoxScene;
        Label deleteMediaComboBoxLabel = makeLabel();
        deleteMediaComboBoxLabel.setText("input code of media you want to delete ");
        TextField deleteMediaComboBoxTextField = makeTextField();
        deleteMediaComboBoxTextField.setMaxSize(300, 50);
        Button deleteMediaComboBoxDeleteButton = makeButton("Delete", "/home/hp/Desktop/phase2Pics/delete.png");


        VBox deleteMediaComboBoxVbox = new VBox(10);
        deleteMediaComboBoxVbox.setStyle("-fx-background-color: #383838");
        deleteMediaComboBoxVbox.setAlignment(Pos.CENTER);

        Button deleteMediaComboBoxReturnButton = makeReturnButton();
        deleteMediaComboBoxScene = new Scene(deleteMediaComboBoxVbox);
        deleteMediaComboBoxVbox.getChildren().addAll(deleteMediaComboBoxLabel, deleteMediaComboBoxTextField, deleteMediaComboBoxDeleteButton, deleteMediaComboBoxReturnButton);


        deleteMediaComboBoxReturnButton.setOnAction(d -> {
            stage.setScene(mediaMenuScene);

        });
        mediaDeleteButton.setOnAction(f -> {
            stage.setScene(deleteMediaComboBoxScene);
        });
        deleteMediaComboBoxDeleteButton.setOnAction(bk -> {
            try {
                deleteMedia(deleteMediaComboBoxTextField.getText());
                writeMedia();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        //*********************************************** print Media combo box***************************
        Scene printMediaComboBoxScene;
        VBox printMediaComboBoxVbox = new VBox(30);
        printMediaComboBoxVbox.setAlignment(Pos.CENTER);
        TextArea printMediaComboBoxTextArea = new TextArea();
        printMediaComboBoxTextArea.setPrefSize(300, 200);

        printMediaComboBoxVbox.setStyle("-fx-background-color: #383838");
        Button printMediaComboBoxPrintButton = makeButton("print", "/home/hp/Desktop/phase2Pics/print.png");

        Button printMediaComboBoxReturnButton = makeReturnButton();
        printMediaComboBoxVbox.getChildren().addAll(printMediaComboBoxTextArea, printMediaComboBoxPrintButton, printMediaComboBoxReturnButton);
        printMediaComboBoxScene = new Scene(printMediaComboBoxVbox);

        printMediaComboBoxReturnButton.setOnAction(g -> {
            stage.setScene(mediaMenuScene);
        });
        mediaPrintButton.setOnAction(gg -> {
            stage.setScene(printMediaComboBoxScene);
            printMediaComboBoxTextArea.clear();
        });

        printMediaComboBoxPrintButton.setOnAction(bk -> {
            String string = "";

            for(int i = 0 ;i<MediaRentalManager.mediaArrayList.size();i++){
                string += (MediaRentalManager.mediaArrayList.get(i).getInfo());
                string+="\n";
            }

            printMediaComboBoxTextArea.setText (string);


        });


        //**********************************update Media combo box**********************
        Scene updateMediaComboBoxScene;
        BorderPane updateMediaComboBoxBorderPane = new BorderPane();
        updateMediaComboBoxBorderPane.setStyle("-fx-background-color: #383838");


        HBox updateMediaComboBoxHboxTop = new HBox(50);
        updateMediaComboBoxHboxTop.setStyle("-fx-background-color: #383838");
        updateMediaComboBoxHboxTop.setAlignment(Pos.CENTER);
        ComboBox<String> updateMediaComboBox = new ComboBox<>();
        updateMediaComboBox.getItems().addAll("Movie", "Album", "Game");
        updateMediaComboBox.setPromptText("Please Choose the type of media");
        updateMediaComboBox.setMinSize(200, 50);
        updateMediaComboBoxHboxTop.getChildren().add(updateMediaComboBox);
        updateMediaComboBoxBorderPane.setTop(updateMediaComboBoxHboxTop);

        Button updateMediaComboBoxReturnButton = makeReturnButton();
        Button updateMediaComboBoxUpdateButton = makeButton("Update", "/home/hp/Desktop/phase2Pics/updateCust.png");

        HBox updateMediaComboBoxHboxBot = new HBox(50);
        updateMediaComboBoxHboxBot.setStyle("-fx-background-color: #383838");
        updateMediaComboBoxHboxBot.getChildren().addAll(updateMediaComboBoxUpdateButton, updateMediaComboBoxReturnButton);
        updateMediaComboBoxHboxBot.setAlignment(Pos.CENTER);
        updateMediaComboBoxBorderPane.setBottom(updateMediaComboBoxHboxBot);

        updateMediaComboBoxScene = new Scene(updateMediaComboBoxBorderPane);



        //branch1

        GridPane comboUpdateBranch1GridPane = new GridPane();
        comboUpdateBranch1GridPane.setStyle("-fx-background-color: #383838");


        Label comboUpdateBranch1Label1 = makeLabel();
        comboUpdateBranch1Label1.setText("Movie code");
        TextField comboUpdateBranch1TextField1 = makeTextField();
        Label comboUpdateBranch1Label2 = makeLabel();
        comboUpdateBranch1Label2.setText(" New Movie title");
        TextField comboUpdateBranch1TextField2 = makeTextField();


        Label comboUpdateBranch1Label3 = makeLabel();
        comboUpdateBranch1Label3.setText("New Number of Copies");
        comboUpdateBranch1Label3.setFont(new Font("Roboto", 18));
        TextField comboUpdateBranch1TextField3 = makeTextField();

        Label comboUpdateBranch1Label4 = makeLabel();
        comboUpdateBranch1Label4.setText("New Movie Rating");
        ToggleGroup updateTg = new ToggleGroup();


        HBox radioButtonsUpdateHbox = new HBox(10);
        RadioButton wrUpdate = new RadioButton("WR");
        wrUpdate.setTextFill(Color.WHITE);
        RadioButton hrUpdate = new RadioButton("HR");
        hrUpdate.setTextFill(Color.WHITE);
        RadioButton acUpdate = new RadioButton("AC");
        acUpdate.setTextFill(Color.WHITE);
        wrUpdate.setToggleGroup(updateTg);
        hrUpdate.setToggleGroup(updateTg);
        acUpdate.setToggleGroup(updateTg);


        radioButtonsUpdateHbox.getChildren().addAll(wrUpdate, hrUpdate, acUpdate);
        radioButtonsUpdateHbox.setAlignment(Pos.CENTER);


        comboUpdateBranch1GridPane.setAlignment(Pos.CENTER);

        Label updateBranch1Label = makeLabel();
        comboUpdateBranch1Label3.setText("new Number Of copies");
        TextField updateBranch1TextField = makeTextField();

        //comboUpdateBranch1GridPane.addRow(0,updateBranch1Label,updateBranch1TextField);

        comboUpdateBranch1GridPane.addRow(1, comboUpdateBranch1Label1, comboUpdateBranch1TextField1);
        comboUpdateBranch1GridPane.addRow(2, comboUpdateBranch1Label2, comboUpdateBranch1TextField2);
        comboUpdateBranch1GridPane.addRow(3, comboUpdateBranch1Label3, comboUpdateBranch1TextField3);
        comboUpdateBranch1GridPane.addRow(4, comboUpdateBranch1Label4, radioButtonsUpdateHbox);

        // branch2

        GridPane comboUpdateBranch2GridPane = new GridPane();
        comboUpdateBranch2GridPane.setStyle("-fx-background-color: #383838");


        Label comboUpdateBranch2Label = makeLabel();
        comboUpdateBranch2Label.setText("Game code");
        TextField comboUpdateBranch2TextField1 = makeTextField();
        Label comboUpdateBranch2Label2 = makeLabel();
        comboUpdateBranch2Label2.setText("New Game title");
        TextField comboUpdateBranch2TextField2 = makeTextField();


        Label comboUpdateBranch2Label3 = makeLabel();
        comboUpdateBranch2Label3.setText("New Number of Copies");
        comboUpdateBranch2Label3.setFont(new Font("Roboto", 18));
        TextField comboUpdateBranch2TextField3 = makeTextField();

        Label comboUpdateBranch2Label4 = makeLabel();
        comboUpdateBranch2Label4.setText("New weight");
        TextField comboUpdateBranch2TextField4 = makeTextField();

        comboUpdateBranch2GridPane.addRow(0, comboUpdateBranch2Label, comboUpdateBranch2TextField1);
        comboUpdateBranch2GridPane.addRow(1, comboUpdateBranch2Label2, comboUpdateBranch2TextField2);
        comboUpdateBranch2GridPane.addRow(2, comboUpdateBranch2Label3, comboUpdateBranch2TextField3);
        comboUpdateBranch2GridPane.addRow(3, comboUpdateBranch2Label4, comboUpdateBranch2TextField4);
        comboUpdateBranch2GridPane.setAlignment(Pos.CENTER);

        //branch3

        //branch3

        GridPane comboUpdateBranch3GridPane = new GridPane();
        comboUpdateBranch3GridPane.setStyle("-fx-background-color: #383838");


        Label comboUpdateBranch3Label = makeLabel();
        comboUpdateBranch3Label.setText("Album code");
        TextField comboUpdateBranch3TextField1 = makeTextField();
        Label comboUpdateBranch3Label2 = makeLabel();
        comboUpdateBranch3Label2.setText("New title");
        TextField comboUpdateBranch3TextField2 = makeTextField();


        Label comboUpdateBranch3Label3 = makeLabel();
        comboUpdateBranch3Label3.setText("New NO. of Copies");
        comboUpdateBranch3Label3.setFont(new Font("Roboto", 18));
        TextField comboUpdateBranch3TextField3 = makeTextField();

        Label comboUpdateBranch3Label4 = makeLabel();
        comboUpdateBranch3Label4.setText("New Artist");
        TextField comboUpdateBranch3TextField4 = makeTextField();

        Label updateMediaComboBoxSongsLabel = makeLabel();
        updateMediaComboBoxSongsLabel.setText("New Songs");
        TextField updateMediaComboBoxSongsTextField = makeTextField();

        comboUpdateBranch3GridPane.addRow(0, comboUpdateBranch3Label, comboUpdateBranch3TextField1);
        comboUpdateBranch3GridPane.addRow(1, comboUpdateBranch3Label2, comboUpdateBranch3TextField2);
        comboUpdateBranch3GridPane.addRow(2, comboUpdateBranch3Label3, comboUpdateBranch3TextField3);
        comboUpdateBranch3GridPane.addRow(3, comboUpdateBranch3Label4, comboUpdateBranch3TextField4);
        comboUpdateBranch3GridPane.addRow(4, updateMediaComboBoxSongsLabel, updateMediaComboBoxSongsTextField);

        comboUpdateBranch3GridPane.setAlignment(Pos.CENTER);

        //in past menu
        mediaUpdateButton.setOnAction(h -> {
            stage.setScene(updateMediaComboBoxScene);
        });

        updateMediaComboBoxReturnButton.setOnAction(hh -> {
            stage.setScene(mediaMenuScene);
        });


        //combo box actions
        updateMediaComboBox.setOnAction(m -> {
            if (updateMediaComboBox.getValue().equalsIgnoreCase("Movie")) {
                updateMediaComboBoxBorderPane.setCenter(comboUpdateBranch1GridPane);

            } else if (updateMediaComboBox.getValue().equalsIgnoreCase("Game")) {
                updateMediaComboBoxBorderPane.setCenter(comboUpdateBranch2GridPane);

            } else {
                updateMediaComboBoxBorderPane.setCenter(comboUpdateBranch3GridPane);


            }
        });
        String updateRating;
        if (hrUpdate.isSelected()){
            updateRating = "HR" ;
        }
        else if (acUpdate.isSelected()){
            updateRating = "AC";
        }
        else {
            updateRating = "WR";
        }






        updateMediaComboBoxUpdateButton.setOnAction(bk -> {

            if (updateMediaComboBox.getValue().equalsIgnoreCase("Game")){
                boolean exists = false;
                for (int i =0 ;i<MediaRentalManager.mediaArrayList.size();i++){
                    if (MediaRentalManager.mediaArrayList.get(i).getMediaCode().equalsIgnoreCase(comboUpdateBranch2TextField1.getText())) {
                        exists = true;
                        //removes the old one with old attributes
                        MediaRentalManager.mediaArrayList.remove(MediaRentalManager.mediaArrayList.get(i));
                        //adds the new one with new attributes
                        manager.addGame(comboUpdateBranch2TextField1.getText(),comboUpdateBranch2TextField2.getText(),Integer.parseInt(comboUpdateBranch2TextField3.getText()),Double.parseDouble( comboUpdateBranch2TextField4.getText()));
                        try {
                            writeMedia();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }



                }
                if (!exists){
                    alertBox("Media Doesn't exist");
                }
            }
            else  if (updateMediaComboBox.getValue().equalsIgnoreCase("Album")){
                boolean exists = false;

                for (int i =0 ;i<MediaRentalManager.mediaArrayList.size();i++){
                    if (MediaRentalManager.mediaArrayList.get(i).getMediaCode().equalsIgnoreCase(comboUpdateBranch3TextField1.getText())) {
                        exists = true;
                        MediaRentalManager.mediaArrayList.remove(MediaRentalManager.mediaArrayList.get(i));

                        manager.addAlbum(comboUpdateBranch3TextField1.getText(),comboUpdateBranch3TextField2.getText(),Integer.parseInt(comboUpdateBranch3TextField3.getText()), comboUpdateBranch3TextField4.getText(), updateMediaComboBoxSongsTextField.getText() );
                        try {
                            writeMedia();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }
                if (!exists){
                    alertBox("Media Doesn't exist");
                }
            }
            else {
                boolean exists = false;

                for (int i =0 ;i<MediaRentalManager.mediaArrayList.size();i++){
                    if (MediaRentalManager.mediaArrayList.get(i).getMediaCode().equalsIgnoreCase(comboUpdateBranch1TextField1.getText())) {
                        exists = true;

                        MediaRentalManager.mediaArrayList.remove(MediaRentalManager.mediaArrayList.get(i));


                        manager.addMovie(comboUpdateBranch1TextField1.getText(),comboUpdateBranch1TextField2.getText(),Integer.parseInt(comboUpdateBranch1TextField3.getText()), updateRating );
                        try {
                            writeMedia();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }
                if (!exists){
                    alertBox("Media Doesn't exist");
                }




            }


        });

        //********************************* Search Media combo box Scene ****************************************

        BorderPane searchMediaComboBoxBorderPane = new BorderPane();
        searchMediaComboBoxBorderPane.setStyle("-fx-background-color: #383838");


        Scene searchMediaComboBoxScene;
        VBox searchMediaComboBoxVbox = new VBox(30);
        Label searchMediaComboBoxLabel = makeLabel();
        searchMediaComboBoxLabel.setText("Media Code");
        TextField searchMediaComboBoxTextField = makeTextField();
        HBox searchMediaComboBoxHbox = makeHbox();

        TextArea searchMediaComboBoxTextArea = new TextArea();
        //searchMediaComboBoxTextArea.setPrefSize(200,100);
        VBox textAreaVbox = new VBox();
        searchMediaComboBoxTextArea.setPrefHeight(300);
        searchMediaComboBoxTextArea.setPrefWidth(150);
        textAreaVbox.getChildren().addAll(searchMediaComboBoxTextArea);

        searchMediaComboBoxHbox.getChildren().addAll(searchMediaComboBoxLabel, searchMediaComboBoxTextField);
        searchMediaComboBoxBorderPane.setTop(searchMediaComboBoxHbox);

        HBox searchMediaComboBoxHbox1 = makeHbox();
        Button searchMediaComboBoxSearchButton = makeButton("Search", "/home/hp/Desktop/phase2Pics/mediaSearch.png");
        Button searchMediaComboBoxReturnButton = makeReturnButton();
        searchMediaComboBoxHbox1.getChildren().addAll(searchMediaComboBoxSearchButton
                , searchMediaComboBoxReturnButton);

        searchMediaComboBoxBorderPane.setBottom(searchMediaComboBoxHbox1);
        searchMediaComboBoxBorderPane.setCenter(textAreaVbox);


        searchMediaComboBoxScene = new Scene(searchMediaComboBoxBorderPane);

        mediaSearchButton.setOnAction(i -> {
            stage.setScene(searchMediaComboBoxScene);
        });

        searchMediaComboBoxReturnButton.setOnAction(ii -> {
            stage.setScene(mediaMenuScene);
        });

        searchMediaComboBoxSearchButton.setOnAction(bk -> {

            for (int i =0;i<MediaRentalManager.mediaArrayList.size();i++){
                if (MediaRentalManager.mediaArrayList.get(i).getMediaCode().equalsIgnoreCase(searchMediaComboBoxTextField.getText())){
                    searchMediaComboBoxTextArea.setText(MediaRentalManager.mediaArrayList.get(i).getInfo().toString());


                }


            }

        });

        //********************************************* Rent ******************************************
        Scene rentMenuScene;
        BorderPane rentMenuBorderPane = new BorderPane();
        VBox rentMenuVbox = new VBox(5);
        HBox rentMenuHbox = makeHbox();
        HBox rentMenuHbox1 = makeHbox();
        HBox rentMenuHbox2 = makeHbox();
        HBox rentMenuHbox3 = makeHbox();

        Button  findMediaButton = new Button("find");
        Button rentMenuAddToCartButton = makeButton("Add To cart", "/home/hp/Desktop/phase2Pics/greenAdd.png");
        Button rentMenuProcessButton = makeButton("Process Cart", "/home/hp/Desktop/phase2Pics/process.png");
        Button rentMenuBackButton = makeButton("Back", "/home/hp/Desktop/phase2Pics/return.png");
        rentMenuBackButton.setOnAction(k -> {
            stage.setScene(mainScene);
        });

        Label rentMenuIdLabel = makeLabel();
        TextField rentMenuIdTextField = makeTextField();
        TextArea textArea = new TextArea();
        textArea.setMaxSize(600, 100);
        textArea.setPromptText("Needed Information about the Customer will be Displayed here....");

        Label rentMenuMediaCodeLabel = makeLabel();
        rentMenuMediaCodeLabel.setText("Media Code:");
        TextField rentMenuMediaCodeTextField = makeTextField();
        TextArea textArea1 = new TextArea();
        textArea1.setMaxSize(600, 100);
        textArea1.setPromptText("Needed Information about the chosen Media will be Displayed here....");

        Label rentMenuRentedDateLabel = makeLabel();
        rentMenuRentedDateLabel.setText("Rented Date:");
        TextField rentMenuRentedDateTextArea = makeTextField();

        rentMenuVbox.getChildren().addAll(rentMenuHbox,
                textArea,
                rentMenuHbox1,
                textArea1,


                rentMenuHbox2

        );

        Button findCustomerRentButton = new Button("find");

        rentMenuHbox.getChildren().addAll(rentMenuIdLabel, rentMenuIdTextField,findCustomerRentButton);
        rentMenuHbox1.getChildren().addAll(rentMenuMediaCodeLabel, rentMenuMediaCodeTextField,findMediaButton);

        //rentMenuHbox2.getChildren().addAll(rentMenuRentedDateLabel, rentMenuRentedDateTextArea);

        Button returnMediaNewButton = makeButton("Return media","/home/hp/Desktop/phase2Pics/media6.png");

        rentMenuHbox3.getChildren().addAll(rentMenuAddToCartButton, returnMediaNewButton,rentMenuProcessButton, rentMenuBackButton);
        rentMenuHbox3.setAlignment(Pos.CENTER);


        rentMenuVbox.setAlignment(Pos.CENTER);
        rentMenuVbox.setStyle("-fx-background-color: #383838");

        rentMenuBorderPane.setCenter(rentMenuVbox);
        rentMenuBorderPane.setBottom(rentMenuHbox3);
        rentMenuBorderPane.setStyle("-fx-background-color: #383838");


        rentMenuScene = new Scene(rentMenuBorderPane);

        rentButton.setOnAction(j -> {
            stage.setScene(rentMenuScene);
        });

        rentMenuAddToCartButton.setOnAction( bk -> {
            manager.addToCart(rentMenuIdTextField.getText(),rentMenuMediaCodeTextField.getText());

        });

        findCustomerRentButton.setOnAction(bk -> {

            for (int i = 0; i < MediaRentalManager.customerArrayList.size(); i++) {

                if (MediaRentalManager.customerArrayList.get(i).getId().equalsIgnoreCase(rentMenuIdTextField.getText())) {

                    textArea.setText(MediaRentalManager.customerArrayList.get(i).getInfo().replaceAll("\\$","\n"));

                    break;
                }
                else {
                    textArea.setText("Customer not found");
                }

            }

        });

        findMediaButton.setOnAction(bk ->{

            for (int i = 0; i < MediaRentalManager.mediaArrayList.size(); i++) {

                if (MediaRentalManager.mediaArrayList.get(i).getMediaCode().equalsIgnoreCase(rentMenuMediaCodeTextField.getText())) {

                    textArea1.setText(MediaRentalManager.mediaArrayList.get(i).getInfo().replaceAll("\\$","\n"));

                    break;
                }
                else {
                    textArea1.setText("Media not found");

                }

            }

        });

        rentMenuAddToCartButton.setOnAction(bk -> {

            manager.addToCart(rentMenuIdTextField.getText(),rentMenuMediaCodeTextField.getText());
            try {
                //used to check some errors in the past
                /*for (int z =0;z<MediaRentalManager.customerArrayList.size();z++) {
                    for(int w =0;w < MediaRentalManager.customerArrayList.get(z).cart.size();w++){

                    }
                }*/
                writeCustomer();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });
        rentMenuProcessButton.setOnAction(bk -> {

            String processTxt = manager.processRequests();
            System.out.println(processTxt);
            try {
                writeCustomer();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        //return media back-end:
        returnMediaNewButton.setOnAction(bk -> {

            boolean test = manager.returnMedia(rentMenuIdTextField.getText(),rentMenuMediaCodeTextField.getText());
            if (test){
                alertBox("Media not found in the rented list");
            }
            else {
                try {
                    addRentedToFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /*
                try {
                    writeCustomer();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    writeMedia();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    addCartToFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                */
            }

        });

        // ********************************************** stage setup******************************
        stage.setMaximized(true);
        stage.setScene(mainScene);
        stage.show();


        // stage.setFullScreen(true);


    }

    private Button makeButton(String name, String path) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        Image image = new Image(fileInputStream);
        ImageView imageView = new ImageView(image);


        return new Button(name, imageView);


    }


    private VBox makeComboBoxVbox() throws FileNotFoundException {

        VBox vBox = new VBox(100);
        vBox.setStyle("-fx-background-color: #383838");
        vBox.setAlignment(Pos.CENTER);
        ComboBox<String> mediaComboBox = new ComboBox<>();
        mediaComboBox.getItems().addAll("Movie", "Album", "Game");
        mediaComboBox.setPromptText("Please Choose the type of media");
        mediaComboBox.setMinSize(200, 50);


        vBox.getChildren().addAll(mediaComboBox);

        return vBox;

    }

    private Button makeReturnButton() throws FileNotFoundException {
        Button button;
        button = makeButton("return", "/home/hp/Desktop/phase2Pics/return.png");
        return button;
    }

    private Label makeLabel() {
        Label label = new Label("Customer ID:");
        label.setFont(new Font("Roboto", 20));
        label.setMinSize(100, 50);
        label.setTextFill(Color.WHITE);
        return label;

    }

    private TextField makeTextField() {
        TextField textField = new TextField();
        textField.setMaxSize(150, 25);

        return textField;
    }

    private HBox makeHbox() {
        HBox customerHbox = new HBox(20);
        customerHbox.setStyle("-fx-background-color: #383838");
        customerHbox.setAlignment(Pos.CENTER);

        return customerHbox;

    }

    private TextField makePlanTextField() {

        TextField addCustomerPlanTextField = makeTextField();
        addCustomerPlanTextField.setMinSize(200, 25);
        addCustomerPlanTextField.setDisable(true);

        return addCustomerPlanTextField;
    }


    /*
    *
    * reads every thing from every file
     */
    private static void getFromFile() throws IOException {

        String line;
        if (customerFile.exists()) {

            Scanner fileScanner = new Scanner(customerFile);
            while (fileScanner.hasNextLine()) {

                line = fileScanner.nextLine();
                String[] customerStrings = line.split("\\$");
                manager.addCustomer(customerStrings[0], customerStrings[1], customerStrings[2], customerStrings[3], customerStrings[4]);
            }
            fileScanner.close();
        }
        else {
            customerFile.createNewFile();
        }
        if (mediaFile.exists()) {

            Scanner fileScanner = new Scanner(mediaFile);
            // read from media file, then add them in the  data base -which is the arraylist -

            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                String[] mediaString = line.split("\\$");
                String mediaType = mediaString[0];      //first word in the line will be the kind of media
                if (mediaType.equalsIgnoreCase("Movie")) {          //Double.parseDouble( mediaString[3])
                    manager.addMovie(mediaString[1], mediaString[2], Integer.parseInt(mediaString[3]), mediaString[4]);
                } else if (mediaType.equalsIgnoreCase("Album")) {
                    manager.addAlbum(mediaString[1], mediaString[2], Integer.parseInt(mediaString[3]), mediaString[4], mediaString[5]);
                } else if (mediaType.equalsIgnoreCase("Game")) {
                    manager.addGame(mediaString[1], mediaString[2], Integer.parseInt(mediaString[3]), Double.parseDouble(mediaString[4]));

                }
            }
            fileScanner.close();
        }

        else {
            mediaFile.createNewFile();
        }

        if (cartFile.exists()){

            Scanner fileScanner = new Scanner(cartFile);
            String customerName;
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                try {

                    String[] cartString = line.split("\\$"); //splits the line into 2 elemrents in the array, first is the name of the customer who's renting, 2nd is the rest, and we will split t hem as well
                    customerName = cartString[0];

                    String[] mediaArray = cartString[1].split(":"); // splits the 2nd part
                    for (int i = 0; i < MediaRentalManager.customerArrayList.size(); i++) {     // goes customer after customer
                        for (int k = 0; k < mediaArray.length; k++) { // checks every media in his cart
                            if (MediaRentalManager.customerArrayList.get(i).getName().equalsIgnoreCase(customerName)) { // if customer exists
                                for (int j = 0; j < MediaRentalManager.mediaArrayList.size(); j++) {//checks all media in stock
                                    if (MediaRentalManager.mediaArrayList.get(j).getTitle().equalsIgnoreCase(mediaArray[k])) { // if this media exists
                                        MediaRentalManager.customerArrayList.get(i).cart.add(mediaArray[k]);// add
                                    }
                                }
                            }
                        }
                    }
                }catch (Exception e){   // when the cart is still empty, we will get array out of bound exception, and we cannot split it

                }
            }

            fileScanner.close();


        }

        else {
            cartFile.createNewFile();
        }

        if (rentedFile.exists()){

            Scanner fileScanner = new Scanner(rentedFile);
            String customerName;
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                try {
                    String[] cartString = line.split("\\$"); //id of customer comes first, and splited by a '$' other stuff seperated by ':'
                    customerName = cartString[0];
                    String[] mediaArray = cartString[1].split(":");//splits the 2nd part
                    for (int i = 0; i < MediaRentalManager.customerArrayList.size(); i++) {// customers
                        for (int k = 0; k < mediaArray.length; k++) { // items

                            if (MediaRentalManager.customerArrayList.get(i).getName().equalsIgnoreCase(customerName)) { // if the customer exists, as it is in the rented file:
                                for (int j = 0; j < MediaRentalManager.mediaArrayList.size(); j++) {
                                    if (MediaRentalManager.mediaArrayList.get(j).getTitle().equalsIgnoreCase(mediaArray[k])) { // this checks the items if they exist in the media list
                                        MediaRentalManager.customerArrayList.get(i).rentedList.add(mediaArray[k]);
                                    }
                                }
                            }
                        }
                    }
                }catch (Exception e){ // to prevent errors when file is empty

                }
            }


            fileScanner.close();


        }

        else {
            rentedFile.createNewFile();
        }
    }


    private static void addCartToFile() throws FileNotFoundException {
        PrintWriter cartWriter = new PrintWriter("cart.txt");
        int i =  0;

        for( i =0;i<MediaRentalManager.customerArrayList.size();i++){ // every customer created, his id is directly printed to the cart file and seperated by a '$'
                cartWriter.print(MediaRentalManager.customerArrayList.get(i).getId() +"$");
                Customer tmpCust = MediaRentalManager.customerArrayList.get(i); // that customer in i

                for (int z = 0; z < tmpCust.cart.size(); z++) {

                    cartWriter.append(tmpCust.cart.get(z) + ":"); // appends every thing in his cart file

                }
                cartWriter.println(); //new line for every customer
        }




        cartWriter.close();
    }


    private static void addRentedToFile() throws FileNotFoundException { // adds customer with his rented list to the file

        PrintWriter rentWriter = new PrintWriter("rented.txt");

        for( int i =0;i<MediaRentalManager.customerArrayList.size();i++){ // customers

            rentWriter.print(MediaRentalManager.customerArrayList.get(i).getId() +"$"); // same, every customer added, his id directly added to the cart
            Customer tmpCust = MediaRentalManager.customerArrayList.get(i);
            for (int z = 0; z < tmpCust.rentedList.size(); z++) {

                rentWriter.append(tmpCust.rentedList.get(z) + ":");

            }
            rentWriter.println();


        }
        rentWriter.close();
    }

   /* private static void addLimitedValueToFie() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(limitedValueFile);
        printWriter.println(MediaRentalManager.planLimit);

    }

    private static void getLimitedValueFromFile() throws FileNotFoundException {
        Scanner filescanner = new Scanner(limitedValueFile);
        MediaRentalManager.planLimit = Integer.parseInt(filescanner.next());

    }*/

    private static void deleteCustomer(String ID) throws FileNotFoundException {

        for (int i = 0; i < MediaRentalManager.customerArrayList.size(); i++) {
            if (MediaRentalManager.customerArrayList.get(i).getId().equalsIgnoreCase(ID)) {
                MediaRentalManager.customerArrayList.remove((MediaRentalManager.customerArrayList.get(i)));

            }

        }

    }

    private static String[] searchCustomer(String ID) {

        String[] arr = new String[4];
        for (int i = 0; i < MediaRentalManager.customerArrayList.size(); i++) {

            if (MediaRentalManager.customerArrayList.get(i).getId().equalsIgnoreCase(ID)) {

                arr[0] = MediaRentalManager.customerArrayList.get(i).getName();
                arr[1] = MediaRentalManager.customerArrayList.get(i).getMobileNumber();
                arr[2] = MediaRentalManager.customerArrayList.get(i).getAddress();
                arr[3] = MediaRentalManager.customerArrayList.get(i).getPlan();


            }

        }
        return arr;

    }

    private static void update(String ID, String name, String address, String mobile, String plan) throws FileNotFoundException {
        for (int i = 0; i < MediaRentalManager.customerArrayList.size(); i++) {

            if (MediaRentalManager.customerArrayList.get(i).getId().equalsIgnoreCase(ID)) {

                MediaRentalManager.customerArrayList.get(i).setName(name);
                MediaRentalManager.customerArrayList.get(i).setAddress(address);
                MediaRentalManager.customerArrayList.get(i).setMobileNumber(mobile);
                MediaRentalManager.customerArrayList.get(i).setPlan(plan);

                writeCustomer(); // updated the list, then rewrite what's inside the list in the files

            }


        }


    }

    private static void deleteMedia(String code) throws FileNotFoundException {
        boolean exists = false;
        for (int i = 0; i < MediaRentalManager.mediaArrayList.size(); i++) {

            if (MediaRentalManager.mediaArrayList.get(i).getMediaCode().equalsIgnoreCase(code)) {
                MediaRentalManager.mediaArrayList.remove((MediaRentalManager.mediaArrayList.get(i)));
                exists = true;

            }

        }
        if (!exists){
            alertBox("Media Doesn't exist ");
        }

    }

   /*private static String[] searchMedia(String code) {//test

        String[] arr = new String[4];
        for (int i = 0; i < MediaRentalManager.mediaArrayList.size(); i++) {

            if (MediaRentalManager.mediaArrayList.get(i).getMediaCode().equalsIgnoreCase(code)) {

               /*arr[0] = MediaRentalManager.mediaArrayList.get(i).getMediaCode();
                arr[1] = MediaRentalManager.mediaArrayList.get(i).get();
                arr[2] = MediaRentalManager.mediaArrayList.get(i).getMobileNumber();
                arr[3] = MediaRentalManager.mediaArrayList.get(i).getPlan();


            }

        }
        return arr;

    }*/

    // whenever we call it, we re-write from lists to the files, to update the changes
    private static void writeCustomer() throws FileNotFoundException {

        PrintWriter printWriter = new PrintWriter(customerFile);
        for (int j = 0; j < MediaRentalManager.customerArrayList.size(); j++) {

            printWriter.println(MediaRentalManager.customerArrayList.get(j).getInfo().toString());// prints all info of customer in customer file

        }

        addRentedToFile(); // re-writes to the rented file
        printWriter.close();
        addCartToFile();// re-writes to the cart file
    }

    // every time u call it, it  prints the content of media list
    private static void writeMedia() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(mediaFile);

        for (int j = 0; j < MediaRentalManager.mediaArrayList.size(); j++) {
            printWriter.println(MediaRentalManager.mediaArrayList.get(j).getInfo());


        }
        printWriter.close();

    }
        //makes  an alert message
    private static void alertBox(String s){
        Stage alertStage = new Stage();

        Label label = new Label(s);

        VBox vBox = new VBox(10);
        vBox.getChildren().add(label);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox);
        alertStage.setScene(scene);;
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setTitle("Alert");
        alertStage.setMinWidth(250);
        alertStage.show();
    }






}











