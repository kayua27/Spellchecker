import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/**provide an interactive spell checker which will search the document
from beginning to end presenting the user with misspelled words and prompt for corrections
*/
public class InteractiveSC extends SpellChecker {
  /**
  A Scanner which will provide input to the spell checker.
  */
  protected Scanner input;
  /**
  A PrintWriter which will allows output to be created by the spell checker.
  */
  protected PrintWriter output;

  /**
  constructor intializing the field and call constructor of the parent class
  */
  public InteractiveSC
  (String dictFilename,boolean ignoreCase,Scanner input,PrintWriter output){
    super(dictFilename, ignoreCase);
    this.input = input;
    this.output = output;
  }

  /**
  Override correctWord() of the parent class
  @param a word
  @return a Correction of the word
  */
  @Override
  public String correctWord(String word){
    this.output.println("@- Correction for "+super.correctWord(word)+":");
     word = this.input.next();
     this.output.println("@ Corrected to: "+ word);
     // System.out.println(word);
    return word;
  }

  /**Starting from the beginning of the document, apply corrections to all
  misspelled words. and throws SpellingException
  @param take a document to correct.
  */
  @Override
  public void correctDocument(Document doc) throws SpellingException {
    String docContent = "";
    String s = "";
    while (doc.hasNextWord() != false) {
      docContent = doc.nextWord();
      if (isCorrect(docContent) == false) {
        s = docContent;
        docContent = super.correctWord(docContent);
        doc.replaceLastWord(docContent);
         this.output.println("@ MISSPELLING in: "+ doc.currentLine());
         String correct_word = correctWord(s);
         doc.replaceLastWord(correct_word);
      }
    }
  }
}
