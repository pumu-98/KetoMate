package com.example.ketomate;

public class ConfirmedOrderDetails {

    String id,FirstName,LastName,Mobile,Address,OrderDate,Time;

    public ConfirmedOrderDetails() {
    }

    public ConfirmedOrderDetails(String id, String firstName, String lastName, String mobile, String address, String orderDate, String time) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        Mobile = mobile;
        Address = address;
        OrderDate = orderDate;
        Time = time;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getAddress() {
        return Address;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public String getTime() {
        return Time;
    }
}
