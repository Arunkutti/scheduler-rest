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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for handling scheduler endpoints
 * @author arunkumar.pushparaj
 *
 */
@RestController
public class SchedulerRestController {

	@Autowired
	private ScheduleTaskService schedulerService;

	/**
	 * Endpoint to save the schulder with message and delivery time.
	 * @param resource - contains delivery time and message
	 * @return - Saved scheduler reposne
	 */
	@PostMapping(value = "/schedule")
	@ApiOperation(value = "schedule", nickname = "schedule")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 202, message = "Successful", response = SchedulerResource.class) })
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
