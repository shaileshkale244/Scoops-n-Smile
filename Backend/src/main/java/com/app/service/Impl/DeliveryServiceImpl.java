package com.app.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.CustomException;
import com.app.dto.DeliveryResponse;
import com.app.entities.Bill;
import com.app.entities.Delivery;
import com.app.entities.Delivery_Person;
import com.app.entities.Order;
import com.app.entities.OrderStatus;
import com.app.entities.Otp;
import com.app.repository.BillRepository;
import com.app.repository.DeliveryPersonRepository;
import com.app.repository.DeliveryRepository;
import com.app.repository.OrderRepository;
import com.app.service.DeliveryService;
import com.app.service.EmailService;
import com.app.service.OtpService;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OtpService otpService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private DeliveryPersonRepository deliveryPersonRepository;

	@Autowired
	private BillRepository billRepository;

	@Override
	public List<Delivery> getAllDeliveries() {
		return deliveryRepository.findAll();
	}

	@Override
	public Delivery getDeliveryById(Long id) {
		Optional<Delivery> optional = deliveryRepository.findById(id);
		return optional.orElseThrow(() -> new CustomException("Invalid Delivery Id"));
	}

	@Override
	public Delivery getDeliveryByOrderId(Long oid) {
		Order order = orderRepository.findById(oid).orElseThrow();
		return deliveryRepository.findByOrder(order).orElseThrow();

	}

	@Override
	public Delivery createDelivery(Order order) {
		Delivery delivery = new Delivery();
		Otp otp = otpService.createOtp(order);

		// send the otpto the customer
		emailService.sendOtpEmail(order.getCustomer().getEmail(), otp.getOtp_code());

		delivery.setOrder(order);
		delivery.setStatus("Pending");
		delivery.setOtp(otp);

		Bill bill = billRepository.findByOrder(order);
		bill.setPayment_status("Paid");
		billRepository.save(bill);

		Delivery savedDelivery = deliveryRepository.save(delivery);
		return savedDelivery;
	}

	@Override
	public Delivery updateDelivery(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

	@Override
	public String deleteDelivery(Long id) {
		if (deliveryRepository.existsById(id)) {
			deliveryRepository.deleteById(id);
			return "Delivey Deleted";
		}
		throw new CustomException("Deletion operation of delivery failed");
	}

	@Override
	public Delivery assignDeliveryPerson(Long deliveryPersonId, Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow();
		
		System.out.println(order.getId());
		Delivery delivery = deliveryRepository.findByOrder(order).orElseThrow();
		
		System.out.println(delivery.getId());
		Delivery_Person delivery_Person = deliveryPersonRepository.findById(deliveryPersonId).orElseThrow();
		
		delivery.setDelivery_Person(delivery_Person);
		System.out.println(delivery.getDelivery_Person().getName()); 
		return deliveryRepository.save(delivery);
	}

	@Override
	public Boolean verifyOtp(Long orderId, String otpcode) {
		if (otpService.validateOtp(orderId, otpcode)) {
			// update order and delivery status
			Order order = orderRepository.findById(orderId).orElseThrow();
			Delivery delivery = deliveryRepository.findByOrder(order).orElseThrow();

			order.setStatus(OrderStatus.DELIVERD);
			delivery.setStatus("Delivered");

			orderRepository.save(order);
			deliveryRepository.save(delivery);
			return true;
		}
		return false;
	}

	@Override
	public List<DeliveryResponse> getAllByDeliveryPerson(Long deliveryPersonId) {
		List<Delivery> allByDelivery_Person = deliveryRepository.findAll().stream()
				.filter(delivery -> delivery.getDelivery_Person().getId().equals(deliveryPersonId) && delivery.getStatus().equals("Pending")).toList();
		List<DeliveryResponse> list = allByDelivery_Person.stream().map(delivery -> {
			DeliveryResponse response = new DeliveryResponse();
			response.setDeliveryId(delivery.getId());
			response.setDeliveryDate(delivery.getDate());
			response.setOrderId(delivery.getOrder().getId());
			response.setStatus(delivery.getStatus());
			return response;
		}).collect(Collectors.toList());
		return list;
	}

}
