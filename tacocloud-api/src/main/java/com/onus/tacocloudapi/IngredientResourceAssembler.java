package com.onus.tacocloudapi;

import com.onus.tacoclouddomain.Ingredient;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class IngredientResourceAssembler extends
	RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {

	public IngredientResourceAssembler() {
		super(IngredientController.class, IngredientResource.class);
	}

	@Override
	public IngredientResource toModel(Ingredient ingredient) {
		return createModelWithId(ingredient.getId(), ingredient);
	}
}
