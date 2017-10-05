package com.carDealer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Car {
	// JavaFX tries to enable the MVC-Pattern.
	//The Model should be created with Properties to take advantage of Binding
	private SimpleStringProperty carVIN, carMake, carModel, makeYear, carColor ;
	private Image picture;

public Car (String carVIN, String carMake, String carModel,String makeYear,Color carColor)
{
	 this.carVIN = new SimpleStringProperty(carVIN);
	 this.carMake = new SimpleStringProperty(carMake);
	this.carModel = new SimpleStringProperty(carModel);
	 this.makeYear=new SimpleStringProperty(makeYear);
	 this.carColor=new SimpleStringProperty(carColor.toString());
	 picture = new Image("cars.jpg");
	//this.color=color;
}
public void setCarVIN(String carVIN) {
    this.carVIN = new SimpleStringProperty(carVIN);
}

public String getCarVIN() {
    return carVIN.get();
}
public void setCarModel(String carModel) {
    this.carModel = new SimpleStringProperty(carModel);
}

public String getCarModel() {
    return carModel.get();
}
public void setCarMake(String carMake) {
    this.carMake = new SimpleStringProperty(carMake);
}

public String getCarMake() {
    return carMake.get();
}

public void setMakeYear(String makeYear) {
    this.makeYear = new SimpleStringProperty(makeYear);
}

public String getMakeYear() {
    return makeYear.get();
}

public String getCarColor() {
    return carColor.get();
}

public void setCarColor(String carColor) {
    this.carColor = new SimpleStringProperty(carColor);
}


public Image getImage()
{
    return picture;
}

public void setImage(Image newPicture)
{
    this.picture = newPicture;
}

public String toString()
{
    return String.format("%s %s %s %s %s", carVIN, carMake, carModel, makeYear, carColor);
}

}
