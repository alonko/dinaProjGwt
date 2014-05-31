package com.dev.dina.proj.client.main;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public abstract class AbstractTestPresenter {
	protected int totalPoints;
	protected int step;
	protected int timeLeft;
	protected Boolean isPresure;
	protected Timer timer;
	protected ExportToExcelWidget exportWidget;

	public AbstractTestPresenter(Boolean isPresure) {
		this.isPresure = isPresure;
	}

	protected void addExportWidget() {
		exportWidget = new ExportToExcelWidget();
		List<String> sentences = Arrays.asList(
				"This table was built with UIBinder",
				"It uses a SimplePanel to place the export widget",
				"You can also provide it");
		fillaTableWithSentences(exportWidget.getExportFlexTable(), sentences);
		exportWidget.setVisible(false);

		getMmainContainer().add(exportWidget);
	}

	protected abstract FlowPanel getMmainContainer();

	protected void beginTest() {
		totalPoints = 0;
		step = 0;
		updatePoints(0, 0);
	}

	private void fillaTableWithSentences(FlexTable flexTable,
			List<String> sentences) {
		for (int i = 0; i < sentences.size(); i++) {
			String sentence = sentences.get(i);
			String[] words = sentence.split(" ");
			for (int j = 0; j < words.length; j++) {
				String word = words[j];
				flexTable.setWidget(i, j, new Label(word));
			}
		}
	}

	protected abstract void finishTest();

	protected abstract void updateTimer();

	protected abstract void updatePoints(int addedPoints, int reducedPoints);
}
