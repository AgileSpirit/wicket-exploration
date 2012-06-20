package com.agile.spirit.presentation.components.user.form.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import com.agile.spirit.domain.Country;
import com.agile.spirit.domain.Gender;
import com.agile.spirit.domain.Nationality;
import com.agile.spirit.domain.User;

public class PersonalDataSection extends UserFormSection {

  private static final long serialVersionUID = 1L;

  /* Components */
  RequiredTextField<String> firstNameInput;
  RequiredTextField<String> lastNameInput;
  RadioChoice<Gender> genderInput;
  DropDownChoice<Nationality> nationalityInput;
  WebMarkupContainer passportGroup;
  DropDownChoice<Country> countryInput;
  TextField<String> areaInput;
  RequiredTextField<String> passportInput;

  public PersonalDataSection(String id, IModel<User> user) {
    super(id, "personalDataFeedback", user);

    buildFirstNameInput();
    buildLastNameInput();
    buildGenderInput();
    buildNationalityInput();
    buildPassportGroup();
    buildCountryInput();
    buildAreaInput();
  }

  private void buildFirstNameInput() {
    firstNameInput = new RequiredTextField<String>("firstName");
    add(firstNameInput);
  }

  private void buildLastNameInput() {
    lastNameInput = new RequiredTextField<String>("lastName");
    add(lastNameInput);
  }

  private void buildGenderInput() {
    genderInput = new RadioChoice<Gender>("gender", Arrays.asList(Gender.values()),
        new EnumChoiceRenderer<Gender>(this));
    genderInput.setSuffix("&nbsp;");
    add(genderInput);
  }

  private void buildNationalityInput() {
    nationalityInput = new DropDownChoice<Nationality>("nationality", Arrays.asList(Nationality.values()),
        new EnumChoiceRenderer<Nationality>(this));
    nationalityInput.add(new AjaxFormComponentUpdatingBehavior("onchange") {
      private static final long serialVersionUID = 1L;

      @Override
      protected void onUpdate(AjaxRequestTarget target) {
        target.add(passportGroup);
      }
    });
    add(nationalityInput);
  }

  private void buildPassportGroup() {
    passportGroup = new WebMarkupContainer("passportGroup") {
      private static final long serialVersionUID = 1L;

      @Override
      protected void onInitialize() {
        super.onInitialize();
        buildPassportInput();
      }

      @Override
      protected void onConfigure() {
        User model = (User) PersonalDataSection.this.getDefaultModelObject();
        setVisibilityAllowed(model.getNationality() == Nationality.SPANISH);
      }

      private void buildPassportInput() {
        passportInput = new RequiredTextField<String>("passport");
        add(passportInput);
      }
    };
    passportGroup.setOutputMarkupId(true);
    passportGroup.setOutputMarkupPlaceholderTag(true);
    add(passportGroup);
  }

  private void buildCountryInput() {
    countryInput = new DropDownChoice<Country>("country", Arrays.asList(Country.values()),
        new EnumChoiceRenderer<Country>(this));
    countryInput.add(new AjaxFormComponentUpdatingBehavior("onchange") {
      private static final long serialVersionUID = 1L;

      @Override
      protected void onUpdate(AjaxRequestTarget target) {
        target.add(areaInput);
      }
    });
    add(countryInput);
  }

  private void buildAreaInput() {
    areaInput = new AutoCompleteTextField<String>("area", new Model<String>("")) {

      private static final long serialVersionUID = 3562095090905092931L;

      @Override
      protected Iterator<String> getChoices(String input) {
        Country country = countryInput.getModelObject();
        if (country == null || Strings.isEmpty(input)) {
          List<String> emptyList = Collections.emptyList();
          return emptyList.iterator();
        }

        List<String> choices = new ArrayList<String>();

        String[] areas = country.getAreas();

        for (final String area : areas) {
          if (area.toUpperCase().startsWith(input.toUpperCase())) {
            choices.add(area);
          }
        }

        return choices.iterator();
      }
    };
    areaInput.setRequired(true);
    add(areaInput);
  }

  @Override
  public void resetFields() {
    firstNameInput.clearInput();
    lastNameInput.clearInput();
    genderInput.clearInput();
    nationalityInput.clearInput();
    passportInput.clearInput();
    countryInput.clearInput();
    areaInput.clearInput();
  }

}
