package com.rest_api.amazoff.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.amazoff.entity.DeliveryPartner;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {

	public DeliveryPartner findLastDeliverById(int id);

}
