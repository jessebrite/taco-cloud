package com.onus.tacoclouddata;

import com.onus.tacoclouddomain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {}
