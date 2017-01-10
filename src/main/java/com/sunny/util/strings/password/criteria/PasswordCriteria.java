package com.sunny.util.strings.password.criteria;

import com.sunny.util.strings.password.PasswordStrength;

/**
 * Created by sundas on 5/28/2015.
 */
public interface PasswordCriteria {

  /**
   *
   * @param targetPassword
   * @return
   */
  public PasswordStrength validate(char[] targetPassword);

}
