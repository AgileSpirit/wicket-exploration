package com.agile.spirit.presentation.pages.user.login;

import com.agile.spirit.WicketPage;
import com.agile.spirit.presentation.components.user.login.LoginForm;

public class LoginPage extends WicketPage {

  
  public LoginPage() {
    super();
    buildLoginForm();
  }

  private void buildLoginForm() {
    add(new LoginForm("loginForm"));
  }

}
