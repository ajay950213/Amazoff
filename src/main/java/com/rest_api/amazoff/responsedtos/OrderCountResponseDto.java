package com.rest_api.amazoff.responsedtos;

public class OrderCountResponseDto {

	private String deliveryPartnerName;
	private long numberOfOrdersAssigned;
	private String lastDeliveryDate;

	public String getDeliveryPartnerName() {
		return deliveryPartnerName;
	}

	public void setDeliveryPartnerName(String deliveryPartnerName) {
		this.deliveryPartnerName = deliveryPartnerName;
	}

	public long getNumberOfOrdersAssigned() {
		return numberOfOrdersAssigned;
	}

	public void setNumberOfOrdersAssigned(long numberOfOrdersAssigned) {
		this.numberOfOrdersAssigned = numberOfOrdersAssigned;
	}

	public String getLastDeliveryDate() {
		return lastDeliveryDate;
	}

	public void setLastDeliveryDate(String lastDeliveryDate) {
		this.lastDeliveryDate = lastDeliveryDate;
	}

	public OrderCountResponseDto() {
		super();
	}

}
