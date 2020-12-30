package com.onus.tacoclouddata;

import org.springframework.data.repository.CrudRepository;
import com.onus.tacoclouddomain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
