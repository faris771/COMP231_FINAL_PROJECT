/*
 *   Author: Faris Abufarha
 *   ID: 1200546
 *
 *
 *   Note: i use Linux, so if u face any problem, put it in consideration please
 */

package com.example.phase2;

import java.util.ArrayList;

public class Customer implements Comparable<Customer> {
    //fx update
    private String id = "";
    private String name="";
    private String address = "";
    private String plan = "";
    private String mobileNumber = "";


    // to ArrayLists represent 2 databases one for wanted to be rented  items, the other for already rented items
    protected ArrayList<String> rentedList = new ArrayList<>();
    protected ArrayList<String> cart = new ArrayList<>();

    public Customer() {

    }

    // FX EDIT: ID, MOBILE
    public Customer(String id,String name,String mobileNumber, String address, String plan) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.address = address;
        this.plan = plan;
    }


    public String getName() {
        return name;
    }


    public String getPlan() {
        return plan;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public  String getInfo (){

        return this.getId() +"$" + this.getName() + "$" +this.getMobileNumber()+  "$" + this.getAddress() +"$"  + this.getPlan();

    }


    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", plan='" + plan + '\'' +
                ", rentedList=" + rentedList +
                ", cart" + cart+" ";
    }
    // for later use, in order to sort by customers names
    @Override
    public int compareTo(Customer customer) {

        return this.getName().compareTo(customer.getName());

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
