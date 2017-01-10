package com.sunny.util.strings.password.criteria.builder;

import com.sunny.util.strings.password.criteria.PasswordCriteria;
import com.sunny.util.strings.password.criteria.policy.PasswordStrengthPolicy;

/**
 * Created by sundas on 5/28/2015.
 */
public interface CriteriaBuilder {


  /**
   *
   * @param
   * @return
   */
  public PasswordCriteria buildCriteria();

}
