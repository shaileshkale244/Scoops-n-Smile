package com.app.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="deliveries")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="delivery_id")
	private Long id;
	@OneToOne
	@JoinColumn(name="order_id")
	private Order order;
	@ManyToOne
	@JoinColumn(name="delivery_person_id")
	private Delivery_Person  delivery_Person;
	
	private LocalDate date;
	private String status;
	@OneToOne
	@JoinColumn(name = "otp_id")
	private Otp otp;
}
