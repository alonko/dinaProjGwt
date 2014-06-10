package com.dev.dina.proj.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Alon Kodner
 */

public interface TestCompleteEventHandler extends EventHandler {
	void onTestComplete(TestCompleteEvent authenticationEvent);
}