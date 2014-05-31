package com.dev.dina.proj.client.cards;

import com.dev.dina.proj.client.resources.ProjectResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CardsScreenView {
	private Widget root;
	@UiField
	FlowPanel mainContainer;

	@UiField(provided = true)
	Image card1, card2, card3, card4;

	@UiField
	Label resultValue, pointsAddedValue, pointsReducedValue, timerValue,
			timerLbl;

	private static CardsScreenUiBinder uiBinder = GWT
			.create(CardsScreenUiBinder.class);

	interface CardsScreenUiBinder extends UiBinder<Widget, CardsScreenView> {
	}

	public CardsScreenView() {
		ProjectResources resources = ProjectResources.INSTANCE;
		card1 = new Image(resources.cardImage());
		card2 = new Image(resources.cardImage());
		card3 = new Image(resources.cardImage());
		card4 = new Image(resources.cardImage());
		root = uiBinder.createAndBindUi(this);
	}

	public Widget asWidget() {
		return root;
	}

	public Image getCard1() {
		return card1;
	}

	public Image getCard2() {
		return card2;
	}

	public Image getCard3() {
		return card3;
	}

	public Image getCard4() {
		return card4;
	}

	public void setValueToResult(int result) {
		resultValue.setText(String.valueOf(result));
	}

	public void setValueToAddedPoints(int points) {
		pointsAddedValue.setText(String.valueOf(points));
	}

	public void setValueToReducedPoints(int points) {
		pointsReducedValue.setText(String.valueOf(points));
	}

	public void setTimer(int timeLeft) {
		timerValue.setText(String.valueOf(timeLeft));
	}

	public void setTimerVisible(Boolean isVisible) {
		timerValue.setVisible(isVisible);
		timerLbl.setVisible(isVisible);
	}

	public FlowPanel getMainContainer() {
		return mainContainer;
	}
}