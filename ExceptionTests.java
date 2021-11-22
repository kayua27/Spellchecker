import org.junit.*;
import static org.junit.Assert.*;

/**
a class to test SpellingException and SpellingCorrectionException
*/
public class ExceptionTests{
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("ExceptionTests");
  }
// create a document and SpellingException instance
  Document doc = new Document("I am studying computer science.");
  SpellingException excpt = new SpellingCorrectionException();
  @Test(timeout=1000) public void test_spellingexception() {
  try{
    for (int i = 0 ; i < 20 ; ++i ) { // call nextWord() to test SpellingException
      doc.nextWord();
    }
  }
  // if SpellingException fails raise SpellingCorrectionException
  catch (SpellingCorrectionException excpt) { // if SpellingException fails raise
      assertEquals("FAILED SpellingException","No words remain in the document", excpt.getMessage());
  }
  catch (Exception e) {
    assertEquals("FAILED","No words remain in the document", e.getMessage());
  }
  }
}
