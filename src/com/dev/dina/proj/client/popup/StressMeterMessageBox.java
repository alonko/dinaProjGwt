package com.dev.dina.proj.client.popup;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.resources.ProjectResources;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;

public class StressMeterMessageBox extends MessageBox {
	private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;
	private MyConstants constants = MyConstants.INSTANCE;

	public StressMeterMessageBox() {
		super("", true);
		setDescriptionText(constants.selectStressLevelExplanation());
		createStressPanel();
		closeButton.setEnabled(false);
	}

	private void createStressPanel() {
		Label lowestRankLbl = new Label(constants.lowestRank());
		lowestRankLbl.setStyleName(ProjectResources.INSTANCE.css()
				.stressLabel());

		rb1 = createRadioButton("radioGroup", "1");
		rb2 = createRadioButton("radioGroup", "2");
		rb3 = createRadioButton("radioGroup", "3");
		rb4 = createRadioButton("radioGroup", "4");
		rb5 = createRadioButton("radioGroup", "5");
		rb6 = createRadioButton("radioGroup", "6");
		rb7 = createRadioButton("radioGroup", "7");
		rb8 = createRadioButton("radioGroup", "8");
		rb9 = createRadioButton("radioGroup", "9");
		rb10 = createRadioButton("radioGroup", "10");

		Label highestRankLbl = new Label(constants.highestRank());
		highestRankLbl.setStyleName(ProjectResources.INSTANCE.css()
				.stressLabel());

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName(ProjectResources.INSTANCE.css().stressPanel());

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

		setWidgetToPopup(panel);
	}

	private RadioButton createRadioButton(String name, String label) {
		RadioButton rb = new RadioButton(name, label);
		rb.setStyleName(ProjectResources.INSTANCE.css().stressRadio());
		rb.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				closeButton.setEnabled(true);
			}
		});
		return rb;
	}

	public int getSelectedStressValue() {
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
}
