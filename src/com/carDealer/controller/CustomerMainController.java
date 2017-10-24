package com.carDealer.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.carDealer.model.Customer;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import com.carDealer.model.*;
import application.CarDealerDBConfig;
public class CustomerMainController {

    //configure the customer of car  dealer table view
    @FXML private TableView<Customer> customerTableView;
    @FXML private TableColumn<Customer, String> firstNameColumn;
    @FXML private TableColumn<Customer, String> lastNameColumn;
    @FXML private TableColumn<Customer, String> creditHistoryColumn;
    @FXML private TableColumn<Customer, String> addressColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;

    //These instance variables are used to create new customer objects
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField creditHistoryTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField phoneTextField;

    @FXML private Button detailedCustomerViewButton,deleteCustomerButton;


    //The following method is automatically invoked by the FXML loader,
    @FXML
    public void initialize() {
        //set up the columns in the table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        creditHistoryColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("creditHistory"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        //static data, will replace with the database record later
        customerTableView.setItems(getCustomerList());
        //Update the table to allow for the first and last name fields to be editable
        customerTableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //This will allow the table to select multiple rows at once
        customerTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //Disable the detailed Customer view button and the delete button until a row is selected
        this.detailedCustomerViewButton.setDisable(true);
        this.deleteCustomerButton.setDisable(true);
    }


// This method removes one or more selected row(s) from the TableView
    public void deleteButtonPushed()
    {
        ObservableList<Customer> selectedRows, allCustomer;
        allCustomer = customerTableView.getItems();


        //this gives us the rows that were selected
        selectedRows = customerTableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Customer objects from the table
        for (Customer customer: selectedRows)
        {
        	allCustomer.remove(customer);
        	String query = "delete from customer where firstName = ? and lastName = ?";

        	try(
        	    Connection conn = CarDealerDBConfig.getConnection();
        	    PreparedStatement updateprofile = conn.prepareStatement(query);
        	){
        	updateprofile.setString(1, customer.getFirstName());
        	updateprofile.setString(2, customer.getLastName());
        	    updateprofile.executeUpdate();


        	} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);

    		}


        }


    }
  // This method creates a new Customer object and adds it to the table view

    public void newCustomerButtonPushed()
    {

        Customer newCustomer = new Customer(firstNameTextField.getText(),
                                      lastNameTextField.getText(),
                                      creditHistoryTextField.getText(),
                                      addressTextField.getText(),
                                      phoneTextField.getText());

        System.out.println(newCustomer.toString());

        //Get all the items from the table as a list, then add the new Customer to
        //the list
        customerTableView.getItems().add(newCustomer);

        String query = "insert into customer " + "(firstName, lastName, creditHistory, address,phoneNumber)"
				+ "values(?,?,?,?,?)";

		try (Connection conn = CarDealerDBConfig.getConnection();
				PreparedStatement insertprofile = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

			insertprofile.setString(1, newCustomer.getFirstName());
			insertprofile.setString(2, newCustomer.getLastName());
			insertprofile.setString(3, newCustomer.getCreditHistory());
			insertprofile.setString(4, newCustomer.getAddress());
			insertprofile.setString(5, newCustomer.getPhone());


			// get the number of return rows
			int affectedRow = insertprofile.executeUpdate();

			if (affectedRow == 1) {
				System.out.println("Operation is successful");
				firstNameTextField.setText("");
				lastNameTextField.setText("");
				//keys = insertprofile.getGeneratedKeys();

			}

		} catch (Exception e) {
			System.out.println("Status: operation failed due to "+e);

		}

		}


 // ObservableList: A list that enables listeners to track changes when they occur
 // The following  method will return an ObservableList of  object


        public ObservableList<Customer>  getCustomerList()
        {
            ObservableList<Customer> customer = FXCollections.observableArrayList();
             //customer.add(new Customer("Cromer","Dom","Very good", "3003 Lake Union", "770-123-3434"));
            customer.add(new Customer("Job.","Tom","Very good","82 College Circle", "770-455-5482"));

            String SQLQuery = "select firstName, lastName, creditHistory, address,phoneNumber from customer";
    		ResultSet rs = null;

    		try(
    				Connection conn = CarDealerDBConfig.getConnection();
    				PreparedStatement displayprofile = conn.prepareStatement(SQLQuery);
    		){
    			//displayprofile.setInt(1, cutomerId);
    			rs = displayprofile.executeQuery();

    			// check to see if receiving any data
    			while (rs.next()){
    				customer.add(new Customer(rs.getString("firstName").toString(),rs.getString("lastName").toString(),rs.getString("creditHistory").toString(),rs.getString("address").toString(),rs.getString("phoneNumber").toString()));

    			}
    		}catch(SQLException ex){
    			CarDealerDBConfig.displayException(ex);
    			return null;
    		}finally{
    			if(rs != null){
    				//rs.close();
    			}
    		}
            return customer;
        }
 // Method used to enable the detailed view button on mouse click event
    public void mouseClickedOnTableView()
    {
        this.detailedCustomerViewButton.setDisable(false);
        this.deleteCustomerButton.setDisable(false);
    }

    public void changeSceneToCustomerDetailedView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../../com/carDealer/view/CustomerDetailedView.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        //access the controller and calls a method
        CustomerDetailedController controller = loader.getController();
        controller.initData(customerTableView.getSelectionModel().getSelectedItem());
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
