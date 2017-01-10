package com.sunny.util.strings.password;

import com.sunny.util.strings.password.criteria.PasswordCriteria;

/**
 * This class tries to provide utility methods around password strings.
 *
 * Created by sundas on 5/20/2015.
 */
public class PasswordUtils {

  /**
   * This method tries to measure a given passwword's strenght based on the criteria.
   *
   *
   * @param password
   * @param passwordCriteria
   * @return
   */
  public static PasswordStrength findPasswordStrength(char[] password,PasswordCriteria passwordCriteria){
    return passwordCriteria.validate(password);
  }



}
