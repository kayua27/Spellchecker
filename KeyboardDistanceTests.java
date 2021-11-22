import org.junit.*;
import static org.junit.Assert.*;
/** class to test KeyboardDistance method distance() */

public class KeyboardDistanceTests{
  public static void main(String[] args) {
  org.junit.runner.JUnitCore.main("KeyboardDistanceTests");
  }
  // test if character are adjacent and return their distance
  KeyboardDistance c = new KeyboardDistance();
  @Test(timeout=1000) public void test_distance1(){
  assertEquals ("FAILED",c.distance("university","univerdity") ,7);
  }
  @Test(timeout=1000) public void test_distance2(){
  assertEquals ("FAILED",c.distance("test","test") ,0);
  }
  @Test(timeout=1000) public void test_distance3(){
    assertEquals ("FAILED",c.distance("u","u") ,0);
  }
  @Test(timeout=1000) public void test_distance4(){
  assertEquals ("FAILED",c.distance("mason","MASON") ,(int)Double.POSITIVE_INFINITY);
  }
  @Test(timeout=1000) public void test_distance5(){
  assertEquals ("FAILED",c.distance("vocal","vocak") ,5);
  }
  @Test(timeout=1000) public void test_distance6(){
  assertEquals ("FAILED",c.distance("vocaL","vocaK"),5);
  }
  @Test(timeout=1000) public void test_distance7(){
  assertEquals ("FAILED",c.distance("pom","[om;"),1);
  }
  @Test(timeout=1000) public void test_distance8(){
  assertEquals ("FAILED",c.distance("AxyLum","AxyKum") ,4);
  }
}
