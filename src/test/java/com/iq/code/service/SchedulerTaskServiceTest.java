package com.iq.code.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.scheduling.TaskScheduler;

import com.iq.code.ScheduleBaseTest;
import com.iq.code.exception.InvalidDeliveryTimeException;
import com.iq.code.model.Scheduler;
import com.iq.code.repository.SchedulerRepository;
import com.iq.code.resources.SchedulerResource;

public class SchedulerTaskServiceTest extends ScheduleBaseTest {

	@Mock
	SchedulerRepository schedulerRepository;

	@Mock
	TaskScheduler taskScheduler;

	@InjectMocks
	ScheduleTaskService schedulerTaskService;

	@Test
	public void shouldSaveTask_success() {
		// Given
		when(schedulerRepository.save(Mockito.any(Scheduler.class))).then(new Answer<Scheduler>() {
			Long sequence = 1L;

			@Override
			public Scheduler answer(InvocationOnMock invocation) throws Throwable {
				Scheduler scheduler = (Scheduler) invocation.getArgument(0);
				scheduler.setId(sequence++);
				return scheduler;
			}
		});
		SchedulerResource request = new SchedulerResource();
		request.setDeliveryTime("2022-01-15 23:02");
		request.setMessage("Hello World");
		SchedulerResource response = schedulerTaskService.saveTask(request);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(1L, response.getId());

	}

	@Test
	public void shouldSaveTask_fail() {
		SchedulerResource request = new SchedulerResource();
		request.setMessage("Hello World");
		Assertions.assertThrows(InvalidDeliveryTimeException.class, () -> {
			schedulerTaskService.saveTask(request);
		});
	}

}
