package com.dev.dina.proj.client.cards;

import com.google.gwt.user.client.ui.Widget;

public class CardsScreenPresenter {
	private CardsScreenView view;

	public CardsScreenPresenter() {
		view = new CardsScreenView();
	}

	public Widget gwtWidget() {
		return view.asWidget();
	}
}
