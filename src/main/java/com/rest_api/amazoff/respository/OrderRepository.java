package com.rest_api.amazoff.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rest_api.amazoff.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	// we are using this for both 6 and 7
	@Query(value = "select * from order_t where delivery_partner_id =:partnerId", nativeQuery = true)
	public List<Order> listOfOrdersAssignedToPartner(@Param("partnerId") int id);

	@Query(value = "select * from order_t where delivery_partner_id=:partnerId", nativeQuery = true)
	public List<Order> listOfAllOrdersOfPartner(@Param("partnerId") int partnerId);

	public List<Order> findByDeliveryPartnerIsNull();

}
