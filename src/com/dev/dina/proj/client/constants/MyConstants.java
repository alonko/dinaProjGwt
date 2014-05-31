package com.dev.dina.proj.client.constants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface MyConstants extends Constants {
	static final MyConstants INSTANCE = GWT.create(MyConstants.class);

	String exportLbl();

	String examineeLbl();

	String selectTask();

	String timerLbl();

	String resultLbl();
	
	String pointsAddedLbl();

	String pointsReducedLbl();

	String approveBtn();
}