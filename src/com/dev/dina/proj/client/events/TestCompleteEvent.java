package com.dev.dina.proj.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Alon Kodner
 */

public class TestCompleteEvent extends GwtEvent<TestCompleteEventHandler> {

	public static Type<TestCompleteEventHandler> TYPE = new Type<TestCompleteEventHandler>();

	@Override
	public Type<TestCompleteEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TestCompleteEventHandler handler) {
		handler.onTestComplete(this);
	}
}
