package com.dev.dina.proj.client.math.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MathWidget extends Composite implements HasValue<String>,
		IsEditor<TakesValueEditor<String>> {
	private final static int CURSOR_LEFT = 37;
	private final static int CURSOR_RIGHT = 39;
	private final static int TAB = KeyCodes.KEY_TAB;

	private static MathWidgetUiBinder uiBinder = GWT
			.create(MathWidgetUiBinder.class);

	interface MathWidgetUiBinder extends UiBinder<Widget, MathWidget> {
	}

	@UiField
	TextBox firstValue, secondValue, thirdValue, forthValue;

	private TakesValueEditor<String> editor;
	private boolean valueChangeHandlerInitialized;

	public MathWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		firstValue.setMaxLength(1);
		secondValue.setMaxLength(1);
		thirdValue.setMaxLength(1);
		forthValue.setMaxLength(1);

		addHandlers();
	}

	private void addHandlers() {
		firstValue.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				int keyPressed = event.getNativeKeyCode();
				if (keyPressed != CURSOR_LEFT && keyPressed != CURSOR_RIGHT) {
					firstValue.setValue("");
				} else if (keyPressed == CURSOR_LEFT) {
					secondValue.setFocus(true);
				} else {
					forthValue.setFocus(true);
				}
			}
		});

		secondValue.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				int keyPressed = event.getNativeKeyCode();
				if (keyPressed != CURSOR_LEFT && keyPressed != CURSOR_RIGHT) {
					secondValue.setValue("");
				} else if (keyPressed == CURSOR_LEFT) {
					thirdValue.setFocus(true);
				} else {
					firstValue.setFocus(true);
				}
			}
		});

		thirdValue.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				int keyPressed = event.getNativeKeyCode();
				if (keyPressed != CURSOR_LEFT && keyPressed != CURSOR_RIGHT) {
					thirdValue.setValue("");
				} else if (keyPressed == CURSOR_LEFT) {
					forthValue.setFocus(true);
				} else {
					secondValue.setFocus(true);
				}
			}
		});

		forthValue.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				int keyPressed = event.getNativeKeyCode();
				if (keyPressed != CURSOR_LEFT && keyPressed != CURSOR_RIGHT) {
					forthValue.setValue("");
				} else if (keyPressed == CURSOR_LEFT) {
					firstValue.setFocus(true);
				} else {
					thirdValue.setFocus(true);
				}
			}
		});
	}

	@UiHandler("firstValue")
	void onKeyUp(KeyUpEvent event) {
		// if 2 chars were entered focus the next box
		int keyPressed = event.getNativeKeyCode();
		if (keyPressed != CURSOR_LEFT && keyPressed != CURSOR_RIGHT) {
			if (firstValue.getText().length() == firstValue.getMaxLength())
				secondValue.setFocus(true);
		}
	}

	@UiHandler("secondValue")
	void onKeyUp2(KeyUpEvent event) {
		// if 2 chars were entered focus the next box
		int keyPressed = event.getNativeKeyCode();
		if (keyPressed != CURSOR_LEFT && keyPressed != CURSOR_RIGHT) {
			if (secondValue.getText().length() == secondValue.getMaxLength())
				thirdValue.setFocus(true);
		}
	}

	@UiHandler("thirdValue")
	void onKeyUp3(KeyUpEvent event) {
		// if 2 chars were entered focus the next box
		int keyPressed = event.getNativeKeyCode();
		if (keyPressed != CURSOR_LEFT && keyPressed != CURSOR_RIGHT) {
			if (thirdValue.getText().length() == thirdValue.getMaxLength())
				forthValue.setFocus(true);
		}
	}

	@UiHandler("forthValue")
	void onKeyUp4(KeyUpEvent event) {
		// if 2 chars were entered focus the next box
		int keyPressed = event.getNativeKeyCode();
		if (keyPressed != CURSOR_LEFT && keyPressed != CURSOR_RIGHT) {
			if (forthValue.getText().length() == thirdValue.getMaxLength())
				firstValue.setFocus(true);
		}
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		// Initialization code
		if (!valueChangeHandlerInitialized) {
			valueChangeHandlerInitialized = true;
			addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					ValueChangeEvent.fire(MathWidget.this, getValue());
				}
			});
		}
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public String getValue() {
		String value = "";
		value += this.firstValue.getValue() + "/" + this.secondValue.getValue()
				+ "/" + this.thirdValue.getValue() + "/"
				+ this.forthValue.getValue();
		return value;
	}

	@Override
	public void setValue(String value) {
		String[] values = value.split("/");
		if (values.length == 4) {
			this.firstValue.setValue(values[0]);
			this.secondValue.setValue(values[1]);
			this.thirdValue.setValue(values[2]);
			this.forthValue.setValue(values[3]);
		} else {
			this.firstValue.setValue("");
			this.secondValue.setValue("");
			this.thirdValue.setValue("");
			this.forthValue.setValue("");
		}
		System.out.println("Set Value:" + value);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		setValue(value);
		if (fireEvents)
			ValueChangeEvent.fire(this, value);
	}

	@Override
	public TakesValueEditor<String> asEditor() {
		if (editor == null) {
			editor = ValueBoxEditor.of(this);
		}
		return editor;
	}
}