package model;

import java.util.ArrayList;

public class Grid {
  ArrayList<ArrayList<IGamePiece>> grid;

  /**
   * Constructor that creates a 10 x 10 grid of empty game pieces
   */
  public Grid() {
    this.grid = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      ArrayList<IGamePiece> tempRow = new ArrayList<>(10);
      for (int j = 0; j < 10; j++) {
        tempRow.add(new Empty());
      }
      grid.add(tempRow);
    }
  }

  /**
   * Replaces the user determined cells from empty to ship.
   *
   * @param firstX
   * @param firstY
   * @param secX
   * @param secY
   * @param ship
   */
  void placeShip(int firstX, int firstY, int secX, int secY, Ship ship) {
    if (firstX < 0 || firstX > 9 || firstY < 0 || firstY > 9 || secX < 0 || secX > 9 || secY < 0 || secY > 10) {
      throw new IllegalArgumentException("The given coordinates are outside the grid");
    }
    //ship is in the same row
    if (firstX - secX == 0) {
      int first;
      int second;
      //determines which is before the other so that the counter can count in that direction that increases
      if (firstY > secY) {
        first = firstY;
        second = secY;
      }
      else {
        first = secY;
        second = firstY;
      }

      for (int i = second; i <= first; i++) {
        //checks if that cell already has a ship
        if (grid.get(firstX).get(i) instanceof Ship) {
          throw new IllegalArgumentException("Cannot overlap ships");
        }
        //set cells to ship
        grid.get(firstX).set(i, ship);
      }
    }
    //ship is in the same column
    else if (firstY - secY == 0) {
      int first;
      int second;
      //determines which is before the other so that the counter can count in that direction that increases
      if (firstX > secX) {
        first = firstX;
        second = secX;
      }
      else {
        first = secX;
        second = firstX;
      }
      for (int i = second; i <= first; i++) {
        //checks if that cell already has a ship
        if (grid.get(i).get(firstY) instanceof Ship) {
          throw new IllegalArgumentException("Cannot overlap ships");
        }
        //set cells to ship
        grid.get(i).set(firstY, ship);
      }
    }
  }

  /**
   * Returns the game piece at the given x,y
   *
   * @param x
   * @param y
   * @return IGamePiece
   */
  IGamePiece get(int x, int y) {
    return this.grid.get(x).get(y);
  }

  /**
   * Returns the column at that row
   *
   * @param x
   * @return ArrayList
   */
  ArrayList get(int x) {
    return this.grid.get(x);
  }

  /**
   * Returns the ship that was hit, otherwise returns the empty game piece
   * Calls hit on that ship, so the lives decreases
   *
   * @param x
   * @param y
   * @return IGamePiece
   */
  IGamePiece hit(int x, int y) {
    if (grid.get(x).get(y) instanceof Ship) {
      ((Ship) grid.get(x).get(y)).hit();
    }
    //returns the ship that was hit, returns empty game piece if no ship is hit
    return grid.get(x).get(y);
  }

  @Override
  public String toString() {
    String output = "";

    for (int col = 0; col < this.grid.size(); col++) {
      for (int row = 0; row < this.grid.get(col).size(); row++) {
        //cell is empty
        if (this.grid.get(col).get(row) instanceof Empty) {
          output += "E ";
        }
        //cell contains a ship
        else if (this.grid.get(col).get(row) instanceof Ship) {
          output += "S ";
        }
        //cell was attacked and successfully hit a ship
        else if (this.grid.get(col).get(row) instanceof HitCannon) {
          output += "X ";
        }
        //cell was attacked and did not hit a ship
        else if (this.grid.get(col).get(row) instanceof MissCannon) {
          output += "O ";
        }
      }
      output += "\n";
    }

    return output;
  }

}
