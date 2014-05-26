package com.dev.dina.proj.client.cards;

import com.dev.dina.proj.client.MessageBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

public class CardsScreenPresenter {
	private CardsScreenView view;
	private int totalPoints;
	private int steps;
	private static final int MAX_STEPS = 4;

	public CardsScreenPresenter() {
		view = new CardsScreenView();
		totalPoints = 0;
		steps = 0;
		
		view.getCard1().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int value = 100;
				view.setValueTopoints(value);
				totalPoints += value;
				view.setValueToResult(totalPoints);
				steps++;
				if(steps >= MAX_STEPS){
					final MessageBox messageBox = new MessageBox("test complete");
					messageBox.setResult(totalPoints);
					messageBox.show();
					messageBox.setCloseButtonHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							messageBox.hide();
						}
					});
				}
			}
		});
	}

	public Widget gwtWidget() {
		return view.asWidget();
	}
}
