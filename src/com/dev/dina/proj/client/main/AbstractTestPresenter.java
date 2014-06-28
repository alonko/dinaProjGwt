package com.dev.dina.proj.client.main;

import java.util.Date;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.dev.dina.proj.client.popup.MessageBox;
import com.dev.dina.proj.client.popup.StressMeterMessageBox;
import com.dev.dina.proj.client.popup.UserMessage;
import com.dev.dina.proj.client.resources.ProjectResources;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

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
		setVisibleView(true);
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

		final StressMeterMessageBox stressLevelMessage = new StressMeterMessageBox();
		stressLevelMessage.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int selectedStressValue = stressLevelMessage
						.getSelectedStressValue();
				addColumnToTable(constants.selectedStressLevelOutput(),
						String.valueOf(selectedStressValue));
				stressLevelMessage.hide();
				testCompleteMessage.show();
			}
		});
		setVisibleView(false);
		stressLevelMessage.show();
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
				final PopupPanel counterPopup = new UserMessage(countDownLabel);
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

	protected abstract void updateTimer();

	protected abstract FlowPanel getMmainContainer();
	
	protected abstract void setVisibleView(boolean visible);
}