package com.onus.tacocloudapi;

import com.onus.tacoclouddata.TacoRepository;
import com.onus.tacoclouddomain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
	private TacoRepository tacoRepo;

	@Autowired
	EntityLinks entityLinks;

	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {
		PageRequest pageRequest = PageRequest.of(
			0, 12, Sort.by("createdAt").descending());
		return tacoRepo.findAll(pageRequest).getContent();
	}
}
