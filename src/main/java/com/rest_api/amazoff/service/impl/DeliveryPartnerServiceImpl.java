package com.rest_api.amazoff.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest_api.amazoff.entity.DeliveryPartner;
import com.rest_api.amazoff.entity.Order;
import com.rest_api.amazoff.exceptions.DeliveryPartnerNotFoundException;
import com.rest_api.amazoff.exceptions.ResourceNotFoundException;
import com.rest_api.amazoff.requestdtos.DeliveryPartnerRequestDto;
import com.rest_api.amazoff.responsedtos.DeliveryPartnerDto;
import com.rest_api.amazoff.responsedtos.OrderDto;
import com.rest_api.amazoff.respository.DeliveryPartnerRepository;
import com.rest_api.amazoff.respository.OrderRepository;
import com.rest_api.amazoff.service.DeliveryPartnerService;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryPartnerService.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public String addDeliveryPartner(DeliveryPartnerRequestDto deliveryPartnerRequestDto) {
		DeliveryPartner deliveryPartner = modelMapper.map(deliveryPartnerRequestDto, DeliveryPartner.class);
		deliveryPartner.setStatus(true);
		deliveryPartnerRepository.save(deliveryPartner);
		return "Delivery partner saved succesfully";
	}

	@Override
	public DeliveryPartnerDto getPartnerById(int id) {

		Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);

		if (deliveryPartner.isEmpty()) {
			throw new DeliveryPartnerNotFoundException("Delivery partner not found for ID: " + id);
		} else {
			DeliveryPartner deliveryPartner2 = deliveryPartner.get();
			return modelMapper.map(deliveryPartner2, DeliveryPartnerDto.class);
		}

	}

	@Override
	public List<OrderDto> getOrderCountByPartnerId(int partnerId) {
		
		if (partnerId < 0) {
			throw new ResourceNotFoundException("Invaid id " + partnerId);
		}
		DeliveryPartner deliveryPartner = deliveryPartnerRepository
				.findById(partnerId).orElseThrow(() -> new DeliveryPartnerNotFoundException("Delivery partner not found for id "+partnerId));

		List<Order> numberOfOrders = orderRepository.listOfOrdersAssignedToPartner(partnerId);
		
		List<OrderDto> listOfOrderOfPartner = new ArrayList<>();

		for (Order order : numberOfOrders) {
			OrderDto orderDto = new OrderDto();

			orderDto.setId(order.getId());
			orderDto.setName(order.getName());
			orderDto.setDeliveryStatus(order.getDeliveryStatus());
			orderDto.setDeliveryTime(order.getDeliveryTime());
			orderDto.setOrderedDate(order.getOrderedDate());
			
			listOfOrderOfPartner.add(orderDto);
		}
		if (listOfOrderOfPartner.isEmpty()) {
			System.out.println("no data");
			throw new ResourceNotFoundException("No Orders found");
		} else {
			return listOfOrderOfPartner;
		}
	}

	@Override
	public List<Order> getListOfAllOrdersOfPartnerById(int partnerId) {
		Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(partnerId);
		if (deliveryPartner.isEmpty()) {
			throw new DeliveryPartnerNotFoundException("delivery partner not found for " + partnerId);
		}
		List<Order> allOrdersOfPartner = orderRepository.listOfAllOrdersOfPartner(partnerId);
		return allOrdersOfPartner;
	}

	@Override
	public DeliveryPartner getLastDeliveryDateOfPartner(int partnerId) {
		logger.trace("Entred into getting last delivery date of delivery partner");

		return deliveryPartnerRepository.findLastDeliverById(partnerId);
	}

	@Override
	public String deleteDeliveryPartnerById(int partnerId) {

		Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(partnerId);
		if (deliveryPartner.isEmpty()) {
			throw new DeliveryPartnerNotFoundException("Order not found with id: " + partnerId);
		}

		DeliveryPartner deliveryPartner2 = deliveryPartner.get();
		deliveryPartner2.setStatus(false);

		List<Order> listOfAllOrdersOfPartner = getListOfAllOrdersOfPartnerById(partnerId);

		for (Order order : listOfAllOrdersOfPartner) {
			order.setDeliveryPartner(null);
			orderRepository.save(order);
		}

		deliveryPartnerRepository.save(deliveryPartner2);

		return "Delivery partner deleted succesfully";
	}

}
