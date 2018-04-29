package com.test.address.controllers;


import com.test.address.models.Address;
import com.test.address.services.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressServiceImpl addressServiceImpl;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody Address address) {
        UUID uuid = addressServiceImpl.createAddress(address);
        return new ResponseEntity<>(uuid, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get/guestId/{guestId}/addressId/{addressId}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<Object> getAddress(@PathVariable String guestId,@PathVariable UUID addressId)
    {
        System.out.println("guestId "+guestId +" & AddressId "+addressId);
        Address address = addressServiceImpl.getAddress(addressId,Integer.parseInt(guestId));
        return new ResponseEntity<>(address,HttpStatus.OK);
    }

    @RequestMapping(value = "/get/guestId/{guestId}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<Object> getAddressById(@PathVariable String guestId)
    {
        System.out.println("guestId "+guestId);
        List<Address> address = addressServiceImpl.getAddressByGuestId(Integer.parseInt(guestId));
        return new ResponseEntity<>(address,HttpStatus.OK);
    }


}
