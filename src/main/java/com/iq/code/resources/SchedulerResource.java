package com.iq.code.resources;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchedulerResource {

	private Long id;

	@NotNull
	@NotEmpty
	private String message;

	@NotNull
	@NotEmpty
	private String deliveryTime;
}
