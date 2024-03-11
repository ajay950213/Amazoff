package com.rest_api.amazoff.responsedtos;

import com.rest_api.amazoff.enums.DeliveryStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OrderDto {

	private int id;
	private String name;

	private String orderedDate;
	private boolean status;

	@Enumerated(value = EnumType.STRING)
	private DeliveryStatus deliveryStatus;

	private String deliveryTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public OrderDto() {
		super();
	}

}
