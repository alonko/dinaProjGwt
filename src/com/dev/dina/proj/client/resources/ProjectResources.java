package com.dev.dina.proj.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Alon Kodner
 */

public interface ProjectResources extends ClientBundle {
	public static final ProjectResources INSTANCE = GWT
			.create(ProjectResources.class);

	@Source("com/dev/dina/proj/client/resources/images/card.png")
	ImageResource cardImage();

	@Source("com/dev/dina/proj/client/resources/images/logo_excel.gif")
	ImageResource excelImage();

	@Source("com/dev/dina/proj/client/resources/css/projectStyle.css")
	public ProjectCssResources css();

	public interface ProjectCssResources extends CssResource {
		// Main
		String button();

		String mainButton();

		String okButton();

		String mainButtonContainer();

		String controlPanel();

		String mainContainer();

		String buttonsHeader();

		String timerContainer();

		String timer();

		String examineeContainer();

		String examineeInnerContainer();

		String examineeLbl();

		String examineeText();

		String popupPanel();

		String popupContainer();

		String popupTextArea();

		String popupCloseButton();

		String popupTitle();
		
		String countDownLabel(); 

		// Cards
		String cardsMainPanel();

		String resultPanel();

		String resultLeftPanel();
		
		String resultCenterPanel();

		String resultRightPanel();
		
		String cardsTimerContainer();

		String resultInnerPanel();

		String resultLbl();

		String resultValue();
		
		String timerValue();

		String cardContainer();

		String card();
		
		String cardLabel();

		// instructions
		String instructionsPanel();

		// Math
		String mathMainContainer();

		String mathContainer();

		String mathValue();

		String addLbl();

		String equals();

		String questionPanel();

		String answerPanel();
		
		String answersContainer();

		String answer();
		
		String answerContainer();

		String buttonPanel();

		String messagePanel();

		String posetiveMessage();

		String negativeMessage();

		String regularMessage();
	}
}