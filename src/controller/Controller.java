package controller;

import java.io.IOException;
import java.util.Scanner;
import model.GameModel;
import view.IView;
import view.TextualView;

public class Controller implements IController {
  GameModel game;
  //0 --> player1
  //1 --> player2
  int turn;
  private final Readable rd;
  private final Appendable ap;

  public Controller(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Argument is null");
    } else {
      this.rd = rd;
      this.ap = ap;
    }
  }

  @Override
  public void play(GameModel game) {
    this.game = game;
    Scanner scan = new Scanner(this.rd);
    boolean gameOver = false;

    if (game == null) {
      throw new IllegalArgumentException("Game is null");
    }

    IView view = new TextualView(this.game, this.ap);
    this.turn = 10;

    while (!gameOver) {   //while the game is not over...

      //switch turns after every command
      if (turn == 0) {
        turn = 1;
      }
      else {
        turn = 0;
      }

      try {
        //add the game state exactly as it is to the apppendable
        view.render(turn);

        String userMove;
        if (scan.hasNext()) {
          userMove = scan.next();
        }
        else {
          throw new IllegalStateException();
        }

        switch (userMove) {
          case "place":
            int row1 = this.catchBadInputs(scan);
            //checks if user quit in the middle of giving arguments
            if (row1 == -1) {
              gameOver = true;
              this.ap.append("Game quit! ");
              this.ap.append("Game state when quit:\n");
              view.render(0);
              view.render(1);
              break;
            }
            int col1 = this.catchBadInputs(scan);
            //checks if user quit in the middle of giving arguments
            if (col1 == -1) {
              gameOver = true;
              this.ap.append("Game quit! ");
              this.ap.append("Game state when quit:\n");
              view.render(0);
              view.render(1);
              break;
            }
            int row2 = this.catchBadInputs(scan);
            //checks if user quit in the middle of giving arguments
            if (row2 == -1) {
              gameOver = true;
              this.ap.append("Game quit! ");
              this.ap.append("Game state when quit:\n");
              view.render(0);
              view.render(1);
              break;
            }
            int col2 = this.catchBadInputs(scan);
            //checks if user quit in the middle of giving arguments
            if (col2 == -1) {
              gameOver = true;
              this.ap.append("Game quit! ");
              this.ap.append("Game state when quit:\n");
              view.render(0);
              view.render(1);
              break;
            }
            int size = this.catchBadInputs(scan);
            //checks if user quit in the middle of giving arguments
            if (size == -1) {
              gameOver = true;
              this.ap.append("Game quit! ");
              this.ap.append("Game state when quit:\n");
              view.render(0);
              view.render(1);
              break;
            }
            this.game.placeShip(row1, col1, row2, col2, (size + 1), this.turn);
            gameOver = game.isGameOver();
            //view.render(this.turn);

            break;
          case "fire":
            int row = this.catchBadInputs(scan);
            //checks if user quit in the middle of giving arguments
            if (row == -1) {
              gameOver = true;
              this.ap.append("Game quit! ");
              this.ap.append("Game state when quit:\n");
              view.render(0);
              view.render(1);
              break;
            }
            int col = this.catchBadInputs(scan);
            //checks if user quit in the middle of giving arguments
            if (col == -1) {
              gameOver = true;
              this.ap.append("Game quit! ");
              this.ap.append("Game state when quit:\n");
              view.render(0);
              view.render(1);
              break;
            }
            this.ap.append(this.game.shoot(row, col, this.turn));
            gameOver = game.isGameOver();
            //view.render(turn);
            break;
          case "q":
          case "Q":
            gameOver = true;
            this.ap.append("Game quit! ");
            this.ap.append("Game state when quit:\n");
            view.render(0);
            view.render(1);
            break;
          default:
            this.ap.append("Invalid command, please enter again\n");
            break;
        }
      }
      catch (IOException e) {
        throw new IllegalStateException();
      }


    }

    try {
      this.ap.append(game.winner());
    }
    catch (IOException e) {
      throw new IllegalStateException();
    }


  }

  /**
   * catches if the user input is not valid (not q or not number).
   *
   * @param scan scanner object
   * @return integer that represents the card row/col or -1 if input is q
   */
  private int catchBadInputs(Scanner scan) {
    while (scan.hasNext()) {
      String input = scan.next();
      if (input.equals("q") || input.equals("Q")) {
        return -1;
      } else {
        try {
          return Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
          try {
            this.ap.append("Value is invalid, please re-enter value\n");
          } catch (IOException io) {
            throw new IllegalStateException();
          }
        }
      }
    }
    throw new IllegalStateException("Scanner doesn't have anymore inputs");
  }
}
