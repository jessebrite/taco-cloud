package com.onus.tacocloudweb;

import com.onus.tacoclouddomain.Order;
import com.onus.tacoclouddomain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import com.onus.tacoclouddata.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private final OrderRepository orderRepo;
	private final OrderProps props;

	public OrderController(OrderRepository orderRepo,
	                       OrderProps props) {
		this.orderRepo = orderRepo;
		this.props = props;
	}

	@GetMapping("/current")
	public String orderForm(@AuthenticationPrincipal User user,
	                        @ModelAttribute Order order) {
		if (order.getDeliveryName() == null)
			order.setDeliveryName(user.getFullname());

		if (order.getDeliveryStreet() == null)
			order.setDeliveryStreet(user.getStreet());

		if (order.getDeliveryCity() == null)
			order.setDeliveryCity(user.getCity());

		if (order.getDeliveryState() == null)
			order.setDeliveryState(user.getState());

		if (order.getDeliveryZip() == null)
			order.setDeliveryZip(user.getZip());

		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors,
	                           SessionStatus sessionStatus,
	                           @AuthenticationPrincipal User user) {
		if (errors.hasErrors())
			return "orderForm";

		log.info("Order submitted: " + order);

		order.setUser(user);

		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}

	@GetMapping
	public String orderForUser(@AuthenticationPrincipal User user,
                             Model model) {

		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders",
			orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

		return "orderList";
	}
}