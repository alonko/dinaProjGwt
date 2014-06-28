package com.dev.dina.proj.client.cards;

import java.util.Date;

import com.dev.dina.proj.client.main.AbstractTestPresenter;
import com.dev.dina.proj.client.popup.MessageBox;
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
	private static final int MAX_STEPS = 7; // 50
	private static int TEST_TIME = 4;
	private static int PENALTY_POINTS = 300;

	private static int START_POINTS = 1000;
	private static int DECK_A_POSITIVE_POINTS = 100;
	private static int DECK_B_POSITIVE_POINTS = 100;
	private static int DECK_C_POSITIVE_POINTS = 50;
	private static int DECK_D_POSITIVE_POINTS = 50;

	private int totalWinAmount, totalLoseAmount, totalPoints;

	private int[] deckANegativePoints = { 0, 0, 150, 0, 300, 0, 200, 0, 250,
			350, 0, 350, 0, 250, 200, 0, 300, 150, 0, 0, 0, 300, 0, 350, 0,
			200, 250, 150, 0, 0, 350, 200, 250, 0, 0, 0, 150, 300, 0, 0, 0, 0,
			150, 0, 300, 0, 200, 0, 250, 350 };

	private int[] deckBNegativePoints = { 0, 0, 0, 0, 0, 0, 0, 0, 1250, 0, 0,
			0, 0, 1250, 0, 0, 0, 0, 0, 0, 1250, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			1250, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1250, 0 };

	private int[] deckCNegativePoints = { 0, 0, 50, 0, 50, 0, 50, 0, 50, 50, 0,
			25, 75, 0, 0, 0, 25, 75, 0, 50, 0, 0, 0, 50, 25, 50, 0, 0, 75, 50,
			0, 0, 0, 25, 25, 0, 75, 0, 50, 75, 0, 0, 50, 0, 50, 0, 50, 0, 50,
			50 };

	private int[] deckDNegativePoints = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 250, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 250, 0, 0, 0, 0, 0, 0, 0, 0, 250, 0, 0, 0, 0,
			0, 250, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 250 };

	private Integer selectedDeckNumber;

	public CardsScreenPresenter(Boolean isPresure, String examineeNumber) {
		super(isPresure, examineeNumber);
		view = new CardsScreenView();
		setVisibleView(false);
		view.setTimerVisible(false);
		addExportWidget("Cards");
		addCardClickHandlers();

		String message;
		if (isPresure) {
			message = constants.cardsPressureExamExplanation();
		} else {
			message = constants.cardsExamExplanation();
		}
		showExplanationScreen(constants.examExplanation(), message);
	}

	private void addCardClickHandlers() {
		view.getCard1().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(1);
				// cardClicked(DECK_A_POSITIVE_POINTS,
				// deckANegativePoints[step],
				// "A");
			}
		});

		view.getCard2().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(2);
				// cardClicked(DECK_B_POSITIVE_POINTS,
				// deckBNegativePoints[step],
				// "B");
			}
		});

		view.getCard3().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(3);
				// cardClicked(DECK_C_POSITIVE_POINTS,
				// deckCNegativePoints[step],
				// "C");
			}
		});

		view.getCard4().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cardClicked(4);
				// cardClicked(DECK_D_POSITIVE_POINTS,
				// deckDNegativePoints[step],
				// "D");
			}
		});

		view.setApproveClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (selectedDeckNumber != null) {
					if (selectedDeckNumber == 1) {
						cardClickApproved(DECK_A_POSITIVE_POINTS,
								deckANegativePoints[step], "A");
					} else if (selectedDeckNumber == 2) {
						cardClickApproved(DECK_B_POSITIVE_POINTS,
								deckBNegativePoints[step], "B");
					} else if (selectedDeckNumber == 3) {
						cardClickApproved(DECK_C_POSITIVE_POINTS,
								deckCNegativePoints[step], "C");
					} else {// (selectedDeckNumber == 4)
						cardClickApproved(DECK_D_POSITIVE_POINTS,
								deckDNegativePoints[step], "D");
					}
				} else {
					MessageBox messageBox = new MessageBox();
					messageBox.setCloseButtonVsisble(true);
					messageBox.setDescriptionText(constants.selectCard());
					messageBox.show();
				}
			}
		});
	}

	@Override
	protected void beginTest() {
		super.beginTest();
		selectedDeckNumber = null;
		view.setViewVisible(true);
		totalPoints = START_POINTS;
		totalWinAmount = 0;
		totalLoseAmount = 0;
		updatePoints(0, 0);

		Date now = new Date();
		addColumnToTable(constants.examineeNumberOutput(), examineeNumber);
		addColumnToTable(constants.dateOutput(), now.toString());
		addColumnToTable(constants.isPresureOutput(), isPresure.toString());

		view.setTimerVisible(isPresure);
		view.setTimer(TEST_TIME);
		updateTimer();
	}

	@Override
	protected void finishTest() {
		super.finishTest();
		testComplete = true;
		view.setTimerVisible(false);

		addColumnToTable(constants.totalWinAmountOutput(),
				String.valueOf(totalWinAmount));

		addColumnToTable(constants.totalLoseAmountOutput(),
				String.valueOf(totalLoseAmount));

		addColumnToTable(constants.totalAmountOutput(),
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

		selectedDeckNumber = null;
		view.unselectedAllDecks();
		view.setApproveButtonEnabled(false);
	}

	private void cardClicked(int selectedDeckNumber) {
		this.selectedDeckNumber = selectedDeckNumber;
		view.markDeckSelected(selectedDeckNumber);
		view.setApproveButtonEnabled(true);
	}

	private void cardClickApproved(int addedPoints, int reducedPoints,
			String deck) {
		updatePoints(addedPoints, reducedPoints);
		totalWinAmount += addedPoints;
		totalLoseAmount += reducedPoints;

		String questionNumner = String.valueOf(step + 1) + " ";
		addColumnToTable(questionNumner + constants.cardClickedTimeOutput(),
				new Date().toString());

		addColumnToTable(questionNumner + constants.questionTimeOutput(),
				String.valueOf(turnTime));

		addColumnToTable(questionNumner + constants.selectedDeckOutput(), deck);

		addColumnToTable(questionNumner + constants.winAmountOutput(),
				String.valueOf(addedPoints));

		addColumnToTable(questionNumner + constants.loseAmountOutput(),
				String.valueOf(reducedPoints));

		addColumnToTable(questionNumner + constants.turnTotalAmountOutput(),
				String.valueOf(addedPoints - reducedPoints));

		playTurn();
	}

	protected void updatePoints(int addedPoints, int reducedPoints) {
		view.setValueToAddedPoints(addedPoints);
		view.setValueToReducedPoints(reducedPoints);
		totalPoints += addedPoints - reducedPoints;
		view.setValueToCurrentResult(totalPoints);
	}

	@Override
	protected void updateTimer() {
		timer = new Timer() {
			@Override
			public void run() {
				turnTime++;
				if (isPresure) {
					if (turnTime > TEST_TIME) {
						final MessageBox messageBox = new MessageBox();
						messageBox.setCloseButtonVsisble(false);
						messageBox.setTitle(constants.timeUpTitle());
						messageBox.setDescriptionText(constants
								.timeUpDescription()
								+ " "
								+ PENALTY_POINTS
								+ " " + constants.points());
						messageBox.show();
						timer.cancel();

						final Timer messageTimer = new Timer() {
							@Override
							public void run() {
								messageBox.hide();
								cardClickApproved(0, PENALTY_POINTS, null);
								if (!testComplete) {
									timer.run();
								}
							}
						};
						messageTimer.schedule(2000);
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

	@Override
	protected void setVisibleView(boolean visible) {
		view.setVisibleView(visible);
	}
}
