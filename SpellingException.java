/**
create own Exception by extending it to parent class Exception
*/
public class SpellingException extends Exception{
  /**
  defautl constructor
  */
    public SpellingException(){
      super();
    }
    /**
    constructor with a String paremeter
    */
    public SpellingException(String showError)
    {
        // Call constructor of parent Exception
        super(showError);
    }
}
