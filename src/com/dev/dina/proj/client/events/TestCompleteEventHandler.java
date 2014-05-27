package com.dev.dina.proj.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface TestCompleteEventHandler extends EventHandler {
	void onTestComplete(TestCompleteEvent authenticationEvent);
}