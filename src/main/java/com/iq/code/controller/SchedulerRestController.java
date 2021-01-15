package com.iq.code.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iq.code.exception.InvalidDeliveryTimeException;
import com.iq.code.resources.SchedulerResource;
import com.iq.code.service.ScheduleTaskService;

@RestController
public class SchedulerRestController {

	@Autowired
	private ScheduleTaskService schedulerService;

	@PostMapping(value = "/schedule", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> index(@RequestBody @Valid SchedulerResource resource) {
		try {
			SchedulerResource response = schedulerService.saveTask(resource);
			return new ResponseEntity<Object>(response, HttpStatus.ACCEPTED);
		} catch (InvalidDeliveryTimeException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
