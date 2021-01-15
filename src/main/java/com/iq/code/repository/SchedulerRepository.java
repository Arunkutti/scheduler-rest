package com.iq.code.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iq.code.model.Scheduler;

public interface SchedulerRepository extends JpaRepository<Scheduler, Serializable> {

}
