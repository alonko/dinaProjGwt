package com.dev.dina.proj.client.main;

import com.dev.dina.proj.client.constants.MyConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtTableToExcel.client.TableToExcelClientBuilder;

public class ExportToExcelWidget extends Composite {
	private Button exportWidget;
	@UiField(provided = true)
	FlexTable exportFlexTable;
	@UiField
	SimplePanel exportPanel;
	@UiField(provided = true)
	FormPanel exportForm;

	private static exportToExcelUiBinder uiBinder = GWT
			.create(exportToExcelUiBinder.class);

	interface exportToExcelUiBinder extends
			UiBinder<Widget, ExportToExcelWidget> {
	}

	public ExportToExcelWidget(String examineeNumber, String testType,
			Boolean isPresure) {
		exportFlexTable = new FlexTable();
		TableToExcelClientBuilder fromTable = TableToExcelClientBuilder
				.fromTable(exportFlexTable);
		exportWidget = new Button(MyConstants.INSTANCE.exportLbl());
		fromTable.withClickable(exportWidget);
		fromTable.toFileName(examineeNumber + "_" + testType + "_" + isPresure);

		exportForm = fromTable.buildExportFormWidget();

		initWidget(uiBinder.createAndBindUi(this));
	}

	public void performExport() {
		exportWidget.fireEvent(new ButtonClickEvent());
	}

	private class ButtonClickEvent extends ClickEvent {
		/*
		 * To call click() function for Programmatic equivalent of the user
		 * clicking the button.
		 */
	}

	public FlexTable getExportFlexTable() {
		return exportFlexTable;
	}
}