package com.sunny.util.strings.password.criteria.builder.factory;

import com.sunny.util.strings.password.criteria.builder.CriteriaBuilder;
import com.sunny.util.strings.password.criteria.builder.impl.DefaultCriteriaBuilder;
import com.sunny.util.strings.password.criteria.policy.PasswordStrengthPolicy;

/**
 * Created by sundas on 5/28/2015.
 */
public class CriteriaBuilderFactory {


  public static CriteriaBuilder getCriteriaBuilder(PasswordStrengthPolicy passwordStrengthPolicy){
    CriteriaBuilder criteriaBuilder = null;
    switch(passwordStrengthPolicy){
      case DEFAULT:
        criteriaBuilder = new DefaultCriteriaBuilder();
      default:
        break;
    }
    return criteriaBuilder;
  }


}
