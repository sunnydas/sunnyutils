package com.sunny.util.strings.password.criteria.builder.impl;

import com.sunny.util.strings.password.criteria.PasswordCriteria;
import com.sunny.util.strings.password.criteria.builder.CriteriaBuilder;
import com.sunny.util.strings.password.criteria.impl.DefaultPasswordCriteria;
import com.sunny.util.strings.password.criteria.policy.PasswordStrengthPolicy;
import com.sunny.util.strings.password.criteria.policy.PolicyConstants;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by sundas on 5/28/2015.
 */
public class DefaultCriteriaBuilder implements CriteriaBuilder {

  Logger logger = Logger.getLogger(DefaultCriteriaBuilder.class);

  @Override
  public PasswordCriteria buildCriteria()  {
    DefaultPasswordCriteria defaultPasswordCriteria = null;
    try {
      Properties criteriaProperties = loadProperties(System.getProperty(PolicyConstants.POLICY_FILE));
      if (criteriaProperties != null && !criteriaProperties.isEmpty()) {
        defaultPasswordCriteria = new DefaultPasswordCriteria();
        defaultPasswordCriteria.setDelimiter(criteriaProperties.getProperty("separator"));
        defaultPasswordCriteria.setDictionary(loadDictionary(criteriaProperties.getProperty("dictionaryFile")));
        defaultPasswordCriteria.setMandatoryCharacters(getMandatoryCharacters(criteriaProperties.
            getProperty("mandatoryCharacters"), defaultPasswordCriteria.getDelimiter()));
        defaultPasswordCriteria.setExcludedCharacters(getExcludeCharacters(criteriaProperties.
            getProperty("excludedCharacters"), defaultPasswordCriteria.getDelimiter()));
        defaultPasswordCriteria.setMinimumLength(Integer.parseInt(criteriaProperties.getProperty("minimumLength")));
        defaultPasswordCriteria.setMaximumLength(Integer.parseInt(criteriaProperties.getProperty("maximumLegth")));
      }
    } catch (Exception e){
      logger.error(e.getMessage(),e);
    }
    return defaultPasswordCriteria;
  }

  /**
   *
   * @param excludeCharacters
   * @param delimiter
   * @return
   */
  private Set<Character> getExcludeCharacters(String excludeCharacters,String delimiter){
    Set<Character> excludedCharacters = populateSet(excludeCharacters,delimiter);
    return excludedCharacters;
  }


  /**
   *
   * @param mandatoryCharacters
   * @param delimiter
   * @return
   */
  private Set<Character> getMandatoryCharacters(String mandatoryCharacters,String delimiter){
    Set<Character> mandatoryCharactersSet = populateSet(mandatoryCharacters,delimiter);
    return mandatoryCharactersSet;
  }


  /**
   *
   * @param chars
   * @param delimiter
   * @return
   */
  private Set<Character> populateSet(String chars,String delimiter){
    Set<Character> charSet = null;
    if(chars != null && delimiter != null){
      String[] splitted = chars.split(delimiter);
      for(String myStr : splitted){
        if(charSet == null){
          charSet = new HashSet<>();
        }
        charSet.add(myStr.charAt(0));
      }
    }
    return charSet;
  }


  /**
   *
   * @param dictionaryFile
   * @return
   */
  private Set<String>  loadDictionary(String dictionaryFile) throws IOException {
    Set<String> dictionary = null;
    if(dictionaryFile != null){
      FileReader fileReader = null;
      BufferedReader bufferedReader = null;
      try {
        fileReader = new FileReader(new File(dictionaryFile));
        bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while((line = bufferedReader.readLine()) != null){
           if(dictionary == null){
             dictionary = new HashSet<>();
           }
           dictionary.add(line);
        }
      } finally {
        if(bufferedReader != null){
          bufferedReader.close();
        }
      }
    }
    return dictionary;
  }


  /**
   *
   * @param fileName
   * @return
   * @throws IOException
   */
  private Properties loadProperties(String fileName) throws IOException {
    assert(fileName != null);
    Properties properties = new Properties();
    properties.load(new FileInputStream(new File(fileName)));
    return properties;
  }

}
