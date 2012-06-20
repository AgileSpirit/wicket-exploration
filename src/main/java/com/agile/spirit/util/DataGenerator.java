package com.agile.spirit.util;

import java.util.Arrays;
import java.util.Random;

import com.agile.spirit.application.UserServiceImpl;
import com.agile.spirit.domain.Country;
import com.agile.spirit.domain.Gender;
import com.agile.spirit.domain.Nationality;
import com.agile.spirit.domain.User;

public class DataGenerator {

  private final static Random random = new Random();

  public static void generateData(int rowsNumber) {
    // Generate Admin user
    User admin = new User();
    admin.setFirstName("Chuck");
    admin.setLastName("Noris");
    admin.setGender(Gender.MALE);
    admin.setNationality(Nationality.OTHER);
    admin.setCountry(Country.OTHER);
    admin.setArea("Texas");
    admin.setPassport("N/A");
    admin.setEmail("admin@agile-spirit.fr");
    admin.setPassword("admin");
    UserServiceImpl.getInstance().saveOrUpdate(admin);

    for (int i = 0 ; i < rowsNumber - 1 ; i++) {
      User user = new User();
      user.setFirstName(generateRandomString());
      user.setLastName(generateRandomString());
      user.setGender(generateRandomGender());
      user.setNationality(generateRandomNationality());
      user.setCountry(generateRandomCountry());
      user.setArea(generateRandomString());
      user.setPassport(generateRandomString());
      user.setEmail(generateRandomEmail());
      user.setPassword(generateRandomString());
      UserServiceImpl.getInstance().saveOrUpdate(user);
    }
  }

  /* Set of characters that is valid. Must be printable, memorable,
   * and "won't break HTML" (i.e., not '<', '>', '&', '=', ...).
   * or break shell commands (i.e., not '<', '>', '$', '!', ...).
   * I, L and O are good to leave out, as are numeric zero and one.
   */
  protected static char[] goodChar = {
    // Comment out next two lines to make upper-case-only, then
    // use String toUpper() on the user's input before validating.
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
    'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N',
    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    //    '2', '3', '4', '5', '6', '7', '8', '9',
    //      '+', '-', '@',
  };

  public static String generateRandomString() {
    int length = random.nextInt(12);
    if (length == 0) {
      length = 8;
    }
    return generateRandomString(length);
  }
  public static String generateRandomString(int length) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0 ; i < length ; i++) {
      sb.append(goodChar[random.nextInt(goodChar.length)]);
    }
    return sb.toString();
  }

  public static Gender generateRandomGender() {
    return Arrays.asList(Gender.values()).get(random.nextInt(Gender.values().length));
  }

  public static Nationality generateRandomNationality() {
    return Arrays.asList(Nationality.values()).get(random.nextInt(Nationality.values().length));
  }

  public static Country generateRandomCountry() {
    return Arrays.asList(Country.values()).get(random.nextInt(Country.values().length));
  }

  public static String generateRandomEmail() {
    return generateRandomString() + "@agile-spirit.fr";
  }

}
