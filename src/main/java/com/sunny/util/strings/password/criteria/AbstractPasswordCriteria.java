package com.sunny.util.strings.password.criteria;

import com.sunny.util.strings.password.PasswordStrength;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by sundas on 5/20/2015.
 */
public abstract class AbstractPasswordCriteria implements  PasswordCriteria {

  /*
  The minimum  length the password should have.
   */
  protected int minimumLength;


  /*
  The maximumLength that the password can have.
   */
  protected int maximumLength;


  public String getDelimiter() {
    return delimiter;
  }

  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  /**
   * Delimiter for complex keys
   */
  protected String delimiter;

  /*
  The password should must have all of  these characters
   */
  protected Set<Character>  mandatoryCharacters;


  public int getMinimumLength() {
    return minimumLength;
  }

  public AbstractPasswordCriteria() {
  }

  public void setMinimumLength(int minimumLength) {
    this.minimumLength = minimumLength;

  }

  public AbstractPasswordCriteria(int minimumLength, int maximumLength, Set<Character> mandatoryCharacters, Set<Character> excludedCharacters, Set<String> dictionary) {
    this.minimumLength = minimumLength;
    this.maximumLength = maximumLength;
    this.mandatoryCharacters = mandatoryCharacters;
    this.excludedCharacters = excludedCharacters;
    this.dictionary = dictionary;
  }

  public int getMaximumLength() {
    return maximumLength;
  }

  public void setMaximumLength(int maximumLength) {
    this.maximumLength = maximumLength;
  }

  public Set<Character> getMandatoryCharacters() {
    return mandatoryCharacters;
  }

  public void setMandatoryCharacters(Set<Character> mandatoryCharacters) {
    this.mandatoryCharacters = mandatoryCharacters;
  }

  public Set<Character> getExcludedCharacters() {
    return excludedCharacters;
  }

  public void setExcludedCharacters(Set<Character> excludedCharacters) {
    this.excludedCharacters = excludedCharacters;
  }

  public Set<String> getDictionary() {
    return dictionary;
  }

  public void setDictionary(Set<String> dictionary) {
    this.dictionary = dictionary;
  }

  /*

    The password should not have any of these characters
     */
  protected Set<Character> excludedCharacters;


  /**
   * The dictionary against which password strangth may be measured.
   */
  protected Set<String> dictionary;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AbstractPasswordCriteria that = (AbstractPasswordCriteria) o;

    if (minimumLength != that.minimumLength) return false;
    if (maximumLength != that.maximumLength) return false;
    if (mandatoryCharacters != null ? !mandatoryCharacters.equals(that.mandatoryCharacters) : that.mandatoryCharacters != null)
      return false;
    if (excludedCharacters != null ? !excludedCharacters.equals(that.excludedCharacters) : that.excludedCharacters != null)
      return false;
    return !(dictionary != null ? !dictionary.equals(that.dictionary) : that.dictionary != null);

  }

  @Override
  public int hashCode() {
    int result = minimumLength;
    result = 31 * result + maximumLength;
    result = 31 * result + (mandatoryCharacters != null ? mandatoryCharacters.hashCode() : 0);
    result = 31 * result + (excludedCharacters != null ? excludedCharacters.hashCode() : 0);
    result = 31 * result + (dictionary != null ? dictionary.hashCode() : 0);
    return result;
  }


  /**
   *
   * @param targetPassword
   * @return
   */
  public abstract PasswordStrength validate(char[] targetPassword);


  /**
   *
   * @param targetPassword
   * @return
   */
  protected PasswordStrength findPasswordStrength(char[] targetPassword){
    PasswordStrength passwordStrength = PasswordStrength.ERROR;
    if(targetPassword != null && targetPassword.length > 0){
      if(targetPassword.length < this.minimumLength || targetPassword.length > this.maximumLength){
        passwordStrength = PasswordStrength.WEAK;
      }
      else if(isDictionaryWord(targetPassword)) {
        passwordStrength = PasswordStrength.WEAK;
      }
      else if(!containsMandatoryCharacters(targetPassword)){
        passwordStrength = PasswordStrength.WEAK;
      }
      else if(containsExcludedCharacters(targetPassword)){
        passwordStrength = PasswordStrength.WEAK;
      }
      else if(targetPassword.length < this.getMinimumLength() || targetPassword.length > this.getMaximumLength()){
        passwordStrength = PasswordStrength.WEAK;
      }
      else{
        passwordStrength = PasswordStrength.STRONG;
      }
    }
    return passwordStrength;
  }


  /**
   *
   * @param targetPassword
   * @return
   */
  protected boolean containsExcludedCharacters(char[] targetPassword){
    boolean containsExcludedCharacters = false;
    Set<Character> excludedCharacters = this.getExcludedCharacters();
    if(excludedCharacters != null){
      Iterator<Character> iterator = excludedCharacters.iterator();
      while(iterator.hasNext()){
        char excludedCharacter = iterator.next();
        if(containsMandatoryCharacter(targetPassword, excludedCharacter)){
          containsExcludedCharacters = true;
          break;
        }
      }
    }
    return containsExcludedCharacters;
  }


  /**
   *
   * @param targetPassword
   * @return
   */
  protected boolean containsMandatoryCharacters(char[] targetPassword){
    boolean containsMandatoryCharacters = false;
    Set<Character> mandatoryCharacters = this.getMandatoryCharacters();
    if(mandatoryCharacters != null){
      containsMandatoryCharacters = true;
      Iterator<Character> iterator = mandatoryCharacters.iterator();
      while(iterator.hasNext()){
        char mandatoryCharacter = iterator.next();
        if(!containsMandatoryCharacter(targetPassword, mandatoryCharacter)){
          containsMandatoryCharacters = false;
          break;
        }
      }
    }
    return containsMandatoryCharacters;
  }


  /**
   *
   * @param targetPassword
   * @param mandatoryCharacter
   * @return
   */
  private boolean containsMandatoryCharacter(char[] targetPassword,char mandatoryCharacter){
    boolean contains = false;
    for(int i = 0 ; i < targetPassword.length ; i++){
      if(targetPassword[i] == mandatoryCharacter){
        contains = true;
        break;
      }
    }
    return contains;
  }



  /**
   *
   * @param targetPassword
   * @return
   */
  protected boolean isDictionaryWord(char[] targetPassword){
     return this.getDictionary().contains(new String(targetPassword));
  }

}
