package com.dev.dina.proj.client.cards;

import java.util.Date;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.main.AbstractTestPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alon Kodner
 */

public class CardsScreenPresenter extends AbstractTestPresenter {
	private CardsScreenView view;
	private static final int MAX_STEPS = 4;
	private static int TEST_TIME = 3;
	private static int PENALTY_POINTS = 300;

	private int totalWinAmount, totalLoseAmount, totalPoints;

	private int[] firstDeckPositivePoints = { 100, 200, 300, 400 };
	private int[] firstDeckNegativePoints = { 50, 300, 100, 0 };
	private int[] secondDeckPositivePoints = { 100, 200, 300, 400 };
	private int[] secondDeckNegativePoints = { 50, 300, 100, 0 };
	private int[] thirdDeckPositivePoints = { 100, 200, 300, 400 };
	private int[] thirdDeckNegativePoints = { 50, 300, 100, 0 };
	private int[] forthDeckPositivePoints = { 100, 200, 300, 400 };
	private int[] forthDeckNegativePoints = { 50, 300, 100, 0 };

	public CardsScreenPresenter(Boolean isPresure, String examineeNumber) {
		super(isPresure, examineeNumber);
		view = new CardsScreenView();
		addExportWidget("Cards");
		addCardClickHandlers();

		showExplanationScreen(MyConstants.INSTANCE.examExplanation(),
				MyConstants.INSTANCE.cardsExamExplanation());
	}

	private void addCardClickHandlers() {
		view.getCard1().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(firstDeckPositivePoints[step],
						firstDeckNegativePoints[step], "A");
			}
		});

		view.getCard2().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(secondDeckPositivePoints[step],
						secondDeckNegativePoints[step], "B");
			}
		});

		view.getCard3().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(thirdDeckPositivePoints[step],
						thirdDeckNegativePoints[step], "C");
			}
		});

		view.getCard4().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(forthDeckPositivePoints[step],
						forthDeckNegativePoints[step], "D");
			}
		});
	}

	@Override
	protected void beginTest() {
		super.beginTest();
		totalPoints = 0;
		totalWinAmount = 0;
		totalLoseAmount = 0;
		updatePoints(0, 0);

		Date now = new Date();
		addColumnToTable(MyConstants.INSTANCE.examineeNumberOutput(),
				examineeNumber);
		addColumnToTable(MyConstants.INSTANCE.dateOutput(), now.toString());
		addColumnToTable(MyConstants.INSTANCE.isPresureOutput(),
				isPresure.toString());

		view.setTimerVisible(isPresure);
		view.setTimer(TEST_TIME);
		updateTimer();
	}

	@Override
	protected void finishTest() {
		super.finishTest();
		view.setTimerVisible(false);

		addColumnToTable(MyConstants.INSTANCE.totalWinAmountOutput(),
				String.valueOf(totalWinAmount));

		addColumnToTable(MyConstants.INSTANCE.totalLoseAmountOutput(),
				String.valueOf(totalLoseAmount));

		addColumnToTable(MyConstants.INSTANCE.totalAmountOutput(),
				String.valueOf(totalPoints));
	}

	private void playTurn() {
		step++;
		if (timer != null) {
			timer.cancel();
		}
		totalTestTime += turnTime;

		if (step < MAX_STEPS) {
			turnTime = 0;
			if (isPresure) {
				view.setTimer(TEST_TIME);
			}
			updateTimer();
		} else {
			finishTest();
		}
	}

	private void cardClicked(int addedPoints, int reducedPoints, String deck) {
		updatePoints(addedPoints, reducedPoints);
		totalWinAmount += addedPoints;
		totalLoseAmount += reducedPoints;

		String questionNumner = String.valueOf(step + 1) + " ";
		addColumnToTable(
				questionNumner + MyConstants.INSTANCE.questionTimeOutput(),
				String.valueOf(turnTime));

		addColumnToTable(
				questionNumner + MyConstants.INSTANCE.selectedDeckOutput(),
				deck);

		addColumnToTable(
				questionNumner + MyConstants.INSTANCE.winAmountOutput(),
				String.valueOf(addedPoints));

		addColumnToTable(
				questionNumner + MyConstants.INSTANCE.loseAmountOutput(),
				String.valueOf(reducedPoints));
		
		addColumnToTable(
				questionNumner + MyConstants.INSTANCE.turnTotalAmountOutput(),
				String.valueOf(addedPoints - reducedPoints));

		playTurn();
	}

	protected void updatePoints(int addedPoints, int reducedPoints) {
		view.setValueToAddedPoints(addedPoints);
		view.setValueToReducedPoints(reducedPoints);
		totalPoints += addedPoints - reducedPoints;
		view.setValueToResult(totalPoints);
	}

	@Override
	protected void updateTimer() {
		timer = new Timer() {
			@Override
			public void run() {
				turnTime++;
				if (isPresure) {
					if (turnTime > TEST_TIME) {
						cardClicked(0, PENALTY_POINTS, null);
					} else {
						view.setTimer(TEST_TIME - turnTime);
					}
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
