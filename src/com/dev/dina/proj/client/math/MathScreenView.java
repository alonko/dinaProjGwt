package com.dev.dina.proj.client.math;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MathScreenView {
	private Widget root;
	private static CardsScreenUiBinder uiBinder = GWT
			.create(CardsScreenUiBinder.class);

	interface CardsScreenUiBinder extends UiBinder<Widget, MathScreenView> {
	}

	@UiField
	FlowPanel mainContainer;

	@UiField
	Label firstNumberLbl, secondNumberLbl, thirdNumberLbl;

	@UiField
	Label timerValue, timerLbl;

	@UiField
	TextBox firstAnswer, secondAnswer, thirdAnswer, forthAnswer;

	public MathScreenView() {

		root = uiBinder.createAndBindUi(this);
		firstAnswer.setMaxLength(1);
		secondAnswer.setMaxLength(1);
		thirdAnswer.setMaxLength(1);
		forthAnswer.setMaxLength(1);

		firstAnswer.setTabIndex(1);
		secondAnswer.setTabIndex(2);
		thirdAnswer.setTabIndex(3);
		forthAnswer.setTabIndex(4);

		firstAnswer.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				secondAnswer.setFocus(true);
			}
		});

		secondAnswer.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				thirdAnswer.setFocus(true);
			}
		});
		
		thirdAnswer.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				forthAnswer.setFocus(true);
			}
		});

		// Widget[] objects = new Widget[4];
		// objects[0] = firstAnswer;
		// objects[1] = secondAnswer;
		// objects[2] = thirdAnswer;
		// objects[3] = forthAnswer;
	}

	public Widget asWidget() {
		return root;
	}

	public String getFirstValue() {
		return firstNumberLbl.getText();
	}

	public String getSecondValue() {
		return secondNumberLbl.getText();
	}

	public String getThirdValue() {
		return thirdNumberLbl.getText();
	}

	public void setFirstValue(int value) {
		firstNumberLbl.setText(String.valueOf(value));
	}

	public void setSecondValue(int value) {
		secondNumberLbl.setText(String.valueOf(value));
	}

	public void setThirdValue(int value) {
		thirdNumberLbl.setText(String.valueOf(value));
	}

	// public void setValueToResult(int result) {
	// resultLbl.setText(String.valueOf(result));
	// }
	//
	// public void setValueToErnedPoints(int points) {
	// pointsLbl.setText(String.valueOf(points));
	// }

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