package com.dev.dina.proj.client.math;

import com.dev.dina.proj.client.events.AppUtils;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.dev.dina.proj.client.main.AbstractTestPresenter;
import com.dev.dina.proj.client.popup.MessageBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class MathScreenPresenter extends AbstractTestPresenter {
	private MathScreenView view;
	private static int TEST_TIME = 10;
	private static final int MAX_STEPS = 4;
	private int[] firstNumbersArray = { 100, 200, 300, 400, 500, 600, 700, 800,
			900, 1000 };
	private int[] secondNumbersArray = { 200, 300, 400, 500, 600, 700, 800,
			900, 1000, 1100 };
	private int[] thirdNumbersArray = { 300, 400, 500, 600, 700, 800, 900,
			1000, 1100, 1200 };

	public MathScreenPresenter(Boolean isPresure) {
		super(isPresure);
		view = new MathScreenView();
		addExportWidget();

		view.getApproveBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				playTurn();
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
		playTurn();
	}

	private void playTurn() {
		view.setFocusOnFirst();
		if (step <= MAX_STEPS) {
			if (timer != null) {
				timer.cancel();
			}
			timeLeft = TEST_TIME;
			view.setTimer(timeLeft);
			updateTimer();
			view.setFirstValue(firstNumbersArray[step]);
			view.setSecondValue(secondNumbersArray[step]);
			view.setThirdValue(thirdNumbersArray[step]);
			step++;
		} else {
			finishTest();
		}
	}

	@Override
	protected void finishTest() {
		view.setTimerVisible(false);
		timer.cancel();
		final MessageBox messageBox = new MessageBox("test complete");
		messageBox.show();
		messageBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageBox.hide();
				AppUtils.EVENT_BUS.fireEvent(new TestCompleteEvent());
				exportWidget.performExport();
			}
		});
	}

	@Override
	protected void updateTimer() {
		timer = new Timer() {
			@Override
			public void run() {
				timeLeft--;
				if (timeLeft < 0) {
					
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
	protected void updatePoints(int addedPoints, int reducedPoints) {
		// TODO Auto-generated method stub

	}

	@Override
	protected FlowPanel getMmainContainer() {
		return view.getMainContainer();
	}

	public Widget getWidget() {
		return view.asWidget();
	}
}
