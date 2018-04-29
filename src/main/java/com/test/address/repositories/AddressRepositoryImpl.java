package com.test.address.repositories;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.test.address.configurations.CassandraConfigMapper;
import com.test.address.configurations.CassandraStatementExecutor;
import com.test.address.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

@Service
public class AddressRepositoryImpl {

    @Autowired
    private CassandraConfigMapper cassandraConfigMapper;

    @Value("${cassandra.keyspace.name}")
    private String keyspaceName;
    private Session session;
    private MappingManager manager;
    private Mapper<Address> mapper;

 /*
    AddressRepositoryImpl(){
        try {
            session = cassandraConfigMapper.getSession();
            manager = new MappingManager(session);
            mapper = manager.mapper(Address.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initiate AddressRepositoryImpl", e);
        }
    }*/

    @PostConstruct
    public void init() {
        try {
            session = cassandraConfigMapper.getSession();
            manager = new MappingManager(session);
            mapper = manager.mapper(Address.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initiate AddressRepositoryImpl", e);
        }
    }


    public List< Address > getAddressByGuestId(Integer guestid) throws Exception {
        Result< Address > result = null;
        List<Address> addressList = new ArrayList<Address>();
        Statement statement = QueryBuilder
                .select()
                .from(keyspaceName, "address")
                .where(eq("guestid", guestid));
        // statement.setConsistencyLevel(cassandraConnector.getConsistencyLevel());
        try {
            System.out.println("session object: "+session);
            ResultSet resultSet = session.execute(statement);
            result = mapper.map(resultSet);
            result.forEach(e->addressList.add(e));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to search Student for studentId :" + guestid, e);
        }
        return addressList;
    }



}
