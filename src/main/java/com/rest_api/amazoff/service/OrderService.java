package com.rest_api.amazoff.service;

import java.util.List;

import com.rest_api.amazoff.entity.Order;
import com.rest_api.amazoff.responsedtos.OrderDto;

public interface OrderService {

	// 1.Done
	public String addOrder(Order order);

	// 3.Done
	public String addOrderPartnerPair(int partnerId, int orderId);

	// 4.Ongoing
	public Order getOrderById(int id);

	// 8.all orders in system
	public List<OrderDto> getAllOrders();

	// same as 9th
	public List<OrderDto> getListOfUnassignedOrders();

	public Order setDeliveryAsDone(int orderId);

	public String deleteOrderById(int orderId);

//	//9.
//	public int getCountOfUnassignedOrders();
//	

	//

}
