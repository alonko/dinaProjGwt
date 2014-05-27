package com.dev.dina.proj.client.cards;

import com.dev.dina.proj.client.MessageBox;
import com.dev.dina.proj.client.events.AppUtils;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.dev.dina.proj.client.main.AbstractPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

public class CardsScreenPresenter extends AbstractPresenter{
	private CardsScreenView view;
	private static final int MAX_STEPS = 4;
	private static int TEST_TIME = 10;
	private static int PENALTY_POINTS = 10;
	

	public CardsScreenPresenter(Boolean isPresure) {
		this.isPresure = isPresure;
		view = new CardsScreenView();

		view.getCard1().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(100, -200);
			}
		});

		view.getCard2().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(200, 0);
			}
		});

		view.getCard3().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(100,-300);
			}
		});

		view.getCard4().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(200,-400);
			}
		});
		beginTest();
	}

	@Override
	protected void beginTest() {
		totalPoints = 0;
		step = 0;
		updatePoints(0, 0);
		view.setTimerVisible(isPresure);
		timeLeft = TEST_TIME;
		view.setTimer(timeLeft);
		playTurn();
	}

	@Override
	protected void finishTest() {
		view.setTimerVisible(false);
		timer.cancel();
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

	private void cardClicked(int addedPoints, int reducedPoints) {		
		updatePoints(addedPoints, reducedPoints);
		playTurn();
	}

	private void updatePoints(int addedPoints, int reducedPoints) {
		view.setValueToAddedPoints(addedPoints);
		view.setValueToReducedPoints(reducedPoints);
		totalPoints += addedPoints + reducedPoints;
		view.setValueToResult(totalPoints);
	}

	private void playTurn() {
		step++;
		if (step <= MAX_STEPS) {
			if (isPresure) {
				if (timer != null) {
					timer.cancel();
				}
				timeLeft = TEST_TIME;
				view.setTimer(timeLeft);
				updateTimer();
			}
		} else {
			finishTest();
		}
	}

	@Override
	protected void updateTimer() {
		timer = new Timer() {
			@Override
			public void run() {
				timeLeft--;
				if (timeLeft < 0) {
					int reducedPoints = -PENALTY_POINTS;
					updatePoints(0, reducedPoints);
					playTurn();
					this.cancel(); // cancel the timer -- important!
				} else {
					view.setTimer(timeLeft);
				}
			}
		};
		timer.scheduleRepeating(1000);
	}

	public Widget gwtWidget() {
		return view.asWidget();
	}
}
