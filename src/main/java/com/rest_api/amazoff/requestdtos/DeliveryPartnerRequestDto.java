package com.rest_api.amazoff.requestdtos;

public class DeliveryPartnerRequestDto {

	private String name;
	private String lastDelivery;
	private boolean status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastDelivery() {
		return lastDelivery;
	}

	public void setLastDelivery(String lastDelivery) {
		this.lastDelivery = lastDelivery;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public DeliveryPartnerRequestDto() {
		super();
	};

}
