import controller.Controller;
import controller.IController;
import java.io.IOException;
import java.io.InputStreamReader;
import model.GameModel;

public class Pirateship {

  public static void main(String args[]) throws IOException {
    GameModel game = new GameModel();
    IController c = new Controller(new InputStreamReader(System.in), System.out);
    System.out.print("Rules for Pirateship\n"
        + "\nGame Objective:\n"
        + "The object of Pirateship is to try and sink all of the other player's ships before they sink all of your ships.\n"
        + "All of the other player's ships are somewhere on his/her board.  You try and hit them by entering"
        + " the coordinates of one of the cells on the board.\nThe other player also tries to hit your ships by entering"
        + " coordinates.  Neither you nor the other player can see the other's board so you must try to guess where they are. \n"
        + "\nStarting a New Game:\n"
        + "Each player places the 6 ships somewhere on their board.  The ships can only be placed vertically or horizontally."
        + " Diagonal placement is not allowed.\nNo part of a ship may hang off the edge of the board.\n "
        + " No ships may be placed on another ship. \n"
        + "Once the guessing begins, the players may not move the ships.\n"
        + "The 5 ships are:  Carrier (occupies 5 spaces), Battleship (4), Cruiser (3), Submarine (3) X 2, and Destroyer (2).  \n"
        + "\nPlaying the Game:\n"
        + "Player's take turns guessing by entering the coordinates. The program responds with \"hit\" or \"miss\" as appropriate. \n"
        + "To place a ship enter \"place\" followed by the row and col of the starting cell you want the ship to be placed at\n"
        + "and the row and col of the ending cell you want the ship to be placed at and the size of the ship you want to place.\n"
        + "\t\t place int row1,  int col2,  int row2, int col2, int size\n"
        + "To fire a cannon at the opponent enter \"fire\" followed by the row and col of the cell you want to attack.\n"
        + "You cannot shoot a cannon before placing all of your ships. \n"
        + "\t\t fire int row, int col\n"
        + "When all of the cells that one your ships occupies have been hit, the ship will be sunk. \n"
        + "As soon as all of one player's ships have been sunk, the game ends.\n\n");
    c.play(game);
  }

}
