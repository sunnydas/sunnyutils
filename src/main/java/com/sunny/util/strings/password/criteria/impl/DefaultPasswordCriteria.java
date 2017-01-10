package com.sunny.util.strings.password.criteria.impl;

import com.sunny.util.strings.password.PasswordStrength;
import com.sunny.util.strings.password.criteria.AbstractPasswordCriteria;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by sundas on 5/28/2015.
 */
public class DefaultPasswordCriteria extends AbstractPasswordCriteria {

  public DefaultPasswordCriteria(int minimumLength, int maximumLength, Set<Character> mandatoryCharacters, Set<Character> excludedCharacters, Set<String> dictionary) {
    super(minimumLength, maximumLength, mandatoryCharacters, excludedCharacters, dictionary);
  }

  public DefaultPasswordCriteria() {
    super();
  }

  @Override
  public PasswordStrength validate(char[] targetPassword) {
    return super.findPasswordStrength(targetPassword);
  }


  public static void main(String[] args) {
    Object o = new ArrayList<>();
    String[] strings = new String[]{"dladl;k"};
    System.out.println((strings instanceof String[]));
    System.out.println((o instanceof String[]));
  }
}
