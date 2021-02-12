package model;// represents a pirate ship, with a size (corresponding to number of grid cells)
// x3 3 cell ship
// x1 4 cell ship
// x1 5 cell ship
// x1 2 cell ship

public class Ship implements IGamePiece {
  public final int size;
  private int lives;
  private boolean sunk;
  private boolean placed;

  public Ship (int size) {
    this.size = size;
    this.lives = size;
    this.sunk = false;
    this.placed = false;
  }

  /**
   * Changes subtracts from the lives of the ship, changes the status to sunk if there are no lives left
   */
  public void hit() {
    this.lives--;
    if (this.lives == 0) {
      this.sunk = true;
    }
  }

  void place() {
    this.placed = true;
  }

  @Override
  public String toString() {
    return "ship of size " + this.size;
  }

  @Override
  public boolean getSunk() {
    return this.sunk;
  }

  @Override
  public boolean getPlaced() {
    return this.placed;
  }
}
