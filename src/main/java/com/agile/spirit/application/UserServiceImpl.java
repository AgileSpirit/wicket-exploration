package com.agile.spirit.application;

import java.util.ArrayList;
import java.util.List;

import com.agile.spirit.domain.Country;
import com.agile.spirit.domain.Gender;
import com.agile.spirit.domain.Nationality;
import com.agile.spirit.domain.User;

public class UserServiceImpl implements UserService {

  private static UserService instance;
  private final List<User> list = new ArrayList<User>();
  private Integer index = 1;

  private UserServiceImpl() {
  }

  public static UserService getInstance() {
    if (instance == null) {
      instance = new UserServiceImpl();
    }
    return instance;
  }

  @Override
  public List<User> find(UserSearchCriteria criteria) {
    if (criteria == null || criteria.isEmpty()) {
      return new ArrayList<User>(list);
    }
    List<User> result = new ArrayList<User>();
    for (User user : list) {
      boolean isUserMatching = matchName(criteria.getName(), user) && matchGender(criteria.getGender(), user)
          && matchNationality(criteria.getNationality(), user) && matchCountry(criteria.getCountry(), user);
      if (isUserMatching) {
        result.add(user);
      }
    }
    return result;
  }

  private boolean matchName(String name, User user) {
    if (name != null) {
      if (user.getEmail().toUpperCase().contains(name.toUpperCase()) || user.getFirstName().toUpperCase().contains(name.toUpperCase())
          || user.getLastName().toUpperCase().contains(name.toUpperCase())) {
        return true;
      }
      return false;
    }
    return true;
  }

  private boolean matchGender(Gender gender, User user) {
    if (gender != null) {
      if (gender == user.getGender()) {
        return true;
      }
      return false;
    }
    return true;
  }

  private boolean matchNationality(Nationality nationality, User user) {
    if (nationality != null) {
      if (nationality == user.getNationality()) {
        return true;
      }
      return false;
    }
    return true;
  }

  private boolean matchCountry(Country country, User user) {
    if (country != null) {
      if (country == user.getCountry()) {
        return true;
      }
      return false;
    }
    return true;
  }

  @Override
  public User getById(Integer id) {
    if (id != null) {
      for (User user : list) {
        if (user.getId().equals(id)) {
          return user;
        }
      }
    }
    return null;
  }

  @Override
  public User getByEmail(String email) {
    if (email != null) {
      for (User user : list) {
        if (user.getEmail().equals(email)) {
          return user;
        }
      }
    }
    return null;
  }

  @Override
  public User saveOrUpdate(User user) {
    if (user != null) {
      User persisted = getById(user.getId());
      // Save
      if (persisted == null) {
        user.setId(index);
        list.add(user);
        index++;
      } else { // Update
        persisted.setFirstName(user.getFirstName());
        persisted.setLastName(user.getLastName());
        persisted.setGender(user.getGender());
        persisted.setNationality(user.getNationality());
        persisted.setCountry(user.getCountry());
        persisted.setArea(user.getArea());
        persisted.setPassport(user.getPassport());
        persisted.setEmail(user.getEmail());
        persisted.setPassword(user.getPassword());
      }
      return persisted;
    }
    return null;
  }

  @Override
  public boolean delete(Integer id) {
    if (id != null) {
      for (User user : list) {
        if (user.getId().equals(id)) {
          list.remove(user);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public User authenticate(String email, String password) {
    User user = UserServiceImpl.getInstance().getByEmail(email);
    if (user != null) {
      if (password.equals(user.getPassword())) {
        return user;
      }
    }
    return null;
  }

}
