package com.carDealer.controller;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.carDealer.model.Car;
import com.carDealer.model.Customer;
import javafx.scene.paint.Color;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
public class CarMainController {
	  //configure the car of car  dealer table view
    @FXML private TableView<Car> carTableView;
    @FXML private TableColumn<Car, String> vinColumn;
    @FXML private TableColumn<Car, String> makeColumn;
    @FXML private TableColumn<Car, String> modelColumn;
    @FXML private TableColumn<Car, String> yearColumn;
    @FXML private TableColumn<Car, String> colorColumn;
   // @FXML private TableColumn<Car, String> colorColumn;

    //These instance variables are used to create new car objects
    @FXML private TextField vinTextField;
    @FXML private TextField makeTextField;
    @FXML private TextField modelTextField;
    @FXML private TextField yearTextField;
    @FXML private ColorPicker colorTextField;
    @FXML private Button detailedCustomerViewButton;
    //@FXML private ColorPicker colorTextField;

public void initialize() {
    //set up the columns in the car view table
    vinColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("carVIN"));
    makeColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("carMake"));
    modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("carModel"));
    yearColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("makeYear"));
    colorColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("carColor"));
    //static data, we will replace with the database record later

    carTableView.setItems(getCarList());
    System.out.println(getCarList().toString());

    //Update the table to allow for the car information
    //to be editable
    carTableView.setEditable(true);
    vinColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    makeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    yearColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    //colorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    //This will allow the table to select multiple rows at once
    carTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    //Disable the detailed Car view button until a row is selected
   // this.detailedCustomerViewButton.setDisable(true);
}
//This method will remove the selected row(s) from the table

public void deleteButtonPushed()
{
    ObservableList<Car> selectedRows, allCar;
    allCar = carTableView.getItems();

    //this gives us the rows that were selected
    selectedRows = carTableView.getSelectionModel().getSelectedItems();

    //loop over the selected rows and remove the car objects from the table
    for (Car car: selectedRows)
    {
    	allCar.remove(car);
    }
}
//  This method is used to  create a new car object and add it to the table

public void newCarButtonPushed()
{
    Car newCar = new Car(vinTextField.getText(),
    		                           makeTextField.getText(),
    		                          modelTextField.getText(),
                                  yearTextField.getText(),
                                  colorTextField.getValue());

    //Get all the items from the table as a list, then add the new Car to
    //the list
    System.out.println(newCar.toString());
    carTableView.getItems().add(newCar);
    // The following will be used to clear the TextField after adding the data into the TableView
    vinTextField.setText("");
}

// ObservableList: A list that enables listeners to track changes when they occur
// The following  method will return an ObservableList of  objects

public ObservableList<Car>  getCarList()
{
	ColorPicker cp=new ColorPicker();
    ObservableList<Car> car = FXCollections.observableArrayList();
     car.add(new Car("tamirat","abegaz","ung","2333",cp.getValue()));
    car.add(new Car("shean","dagn","delta","2211", cp.getValue()));
    return car;
}
public void changeSceneToCarDetailedView(ActionEvent event) throws IOException
{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../../../com/carDealer/view/CarDetailedView.fxml"));
    Parent tableViewParent = loader.load();
    Scene tableViewScene = new Scene(tableViewParent);
    //access the controller and calls a method
    CarDetailViewController controller = loader.getController();
    controller.initData(carTableView.getSelectionModel().getSelectedItem());
    //This line gets the Stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    window.setScene(tableViewScene);
    window.show();
}

}
