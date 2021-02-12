package view;

import java.io.IOException;
import model.GameModel;

public class TextualView implements IView {
  private final GameModel game;
  private final Appendable ap;

  /**
   * Constructor for only the game.
   *
   * @param game
   */
  public TextualView(GameModel game) {
    this.game = game;
    this.ap = null;
  }

  /**
   * Constructor for game and appendable.
   *
   * @param game
   * @param ap
   */
  public TextualView(GameModel game, Appendable ap) {
    this.game = game;
    this.ap = ap;
  }

  public String toString(int turn) {
    if (game.isGameOver()) {
      return "Game over!";
    }

    String output = "";
    //p1
    if (turn == 0) {
      output += "--------------------\n"
          + "|     Player 1     |\n"
          + "--------------------\n"
          + "P1 personal screen\n" + game.getP1().get(0).toString() + "\n"
          + "P1 attack screen\n" + game.getP1().get(1).toString() + "\n";
      return output;
    }
    //p2
    else if (turn == 1) {
      output += "--------------------\n"
          + "|     Player 2     |\n"
          + "--------------------\n"
          + "P2 personal screen\n" + game.getP2().get(0).toString() + "\n"
          + "P2 attack screen\n" + game.getP2().get(1).toString() + "\n";
      return output;
    }
    else {
      throw new IllegalArgumentException("Not a valid turn");
    }
  }

  @Override
  public void render(int turn) throws IOException {
    try {
      this.ap.append(this.toString(turn) + "\n");
    } catch (IOException e) {
      throw new IOException();
    }
  }



}
