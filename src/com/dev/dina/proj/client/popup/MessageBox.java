package com.dev.dina.proj.client.popup;

import com.dev.dina.proj.client.constants.MyConstants;
import com.dev.dina.proj.client.resources.ProjectResources;
import com.dev.dina.proj.client.resources.ProjectResources.ProjectCssResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alon Kodner
 */

public class MessageBox {
	private PopupPanel root;
	@UiField
	TextArea messageContent;
	@UiField
	Button closeButton;
	@UiField
	Label title;
	@UiField
	FlowPanel widgetPanel;

	private ProjectCssResources style;

	private static MessageBoxUiBinder uiBinder = GWT
			.create(MessageBoxUiBinder.class);

	interface MessageBoxUiBinder extends UiBinder<PopupPanel, MessageBox> {
	}

	public MessageBox(String title, String content) {
		this(content);
		setTitle(title);
	}

	public MessageBox() {
		this("");
	}

	
	public MessageBox(String text) {
		this("", false);
	}
	
	public MessageBox(String text, Boolean preventDefaultCloseButton) {
		
		root = uiBinder.createAndBindUi(this);
		style = ProjectResources.INSTANCE.css();

		setDescriptionText(text);
		root.setAnimationEnabled(true);
		root.setModal(true);
		root.setSize("600px", "500px");
		messageContent.setEnabled(false);
		setCloseButtonText(MyConstants.INSTANCE.closeBtn());
		setCloseButtonVsisble(true);
		if(!preventDefaultCloseButton){
			setHandlers();
		}
		
		closeButton.addStyleName("btn");
		closeButton.addStyleName("btn-large");
		closeButton.addStyleName("btn-success");
		closeButton.addStyleName("placeholder");
		closeButton.addStyleName(style.okButton());
	}

	private void setHandlers() {
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}

	public void show() {
		root.center();
	}

	public void hide() {
		root.hide();
	}

	public void setCloseButtonHandler(ClickHandler clickHandler) {
		closeButton.addClickHandler(clickHandler);
	}

	public void setCloseButtonVsisble(Boolean visible) {
		closeButton.setVisible(visible);
	}

	public void setCloseButtonText(String text) {
		closeButton.setText(text);
	}

	public void setCloseButtonEnabled(Boolean isEnabled) {
		closeButton.setEnabled(isEnabled);
	}

	public void setDescriptionText(String text, Boolean isPositive) {
		setDescriptionText(text);
		if (isPositive) {
			messageContent.addStyleName(style.posetiveMessage());
			messageContent.removeStyleName(style.negativeMessage());
		} else {
			messageContent.addStyleName(style.negativeMessage());
			messageContent.removeStyleName(style.posetiveMessage());
		}
	}

	public void setDescriptionText(String text) {
		messageContent.setVisible(true);
		messageContent.setText(text);
		messageContent.removeStyleName(style.posetiveMessage());
		messageContent.removeStyleName(style.negativeMessage());
	}

	public PopupPanel asWidget() {
		return root;
	}

	public void setTitle(String titleText) {
		title.setText(titleText, Direction.RTL);
	}
	
	public void setWidgetToPopup(Widget widget){
		widgetPanel.setVisible(true);
		widgetPanel.add(widget);
	}
}