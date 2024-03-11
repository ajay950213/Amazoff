package com.rest_api.amazoff.responsedtos;

public class UnassignedOrderCountDto {

	private int unassignedOrders;

	public int getUnassignedOrders() {
		return unassignedOrders;
	}

	public void setUnassignedOrders(int unassignedOrders) {
		this.unassignedOrders = unassignedOrders;
	}

	public UnassignedOrderCountDto(int unassignedOrders) {
		super();
		this.unassignedOrders = unassignedOrders;
	}
	
	
}
