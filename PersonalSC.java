import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/**
A spell checker which allows use of a personal dictionary. The
personal dictionary is initially read from a file though the file
may be non-existent in which case the personal dictionary is empty to
begin with.
*/
public class PersonalSC extends InteractiveSC {
  /** private field
  */
  private String [] personalDictFilename;
  private String perDictname;

  /**constructor is similar to its parent, it only has one extra parameter
  initialise class field to array of string by using readAllLines
  and personalDictFilename
  */
  PersonalSC
  (String dictFilename, boolean ignoreCase, Scanner input, PrintWriter output, String personalDictFilename){
    super(dictFilename, ignoreCase, input, output);
    this.personalDictFilename = SpellChecker.readAllLines(personalDictFilename);
    this.perDictname = personalDictFilename;

  }
  /** get the size of personalDictSize
  @return the size of the personal dictionary
  */
  public int personalDictSize(){
    int count = 0;
    count = this.personalDictFilename.length;
    return count;
  }

  /**
  Check if the word is correct according to the same methodology as
  the parent class.  If not, check whether the word appears in the
  personal dictionary associated with this spell checker.
  @param word
  @return true or false
  */
  @Override
  public boolean isCorrect(String word){
    boolean check = false;
    if (super.isCorrect(word) == true) {
      check = true;
    }
    else {
      for (int i =0 ; i < this.personalDictFilename.length ;++i ) {
        if (this.personalDictFilename[i].equals(word)) {
          check = true;
          break;
        }
        else if ((ignoreCase == true) && (this.personalDictFilename[i].equalsIgnoreCase(word))) {
          check = true;
          break;
        }
      }
    }
    return check;
  }

  /**If the parameter word is not in the system or personal dictionary, prompt
  the user on whether they would like to add it to the dictionary
  @param word to correct
  @return word corrected
  */
  @Override
 public String correctWord(String word){
   String wordCorrected = word;
   if (isCorrect(word) == true) {
     wordCorrected = super.correctWord(word);
   }
   else {
     output.println("@- **"+word+"** not in dictionary add it? (yes / no)");
     String answer = input.next();
     if (answer.equals("yes")) {
       append();
       this.personalDictFilename[this.personalDictFilename.length -1] = word;
     }
     else {
       wordCorrected = super.correctWord(word);
     }
   }
   return wordCorrected;
 }
 /** helper method for correctWord */
  private void append () {
    String [] copyArr = new String[this.personalDictFilename.length + 1];
    System.arraycopy(this.personalDictFilename, 0, copyArr, 0, this.personalDictFilename.length);
    this.personalDictFilename = copyArr;
  }

  /**returns all the words in personal dictionary
  */
   public String getAllPersonalDictWords(){
     String oneString = "";
     for (int i = 0; i < this.personalDictFilename.length ; ++i) {
       oneString += (this.personalDictFilename[i] + "\n");
     }
     return oneString;
   }

   /**
   saves the personal dictionary back to the original file that
  was used for opening it
  */
  public void savePersonalDict(){
    try{
      String data = getAllPersonalDictWords();
      File writeFile = new File(this.perDictname);
      PrintWriter f = new PrintWriter(writeFile);
      f.write(data);

      f.close();
    }
    catch (Exception e) {
      System.err.println(e);
    }
    output.println("@ Personal dictionary written to file "+ this.perDictname);
  }
}
