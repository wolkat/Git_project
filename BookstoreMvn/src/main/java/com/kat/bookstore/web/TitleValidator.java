package com.kat.bookstore.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("titleValidator")
public class TitleValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String title = (String) value;

		if (title.length() < 2) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Tytuł musi mieć > 2 znaki");
			message.setSummary("Tytuł musi mieć > 2 znaki");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
