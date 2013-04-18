package de.wombatsoftware.TweetCamp.validation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
public class UserValidator {
    public void validateUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	String username = (String) value;
	int size = username.length();

	if (size < 5 || size > 10) {
//	    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username too short or too long", null));
	}
    }

    public void validatePassword(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	String password = (String) value;
	int size = password.length();

	if (size < 5 || size > 10) {
//	    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password too short or too long", null));
	}
    }
}