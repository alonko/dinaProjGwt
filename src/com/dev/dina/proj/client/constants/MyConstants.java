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

	String resultLbl();
	
	String pointsAddedLbl();

	String pointsReducedLbl();

	String approveBtn();

	String closeBtn();

	String testComplete();

	String previewComplete();

	String examExplanation();

	String cardsExamExplanation();
	
	String mathExamExplanation();
	
	String correctAnswer();
	
	String incorrectAnswer();
	
//	File output
	String examineeNumberOutput();

	String isPresureOutput();

	String dateOutput();

	String questionTimeOutput();

	String examineeAnswerOutput();

	String isCorrectAnswerOutput();

	String numberOfCorrectAnswersOutput();

	String totalTestTimeOutput();

	String selectedDeckOutput();

	String winAmountOutput();

	String loseAmountOutput();
	
	String turnTotalAmountOutput();

	String totalWinAmountOutput();

	String totalLoseAmountOutput();

	String totalAmountOutput();

	String numberOfStepsOutput();	
}