package com.test.address.models;

public class Tables {

    String address="CREATE TABLE IF NOT EXISTS address (guestid int,addressid uuid,city text,country text,pincode int,locality text,creation_date timestamp, PRIMARY KEY (guestId,addressid));";
}
