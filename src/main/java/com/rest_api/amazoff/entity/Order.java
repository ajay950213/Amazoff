package com.rest_api.amazoff.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rest_api.amazoff.enums.DeliveryStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	private String orderedDate;
	private boolean status;

	@Enumerated(value = EnumType.STRING)
	private DeliveryStatus deliveryStatus;

	private String deliveryTime;

	@ManyToOne
	@JoinColumn(name = "delivery_partner_id")
	@JsonBackReference
	private DeliveryPartner deliveryPartner;

	private String deliveredTime;

	public String getDeliveredTime() {
		return deliveredTime;
	}

	public void setDeliveredTime(String deliveredTime) {
		this.deliveredTime = deliveredTime;
	}

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

	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}

	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", orderedDate=" + orderedDate + ", status=" + status
				+ ", deliveryStatus=" + deliveryStatus + ", deliveryTime=" + deliveryTime + ", deliveryPartner="
				+ deliveryPartner + ", deliveredTime=" + deliveredTime + "]";
	}

}
