package com.iq.code.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller class to checking the health of an API
 * @author arunkumar.pushparaj
 *
 */
@RestController
public class HealthController {

	@GetMapping("/")
	@ApiOperation(value = "health", nickname = "Health Check")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Ineternal server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Up", response = String.class) })
	public String index() {
		return "Up";
	}

}
