package com.agile.spirit.presentation.components.user.search;


import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.agile.spirit.WicketPanel;
import com.agile.spirit.application.UserSearchCriteria;
import com.agile.spirit.domain.Country;
import com.agile.spirit.domain.Gender;
import com.agile.spirit.domain.Nationality;
import com.agile.spirit.domain.User;

public class UserSearchPanel extends WicketPanel {

  private static final long serialVersionUID = 4907116541209734919L;

  /* Components */
  Form<User> form;
  TextField<String> nameInput;
  DropDownChoice<Gender> genderInput;
  DropDownChoice<Nationality> nationalityInput;
  DropDownChoice<Country> countryInput;

  /* Model data*/
  IModel<UserSearchCriteria> model;

  public UserSearchPanel(String id) {
    super(id);
    buildForm();
  }

  @Override
  public void onConfigure() {
    super.onConfigure();
    this.model = new Model<UserSearchCriteria>(getWicketSession().getUserSearchCriteria());
    this.setDefaultModel(new CompoundPropertyModel<UserSearchCriteria>(model));
  }
  
  private void buildForm() {
    form = new Form<User>("form");
    form.setOutputMarkupId(true);

    buildNameInput();
    buildGenderInput();
    buildNationalityInput();
    buildCountryInput();
    buildButtonBar();

    add(form);
  }

  private void buildNameInput() {
    nameInput = new TextField<String>("name");
    form.add(nameInput);
  }

  private void buildGenderInput() {
    genderInput = new DropDownChoice<Gender>("gender", Arrays.asList(Gender.values()),
        new EnumChoiceRenderer<Gender>(this));
    genderInput.setNullValid(true);
    genderInput.setOutputMarkupId(true);
    form.add(genderInput);
  }

  private void buildNationalityInput() {
    nationalityInput = new DropDownChoice<Nationality>("nationality", Arrays.asList(Nationality.values()),
        new EnumChoiceRenderer<Nationality>(this));
    nationalityInput.setNullValid(true);
    nationalityInput.setOutputMarkupId(true);
    form.add(nationalityInput);
  }

  private void buildCountryInput() {
    countryInput = new DropDownChoice<Country>("country", Arrays.asList(Country.values()),
        new EnumChoiceRenderer<Country>(this));
    countryInput.setNullValid(true);
    countryInput.setOutputMarkupId(true);
    form.add(countryInput);
  }

  private void buildButtonBar() {
    buildResetButton();
    buildSearchButton();
  }

  private void buildResetButton() {
    Button resetButton = new AjaxButton("resetButton") {
      private static final long serialVersionUID = -6212470738648231613L;

      @Override
      protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
        getWicketSession().setUserSearchCriteria(new UserSearchCriteria());
        setResponsePage(getPage());
      }

      @Override protected void onError(AjaxRequestTarget target, Form<?> form) { }

    }.setDefaultFormProcessing(false);
    form.add(resetButton);
  }

  private void buildSearchButton() {
    Button searchButton = new Button("searchButton") {
      private static final long serialVersionUID = 342974993599665914L;

      @Override
      public void onSubmit() {
        setResponsePage(getPage());
      }
    };
    form.add(searchButton);
  }


}
