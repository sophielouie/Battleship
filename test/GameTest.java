import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.IController;
import java.io.IOException;
import java.io.StringReader;
import model.GameModel;
import model.Ship;
import org.junit.Test;

public class GameTest {

  //Q and q works to quit game

  @Test
  public void testGameQuit0() throws IOException {
    //capital Q quit
    Readable read = new StringReader("Q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
    assertTrue(append.toString().contains("Game quit!"));
  }

  @Test
  public void testGameQuit1() throws IOException {
    //capital Q quit
    Readable read = new StringReader("q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
    assertTrue(append.toString().contains("Game quit!"));
  }

  //------------------------------------------------------------------------------------------------

  //test that invalid commands don't do anything

  @Test
  public void testGameInvalid() throws IOException {
    //invalid command
    Readable read = new StringReader("hello place 1 2 1 4 3 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
    assertTrue(append.toString().contains("Invalid command, please enter again"));
  }

  //------------------------------------------------------------------------------------------------

  //test that program will wait for valid row, col

  @Test
  public void testGameBogus() throws IOException {
    //invalid command
    Readable read = new StringReader("hello place x 1 y 2 z 1 4 3 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);

    String p2Personal = "P2 personal screen\n"
        + "E S S S E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E ";

    assertTrue(append.toString().contains("Value is invalid, please re-enter value"));
    assertTrue(append.toString().contains(p2Personal));
  }

  //------------------------------------------------------------------------------------------------


  //regular placement test (vertical and horizontal)

  @Test
  public void testGame0() throws IOException {
    //p1 vertical and p2 horizontal
    Readable read = new StringReader("place 1 3 1 5 3 place 3 3 7 3 5 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
    System.out.print(append.toString());
  }

  @Test
  public void testGame1() throws IOException {
    //p1 horizontal and p2 vertical
    Readable read = new StringReader("place 1 10 4 10 4 place 8 5 8 7 2 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
    System.out.print(append.toString());
  }

  //------------------------------------------------------------------------------------------------

  //overlapping placement test

  @Test (expected = IllegalArgumentException.class)   //cannot overlap ships exception
  public void testGame2() throws IOException {
    //p1 overlapping at (5,5)
    Readable read = new StringReader("place 5 5 8 5 4 place 8 5 8 7 3 place 5 1 5 5 5 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)   //cannot overlap ships exception
  public void testGame3() throws IOException {
    //p2 overlapping at (8,5)
    Readable read = new StringReader("place 5 5 8 5 4 place 8 5 8 7 3 place 1 1 1 2 2 place 7 5 9 5 3 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  //------------------------------------------------------------------------------------------------

  //off the grid placement test (x and y)


  //p1

  @Test (expected = IllegalArgumentException.class)
  public void testGame4() throws IOException {
    //p1 y-position too high
    Readable read = new StringReader("place 2 8 -1 8 3 place q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame5() throws IOException {
    //p1 y-position too low
    Readable read = new StringReader("place 8 9 8 12 4 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame6() throws IOException {
    //p1 x-position too low
    Readable read = new StringReader("place -1 1 3 1 5 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame7() throws IOException {
    //p1 x-position too high
    Readable read = new StringReader("place 9 10 11 10 3 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  //p2

  @Test (expected = IllegalArgumentException.class)
  public void testGame8() throws IOException {
    //p2 y-position too high
    Readable read = new StringReader("place 3 5 5 5 3 place 2 8 -1 8 3 place q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame9() throws IOException {
    //p1 y-position too low
    Readable read = new StringReader("place 9 1 9 4 4 place 8 9 8 12 4 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame10() throws IOException {
    //p1 x-position too low
    Readable read = new StringReader("place 1 10 2 10 2 place -1 1 3 1 5 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame11() throws IOException {
    //p1 x-position too high
    Readable read = new StringReader("place 1 1 5 1 5 place 9 10 11 10 3 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  //------------------------------------------------------------------------------------------------

  //diagonal placement (aka (x,y) doesnt correspond)

  @Test (expected = IllegalArgumentException.class)
  public void testGame12() throws IOException {
    //p1 diagonal placement
    Readable read = new StringReader("place 1 1 4 4 4 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame13() throws IOException {
    //p2 diagonal placement
    Readable read = new StringReader("place 4 1 4 5 5 place 1 1 4 4 4 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  //------------------------------------------------------------------------------------------------

  //x,y doesn't correspond to ship size


  //p1

  @Test (expected = IllegalArgumentException.class)
  public void testGame14() throws IOException {
    //p1 ship size too big
    Readable read = new StringReader("place 4 1 4 5 6 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame15() throws IOException {
    //p1 ship size too small
    Readable read = new StringReader("place 4 1 4 5 1 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  //p2

  @Test (expected = IllegalArgumentException.class)
  public void testGame16() throws IOException {
    //p2 ship size too big
    Readable read = new StringReader("place 7 8 7 10 3 place 4 1 4 5 6 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGame17() throws IOException {
    //p2 ship size too small
    Readable read = new StringReader("place 10 1 7 1 4 place 4 1 4 5 1 q");
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  //------------------------------------------------------------------------------------------------

  String input = "place 1 1 1 3 3 place 3 4 7 4 5 place 1 10 1 8 3 place 5 5 5 6 2 "
      + "place 8 7 8 10 4 place 4 1 6 1 3 place 6 5 6 9 5 place 3 7 6 7 4 place 10 2 10 4 3 place 10 2 10 4 3 "
      + "place 4 2 4 3 2 place 8 7 8 9 3";

  //regular cannon shoot (hit and miss)

  //p1


  @Test
  public void testShoot0() throws IOException {
    //test p1 shoot - miss
    String in = input + " fire 2 9 q";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);

    String p1Attack = "E E E E E E E E E E \n"
        + "E E E E E E E E O E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E ";

    String p2Personal = "E E E E E E E E E E \n"
        + "E E E E E E E E O E \n"
        + "E E E S E E S E E E \n"
        + "S E E S E E S E E E \n"
        + "S E E S S S S E E E \n"
        + "S E E S E E S E E E \n"
        + "E E E S E E E E E E \n"
        + "E E E E E E S S S E \n"
        + "E E E E E E E E E E \n"
        + "E S S S E E E E E E ";

    assertTrue(append.toString().contains("Miss!"));
    assertTrue(append.toString().contains(p1Attack));
    assertTrue(append.toString().contains(p2Personal));
  }

  @Test
  public void testShoot1() throws IOException {
    //test p1 shoot - hit
    String in = input + " fire 4 4 q";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);

    String p1Attack = "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E X E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E ";

    String p2Personal = "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E S E E S E E E \n"
        + "S E E X E E S E E E \n"
        + "S E E S S S S E E E \n"
        + "S E E S E E S E E E \n"
        + "E E E S E E E E E E \n"
        + "E E E E E E S S S E \n"
        + "E E E E E E E E E E \n"
        + "E S S S E E E E E E ";

    assertTrue(append.toString().contains("Hit!"));
    assertTrue(append.toString().contains(p1Attack));
    assertTrue(append.toString().contains(p2Personal));
  }


  //p2


  @Test
  public void testShoot2() throws IOException {
    //test p2 shoot - hit
    String in = input + " fire 2 9 fire 1 1 Q";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);

    System.out.println(append.toString());

    String p2Attack = "X E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E ";

    String p1Personal = "X S S E E E E S S S \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E S S E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E S S S S S E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E S S S S \n"
        + "E E E E E E E E E E \n"
        + "E S S S E E E E E E ";

    //can test if it only says "hit" because we know from two tests above that the fire coordinates for player1 miss
    assertTrue(append.toString().contains("Hit!"));
    assertTrue(append.toString().contains(p2Attack));
    assertTrue(append.toString().contains(p1Personal));
  }

  @Test
  public void testShoot3() throws IOException {
    //test p2 shoot - miss
    String in = input + " fire 4 4 fire 2 2 q";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);

    String p2Attack = "E E E E E E E E E E \n"
        + "E O E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E E E E E ";

    String p1Personal = "S S S E E E E S S S \n"
        + "E O E E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E S S E E E E E E E \n"
        + "E E E E E E E E E E \n"
        + "E E E E S S S S S E \n"
        + "E E E E E E E E E E \n"
        + "E E E E E E S S S S \n"
        + "E E E E E E E E E E \n"
        + "E S S S E E E E E E ";

    //can test if it only says "miss" because we know from two tests above that the fire coordinates for player1 hit
    assertTrue(append.toString().contains("Miss!"));
    assertTrue(append.toString().contains(p2Attack));
    assertTrue(append.toString().contains(p1Personal));
  }

  //------------------------------------------------------------------------------------------------

  //off the grid cannon shoot

  @Test (expected = IllegalArgumentException.class)
  public void testBadShoot0() throws IOException {
    //p1 shoots off grid
    String in = input + " fire 2 11";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadShoot1() throws IOException {
    //p2 shoots off grid
    String in = input + "fire 9 5 fire 2 11";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  //------------------------------------------------------------------------------------------------

  //make sure that player cannot shoot before placing all their ships

  @Test (expected = IllegalStateException.class)
  public void testEarlyShot0() throws IOException {
    //p1 tries to fire a cannon before placing all her ships
    String in = "fire 9 5";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  @Test (expected = IllegalStateException.class)
  public void testEarlyShot1() throws IOException {
    //p2 tries to fire a cannon before placing all her ships
    String in = "place 1 5 1 7 3 fire 10 3";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
  }

  //------------------------------------------------------------------------------------------------

  //test tells player when a ship is sunk


  @Test
  public void testSunk0() throws IOException {
    //p2 sinks p1 ship at (4,2) (4,3)
    String in = input + " fire 10 2 fire 4 2 fire 10 3 fire 4 3 Q";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
    assertTrue(append.toString().contains("Hit, P2 has sunk a ship!"));
    assertEquals(game.getP2Hit(), 2);

    // check to make sure that P1 ship status was changed to sunk
    boolean sunkShip = false;
    for (Ship ship : game.getP1Ships()) {
      if (ship.size == 2 && ship.getSunk() == true) {
        sunkShip = true;
      }
    }
    assertTrue(sunkShip);
  }

  @Test
  public void testSunk1() throws IOException {
    //p1 sinks p2 ship at (10,2) (10,3) (10,4)
    String in = input + " fire 10 2 fire 9 10 fire 10 3 fire 4 3 fire 10 4 Q";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);
    assertTrue(append.toString().contains("Hit, P1 has sunk a ship!"));
    assertEquals(game.getP1Hit(), 3);

    // check to make sure that P2 ship status was changed to sunk
    boolean sunkShip = false;
    for (Ship ship : game.getP2Ships()) {
      if (ship.size == 3 && ship.getSunk() == true) {
        sunkShip = true;
      }
    }
    assertTrue(sunkShip);
  }


  //------------------------------------------------------------------------------------------------
  //String input = "place 1 1 1 3 3 place 3 4 7 4 5 place 1 10 1 8 3 place 5 5 5 6 2 "
  //    + "place 8 7 8 10 4 place 4 1 6 1 3 place 6 5 6 9 5 place 3 7 6 7 4 place 10 2 10 4 3 place 10 2 10 4 3 "
   //   + "place 4 2 4 3 2 place 8 7 8 9 3";

  //test game over (p1 and p2)


  //P1 ship placements:
  // 1 - (10,2) (10,3) (10,4)
  // 2 - (1,1) (1,2) (1,3)
  // 3 - (1, 10) (1,9), (1,8)
  // 4 - (8,7) (8,8) (8,9) (8, 10)
  // 5 - (6,5) (6,6) (6,7) (6,8) (6,9)
  // 6 - (4,2) (4,3)

  //P2 ship placements:
  // 1 - (3,4) (4,4) (5,4) (6,4) (7,4)
  // 2 - (5,5) (5,6)
  // 3 - (4,1) (5,1) (6,1)
  // 4 - (3,7) (4,7) (5,7) (6,7)
  // 5 - (10,2) (10,3) (10,4)
  // 6 - (8,7) (8,8) (8,9)

  @Test
  public void testGameOver0() throws IOException {
    //p1 sinks all of p2 ships
    String in = input + " fire 3 4 fire 10 2 fire 4 4 fire 10 3 fire 5 4 fire 10 4 fire 6 4 fire 1 1 fire 7 4 "
        + "fire 1 2 fire 5 5 fire 1 3 fire 5 6 fire 1 10 fire 4 1 fire 1 9 fire 5 1 fire 1 8 fire 6 1 fire 8 7 "
        + "fire 3 7 fire 8 8 fire 4 7 fire 8 9 fire 5 7 fire 8 10 fire 6 7 fire 6 5 fire 10 2 fire 6 6 "
        + "fire 10 3 fire 6 7 fire 10 4 fire 6 8 fire 8 7 fire 6 9 fire 8 8 fire 4 2 fire 8 9";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);

    assertTrue(append.toString().contains("Player 1 wins!"));
  }

  @Test
  public void testGameOver1() throws IOException {
    //p1 sinks all of p2 ships
    String in = input + " fire 3 4 fire 10 2 fire 4 4 fire 10 3 fire 5 4 fire 10 4 fire 6 4 fire 1 1 fire 7 4 "
        + "fire 1 2 fire 5 5 fire 1 3 fire 5 6 fire 1 10 fire 4 1 fire 1 9 fire 5 1 fire 1 8 fire 6 1 fire 8 7 "
        + "fire 3 7 fire 8 8 fire 4 7 fire 8 9 fire 5 7 fire 8 10 fire 6 7 fire 6 5 fire 10 2 fire 6 6 "
        + "fire 10 3 fire 6 7 fire 10 4 fire 6 8 fire 8 7 fire 6 9 fire 8 8 fire 4 2 fire 8 10 fire 4 3";
    Readable read = new StringReader(in);
    Appendable append = new StringBuilder();
    GameModel game = new GameModel();
    IController c = new Controller(read, append);
    c.play(game);

    assertTrue(append.toString().contains("Player 2 wins!"));
  }

}
