package com.onus.tacoclouddata;

import com.onus.tacoclouddomain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {}
