package com.rest_api.amazoff.requestdtos;

import com.rest_api.amazoff.enums.DeliveryStatus;

public class OrderRequestDto {

	private String name;
	private String orderedDate;
	private boolean status;

	private DeliveryStatus deliveryStatus;

	private String deliveryTime;

	private long deliveryPartnerId;

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

	public long getDeliveryPartnerId() {
		return deliveryPartnerId;
	}

	public void setDeliveryPartnerId(long deliveryPartnerId) {
		this.deliveryPartnerId = deliveryPartnerId;
	}

	public OrderRequestDto() {
		super();
	}

}
