package com.sunny.util.strings.password;

import com.sunny.util.strings.password.criteria.PasswordCriteria;
import com.sunny.util.strings.password.criteria.builder.factory.CriteriaBuilderFactory;
import com.sunny.util.strings.password.criteria.builder.impl.DefaultCriteriaBuilder;
import com.sunny.util.strings.password.criteria.impl.DefaultPasswordCriteria;
import com.sunny.util.strings.password.criteria.policy.PasswordStrengthPolicy;
import com.sunny.util.strings.password.criteria.policy.PolicyConstants;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by sundas on 5/28/2015.
 */
public class PasswordUtilsTest extends junit.framework.TestCase {

  private Set<String> cleanPasswords;

  private PasswordCriteria passwordCriteria;


  @org.junit.Before
  public void setUp() throws Exception { System.setProperty(PolicyConstants.POLICY_FILE,"D:/project/PhoenixRising/SunnyUtil/resource/policy.properties");
      passwordCriteria = CriteriaBuilderFactory.getCriteriaBuilder(PasswordStrengthPolicy.DEFAULT).
          buildCriteria();
  }

  @Test
  public void testNormalHappyPath(){
    cleanPasswords = new HashSet<>();
    cleanPasswords.add("$unny@12341");
    org.junit.Assert.assertEquals(PasswordStrength.STRONG,PasswordUtils.findPasswordStrength(cleanPasswords.iterator().next().toCharArray(),passwordCriteria));
  }

  @Test
  public void testForDictionaryWord(){
    cleanPasswords = new HashSet<>();
    cleanPasswords.add("dictionary");
    Iterator<String> iterator = cleanPasswords.iterator();
    while(iterator.hasNext()){
      org.junit.Assert.assertEquals(PasswordStrength.WEAK,PasswordUtils.findPasswordStrength(iterator.next().toCharArray(),passwordCriteria));
    }
  }

  @Test
  public void testExcludedCharactersWord(){
    cleanPasswords = new HashSet<>();
    cleanPasswords.add("$u@()123456789");
    Iterator<String> iterator = cleanPasswords.iterator();
    while(iterator.hasNext()){
      org.junit.Assert.assertEquals(PasswordStrength.WEAK,PasswordUtils.findPasswordStrength(iterator.next().toCharArray(),passwordCriteria));
    }
  }


  @Test
  public void testDoesNotContainMinCharactersWord(){
    cleanPasswords = new HashSet<>();
    cleanPasswords.add("$u@()");
    Iterator<String> iterator = cleanPasswords.iterator();
    while(iterator.hasNext()){
      org.junit.Assert.assertEquals(PasswordStrength.WEAK,PasswordUtils.findPasswordStrength(iterator.next().toCharArray(),passwordCriteria));
    }
  }


  @Test
  public void testContainMoreThanMaxCharactersWord(){
    cleanPasswords = new HashSet<>();
    cleanPasswords.add("$unny@123435345345363464564564654654645654645645645645645654654654654654645654");
    Iterator<String> iterator = cleanPasswords.iterator();
    while(iterator.hasNext()){
      org.junit.Assert.assertEquals(PasswordStrength.WEAK,PasswordUtils.findPasswordStrength(iterator.next().toCharArray(),passwordCriteria));
    }
  }

  @org.junit.After
  public void tearDown() throws Exception {

  }
}