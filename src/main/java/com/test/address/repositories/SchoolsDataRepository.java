package com.test.address.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.test.address.models.SchoolsData;

public interface SchoolsDataRepository extends CassandraRepository<SchoolsData> {

}
