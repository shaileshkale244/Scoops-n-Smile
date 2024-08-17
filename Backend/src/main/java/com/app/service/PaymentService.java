package com.app.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@Service
public class PaymentService {
	


	@Value("${razorpay.key_id}")
	private String razorpayKeyId;

	@Value("${razorpay.key_secret}")
	private String razorpayKeySecret;

	public String createOrder(Double amount, String currency) throws RazorpayException {
		RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

		Map<String, Object> orderRequest = new HashMap<>();
		orderRequest.put("amount", (int) (amount * 100)); // Amount is in the smallest currency unit
		orderRequest.put("currency", currency);
		orderRequest.put("receipt", "txn_123456");

		Order order = razorpayClient.orders.create(new JSONObject(orderRequest));
		return order.toString(); // You can return the order ID to the frontend
	}

	public void handlePaymentSuccess(String razorpayPaymentId, String razorpayOrderId, String razorpaySignature)
			throws RazorpayException {
		try {
//            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

			JSONObject options = new JSONObject();
			options.put("razorpay_order_id", razorpayOrderId);
			options.put("razorpay_payment_id", razorpayPaymentId);
			options.put("razorpay_signature", razorpaySignature);
			Utils.verifyPaymentSignature(options, razorpayKeySecret);
			//updating the payment status of the order and generating the delivery for order
			//service.updateOrder(null, null);
		
		} catch (RazorpayException e) {
			e.printStackTrace();
			throw new RazorpayException("Invalid signature");
		}
	}

}
