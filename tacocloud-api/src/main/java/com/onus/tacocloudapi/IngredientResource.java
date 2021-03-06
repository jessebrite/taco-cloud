package com.onus.tacocloudapi;

import com.onus.tacoclouddomain.Ingredient;
import com.onus.tacoclouddomain.Ingredient.Type;
import lombok.Getter;
import org.springframework.hateoas.EntityModel;

public class IngredientResource extends EntityModel<IngredientResource> {

	@Getter
	private final String name;

	@Getter
	private final Type type;

	public IngredientResource(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}
}
