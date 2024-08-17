package com.app.service.Impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Bill;
import com.app.entities.Order;
import com.app.entities.Order_Item;
import com.app.repository.BillRepository;
import com.app.repository.OrderRepository;
import com.app.service.BillService;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Bill generateBill(Order order) {
		
		Bill bill=new Bill();
		bill.setOrder(order);
		bill.setPayment_status("Unpaid");
		bill.setTotal(order.getItems().stream().mapToDouble(Order_Item::getSubtotal).sum());
		bill.setDate(LocalDate.now());
		return billRepository.save(bill);
	}

	@Override
	public Bill getBillByOrderId(Long id) {
		Order order = orderRepository.findById(id).orElseThrow();
		return billRepository.findByOrder(order);
	}

	@Override
	public Bill getBillById(Long id) {

		return billRepository.findById(id).orElseThrow();
	}

	
	//for updating the payment status of the bill
	@Override
	public Bill updatePaymentStatus(Bill bill, String status) {
		Bill bill2 = billRepository.findById(bill.getId()).orElseThrow();
		bill2.setPayment_status(status);		
		return billRepository.save(bill2);
	}

}
