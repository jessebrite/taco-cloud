package com.onus.tacocloudapi;

import com.onus.tacoclouddomain.Taco;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

public class TacoResource extends RepresentationModel<TacoResource> {

	private static final IngredientResourceAssembler
		ingredientAssembler = new IngredientResourceAssembler();

	@Getter
	private String name;

	@Getter
	private final Date createdAt;

	@Getter
	private final CollectionModel<IngredientResource> ingredients;

	public TacoResource(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
	}

	@Autowired
	public TacoResource(Date createdAt, CollectionModel<IngredientResource> ingredients) {
		this.createdAt = createdAt;
		this.ingredients = ingredients;
	}
}
