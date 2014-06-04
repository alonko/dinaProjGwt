package com.dev.dina.proj.client.cards;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.events.AppUtils;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.dev.dina.proj.client.main.AbstractTestPresenter;
import com.dev.dina.proj.client.popup.MessageBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class CardsScreenPresenter extends AbstractTestPresenter {
	private CardsScreenView view;
	private static final int MAX_STEPS = 4;
	private static final int MAX_PREVIEW_STEPS = 4;
	private static int TEST_TIME = 10;
	private static int PENALTY_POINTS = 10;
	private Boolean isPreview;

	private int[] previewFirstDeckPositivePoints = { 10, 20, 30, 40 };
	private int[] previewFirstDeckNegativePoints = { 5, 30, 10, 0 };
	private int[] previewSecondDeckPositivePoints = { 10, 20, 30, 40 };
	private int[] previewSecondDeckNegativePoints = { 5, 30, 10, 0 };
	private int[] previewThirdDeckPositivePoints = { 10, 20, 30, 40 };
	private int[] previewThirdDeckNegativePoints = { 5, 30, 10, 0 };
	private int[] previewForthDeckPositivePoints = { 10, 20, 30, 40 };
	private int[] previewForthDeckNegativePoints = { 5, 30, 10, 0 };

	private int[] firstDeckPositivePoints = { 100, 200, 300, 400 };
	private int[] firstDeckNegativePoints = { 50, 300, 100, 0 };
	private int[] secondDeckPositivePoints = { 100, 200, 300, 400 };
	private int[] secondDeckNegativePoints = { 50, 300, 100, 0 };
	private int[] thirdDeckPositivePoints = { 100, 200, 300, 400 };
	private int[] thirdDeckNegativePoints = { 50, 300, 100, 0 };
	private int[] forthDeckPositivePoints = { 100, 200, 300, 400 };
	private int[] forthDeckNegativePoints = { 50, 300, 100, 0 };

	public CardsScreenPresenter(Boolean isPresure) {
		super(isPresure);
		isPreview = true;
		view = new CardsScreenView();
		addExportWidget();
		addCardClickHandlers();
		
		final MessageBox msgBox = new MessageBox(MyConstants.INSTANCE.examExplanation(), MyConstants.INSTANCE.cardsExamExplanation()); 
		msgBox.asWidget().setSize("700px", "400px");
		msgBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				msgBox.hide();
				beginTest();
			}
		});
		msgBox.show();
	}

	private void addCardClickHandlers() {
		view.getCard1().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!isPreview) {
					cardClicked(firstDeckPositivePoints[step],
							firstDeckNegativePoints[step]);
				} else {
					cardClicked(previewFirstDeckPositivePoints[step],
							previewFirstDeckNegativePoints[step]);
				}
			}
		});

		view.getCard2().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!isPreview) {
					cardClicked(secondDeckPositivePoints[step],
							secondDeckNegativePoints[step]);
				} else {
					cardClicked(previewSecondDeckPositivePoints[step],
							previewSecondDeckNegativePoints[step]);
				}
			}
		});

		view.getCard3().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!isPreview) {
					cardClicked(thirdDeckPositivePoints[step],
							thirdDeckNegativePoints[step]);
				} else {
					cardClicked(previewThirdDeckPositivePoints[step],
							previewThirdDeckNegativePoints[step]);
				}
			}
		});

		view.getCard4().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!isPreview) {
					cardClicked(forthDeckPositivePoints[step],
							forthDeckNegativePoints[step]);
				} else {
					cardClicked(previewForthDeckPositivePoints[step],
							previewForthDeckNegativePoints[step]);
				}
			}
		});
	}

	@Override
	protected void beginTest() {
		super.beginTest();
		view.setTimerVisible(isPresure);
		timeLeft = TEST_TIME;
		view.setTimer(timeLeft);
		updateTimer();
	}

	@Override
	protected void finishTest() {
		view.setTimerVisible(false);
		timer.cancel();
		final MessageBox messageBox = new MessageBox("test complete");
		messageBox.setTitle(MyConstants.INSTANCE.testComplete());
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

	private void cardClicked(int addedPoints, int reducedPoints) {
		updatePoints(addedPoints, reducedPoints);
		playTurn();
	}

	@Override
	protected void updatePoints(int addedPoints, int reducedPoints) {
		view.setValueToAddedPoints(addedPoints);
		view.setValueToReducedPoints(reducedPoints);
		totalPoints += addedPoints - reducedPoints;
		view.setValueToResult(totalPoints);
	}

	private void playTurn() {
		step++;
		if (timer != null) {
			timer.cancel();
		}
		if (!isPreview) {
			if (step < MAX_STEPS) {
				timeLeft = TEST_TIME;
				view.setTimer(timeLeft);
				updateTimer();
			} else {
				finishTest();
			}
		} else {
			if (step < MAX_PREVIEW_STEPS) {
				timeLeft = TEST_TIME;
				view.setTimer(timeLeft);
				updateTimer();
			} else {
				isPreview = false;
				final MessageBox msgBox = new MessageBox(
						MyConstants.INSTANCE.previewComplete());
				msgBox.setCloseButtonHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						step = 0;
						beginTest();
						msgBox.hide();
					}
				});

				msgBox.show();
			}
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
