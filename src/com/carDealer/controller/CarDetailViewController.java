package com.carDealer.controller;

import java.io.IOException;

import com.carDealer.model.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class CarDetailViewController {
	 private Car selectedCar;
	 @FXML private Label vinLabel;
	    @FXML private Label modelLabel;
	    @FXML private Label makeLabel;
	    @FXML private Label makeYearLabel;
	    @FXML private Label colorLabel;
	    @FXML private ImageView picture;


	    //This method is used to display the detailed car information
	    public void initData(Car car)
	    {
	    	selectedCar = car;
	        vinLabel.setText(selectedCar.getCarVIN());
	        makeLabel.setText(selectedCar.getCarMake());
	        modelLabel.setText(selectedCar.getCarModel());
	        makeYearLabel.setText(selectedCar.getMakeYear());
	        //colorLabel.setValue(selectedCar.getCarColor());
	        picture.setImage(selectedCar.getImage());
	    }


	// This method will redirect the scene into the customer TableView
	    public void changeSceneToCarMainView(ActionEvent event) throws IOException
	    {
	        Parent tableViewParent = FXMLLoader.load(getClass().getResource("com/carDealer/view/CarMainView.fxml"));
	        Scene tableViewScene = new Scene(tableViewParent);
	        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	        window.setScene(tableViewScene);
	        window.show();
	    }



	}