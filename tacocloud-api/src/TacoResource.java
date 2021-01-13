package com.onus.tacocloudapi;

import com.onus.tacoclouddomain.Taco;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends EntityModel<TacoResource> {

	private static final IngredientResourceAssembler
		ingredientAssembler = new IngredientResourceAssembler();

	@Getter
	private final String name;

	@Getter
	private final Date createdAt;

	@Getter
	private final CollectionModel<IngredientResource> ingredients;

	public TacoResource(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
	}
}
