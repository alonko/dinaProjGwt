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
	private MessageBox messageBox;

	private final static int ENTER = KeyCodes.KEY_ENTER;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		style = ProjectResources.INSTANCE.css();
		style.ensureInjected();
		messageBox = new MessageBox();

		Label buttonsHeader = new Label();
		buttonsHeader.setStyleName(style.buttonsHeader());
		buttonsHeader.setText(MyConstants.INSTANCE.selectTask());

		final Button mathPreviewExaButton = createButton("Preview");
		final Button mathExamButton = createButton("Math");
		final Button mathPresureExamButton = createButton("Math Time");
		final Button cardsExaButton = createButton("Cards");
		final Button cardsPresureExaButton = createButton("Cards Time");

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
		approveButton.addStyleName("btn");
		approveButton.addStyleName("btn-large");
		approveButton.addStyleName("btn-success");
		approveButton.addStyleName("placeholder");
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
		
//		
//		AbsolutePanel container1 = new AbsolutePanel();
////		SliderBarSimpleHorizontal slider = new SliderBarSimpleHorizontal(100, "300px",true);
////		slider.addStyleName("slid");
////		container.add(slider);
//		
//		KDEVerticalTopBW kDEVerticalTopBW = new KDEVerticalTopBW(500,"320px");
//		KDEVerticalBottomBW kDEVerticalBottomBW = new KDEVerticalBottomBW(10,"320px");
//		KDEHorizontalRightBW kDEHorizontalRightBW = new KDEHorizontalRightBW(20, "400px");
//		KDEHorizontalLeftBW kDEHorizontalLeftBW = new KDEHorizontalLeftBW(500, "400px");
////		SliderBarGMapDemo sliderBarGMapDemoSelected = new SliderBarGMapDemo(false, 4);
////		YBarDemo yBarDemo = new YBarDemo(false, 5);
//		IpSliderBar146 ipSliderBar146MF = new IpSliderBar146("MALE", "FEMALE");
//		IpSliderBar146 ipSliderBar146RS= new IpSliderBar146("Rain", "Snow");
//		IpSliderBar51 ipSliderBar51OnOff = new IpSliderBar51("Off","On");
//		IpSliderBar51 ipSliderBar51YesNo = new IpSliderBar51("No","Yes");
//		SpeedAjuster speedAjuster = new SpeedAjuster();
//		SizeAjuster sizeAjuster = new SizeAjuster();
//		AdvancedSliderBar advancedSliderBar = new AdvancedSliderBar();
//		
//		Label title = new Label("Various SliderBar samples");
//		title.addStyleName("demosettitle");
//
//		container1.addStyleName("demosetpanel");
//		container1.setSize("500px", "425px");
//		container1.add(title, 10,10);
//		container1.add(kDEVerticalTopBW,10, 60 );
//		kDEVerticalTopBW.setValue(100);
//		container1.add(kDEVerticalBottomBW,40, 60 );
//		kDEVerticalBottomBW.drawMarks("white", 8);
//		kDEVerticalBottomBW.setValue(7);
//		container1.add(kDEHorizontalRightBW, 70, 60);
//		kDEHorizontalRightBW.drawMarks("white", 8);
//		kDEHorizontalRightBW.setValue(13);
//		container1.add(kDEHorizontalLeftBW, 70, 90);
//		kDEHorizontalLeftBW.setValue(100);
//
////		container1.add(yBarDemo, 70, 120);
////		container1.add(sliderBarGMapDemoSelected,190, 120);
//		container1.add(ipSliderBar146MF, 323, 120);
//		ipSliderBar146MF.setNotSelectedInFocus();
//		container1.add(ipSliderBar146RS, 323, 172);
//		ipSliderBar146RS.setValue(1);
//		container1.add(ipSliderBar51OnOff, 323, 224);
//		container1.add(ipSliderBar51YesNo, 415, 224);
//		ipSliderBar51OnOff.setValue(1);
//		container1.add(speedAjuster, 323, 276);
//		speedAjuster.drawMarks("white", 2);
//		speedAjuster.setValue(1);
//		container1.add(sizeAjuster, 323, 323);
//		sizeAjuster.drawMarks("black", 2);
//		sizeAjuster.setValue(2);
//		container1.add(advancedSliderBar, 323, 360);
//		advancedSliderBar.drawMarks("black", 2);
//		advancedSliderBar.setValue(3);
//
//		examineeContainer.add(container1);

		
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
									messageBox
											.setDescriptionText(MyConstants.INSTANCE
													.enterExamineeNumber());
									messageBox.show();
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

	private void mathExam(Boolean isPresure, String examineeNumber,
			Boolean isPreview) {
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
		final Button button = new Button(text);
		button.setStyleName(style.mainButton());
		// mathPresureExamButton.setStyleName(style.button());
		button.addStyleName("btn");
		button.addStyleName("btn-large");
		button.addStyleName("btn-success");
		button.addStyleName("placeholder");
		return button;
	}
}