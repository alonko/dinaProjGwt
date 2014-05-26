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

	@Source("com/dev/dina/proj/client/resources/css/projectStyle.css")
	public ProjectCssResources css();

	public interface ProjectCssResources extends CssResource {
		//Main
		String mainButton();

		String controlPanel();

		String mainContainer();
		
		String buttonsHeader();
		
		//Cards
		String cardsMainPanel();
		
		String cardsResultPanel();
		
		String cardContainer();
		
		String card();
		
		//instructions
		String instructionsPanel();
	}
}