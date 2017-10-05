package com.carDealer.controller;

import java.io.IOException;

import com.carDealer.model.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class CustomerDetailedController {

    private Customer selectedCustomer;

    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label addressLabel;
    @FXML private Label creditHistoryLabel;
    @FXML private Label phoneLabel;

//This method is used to display the detailed customer information
    public void initData(Customer customer)
    {
        selectedCustomer = customer;
        firstNameLabel.setText(selectedCustomer.getFirstName());
        lastNameLabel.setText(selectedCustomer.getLastName());
        creditHistoryLabel.setText(selectedCustomer.getCreditHistory());
        addressLabel.setText(selectedCustomer.getAddress());
        phoneLabel.setText(selectedCustomer.getPhone());
      }


// This method will redirect the scene into the customer TableView
    public void changeSceneToCustomerMainView(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("com/carDealer/view/CustomerMainView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }



}
