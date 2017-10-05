package com.carDealer.model;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private SimpleStringProperty firstName, lastName, address, phone, creditHistory;


    public Customer(String firstName, String lastName, String creditHistory,String address, String phone) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.creditHistory = new SimpleStringProperty(creditHistory);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getCreditHistory() {
        return creditHistory.get();
    }

    public void setCreditHistory(String creditHistory) {
        this.creditHistory = new SimpleStringProperty(creditHistory);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone = new SimpleStringProperty(phone);
    }


    public String toString()
    {
        return String.format("%s %s", firstName, lastName);
    }
}
