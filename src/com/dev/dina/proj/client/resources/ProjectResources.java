package com.dev.dina.proj.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface ProjectResources extends ClientBundle {
	public static final ProjectResources INSTANCE = GWT
			.create(ProjectResources.class);

	@Source("com/dev/dina/proj/client/resources/images/card.jpg")
	ImageResource cardImage();

	@Source("com/dev/dina/proj/client/resources/images/logo_excel.gif")
	ImageResource excelImage();

	@Source("com/dev/dina/proj/client/resources/css/projectStyle.css")
	public ProjectCssResources css();

	public interface ProjectCssResources extends CssResource {
		// Main
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
		
		// Cards
		String cardsMainPanel();

		String resultPanel();
		
		String resultInnerPanel(); 
		
		String resultLbl();
		
		String resultValue();

		String cardContainer();

		String card();

		// instructions
		String instructionsPanel();

		// Math
		String mathContainer();

		String mathValue();

		String addLbl();

		String equals();
		
		String questionPanel();

		String answerPanel();

		String answer();
	}
}