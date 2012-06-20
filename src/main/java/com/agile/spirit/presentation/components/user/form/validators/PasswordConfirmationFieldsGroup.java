package com.agile.spirit.presentation.components.user.form.validators;

import org.apache.wicket.markup.html.form.TextField;


public interface PasswordConfirmationFieldsGroup {

  TextField<String> getPasswordInput();

  TextField<String> getConfirmationInput();

}
