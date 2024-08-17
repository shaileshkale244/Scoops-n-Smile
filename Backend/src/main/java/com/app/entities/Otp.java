package com.app.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="otps")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="otp")
	private Long id;
	@OneToOne
	@JoinColumn(name="order_id")
	private Order order;
	private String otp_code;
	private LocalDate generationDate;
	private LocalDate verificationDate;
}
