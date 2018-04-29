package com.test.address.models;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

@com.datastax.driver.mapping.annotations.Table(keyspace = "test01", name = "address")
public class Address {

    @PrimaryKeyColumn(name = "guestId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer guestId;

    @PrimaryKeyColumn(name = "addressId", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID addressId;

    @Column(value="city")
    private String city;

    @Column(value = "country")
    private String country;

    @Column(value = "pinCode")
    private String pinCode;

    @Column(value = "locality")
    private String locality;

    @Column(value = "creation_date")
    private Date creation_date = new java.util.Date();

    public Integer getGuestId() {
        return guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}
