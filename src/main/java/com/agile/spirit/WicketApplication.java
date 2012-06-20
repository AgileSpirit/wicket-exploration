package com.agile.spirit;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.agile.spirit.application.UserServiceImpl;
import com.agile.spirit.presentation.pages.user.form.UserFormPage;
import com.agile.spirit.presentation.pages.user.list.UserListPage;
import com.agile.spirit.presentation.pages.user.login.LoginPage;
import com.agile.spirit.util.DataGenerator;

public class WicketApplication extends WebApplication {

  public WicketApplication() {
    super();
    if (UserServiceImpl.getInstance().find(null).size() == 0) {
      DataGenerator.generateData(10);
    }
  }
  
  @Override
  public void init() {
    super.init();

    removeThreadMonitoringFromResourceWatcherForGaeSupport();
    
    mountPage("/users/list", UserListPage.class);
    mountPage("/users/form", UserFormPage.class);
  }

  private void removeThreadMonitoringFromResourceWatcherForGaeSupport() {
    this.getResourceSettings().setResourcePollFrequency(null);
  }
  
  @Override
  public Class<? extends Page> getHomePage() {
    return LoginPage.class;
  }

  @Override
  public Session newSession(Request request, Response response) {
    return new WicketSession(request);
  }

}
