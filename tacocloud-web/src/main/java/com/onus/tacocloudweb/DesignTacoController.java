package com.onus.tacocloudweb;

import com.onus.tacoclouddata.IngredientRepository;
import com.onus.tacoclouddata.TacoRepository;
import com.onus.tacoclouddata.UserRepository;
import com.onus.tacoclouddomain.Ingredient;
import com.onus.tacoclouddomain.Ingredient.Type;
import com.onus.tacoclouddomain.Order;
import com.onus.tacoclouddomain.Taco;
import com.onus.tacoclouddomain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	private final TacoRepository designRepo;
	private final UserRepository userRepo;

	@Autowired
	public DesignTacoController(
		IngredientRepository ingredientRepo,
		TacoRepository designRepo,
		UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
		this.userRepo = userRepo;
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model, Principal principal) {
		log.info("   --- Designing taco");
		List<Ingredient> ingredients = new ArrayList<>();

		// populating the findAll results
		ingredientRepo.findAll().forEach(ingredients::add);

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
				filterByType(ingredients, type));
		}

		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user", user);

		return "design";
	}

	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors,
															@ModelAttribute Order order) {
		if (errors.hasErrors())
			return "design";

		// Save the taco design...
		// We'll do this in later
		log.info("Process design: " + design);

		Taco saved = designRepo.save(design);
		order.addDesign(saved);
		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
			.filter(x -> x.getType().equals(type))
			.collect(Collectors.toList());
	}
}
