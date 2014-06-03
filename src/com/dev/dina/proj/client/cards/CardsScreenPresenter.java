package com.dev.dina.proj.client.cards;

import com.dev.dina.proj.client.MessageBox;
import com.dev.dina.proj.client.events.AppUtils;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.dev.dina.proj.client.main.AbstractTestPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class CardsScreenPresenter extends AbstractTestPresenter {
	private CardsScreenView view;
	private static final int MAX_STEPS = 4;
	private static int TEST_TIME = 10;
	private static int PENALTY_POINTS = 10;
	
	private int[] firstDeckPositivePoints = {100, 200, 300, 400};
	private int[] firstDeckNegativePoints = {50, 300, 100, 0};
	private int[] secondDeckPositivePoints = {100, 200, 300, 400};
	private int[] secondDeckNegativePoints = {50, 300, 100, 0};
	private int[] thirdDeckPositivePoints = {100, 200, 300, 400};
	private int[] thirdDeckNegativePoints = {50, 300, 100, 0};
	private int[] forthDeckPositivePoints = {100, 200, 300, 400};
	private int[] forthDeckNegativePoints = {50, 300, 100, 0};
	
	public CardsScreenPresenter(Boolean isPresure) {
		super(isPresure);
		view = new CardsScreenView();
		addExportWidget();

		view.getCard1().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(firstDeckPositivePoints[step], firstDeckNegativePoints[step]);
			}
		});

		view.getCard2().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(secondDeckPositivePoints[step], secondDeckNegativePoints[step]);
			}
		});

		view.getCard3().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(thirdDeckPositivePoints[step], thirdDeckNegativePoints[step]);
			}
		});

		view.getCard4().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(forthDeckPositivePoints[step], forthDeckNegativePoints[step]);
			}
		});
		beginTest();
	}

	@Override
	protected void beginTest() {
		super.beginTest();
		view.setTimerVisible(isPresure);
		timeLeft = TEST_TIME;
		view.setTimer(timeLeft);
//		playTurn();
	}

	@Override
	protected void finishTest() {
		view.setTimerVisible(false);
		timer.cancel();
		final MessageBox messageBox = new MessageBox("test complete");
		messageBox.setDescriptionText("test desc");
		messageBox.setText(String.valueOf(totalPoints));
		messageBox.center();
		messageBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageBox.hide();
				AppUtils.EVENT_BUS.fireEvent(new TestCompleteEvent());
				exportWidget.performExport();

			}
		});
	}

	private void cardClicked(int addedPoints, int reducedPoints) {
		updatePoints(addedPoints, reducedPoints);
		playTurn();
	}

	@Override
	protected void updatePoints(int addedPoints, int reducedPoints) {
		view.setValueToAddedPoints(addedPoints);
		view.setValueToReducedPoints(reducedPoints);
		totalPoints += addedPoints + reducedPoints;
		view.setValueToResult(totalPoints);
	}

	private void playTurn() {
		step++;
		if (step < MAX_STEPS) {
			if (timer != null) {
				timer.cancel();
			}
			timeLeft = TEST_TIME;
			view.setTimer(timeLeft);
			updateTimer();
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

	@Override
	protected FlowPanel getMmainContainer() {
		return view.getMainContainer();
	}

	public Widget getWidget() {
		return view.asWidget();
	}
}
