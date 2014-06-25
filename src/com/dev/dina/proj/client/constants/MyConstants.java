package com.dev.dina.proj.client.constants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

/**
 * @author Alon Kodner
 */

public interface MyConstants extends Constants {
	static final MyConstants INSTANCE = GWT.create(MyConstants.class);

	String exportLbl();

	String examineeLbl();

	String enterExamineeNumber();

	String selectTask();

	String timerLbl();

	String previousResultLbl();

	String currentResultLbl();

	String pointsAddedLbl();

	String pointsReducedLbl();

	String timeUpTitle();

	String timeUpDescription();

	String points();

	String approveBtn();

	String closeBtn();

	String testCompleteMessage();

	String previewComplete();

	String examExplanation();

	String cardsExamExplanation();

	String cardsPressureExamExplanation();

	String mathPreviewExamExplanation();

	String mathPressureExamExplanation();

	String mathExamExplanation();

	String bioMessage();
	
	String bioMessagePreview();

	String correctAnswer();

	String incorrectAnswer();
	
	String pleaseWait();

	// File output
	String examineeNumberOutput();

	String isPresureOutput();

	String dateOutput();

	String cardClickedTimeOutput();

	String mathQuestionAnsweredTimeOutput();

	String questionTimeOutput();

	String examineeAnswerOutput();

	String isCorrectAnswerOutput();

	String numberOfCorrectAnswersOutput();

	String totalTestTimeOutput();

	String testStartTimeOutput();

	String testCompleteTimeOutput();

	String selectedDeckOutput();

	String winAmountOutput();

	String loseAmountOutput();

	String turnTotalAmountOutput();

	String totalWinAmountOutput();

	String totalLoseAmountOutput();

	String totalAmountOutput();

	String numberOfStepsOutput();
}