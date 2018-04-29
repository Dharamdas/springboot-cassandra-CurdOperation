package com.test.address.services;

import com.test.address.models.Address;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface AddressService {

    public UUID createAddress(Address address);
    public Address getAddress(UUID addressId,Integer guestid);
    public List<Address> getAddressByGuestId(Integer guestid);
    public List<Address> findAll();
}
