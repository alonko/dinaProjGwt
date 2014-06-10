package com.dev.dina.proj.client.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

/**
 * @author Alon Kodner
 */

public class AppUtils {
	public static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);
}
