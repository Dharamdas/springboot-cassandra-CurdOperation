package com.test.address.services;

import com.test.address.models.Address;
import com.test.address.repositories.AddressRepository;
import com.test.address.repositories.AddressRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressRepositoryImpl addressRepositoryImpl;

    AddressServiceImpl(){

    }

    public UUID createAddress(Address address){
        address.setAddressId(UUID.randomUUID());
        addressRepository.insert(address);
        return address.getAddressId();
    }

    @Override
    public Address getAddress(UUID addressId,Integer guestid) {

        return addressRepository.findOne(BasicMapId.id("addressId",addressId).with("guestId",guestid));
    }

    @Override
    public List<Address> getAddressByGuestId(Integer guestid) {
        try {
            log.info("Fetching All the Addresses the for member {} in new cassandra",
                     guestid);
            return addressRepositoryImpl.getAddressByGuestId(guestid);
        }catch (Exception e)
        {
            log.info("Fetching All the Addresses Failed the for member {} in new cassandra",
                    guestid,e);
        }
        return null;
    }

    @Override
    public List<Address> findAll() {
        return null;
    }

}
