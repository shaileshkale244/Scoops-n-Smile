package com.app.service;

import com.app.entities.Bill;
import com.app.entities.Order;

public interface BillService {
 Bill generateBill(Order order);
 
 Bill getBillByOrderId(Long oid);
 
 Bill getBillById(Long id);
 
 Bill updatePaymentStatus(Bill bill,String status);
}
