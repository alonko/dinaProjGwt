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
		String button();

		String controlPanel();

		String mainContainer();
		
		//Cards
		String cardsMainPanel();
		
		String cardsResultPanel();
		
		String card();
		
		//instructions
		String instructionsPanel();
	}
}