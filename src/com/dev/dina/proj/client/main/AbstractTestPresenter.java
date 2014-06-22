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

	protected abstract FlowPanel getMmainContainer();

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
		final MessageBox messageBox = new MessageBox();
		messageBox.setDescriptionText(constants.testCompleteTitle());
		messageBox.setTitle(constants.testCompleteTitle());
		messageBox.show();
		messageBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageBox.hide();
				addColumnToTable(constants.testCompleteTimeOutput(),
						new Date().toString());
				AppUtils.EVENT_BUS.fireEvent(new TestCompleteEvent());
				exportWidget.performExport();
			}
		});
	}

	protected void showExplanationScreen(String title, String message) {
		final MessageBox messageBox = new MessageBox();
		messageBox.setDescriptionText(message);
		messageBox.setTitle(title);
		messageBox.asWidget().setSize("700px", "400px");
		messageBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageBox.hide();
				beginTest();
			}
		});

		// countDown = 15;
		// final MessageBox bioCounterMessage = new MessageBox();
		// bioCounterMessage.asWidget().setSize("80px", "80px");
		// bioCounterMessage.setCloseButtonVsisble(false);
		//
		// countDownTimer = new Timer() {
		// @Override
		// public void run() {
		// if (countDown > 0) {
		// bioCounterMessage.setDescriptionText(String
		// .valueOf(countDown));
		// countDown--;
		// } else {
		// bioCounterMessage.hide();
		// messageBox.show();
		// addColumnToTable(constants.testStartTimeOutput(),
		// new Date().toString());
		// countDownTimer.cancel();
		// }
		// }
		// };
		// countDownTimer.scheduleRepeating(1000);

		final MessageBox bioMessage = new MessageBox();
		bioMessage.setDescriptionText(constants.bioMessage());
		bioMessage.asWidget().setSize("700px", "400px");
		bioMessage.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				countDown = 15;
				bioMessage.hide();
				final PopupPanel counterPopup = new PopupPanel();
				counterPopup.setAutoHideEnabled(false);
				counterPopup.setAnimationEnabled(true);
				counterPopup.setGlassEnabled(true);
				counterPopup.setModal(true);
				counterPopup.setSize("250px", "60px");
				final Label countDownLabel = new Label();
				countDownLabel.setStyleName(ProjectResources.INSTANCE.css().countDownLabel());
				counterPopup.setWidget(countDownLabel);
				counterPopup.center();
				// bioMessage.setCloseButtonEnabled(false);

				countDownTimer = new Timer() {
					@Override
					public void run() {
						if (countDown > 0) {
							// bioMessage.setCloseButtonText(String
							// .valueOf(countDown));
							// counterPopup.setTitle();
							countDownLabel.setText(MyConstants.INSTANCE.pleaseWait() + String.valueOf(countDown));
							countDown--;
						} else {
							// bioMessage.setCloseButtonText(String
							// .valueOf(countDown));
							counterPopup.hide();
							countDownTimer.cancel();
							// bioMessage.setCloseButtonEnabled(true);
							// bioMessage
							// .setCloseButtonHandler(new ClickHandler() {
							// @Override
							// public void onClick(ClickEvent event) {
							// bioMessage.hide();
							// bioCounterMessage.hide();
							messageBox.show();
							addColumnToTable(constants.testStartTimeOutput(),
									new Date().toString());
						}
						// });

					}
					// }
				};
				countDownTimer.scheduleRepeating(1000);
				// bioMessage.hide();
				// bioCounterMessage.show();

			}
		});
		bioMessage.show();
	}

	protected abstract void updateTimer();
}
