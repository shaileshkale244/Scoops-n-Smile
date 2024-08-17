package com.app.service;

import com.app.entities.Order;
import com.app.entities.Otp;

public interface OtpService {

	Otp getOtpById(Long id);

	Otp getOtpByOrderId(Long oid);

	Otp createOtp(Order order);
	
	Boolean validateOtp(Long OrderId, String otpCode);
}
