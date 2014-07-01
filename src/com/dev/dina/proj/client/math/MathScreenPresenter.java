package com.dev.dina.proj.client.math;

import java.util.Date;

import com.dev.dina.proj.client.events.AnswerRecivedEvent;
import com.dev.dina.proj.client.events.AnswerRecivedHandler;
import com.dev.dina.proj.client.main.AbstractTestPresenter;
import com.dev.dina.proj.client.main.AppUtils;
import com.dev.dina.proj.client.popup.MessageBox;
import com.dev.dina.proj.client.popup.StressMeterMessageBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alon Kodner
 */

public class MathScreenPresenter extends AbstractTestPresenter {
	private MathScreenView view;
	private static int TEST_TIME = 17;
	private static final int MAX_STEPS = 30;
	private static final int HALF_STEPS = Math.round(MAX_STEPS / 2);
	private static final int PREVIEW_CORRECT_ANSWERS = 2;
	private int numberOfCorrectAnswers;

	private int[] firstNumbers = { 801, 903, 414, 490, 115, 975, 711, 147, 148,
			748, 683, 346, 627, 279, 826, 229, 482, 716, 114, 125, 775, 409,
			338, 638, 167, 498, 521, 238, 426, 116 };

	private int[] secondNumbers = { 483, 534, 783, 321, 385, 837, 998, 311,
			982, 227, 986, 224, 240, 401, 597, 880, 997, 370, 603, 562, 387,
			222, 438, 465, 587, 330, 150, 398, 928, 677 };

	private int[] thirdNumbers = { 975, 470, 600, 966, 269, 957, 831, 585, 218,
			417, 474, 634, 708, 135, 944, 653, 756, 583, 438, 504, 293, 882,
			716, 912, 658, 420, 138, 426, 367, 196 };

	private int[] previewFirstNumbers = { 602, 927, 519, 863, 400, 875, 646,
			778, 600, 367, 227, 957, 396, 890, 248, 150, 285, 614, 325, 594,
			563, 893, 425, 491, 702, 139, 673, 722, 519, 493 };

	private int[] previewSecondNumbers = { 802, 256, 325, 135, 984, 475, 803,
			118, 768, 736, 706, 388, 859, 754, 399, 258, 809, 434, 871, 587,
			579, 918, 309, 202, 370, 268, 200, 139, 445, 238 };

	private int[] previewThirdNumbers = { 965, 963, 883, 694, 828, 825, 977,
			325, 244, 780, 163, 495, 656, 831, 631, 993, 200, 882, 369, 562,
			396, 246, 538, 643, 107, 537, 174, 724, 404, 322 };

	public MathScreenPresenter(Boolean isPresure, String examineeNumber,
			Boolean isPreview) {
		super(isPresure, examineeNumber);
		this.isPreview = isPreview;
		view = new MathScreenView();
		setVisibleView(false);
		setHandlers();
		view.setTimerVisible(false);

		String fileName;
		if (isPreview) {
			fileName = "Preview_Math";
		} else {
			fileName = "Math";
		}
		addExportWidget(fileName);

		String message;
		if (isPreview) {
			message = constants.mathPreviewExamExplanation();
		} else if (isPresure) {
			message = constants.mathPressureExamExplanation();
		} else {
			message = constants.mathExamExplanation();
		}
		showExplanationScreen(constants.examExplanation(), message);
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
				String questionNumner = String.valueOf(step) + " ";
				addColumnToTable(
						questionNumner
								+ constants.mathQuestionAnsweredTimeOutput(),
						new Date().toString());

				Boolean showMessage = false;
				final Boolean isCorrectAnswer = isCorrectAnswer();
				final MessageBox messageBox = new MessageBox();
				messageBox.setCloseButtonVsisble(false);
				if (isCorrectAnswer) {
					numberOfCorrectAnswers++;
					if (!isPresure) {
						showMessage = true;
						messageBox.setDescriptionText(
								constants.correctAnswer(), true);
						messageBox.show();
					}
				} else {// incorrect answer
					showMessage = true;
					messageBox.setDescriptionText(constants.incorrectAnswer(),
							false);
					messageBox.show();
					if (isPreview) {
						numberOfCorrectAnswers = 0;
					}
				}

				if (!showMessage) {
					finishTurn(isCorrectAnswer);
				} else {
					Timer timer = new Timer() {
						@Override
						public void run() {
							messageBox.hide();
							finishTurn(isCorrectAnswer);
						}
					};
					timer.schedule(1000);
				}

			}

			private void finishTurn(final Boolean isCorrectAnswer) {
				if (!isPreview) {
					updateOutputFile(isCorrectAnswer);
				}
				view.clearValue();

				if (!isPreview && step == HALF_STEPS) {
					if (timer != null) {
						timer.cancel();
					}

					final MessageBox message = new MessageBox();
					String userMessage;
					if (isPresure) {
						userMessage = constants.middleMessagePressure();
					} else {
						userMessage = constants.middleMessageRegular();
					}
					message.setDescriptionText(userMessage);
					message.setCloseButtonHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							message.hide();
							setVisibleView(true);
							playTurn();							
						}
					});

					final StressMeterMessageBox stressMsg = new StressMeterMessageBox();
					stressMsg.setCloseButtonHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							int selectedStressValue = stressMsg
									.getSelectedStressValue();
							addColumnToTable(
									constants.selectedMiddleStressLevelOutput(),
									String.valueOf(selectedStressValue));
							stressMsg.hide();
							message.show();
						}
					});
					setVisibleView(false);
					stressMsg.show();
				} else {
					playTurn();
				}
			}

			private void updateOutputFile(Boolean isCorrectAnswer) {
				String questionNumner = String.valueOf(step) + " ";
				addColumnToTable(
						questionNumner + constants.questionTimeOutput(),
						String.valueOf(turnTime));
				addColumnToTable(
						questionNumner + constants.examineeAnswerOutput(),
						view.getAnswer());
				addColumnToTable(
						questionNumner + constants.isCorrectAnswerOutput(),
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
		view.setViewVisible(true);
		step = 0;
		numberOfCorrectAnswers = 0;

		Date now = new Date();
		addColumnToTable(constants.examineeNumberOutput(), examineeNumber);
		addColumnToTable(constants.dateOutput(), now.toString());
		if (!isPreview) {
			addColumnToTable(constants.isPresureOutput(), isPresure.toString());
		}
		if (isPreview) {
			view.setTimerVisible(false);
		} else {
			view.setTimerVisible(isPresure);
		}
		playTurn();
	}

	@Override
	protected void finishTest() {
		super.finishTest();
		view.setTimerVisible(false);
		if (!isPreview) {
			addColumnToTable(constants.numberOfCorrectAnswersOutput(),
					String.valueOf(numberOfCorrectAnswers));
		} else {
			addColumnToTable(constants.numberOfStepsOutput(),
					String.valueOf(step));
		}
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
			if (step < MAX_STEPS) {
				view.setFirstValue(firstNumbers[step]);
				view.setSecondValue(secondNumbers[step]);
				view.setThirdValue(thirdNumbers[step]);
				step++;
			} else {
				finishTest();
			}
		} else { // preview
			if (numberOfCorrectAnswers < PREVIEW_CORRECT_ANSWERS) {
				view.setFirstValue(previewFirstNumbers[step]);
				view.setSecondValue(previewSecondNumbers[step]);
				view.setThirdValue(previewThirdNumbers[step]);
				step++;
			} else {
				finishTest();
			}
		}
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

	@Override
	protected void setVisibleView(boolean visible) {
		view.setVisibleView(visible);
	}
}
