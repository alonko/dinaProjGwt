package com.dev.dina.proj.client.main;

import com.dev.dina.proj.client.cards.CardsScreenPresenter;
import com.dev.dina.proj.client.constants.MyConstants;
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
 * @author Alon Kodner
 */

public class MainScreen implements EntryPoint {
	private RootPanel mainContainer, controlPanel;
	private ProjectCssResources style;
	private HandlerRegistration approveHandlerRegistration;
	private FlowPanel examineeContainer;
	private Button approveButton;
	private TextBox examineeNumber;

	private final static int ENTER = KeyCodes.KEY_ENTER;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		style = ProjectResources.INSTANCE.css();
		style.ensureInjected();

		Label buttonsHeader = new Label();
		buttonsHeader.setStyleName(style.buttonsHeader());
		buttonsHeader.setText(MyConstants.INSTANCE.selectTask());

		final Button mathPreviewExaButton = createButton("MathPre");
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
		buttonContainer.add(mathPreviewExaButton);
		controlPanel.add(buttonContainer);
		
		buttonContainer = new FlowPanel();
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

		mathPreviewExaButton.addClickHandler(getMainButtonClickHandler(5));

		setHandlers();
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
											MyConstants.INSTANCE
													.enterExamineeNumber());
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
			mathExam(false, examineeNumber, false);
		} else if (testNumber == 4) {
			mathExam(true, examineeNumber, false);
		} else { // math preview
			mathExam(false, examineeNumber, true);
		}
	}

	private void cardsExam(Boolean isPresure, String examineeNumber) {
		CardsScreenPresenter cardsScreenPresenter = new CardsScreenPresenter(
				isPresure, examineeNumber);
		mainContainer.add(cardsScreenPresenter.getWidget());
		controlPanel.setVisible(false);
	}

	private void mathExam(Boolean isPresure, String examineeNumber, Boolean isPreview) {
		MathScreenPresenter mathScreenPresenter = new MathScreenPresenter(
				isPresure, examineeNumber, isPreview);
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