package com.onus.tacocloudapi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "orders", produces = "application/json")
@CrossOrigin("*")
public class OrderRepository {
	private OrderRepository orderRepo;
	
}
