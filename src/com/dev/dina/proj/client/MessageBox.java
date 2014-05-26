package com.dev.dina.proj.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MessageBox extends DialogBox {
	private Button closeButton;
	private HTML result;

	public MessageBox(String text) {
		setText(text);
		setAnimationEnabled(true);
		closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Test  complete, your result:</b>"));
		dialogVPanel.add(textToServerLabel);
		result = new HTML();
		dialogVPanel.add(result);
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		setWidget(dialogVPanel);
	}

	public void setCloseButtonHandler(ClickHandler clickHandler) {
		closeButton.addClickHandler(clickHandler);
	}
	
	public void setResult(int resultValue){
		result.setText(String.valueOf(resultValue));
	}
}