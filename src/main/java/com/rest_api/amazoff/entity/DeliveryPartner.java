package com.rest_api.amazoff.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DeliveryPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String lastDelivery;

	@OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders = new ArrayList<>();

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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public DeliveryPartner() {
		super();
	}

	@Override
	public String toString() {
		return "DeliveryPartner [id=" + id + ", name=" + name + ", lastDelivery=" + lastDelivery + ", orders=" + orders
				+ ", status=" + status + "]";
	}

}
