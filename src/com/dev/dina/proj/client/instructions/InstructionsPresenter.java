package com.dev.dina.proj.client.instructions;

import com.google.gwt.user.client.ui.Widget;

public class InstructionsPresenter {
	private InstructionsView view;

	public InstructionsPresenter() {
		view = new InstructionsView();
	}

	public Widget gwtWidget() {
		return view.asWidget();
	}
}
