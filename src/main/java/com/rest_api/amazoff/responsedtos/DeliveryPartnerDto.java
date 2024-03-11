package com.rest_api.amazoff.responsedtos;

public class DeliveryPartnerDto {

	private int id;
	private String name;
	private String lastDelivery;
	private boolean status;

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

	public DeliveryPartnerDto() {
		super();
	}

}
