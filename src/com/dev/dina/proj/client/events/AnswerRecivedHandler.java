package com.dev.dina.proj.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface AnswerRecivedHandler extends EventHandler {
	void onAnswerRecived(AnswerRecivedEvent authenticationEvent);
}