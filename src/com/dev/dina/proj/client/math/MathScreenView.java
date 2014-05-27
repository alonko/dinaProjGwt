package com.dev.dina.proj.client.math;

import com.dev.dina.proj.client.resources.ProjectResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MathScreenView {
	private Widget root;
	private static CardsScreenUiBinder uiBinder = GWT
			.create(CardsScreenUiBinder.class);

	interface CardsScreenUiBinder extends UiBinder<Widget, MathScreenView> {
	}

	@UiField(provided = true)
	Image card1, card2, card3, card4;

	@UiField
	Label resultLbl, pointsLbl, timerValue, timerLbl;

	public MathScreenView() {
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
		resultLbl.setText(String.valueOf(result));
	}

	public void setValueToErnedPoints(int points) {
		pointsLbl.setText(String.valueOf(points));
	}

	public void setTimer(int timeLeft) {
		timerValue.setText(String.valueOf(timeLeft));
	}

	public void setTimerVisible(Boolean isVisible) {
		timerValue.setVisible(isVisible);
		timerLbl.setVisible(isVisible);
	}
}