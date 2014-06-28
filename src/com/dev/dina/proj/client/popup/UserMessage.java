package com.dev.dina.proj.client.popup;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserMessage extends PopupPanel{
	public UserMessage(Widget widget){
		setAutoHideEnabled(false);
		setAnimationEnabled(true);
		setGlassEnabled(true);
		setModal(true);
		setSize("250px", "60px");
		setWidget(widget);
	}
}
