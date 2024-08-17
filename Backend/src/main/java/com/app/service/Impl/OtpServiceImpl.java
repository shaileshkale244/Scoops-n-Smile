package com.app.service.Impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Order;
import com.app.entities.Otp;
import com.app.repository.OrderRepository;
import com.app.repository.OtpRepository;
import com.app.service.OtpService;

@Service
@Transactional
public class OtpServiceImpl implements OtpService {

	private static final int OTP_LENGTH = 6;
	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Otp getOtpById(Long id) {
		return otpRepository.findById(id).orElseThrow();
	}

	@Override
	public Otp getOtpByOrderId(Long oid) {
		Order order = orderRepository.findById(oid).orElseThrow();
		return otpRepository.findByOrder(order).orElseThrow();
	}

	@Override

	public Otp createOtp(Order order) {
		Random random = new Random();
		Otp otp = new Otp();
		StringBuilder otpcode = new StringBuilder();
		for (int i = 0; i < OTP_LENGTH; i++) {
			otpcode.append(random.nextInt(10));
		}
		otp.setOtp_code(otpcode.toString());
		otp.setOrder(order);

		return otpRepository.save(otp);
	}

	@Override
	public Boolean validateOtp(Long OrderId, String otpCode) {
		Otp otpByOrderId = getOtpByOrderId(OrderId);
		if (otpByOrderId != null && otpByOrderId.getOtp_code().equals(otpCode))
			return true;
		return false;
	}

}
