package com.onus.tacocloudapi;

import com.onus.tacoclouddomain.Taco;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class
TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

	public TacoResourceAssembler() {
		super(DesignTacoController.class, TacoResource.class);
	}

	@Override
	public TacoResource toModel(Taco taco) {
		return createModelWithId(taco.getId(), taco);
	}
}
