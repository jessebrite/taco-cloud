package com.onus.tacocloudapi;

import com.onus.tacoclouddata.OrderRepository;
import com.onus.tacoclouddomain.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin("*")
public class OrderApiController {

	private final OrderRepository orderRepo;

	public OrderApiController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping(produces="application/json")
	public Iterable<Order> allOrders() {
		return orderRepo.findAll();
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Order postOrder(@RequestBody Order order) {
		return orderRepo.save(order);
	}

	@PutMapping(path = "/{orderId}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Order putOrder(@RequestBody Order order) {
		return orderRepo.save(order);
	}

	@PatchMapping(path = "/{orderId}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Order patchOrder(@PathVariable("orderId") Long orderId,
	                        @RequestBody Order patch) {
		Order order = orderRepo.findById(orderId).get();

		if (patch.getDeliveryName() != null)
			order.setDeliveryName(patch.getDeliveryName());

		if (patch.getDeliveryStreet() != null)
			order.setDeliveryStreet(patch.getDeliveryStreet());

		if (patch.getDeliveryCity() != null)
			order.setDeliveryCity(patch.getDeliveryCity());

		if (patch.getDeliveryState() != null)
			order.setDeliveryState(patch.getDeliveryState());

		if (patch.getDeliveryZip() != null)
			order.setDeliveryZip(patch.getDeliveryState());

		if (patch.getCcNumber() != null)
			order.setCcNumber(patch.getCcNumber());

		if (patch.getCcExpiration() != null)
			order.setCcExpiration(patch.getCcExpiration());

		if (patch.getCcCVV() != null)
			order.setCcCVV(patch.getCcCVV());

		return orderRepo.save(order);
	}

	@DeleteMapping("/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
		try {
			orderRepo.deleteById(orderId);
		} catch (EmptyResultDataAccessException exception) {}
	}
}
