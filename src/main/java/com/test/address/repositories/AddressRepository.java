package com.test.address.repositories;

import com.test.address.models.Address;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;

@Service
public interface AddressRepository extends CassandraRepository<Address> {

}
