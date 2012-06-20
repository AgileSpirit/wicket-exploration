package com.agile.spirit;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.agile.spirit.application.UserSearchCriteria;
import com.agile.spirit.domain.User;

public class WicketSession extends WebSession {

  private static final long serialVersionUID = 1L;

  private User user = null;
  private UserSearchCriteria userSearchCriteria = null;

  public WicketSession(Request request) {
    super(request);
    userSearchCriteria = new UserSearchCriteria();
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public UserSearchCriteria getUserSearchCriteria() {
    return userSearchCriteria;
  }

  public void setUserSearchCriteria(UserSearchCriteria userSearchCriteria) {
    this.userSearchCriteria = userSearchCriteria;
  }

}
