package com.dev.dina.proj.client.math;

import com.dev.dina.proj.client.main.AbstractTestPresenter;
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
		step++;
		if (step <= MAX_STEPS) {
			if (timer != null) {
				timer.cancel();
			}
			timeLeft = TEST_TIME;
			view.setTimer(timeLeft);
			updateTimer();
			view.setFirstValue(firstNumbersArray[step-1]);
			view.setSecondValue(secondNumbersArray[step-1]);
			view.setThirdValue(thirdNumbersArray[step-1]);
		} else {
			finishTest();
		}
	}

	@Override
	protected void finishTest() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateTimer() {
		// TODO Auto-generated method stub

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
