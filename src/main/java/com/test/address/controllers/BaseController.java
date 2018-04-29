package com.test.address.controllers;

import com.test.address.exceptions.CustomResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BaseController {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final static ObjectMapper objectMapper = new ObjectMapper();

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleControllerException(Exception ex) {
		LOGGER.info(ex.getMessage());
		throw new CustomResourceNotFoundException(ex.getMessage(), ex);
	}

	public ObjectNode createObjectNode() {
		return objectMapper.createObjectNode();
	}
}
