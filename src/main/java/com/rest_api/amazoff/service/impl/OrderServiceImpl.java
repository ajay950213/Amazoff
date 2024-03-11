package com.rest_api.amazoff.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest_api.amazoff.entity.DeliveryPartner;
import com.rest_api.amazoff.entity.Order;
import com.rest_api.amazoff.enums.DeliveryStatus;
import com.rest_api.amazoff.exceptions.OrderNotFoundException;
import com.rest_api.amazoff.responsedtos.OrderDto;
import com.rest_api.amazoff.respository.DeliveryPartnerRepository;
import com.rest_api.amazoff.respository.OrderRepository;
import com.rest_api.amazoff.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;

	@Override
	public String addOrder(Order order) {
		String date = new Date() + "";
		order.setOrderedDate(date);
		order.setStatus(false);
		orderRepository.save(order);
		return "Order create sccesfully";
	}

	@Override
	public String addOrderPartnerPair(int partnerId, int orderId) {
		try {

			Optional<Order> order = orderRepository.findById(orderId);
			Optional<DeliveryPartner> partner = deliveryPartnerRepository.findById(partnerId);

			if (partner.isEmpty()) {
				throw new OrderNotFoundException("Delivery partner not found with id: " + partnerId);
			} else if (order.isEmpty()) {
				throw new OrderNotFoundException("Order not found with id: " + orderId);
			}

			if (!order.isEmpty() && !partner.isEmpty()) {

				Order order1 = order.get();
				DeliveryPartner partner1 = partner.get();
				order1.setDeliveryPartner(partner1);
				String date = new Date() + "";

				order1.setDeliveryTime(date);
				order1.setDeliveryStatus(DeliveryStatus.DISPATCHED);
				orderRepository.save(order1);

				return "Delivery partner added succesfully";

			}
		} catch (Exception e) {
			throw e;
		}
		return "Delivery partner not added";
	}

	@Override
	public Order getOrderById(int id) {

		Optional<Order> order = orderRepository.findById(id);
		return order.orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
	}

	@Override
	public List<OrderDto> getAllOrders() {
		try {

			List<OrderDto> listOfOrderDto = new ArrayList<>();

			List<Order> allOrders = orderRepository.findAll();
			for (Order order : allOrders) {
				OrderDto oneOrderDto = new OrderDto();
				oneOrderDto.setName(order.getName());
				oneOrderDto.setDeliveryStatus(order.getDeliveryStatus());
				oneOrderDto.setDeliveryTime(order.getDeliveryTime());
				oneOrderDto.setId(order.getId());
				oneOrderDto.setOrderedDate(order.getOrderedDate());
				listOfOrderDto.add(oneOrderDto);
			}

			if (listOfOrderDto.isEmpty()) {
				return listOfOrderDto;
			}
			return listOfOrderDto;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<OrderDto> getListOfUnassignedOrders() {
		List<Order> listOfUnAssignedOrders = orderRepository.findByDeliveryPartnerIsNull();

		List<OrderDto> listOfOrderDto = new ArrayList<>();

		for (Order order : listOfUnAssignedOrders) {
			OrderDto oneOrderDto = new OrderDto();
			oneOrderDto.setName(order.getName());
			oneOrderDto.setDeliveryStatus(order.getDeliveryStatus());
			oneOrderDto.setDeliveryTime(order.getDeliveryTime());
			oneOrderDto.setId(order.getId());
			oneOrderDto.setOrderedDate(order.getOrderedDate());
			listOfOrderDto.add(oneOrderDto);
		}
		if (listOfOrderDto.isEmpty()) {
			return listOfOrderDto;
		}
		return listOfOrderDto;
	}

	@Override
	public Order setDeliveryAsDone(int orderId) {
		try {
			Optional<Order> order = orderRepository.findById(orderId);

			if (!order.isEmpty()) {
				int deliveryPartnerIdOfOrder = order.get().getId();

				if (deliveryPartnerIdOfOrder > 0) {
					Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository
							.findById(deliveryPartnerIdOfOrder);

					Order order1 = order.get();
					order1.setDeliveryStatus(DeliveryStatus.DONE); // delivery status set as done

					String deliveredTime = new Date() + "";
					order1.setDeliveredTime(deliveredTime); // delivered time set

					orderRepository.save(order1); // update with above value and saved

					DeliveryPartner deliveryPartner2 = deliveryPartner.get();
					deliveryPartner2.setLastDelivery(deliveredTime);

					deliveryPartnerRepository.save(deliveryPartner2); // updated with above vales and saved

					return order1;
				}

			} else if (order.isEmpty()) {
				throw new OrderNotFoundException("Order not found with id " + orderId);
			}

		} catch (Exception e) {
			throw e;
		}
		return null;

	}

	@Override
	public String deleteOrderById(int orderId) {
		Order order = getOrderById(orderId);

		order.setDeliveryPartner(null);
		orderRepository.save(order);

		return "Order deleted succesfully";
	}

}
