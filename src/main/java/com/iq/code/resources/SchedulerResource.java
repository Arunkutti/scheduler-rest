package com.iq.code.resources;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Scheduler resource class for front end interaction.Constructed using lombok
 * @author arunkumar.pushparaj
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel
public class SchedulerResource {

	@ApiModelProperty(position = 1, required = true, value = "1")
	private Long id;

	@NotNull
	@NotEmpty
	@ApiModelProperty(position = 2, required = true, value = "Hello World")
	private String message;

	@NotNull
	@NotEmpty
	@ApiModelProperty(position = 3, required = true, value = "2022-01-15 23:02")
	private String deliveryTime;
}
