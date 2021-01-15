package com.iq.code.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.iq.code.exception.InvalidDeliveryTimeException;
import com.iq.code.model.Scheduler;
import com.iq.code.repository.SchedulerRepository;
import com.iq.code.resources.SchedulerResource;

@Service
@Transactional
public class ScheduleTaskService {

	Logger logger = Logger.getLogger(ScheduleTaskService.class);

	// Task Scheduler
	@Autowired
	private TaskScheduler taskScheduler;

	@Autowired
	private SchedulerRepository schedulerRepo;

	// A map for keeping scheduled tasks
	Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

	/**
	 * Schedule Task to be executed.
	 * 
	 * @param id
	 * @param task
	 * @param cron
	 */
	private void addTaskToScheduler(Scheduler scheduler) {
		Long id = scheduler.getId();
		if (jobsMap.containsKey(id)) {
			this.removeTaskFromScheduler(id);
		}
		ScheduledFuture<?> scheduledTask = taskScheduler.schedule(new MessageThread(scheduler.getMessage()),
				scheduler.getDeliveryTime());
		jobsMap.put(id, scheduledTask);
	}

	/**
	 * Remove scheduled task
	 * 
	 * @param id
	 */
	private void removeTaskFromScheduler(Long id) {
		ScheduledFuture<?> scheduledTask = jobsMap.get(id);
		if (scheduledTask != null) {
			scheduledTask.cancel(true);
			jobsMap.put(id, null);
		}
	}

	/**
	 * Save/Update scheduler resource.
	 * 
	 * @param resource
	 * @return
	 */
	public SchedulerResource saveTask(SchedulerResource resource) {
		Scheduler scheduler = new Scheduler();
		LocalDateTime dateTime;
		BeanUtils.copyProperties(resource, scheduler);
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			dateTime = LocalDateTime.parse(resource.getDeliveryTime(), formatter);
		} catch (Exception e) {
			throw new InvalidDeliveryTimeException("Could not parse delivery date :" + resource.getDeliveryTime()
					+ " : try format : yyyy-MM-dd HH:mm");
		}

		scheduler.setDeliveryTime(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
		if (scheduler.getDeliveryTime().before(new Date())) {
			throw new InvalidDeliveryTimeException("Scheduled time should be in future");
		}

		// Saving entity in the DB
		schedulerRepo.save(scheduler);

		// Adding scheduler to the list.
		this.addTaskToScheduler(scheduler);

		BeanUtils.copyProperties(scheduler, resource);
		return resource;
	}

	/**
	 * Application ready event listener Load all schedulers when the application
	 * starts.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void doAfterStartup() {
		reInitAllSchedulerFromDB();
	}

	private void reInitAllSchedulerFromDB() {
		List<Scheduler> allSchedulers = schedulerRepo.findAll();
		allSchedulers.forEach(u -> {
			if (u.getDeliveryTime().before(new Date())) {
				schedulerRepo.delete(u);
			} else {
				this.addTaskToScheduler(u);
			}

		});
	}

}
