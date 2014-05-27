package com.dev.dina.proj.client.cards;

import com.dev.dina.proj.client.MessageBox;
import com.dev.dina.proj.client.events.AppUtils;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

public class CardsScreenPresenter {
	private CardsScreenView view;
	private int totalPoints;
	private int step;
	private int timeLeft;
	private Boolean isPresure;

	private static final int MAX_STEPS = 4;
	private static int TEST_TIME = 10;
	private static int PENALTY_POINTS = 10;
	private Timer timer;

	public CardsScreenPresenter(Boolean isPresure) {
		this.isPresure = isPresure;
		view = new CardsScreenView();		

		view.getCard1().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(100);
			}
		});

		view.getCard2().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(200);
			}
		});

		view.getCard3().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(-300);
			}
		});

		view.getCard4().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(-400);
			}
		});
		beginTest();
	}

	private void beginTest() {
		totalPoints = 0;
		step = 0;
		updatePoints(0);
		view.setTimerVisible(isPresure);
		timeLeft = TEST_TIME;
		view.setTimer(timeLeft);
		playTurn();
	}

	private void finishTest() {
		view.setTimerVisible(false);
		final MessageBox messageBox = new MessageBox("test complete");
		messageBox.setResult(totalPoints);
		messageBox.show();
		messageBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageBox.hide();
				AppUtils.EVENT_BUS.fireEvent(new TestCompleteEvent());
			}
		});
	}

	private void cardClicked(int cardValue) {
		int value = cardValue;
		updatePoints(value);
		playTurn();
	}
	
	private void updatePoints(int value) {
		view.setValueToErnedPoints(value);
		totalPoints += value;
		view.setValueToResult(totalPoints);
	}

	private void playTurn() {
		step++;
		if (step <= MAX_STEPS) {
			if (isPresure) {
				timeLeft = TEST_TIME;
				updateTimer();
			}
		} else {
			finishTest();
		}
	}

	private void updateTimer() {
		timer = new Timer() {
			@Override
			public void run() {
				timeLeft--;
				if (timeLeft == 0) {
					int value = -PENALTY_POINTS;
					updatePoints(value);
					playTurn();
				} else {
					view.setTimer(timeLeft);
					updateTimer();
				}
			}
		};
		timer.schedule(1000);
	}

	public Widget gwtWidget() {
		return view.asWidget();
	}
}
