package com.test.address.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${cassandra.host}")
	private String CASSANDRA_CONTACTPOINT;

	@Value("${cassandra.port}")
	private Integer CASSANDRA_PORT;

	@Value("${cassandra.keyspace.name}")
	private String CASSANDRA_KEYSPACE;
	
	@Value("${cassandra.cluster.username}")
	private String CASSANDRA_USERNAME;
	
	@Value("${cassandra.cluster.password}")
	private String CASSANDRA_PASSWORD;

	@Override
	protected String getKeyspaceName() {
		return CASSANDRA_KEYSPACE;
	}

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(CASSANDRA_CONTACTPOINT);
		cluster.setPort(CASSANDRA_PORT);
		cluster.setUsername(CASSANDRA_USERNAME);
		cluster.setPassword(CASSANDRA_PASSWORD);
		return cluster;
	}

	@Bean
	public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
		return new BasicCassandraMappingContext();
	}
}
