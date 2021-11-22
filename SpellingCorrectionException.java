/**
SpellingCorrectionException is a subtype of SpellingException
*/
class SpellingCorrectionException extends SpellingException {
  /**
  defautl constructor
  */
  public SpellingCorrectionException(){
    super();
  }
  /**
  constructor with a String paremeter
  */
  public SpellingCorrectionException(String errorDisplay){
    //// Call constructor of parent Exception
    super(errorDisplay);
  }
}
