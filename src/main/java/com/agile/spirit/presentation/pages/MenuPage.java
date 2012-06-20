package com.agile.spirit.presentation.pages;


import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import com.agile.spirit.WicketPage;
import com.agile.spirit.presentation.pages.user.form.UserFormPage;
import com.agile.spirit.presentation.pages.user.list.UserListPage;

public class MenuPage extends WicketPage {

  private static final long serialVersionUID = -3532854894763177524L;

  public MenuPage() {
    super();
    add(new BookmarkablePageLink("userListPage", UserListPage.class));
    add(new BookmarkablePageLink("userFormPage", UserFormPage.class));
  }

}
