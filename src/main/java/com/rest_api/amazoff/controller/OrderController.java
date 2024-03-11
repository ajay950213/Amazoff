package com.rest_api.amazoff.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest_api.amazoff.entity.Order;
import com.rest_api.amazoff.responsedtos.OrderDto;
import com.rest_api.amazoff.responsedtos.UnassignedOrderCountDto;
import com.rest_api.amazoff.service.OrderService;

import jakarta.validation.Valid;

/**
 * Controller class for managing orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	/**
	 * Endpoint for adding a new order.
	 *
	 * @param order The Order object to be added.
	 * @return ResponseEntity the result of the operation.
	 */
	@PostMapping("/add_order")
	public ResponseEntity<String> addOrder(@Valid @RequestBody Order order) {
		logger.info("Adding a new order");
		String result = orderService.addOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	/**
	 * Endpoint for adding a partner-order pair.
	 *
	 * @param partId  The ID of the partner.
	 * @param orderId The ID of the order.
	 * @return ResponseEntity returning the result of the operation.
	 */
	@PutMapping("/add-order-partner-pair/{partId}/{orderId}")
	public ResponseEntity<String> addOrderPartnerPair(@Valid @PathVariable int partId, @PathVariable int orderId) {
		logger.info("Adding partner-order pair: Partner ID - {}, Order ID - {}", partId, orderId);
		String result = orderService.addOrderPartnerPair(partId, orderId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * Endpoint for retrieving an order by ID.
	 *
	 * @param id The ID of the order.
	 * @return ResponseEntity returning the Order object.
	 */
	@GetMapping("/get-order-by-id/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable int id) {
		logger.info("Retrieving order by ID: {}", id);
		Order order = orderService.getOrderById(id);
		if (order != null) {
			return ResponseEntity.status(HttpStatus.OK).body(order);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Object not found");
	}

	/**
	 * Endpoint for retrieving all orders.
	 *
	 * @return ResponseEntity returning a list of all orders.
	 */
	@GetMapping("/get-all-orders")
	public ResponseEntity<?> getAllOrder() {
		try {
			logger.info("Retrieving all orders");
			List<OrderDto> allOrders = orderService.getAllOrders();
			if (allOrders.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(allOrders);
			}
			return ResponseEntity.status(HttpStatus.OK).body(allOrders);
		} catch (Exception e) {
			logger.error("Error while retrieving all orders", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No data found");
		}
	}

	/**
	 * Endpoint for retrieving a list of unassigned orders.
	 *
	 * @return ResponseEntity returning a list of unassigned orders.
	 */
	@GetMapping("/get-list-of-unassigned-orders")
	public ResponseEntity<List<OrderDto>> getListOfUnassignedOrders() {
		logger.info("Retrieving a list of unassigned orders");
		List<OrderDto> listOfUnassignedOrders = orderService.getListOfUnassignedOrders();
		return ResponseEntity.status(HttpStatus.OK).body(listOfUnassignedOrders);
	}

	/**
	 * Endpoint for retrieving the count of unassigned orders.
	 *
	 * @return ResponseEntity returning the count of unassigned orders.
	 */
	@GetMapping("/get-count-of-unassigned-orders")
	public ResponseEntity<UnassignedOrderCountDto> getCoutOfUnassignedOrders() {
		logger.info("Retrieving the count of unassigned orders");
		List<OrderDto> listOfUnassignedOrders = orderService.getListOfUnassignedOrders();
		int numberOfUnassignedOrders = listOfUnassignedOrders.size();
		UnassignedOrderCountDto unassignedOrderCountDto = new UnassignedOrderCountDto(numberOfUnassignedOrders);
		return ResponseEntity.status(HttpStatus.OK).body(unassignedOrderCountDto);
	}

	/**
	 * Endpoint for setting the delivery status as done for an order.
	 *
	 * @param order's orderId.
	 * @return ResponseEntity returning product delivery status as string
	 */
	@PutMapping("/set_delivey_status_as_done/{orderId}")
	public ResponseEntity<?> setDeliveryStatusAsDone(@Valid @PathVariable int orderId) {
		logger.info("Setting delivery status as done for order with ID: {}", orderId);
		orderService.setDeliveryAsDone(orderId);
		return ResponseEntity.status(HttpStatus.OK).body("Product delivered");
	}

	/**
	 * Endpoint for deleting an order by ID.
	 *
	 * @param orderId The ID of the order.
	 * @return ResponseEntity returning result of delivery whether it is deleted or
	 *         not
	 */
	@DeleteMapping("/delete_order_by_id/{orderId}")
	public ResponseEntity<String> deleteOrderById(@PathVariable int orderId) {
		logger.info("Deleting order with ID: {}", orderId);
		String result = orderService.deleteOrderById(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
