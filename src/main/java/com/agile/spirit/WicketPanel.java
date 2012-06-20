package com.agile.spirit;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Panel;

public class WicketPanel extends Panel {

  private static final long serialVersionUID = -4081768740427027177L;

  public WicketPanel(String id) {
    super(id);
  }
  
  public WicketSession getWicketSession() {
    if (getPage() instanceof WicketPage) {
      return ((WicketPage) getPage()).getWicketSession();
    }
    return null;
  }

  public ModalWindow getModal() {
    if (getPage() instanceof WicketPage) {
      return ((WicketPage) getPage()).getModal();
    }
    return null;
  }
  
  public void configureModal(Panel content, int width) {
    ModalWindow modal = getModal();
    modal.setInitialWidth(width);
    modal.setContent(content);
  }
  
  public void showModal(AjaxRequestTarget target) {
    ModalWindow modal = getModal();
    target.appendJavaScript( "Wicket.Window.unloadConfirmation = false;" ); 
    modal.show(target);
  }
}
