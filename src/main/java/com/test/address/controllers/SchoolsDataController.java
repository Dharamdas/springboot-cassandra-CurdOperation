package com.test.address.controllers;

import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.address.models.SchoolsData;
import com.test.address.services.SchoolsDataService;

@RestController
@RequestMapping("/api")
public class SchoolsDataController extends BaseController {

	@Autowired
	private SchoolsDataService service;

	@RequestMapping(value = "/student_data", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> save(@RequestBody SchoolsData data) {
		SchoolsData one = service.save(data);
		return new ResponseEntity<>(one, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/student_data", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/student_data/id/{id}/user/{user}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> findOne(@PathVariable UUID id, @PathVariable String user) {
		SchoolsData studentData = service.findOne(id,user);
		return new ResponseEntity<>(studentData == null ? Collections.EMPTY_MAP : studentData, HttpStatus.OK);
	}

	@RequestMapping(value = "/student_data/id/{id}/user/{user}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable UUID id, @PathVariable String user) {
		service.delete(id, user);
		return new ResponseEntity<>(Collections.EMPTY_MAP, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student_data/deleteAll", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> deleteAll() {
		service.deleteAll();
		return new ResponseEntity<>(Collections.EMPTY_MAP, HttpStatus.OK);
	}

	@RequestMapping(value = "/student_data/count", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> count() {
		ObjectNode node = createObjectNode();
		node.put("count", service.count());
		return new ResponseEntity<>(node, HttpStatus.OK);
	}
}
