package com.dev.dina.proj.client.math;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.math.widget.MathWidget;
import com.dev.dina.proj.client.resources.ProjectResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alon Kodner
 */

public class MathScreenView {
	private Widget root;
	@UiField
	FlowPanel mainContainer;
	@UiField
	Label firstNumberLbl, secondNumberLbl, thirdNumberLbl;
	@UiField
	Label timerValue, timerLbl, messageLbl; 
	@UiField
	MathWidget answerWidget;
	@UiField
	Button approveBtn;

	private static CardsScreenUiBinder uiBinder = GWT
			.create(CardsScreenUiBinder.class);

	interface CardsScreenUiBinder extends UiBinder<Widget, MathScreenView> {
	}

	public MathScreenView() {
		root = uiBinder.createAndBindUi(this);
		approveBtn.setText(MyConstants.INSTANCE.approveBtn());
		messageLbl.setText("qwewqe");
	}

	public Widget asWidget() {
		return root;
	}

	public String getFirstValue() {
		return firstNumberLbl.getText();
	}

	public String getSecondValue() {
		return secondNumberLbl.getText();
	}

	public String getThirdValue() {
		return thirdNumberLbl.getText();
	}

	public void setFirstValue(int value) {
		firstNumberLbl.setText(String.valueOf(value));
	}

	public void setSecondValue(int value) {
		secondNumberLbl.setText(String.valueOf(value));
	}

	public void setThirdValue(int value) {
		thirdNumberLbl.setText(String.valueOf(value));
	}

	public void setTimer(int timeLeft) {
		timerValue.setText(String.valueOf(timeLeft));
	}

	public void setTimerVisible(Boolean isVisible) {
		timerValue.setVisible(isVisible);
		timerLbl.setVisible(isVisible);
	}

	public FlowPanel getMainContainer() {
		return mainContainer;
	}

	public Button getApproveBtn() {
		return approveBtn;
	}

	public void setFocusOnFirst() {
		answerWidget.setFocusOnFirst();
	}

	public String getAnswer() {
		return answerWidget.getValue();
	}

	public void clearValue() {
		answerWidget.clearValue();
	}
	
	public void setMessageToUser(String msg, Boolean isPositive){
		if(isPositive){
			messageLbl.setStyleName(ProjectResources.INSTANCE.css().posetiveMessage());
		}else{
			messageLbl.setStyleName(ProjectResources.INSTANCE.css().negativeMessage());
		}
		
		messageLbl.setText(msg);
		Timer timer = new Timer() {
			@Override
			public void run() {
//				messageLbl.setText("");
			}
		};
		timer.schedule(5000);
	}
}