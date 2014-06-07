package com.dev.dina.proj.client.popup;

import com.dev.dina.proj.client.constants.MyConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;

public class MessageBox {
	private PopupPanel root;
	@UiField
	TextArea messageContent;
	@UiField
	Button closeButton;
	@UiField
	Label title;

	private static MessageBoxUiBinder uiBinder = GWT
			.create(MessageBoxUiBinder.class);

	interface MessageBoxUiBinder extends UiBinder<PopupPanel, MessageBox> {
	}

	public MessageBox(String title, String content) {
		this(content);
		setTitle(title);
	}

	public MessageBox(String text) {
		root = uiBinder.createAndBindUi(this);
		setDescriptionText(text);
		root.setAnimationEnabled(true);
		root.setModal(true);
		messageContent.setEnabled(false);
		closeButton.setText(MyConstants.INSTANCE.closeBtn());
		setHandlers();
	}

	private void setHandlers() {
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageContent.setVisible(false);
			}
		});
	}

	public void show() {
		root.center();
//		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
//			@Override
//			public void execute() {
//				closeButton.setFocus(true);
//			}
//		});
	}

	public void hide() {
		root.hide();
	}

	public void setCloseButtonHandler(ClickHandler clickHandler) {
		closeButton.addClickHandler(clickHandler);
	}

	public void setDescriptionText(String text) {
		messageContent.setVisible(true);
		messageContent.setText(text);
	}

	public PopupPanel asWidget() {
		return root;
	}

	public void setTitle(String titleText) {
		title.setText(titleText, Direction.RTL);
	}
}