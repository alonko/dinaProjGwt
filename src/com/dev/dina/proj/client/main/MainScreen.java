package com.dev.dina.proj.client.main;

import com.dev.dina.proj.client.cards.CardsScreenPresenter;
import com.dev.dina.proj.client.resources.ProjectResources;
import com.dev.dina.proj.client.resources.ProjectResources.ProjectCssResources;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainScreen implements EntryPoint {
	private RootPanel mainContainer, controlPanel;
	
//	/**
//	 * The message displayed to the user when the server cannot be reached or
//	 * returns an error.
//	 */
//	private static final String SERVER_ERROR = "An error occurred while "
//			+ "attempting to contact the server. Please check your network "
//			+ "connection and try again.";
//
//	/**
//	 * Create a remote service proxy to talk to the server-side Greeting
//	 * service.
//	 */
//	private final GreetingServiceAsync greetingService = GWT
//			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		ProjectCssResources style = ProjectResources.INSTANCE.css();
		style.ensureInjected(); 

		final TextBox nameField = new TextBox();
		nameField.setText("Select task");

		final Button mathExamButton = new Button("Math");
//		mathExamButton.setStyleName(style.button());
		mathExamButton.setStyleName("btn");
		mathExamButton.addStyleName("btn-large");
		mathExamButton.addStyleName("btn-success");
		

		final Button mathPresureExamButton = new Button("MathP");
		mathPresureExamButton.setStyleName("btn");
		mathPresureExamButton.addStyleName("btn-large");
		mathPresureExamButton.addStyleName("btn-success");

		final Button cardsExaButton = new Button("Cards");
		cardsExaButton.setStyleName("btn");
		cardsExaButton.addStyleName("btn-large");
		cardsExaButton.addStyleName("btn-success");

		final Button cardsPresureExaButton = new Button("CardsP");
		cardsPresureExaButton.setStyleName("btn");
		cardsPresureExaButton.addStyleName("btn-large");
		cardsPresureExaButton.addStyleName("btn-success");

		controlPanel = RootPanel.get("controlPanelContainer");
		controlPanel.setStyleName("btn-group");
		controlPanel.addStyleName("btn-group-justified");
		controlPanel.add(mathExamButton);
		controlPanel.add(mathPresureExamButton);
		controlPanel.add(cardsExaButton);
		controlPanel.add(cardsPresureExaButton);
		
		mainContainer = RootPanel.get("mainScreenContainer");
		mainContainer.add(nameField);
		
		cardsExaButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CardsScreenPresenter cardsScreenPresenter = new CardsScreenPresenter();
				mainContainer.add(cardsScreenPresenter.gwtWidget());
			}
		});

		// // Focus the cursor on the name field when the app loads
		// nameField.setFocus(true);
		// nameField.selectAll();
		//
		// // Create the popup dialog box
		// final DialogBox dialogBox = new DialogBox();
		// dialogBox.setText("Remote Procedure Call");
		// dialogBox.setAnimationEnabled(true);
		// final Button closeButton = new Button("Close");
		// // We can set the id of a widget by accessing its Element
		// closeButton.getElement().setId("closeButton");
		// final Label textToServerLabel = new Label();
		// final HTML serverResponseLabel = new HTML();
		// VerticalPanel dialogVPanel = new VerticalPanel();
		// dialogVPanel.addStyleName("dialogVPanel");
		// dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		// dialogVPanel.add(textToServerLabel);
		// dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		// dialogVPanel.add(serverResponseLabel);
		// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		// dialogVPanel.add(closeButton);
		// dialogBox.setWidget(dialogVPanel);
		//
		// mathExamButton.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// }
		// });

		//
		// // Create a handler for the sendButton and nameField
		// class MyHandler implements ClickHandler, KeyUpHandler {
		// /**
		// * Fired when the user clicks on the sendButton.
		// */
		// public void onClick(ClickEvent event) {
		// sendNameToServer();
		// }
		//
		// /**
		// * Fired when the user types in the nameField.
		// */
		// public void onKeyUp(KeyUpEvent event) {
		// if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		// sendNameToServer();
		// }
		// }
		//
		// /**
		// * Send the name from the nameField to the server and wait for a
		// * response.
		// */
		// private void sendNameToServer() {
		// // First, we validate the input.
		// // errorLabel.setText("");
		// String textToServer = nameField.getText();
		// if (!FieldVerifier.isValidName(textToServer)) {
		// // errorLabel.setText("Please enter at least four characters");
		// return;
		// }
		//
		// // Then, we send the input to the server.
		// textToServerLabel.setText(textToServer);
		// serverResponseLabel.setText("");
		// greetingService.greetServer(textToServer,
		// new AsyncCallback<String>() {
		// public void onFailure(Throwable caught) {
		// // Show the RPC error message to the user
		// dialogBox
		// .setText("Remote Procedure Call - Failure");
		// serverResponseLabel
		// .addStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(SERVER_ERROR);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		//
		// public void onSuccess(String result) {
		// dialogBox.setText("Remote Procedure Call");
		// serverResponseLabel
		// .removeStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(result);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		// });
		// }
		// }
		// // Add a handler to send the name to the server
		// MyHandler handler = new MyHandler();
		// nameField.addKeyUpHandler(handler);
		// }
	}
}