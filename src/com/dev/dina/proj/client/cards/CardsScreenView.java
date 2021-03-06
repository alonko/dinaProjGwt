package com.dev.dina.proj.client.cards;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.resources.ProjectResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alon Kodner
 */

public class CardsScreenView {
	private Widget root;
	@UiField
	FlowPanel mainContainer;

	@UiField(provided = true)
	Image card1, card2, card3, card4;

	@UiField
	Label currentResultValue, pointsAddedValue, pointsReducedValue, timerValue,
			timerLbl;
	@UiField
	Button approveButton;

	ProjectResources resources = ProjectResources.INSTANCE;

	private static CardsScreenUiBinder uiBinder = GWT
			.create(CardsScreenUiBinder.class);

	interface CardsScreenUiBinder extends UiBinder<Widget, CardsScreenView> {
	}

	public CardsScreenView() {
		card1 = new Image(resources.cardImage());
		card2 = new Image(resources.cardImage());
		card3 = new Image(resources.cardImage());
		card4 = new Image(resources.cardImage());
		root = uiBinder.createAndBindUi(this);
		setViewVisible(false);
		
		approveButton.addStyleName("btn");
		approveButton.addStyleName("btn-large");
		approveButton.addStyleName("btn-success");
		approveButton.addStyleName("placeholder");
		approveButton.addStyleName(resources.css().okButton());
		setApproveButtonEnabled(false);

		currentResultValue.addStyleName(resources.css().posetiveMessage());
		pointsAddedValue.addStyleName(resources.css().posetiveMessage());
		pointsReducedValue.addStyleName(resources.css().negativeMessage());

		timerLbl.addStyleName(resources.css().negativeMessage());
		timerValue.addStyleName(resources.css().negativeMessage());
	}

	public Widget asWidget() {
		return root;
	}

	public Image getCard1() {
		return card1;
	}

	public Image getCard2() {
		return card2;
	}

	public Image getCard3() {
		return card3;
	}

	public Image getCard4() {
		return card4;
	}
	
	public void setApproveClickHandler(ClickHandler handler) {
		approveButton.addClickHandler(handler);
	}
	
	public void setApproveButtonEnabled(Boolean enabled) {
		approveButton.setEnabled(enabled);
	}

	public void setValueToCurrentResult(int result) {
		currentResultValue.setText(String.valueOf(result));
		if (result > 0) {
			currentResultValue.addStyleName(resources.css().posetiveMessage());
			currentResultValue.removeStyleName(resources.css()
					.negativeMessage());
		} else {
			currentResultValue.addStyleName(resources.css().negativeMessage());
			currentResultValue.removeStyleName(resources.css()
					.posetiveMessage());
		}
	}

	public void setValueToAddedPoints(int points) {
		pointsAddedValue.setText(String.valueOf(points));
	}

	public void setValueToReducedPoints(int points) {
		pointsReducedValue.setText(String.valueOf(points));
	}

	public void setTimer(int timeLeft) {
		timerValue.setText(String.valueOf(timeLeft));
	}

	public void setTimerVisible(Boolean isVisible) {
		timerValue.getParent().setVisible(isVisible);
		timerValue.setVisible(isVisible);
		timerLbl.setVisible(isVisible);
	}

	public FlowPanel getMainContainer() {
		return mainContainer;
	}

	public void setViewVisible(boolean isVisible) {
		root.setVisible(isVisible);
	}

	public void markDeckSelected(int selectedDeckNumber) {
		unselectedAllDecks();
		Image selectedDeck = card1;
		if (selectedDeckNumber == 1) {
			selectedDeck = card1;
		} else if (selectedDeckNumber == 2) {
			selectedDeck = card2;
		} else if (selectedDeckNumber == 3) {
			selectedDeck = card3;
		} else {
			selectedDeck = card4;
		}
		selectedDeck.addStyleName(resources.css().selectedDeck());
	}

	public void unselectedAllDecks() {
		card1.removeStyleName(resources.css().selectedDeck());
		card2.removeStyleName(resources.css().selectedDeck());
		card3.removeStyleName(resources.css().selectedDeck());
		card4.removeStyleName(resources.css().selectedDeck());
	}
	
	public void setVisibleView(boolean visible){
		asWidget().setVisible(visible);
	}
}