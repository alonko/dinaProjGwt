package com.dev.dina.proj.client.math;

import java.util.Date;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.events.AnswerRecivedEvent;
import com.dev.dina.proj.client.events.AnswerRecivedHandler;
import com.dev.dina.proj.client.main.AbstractTestPresenter;
import com.dev.dina.proj.client.main.AppUtils;
import com.dev.dina.proj.client.popup.MessageBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class MathScreenPresenter extends AbstractTestPresenter {
	private MathScreenView view;
	private static int TEST_TIME = 10;
	private static final int MAX_STEPS = 4;
	private static final int PREVIEW_CORRECT_ANSWERS = 2;
	private int numberOfCorrectAnswers;

	private int[] firstNumbersArray = { 100, 200, 300, 400, 500, 600, 700, 800,
			900, 1000 };
	private int[] secondNumbersArray = { 200, 300, 400, 500, 600, 700, 800,
			900, 1000, 1100 };
	private int[] thirdNumbersArray = { 300, 400, 500, 600, 700, 800, 900,
			1000, 1100, 1200 };
	private Boolean isPreview;

	public MathScreenPresenter(Boolean isPresure, String examineeNumber) {
		super(isPresure, examineeNumber);
		isPreview = true;
		view = new MathScreenView();
		addExportWidget("Math");
		setHandlers();
		showExplanationScreen(MyConstants.INSTANCE.examExplanation(),MyConstants.INSTANCE.mathExamExplanation());		
	}

	private void setHandlers() {
		EventBus eventBus = AppUtils.EVENT_BUS;
		eventBus.addHandler(AnswerRecivedEvent.TYPE,
				new AnswerRecivedHandler() {
					@Override
					public void onAnswerRecived(
							AnswerRecivedEvent authenticationEvent) {
						view.getApproveBtn().click();
					}
				});

		view.getApproveBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Boolean isCorrectAnswer = isCorrectAnswer();
				if (isCorrectAnswer) {
					numberOfCorrectAnswers++;
				} else if (isPreview) {
					numberOfCorrectAnswers = 0;
				}

				if (!isPreview) {
					updateOutputFile(isCorrectAnswer);
				}
				view.clearValue();
				playTurn();
			}

			private void updateOutputFile(Boolean isCorrectAnswer) {
				String questionNumner = String.valueOf(step) + " ";
				addColumnToTable(
						questionNumner
								+ MyConstants.INSTANCE.questionTimeOutput(),
						String.valueOf(turnTime));
				addColumnToTable(
						questionNumner
								+ MyConstants.INSTANCE.examineeAnswerOutput(),
						view.getAnswer());
				addColumnToTable(
						questionNumner
								+ MyConstants.INSTANCE.isCorrectAnswerOutput(),
						isCorrectAnswer.toString());
			}
		});
	}

	private Boolean isCorrectAnswer() {
		int firstValue = Integer.parseInt(view.getFirstValue());
		int secondValue = Integer.parseInt(view.getSecondValue());
		int thirdValue = Integer.parseInt(view.getThirdValue());
		int correctAnswer = firstValue + secondValue + thirdValue;

		String userAnswer = view.getAnswer();
		if (!userAnswer.isEmpty()) {
			if (correctAnswer == Integer.parseInt(userAnswer)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	protected void beginTest() {
		super.beginTest();
		numberOfCorrectAnswers = 0;
		if (!isPreview) {
			Date now = new Date();
			addColumnToTable(MyConstants.INSTANCE.examineeNumberOutput(),
					examineeNumber);
			addColumnToTable(MyConstants.INSTANCE.dateOutput(), now.toString());
			addColumnToTable(MyConstants.INSTANCE.isPresureOutput(),
					isPresure.toString());
		}
		view.setTimerVisible(isPresure);
		playTurn();
	}

	@Override
	protected void finishTest() {
		super.finishTest();
		view.setTimerVisible(false);
		addColumnToTable(MyConstants.INSTANCE.numberOfCorrectAnswersOutput(),
				String.valueOf(numberOfCorrectAnswers));		
	}

	private void playTurn() {
		view.setFocusOnFirst();
		totalTestTime += turnTime;
		turnTime = 0;
		if (isPresure) {
			view.setTimer(TEST_TIME);
		}
		if (timer != null) {
			timer.cancel();
		}
		updateTimer();

		if (!isPreview) {
			if (step <= MAX_STEPS) {
				view.setFirstValue(firstNumbersArray[step]);
				view.setSecondValue(secondNumbersArray[step]);
				view.setThirdValue(thirdNumbersArray[step]);
				step++;
			} else {
				finishTest();
			}
		} else { // preview
			if (numberOfCorrectAnswers < PREVIEW_CORRECT_ANSWERS) {
				view.setFirstValue(getRandomNumber(100, 999));
				view.setSecondValue(getRandomNumber(100, 999));
				view.setThirdValue(getRandomNumber(100, 999));
				step++;
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

	private int getRandomNumber(int min, int max) {
		return (min + (int) (Math.random() * ((max - min) + 1)));
	}

	@Override
	protected void updateTimer() {
		timer = new Timer() {
			@Override
			public void run() {
				turnTime++;
				if (isPresure) {
					if (turnTime < TEST_TIME) {
						view.setTimer(TEST_TIME - turnTime);
					} else {
						view.getApproveBtn().click();						
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
