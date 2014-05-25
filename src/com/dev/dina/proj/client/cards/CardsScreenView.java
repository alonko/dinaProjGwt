package com.dev.dina.proj.client.cards;

import com.dev.dina.proj.client.resources.ProjectResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CardsScreenView {
	private Widget root;
	private static CardsScreenUiBinder uiBinder = GWT
			.create(CardsScreenUiBinder.class);

	interface CardsScreenUiBinder extends UiBinder<Widget, CardsScreenView> {
	}

	@UiField(provided = true)
	Image card1, card2, card3, card4;

	@UiField
	Label resultLbl;

	public CardsScreenView() {
		ProjectResources resources = ProjectResources.INSTANCE;
		card1 = new Image(resources.cardImage());
		card1.setSize("200px", "400px");

		card2 = new Image(resources.cardImage());
		card2.setSize("200px", "400px");

		card3 = new Image(resources.cardImage());
		card3.setSize("200px", "400px");

		card4 = new Image(resources.cardImage());
		card4.setSize("200px", "400px");
		root = uiBinder.createAndBindUi(this);

	}

	public Widget asWidget() {
		return root;
	}
}