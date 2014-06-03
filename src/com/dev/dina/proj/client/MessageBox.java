package com.dev.dina.proj.client;

import com.dev.dina.proj.client.constants.MyConstants;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MessageBox extends DialogBox {
	private Button closeButton;
	private TextArea descriptionText;

	// private HTML result;

	public MessageBox(String text) {
		setText(text);
		setAnimationEnabled(true);
		closeButton = new Button(MyConstants.INSTANCE.closeBtn());
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				descriptionText.setVisible(false);
			}
		});
		// final Label textToServerLabel = new Label();
		// final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		FlowPanel descriptionPanel = new FlowPanel();
		descriptionText = new TextArea();
		descriptionPanel.add(descriptionText);
		descriptionPanel.setVisible(false);
		dialogVPanel.add(descriptionPanel);
		// dialogVPanel.add(textToServerLabel);
		// result = new HTML();
		// dialogVPanel.add(result);
		// dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		setWidget(dialogVPanel);
	}

	public void setCloseButtonHandler(ClickHandler clickHandler) {
		closeButton.addClickHandler(clickHandler);
	}

	/*
	 * public void setResult(int resultValue){
	 * result.setText(String.valueOf(resultValue)); }
	 */

	public void setDescriptionText(String text) {
		descriptionText.setVisible(true);
		descriptionText.setText(text);
	}
}