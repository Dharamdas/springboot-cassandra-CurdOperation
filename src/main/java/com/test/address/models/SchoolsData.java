package com.test.address.models;

import java.util.Date;
import java.util.UUID;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.support.BasicMapId;

import com.datastax.driver.core.utils.UUIDs;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(value = "schools")
public class SchoolsData {

	@PrimaryKeyColumn(name = "user", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String user;
	@PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private UUID id = UUIDs.timeBased();
	@Column(value = "creation_date")
	private Date creation_date = new java.util.Date();
	@Column(value = "greet")
	private String greet;

	public SchoolsData() {
	};

	public SchoolsData(String user, UUID id, Date creation_date, String greet) {
		this.user = user;
		this.id = id;
		this.creation_date = creation_date;
		this.greet = greet;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getGreet() {
		return greet;
	}

	public void setGreet(String greet) {
		this.greet = greet;
	}

	@JsonIgnore
	public MapId getMapId() {
		return BasicMapId.id("user", user).with("id", id);
	}
}