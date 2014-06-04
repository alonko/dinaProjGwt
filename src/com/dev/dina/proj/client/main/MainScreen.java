package com.dev.dina.proj.client.main;

import com.dev.dina.proj.client.cards.CardsScreenPresenter;
import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.events.AppUtils;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.dev.dina.proj.client.events.TestCompleteEventHandler;
import com.dev.dina.proj.client.math.MathScreenPresenter;
import com.dev.dina.proj.client.popup.MessageBox;
import com.dev.dina.proj.client.resources.ProjectResources;
import com.dev.dina.proj.client.resources.ProjectResources.ProjectCssResources;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainScreen implements EntryPoint {
	private RootPanel mainContainer, controlPanel;
	private ProjectCssResources style;
	private HandlerRegistration approveHandlerRegistration;
	private FlowPanel examineeContainer;
	private Button approveButton;
	private TextBox examineeNumber;

	private final static int ENTER = KeyCodes.KEY_ENTER;

	// /**
	// * The message displayed to the user when the server cannot be reached or
	// * returns an error.
	// */
	// private static final String SERVER_ERROR = "An error occurred while "
	// + "attempting to contact the server. Please check your network "
	// + "connection and try again.";
	//
	// /**
	// * Create a remote service proxy to talk to the server-side Greeting
	// * service.
	// */
	// private final GreetingServiceAsync greetingService = GWT
	// .create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		style = ProjectResources.INSTANCE.css();
		style.ensureInjected();

		Label buttonsHeader = new Label();
		buttonsHeader.setStyleName(style.buttonsHeader());
		buttonsHeader.setText(MyConstants.INSTANCE.selectTask());

		final Button mathExamButton = createButton("Math");
		final Button mathPresureExamButton = createButton("MathP");
		final Button cardsExaButton = createButton("Cards");
		final Button cardsPresureExaButton = createButton("CardsP");

		Label examineeLbl = new Label(MyConstants.INSTANCE.examineeLbl());
		examineeLbl.setStyleName(style.examineeLbl());
		examineeNumber = new TextBox();
		examineeNumber.setStyleName(style.examineeText());
		examineeNumber.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == ENTER) {
					approveButton.click();
				} else if (!Character.isDigit(event.getCharCode())) {
					examineeNumber.cancelKey();
				}
			}
		});
		approveButton = new Button(MyConstants.INSTANCE.approveBtn());// createButton(MyConstants.INSTANCE.approveBtn());

		approveButton.addStyleName(style.okButton());

		controlPanel = RootPanel.get("controlPanelContainer");
		controlPanel.setStyleName(style.controlPanel());
		FlowPanel buttonContainer = new FlowPanel();
		buttonContainer.setStyleName(style.mainButtonContainer());
		buttonContainer.add(mathExamButton);
		controlPanel.add(buttonContainer);

		buttonContainer = new FlowPanel();
		buttonContainer.setStyleName(style.mainButtonContainer());
		buttonContainer.add(mathPresureExamButton);
		controlPanel.add(buttonContainer);

		buttonContainer = new FlowPanel();
		buttonContainer.setStyleName(style.mainButtonContainer());
		buttonContainer.add(cardsExaButton);
		controlPanel.add(buttonContainer);

		buttonContainer = new FlowPanel();
		buttonContainer.setStyleName(style.mainButtonContainer());
		buttonContainer.add(cardsPresureExaButton);
		controlPanel.add(buttonContainer);

		mainContainer = RootPanel.get("mainScreenContainer");
		mainContainer.setStyleName(style.mainContainer());

		examineeContainer = new FlowPanel();
		FlowPanel container = new FlowPanel();
		container.add(examineeLbl);
		container.setStyleName(style.examineeInnerContainer());
		examineeContainer.add(container);

		container = new FlowPanel();
		container.add(examineeNumber);
		container.setStyleName(style.examineeInnerContainer());
		examineeContainer.add(container);

		container = new FlowPanel();
		container.add(approveButton);
		container.setStyleName(style.examineeInnerContainer());
		examineeContainer.add(container);

		examineeContainer.setStyleName(style.examineeContainer());
		examineeContainer.setVisible(false);
		controlPanel.add(examineeContainer);

		cardsExaButton.addClickHandler(getMainButtonClickHandler(1));

		cardsPresureExaButton.addClickHandler(getMainButtonClickHandler(2));

		mathExamButton.addClickHandler(getMainButtonClickHandler(3));

		mathPresureExamButton.addClickHandler(getMainButtonClickHandler(4));

		setHandlers();

		// Grid grid = new Grid(4, 4);
		// TableElement table = new TableElement();
		// CellTable<String> table = new CellTable<String>();
		// TableToExcelClientBuilder tableToExcelClient =
		// TableToExcelClientBuilder.fromTable(table);
		// TableToExcelClient tableToExcelClient = new
		// TableToExcelClient(table);
		// FlowPanel myPanel = new FlowPanel();
		// myPanel.add(tableToExcelClient.getExportWidget());

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

	private ClickHandler getMainButtonClickHandler(final int testNumber) {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (approveHandlerRegistration != null) {
					approveHandlerRegistration.removeHandler();
				}
				examineeContainer.setVisible(true);
				examineeNumber.setFocus(true);
				approveHandlerRegistration = approveButton
						.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								String examinee = examineeNumber.getText();
								if (!examinee.isEmpty()) {
									startExam(testNumber, examinee);
									examineeNumber.setText("");
									examineeContainer.setVisible(false);
								} else {
									final MessageBox messageBox = new MessageBox(
											"enter examinee number");
									messageBox.show();
									messageBox
											.setCloseButtonHandler(new ClickHandler() {
												@Override
												public void onClick(
														ClickEvent event) {
													messageBox.hide();
												}
											});
								}
							}
						});
			}
		};
	}

	private void startExam(int testNumber, String examineeNumber) {
		if (testNumber == 1) {
			cardsExam(false, examineeNumber);
		} else if (testNumber == 2) {
			cardsExam(true, examineeNumber);
		} else if (testNumber == 3) {
			mathExam(false, examineeNumber);
		} else { // (testNumber == 4)
			mathExam(true, examineeNumber);
		}
	}

	private void cardsExam(Boolean isPresure, String examineeNumber) {
		CardsScreenPresenter cardsScreenPresenter = new CardsScreenPresenter(
				isPresure);
		mainContainer.add(cardsScreenPresenter.getWidget());
		controlPanel.setVisible(false);
	}

	private void mathExam(Boolean isPresure, String examineeNumber) {
		MathScreenPresenter mathScreenPresenter = new MathScreenPresenter(
				isPresure);
		mainContainer.add(mathScreenPresenter.getWidget());
		controlPanel.setVisible(false);
	}

	private void setHandlers() {
		EventBus eventBus = AppUtils.EVENT_BUS;
		eventBus.addHandler(TestCompleteEvent.TYPE,
				new TestCompleteEventHandler() {
					@Override
					public void onTestComplete(
							TestCompleteEvent authenticationEvent) {
						mainContainer.clear();
						controlPanel.setVisible(true);

					}
				});
	}

	private Button createButton(String text) {
		final Button mathPresureExamButton = new Button(text);
		mathPresureExamButton.setStyleName(style.mainButton());
		mathPresureExamButton.addStyleName("btn");
		mathPresureExamButton.addStyleName("btn-large");
		mathPresureExamButton.addStyleName("btn-success");
		mathPresureExamButton.addStyleName("placeholder");
		return mathPresureExamButton;
	}
}