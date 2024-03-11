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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest_api.amazoff.entity.DeliveryPartner;
import com.rest_api.amazoff.exceptions.LastDeliveryDateNotFoundException;
import com.rest_api.amazoff.requestdtos.DeliveryPartnerRequestDto;
import com.rest_api.amazoff.responsedtos.DeliveryPartnerDto;
import com.rest_api.amazoff.responsedtos.OrderCountResponseDto;
import com.rest_api.amazoff.responsedtos.OrderDto;
import com.rest_api.amazoff.service.DeliveryPartnerService;

import jakarta.validation.Valid;

/**
 * Controller class for managing delivery partners.
 */
@RestController
@RequestMapping("/delivery_partner")
public class DelieveryPartnerController {

    private static final Logger logger = LoggerFactory.getLogger(DelieveryPartnerController.class);

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    /**
     * Endpoint for adding a new delivery partner.
     *
     * @param deliveryPartner The DeliveryPartner object to be added.
     * @return ResponseEntity returning result of delivery partner creation 
     */
    @PostMapping("/add_partner")
    public ResponseEntity<String> addPartner(@Valid @RequestBody DeliveryPartnerRequestDto deliveryPartnerRequestDto) {
        logger.info("Adding a new delivery partner");
        String result = deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * Endpoint for retrieving a delivery partner by ID.
     *
     * @param id The ID of the delivery partner.
     * @return ResponseEntity containing the DeliveryPartner object.
     */
    @GetMapping("/get-partner-by-id/{id}")
    public ResponseEntity<?> getPartnerById(@PathVariable int id) {
        logger.info("Retrieving delivery partner by ID: {}", id);
        DeliveryPartnerDto deliveryPartnerDto = deliveryPartnerService.getPartnerById(id);
        return new ResponseEntity<>(deliveryPartnerDto, HttpStatus.OK);
    }

    /**
     * Endpoint for getting the number of orders assigned to a delivery partner by ID.
     *
     * @param id The ID of the delivery partner.
     * @return ResponseEntity containing the number of orders.
     */
    @GetMapping("/get-order-count-by-partner-id/{id}")
    public ResponseEntity<OrderCountResponseDto> numberOfOrdersAssignedToPartnerById(@PathVariable int id) {
        logger.info("Getting the number of orders assigned to delivery partner with ID: {}", id);
        List<OrderDto> listOfOrdersOfPartner = deliveryPartnerService.getOrderCountByPartnerId(id);
        int numberOfOrdersOfDeliveryPartner = listOfOrdersOfPartner.size();
        
        OrderCountResponseDto orderCountResponseDto = new OrderCountResponseDto();
        
        DeliveryPartnerDto deliveryPartnerDto = deliveryPartnerService.getPartnerById(id);
        
        String deliveryPartnerName = deliveryPartnerDto.getName();
        
        orderCountResponseDto.setDeliveryPartnerName(deliveryPartnerName);
        
        orderCountResponseDto.setNumberOfOrdersAssigned(numberOfOrdersOfDeliveryPartner);
        
        String lastDeliveryDate = deliveryPartnerDto.getLastDelivery();
        orderCountResponseDto.setLastDeliveryDate(lastDeliveryDate);
        
        return ResponseEntity.status(HttpStatus.OK).body(orderCountResponseDto);
    }

    /**
     * Endpoint for getting the list of orders assigned to a delivery partner by ID.
     *
     * @param id The ID of the delivery partner.
     * @return ResponseEntity returning the list of orders. 
     */
    @GetMapping("/get-list-orders-by-partner-id/{id}")
    public ResponseEntity<List<OrderDto>> listOfOrdersAssignedToPartnerById(@PathVariable int id) {
        logger.info("Getting the list of orders assigned to delivery partner with ID: {}", id);
        List<OrderDto> listOfOrdersOfPartner = deliveryPartnerService.getOrderCountByPartnerId(id);
        return ResponseEntity.status(HttpStatus.OK).body(listOfOrdersOfPartner);
    }

    /**
     * Endpoint for getting the last delivery date of a delivery partner by ID.
     *
     * @param partnerId The ID of the delivery partner.
     * @return ResponseEntity returning last delivery date of delivery partner.
     */
    @GetMapping("/last_delivery_date_of_partner/{partnerId}")
    public ResponseEntity<String> getLastDeliveryDateOfPartner(@PathVariable int partnerId) {
        logger.info("Getting the last delivery date of delivery partner with ID: {}", partnerId);
        String message = "last delivery date not found for id " + partnerId;
        try {
            logger.trace("Entered into getting last delivery date of delivery partner");
            DeliveryPartner deliveryPartner = deliveryPartnerService.getLastDeliveryDateOfPartner(partnerId);
            String lastDelivery = deliveryPartner.getLastDelivery();
            if (lastDelivery.isEmpty()) {
                throw new LastDeliveryDateNotFoundException(message);
            }
            return ResponseEntity.status(HttpStatus.OK).body(lastDelivery);
        } catch (Exception e) {
            logger.error("Error while getting the last delivery date of delivery partner with ID: {}", partnerId, e);
            throw new LastDeliveryDateNotFoundException(message);
        }
    }

    /**
     * Endpoint for deleting a delivery partner by ID.
     *
     * @param partnerId The ID of the delivery partner.
     * @return ResponseEntity  returning result of order whether it is deleted or not
     */
    @DeleteMapping("/delivery_partnerby-id/{partnerId}")
    public ResponseEntity<String> deletePartrnerById(@PathVariable int partnerId) {
        logger.info("Deleting delivery partner with ID: {}", partnerId);
        String result = deliveryPartnerService.deleteDeliveryPartnerById(partnerId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
