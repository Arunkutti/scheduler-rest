package com.iq.code.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Scheduler Entity.Constructed using lombok
 * @author arunkumar.pushparaj
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Scheduler {

	@Id
	@GeneratedValue
	private Long id;

	private String message;

	private Date deliveryTime;

}
