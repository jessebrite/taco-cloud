package com.onus.tacoclouddata;

import com.onus.tacoclouddomain.Order;
import com.onus.tacoclouddomain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
