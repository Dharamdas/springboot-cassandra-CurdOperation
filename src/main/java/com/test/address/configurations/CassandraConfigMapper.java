package com.test.address.configurations;

import com.datastax.driver.core.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.List;

@Repository
@Slf4j
public class CassandraConfigMapper {

    private static final Logger logger = LoggerFactory.getLogger(CassandraConfig.class);

    @Value("${cassandra.host}")
    private String host;

    @Value("${cassandra.port}")
    private String port;

    @Value("${cassandra.cluster.name}")
    private String clusterName;

    @Value("${cassandra.cluster.username}")
    private String userName;

    @Value("${cassandra.cluster.password}")
    private String password;

    private static Cluster cluster;
    private static Session session;
    private static ConsistencyLevel consistencyLevel;

    CassandraConfigMapper(){
        System.out.println("CassandraConfigMapper initiated");
    }


    @PostConstruct
    public void connectToCluster() throws Exception {

        try{
            PoolingOptions poolingOptions = new PoolingOptions();
            poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL,10);
            poolingOptions.setPoolTimeoutMillis(5000);
            poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL,10);
            PlainTextAuthProvider authProvider = new PlainTextAuthProvider(userName, password);
            cluster = Cluster.builder()
                    .addContactPointsWithPorts(getHostIntetSocketAddressList(host+":"+port))
                    .withClusterName(clusterName).withAuthProvider(authProvider)
                    .withPoolingOptions(poolingOptions)
                    .build();

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    private List < InetSocketAddress > getHostIntetSocketAddressList(String hostList) {
        List<InetSocketAddress> cassandraHosts = Lists.newArrayList();
       // for (String host: hostList.split(",")) {
            InetSocketAddress socketAddress = new InetSocketAddress(host, Integer.valueOf(port));
            cassandraHosts.add(socketAddress);
       // }
        return cassandraHosts;
    }
  /*  public ConsistencyLevel getConsistencyLevel() {
        Optional< ConsistencyLevel > consistencyLevelOptional = Optional.of(consistencyLevel);
        if (consistencyLevelOptional.isPresent()) {
            return consistencyLevel;
        }
        return null;
    }*/

    public Session getSession() {
        if (cluster == null) {
            throw new RuntimeException("Cassandra Cluster in NULL");
        }
        if (session == null) {
            session = cluster.connect();
        }
        return session;
    }

    public void close() {
        if (session != null) {
            logger.debug("Closing Session....");
            try {
                session.close();
            } catch (Exception e) {
                logger.error("Error while closing the Session ...");
            }
        }
        if (cluster != null) {
            logger.debug("Closing Cluster....");
            try {
                cluster.close();
            } catch (Exception e) {
                logger.error("Error while closing the Cluster ...");
            }
        }
    }

}
