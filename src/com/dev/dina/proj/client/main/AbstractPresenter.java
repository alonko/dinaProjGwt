package com.dev.dina.proj.client.main;

import com.google.gwt.user.client.Timer;

public abstract class AbstractPresenter {
	protected int totalPoints;
	protected int step;
	protected int timeLeft;
	protected Boolean isPresure;
	protected Timer timer;

	protected abstract void beginTest();

	protected abstract void finishTest();

	protected abstract void updateTimer();
}
