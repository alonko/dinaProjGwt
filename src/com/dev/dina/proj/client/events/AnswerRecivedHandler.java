package com.dev.dina.proj.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Alon Kodner
 */

public interface AnswerRecivedHandler extends EventHandler {
	void onAnswerRecived(AnswerRecivedEvent authenticationEvent);
}