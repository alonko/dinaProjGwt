package com.dev.dina.proj.client.main;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.events.TestCompleteEvent;
import com.dev.dina.proj.client.popup.MessageBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

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

	public AbstractTestPresenter(Boolean isPresure, String examineeNumber) {
		this.isPresure = isPresure;
		this.examineeNumber = examineeNumber;
		gridColumnNumber = 0;
		turnTime = 0;
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
		addColumnToTable(MyConstants.INSTANCE.totalTestTimeOutput(),
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
		final MessageBox messageBox = new MessageBox(
				MyConstants.INSTANCE.testComplete());
		messageBox.setTitle(MyConstants.INSTANCE.testComplete());
		messageBox.show();
		messageBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageBox.hide();
				AppUtils.EVENT_BUS.fireEvent(new TestCompleteEvent());
				exportWidget.performExport();
			}
		});
	}

	protected void showExplanationScreen(String title, String message) {
		final MessageBox msgBox = new MessageBox(title, message);
		msgBox.asWidget().setSize("700px", "400px");
		msgBox.setCloseButtonHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				msgBox.hide();
				beginTest();
			}
		});
		msgBox.show();
	}

	protected abstract void updateTimer();
}
