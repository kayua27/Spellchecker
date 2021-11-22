/**
A spell checker which automatically selects a correction for a
misspelled word.  It inherets most functionality from its parent
class but adjusts how correctWord(..) performs.
*/
public class AutomaticSC extends SpellChecker{
  /**
  create a comparator of interface StringComparator
  */
  private StringComparator comparator;

  /**
  Construct an automatic spell checker. Pass the required parameters to the
  parent class constructor.
  */
  public AutomaticSC(String dictFilename,
  boolean ignoreCase, StringComparator comparator){
    super(dictFilename, ignoreCase);
    this.comparator = new LevenshteinDistance();
  }

  /**
  Overloaded constructor that is missing the last parameter. In this case a
  LevenshteinDistance comparator is created
  */
  public AutomaticSC(String dictFilename, boolean ignoreCase){
    super(dictFilename, ignoreCase);
    this.comparator = new LevenshteinDistance();
  }

  /**
  Return a correction for the given word. The correction is the
  word in the dictionary which has the smallest distance from
  the given word. If there are ties, favor whichever word appears
  earlier in the dictionary.
  @param word is a string
  @return a string of the corrected word
  */
  @Override
  public String correctWord(String word){
    String accurateWord = this.dictWords[0];
    int minDistance = comparator.distance(word,this.dictWords[0]);

    if (this.ignoreCase == false) {
      for (int i =0 ; i < this.dictWords.length ; ++i ) {
        if (comparator.distance(word,this.dictWords[i]) < minDistance) {
          minDistance = comparator.distance(word,this.dictWords[i]);
          accurateWord = this.dictWords[i];
        }
      }
    }
    else {
      String lowerWord = word.toLowerCase();
      minDistance = comparator.distance(lowerWord,this.dictWords[0].toLowerCase());
      for (int i =0 ; i < this.dictWords.length ; ++i ) {
        this.dictWords[i] = this.dictWords[i].toLowerCase();
        if (comparator.distance(lowerWord,this.dictWords[i]) < minDistance) {
          minDistance = comparator.distance(lowerWord,this.dictWords[i]);
          accurateWord = this.dictWords[i];
        }
      }
      accurateWord = matchCase(word, accurateWord);
    }

    return accurateWord;
  }

  /**Utility method to handle case matching between words. Check if
  parameter model is all caps or only the first character is
  capitalized and transform source to match the capitalization.
  @param model word
  @param source word
  @return  a string of the transformed source word
  */
  public static String matchCase(String model, String source){
    int count = 0;
    boolean check = true;
    String s = source;

      for (int i = 0; i < model.length() ;++i ) {
        if (Character.isUpperCase(model.charAt(i)) == true) {
              ++count;
        }
        else if (!(Character.isUpperCase(model.charAt(i))) && (count > 1)) {
          s = source;
          break;
        }
      }
      if (count == model.length()) {
        s = source.toUpperCase();
      }
      else if ((count == 1) && (Character.isUpperCase(model.charAt(0)))) {
        s = source.substring(0, 1).toUpperCase() + source.substring(1);
      }
    return s;
  }
}
