package com.dev.dina.proj.client.main;

import java.util.Date;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.dev.dina.proj.client.popup.MessageBox;
import com.dev.dina.proj.client.resources.ProjectResources;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alon Kodner
 */

public abstract class AbstractTestPresenter {
	protected int step;
	protected int turnTime;
	protected int totalTestTime;
	protected Boolean isPresure;
	protected Timer timer;
	protected ExportToExcelWidget exportWidget;
	protected int gridColumnNumber;
	protected String examineeNumber;
	protected MyConstants constants = MyConstants.INSTANCE;
	protected Boolean testComplete;
	private int countDown;
	private Timer countDownTimer;
	protected Boolean isPreview = false;
	RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;

	public AbstractTestPresenter(Boolean isPresure, String examineeNumber) {
		this.isPresure = isPresure;
		this.examineeNumber = examineeNumber;
		gridColumnNumber = 0;
		turnTime = 0;
		testComplete = false;
	}

	protected void addExportWidget(String testType) {
		exportWidget = new ExportToExcelWidget(examineeNumber, testType,
				isPresure);
		exportWidget.setVisible(false);

		getMmainContainer().add(exportWidget);
	}

	protected void beginTest() {
		step = 0;
		turnTime = 0;
		totalTestTime = 0;
	}

	protected void finishTest() {
		timer.cancel();
		addColumnToTable(constants.totalTestTimeOutput(),
				String.valueOf(totalTestTime));
		showTestCompleteMessage();
	}

	protected void addColumnToTable(String header, String value) {
		exportWidget.getExportFlexTable().setWidget(0, gridColumnNumber,
				new Label(header));
		exportWidget.getExportFlexTable().setWidget(1, gridColumnNumber,
				new Label(value));
		gridColumnNumber++;

	}

	protected void showTestCompleteMessage() {
		final MessageBox testCompleteMessage = new MessageBox();
		testCompleteMessage.setDescriptionText(constants.testCompleteMessage());
		testCompleteMessage.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addColumnToTable(constants.testCompleteTimeOutput(),
						new Date().toString());
				AppUtils.EVENT_BUS.fireEvent(new TestCompleteEvent());
				exportWidget.performExport();
			}
		});

		final MessageBox stressLevelMessage = new MessageBox("", true);
		stressLevelMessage.setDescriptionText(constants
				.selectStressLevelExplanation());
		HorizontalPanel radioPanel = createStressRadio();
		stressLevelMessage.setWidgetToPopup(radioPanel);
		stressLevelMessage.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int selectedStressValue = getSelectedStressValue();
				if (selectedStressValue != 0) {
					addColumnToTable(constants.selectedStressLevelOutput(),
							String.valueOf(selectedStressValue));
					stressLevelMessage.hide();
					testCompleteMessage.show();
				} else {
					final Label selectStressLabel = new Label();
					selectStressLabel.setStyleName(ProjectResources.INSTANCE
							.css().countDownLabel());
					selectStressLabel.setText(constants.selectStressLevel());
					final PopupPanel userMessage = getUserMessage(selectStressLabel);
					userMessage.center();

					Timer timer = new Timer() {
						@Override
						public void run() {
							userMessage.hide();
						}
					};
					timer.schedule(1500);
				}
			}
		});
		stressLevelMessage.show();
	}

	private HorizontalPanel createStressRadio() {
		Label lowestRankLbl = new Label(constants.lowestRank());
		lowestRankLbl.setStyleName(ProjectResources.INSTANCE.css()
				.stressLabel());

		rb1 = new RadioButton("radioGroup", "1");
		rb1.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb2 = new RadioButton("radioGroup", "2");
		rb2.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb3 = new RadioButton("radioGroup", "3");
		rb3.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb4 = new RadioButton("radioGroup", "4");
		rb4.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb5 = new RadioButton("radioGroup", "5");
		rb5.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb6 = new RadioButton("radioGroup", "6");
		rb6.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb7 = new RadioButton("radioGroup", "7");
		rb7.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb8 = new RadioButton("radioGroup", "8");
		rb8.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb9 = new RadioButton("radioGroup", "9");
		rb9.setStyleName(ProjectResources.INSTANCE.css().stressRadio());

		rb10 = new RadioButton("radioGroup", "10");
		rb10.setStyleName(ProjectResources.INSTANCE.css().stressRadio());
		Label highestRankLbl = new Label(constants.highestRank());
		highestRankLbl.setStyleName(ProjectResources.INSTANCE.css()
				.stressLabel());

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName(ProjectResources.INSTANCE.css().stressPanel());

		// panel.setSpacing(10);
		panel.add(lowestRankLbl);
		panel.add(rb1);
		panel.add(rb2);
		panel.add(rb3);
		panel.add(rb4);
		panel.add(rb5);
		panel.add(rb6);
		panel.add(rb7);
		panel.add(rb8);
		panel.add(rb9);
		panel.add(rb10);
		panel.add(highestRankLbl);
		return panel;
	}

	private int getSelectedStressValue() {
		int stress = 0;
		if (rb1.getValue() == true) {
			stress = 1;
		} else if (rb2.getValue() == true) {
			stress = 2;
		} else if (rb3.getValue() == true) {
			stress = 3;
		} else if (rb4.getValue() == true) {
			stress = 4;
		} else if (rb5.getValue() == true) {
			stress = 5;
		} else if (rb6.getValue() == true) {
			stress = 6;
		} else if (rb7.getValue() == true) {
			stress = 7;
		} else if (rb8.getValue() == true) {
			stress = 8;
		} else if (rb9.getValue() == true) {
			stress = 9;
		} else if (rb10.getValue() == true) {
			stress = 10;
		}
		return stress;
	}

	protected void showExplanationScreen(String title, String message) {
		final MessageBox messageBox = new MessageBox();
		messageBox.setDescriptionText(message);
		messageBox.setTitle(title);
		messageBox.asWidget().setSize("700px", "400px");
		messageBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				beginTest();
			}
		});

		String bioMessageToUser;
		if (!isPreview) {
			bioMessageToUser = constants.bioMessage();
		} else {
			bioMessageToUser = constants.bioMessagePreview();
		}
		final MessageBox bioMessage = new MessageBox();

		bioMessage.setDescriptionText(bioMessageToUser);
		bioMessage.asWidget().setSize("700px", "400px");
		bioMessage.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				countDown = 15;
				final Label countDownLabel = new Label();
				countDownLabel.setStyleName(ProjectResources.INSTANCE.css()
						.countDownLabel());
				final PopupPanel counterPopup = getUserMessage(countDownLabel);
				counterPopup.center();

				countDownTimer = new Timer() {
					@Override
					public void run() {
						if (countDown > 0) {
							countDownLabel.setText(MyConstants.INSTANCE
									.pleaseWait() + String.valueOf(countDown));
							countDown--;
						} else {
							counterPopup.hide();
							countDownTimer.cancel();

							messageBox.show();
							addColumnToTable(constants.testStartTimeOutput(),
									new Date().toString());
						}
					}
				};
				countDownTimer.scheduleRepeating(1000);
				countDownTimer.run();
			}
		});
		bioMessage.show();
	}

	protected PopupPanel getUserMessage(Widget widget) {
		final PopupPanel userMessage = new PopupPanel();
		userMessage.setAutoHideEnabled(false);
		userMessage.setAnimationEnabled(true);
		userMessage.setGlassEnabled(true);
		userMessage.setModal(true);
		userMessage.setSize("250px", "60px");
		userMessage.setWidget(widget);
		return userMessage;
	}

	protected abstract void updateTimer();

	protected abstract FlowPanel getMmainContainer();
}