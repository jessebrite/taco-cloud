package com.onus.tacocloudapi;

import com.onus.tacoclouddata.IngredientRepository;
import com.onus.tacoclouddomain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {

	private final IngredientRepository ingredientRepo;

	@Autowired
	public IngredientController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@GetMapping
	public Iterable<Ingredient> allIngredients() {
		return ingredientRepo.findAll();
	}
}
