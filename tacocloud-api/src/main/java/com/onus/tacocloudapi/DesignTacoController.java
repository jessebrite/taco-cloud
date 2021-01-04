package com.onus.tacocloudapi;

import com.onus.tacoclouddata.TacoRepository;
import com.onus.tacoclouddomain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
	
	private final TacoRepository tacoRepo;

	@Autowired
	EntityLinks entityLinks;

	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

	@GetMapping("/recent")
	public CollectionModel<EntityModel<Taco>> recentTacos() {
		PageRequest pageRequest = PageRequest.of(
			0, 12, Sort.by("createdAt").descending());

		List<Taco> tacos = tacoRepo.findAll(pageRequest).getContent();
		CollectionModel<EntityModel<Taco>> recentResources = CollectionModel.wrap(tacos);
		recentResources.add(
			linkTo(methodOn(DesignTacoController.class).recentTacos())
			.withRel("recents"));

		return recentResources;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent())
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}
}
