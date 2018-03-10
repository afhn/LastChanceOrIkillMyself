package org.erratica.app.utils;

import java.beans.PropertyEditorSupport;

public class MayusculaEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		}
		else {
			String value = text.toUpperCase();
			setValue(value);
		}
	}
}
