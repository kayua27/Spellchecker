/** A class to find distance between adjacent misspelled word. It is a child of
StringComparator.It houses a single method, distance(x,y).
*/
public class KeyboardDistance implements StringComparator{
  /**
  The distance of a mispelled word from a correct word depends on the location
  of the mispelling: if the mispelling is in the first character,
  the distance is 1, if it's in the second character, the distance is 2, and so
  forth. If the two words are the same, the distance is 0.
  If the two words cannot count as a misspelling of each other, the distance is infinite
  @param two string that represent a mispelling of each other
  @return the distance to turn x into y
  */
  public int distance(String x, String y){
    int countdistance = 0;
//iterate through string x
    for (int i = 0; i < x.length() ;++i ) {
      char letterX = x.charAt(i);
      char letterY = y.charAt(i);
// if characters at same index are different and are adjacent
      if ((letterX != letterY) && (findAdjacent(letterX , letterY) == true)) {
        countdistance = i+1;
        break;
      }
      else if ((letterX != letterY) && (findAdjacent(letterX , letterY) == false)) {
        countdistance = (int) Double.POSITIVE_INFINITY;
        break;
      }
    }
    return countdistance;
  }
  /** helper method for distance*/
    private boolean findAdjacent (char a, char b) {
    boolean result = false;
    char [][] keyboard = // 2D array representing character of keyboard
    {{'\t','q','w','e','r','t','y','u','i','o','p','['},
     {'a','s','d','f','g','h','j','k','l',';'},
      {'z','x','c','v','b','n','m',','}};
// if characters are upper case, turn them in lower case
    if ((Character.isUpperCase(a) == true) && (Character.isUpperCase(b) == true)) {
      a = Character.toLowerCase(a);
      b = Character.toLowerCase(b);
    }
    // iterate through keyboard
    for (int i = 0; i< keyboard.length;i++){
        for (int j = 0; j < keyboard[i].length;j++){
            if ((keyboard [i][j] == a) && (Character.isLetter(a))) {// if character is alphabet
              if ((j == 0) && (b == keyboard[i][j+1])) {
                result = true;
                break;
              }
              else if (( j == keyboard[i].length - 1) && (b == keyboard[i][j-1])) {
                result = true;
                break;
              }
              else if( ((j > 0) && ( j < keyboard[i].length - 1 )) &&
               ((b == keyboard[i][j-1]) || (b == keyboard[i][j+1]))) {
                  result = true;
                  break;
                }

            } // end if
        } // end for
    } // end for
    return result;
  }
}
