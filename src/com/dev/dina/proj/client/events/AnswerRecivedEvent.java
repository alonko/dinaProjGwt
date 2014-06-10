package com.dev.dina.proj.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Alon Kodner
 */

public class AnswerRecivedEvent extends GwtEvent<AnswerRecivedHandler> {

	public static Type<AnswerRecivedHandler> TYPE = new Type<AnswerRecivedHandler>();

	@Override
	public Type<AnswerRecivedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AnswerRecivedHandler handler) {
		handler.onAnswerRecived(this);
	}
}
