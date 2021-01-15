package com.iq.code.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iq.code.ScheduleBaseTest;
import com.iq.code.resources.SchedulerResource;

@TestInstance(Lifecycle.PER_CLASS)
public class SchedulerControllerTests extends ScheduleBaseTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testSchedule_success() throws Exception {
		SchedulerResource resource = new SchedulerResource();
		resource.setDeliveryTime("2022-01-15 23:02");
		resource.setMessage("Hello World!");
		mockMvc.perform(post("/schedule").contentType(MediaType.APPLICATION_JSON).content(asJsonString(resource)))
				.andExpect(status().isAccepted()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	@Test
	public void testSchedule_bad_request() throws Exception {
		SchedulerResource resource = new SchedulerResource();
		resource.setMessage("Hello World!");
		mockMvc.perform(post("/schedule").contentType(MediaType.APPLICATION_JSON).content(asJsonString(resource)))
				.andExpect(status().isBadRequest());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
