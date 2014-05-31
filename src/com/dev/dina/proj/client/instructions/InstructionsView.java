package com.dev.dina.proj.client.instructions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class InstructionsView {
	private Widget root;
	private static InstructionsUiBinder uiBinder = GWT
			.create(InstructionsUiBinder.class);

	interface InstructionsUiBinder extends UiBinder<Widget, InstructionsView> {
	}

	@UiField
	Button approveBtn;

	public InstructionsView() {
		root = uiBinder.createAndBindUi(this);
	}

	public Widget asWidget() {
		return root;
	}
}