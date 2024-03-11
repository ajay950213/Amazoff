package com.rest_api.amazoff.service;

import java.util.List;

import com.rest_api.amazoff.entity.DeliveryPartner;
import com.rest_api.amazoff.entity.Order;
import com.rest_api.amazoff.requestdtos.DeliveryPartnerRequestDto;
import com.rest_api.amazoff.responsedtos.DeliveryPartnerDto;
import com.rest_api.amazoff.responsedtos.OrderDto;

public interface DeliveryPartnerService {
	// 2.Done
	public String addDeliveryPartner(DeliveryPartnerRequestDto deliveryPartnerRequestDto);

	// 5.Done
	public DeliveryPartnerDto getPartnerById(int id);

	// 6.Done
	public List<OrderDto> getOrderCountByPartnerId(int id);

	// 7.
	public List<Order> getListOfAllOrdersOfPartnerById(int partnerId);

	public DeliveryPartner getLastDeliveryDateOfPartner(int partnerId);

	public String deleteDeliveryPartnerById(int id);

}
