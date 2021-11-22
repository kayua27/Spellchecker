import java.util.Scanner;
import java.io.File;

/**
A class to do spell checking. This version only marks misspelled words with
 asterisks as in **mispeling**.  It serves as a parent class for other spell
checkers to inherit functionality to add features by overriding methods.
*/

public class SpellChecker{
  /** Array of words considered correct by spell checker */
  protected String [] dictWords;
  /**
  If true, ignore case when checking the spelling of words; otherwise
  capitalization differences will be counted as misspellings
  */
  protected boolean ignoreCase;

  /**
  Utility which reads all lines from a file and returns them as an array of
  strings. If problems are encountered during reading, return a string array
  of length 0 (empty).
  */
  public static String [] readAllLines(String filename){
    String [] arr;
    String lineOfArr = "";
    try {
      File readfile = new File(filename);
      if (readfile.length() == 0) {
        arr = new String [0];
        return arr;
      }
      Scanner sc = new Scanner(readfile);
      while (sc.hasNextLine()){
        lineOfArr += (sc.nextLine()+ ",");
      };
      arr = lineOfArr.split(",");
    }
    catch (Exception e) {
      System.err.println(e);
      arr = new String [0];
      return arr;
    }
    return arr;
  }

  /**
  Construct a spellchecker. dictFilename is the name of a file containing all
 words that are considered correct, one on each line; english-dict.txt is
 commonly used.  ignoreCase indicates whether case should be ignored or used
 when checking for word correctness against dictionary words.
  */
  public SpellChecker(String dictFilename, boolean ignoreCase){
    this.dictWords = readAllLines(dictFilename);
    this.ignoreCase = ignoreCase;
  }
/**
Return the size of the dictionary used by this spellchecker which is the
number of words read from the dictionary file and stored in the
dictWords array.
*/
  public int dictSize(){
    int count = 0;
    count = this.dictWords.length;
    return count;
  }

/**
Return true if the provided word is considered correct by the spell checker
and false otherwise. A word is correct if it is equal to a word in the
dictionaryWord array. It is also correct if case is being ignored and is
equal ignoring case to some word in the dictWords array.
*/
  public boolean isCorrect(String word){
    boolean check = false;
    for (int i =0 ; i < this.dictWords.length ;++i ) {
      if (this.dictWords[i].equals(word)) {
        check = true;
        break;
      }
      else if ((ignoreCase == true) && (this.dictWords[i].equalsIgnoreCase(word))) {
        check = true;
        break;
      }
    }
    return check;
  }

/**
Create a correction for the given word.  Return the word surrounded by
asterisks which mark it as incorrect as in the word "misunderestimated"
should become "**misunderestimated**".
*/
  public String correctWord(String word){
    String str = "";
    str = "**" + word + "**";
    return str;
  }

/**
From the beginning of the document, apply corrections to all words in the
document. Each misspelled word will be marked with asterisks according to
the correctWord() method.
*/
  public void correctDocument(Document doc) throws SpellingException {
    String docContent = "";
    while (doc.hasNextWord() != false) {
      docContent = doc.nextWord();
      if (isCorrect(docContent) == false) {
        docContent = correctWord(docContent);
        doc.replaceLastWord(docContent);
        doc.toString();
      }
    }
  }
}
